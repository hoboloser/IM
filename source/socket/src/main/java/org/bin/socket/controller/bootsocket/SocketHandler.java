package org.bin.socket.controller.bootsocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import org.bin.socket.constant.Constant;
import org.bin.socket.entity.ChatMessage;
import org.bin.socket.entity.FriendGroupMember;
import org.bin.socket.entity.FriendMapper;
import org.bin.socket.enums.ChatType;
import org.bin.socket.enums.MessageType;
import org.bin.socket.enums.PushType;
import org.bin.socket.enums.ReadType;
import org.bin.socket.enums.UserStatus;
import org.bin.socket.service.MemberService;
import org.bin.socket.service.MessageService;
import org.bin.socket.service.UserGroupService;
import org.bin.socket.vo.LinkVO;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class SocketHandler extends TextWebSocketHandler {

	private static final AtomicInteger connects = new AtomicInteger(0);
	
	private static CopyOnWriteArraySet<WebSocketSession> set = new CopyOnWriteArraySet<WebSocketSession>();

	private static ConcurrentHashMap<String, Object> route = new ConcurrentHashMap<String, Object>();

	private static ConcurrentHashMap<String, List<String>> unread = new ConcurrentHashMap<String, List<String>>();
	
	private static List<String> userList = new ArrayList<String>();
	
	private final UserGroupService userGroupService;
	
	private final MemberService memberService;
	
	private final MessageService messageService;
	
	public SocketHandler( UserGroupService userGroupService,MemberService memberService,MessageService messageService) {
		this.userGroupService = userGroupService;
		this.memberService = memberService;
		this.messageService = messageService;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		
		Map map = session.getAttributes();
		String userid = (String) map.get(Constant.LOGIN_WEBSOCKET_SESSION);
		if(!userList.contains(userid)){
			set.add(session);
			connects.incrementAndGet();
			route.put(userid, session);
			userList.add(userid);
		}
		String message = getMessage("[" + userid + "]加入聊天室,当前在线人数为" + connects.get() + "位","notice", userList);
		broadcast(message); // 广播
		List<FriendMapper> friends = userGroupService.findFriendsByAccount(userid);
		if(friends != null && !friends.isEmpty()){
			for (FriendMapper friendMapper : friends) {
				LinkVO vo = new LinkVO();
				vo.setMessage(Constant.ONLINE_MSG);
				vo.setStatus(UserStatus.ONLINE);
				vo.setType(PushType.ONLINE);
				vo.setUserid(userid);
				if(route.get(friendMapper.getFriendAccount()) != null){
					singleSend(JSON.toJSONString(vo), (WebSocketSession) route.get(friendMapper.getFriendAccount()));
				}
			}
		}
		List<ChatMessage> unlist = messageService.queryUnReadMessage(userid);
		if(unlist != null && !unlist.isEmpty()){
			for (ChatMessage chatMessage : unlist) {
				singleSend(chatMessage.getText(), (WebSocketSession) route.get(userid));
			}
		}
	}

	/**
	 * handleMessage 与 handleTextMessage 不可同时用
	 */
	// @Override
	// public void handleMessage(WebSocketSession session,WebSocketMessage<?>
	// message) throws Exception {
	// System.out.println("handleMessage");
	// }

	@Override
	protected void handleTextMessage(WebSocketSession session,TextMessage message) throws Exception {
		String echoMessage = message.getPayload();
		JSONObject socket = JSON.parseObject(echoMessage);
		JSONObject messages = JSON.parseObject(socket.get("message").toString());
		String chatType = (String)messages.get("chatType");
		String msgType = (String)messages.get("msgType");
		String roomId = String.valueOf(messages.get("room")); //聊天室信息
		String from = String.valueOf(messages.get("from"));
		
		if(ChatType.SIGNLE.toString().equals(chatType)){
			String to = String.valueOf(messages.get("to"));
			singleChatMsg(from, to, roomId, echoMessage, msgType);
		}else if(ChatType.GROUP.toString().equals(chatType)){
			String group = String.valueOf(messages.get("group"));
			doubleChatMsg(from, roomId, group, echoMessage, msgType);
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session,Throwable exception) throws Exception {
		System.out.println("handleTransportError");
		session.close(CloseStatus.SERVER_ERROR);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception {
		set.remove(session);
		connects.decrementAndGet();
		Map map = session.getAttributes();
		String userId = (String) map.get(Constant.LOGIN_WEBSOCKET_SESSION);
		userList.remove(userId);
		route.remove(userId);
		String message = getMessage("[" + userId + "]离开聊天室,当前在线人数为" + connects.get() + "位","notice", userList);
		broadcast(message); // 广播
		List<FriendMapper> friends = userGroupService.findFriendsByAccount(userId);
		if(friends != null && !friends.isEmpty()){
			for (FriendMapper friendMapper : friends) {
				LinkVO vo = new LinkVO();
				vo.setMessage(Constant.OFFLINE_MSG);
				vo.setStatus(UserStatus.OFFLINE);
				vo.setType(PushType.OFFLINE);
				vo.setUserid(userId);
				if(route.get(friendMapper.getFriendAccount()) != null){
					singleSend(JSON.toJSONString(vo), (WebSocketSession) route.get(friendMapper.getFriendAccount()));
					
				}
			}
		}
	}

	/**
	 * 私聊
	 */
	private void singleChatMsg(String from,String to,String roomId,String message,String msgType){
		//发送给自已
		singleSend(message, (WebSocketSession) route.get(from));
		//判断对方是否在线
		ReadType isRead = null;
		if(route.get(to) == null){
			List<String> unreadlist = unread.get(to);
			if(unreadlist == null){
				unreadlist = new ArrayList<String>();
			}
			unreadlist.add(message);
			unread.put(to, unreadlist);
			isRead = ReadType.UNREAD;
		}else{
			isRead = ReadType.READ;
			singleSend(message, (WebSocketSession) route.get(to)); // 分别发送给每个指定用户
		}
		messageService.sendMessage(Long.parseLong(roomId),from, to, message, MessageType.valueOf(msgType),isRead);
	}
	
	/**
	 * 群聊
	 */
	private void doubleChatMsg(String from,String roomId,String group,String message,String msgType){
		singleSend(message, (WebSocketSession) route.get(from));
		List<FriendGroupMember> ls = memberService.findGroupMember(group);
		for (FriendGroupMember friendGroupMember : ls) {
			if (!friendGroupMember.getAccount().equals(from)) {
				singleSend(message, (WebSocketSession) route.get(friendGroupMember.getAccount()));
			}
		}
		messageService.sendGroupMessage(Long.parseLong(roomId), from, message, MessageType.valueOf(msgType));
	}

	/**
	 * 广播消息
	 * 
	 * @param message
	 */
	public void broadcast(String message) {
		for (WebSocketSession session : set) {
			try {
				session.sendMessage(new TextMessage(message));
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	/**
	 * 对特定用户发送消息
	 * 
	 * @param message
	 * @param session
	 */
	public void singleSend(String message, WebSocketSession session) {
		try {
			session.sendMessage(new TextMessage(message));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 组装返回给前台的消息
	 * 
	 * @param message
	 *            交互信息
	 * @param type
	 *            信息类型
	 * @param list
	 *            在线列表
	 * @return
	 */
	public String getMessage(String message, String type, List list) {
		JSONObject member = new JSONObject();
		member.put("message", message);
		member.put("type", type);
		member.put("list", list);
		return member.toString();
	}
}

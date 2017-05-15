//package org.bin.socket.controller.socket;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.CopyOnWriteArraySet;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import javax.servlet.http.HttpSession;
//import javax.websocket.EndpointConfig;
//import javax.websocket.OnClose;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.ServerEndpoint;
//
//import org.bin.socket.constant.Constant;
//import org.bin.socket.controller.other.HttpSessionConfigurator;
//import org.bin.socket.entity.ChatMessage;
//import org.bin.socket.entity.FriendGroupMember;
//import org.bin.socket.entity.FriendMapper;
//import org.bin.socket.enums.ChatType;
//import org.bin.socket.enums.MessageType;
//import org.bin.socket.enums.PushType;
//import org.bin.socket.enums.ReadType;
//import org.bin.socket.enums.UserStatus;
//import org.bin.socket.init.BeanFactory;
//import org.bin.socket.service.MemberService;
//import org.bin.socket.service.MessageService;
//import org.bin.socket.service.UserGroupService;
//import org.bin.socket.vo.LinkVO;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//
//@Component
//@ServerEndpoint(value = "/socketServer", configurator = HttpSessionConfigurator.class)
//public class SocketServer {
//
//	private static final AtomicInteger connects = new AtomicInteger(0);
//
//	/**
//	 * 读写分离，正常读，加锁写，写为复制一份，为新的list
//	 */
//	private static CopyOnWriteArraySet<SocketServer> set = new CopyOnWriteArraySet<SocketServer>();
//
//	private String userId;
//
//	private Session session;
//
//	private HttpSession httpSession;
//
//	private static List<String> userList = new ArrayList<String>();
//
//	private static ConcurrentHashMap<String, Object> route = new ConcurrentHashMap<String, Object>();
//
//	private static ConcurrentHashMap<String, List<String>> unread = new ConcurrentHashMap<String, List<String>>();
//	
//	private UserGroupService userGroupService = null;
//	
//	private MemberService memberService = null;
//	
//	private MessageService messageService = null;
//	/**
//	 * 用户接入
//	 */
//	@OnOpen
//	public void connect(Session session, EndpointConfig config) {
//		// 连接
//		// 更新上线数
//		// 更新用户状态
//		// 广播至上线通知
//		// 更新好友在线数量
//		this.session = session;
//		set.add(this);
//		connects.incrementAndGet();
//		this.userId = session.getRequestParameterMap().get("userid").get(0);
//		userList.add(userId);
//		route.put(userId, session);
//		String message = getMessage("[" + userId + "]加入聊天室,当前在线人数为" + connects.get() + "位","notice", userList);
//		broadcast(message); // 广播
//		
//		userGroupService = (UserGroupService)BeanFactory.getBean("userGroupService");
//		messageService = (MessageService)BeanFactory.getBean("messageService");
//		
//		List<FriendMapper> friends = userGroupService.findFriendsByAccount(userId);
//		if(friends != null && !friends.isEmpty()){
//			for (FriendMapper friendMapper : friends) {
//				LinkVO vo = new LinkVO();
//				vo.setMessage(Constant.ONLINE_MSG);
//				vo.setStatus(UserStatus.ONLINE);
//				vo.setType(PushType.ONLINE);
//				vo.setUserid(userId);
//				if(route.get(friendMapper.getFriendAccount()) != null){
//					singleSend(JSON.toJSONString(vo), (Session) route.get(friendMapper.getFriendAccount()));
//				}
//			}
//		}
//		List<ChatMessage> unlist = messageService.queryUnReadMessage(userId);
//		if(unlist != null && !unlist.isEmpty()){
//			for (ChatMessage chatMessage : unlist) {
//				singleSend(chatMessage.getText(), (Session) route.get(userId));
//			}
//		}
//	}
//
//	/**
//	 * 连接关闭
//	 */
//	@OnClose
//	public void disconnect() {
//		set.remove(this);
//		connects.decrementAndGet();
//		userList.remove(userId);
//		route.remove(userId);
//		String message = getMessage("[" + userId + "]加入聊天室,当前在线人数为" + connects.get() + "位","notice", userList);
//		broadcast(message); // 广播
//		userGroupService = (UserGroupService)BeanFactory.getBean("userGroupService");
//		List<FriendMapper> friends = userGroupService.findFriendsByAccount(userId);
//		if(friends != null && !friends.isEmpty()){
//			for (FriendMapper friendMapper : friends) {
//				LinkVO vo = new LinkVO();
//				vo.setMessage(Constant.OFFLINE_MSG);
//				vo.setStatus(UserStatus.OFFLINE);
//				vo.setType(PushType.OFFLINE);
//				vo.setUserid(userId);
//				if(route.get(friendMapper.getFriendAccount()) != null){
//					singleSend(JSON.toJSONString(vo), (Session) route.get(friendMapper.getFriendAccount()));
//					
//				}
//			}
//		}
//		
//	}
//
//	/**
//	 * 发送消息
//	 */
//	@OnMessage
//	public void message(String message, Session session) {
//		JSONObject socket = JSON.parseObject(message);
//		JSONObject messages = JSON.parseObject(socket.get("message").toString());
//		String chatType = (String)messages.get("chatType");
//		String msgType = (String)messages.get("msgType");
//		String roomId = String.valueOf(messages.get("room")); //聊天室信息
//		String from = String.valueOf(messages.get("from"));
//		messageService = (MessageService)BeanFactory.getBean("messageService");
//		
//		if(ChatType.SIGNLE.toString().equals(chatType)){
//			String to = String.valueOf(messages.get("to"));
//			singleChatMsg(from, to, roomId, message, msgType);
//		}else if(ChatType.GROUP.toString().equals(chatType)){
//			String group = String.valueOf(messages.get("group"));
//			doubleChatMsg(from, roomId, group, message, msgType);
//		}
//	}
//	/**
//	 * 私聊
//	 */
//	private void singleChatMsg(String from,String to,String roomId,String message,String msgType){
//		//发送给自已
//		singleSend(message, (Session) route.get(from));
//		//判断对方是否在线
//		ReadType isRead = null;
//		if(route.get(to) == null){
//			List<String> unreadlist = unread.get(to);
//			if(unreadlist == null){
//				unreadlist = new ArrayList<String>();
//			}
//			unreadlist.add(message);
//			unread.put(to, unreadlist);
//			isRead = ReadType.UNREAD;
//		}else{
//			isRead = ReadType.READ;
//			singleSend(message, (Session) route.get(to)); // 分别发送给每个指定用户
//		}
//		messageService.sendMessage(Long.parseLong(roomId),from, to, message, MessageType.valueOf(msgType),isRead);
//	}
//	
//	/**
//	 * 群聊
//	 */
//	private void doubleChatMsg(String from,String roomId,String group,String message,String msgType){
//		memberService = (MemberService)BeanFactory.getBean("memberService");
//		singleSend(message, (Session) route.get(from));
//		List<FriendGroupMember> ls = memberService.findGroupMember(group);
//		for (FriendGroupMember friendGroupMember : ls) {
//			if (!friendGroupMember.getAccount().equals(from)) {
//				singleSend(message, (Session) route.get(friendGroupMember.getAccount()));
//			}
//		}
//		messageService.sendGroupMessage(Long.parseLong(roomId), from, message, MessageType.valueOf(msgType));
//	}
//
//	/**
//	 * 广播消息
//	 * 
//	 * @param message
//	 */
//	public void broadcast(String message) {
//		for (SocketServer socket : set) {
//			try {
//				socket.session.getBasicRemote().sendText(message);
//			} catch (IOException e) {
//				e.printStackTrace();
//				continue;
//			}
//		}
//	}
//
//	/**
//	 * 对特定用户发送消息
//	 * 
//	 * @param message
//	 * @param session
//	 */
//	public void singleSend(String message, Session session) {
//		try {
//			session.getBasicRemote().sendText(message);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 组装返回给前台的消息
//	 * 
//	 * @param message
//	 *            交互信息
//	 * @param type
//	 *            信息类型
//	 * @param list
//	 *            在线列表
//	 * @return
//	 */
//	public String getMessage(String message, String type, List list) {
//		JSONObject member = new JSONObject();
//		member.put("message", message);
//		member.put("type", type);
//		member.put("list", list);
//		return member.toString();
//	}
//}

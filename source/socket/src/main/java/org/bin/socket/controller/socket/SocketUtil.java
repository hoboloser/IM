package org.bin.socket.controller.socket;

import java.util.List;

import org.bin.socket.entity.ChatMessage;
import org.bin.socket.entity.FriendGroupMember;
import org.bin.socket.entity.FriendMapper;
import org.bin.socket.enums.MessageType;
import org.bin.socket.enums.ReadType;
import org.bin.socket.service.MemberService;
import org.bin.socket.service.MessageService;
import org.bin.socket.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;

public class SocketUtil {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private UserGroupService userGroupService;
	
	public List<FriendMapper> method01(String userid){
		List<FriendMapper> friends = userGroupService.findFriendsByAccount(userid);
		return friends;
	}
	
	public List<FriendGroupMember> method05(String group){
		List<FriendGroupMember> ls = memberService.findGroupMember(group);
		return ls;
	}
	
	public List<ChatMessage> method02(String userid){
		List<ChatMessage> unlist = messageService.queryUnReadMessage(userid);
		return unlist;
	}
	
	public void method03(String from,String to,String roomId,String message,MessageType msgType,ReadType isRead ){
		messageService.sendMessage(Long.parseLong(roomId),from, to, message, msgType,isRead);
	}
	
	public void method04(String from,String roomId,String group,String message,MessageType msgType){
		messageService.sendGroupMessage(Long.parseLong(roomId), from, message, msgType);
	}
	
	
	
}

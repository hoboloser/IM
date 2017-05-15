package org.bin.socket.service.impl;

import java.util.Date;
import java.util.List;

import org.bin.socket.dao.ChatRoomDAO;
import org.bin.socket.dao.DoubleChatRoomDAO;
import org.bin.socket.entity.ChatRoom;
import org.bin.socket.entity.DoubleChatRoom;
import org.bin.socket.enums.RoomType;
import org.bin.socket.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roomService")
public class RoomServiceImpl implements RoomService {

	@Autowired
	private ChatRoomDAO chatRoomDAO;
	
	@Autowired
	private DoubleChatRoomDAO doubleChatRoomDAO;
	
	@Override
	public ChatRoom generateRoom(String from, String to) {
		List<ChatRoom> list = chatRoomDAO.findChatRoomLocal(to, from);
		if(list == null || list.isEmpty()){
			list = chatRoomDAO.findChatRoomLocal(from, to);
			if(list == null || list.isEmpty()){
				ChatRoom cr = new ChatRoom();
				cr.setCreateTime(new Date());
				cr.setFrom(from);
				cr.setTo(to);
				chatRoomDAO.addChatRoomLocal(cr);
				return cr;
			}
		}
		return list.get(0);
	}

	@Override
	public DoubleChatRoom generateGroupRoom(String groupId) {
		return generate(groupId,RoomType.GROUP);
	}

	private DoubleChatRoom generate(String groupId,RoomType type){
		List<DoubleChatRoom> list = doubleChatRoomDAO.findDoubleChatRoomLocal(groupId, type.toString());
		if(list == null || list.isEmpty()){
			DoubleChatRoom dcr = new DoubleChatRoom();
			dcr.setCreateTime(new Date());
			dcr.setGroupId(groupId);
			dcr.setType(type);
			doubleChatRoomDAO.addDoubleChatRoomLocal(dcr);
			return dcr;
		}
		return list.get(0);
	}
	@Override
	public DoubleChatRoom generateDiscuRoom(String groupId) {
		return generate(groupId,RoomType.DISCUSSION);
	}
 
}

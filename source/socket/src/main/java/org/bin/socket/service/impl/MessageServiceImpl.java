package org.bin.socket.service.impl;

import java.util.Date;
import java.util.List;

import org.bin.socket.dao.ChatMessageDAO;
import org.bin.socket.entity.ChatMessage;
import org.bin.socket.enums.ChatType;
import org.bin.socket.enums.MessageType;
import org.bin.socket.enums.ReadType;
import org.bin.socket.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhicall.care.mybatis.page.Page;

@Service("messageService")
public class MessageServiceImpl implements MessageService{

	@Autowired
	private ChatMessageDAO chatMessageDAO;

	@Override
	public ChatMessage sendMessage(long roomId, String from, String to, String message,	MessageType type,ReadType isRead) {
		ChatMessage cm = generate(from, to, message, roomId, ChatType.SIGNLE, type,isRead);
		chatMessageDAO.addChatMessageLocal(cm);
		return cm;
	}

	private ChatMessage generate(String from, String to, String message,Long roomId,ChatType chatType,	MessageType type,ReadType isRead){
		ChatMessage cm = new ChatMessage();
		cm.setFrom(from);
		cm.setTo(to);
		cm.setType(chatType);
		cm.setCreateTime(new Date());
		if(MessageType.TEXT.equals(type)){
			cm.setText(message);
		}else if(MessageType.AUDIO.equals(type)){
			cm.setAudio(message);
		}else if(MessageType.IMAGE.equals(type)){
			cm.setImage(message);
		}
		if(roomId != null){
			cm.setRoomId(roomId);
		}
		cm.setIsRead(isRead);
		return cm;
	}
	@Override
	public ChatMessage sendGroupMessage(long roomId, String from, String message,	MessageType type) {
		ChatMessage cm = generate(from, null, message, roomId, ChatType.GROUP, type,ReadType.READ);
		chatMessageDAO.addChatMessageLocal(cm);
		return cm;
	}

	@Override
	public ChatMessage sendDiscuMessage(long roomId, String from, String message,	MessageType type) {
		ChatMessage cm = generate(from, null, message, roomId, ChatType.DISCUSS, type,ReadType.READ);
		chatMessageDAO.addChatMessageLocal(cm);
		return cm;
	}

	@Override
	public Page<ChatMessage> queryChatMessage(long roomId,String from, String to, int pageNum, int pageSize) {
		return chatMessageDAO.findChatMessageByPageLocal(from, to, null, ChatType.SIGNLE, pageNum, pageSize);
	}

	@Override
	public Page<ChatMessage> queryGroupMessage(long roomId, int pageNum, int pageSize) {
		return chatMessageDAO.findChatMessageByPageLocal(null, null, roomId, ChatType.GROUP, pageNum, pageSize);
	}

	@Override
	public Page<ChatMessage> queryDiscussMessage(long roomId, int pageNum, int pageSize) {
		return chatMessageDAO.findChatMessageByPageLocal(null, null, roomId, ChatType.DISCUSS, pageNum, pageSize);
	}

	@Override
	public List<ChatMessage> queryUnReadMessage(String account) {
		List<ChatMessage> unread = chatMessageDAO.findChatMessageLocal(null, account, null, null, ReadType.UNREAD);
		
		chatMessageDAO.updateUnReadMessage(null, account, ReadType.UNREAD);
		return unread;
	}


}

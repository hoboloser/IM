package org.bin.socket.service;

import java.util.List;

import org.bin.socket.entity.ChatMessage;

import com.zhicall.care.mybatis.page.Page;

public interface ChatMessageService {

	public List<ChatMessage> findChatMessageLocal();
	
	public Page<ChatMessage> findChatMessageByPageLocal(int pageNum,int pageSize);
	
	public Long addChatMessageLocal(ChatMessage chatMessage); 

    public ChatMessage findChatMessageByIdLocal(long id);
    
    public void updateChatMessageLocal(ChatMessage chatMessage);
    
}

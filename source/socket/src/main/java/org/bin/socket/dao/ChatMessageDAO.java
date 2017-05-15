package org.bin.socket.dao;

import java.util.List;

import org.bin.socket.entity.ChatMessage;
import org.bin.socket.enums.ChatType;
import org.bin.socket.enums.ReadType;

import com.zhicall.care.mybatis.page.Page;

public interface ChatMessageDAO {

	public List<ChatMessage> findChatMessageLocal(String from,String to,Long roomId,ChatType type,ReadType isRead);
	
	public Page<ChatMessage> findChatMessageByPageLocal(String from,String to,Long roomId,ChatType type,int pageNum,int pageSize);
	
	public Long addChatMessageLocal(ChatMessage chatMessage); 

    public ChatMessage findChatMessageByIdLocal(long id);
    
    public void updateChatMessageLocal(ChatMessage chatMessage);
    
    public void updateUnReadMessage(String from,String to,ReadType isRead);
}

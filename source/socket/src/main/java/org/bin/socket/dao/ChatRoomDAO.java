package org.bin.socket.dao;

import java.util.List;

import org.bin.socket.entity.ChatRoom;

import com.zhicall.care.mybatis.page.Page;

public interface ChatRoomDAO {

	public List<ChatRoom> findChatRoomLocal(String to,String from);
	
	public Page<ChatRoom> findChatRoomByPageLocal(int pageNum,int pageSize);
	
	public Long addChatRoomLocal(ChatRoom chatRoom); 

    public ChatRoom findChatRoomByIdLocal(long id);
    
    public void updateChatRoomLocal(ChatRoom chatRoom);
}

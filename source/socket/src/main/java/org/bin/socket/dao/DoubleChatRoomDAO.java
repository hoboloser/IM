package org.bin.socket.dao;

import java.util.List;

import org.bin.socket.entity.DoubleChatRoom;

import com.zhicall.care.mybatis.page.Page;

public interface DoubleChatRoomDAO {
	
	public List<DoubleChatRoom> findDoubleChatRoomLocal(String groupId,String type);
	
	public Page<DoubleChatRoom> findDoubleChatRoomByPageLocal(int pageNum,int pageSize);
	
	public Long addDoubleChatRoomLocal(DoubleChatRoom doubleChatRoom); 

    public DoubleChatRoom findDoubleChatRoomByIdLocal(long id);
    
    public void updateDoubleChatRoomLocal(DoubleChatRoom doubleChatRoom);
    
}

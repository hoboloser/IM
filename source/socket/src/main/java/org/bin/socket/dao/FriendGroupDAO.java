package org.bin.socket.dao;

import java.util.List;

import org.bin.socket.entity.FriendGroup;

import com.zhicall.care.mybatis.page.Page;

public interface FriendGroupDAO {

	public List<FriendGroup> findFriendGroupLocal(String name,String account);
	
	public Page<FriendGroup> findFriendGroupByPageLocal(int pageNum,int pageSize);
	
	public Long addFriendGroupLocal(FriendGroup friendGroup); 

    public FriendGroup findFriendGroupByIdLocal(long id);
    
    public FriendGroup findFriendGroupByGroupIdLocal(String groupId);
    
    public void updateFriendGroupLocal(FriendGroup friendGroup);
    
}

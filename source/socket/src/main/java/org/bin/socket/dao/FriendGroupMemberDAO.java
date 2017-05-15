package org.bin.socket.dao;

import java.util.List;

import org.bin.socket.entity.FriendGroupMember;

import com.zhicall.care.mybatis.page.Page;

public interface FriendGroupMemberDAO {

	public List<FriendGroupMember> findFriendGroupMemberLocal(String groupAccount);
	
	public Page<FriendGroupMember> findFriendGroupMemberByPageLocal(int pageNum,int pageSize);
	
	public Long addFriendGroupMemberLocal(FriendGroupMember friendGroupMember); 

    public FriendGroupMember findFriendGroupMemberByIdLocal(long id);
    
    public void updateFriendGroupMemberLocal(FriendGroupMember friendGroupMember);
    
}

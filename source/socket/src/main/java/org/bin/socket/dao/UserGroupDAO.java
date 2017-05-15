package org.bin.socket.dao;

import java.util.List;

import org.bin.socket.entity.UserGroup;

import com.zhicall.care.mybatis.page.Page;

public interface UserGroupDAO {

	public List<UserGroup> findUserGroupLocal(String name,String account);
	
	public Page<UserGroup> findUserGroupByPageLocal(int pageNum,int pageSize);
	
	public Long addUserGroupLocal(UserGroup userGroup); 

    public UserGroup findUserGroupByIdLocal(long id);
    
    public void updateUserGroupLocal(UserGroup userGroup);
    
}

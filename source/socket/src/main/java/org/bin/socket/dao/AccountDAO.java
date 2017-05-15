package org.bin.socket.dao;

import java.util.List;

import org.bin.socket.entity.Users;
import org.bin.socket.enums.UserStatus;

import com.zhicall.care.mybatis.page.Page;

public interface AccountDAO {

	public List<Users> findUsersLocal();
	
	public Page<Users> findUsersByPageLocal(int pageNum,int pageSize);
	
	public Long addUsersLocal(Users users); 

    public Users findUsersByIdLocal(long id);
    
    public Users findUsersByAccountLocal(String account);
    
    public int updateUsersLocal(Users users);
    
    public int updateStatusAndLastLoginTime(String account,UserStatus status);
    
    public int updatePwd(String account,String newPassword);
    
    public int updateActive(String account);
}

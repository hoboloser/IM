package org.bin.socket.dao;

import java.util.List;

import org.bin.socket.entity.LoginLog;

import com.zhicall.care.mybatis.page.Page;

public interface LoginLogDAO {

	public List<LoginLog> findLoginLogLocal();
	
	public Page<LoginLog> findLoginLogByPageLocal(String account,int pageNum,int pageSize);
	
	public Long addLoginLogLocal(LoginLog loginLog); 

    public LoginLog findLoginLogByIdLocal(long id);
    
    public void updateLoginLogLocal(LoginLog loginLog);
    
}

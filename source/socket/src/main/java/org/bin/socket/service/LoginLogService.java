package org.bin.socket.service;

import org.bin.socket.entity.LoginLog;

import com.zhicall.care.mybatis.page.Page;

public interface LoginLogService {

	public void addLog(String account,String ip,String address); 
	
	public Page<LoginLog> findAll(String account,int pageNum,int pageSize);
    
}

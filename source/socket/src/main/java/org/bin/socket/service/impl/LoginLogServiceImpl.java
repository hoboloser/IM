package org.bin.socket.service.impl;

import java.util.Date;

import org.bin.socket.dao.LoginLogDAO;
import org.bin.socket.entity.LoginLog;
import org.bin.socket.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhicall.care.mybatis.page.Page;

@Service("loginLogService")
public class LoginLogServiceImpl implements LoginLogService {

	@Autowired
	private LoginLogDAO loginLogDAO;
	
	@Override
	public void addLog(String account,String ip,String address) {
		LoginLog log = new LoginLog();
		log.setAccount(account);
		log.setLoginIp(ip);
		log.setLoginAddress(address);
		log.setCreateTime(new Date());
		
		loginLogDAO.addLoginLogLocal(log);
	}

	@Override
	public Page<LoginLog> findAll(String account, int pageNum, int pageSize) {
		
		return loginLogDAO.findLoginLogByPageLocal(account, pageNum, pageSize);
	}

    
}

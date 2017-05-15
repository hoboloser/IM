package org.bin.socket.service.impl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.bin.socket.dao.LoginAccountNoDAO;
import org.bin.socket.entity.LoginAccountNo;
import org.bin.socket.enums.NOType;
import org.bin.socket.service.LoginAccountNoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginAccountNoService")
public class LoginAccountNoServiceImpl implements LoginAccountNoService {

	@Autowired
	private LoginAccountNoDAO loginAccountNoDAO;
	//
	private static ConcurrentHashMap<String, List<String>> ptno = new ConcurrentHashMap<String, List<String>>();
	
	@Override
	public LoginAccountNo generateNO(NOType type) {
		
		return null;
	}
	
	/**
	 * 生成一个不存在的账号
	 * 规则：
	 * 		登录账号：
	 * 			位数：6-12位
	 * 			创建级别：由少到多
	 * 			过滤：重叠账号，吉利账号，顺序账号
	 * 		群：
	 * 			位数：6-12位
	 * 			创建级别：由少到多
	 * 			过滤：重叠账号，吉利账号，顺序账号
	 * 		讨论组：13-18位，由少到多，账号不对外公开
	 */
}

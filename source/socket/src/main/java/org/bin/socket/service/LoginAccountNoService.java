package org.bin.socket.service;

import org.bin.socket.entity.LoginAccountNo;
import org.bin.socket.enums.NOType;

public interface LoginAccountNoService {

	/**
	 * 生成一个不存在的账号
	 * @param type
	 * @return
	 */
	public LoginAccountNo generateNO(NOType type);
}

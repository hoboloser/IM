package org.bin.socket.service.impl;

import org.bin.socket.constant.Constant;
import org.bin.socket.dao.AccountDAO;
import org.bin.socket.entity.Users;
import org.bin.socket.enums.UserStatus;
import org.bin.socket.enums.ValidFlag;
import org.bin.socket.exception.ServiceException;
import org.bin.socket.service.UsersService;
import org.bin.socket.util.MD5Util;
import org.bin.socket.util.NOUtil;
import org.bin.socket.util.StringUtil;
import org.bin.socket.util.email.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("usersService")
public class UsersServiceImpl implements UsersService {

	private static final String random = "xj1j29KSDWJQKDBFUQADNJKASLBC928J";
	@Autowired
	private AccountDAO accountDAO;
	
	@Override
	public Users login(String account, String password) {
		Users user = queryUsersByAccount(account);
		if(null == user){
			throw new ServiceException(Constant.ACCOUNT_NO_REGISTER);
		}
		String pwd = user.getPassword();
		if(!MD5Util.md5(password).equals(pwd)){
			throw new ServiceException(Constant.LOGIN_ERROR);
		}
		accountDAO.updateStatusAndLastLoginTime(account,UserStatus.ONLINE);
		return user;
	}

	@Override
	public Users queryUsersByAccount(String account) {
		return accountDAO.findUsersByAccountLocal(account);
	}

	@Override
	public Users register(Users user) {
		Users exist = queryUsersByAccount(user.getAccount());
		if(exist != null){
			throw new ServiceException(Constant.ACCOUNT_REGISTER);
		}
		user.setAccount(NOUtil.createLoginNO());
		user.setActive(0);
		user.setValidFlag(ValidFlag.ENABLE);
		user.setPassword(MD5Util.md5(user.getPassword()));
		user.setStatus(UserStatus.OFFLINE);
		long ua = accountDAO.addUsersLocal(user);
		if(ua > 0){
			String content = "欢迎您注册，账号已注册成功，请点击以下链接完成账号激活：http://localhost:8083/socket/user/active?account="+user.getAccount()+"&random="+random;
			MailUtil.sendSimpleEmail(content, Constant.EMAIL_TITLE, user.getEmail());
			return user;
		}
		throw new ServiceException(Constant.ACCOUNT_REGISTER_FAIL);
	}

	@Override
	public Users updateUser(Users user) {
		if(StringUtil.isEmpty(user.getAccount())){
			throw new ServiceException(Constant.ACCOUNT_NO_REGISTER);
		}
		Users exist = queryUsersByAccount(user.getAccount());
		if(exist == null){
			throw new ServiceException(Constant.UPDATE_FAIL);
		}
		user.setId(exist.getId());
		int ua = accountDAO.updateUsersLocal(user);
		if(ua > 0){
			return queryUsersByAccount(user.getAccount());
		}
		throw new ServiceException(Constant.UPDATE_FAIL);
	}

	@Override
	public Users updatePwd(String account, String oldPassword, String newPassword) {
		Users user = queryUsersByAccount(account);
		if(null == user){
			throw new ServiceException(Constant.ACCOUNT_NO_REGISTER);
		}
		String pwd = user.getPassword();
		if(!MD5Util.md5(oldPassword).equals(pwd)){
			throw new ServiceException(Constant.LOGIN_ERROR);
		}
		int ua = accountDAO.updatePwd(account, MD5Util.md5(newPassword));
		if(ua > 0){
			return queryUsersByAccount(account);
		}
		throw new ServiceException(Constant.UPDATE_PASSWORD_FAIL);
	}

	@Override
	public int active(String account, String md5) {
		//md5 匹配
		if(!random.equals(md5)){
			throw new ServiceException(Constant.ACTIVE_FAIL);
		}
		return accountDAO.updateActive(account);
	}

	@Override
	public int loginStatusChange(String account, String status) {
		Users user = queryUsersByAccount(account);
		if(null == user){
			throw new ServiceException(Constant.ACCOUNT_NO_REGISTER);
		}
		return accountDAO.updateStatusAndLastLoginTime(account, UserStatus.valueOf(status));
	}

    
}

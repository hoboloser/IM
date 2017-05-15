package org.bin.socket.service;

import org.bin.socket.entity.Users;

public interface UsersService {

	/**
	 * 登录
	 * @param account
	 * @param password
	 * @return
	 */
	public Users login(String account,String password);
	
	/**
	 * 通过账号查询
	 * @param account
	 * @return
	 */
	public Users queryUsersByAccount(String account);

	/**
	 * 注册
	 * @return
	 */
	public Users register(Users user);
	
	/**
	 * 个人信息更新
	 * @param user
	 * @return
	 */
    public Users updateUser(Users user);
    
    /**
     * 更新密码
     * @param account
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public Users updatePwd(String account,String oldPassword,String newPassword);
    
    /**
     * 账号激活
     * @param account
     * @return
     */
    public int active(String account,String md5);
    
    /**
     * 在线状态变更
     * TODO
     * @return
     */
    public int loginStatusChange(String account,String status);
}

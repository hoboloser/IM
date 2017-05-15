package org.bin.socket.service;

import java.util.List;

import org.bin.socket.entity.FriendMapper;
import org.bin.socket.entity.UserGroup;
import org.bin.socket.vo.FriendListVO;

public interface UserGroupService {

	/**
	 * 创建分组
	 * @param name 分组名
	 * @param account 登录者账号
	 * @return
	 */
	public UserGroup createUserGroup(String name,String account);
	
	/**
	 * 分组顺序变更
	 */
	public void changeOrder();
    
	/**
	 * 添加成员至分组列表
	 * @param account 登录者账号
	 * @param applyAccounts 需加入分组列表的账号
	 */
	public void addMemberToGroup(long groupId,String account,List<String> applyAccounts);
	
	/**
	 * 添加好友
	 * @param account
	 * @param applyAccount
	 */
	public FriendMapper addMember(long groupId,String account,String applyAccount,String remark);
	/**
	 * 删除好友
	 * @param account
	 * @param applyAccount
	 * @return
	 */
	public int deleteMember(String account,String applyAccount);
	
	/**
	 * 查询好友信息附带分组
	 * @param account
	 * @return
	 */
	public List<FriendListVO> findFriendsByAccountWithGroup(String account);
	/**
	 * 查询好友信息
	 * @param account
	 * @return
	 */
	public List<FriendMapper> findFriendsByAccount(String account);
	
}

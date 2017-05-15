package org.bin.socket.service;

import java.util.List;

import org.bin.socket.entity.DiscussionGroup;
import org.bin.socket.entity.FriendGroup;
import org.bin.socket.entity.FriendGroupMember;

public interface MemberService {

	/**
	 * 生成群信息
	 * @param name 姓名
	 * @param introduce 介绍
	 * @param createAccount 创建者
	 * @param avatar 头像
	 * @return
	 */
	public FriendGroup generateGroup(String name,String introduce,String createAccount,String avatar);
	
	/**
	 * 生成讨论组信息
	 * @param name 姓名
	 * @param introduce 介绍
	 * @param createAccount 创建者
	 * @param avatar 头像
	 * @return
	 */
	public DiscussionGroup generateDiscuGroup(String name,String introduce,String createAccount,String avatar);
	
	/**
	 * 加群
	 * @param groupAccount 群账号
	 * @param applyAccount 申请加群账号
	 * @param remark 备注
	 * @return
	 */
	public FriendGroupMember addMemberToGroup(String groupAccount,String applyAccount,String remark);
	
	/**
	 * 查看群成员
	 * @param groupAccount 群账号
	 * @return
	 */
	public List<FriendGroupMember> findGroupMember(String groupAccount);
}

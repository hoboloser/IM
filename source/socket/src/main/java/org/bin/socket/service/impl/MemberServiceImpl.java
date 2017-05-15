package org.bin.socket.service.impl;

import java.util.Date;
import java.util.List;

import org.bin.socket.constant.Constant;
import org.bin.socket.dao.AccountDAO;
import org.bin.socket.dao.DiscussionGroupDAO;
import org.bin.socket.dao.FriendGroupDAO;
import org.bin.socket.dao.FriendGroupMemberDAO;
import org.bin.socket.entity.DiscussionGroup;
import org.bin.socket.entity.FriendGroup;
import org.bin.socket.entity.FriendGroupMember;
import org.bin.socket.entity.Users;
import org.bin.socket.exception.ServiceException;
import org.bin.socket.service.MemberService;
import org.bin.socket.util.NOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

	@Autowired
	private FriendGroupDAO friendGroupDAO;
	
	@Autowired
	private DiscussionGroupDAO discussionGroupDAO;
	
	@Autowired
	private FriendGroupMemberDAO friendGroupMemberDAO;
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Override
	public FriendGroup generateGroup(String name, String introduce,	String createAccount,String avatar) {
		Users user = accountDAO.findUsersByAccountLocal(createAccount);
		if(null == user){
			throw new ServiceException(Constant.ACCOUNT_NO_REGISTER);
		}
		List<FriendGroup> list = friendGroupDAO.findFriendGroupLocal(name, createAccount);
		if(list != null && list.size() > 0){
			throw new ServiceException(Constant.NAME_EXISTS);
		}
		FriendGroup group = new FriendGroup();
		group.setAccount(createAccount);
		group.setAvatar(avatar);
		group.setFgroupName(name);
		group.setIntroduction(introduce);
		group.setCreateTime(new Date());
		group.setFgroupId(NOUtil.createGroupNO());
		friendGroupDAO.addFriendGroupLocal(group);
		
		//建群者先加入，设置管理员
		addMemberToGroup(group.getFgroupId(), createAccount, null);
		return group;
	}

	@Override
	public DiscussionGroup generateDiscuGroup(String name, String introduce, String createAccount, String avatar) {
		Users user = accountDAO.findUsersByAccountLocal(createAccount);
		if(null == user){
			throw new ServiceException(Constant.ACCOUNT_NO_REGISTER);
		}
		List<DiscussionGroup> list = discussionGroupDAO.findDiscussionGroupLocal(name, createAccount);
		if(list != null && list.size() > 0){
			throw new ServiceException(Constant.NAME_EXISTS);
		}
		DiscussionGroup group = new DiscussionGroup();
		group.setAccount(createAccount);
		group.setAvatar(avatar);
		group.setDgroupName(name);
		group.setIntroduction(introduce);
		group.setCreateTime(new Date());
		group.setDgroupId(NOUtil.creatDiscuNO());
		discussionGroupDAO.addDiscussionGroupLocal(group);
		return group;
	}

	@Override
	public FriendGroupMember addMemberToGroup(String groupAccount,	String applyAccount, String remark) {
		Users user = accountDAO.findUsersByAccountLocal(applyAccount);
		if(null == user){
			throw new ServiceException(Constant.ACCOUNT_NO_REGISTER);
		}
		FriendGroup fg = friendGroupDAO.findFriendGroupByGroupIdLocal(groupAccount);
		if(null == fg){
			throw new ServiceException(Constant.GROUP_NOT_EXISTS);
		}
		FriendGroupMember fgm = new FriendGroupMember();
		fgm.setAccount(applyAccount);
		fgm.setCreateTime(new Date());
		fgm.setFgroupId(groupAccount);
		fgm.setRemark(remark);
		friendGroupMemberDAO.addFriendGroupMemberLocal(fgm);
		return fgm;
	}

	@Override
	public List<FriendGroupMember> findGroupMember(String groupAccount) {
		List<FriendGroupMember> fgmList = friendGroupMemberDAO.findFriendGroupMemberLocal(groupAccount);
		return fgmList;
	}

	
}

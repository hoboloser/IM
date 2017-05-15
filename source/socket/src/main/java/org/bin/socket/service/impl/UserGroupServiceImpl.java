package org.bin.socket.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.bin.socket.constant.Constant;
import org.bin.socket.dao.AccountDAO;
import org.bin.socket.dao.AccountGroupDAO;
import org.bin.socket.dao.FriendMapperDAO;
import org.bin.socket.dao.UserGroupDAO;
import org.bin.socket.entity.AccountGroup;
import org.bin.socket.entity.FriendMapper;
import org.bin.socket.entity.UserGroup;
import org.bin.socket.entity.Users;
import org.bin.socket.enums.ValidFlag;
import org.bin.socket.exception.ServiceException;
import org.bin.socket.service.UserGroupService;
import org.bin.socket.vo.FriendListVO;
import org.bin.socket.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userGroupService")
public class UserGroupServiceImpl implements UserGroupService {

	@Autowired
	private UserGroupDAO userGroupDAO;

	@Autowired
	private FriendMapperDAO friendMapperDAO;

	@Autowired
	private AccountGroupDAO accountGroupDAO;

	@Autowired
	private AccountDAO accountDAO;

	@Override
	public UserGroup createUserGroup(String name, String account) {
		List<UserGroup> list = userGroupDAO.findUserGroupLocal(name, account);
		if (list != null && list.size() > 0) {
			throw new ServiceException(Constant.NAME_EXISTS);
		}
		UserGroup userGroup = new UserGroup();
		userGroup.setAccount(account);
		userGroup.setGroupName(name);
		userGroup.setCreateTime(new Date());
		userGroup.setOrder(new Long(0));
		userGroupDAO.addUserGroupLocal(userGroup);
		return userGroup;
	}

	@Override
	public void changeOrder() {

	}

	@Override
	public void addMemberToGroup(long groupId, String account,
			List<String> applyAccounts) {
		if (applyAccounts == null || applyAccounts.isEmpty()) {
			return;
		}
		UserGroup ug = userGroupDAO.findUserGroupByIdLocal(groupId);
		if (ug == null) {
			throw new ServiceException(Constant.USERGROUP_NOT_EXISTS);
		}
		Date date = new Date();
		for (String ga : applyAccounts) {
			AccountGroup ag = new AccountGroup();
			ag.setAccount(account);
			ag.setGroupAccount(ga);
			ag.setGroupId(groupId);
			ag.setCreateTime(date);
			accountGroupDAO.addAccountGroupLocal(ag);
		}
	}

	@Override
	public FriendMapper addMember(long groupId, String account,
			String applyAccount, String remark) {
		List<FriendMapper> list = friendMapperDAO.findFriendMapperLocal(
				account, applyAccount);
		if (list != null && list.size() > 0) {
			throw new ServiceException(Constant.FRIEND_IS_EXISTS);
		}
		if (groupId > 0) {
			UserGroup ug = userGroupDAO.findUserGroupByIdLocal(groupId);
			if (ug == null) {
				throw new ServiceException(Constant.USERGROUP_NOT_EXISTS);
			}
		}
		FriendMapper fm = new FriendMapper();
		fm.setAccount(account);
		fm.setFriendAccount(applyAccount);
		fm.setFriendRemark(remark);
		fm.setValidFlag(ValidFlag.ENABLE);
		fm.setCreateTime(new Date());
		friendMapperDAO.addFriendMapperLocal(fm);
		if (groupId > 0) {
			AccountGroup ag = new AccountGroup();
			ag.setAccount(account);
			ag.setGroupAccount(applyAccount);
			ag.setGroupId(groupId);
			ag.setCreateTime(new Date());
			accountGroupDAO.addAccountGroupLocal(ag);
		}
		return fm;
	}

	@Override
	public int deleteMember(String account, String applyAccount) {
		List<FriendMapper> list = friendMapperDAO.findFriendMapperLocal(
				account, applyAccount);
		if (list == null || list.isEmpty()) {
			return 0;
		}
		return friendMapperDAO.deleteFrientMapperById(list.get(0).getId());
	}

	@Override
	public List<FriendListVO> findFriendsByAccountWithGroup(String account) {
		// 验证当前用户
		Users user = accountDAO.findUsersByAccountLocal(account);
		if (user == null) {
			return null;
		}
		// 查分组信息
		List<UserGroup> ugList = userGroupDAO.findUserGroupLocal(null, account);
		// 查好友信息
		List<FriendMapper> fmList = friendMapperDAO.findFriendMapperLocal(
				account, null);
		if (ugList == null || ugList.isEmpty()) {// 集合为空判断待优化
			return dealwith(fmList);
		} else {
			// 查好友分组信息
			List<AccountGroup> agList = accountGroupDAO
					.findAccountGroupLocal(account);
			// 分组处理
			return dealwith01(fmList, agList, ugList);
		}
	}

	private List<FriendListVO> dealwith(List<FriendMapper> fmList) {
		List<FriendListVO> vos = new ArrayList<FriendListVO>();
		FriendListVO vo = new FriendListVO();
		vo.setGroupName(Constant.DEFAULT_FRIEND_LIST);
		if (fmList == null || fmList.isEmpty()) {

		} else {
			// 待优化
			List<UserVO> list = new ArrayList<UserVO>();
			for (FriendMapper fm : fmList) {
				String frAccount = fm.getFriendAccount();
				Users user = accountDAO.findUsersByAccountLocal(frAccount);
				UserVO userVO = new UserVO();
				userVO.convertPOToVO(user);
				list.add(userVO);
			}
			vo.setDetails(list);
		}
		vos.add(vo);

		return vos;
	}

	private List<FriendListVO> dealwith01(List<FriendMapper> fmList, List<AccountGroup> agList, List<UserGroup> ugList) {
		int fmSize = fmList.size();
		int agSize = agList.size();
		List<FriendListVO> vos = new ArrayList<FriendListVO>();
		List<String> existAccount = new ArrayList<String>();
		for (UserGroup ug : ugList) {// 循环分组列表
			FriendListVO vo = new FriendListVO();
			vo.setGroupName(ug.getGroupName());
			List<UserVO> list = new ArrayList<UserVO>();
			Iterator<AccountGroup> it = agList.iterator();
			while (it.hasNext()) {
				AccountGroup ag = it.next();
				if (ug.getId() == ag.getGroupId()) {
					String frAccount = ag.getGroupAccount();
					Users user = accountDAO.findUsersByAccountLocal(frAccount);
					UserVO userVO = new UserVO();
					userVO.convertPOToVO(user);
					list.add(userVO);
					
					existAccount.add(frAccount);//加入已处理账号信息
					
					it.remove();//移除掉当前匹配信息
				}
			}
			vo.setDetails(list);
			vos.add(vo);
		}
		
		if (fmSize != agSize) {//是否存在未分组好友信息
			FriendListVO dvo = new FriendListVO();
			dvo.setGroupName(Constant.DEFAULT_FRIEND_LIST);
			List<UserVO> list = new ArrayList<UserVO>();
			for (FriendMapper fm : fmList) {//
				String fmaccount = fm.getFriendAccount();
				if(!existAccount.contains(fmaccount)){
					Users user = accountDAO.findUsersByAccountLocal(fmaccount);
					UserVO userVO = new UserVO();
					userVO.convertPOToVO(user);
					list.add(userVO);
				}
			}
			dvo.setDetails(list);
			vos.add(dvo);
		}

		return vos;
	}

	@Override
	public List<FriendMapper> findFriendsByAccount(String account) {
		Users user = accountDAO.findUsersByAccountLocal(account);
		if (user == null) {
			return null;
		}
		// 查好友信息
		List<FriendMapper> fmList = friendMapperDAO.findFriendMapperLocal(account, null);
		return fmList;
	}

}

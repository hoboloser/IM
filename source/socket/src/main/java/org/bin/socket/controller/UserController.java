package org.bin.socket.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.bin.socket.constant.Constant;
import org.bin.socket.entity.FriendMapper;
import org.bin.socket.entity.UserGroup;
import org.bin.socket.service.UserGroupService;
import org.bin.socket.util.StringUtil;
import org.bin.socket.vo.FriendListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhicall.care.realtime.util.ResultMessageBuilder;

@RestController
@RequestMapping("/user/account")
public class UserController extends BaseController{

	@Autowired
	private UserGroupService userGroupService;
	
	/**
	 * 添加好友 
	 * @param account 当前用户
	 * @param applyAccount 添加者账号
	 * @param remark 备注
	 * @param groupId 分组
	 * @param response
	 */
	@PostMapping("/friend/add")
	public void addFriend(String account,String applyAccount,String remark,long groupId,HttpServletResponse response){
		if (StringUtil.isEmpty(account) || StringUtil.isEmpty(applyAccount)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL, null), response);
			return;
		}
		FriendMapper fm = userGroupService.addMember(groupId, account, applyAccount, remark);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, Constant.SUCCESS, fm), response);
	}
	
	/**
	 * 删除好友
	 * @param account 当前用户
	 * @param applyAccount 被删除者账户
	 * @param response
	 */
	@PostMapping("/friend/del")
	public void deleteFriend(String account,String applyAccount,HttpServletResponse response){
		if (StringUtil.isEmpty(account) || StringUtil.isEmpty(applyAccount)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL, null), response);
			return;
		}
		userGroupService.deleteMember(account, applyAccount);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, Constant.SUCCESS), response);
	}
	
	/**
	 * 添加好友分组
	 * @param 分组名
	 * @param 当前账号
	 */
	@PostMapping("/group/add")
	public void addFriendGroup(String name,String account,HttpServletResponse response){
		if (StringUtil.isEmpty(account) || StringUtil.isEmpty(name)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL, null), response);
			return;
		}
		UserGroup ug = userGroupService.createUserGroup(name, account);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, Constant.SUCCESS, ug), response);
	}
	
	/**
	 * 查询当前用户下所有好友信息，附带分组信息
	 * @param account 当前账号
	 */
	@PostMapping("/friend/fd")
	public void findAllFriend(String account,HttpServletResponse response){
		if (StringUtil.isEmpty(account)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL, null), response);
			return;
		}
		List<FriendListVO> vos = userGroupService.findFriendsByAccountWithGroup(account);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, Constant.SUCCESS, vos), response);
	}
}

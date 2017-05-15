package org.bin.socket.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.bin.socket.constant.Constant;
import org.bin.socket.entity.DiscussionGroup;
import org.bin.socket.entity.FriendGroup;
import org.bin.socket.entity.FriendGroupMember;
import org.bin.socket.service.MemberService;
import org.bin.socket.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhicall.care.realtime.util.ResultMessageBuilder;

@RestController
@RequestMapping("/user/member")
public class MemberController extends BaseController{

	@Autowired
	private MemberService memberService;
	/**
	 * 加群
	 */
	@PostMapping("/group/join")
	public void addMemberToGroup(String groupId,String account,String remark,HttpServletResponse response){
		if (StringUtil.isEmpty(groupId) || StringUtil.isEmpty(account)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL, null), response);
			return;
		}
		FriendGroupMember fgm = memberService.addMemberToGroup(groupId, account, remark);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, Constant.SUCCESS, fgm), response);
	}
	/**
	 * 建群
	 */
	@PostMapping("/group/create")
	public void createGroup(FriendGroup group,HttpServletResponse response){
		if (group == null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL, null), response);
			return;
		}
		if (StringUtil.isEmpty(group.getFgroupName())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL_NAME, null), response);
			return;
		}
		FriendGroup fg = memberService.generateGroup(group.getFgroupName(), group.getIntroduction(), group.getAccount(), group.getAvatar());
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, Constant.SUCCESS, fg), response);
	}
	
	@PostMapping("/{groupId}/group/fd")
	public void groupFind(@PathVariable String groupId,HttpServletResponse response){
		List<FriendGroupMember> list = memberService.findGroupMember(groupId);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, Constant.SUCCESS, list), response);
	}
	/**
	 * 退群
	 */
	@PostMapping("/group/out")
	public void dropOutGroup(String groupId,String account,HttpServletResponse response){
		
	}
	
	/**
	 * 加讨论组
	 */
	@PostMapping("/discu/join")
	public void addMemberToDiscu(String groupId,String account,HttpServletResponse response){
		
	}
	/**
	 * 建讨论组
	 */
	@PostMapping("/discu/cd")
	public void createDiscu(DiscussionGroup group,HttpServletResponse response){
		
	}
	
	/**
	 * 退组
	 */
	@PostMapping("/discu/out")
	public void dropOutDiscu(String groupId,String account,HttpServletResponse response){
		
	}
}
	
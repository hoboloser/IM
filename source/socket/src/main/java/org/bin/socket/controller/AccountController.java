package org.bin.socket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bin.socket.constant.Constant;
import org.bin.socket.entity.Users;
import org.bin.socket.enums.UserStatus;
import org.bin.socket.service.UsersService;
import org.bin.socket.util.StringUtil;
import org.bin.socket.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhicall.care.realtime.util.ResultMessageBuilder;

@RestController
@RequestMapping("/account")
public class AccountController extends BaseController{
	
	@Autowired
	private UsersService usersService;
	
	/**
	 * 更新密码
	 */
	@PostMapping("/password/update")
	public void updatePwd(String account,String oldPwd,String newPwd,String repartPwd,HttpServletRequest request, HttpServletResponse response){
		if (StringUtil.isEmpty(account)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL_ACCOUNT, null), response);
			return;
		}
		if (StringUtil.isEmpty(oldPwd)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL_PASSWORD, null), response);
			return;
		}
		if (StringUtil.isEmpty(newPwd)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL_PASSWORD, null), response);
			return;
		}
		if (!newPwd.equals(repartPwd)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_REPART_PASSWORD, null), response);
			return;
		}
		Users user = usersService.updatePwd(account, oldPwd, newPwd);
		HttpSession session = request.getSession();
		session.removeAttribute(Constant.LOGIN_SESSION);
		session.setAttribute(Constant.LOGIN_STATUS, UserStatus.OFFLINE);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, Constant.UPDATE_PASSWORD_SUCCESS, null), response);
	}
	
	/**
	 * 上传头像
	 */
	@PostMapping("/avatar/update")
	public void uploadAvatar(){
		
	}
	
	/**
	 * 更新用户信息
	 */
	@PostMapping("/updates")
	public void updateInfo(Users user,HttpServletRequest request, HttpServletResponse response){
		if (null == user) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL, null), response);
			return;
		}
		Users res = usersService.updateUser(user);
		UserVO vo = new UserVO();
		vo.convertPOToVO(res);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, Constant.UPDATE_SUCCESS, vo), response);
	}
	
	/**
	 * 在线状态变更
	 * @param mobile
	 * @param status
	 * @param response
	 */
	@GetMapping("/{account}/{status}/change")
	public void updateLoginStatus(@PathVariable String account,@PathVariable String status,HttpServletRequest request, HttpServletResponse response){
		usersService.loginStatusChange(account, status);
		HttpSession session = request.getSession();
		session.setAttribute(Constant.LOGIN_STATUS, UserStatus.valueOf(status));
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, Constant.UPDATE_SUCCESS), response);
	}
}

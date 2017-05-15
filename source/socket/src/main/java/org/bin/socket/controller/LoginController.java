package org.bin.socket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bin.socket.constant.Constant;
import org.bin.socket.entity.Users;
import org.bin.socket.enums.UserStatus;
import org.bin.socket.service.UsersService;
import org.bin.socket.util.StringUtil;
import org.bin.socket.util.VerifyUtil;
import org.bin.socket.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhicall.care.realtime.util.ResultMessageBuilder;

@RestController
@RequestMapping("/user")
public class LoginController extends BaseController {

	@Autowired
	private UsersService usersService;

	@PostMapping("/login")
	public void login(String account, String password, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		if (StringUtil.isEmpty(account)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL_ACCOUNT, null), response);
			return;
		}
		if (StringUtil.isEmpty(password)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL_PASSWORD, null), response);
			return;
		}
		Users users = usersService.login(account, password);
		if (null == users) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false,	Constant.LOGIN_FAIL, null), response);
			return;
		} else {
			UserVO vo = new UserVO();
			vo.convertPOToVO(users);
			session.setAttribute(Constant.LOGIN_SESSION, users.getAccount());
			session.setAttribute(Constant.LOGIN_STATUS, UserStatus.ONLINE);
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, Constant.LOGIN_SUCCESS, vo), response);
		}
	}
	
	@PostMapping("/logout")
	public void logout(HttpServletRequest request,HttpSession session, HttpServletResponse response){
		session.removeAttribute(Constant.LOGIN_SESSION);
		session.setAttribute(Constant.LOGIN_STATUS, UserStatus.OFFLINE);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, Constant.LOGOUT_SUCCESS, null), response);
	}
	
	private static final String randomCode = "123456";
	/**
	 * 注册
	 */
	@PostMapping("/register")
	public void register(Users user,String code,HttpServletRequest request, HttpServletResponse response){
		if(user == null){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL), response);
			return;
		}
		if(StringUtil.isEmpty(user.getName())){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL_NAME), response);
			return;
		}
		if(StringUtil.isEmpty(user.getPassword())){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL_PASSWORD), response);
			return;
		}
		if(StringUtil.isEmpty(user.getEmail())){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL_PASSWORD), response);
			return;
		}
		if(randomCode.equals(code)){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.VERIFY_CODE_ERROR), response);
			return;
		}
		Users res = usersService.register(user);
		UserVO vo = new UserVO();
		vo.convertPOToVO(res);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, Constant.ACCOUNT_REGISTER_SUCCESS, vo), response);
		
	}
	
	/**
	 * 账号激活
	 */
	@GetMapping("/active")
	public void activation(String account,String random,HttpServletRequest request, HttpServletResponse response){
		if(StringUtil.isEmpty(account) || StringUtil.isEmpty(random)){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL), response);
			return;
		}
		usersService.active(account, random);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, Constant.ACTIVE_SUCCESS, null), response);
	}
	
	
	/**
	 * 注册验证码发送
	 */
	@GetMapping("/{mobile}/register/code")
	public void verifyCode(@PathVariable String mobile, HttpServletResponse response){
		if(StringUtil.isEmpty(mobile) || !VerifyUtil.isPhoneLegal(mobile)){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.MOBILE_ERROR, null), response);
			return;
		}
		//send code
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, Constant.SEND_VERIFY_CODE), response);
	}
	
}

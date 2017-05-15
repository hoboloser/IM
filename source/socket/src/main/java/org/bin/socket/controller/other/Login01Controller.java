package org.bin.socket.controller.other;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.bin.socket.constant.Constant;
import org.bin.socket.entity.Users;
import org.bin.socket.service.LoginLogService;
import org.bin.socket.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@EnableAutoConfiguration
public class Login01Controller {

	@Autowired
	private LoginLogService loginLogService;
	@Autowired
	private UsersService usersService;

	@RequestMapping(value = "/user01/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView view = new ModelAndView("login");
		return view;
	}

	@RequestMapping(value = "/user01/login", method = RequestMethod.POST)
	public String login(String userid, String password,
			RedirectAttributes attributes, HttpServletRequest request) {
		Users user = usersService.login(userid, password);
		if (user == null) {
			attributes.addFlashAttribute("error", Constant.ACCOUNT_NO_REGISTER);
			return "redirect:/user01/login";
		} else {
				request.getSession(true).setAttribute(Constant.LOGIN_SESSION,
						userid);
				request.getSession(true).setAttribute(Constant.LOGIN_STATUS,
						true);
				user.setLastLoginTime(new Date());
				usersService.updateUser(user);
				attributes.addFlashAttribute("message", Constant.LOGIN_SUCCESS);
				return "redirect:/chat";
		}
	}

	@RequestMapping(value = "/user01/logout")
	public String logout(HttpSession session, RedirectAttributes attributes) {
		session.removeAttribute("userid");
		session.removeAttribute("login_status");
		attributes.addFlashAttribute("message", Constant.LOGOUT_SUCCESS);
		return "redirect:/user01/login";
	}
}

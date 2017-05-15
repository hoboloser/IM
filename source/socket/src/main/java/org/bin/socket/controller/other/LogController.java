//package org.bin.socket.controller.other;
//
//import org.bin.socket.entity.LoginLog;
//import org.bin.socket.service.LoginLogService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.zhicall.care.mybatis.page.Page;
//
//@Controller
//@EnableAutoConfiguration
//public class LogController {
//
//	@Autowired
//	private LoginLogService loginLogService;
//	/**
//	 * 操作日志列表
//	 */
//	@RequestMapping(value = "{userid}/log")
//	public ModelAndView selectAll(@PathVariable("userid") String userid,@RequestParam(defaultValue = "1") int page) {
//		int pageSize = 5;
//		ModelAndView view = new ModelAndView("log");
//		Page<LoginLog> pages = loginLogService.findLoginLogByPageLocal(userid, page, pageSize);
//		view.addObject("list", pages.getResult());
//		view.addObject("count", pages.getTotalCount());
//		return view;
//	}
//}

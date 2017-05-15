package org.bin.socket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bin.socket.constant.Constant;
import org.bin.socket.entity.ChatRoom;
import org.bin.socket.entity.DoubleChatRoom;
import org.bin.socket.service.RoomService;
import org.bin.socket.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhicall.care.realtime.util.ResultMessageBuilder;

@RestController
@RequestMapping("/room")
public class RoomController extends BaseController{

	@Autowired
	private RoomService roomService;
	
	/**
	 * 创建普通聊天室
	 */
	@PostMapping("/pt/estab")
	public void createRoom(String from,String to,HttpServletRequest request,HttpServletResponse response){
		if (StringUtil.isEmpty(from) || StringUtil.isEmpty(to)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL, null), response);
			return;
		}
		ChatRoom cr = roomService.generateRoom(from, to);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, Constant.SUCCESS, cr), response);
	}
	/**
	 * 创建讨论组聊天室 
	 * 
	 */
	@PostMapping("/{id}/discu/estab")
	public void createDiscuRoom(@PathVariable String id,HttpServletResponse response){
		if (StringUtil.isEmpty(id)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL, null), response);
			return;
		}
		DoubleChatRoom dcr = roomService.generateDiscuRoom(id);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, Constant.SUCCESS, dcr), response);
	}
	
	/**
	 * 创建群聊天室
	 * @param id 群账号
	 */
	@PostMapping("/{id}/group/estab")
	public void createGroupRoom(@PathVariable String id,HttpServletResponse response){
		if (StringUtil.isEmpty(id)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, Constant.NOT_NULL, null), response);
			return;
		}
		DoubleChatRoom dcr = roomService.generateGroupRoom(id);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, Constant.SUCCESS, dcr), response);
	}
}

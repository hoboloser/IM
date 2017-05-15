package org.bin.socket.controller;

import javax.servlet.http.HttpServletResponse;

import org.bin.socket.constant.Constant;
import org.bin.socket.entity.ChatMessage;
import org.bin.socket.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhicall.care.mybatis.page.Page;
import com.zhicall.care.realtime.util.ResultMessageBuilder;

@RestController
@RequestMapping("/msg")
public class MessageController extends BaseController{

	@Autowired
	private MessageService messageService;
	
	@PostMapping("/{roomId}/signle/msg")
	public void historyChatMsg(@PathVariable String roomId,int pageNum,int pageSize,HttpServletResponse response){
		
		Page<ChatMessage> page = messageService.queryChatMessage(Long.parseLong(roomId),null,null, pageNum, pageSize);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, Constant.SUCCESS, page), response);
	}
	
	@PostMapping("/{roomId}/group/msg")
	public void historyGroupChatMsg(@PathVariable String roomId,int pageNum,int pageSize,HttpServletResponse response){
		
		Page<ChatMessage> page = messageService.queryGroupMessage(Long.parseLong(roomId), pageNum, pageSize);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, Constant.SUCCESS, page), response);
	}
}

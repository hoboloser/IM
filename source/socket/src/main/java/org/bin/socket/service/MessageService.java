package org.bin.socket.service;

import java.util.List;

import org.bin.socket.entity.ChatMessage;
import org.bin.socket.enums.MessageType;
import org.bin.socket.enums.ReadType;

import com.zhicall.care.mybatis.page.Page;

public interface MessageService {

	/**
	 * 发送普通消息
	 */
	public ChatMessage sendMessage(long roomId,String from,String to,String message,MessageType type,ReadType isRead);
	
	/**
	 * 发送群消息
	 */
	public ChatMessage sendGroupMessage(long roomId,String from,String message,MessageType type);
	
	/**
	 * 发送讨论组消息
	 */
	public ChatMessage sendDiscuMessage(long roomId,String from,String message,MessageType type);
	
	/**
	 * 查询私聊聊天历史记录
	 * @return
	 */
	public Page<ChatMessage> queryChatMessage(long roomId,String from,String to,int pageNum,int pageSize);
	
	/**
	 * 查询群聊天历史记录
	 * @param roomId
	 * @return
	 */
	public Page<ChatMessage> queryGroupMessage(long roomId,int pageNum,int pageSize);
	
	/**
	 * 查询讨论组聊天历史记录
	 * @param roomId
	 * @return
	 */
	public Page<ChatMessage> queryDiscussMessage(long roomId,int pageNum,int pageSize);
	
	/**
	 * 查询未读信息
	 * @param account
	 * @return
	 */
	public List<ChatMessage> queryUnReadMessage(String account);
	
}

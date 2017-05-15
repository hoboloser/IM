package org.bin.socket.service;

import org.bin.socket.entity.ChatRoom;
import org.bin.socket.entity.DoubleChatRoom;

public interface RoomService {

	/**
	 *  生成普通聊天室
	 * @return
	 */
	public ChatRoom generateRoom(String from,String to);
	/**
	 * 生成群聊天室 
	 * @param from
	 * @param to
	 * @return
	 */
	public DoubleChatRoom generateGroupRoom(String groupId);
	
	/**
	 * 生成讨论组聊天室 
	 * @param from
	 * @param to
	 * @return
	 */
	public DoubleChatRoom generateDiscuRoom(String groupId);
    
}

package org.bin.socket.entity;

import java.io.Serializable;
import java.util.Date;

import org.bin.socket.enums.ChatType;
import org.bin.socket.enums.ReadType;

public class ChatMessage implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	/**
	 *id
	 */
	private Long id;
	
	/**
	 *聊天会话ID
	 */
	private Long roomId;
	
	/**
	 *发起人账号
	 */
	private String from;
	
	/**
	 *被发起人账号
	 */
	private String to;
	
	/**
	 *文本内容
	 */
	private String text;
	
	/**
	 *语音内容
	 */
	private String audio;
	
	/**
	 *图文内容
	 */
	private String image;
	
	/**
	 *聊天类型
	 */
	private ChatType type;
	/**
	 *
	 */
	private ReadType isRead;
	
	/**
	 *创建时间
	 */
	private Date createTime;
	
	/**
	 *id setter方法
	 */
	public void setId(Long id){
		this.id = id;
	}
	
	/**
	 *id getter方法
	 */
	public Long getId(){
		return id;
	}
	
	/**
	 *聊天会话ID setter方法
	 */
	public void setRoomId(Long roomId){
		this.roomId = roomId;
	}
	
	/**
	 *聊天会话ID getter方法
	 */
	public Long getRoomId(){
		return roomId;
	}
	
	/**
	 *发起人账号 setter方法
	 */
	public void setFrom(String from){
		this.from = from;
	}
	
	/**
	 *发起人账号 getter方法
	 */
	public String getFrom(){
		return from;
	}
	
	/**
	 *被发起人账号 setter方法
	 */
	public void setTo(String to){
		this.to = to;
	}
	
	/**
	 *被发起人账号 getter方法
	 */
	public String getTo(){
		return to;
	}
	
	/**
	 *文本内容 setter方法
	 */
	public void setText(String text){
		this.text = text;
	}
	
	/**
	 *文本内容 getter方法
	 */
	public String getText(){
		return text;
	}
	
	/**
	 *语音内容 setter方法
	 */
	public void setAudio(String audio){
		this.audio = audio;
	}
	
	/**
	 *语音内容 getter方法
	 */
	public String getAudio(){
		return audio;
	}
	
	/**
	 *图文内容 setter方法
	 */
	public void setImage(String image){
		this.image = image;
	}
	
	/**
	 *图文内容 getter方法
	 */
	public String getImage(){
		return image;
	}
	
	/**
	 *聊天类型 setter方法
	 */
	public void setType(ChatType type){
		this.type = type;
	}
	
	/**
	 *聊天类型 getter方法
	 */
	public ChatType getType(){
		return type;
	}
	
	/**
	 *创建时间 setter方法
	 */
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	/**
	 *创建时间 getter方法
	 */
	public Date getCreateTime(){
		return createTime;
	}

	public ReadType getIsRead() {
		return isRead;
	}

	public void setIsRead(ReadType isRead) {
		this.isRead = isRead;
	}
	
}

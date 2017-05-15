package org.bin.socket.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

public class ChatRoom implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	/**
	 *id
	 */
	private Long id;
	
	/**
	 *发起人账号
	 */
	private String from;
	
	/**
	 *被发起人账号
	 */
	private String to;
	
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
	
	
	
}

package org.bin.socket.entity;

import java.io.Serializable;
import java.util.Date;

import org.bin.socket.enums.RoomType;

public class DoubleChatRoom implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	/**
	 *id
	 */
	private Long id;
	
	/**
	 *多人聊天组信息
	 */
	private String groupId;
	
	/**
	 *组类别，群 or 讨论组
	 */
	private RoomType type;
	
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
	 *多人聊天组信息 setter方法
	 */
	public void setGroupId(String groupId){
		this.groupId = groupId;
	}
	
	/**
	 *多人聊天组信息 getter方法
	 */
	public String getGroupId(){
		return groupId;
	}
	
	/**
	 *组类别，群 or 讨论组 setter方法
	 */
	public void setType(RoomType type){
		this.type = type;
	}
	
	/**
	 *组类别，群 or 讨论组 getter方法
	 */
	public RoomType getType(){
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
	
	
	
}

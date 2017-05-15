package org.bin.socket.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

public class UserGroup implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	/**
	 *id
	 */
	private Long id;
	
	/**
	 *账号
	 */
	private String account;
	
	/**
	 *组名
	 */
	private String groupName;
	
	/**
	 *分组顺序
	 */
	private Long order;
	
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
	 *账号 setter方法
	 */
	public void setAccount(String account){
		this.account = account;
	}
	
	/**
	 *账号 getter方法
	 */
	public String getAccount(){
		return account;
	}
	
	/**
	 *组名 setter方法
	 */
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}
	
	/**
	 *组名 getter方法
	 */
	public String getGroupName(){
		return groupName;
	}
	
	/**
	 *分组顺序 setter方法
	 */
	public void setOrder(Long order){
		this.order = order;
	}
	
	/**
	 *分组顺序 getter方法
	 */
	public Long getOrder(){
		return order;
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

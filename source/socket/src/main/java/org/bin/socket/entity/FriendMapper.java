package org.bin.socket.entity;

import java.io.Serializable;
import java.util.Date;

import org.bin.socket.enums.ValidFlag;

public class FriendMapper implements Serializable {
  
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
	 *好友账号
	 */
	private String friendAccount;
	
	/**
	 *好友备注
	 */
	private String friendRemark;
	
	/**
	 *有效标志
	 */
	private ValidFlag validFlag;
	
	/**
	 *更新时间
	 */
	private Date updateTime;
	
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
	 *好友账号 setter方法
	 */
	public void setFriendAccount(String friendAccount){
		this.friendAccount = friendAccount;
	}
	
	/**
	 *好友账号 getter方法
	 */
	public String getFriendAccount(){
		return friendAccount;
	}
	
	/**
	 *好友备注 setter方法
	 */
	public void setFriendRemark(String friendRemark){
		this.friendRemark = friendRemark;
	}
	
	/**
	 *好友备注 getter方法
	 */
	public String getFriendRemark(){
		return friendRemark;
	}
	
	/**
	 *有效标志 setter方法
	 */
	public void setValidFlag(ValidFlag validFlag){
		this.validFlag = validFlag;
	}
	
	/**
	 *有效标志 getter方法
	 */
	public ValidFlag getValidFlag(){
		return validFlag;
	}
	
	/**
	 *更新时间 setter方法
	 */
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	/**
	 *更新时间 getter方法
	 */
	public Date getUpdateTime(){
		return updateTime;
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

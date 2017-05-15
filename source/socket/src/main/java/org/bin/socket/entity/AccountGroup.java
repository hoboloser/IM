package org.bin.socket.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

public class AccountGroup implements Serializable {
  
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
	 *组内账号
	 */
	private String groupAccount;
	
	/**
	 *账号备注
	 */
	private String accountRemark;
	
	/**
	 *组名
	 */
	private Long groupId;
	
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
	 *组内账号 setter方法
	 */
	public void setGroupAccount(String groupAccount){
		this.groupAccount = groupAccount;
	}
	
	/**
	 *组内账号 getter方法
	 */
	public String getGroupAccount(){
		return groupAccount;
	}
	
	/**
	 *账号备注 setter方法
	 */
	public void setAccountRemark(String accountRemark){
		this.accountRemark = accountRemark;
	}
	
	/**
	 *账号备注 getter方法
	 */
	public String getAccountRemark(){
		return accountRemark;
	}
	
	/**
	 *组名 setter方法
	 */
	public void setGroupId(Long groupId){
		this.groupId = groupId;
	}
	
	/**
	 *组名 getter方法
	 */
	public Long getGroupId(){
		return groupId;
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

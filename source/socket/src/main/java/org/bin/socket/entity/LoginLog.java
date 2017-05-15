package org.bin.socket.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

public class LoginLog implements Serializable {
  
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
	 *姓名
	 */
	private String name;
	
	/**
	 *登录时间
	 */
	private Date loginTime;
	
	/**
	 *登录IP
	 */
	private String loginIp;
	
	/**
	 *登录地址
	 */
	private String loginAddress;
	
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
	 *姓名 setter方法
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 *姓名 getter方法
	 */
	public String getName(){
		return name;
	}
	
	/**
	 *登录时间 setter方法
	 */
	public void setLoginTime(Date loginTime){
		this.loginTime = loginTime;
	}
	
	/**
	 *登录时间 getter方法
	 */
	public Date getLoginTime(){
		return loginTime;
	}
	
	/**
	 *登录IP setter方法
	 */
	public void setLoginIp(String loginIp){
		this.loginIp = loginIp;
	}
	
	/**
	 *登录IP getter方法
	 */
	public String getLoginIp(){
		return loginIp;
	}
	
	/**
	 *登录地址 setter方法
	 */
	public void setLoginAddress(String loginAddress){
		this.loginAddress = loginAddress;
	}
	
	/**
	 *登录地址 getter方法
	 */
	public String getLoginAddress(){
		return loginAddress;
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

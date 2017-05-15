package org.bin.socket.entity;

import java.io.Serializable;
import java.util.Date;

import org.bin.socket.enums.NOType;

public class LoginAccountNo implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	/**
	 *id
	 */
	private Long id;
	
	/**
	 *登录账号
	 */
	private Long no;
	
	private NOType type;
	/**
	 *有效性
	 */
	private String validFlag;
	
	/**
	 *创建时间
	 */
	private Date createTime;
	
	/**
	 *修改时间
	 */
	private Date updateTime;
	
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
	 *登录账号 setter方法
	 */
	public void setNo(Long no){
		this.no = no;
	}
	
	/**
	 *登录账号 getter方法
	 */
	public Long getNo(){
		return no;
	}
	
	/**
	 *有效性 setter方法
	 */
	public void setValidFlag(String validFlag){
		this.validFlag = validFlag;
	}
	
	/**
	 *有效性 getter方法
	 */
	public String getValidFlag(){
		return validFlag;
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
	
	/**
	 *修改时间 setter方法
	 */
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	/**
	 *修改时间 getter方法
	 */
	public Date getUpdateTime(){
		return updateTime;
	}

	public NOType getType() {
		return type;
	}

	public void setType(NOType type) {
		this.type = type;
	}
	
}

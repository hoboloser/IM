package org.bin.socket.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

public class DiscussionGroup implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	/**
	 *id
	 */
	private Long id;
	
	/**
	 *讨论组ID
	 */
	private String dgroupId;
	
	/**
	 *讨论组名字
	 */
	private String dgroupName;
	
	/**
	 *创建人
	 */
	private String account;
	
	/**
	 *讨论组显示顺序
	 */
	private Long order;
	
	/**
	 *讨论组头像
	 */
	private String avatar;
	
	/**
	 *讨论组介绍
	 */
	private String introduction;
	
	/**
	 *创建时间
	 */
	private Date createTime;
	
	/**
	 *更新时间
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
	 *讨论组ID setter方法
	 */
	public void setDgroupId(String dgroupId){
		this.dgroupId = dgroupId;
	}
	
	/**
	 *讨论组ID getter方法
	 */
	public String getDgroupId(){
		return dgroupId;
	}
	
	/**
	 *讨论组名字 setter方法
	 */
	public void setDgroupName(String dgroupName){
		this.dgroupName = dgroupName;
	}
	
	/**
	 *讨论组名字 getter方法
	 */
	public String getDgroupName(){
		return dgroupName;
	}
	
	/**
	 *创建人 setter方法
	 */
	public void setAccount(String account){
		this.account = account;
	}
	
	/**
	 *创建人 getter方法
	 */
	public String getAccount(){
		return account;
	}
	
	/**
	 *讨论组显示顺序 setter方法
	 */
	public void setOrder(Long order){
		this.order = order;
	}
	
	/**
	 *讨论组显示顺序 getter方法
	 */
	public Long getOrder(){
		return order;
	}
	
	/**
	 *讨论组头像 setter方法
	 */
	public void setAvatar(String avatar){
		this.avatar = avatar;
	}
	
	/**
	 *讨论组头像 getter方法
	 */
	public String getAvatar(){
		return avatar;
	}
	
	/**
	 *讨论组介绍 setter方法
	 */
	public void setIntroduction(String introduction){
		this.introduction = introduction;
	}
	
	/**
	 *讨论组介绍 getter方法
	 */
	public String getIntroduction(){
		return introduction;
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
	
	
	
}

package org.bin.socket.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

public class FriendGroupMember implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	/**
	 *id
	 */
	private Long id;
	
	/**
	 *群ID
	 */
	private String fgroupId;
	
	/**
	 *成员信息
	 */
	private String account;
	
	/**
	 *成员职位
	 */
	private String position;
	
	/**
	 *群备注
	 */
	private String remark;
	
	/**
	 *加入时间
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
	 *群ID setter方法
	 */
	public void setFgroupId(String fgroupId){
		this.fgroupId = fgroupId;
	}
	
	/**
	 *群ID getter方法
	 */
	public String getFgroupId(){
		return fgroupId;
	}
	
	/**
	 *成员信息 setter方法
	 */
	public void setAccount(String account){
		this.account = account;
	}
	
	/**
	 *成员信息 getter方法
	 */
	public String getAccount(){
		return account;
	}
	
	/**
	 *成员职位 setter方法
	 */
	public void setPosition(String position){
		this.position = position;
	}
	
	/**
	 *成员职位 getter方法
	 */
	public String getPosition(){
		return position;
	}
	
	/**
	 *群备注 setter方法
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	/**
	 *群备注 getter方法
	 */
	public String getRemark(){
		return remark;
	}
	
	/**
	 *加入时间 setter方法
	 */
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	/**
	 *加入时间 getter方法
	 */
	public Date getCreateTime(){
		return createTime;
	}
	
	
	
}

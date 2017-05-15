package org.bin.socket.vo;

import java.util.Date;

import org.bin.socket.entity.Users;
import org.bin.socket.enums.Sex;
import org.bin.socket.enums.UserStatus;
import org.bin.socket.enums.ValidFlag;

public class UserVO implements BaseVO{

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
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 昵称
	 */
	private String birthday;
	
	/**
	 * 性别
	 */
	private Sex sex;
	
	/**
	 * 年龄
	 */
	private int age;
	
	/**
	 *头像
	 */
	private String avatarPic;
	
	/**
	 *状态
	 */
	private UserStatus status;
	
	/**
	 *签名
	 */
	private String signature;
	
	/**
	 *状态类型
	 */
	private String statusType;
	
	/**
	 *有效标志
	 */
	private ValidFlag validFlag;
	
	/**
	 *创建时间
	 */
	private Date createTime;
	
	/**
	 *更新时间
	 */
	private Date updateTime;
	
	/**
	 *最后登录时间
	 */
	private Date lastLoginTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAvatarPic() {
		return avatarPic;
	}

	public void setAvatarPic(String avatarPic) {
		this.avatarPic = avatarPic;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public ValidFlag getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(ValidFlag validFlag) {
		this.validFlag = validFlag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if(poObj != null){
			Users po = (Users)poObj;
			this.account = po.getAccount();
			this.age = po.getAge();
			this.id = po.getId();
			this.avatarPic = po.getAvatarPic();
			this.birthday = po.getBirthday();
			this.createTime = po.getCreateTime();
			this.lastLoginTime = po.getLastLoginTime();
			this.sex = po.getSex();
			this.signature = po.getSignature();
			this.name =po.getName();
			this.nickname = po.getNickname();
			this.updateTime = po.getUpdateTime();
			this.validFlag = po.getValidFlag();
			this.status = po.getStatus();
			this.statusType = po.getStatus().getName();
			this.email = po.getEmail();
		}
	}
	
	
}

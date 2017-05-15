package org.bin.socket.entity;

import java.io.Serializable;
import java.util.Date;

import org.bin.socket.enums.Sex;
import org.bin.socket.enums.UserStatus;
import org.bin.socket.enums.ValidFlag;

public class Users implements Serializable {
  
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
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 昵称
	 */
	private String birthday;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 性别
	 */
	private Sex sex;
	
	/**
	 * 年龄
	 */
	private int age;
	/**
	 *密码
	 */
	private String password;
	
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
	
	private int active;
	
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
	 *密码 setter方法
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	/**
	 *密码 getter方法
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 *头像 setter方法
	 */
	public void setAvatarPic(String avatarPic){
		this.avatarPic = avatarPic;
	}
	
	/**
	 *头像 getter方法
	 */
	public String getAvatarPic(){
		return avatarPic;
	}
	
	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}
	
	/**
	 *签名 setter方法
	 */
	public void setSignature(String signature){
		this.signature = signature;
	}
	
	/**
	 *签名 getter方法
	 */
	public String getSignature(){
		return signature;
	}
	
	/**
	 *状态类型 setter方法
	 */
	public void setStatusType(String statusType){
		this.statusType = statusType;
	}
	
	/**
	 *状态类型 getter方法
	 */
	public String getStatusType(){
		return statusType;
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
	
	/**
	 *最后登录时间 setter方法
	 */
	public void setLastLoginTime(Date lastLoginTime){
		this.lastLoginTime = lastLoginTime;
	}
	
	/**
	 *最后登录时间 getter方法
	 */
	public Date getLastLoginTime(){
		return lastLoginTime;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
}

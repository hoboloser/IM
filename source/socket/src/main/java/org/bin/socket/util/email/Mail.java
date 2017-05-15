package org.bin.socket.util.email;

import java.util.List;

public class Mail {

	/**
	 * 发件人邮箱
	 */
	private String email;
	/**
	 * 发件人名字
	 */
	private String mailName;
	/**
	 * 收件人
	 */
	private List<Recipient> toList;
	/**
	 * 抄送
	 */
	private List<Recipient> ccList;
	/**
	 * 密送
	 */
	private List<Recipient> bccList;
	
	/**
	 * 主题
	 */
	private String title;
	
	/**
	 * 内容/正文
	 */
	private String content;


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMailName() {
		return mailName;
	}

	public void setMailName(String mailName) {
		this.mailName = mailName;
	}

	public List<Recipient> getToList() {
		return toList;
	}

	public void setToList(List<Recipient> toList) {
		this.toList = toList;
	}

	public List<Recipient> getCcList() {
		return ccList;
	}

	public void setCcList(List<Recipient> ccList) {
		this.ccList = ccList;
	}

	public List<Recipient> getBccList() {
		return bccList;
	}

	public void setBccList(List<Recipient> bccList) {
		this.bccList = bccList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}

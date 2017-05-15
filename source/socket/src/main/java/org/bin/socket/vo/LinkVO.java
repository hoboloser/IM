package org.bin.socket.vo;

import org.bin.socket.enums.PushType;
import org.bin.socket.enums.UserStatus;

public class LinkVO {

	private String message;
	
	private PushType type;
	
	private UserStatus status;
	
	private String userid;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PushType getType() {
		return type;
	}

	public void setType(PushType type) {
		this.type = type;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}

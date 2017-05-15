package org.bin.socket.enums;

public enum UserStatus {

	ONLINE(1,"在线"),
	OFFLINE(2,"离线"),
	GOAWAY(3,"离开"),
	BUSY(4,"忙碌"),
	STEALTH(5,"隐身");
	
	private int code;
	
	private String name;
	
	private UserStatus(int code,String name){
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

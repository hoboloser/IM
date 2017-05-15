package org.bin.socket.enums;

public enum DBExceEnums {

	DB_EXCEPTION(-523,"数据库操作异常！");
	
	
	private int code;
	
	private String message;
	
	private DBExceEnums(int code,String message){
		setCode(code);
		setMessage(message);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}

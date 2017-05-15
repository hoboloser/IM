package org.bin.socket.exception;

import org.bin.socket.enums.DBExceEnums;

/**
 * db exception
 * <p>Title:DBException</p>
 * <p>Description:</p>
 * @author binH
 * @date 2017年5月3日 上午10:32:45
 */
public class DBException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DBExceEnums enums;
	
	public DBException(){}
	
	public DBException(DBExceEnums enums){
		super(enums.getMessage());
		setEnums(enums);
	}

	public DBException(String message){
		super(message);
	}

	public DBException(int code){
		
	}

	public DBExceEnums getEnums() {
		return enums;
	}

	public void setEnums(DBExceEnums enums) {
		this.enums = enums;
	}
	
}

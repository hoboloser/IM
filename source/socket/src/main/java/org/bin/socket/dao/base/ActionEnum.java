package org.bin.socket.dao.base;

public enum ActionEnum {

	INSERT(1,"INSERT"),
	UPDATE(2,"UPDATE"),
	UPDATEREDUCE(2,"UPDATE_REDUCE"),
	DELETE(3,"DELETE"),
	QUERYBYID(4,"QUERY_BY_ID"),
	QUERYALL(5,"QUERY_ALL");
	
	private Integer code;
	
	private String name;
	
	private ActionEnum(Integer code,String name){
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

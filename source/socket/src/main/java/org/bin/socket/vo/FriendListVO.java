package org.bin.socket.vo;

import java.util.List;

public class FriendListVO {

	/**
	 * 分组名
	 */
	private String groupName;
	
	/**
	 * 好友列表
	 */
	private List<UserVO> details;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<UserVO> getDetails() {
		return details;
	}

	public void setDetails(List<UserVO> details) {
		this.details = details;
	}
	
}

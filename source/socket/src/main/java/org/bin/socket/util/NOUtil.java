package org.bin.socket.util;

public class NOUtil {

	/**
	 * 生成唯一的登录账号，6-12位数字
	 * 剔除数字均相同情况
	 * 6结束才开始7，顺序生成
	 * @return
	 */
	public static String createLoginNO(){
		int value = (int)(Math.random()*10000);
		return String.valueOf(value);
	}
	
	/**
	 * 生成唯一的群号
	 */
	public static String createGroupNO(){
		int value = (int)(Math.random()*100000);
		return String.valueOf(value);
	}
	
	/**
	 * 生成唯一的讨论组号
	 */
	public static String creatDiscuNO(){
		
		return null;
	}
}

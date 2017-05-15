package org.bin.socket.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	public static String md5(String sign){
		return md5Encrypt(sign);
	}
	private static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7',
		'8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };


	/**
	 * 返回16进制的MDS加密串
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @return
	 */
	public static String md5Encrypt(String str) {

		if (StringUtil.isEmpty(str)) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();

			return toHexString(messageDigest.digest(str.getBytes("UTF-8")));

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 把指定的数据转化为16进制格式的字符串
	 * 
	 * @param data
	 *            待转化的数据
	 * @return 16进制表示的数据
	 */
	public static String toHexString(byte[] data) {

		return toHexString(data, 0, data.length);
	}

	/**
	 * 把指定的数据转化为16进制格式的字符串， 如toHexString({8,9,12,4},1,3) = "090C"
	 * 
	 * @param data
	 *            待转化的数据
	 * @param beginIndex
	 *            需转化的数据的起始索引
	 * @param endIndex
	 *            需转化的数据的结束索引
	 * @return 16进制表示的数据
	 */
	public static String toHexString(byte[] data, int beginIndex, int endIndex) {

		if (data == null || beginIndex < 0)
			return null;
		StringBuilder strBuilder = new StringBuilder();
		for (int i = beginIndex; i < endIndex; i++) {
			strBuilder.append(hexDigits[data[i] >>> 4 & 0xf]);
			strBuilder.append(hexDigits[data[i] & 0xf]);
		}
		return strBuilder.toString();
	}

}

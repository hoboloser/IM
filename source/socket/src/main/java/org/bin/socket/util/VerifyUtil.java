package org.bin.socket.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * <p>
 * Title:ValUtil
 * </p>
 * <p>
 * Description:验证器
 * </p>
 * 
 * @author binH
 * @date 2017年3月29日 下午4:59:50
 */
public class VerifyUtil {

	/**
	 * IP验证
	 * @param ipAddress
	 * @return true
	 */
	public static boolean isBoolIp(String ipAddress) {
		 if(null == ipAddress || ipAddress.length() < 7 || ipAddress.length() > 15 || "".equals(ipAddress)) return false;  
         String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";  
         return Pattern.compile(rexp).matcher(ipAddress).find();
	}

	/**
	 * 大陆号码或香港号码均可
	 * @param str phone
	 */
	public static boolean isPhoneLegal(String str) {
		if(null == str || "".equals(str)) return false;
		return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
	}

	/**
	 * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 此方法中前三位格式有： 13+任意数 15+除4的任意数 18+除1和4的任意数
	 * 17+除9的任意数 147
	 */
	private static boolean isChinaPhoneLegal(String str){
		String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 香港手机号码8位数，5|6|8|9开头+7位任意数
	 */
	private static boolean isHKPhoneLegal(String str){
		String regExp = "^(5|6|8|9)\\d{7}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}
}

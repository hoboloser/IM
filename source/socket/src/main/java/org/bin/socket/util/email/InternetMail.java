package org.bin.socket.util.email;


/**
 * 
 * <p>Title:InternetMail</p>
 * <p>Description:电子邮件</p>
 * @author binH
 * @date 2017年3月27日 上午10:10:46
 */
public class InternetMail extends Mail{
	
	/**
	 * 发件人密码 / 授权码
	 */
	private String password;
	/**
	 * 发件人邮箱的SMTP服务器地址
	 * QQ: 
	 * 	接收pop3:pop.qq.com
	 * 	发送	smtp:smtp.qq.com 
	 * 网易：
	 * 	pop.163.com
	 *  smtp.163.com
	 * 
	 */
	private String smtpHost;
	/**
	 * 服务器端口号
	 * QQ：465 or 587
	 */
	private String port;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}

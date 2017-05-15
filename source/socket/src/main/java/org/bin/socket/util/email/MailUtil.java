package org.bin.socket.util.email;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * <p>
 * Title:MailUtil
 * </p>
 * <p>
 * Description:邮件相关
 * </p>
 * 
 * @author binH
 * @date 2017年3月27日 上午9:49:57
 */
public class MailUtil {

	/**
	 * 邮件发送
	 * @param mail
	 * @throws Exception
	 */
	public static void send(InternetMail mail) throws Exception {
		if(null == mail || null == mail.getToList() || mail.getToList().isEmpty() ){
			throw new IllegalArgumentException("参数非法或收件人为空！");
		}
		Properties props = new Properties();
		
		final String userName = mail.getEmail();
		final String password = mail.getPassword();
		
		props.put("mail.transport.protocol", "smtp");// 连接协议
		props.put("mail.smtp.host", mail.getSmtpHost());// 主机名
		props.put("mail.smtp.port", mail.getPort());// 端口号
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
		props.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
		
		Session session = Session.getDefaultInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});
		
		MimeMessage message = createMimeMessage(session,mail);
		
		Transport.send(message);
	}
	/**
	 * 创建一封只包含文本的简单邮件
	 *
	 * @param session
	 *            和服务器交互的会话
	 * @param sendMail
	 *            发件人邮箱
	 * @param receiveMail
	 *            收件人邮箱
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	private static MimeMessage createMimeMessage(Session session,
			InternetMail mail) throws Exception {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);
		
		// 2. From: 发件人
		message.setFrom(new InternetAddress(mail.getEmail(),
				mail.getMailName(), "UTF-8"));
		
		// 收件人
		List list = new ArrayList<InternetAddress>();
		for (Recipient recipient : mail.getToList()) {
			list.add(new InternetAddress(recipient.getMail()));
		}
		InternetAddress[] toAddress =(InternetAddress[])list.toArray(new InternetAddress[list.size()]);
		message.setRecipients(MimeMessage.RecipientType.TO, toAddress);
		
		// 抄送
		if (null != mail.getCcList()) {
			list.clear();
			for (Recipient recipient : mail.getCcList()) {
				list.add(new InternetAddress(recipient.getMail()));
			}
			InternetAddress[] ccAddress =(InternetAddress[])list.toArray(new InternetAddress[list.size()]);
			message.setRecipients(MimeMessage.RecipientType.CC, ccAddress);
		}
		
		// 密送
		if (null != mail.getBccList()) {
			list.clear();
			for (Recipient recipient : mail.getBccList()) {
				list.add(new InternetAddress(recipient.getMail()));
			}
			InternetAddress[] bccAddress =(InternetAddress[])list.toArray(new InternetAddress[list.size()]);
			message.setRecipients(MimeMessage.RecipientType.BCC, bccAddress);
		}
		
		message.setSubject(mail.getTitle(), "UTF-8");
		message.setContent(mail.getContent(), "text/html;charset=UTF-8");
		message.setSentDate(new Date());
		
		message.saveChanges();
		
		return message;
	}
	
	
	public static void sendSimpleEmail(String content,String title,String toemail){
		InternetMail mail = new InternetMail();
		mail.setEmail("731638471@qq.com");
		mail.setPassword("ujelygsqgoiebeah");
		mail.setContent(content);
		mail.setSmtpHost("smtp.qq.com");
		mail.setTitle(title);
		mail.setPort("587");//465 587
		
		List<Recipient> toList = new ArrayList<Recipient>();
		Recipient recipient = new Recipient();
		recipient.setMail(toemail);
		toList.add(recipient);
		mail.setToList(toList);
		try {
			send(mail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

}

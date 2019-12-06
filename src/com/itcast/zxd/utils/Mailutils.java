package com.itcast.zxd.utils;


import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 	用户注册成功之后，向账户发送激活邮件
 * 
 * */
public class Mailutils {
	public static void sendMail(String email, String message) throws AddressException, MessagingException {
		//创建Property对象，并且设置邮件服务器的基本信息
		Properties props = new Properties();
		//设置邮件的传输协议SMTP
		props.setProperty("mail.transport.protocol", "SMTP");
		//设置服务器地址
		props.setProperty("mail.host", "smtp.sohu.com");
		//设置SMTP服务器是否需要需要验证，需要验证则为true
		props.setProperty("mail.smtp.auth", "true");
		//创建验证器
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("itcast_duhong", "1234567890");
			}
		};
		
		//实例化邮件会话
		Session session = Session.getInstance(props, auth);
		
		//创建一个Message，对象相当于邮件内容
		Message msg = new MimeMessage(session);
		
		//设置发送者
		msg.setFrom(new InternetAddress("itcast_duhong@sohu.com"));
		
		//设置发送方式与接收人，邮件接受者在调用sendMail()方法的时候通过参数传递过来
		msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));
		msg.setSubject("用户激活");
		msg.setContent(message, "text/html;charset=utf-8");
		//发送邮件
		Transport.send(msg);
		
	}
}

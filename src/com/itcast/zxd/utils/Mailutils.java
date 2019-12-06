package com.itcast.zxd.utils;


import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 	�û�ע��ɹ�֮�����˻����ͼ����ʼ�
 * 
 * */
public class Mailutils {
	public static void sendMail(String email, String message) throws AddressException, MessagingException {
		//����Property���󣬲��������ʼ��������Ļ�����Ϣ
		Properties props = new Properties();
		//�����ʼ��Ĵ���Э��SMTP
		props.setProperty("mail.transport.protocol", "SMTP");
		//���÷�������ַ
		props.setProperty("mail.host", "smtp.sohu.com");
		//����SMTP�������Ƿ���Ҫ��Ҫ��֤����Ҫ��֤��Ϊtrue
		props.setProperty("mail.smtp.auth", "true");
		//������֤��
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("itcast_duhong", "1234567890");
			}
		};
		
		//ʵ�����ʼ��Ự
		Session session = Session.getInstance(props, auth);
		
		//����һ��Message�������൱���ʼ�����
		Message msg = new MimeMessage(session);
		
		//���÷�����
		msg.setFrom(new InternetAddress("itcast_duhong@sohu.com"));
		
		//���÷��ͷ�ʽ������ˣ��ʼ��������ڵ���sendMail()������ʱ��ͨ���������ݹ���
		msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));
		msg.setSubject("�û�����");
		msg.setContent(message, "text/html;charset=utf-8");
		//�����ʼ�
		Transport.send(msg);
		
	}
}

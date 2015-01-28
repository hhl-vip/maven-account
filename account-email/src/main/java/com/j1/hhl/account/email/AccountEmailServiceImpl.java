package com.j1.hhl.account.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class AccountEmailServiceImpl implements AccountEmailService{
	private JavaMailSender javaMailSender;
	private String from;
	public void sendEmail(String to, String subject, String htmlText) {
		// TODO Auto-generated method stub
		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper msgHelper = new MimeMessageHelper(msg);
		
		try {
			msgHelper.setFrom(from);
			msgHelper.setTo(to);
			msgHelper.setSubject(subject);
			msgHelper.setText(htmlText);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		javaMailSender.send(msg);
	}
	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	
}

package com.j1.hhl.account.email;

import static org.junit.Assert.assertEquals;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;

public class AccountEmailServiceTest {
	private GreenMail greenMail;
	
	
	@Before
	public void startMailServer(){
		
		greenMail = new GreenMail(ServerSetup.SMTP);
		greenMail.setUser("test@juvenxu.com", "123456");
		greenMail.start();
	}
	
	@Test
	public void testSendMail(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("account-email.xml");
		AccountEmailService accountEmailService = (AccountEmailService) ctx.getBean("accountEmailService");
		String subject = "Test Subject";
		String htmlText="<h3>Test</h3>";
		accountEmailService.sendEmail("hhl_vip@sina.cn",subject,htmlText);
		
		try {
			greenMail.waitForIncomingEmail(2000, 1);
			Message [] msgs = greenMail.getReceivedMessages();
			assertEquals(1,msgs.length);
			assertEquals(subject,msgs[0].getSubject());
			assertEquals(htmlText,GreenMailUtil.getBody(msgs[0]).trim());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@After
	public void stopMailServer(){
		greenMail.stop();
	}
}

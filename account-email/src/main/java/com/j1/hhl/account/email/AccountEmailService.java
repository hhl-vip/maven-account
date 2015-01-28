package com.j1.hhl.account.email;

public interface AccountEmailService {
	
	void sendEmail(String to,String subject,String htmlText);

}

package com.ken.raken.example.module.notification.service;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sendgrid.*;

public class SendGridEmailService implements SendServiceProvider{
	
	Logger logger = LogManager.getLogger(SendGridEmailService.class);
	
	
	public void send(final String mailFrom, final String recipient, final String subject, final String body)  {
		logger.info("Send email from {} to {} subject {}", mailFrom, recipient, subject);
		Email from = new Email(mailFrom);
	 	Content content = new Content("text/plain", body);    
	    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));	  
    	Request request = new Request();
    	Email to = new Email(recipient);
    	Mail mail = new Mail(from, subject, to, content);
  	    request.setMethod(Method.POST);
	    request.setEndpoint("mail/send");
	   
	    try {
	    	  request.setBody(mail.build());
		      Response response = sg.api(request);
		      logger.info(response.getStatusCode());
		     
	    } catch (IOException e) {
	    	logger.error(e);
	    }
	  
	}

}

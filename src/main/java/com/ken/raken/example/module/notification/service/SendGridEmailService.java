package com.ken.raken.example.module.notification.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sendgrid.*;

@Component
public class SendGridEmailService implements SendServiceProvider{
	
	Logger logger = LogManager.getLogger(SendGridEmailService.class);
	
	@Autowired
	private TemplateHelper templateHelper;
	
	@Value("${email.template:templates/email_html_1.template}")
	private String templatePath;
	
	@Autowired
	private SendGrid sg;

	
	public void send(final String mailFrom, final String recipient, final String subject, final String body, final String motd)  {
		logger.info("Send email from {} to {} subject {}", mailFrom, recipient, subject);
		Email from = new Email(mailFrom);
		final String htmlBody = templateHelper.buildByTemplate(new HashMap<String, String>() {
					    	{
					    		put("body", body);
					    		put("motd", motd);
					    	}
					    }, templatePath);
	 	Content content = new Content("text/html", htmlBody);  	 
    	Request request = new Request();
    	Email to = new Email(recipient);
    	Mail mail = new Mail(from, subject, to, content);
  	    request.setMethod(Method.POST);
	    request.setEndpoint("mail/send");
	   
	    try {
	    	  request.setBody(mail.build());
		      Response response = sg.api(request);
		      logger.info(response.getStatusCode());
		     // save status for tracking
	    } catch (IOException e) {
	    	logger.error(e);
	    }
	  
	}

}

package com.ken.raken.example.module.notification.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ken.raken.example.module.notification.rest.RestClientHelper;
import com.sendgrid.SendGrid;


@Component
public class SendServiceProviderSimpleFactory {
	
	@Value("${send.raken.only:true}")
    private String sendRakenOnly;
			
	private SendGridEmailService sendGridEmailService;

	
	
	/**
	 * Factory to create send service provider.
	 * 
	 * If email has domain rakenapp.com, return SendGridEmailService that sends the email to sendGrid
	 * 
	 * Default: LoggingEmailService - log the email message
	 * 
	 * @param email
	 * @return
	 */
    protected SendServiceProvider getSendServiceProvider(final String email) {

    	if(Boolean.parseBoolean(sendRakenOnly)) {
    		if(email.contains("rakenapp.com")) {
        		
        		return getSendGridEmailService();
        	}
    		return new LoggingEmailService();
        	
    	}
    	
    	return  getSendGridEmailService();
    }
    
    
    private SendGridEmailService getSendGridEmailService() {
    	// improve performance
    	if(sendGridEmailService == null) {
    		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));	 
    		sendGridEmailService = new SendGridEmailService(sg);    		
    	}
    	
    	return sendGridEmailService;
    }

}

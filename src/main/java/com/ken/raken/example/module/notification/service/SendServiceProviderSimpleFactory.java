package com.ken.raken.example.module.notification.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class SendServiceProviderSimpleFactory {
	
	@Value("${send.raken.only:true}")
    private String sendRakenOnly;
	
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
        		return new SendGridEmailService();
        	}
            
        	return new LoggingEmailService();
    	}
    	
    	return new SendGridEmailService();
    	
    }

}

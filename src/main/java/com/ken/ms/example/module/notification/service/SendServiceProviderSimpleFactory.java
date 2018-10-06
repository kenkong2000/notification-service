package com.ken.ms.example.module.notification.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ken.ms.example.module.notification.rest.RestClientHelper;
import com.sendgrid.SendGrid;


@Component
public class SendServiceProviderSimpleFactory {
	
	@Value("${send.raken.only:true}")
    private String sendRakenOnly;
	
	@Autowired
	private SendGridEmailService sendGridEmailService;

	@Autowired
	private LoggingEmailService loggingEmailService;
	
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
        		return sendGridEmailService;
        		
        	}
    		return loggingEmailService;
        	
    	}
    	return sendGridEmailService;
    	
    }    


}

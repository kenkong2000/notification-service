package com.ken.raken.example.module.notification.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.Logger;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;

import com.ken.raken.example.module.notification.exception.ApiException;
import com.ken.raken.example.module.notification.model.NotificationDto;



@Component
public class NotificationService {
	
	
	@Value("${from.email:ykkong7@hotmail.com}")
    private String fromEmail;
	
	Logger logger = LogManager.getLogger(NotificationService.class);
	
	@Autowired
	private SendServiceProviderSimpleFactory sendServiceProviderSimpleFactory;
	
	@Autowired
	private MotdHelper motdHelper;
	
	 @PostConstruct
	 public void init() {
		// any init goes here
	 }
	
	@Async
	public void sendEmailAsyn(final List<NotificationDto> dtos, Boolean enrich) throws ApiException {
		logger.info("asyn task - start sending emails");
		
		for(NotificationDto notification : dtos) {			
			String motd = null;
								
			// filter invalid emails 
			List<String> sendList = new ArrayList<>();
			filterMail(notification.getTo(), sendList);
			filterMail(notification.getBcc(), sendList);
			filterMail(notification.getCc(), sendList);
			if(enrich) {
				motd = motdHelper.getMessageOfTheDay();				
			}		
			
			for(String recipient: sendList) {
				// SendServiceProviderSimpleFactory could be extended to add other send service provider such as SMS, application notification etc
				SendServiceProvider sendServiceProvider = sendServiceProviderSimpleFactory.getSendServiceProvider(recipient);			
				sendServiceProvider.send(fromEmail, recipient, notification.getSubject(), notification.getBody(), motd);			
			}
		}
	   	    
	}
	
		
	private void filterMail(List<String> emails, List<String> sendList) {
		if(emails == null || emails.isEmpty()) {
			return;
		}
		
		List<String> result = emails.stream()               
                .filter(line -> line.matches("[a-zA-Z0-9._%-]+@[a-zA-Z0-9._%-]+\\.[a-zA-Z]{2,4}"))    
                .collect(Collectors.toList());    
		
		
		sendList.addAll(result);
		
	}

}

package com.ken.raken.example.module.notification.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.Logger;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;

import com.ken.raken.example.module.notification.exception.ApiException;
import com.ken.raken.example.module.notification.model.NotificationDto;

import com.ken.raken.example.module.notification.rest.RestClientHelper;
import com.ken.raken.example.module.notification.rest.RestClientHelper.RestClientHelperResponse;

@Component
public class NotificationService {
	
		
	@Value("${motd.source:}")
    private String motdSource;
	
	@Value("${motd.user:}")
    private String motdSourceUser;
	
	@Value("${motd.password:}")
    private String motdSourcePassword;
	
	@Value("${from.email:ykkong7@hotmail.com}")
    private String fromEmail;
	
	Logger logger = LogManager.getLogger(NotificationService.class);
	
	@Autowired
	private SendServiceProviderSimpleFactory sendServiceProviderSimpleFactory;
	

	private RestClientHelper restClientHelper;
	
	 @PostConstruct
	 public void init() {
		 restClientHelper = new RestClientHelper();
	 }
	
	@Async
	public void sendEmailAsyn(final List<NotificationDto> dtos, Boolean enrich) throws ApiException {
		logger.debug("asyn task - start sending emails");
		
		if(enrich == null) {
			enrich=false;
		}
		for(NotificationDto notification : dtos) {
			 sendNotification(notification, enrich);
		}
	   	    
	}
	
	public void sendNotification(final NotificationDto dto, final boolean enrich) throws ApiException {
				
		
		
		if(enrich) {
			RestClientHelperResponse messageOfTheDay = restClientHelper.getnResourceByBasicAuth(motdSource,motdSourceUser, motdSourcePassword);
			//TODO use template engine for example Velocity to insert message of the day header
			StringBuilder sb = new StringBuilder(dto.getBody());
			sb.append("\n").append(messageOfTheDay.getBody());
			dto.setBody(sb.toString());
		}
		
				
		// filter invalid emails 
		List<String> sendList = new ArrayList<>();
		filterMail(dto.getTo(), sendList);
		filterMail(dto.getBcc(), sendList);
		filterMail(dto.getCc(), sendList);
		
		for(String recipient: sendList) {
			SendServiceProvider sendServiceProvider = sendServiceProviderSimpleFactory.getSendServiceProvider(recipient);			
			sendServiceProvider.send(fromEmail, recipient, dto.getSubject(), dto.getBody());			
		}
		
	}
	
	
	public List<NotificationDto> getNotificationResult(final String fromEmail, final int pageSize, final int pageIndex) {
		
		// To retrieve the notification result based on the fromEmail. This is currently out of scope of this demo but it shouldn't be hard to complete.
		try {
			
			// retrieve from data source 
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			//Return empty list if any error instead of 
			return new ArrayList<>();
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

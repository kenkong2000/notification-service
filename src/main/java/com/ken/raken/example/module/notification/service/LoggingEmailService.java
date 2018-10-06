package com.ken.raken.example.module.notification.service;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;


@Component
public class LoggingEmailService implements SendServiceProvider{
	
	Logger logger = LogManager.getLogger(LoggingEmailService.class);
	
	
	public void send(final String mailFrom, String recipient, final String subject, final String body, final String motd)  {		
		logger.info("SEND FROM:" +mailFrom+" TO " +recipient);
		logger.info("SUBJECT:" +subject);
		logger.info(body);
		logger.info("\n{}", motd);
	}

}

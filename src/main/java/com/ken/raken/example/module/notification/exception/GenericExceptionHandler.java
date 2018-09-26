package com.ken.raken.example.module.notification.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ken.raken.example.module.notification.service.NotificationService;

//@RestControllerAdvice
public class GenericExceptionHandler {
//	Logger logger = LogManager.getLogger(NotificationService.class);
//	
//	 @RequestMapping(produces = "application/json")
//	 @ExceptionHandler(Throwable.class)
//	 //TODO return ResponseEntity<ErrorDetails>
//	 public String handleException(Throwable ex) {
//		 logger.error(ex);
//		 // Error mapper goes here and return an error object
//	
//		 return  ex.getMessage();
//	 }

}

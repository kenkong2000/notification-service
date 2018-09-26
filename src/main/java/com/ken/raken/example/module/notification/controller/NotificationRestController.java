package com.ken.raken.example.module.notification.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ken.raken.example.module.notification.exception.ApiException;
import com.ken.raken.example.module.notification.model.NotificationDto;

import com.ken.raken.example.module.notification.service.NotificationService;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/notification")
public class NotificationRestController {
	
	  @Autowired
	  private NotificationService service;
	
	 @RequestMapping(value = "/email", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	 public ResponseEntity<?> createEmails(@RequestBody List<NotificationDto> notificationDtos, @RequestParam(value="enrich", required=false) final Boolean enrich)  throws ApiException {
		 	service.sendEmailAsyn(notificationDtos, enrich);
		 	
	        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	    }
	 
	 
//	
//	 @RequestMapping(value = "/email", method = RequestMethod.GET, produces = { "application/json" } )
//	 public ResponseEntity<List<NotificationDto>> listAll(@RequestParam(value="fromemail", required=true) final String nameFilter, 
//	    		@RequestParam(value="pageIndex", required=false, defaultValue="1") final Integer pageIndex,
//	            @RequestParam(value="pageSize", required=false, defaultValue="100") final Integer pageSize) {
//	        List<NotificationDto> result = service.getNotificationResult(nameFilter, pageSize, pageIndex);	       
//	        return new ResponseEntity<List<NotificationDto>>(result, HttpStatus.OK);
//	 }
	

}

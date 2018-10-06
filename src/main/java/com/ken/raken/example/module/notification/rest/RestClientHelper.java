package com.ken.raken.example.module.notification.rest;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ken.raken.example.module.notification.exception.ApiException;
import com.ken.raken.example.module.notification.security.AuthType;



@Component
public class RestClientHelper {

	Logger logger = LogManager.getLogger(RestClientHelper.class);	

	
	 @Autowired
	 private RestTemplate restTemplate;
		
	public RestClientHelper() {
		
	}

	public ResponseEntity<String> getnResource(final String url, final HttpMethod method, final MediaType mediaType, final AuthType authType) throws ApiException {
	    HttpHeaders headers = authType.getHttpHeaders();
	    headers.setContentType(mediaType);
		HttpEntity<String> request = new HttpEntity<String>(authType.getHttpHeaders());
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, method, request, String.class);
		return responseEntity;
	}
	
 

}

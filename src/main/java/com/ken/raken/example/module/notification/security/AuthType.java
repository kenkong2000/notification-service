package com.ken.raken.example.module.notification.security;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

public interface AuthType {
	
	public HttpHeaders getHttpHeaders();

}

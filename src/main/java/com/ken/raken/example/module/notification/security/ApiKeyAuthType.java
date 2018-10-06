package com.ken.raken.example.module.notification.security;


import org.springframework.http.HttpHeaders;

public class ApiKeyAuthType implements AuthType{
	
	
	private String apiKeyValue;
	
	public ApiKeyAuthType(final String apiKeyValue) {		
		this.apiKeyValue = apiKeyValue;
	}
	
	public HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + apiKeyValue);		
		return headers;
	}

}

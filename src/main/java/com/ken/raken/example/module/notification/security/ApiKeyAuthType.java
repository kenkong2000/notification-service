package com.ken.raken.example.module.notification.security;


import org.springframework.http.HttpHeaders;

public class ApiKeyAuthType implements AuthType{
	
	private String apiKeyName;
	private String apiKeyValue;
	
	public ApiKeyAuthType(final String apiKeyName, final String apiKeyValue) {
		this.apiKeyName = apiKeyName;
		this.apiKeyValue = apiKeyValue;
	}
	
	public HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(apiKeyName, apiKeyValue);
		return headers;
	}

}

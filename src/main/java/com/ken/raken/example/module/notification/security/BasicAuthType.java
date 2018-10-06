package com.ken.raken.example.module.notification.security;


import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

public class BasicAuthType implements AuthType{
	
	private String user;
	private String pw;
	
	public BasicAuthType(final String user, final String pw) {
		this.user = user;
		this.pw = pw;
	}
	
	public HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		final String plainCreds = user+":"+pw;		
		byte[] base64CredsBytes = Base64.encodeBase64(plainCreds.getBytes());
		String base64Creds = new String(base64CredsBytes);		
		headers.add("Authorization", "Basic " + base64Creds);
		return headers;
		
	}

}

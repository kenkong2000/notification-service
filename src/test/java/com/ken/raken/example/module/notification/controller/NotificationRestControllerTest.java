package com.ken.raken.example.module.notification.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
//import org.apache.commons.httpclient.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ken.raken.example.module.notification.model.NotificationDto;
import com.ken.raken.example.module.notification.security.SpringSecurityConfig;
import com.ken.raken.example.module.notification.model.NotificationDto;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotificationRestControllerTest {

	@Autowired
	private SpringSecurityConfig config;
	
	 @Autowired
	 private TestRestTemplate restTemplate;
	 
	 HttpHeaders headers = new HttpHeaders();
	 
	 @Before
	 public void setup() {
		 String plainCreds = config.getBasicCred();
		 byte[] plainCredsBytes = plainCreds.getBytes();
		 byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		 String base64Creds = new String(base64CredsBytes);		
		 headers.add("Authorization", "Basic " + base64Creds);
	 }
	 
	 @Test
	 public void whenCallWithoutAuth_thenNotAuthorised() {
		 List<NotificationDto> list = new ArrayList<>();
	        ResponseEntity<String> responseEntity =
	            restTemplate.postForEntity("/api/notification/email", list, String.class);	       
	        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
	 }
	 
	 @Test
	 public void  whenCallWithAuth_butNoBody_thenBadRequest() {
		 List<NotificationDto> list = new ArrayList<>();
		 HttpEntity<String> request = new HttpEntity<String>(headers);
		 ResponseEntity<String> responseEntity = restTemplate.exchange("/api/notification/email", HttpMethod.POST, request, String.class);
		 assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	 }
	 
	 
	 // With body but need to unblock the validator @RequestBody
	 


}

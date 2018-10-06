package com.ken.raken.example.module.notification.controller;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.test.context.junit4.SpringRunner;



import com.ken.raken.example.module.notification.model.NotificationDto;
import com.ken.raken.example.module.notification.security.SpringSecurityConfig;
import com.ken.raken.example.module.notification.service.NotificationService;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotificationRestControllerTest {

	@Autowired
	private SpringSecurityConfig config;
	
	 @Autowired
	 private TestRestTemplate restTemplate;
	 
	 NotificationService serviceMock;
	 
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
	 public void  whenCallWithAuth_wrongContentType_thenBadRequest() {
		 headers.setContentType(MediaType.APPLICATION_XML);
		 HttpEntity<String> request = new HttpEntity<String>(headers);
		 ResponseEntity<String> responseEntity = restTemplate.exchange("/api/notification/email", HttpMethod.POST, request, String.class);
		 assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, responseEntity.getStatusCode());
		 
		 
	 }
	 
	 @Test
	 public void  whenCallWithAuth_butNoBody_thenBadRequest() {
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 HttpEntity<String> request = new HttpEntity<String>(headers);
		 ResponseEntity<String> responseEntity = restTemplate.exchange("/api/notification/email", HttpMethod.POST, request, String.class);
		 assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		 
	 }
	 
		 
	 @Test
	 public void  whenCallWithWrongJsonBody_NotAList_thenSuccess() {
		 
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 String requestJson ="{\"to\":[\"ken@test.com\"],\"subject\":\"my subject\", \"body\":\"hellow world!\" }";
		 HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		 ResponseEntity<String> responseEntity = restTemplate
		            .exchange("/api/notification/email", HttpMethod.POST, entity, String.class);		
		 assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());		
	 }
	 
	 @Test
	 public void  whenCallWithJsonBody_thenSuccess() {
		 
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 String requestJson ="[{\"to\":[\"ken@test.com\"],\"subject\":\"my subject\", \"body\":\"hellow world!\" }]";
		 System.out.println("requestJson:" +requestJson);
		 HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
			
		 ResponseEntity<NotificationDto[]> responseEntity = restTemplate
		            .exchange("/api/notification/email", HttpMethod.POST, entity, NotificationDto[].class);		
		 assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
		 
	 }
	 
	 @Test
	 public void  whenCallWith_multiple_toaddress_thenSuccess() {
		 	 
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 String requestJson ="[{\"to\":[\"ken@test.com\", \"test@rakenapp.com\"],\"subject\":\"my subject\", \"body\":\"hellow world!\" }]";
		 HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		 ResponseEntity<String> responseEntity = restTemplate
		            .exchange("/api/notification/email", HttpMethod.POST, entity, String.class);		
		 assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
		
	 }
	 	
	 
	 @Test
	 public void  whenCallWith_invalid_NotificationDto_thenSuccess() {
		 	 
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 // to1
		 String requestJson ="[{\"to1\":[\"ken@test.com\", \"test@rakenapp.com\"],\"subject\":\"my subject\", \"body\":\"hellow world!\" }]";
		 HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		 ResponseEntity<String> responseEntity = restTemplate
		            .exchange("/api/notification/email", HttpMethod.POST, entity, String.class);		
		 assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		 assertTrue(responseEntity.getBody().contains("to is required"));
		 
	 }
		 
	 
}

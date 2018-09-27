package com.ken.raken.example.module.notification.test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ken.raken.example.module.notification.controller.NotificationRestController;
import com.ken.raken.example.module.notification.service.NotificationService;

import ch.qos.logback.core.net.server.Client;
import com.ken.raken.example.module.notification.model.NotificationDto;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotifServiceApplicationTests {
	
	

	@Test
	public void contextLoads() {
//		mvc.perform(post("/api/notification/email")
//			      .body(""))
//			      .andExpect(400)
		
		// Need some time to make the mvc.perform to work but test cases can start here
		
		//mvc.perform(post("/api/notification/email")
		//		      .body("[\"to\":[\"test@test.com\"], \"subject\":\"test\", \"body\":\"just body\" ]"))
		//		      .andExpect(204);
		
		
	}

	

}

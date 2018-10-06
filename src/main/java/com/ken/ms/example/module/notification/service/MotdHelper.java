package com.ken.ms.example.module.notification.service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ken.ms.example.module.notification.exception.ApiException;
import com.ken.ms.example.module.notification.rest.RestClientHelper;
import com.ken.ms.example.module.notification.security.ApiKeyAuthType;



@Component
public class MotdHelper {
	
	@Value("${motd.source:}")
    private String motdSource;
	
	@Value("${MOTD_API_KEY}")
	private String openWeatherApiKey;
	
	@Value("#{'${motd.locations}'.split(',')}")
	private List<String> locations;
	
	@Value("${motd.template:The weather in $city is $desc}")
	private String motd;
	
	@Autowired
	private TemplateHelper templateHelper;
	
	Logger logger = LogManager.getLogger(MotdHelper.class);
	

	@Autowired
	private RestClientHelper restClientHelper;
	
	protected String getMessageOfTheDay() {
		String result = "";
		try {
			final String location=locations.get(new Random().nextInt(locations.size()));
			ResponseEntity<String> response = restClientHelper.getnResource(buildUrl(location), HttpMethod.GET, MediaType.APPLICATION_JSON,
																								new ApiKeyAuthType(openWeatherApiKey));
			if(response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
			    JsonNode jsonObject = mapper.readTree(response.getBody()).path("weather");
			    
			    if(jsonObject != null && jsonObject.isArray() ) {
			    	final String weatherDescription = jsonObject.get(0).path("description").textValue();
			    	 result = templateHelper.buildByTemplate(new HashMap<String, String>() {
					    	{
					    		put("city", location);
					    		put("desc", weatherDescription);
					    	}
					    }, motd, "Random Motd Weather");
			    }			   
			}
		
		} catch (ApiException | IOException | ParseException e) {
			// motd error shouldn't affect the main service
			logger.error("error when calling motd - {}", e);
		}
		return result;
	}
	
	
	private String buildUrl(final String location) {
		StringBuilder sb = new StringBuilder(motdSource).append("?q=");		
		sb.append(location).append("&APPID=").append(openWeatherApiKey);
		return sb.toString();
	}	
	

}

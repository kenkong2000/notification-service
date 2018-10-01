package com.ken.raken.example.module.notification.rest;


import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ken.raken.example.module.notification.exception.ApiException;
import com.ken.raken.example.module.notification.service.NotificationService;

import java.io.IOException;


public class RestClientHelper {

	Logger logger = LogManager.getLogger(RestClientHelper.class);
		
	public RestClientHelper() {
		
	}
    
    public RestClientHelperResponse getnResourceByBasicAuth(final String url, final String user, final String password) throws ApiException {
    	    	
        GetMethod method = new GetMethod(url);
        method.setDoAuthentication(true);
        method.setRequestHeader("MimeType", "application/json");
        method.setRequestHeader("Content-type", "application/json; version=1.0");
        method.setRequestHeader("Accept", "application/json; version=1.*");

        RestClientHelperResponse result = new RestClientHelperResponse();

            try {
                HttpClient client = new HttpClient();
                Credentials credentials = new UsernamePasswordCredentials(user, password);
                HttpState state = client.getState();
                state.setCredentials(AuthScope.ANY, credentials);
                client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

                int statusCode = client.executeMethod(method);
                result.setStatus(statusCode);
                result.setBody(method.getResponseBodyAsString());

            } catch (IOException e) {
            	//should not affect the process if motd fail
            	logger.info("fail to retrieve motd: {}", e.getMessage());
            } finally {
                method.releaseConnection();
            }

        return result;
    }



    public class RestClientHelperResponse {
        private int status;
        private String body;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }


}

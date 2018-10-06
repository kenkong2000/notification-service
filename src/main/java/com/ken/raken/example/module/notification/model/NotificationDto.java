package com.ken.raken.example.module.notification.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class NotificationDto {

	@JsonProperty("to")
	@NotNull(message = "to is required")
	@NotEmpty(message = "to is required")
	private List<String> to = null;
	
	@JsonProperty("cc")
	private List<String> cc = null;
	
	@JsonProperty("bcc")
	private List<String> bcc = null;

	@JsonProperty("subject")
	private String subject;

	@JsonProperty("body")
	private String body;
	

	
	@JsonCreator
	public NotificationDto(List<String> to, 
			List<String> cc, 
			List<String> bcc, 
			String subject, 
			String body) {
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.body = body;
	}
	

	public List<String> getTo() {
		return to;
	}
	

	public void setTo(List<String> to) {
		this.to = to;
	}
	

	public List<String> getCc() {
		return cc;
	}
	

	public void setCc(List<String> cc) {
		this.cc = cc;
	}
	

	public List<String> getBcc() {
		return bcc;
	}
	

	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
	}
	

	public String getSubject() {
		return subject;
	}
	

	public void setSubject(String subject) {
		this.subject = subject;
	}
	

	public String getBody() {
		return body;
	}
	

	public void setBody(String body) {
		this.body = body;
	}

}


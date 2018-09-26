package com.ken.raken.example.module.notification.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"to",
"cc",
"bcc",
"subject",
"body"
})
public class NotificationDto {

	@JsonProperty("to")
	private List<String> to = null;
	@JsonProperty("cc")
	private List<String> cc = null;
	@JsonProperty("bcc")
	private List<String> bcc = null;
	@JsonProperty("subject")
	private String subject;
	@JsonProperty("body")
	private String body;
	
	@JsonProperty("to")
	@NotNull(message = "to is required")
	@NotEmpty(message = "to is required")
	public List<String> getTo() {
		return to;
	}
	
	@JsonProperty("to")
	public void setTo(List<String> to) {
		this.to = to;
	}
	
	@JsonProperty("cc")
	public List<String> getCc() {
		return cc;
	}
	
	@JsonProperty("cc")
	public void setCc(List<String> cc) {
		this.cc = cc;
	}
	
	@JsonProperty("bcc")
	public List<String> getBcc() {
		return bcc;
	}
	
	@JsonProperty("bcc")
	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
	}
	
	@JsonProperty("subject")
	public String getSubject() {
		return subject;
	}
	
	@JsonProperty("subject")
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@JsonProperty("body")
	public String getBody() {
		return body;
	}
	
	@JsonProperty("body")
	public void setBody(String body) {
		this.body = body;
	}

}


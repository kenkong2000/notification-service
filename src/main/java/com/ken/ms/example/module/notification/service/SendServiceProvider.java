package com.ken.ms.example.module.notification.service;

import java.util.List;

public interface SendServiceProvider {
	
	public void send(final String mailFrom, String recipient, final String subject, final String body, final String motd);

}

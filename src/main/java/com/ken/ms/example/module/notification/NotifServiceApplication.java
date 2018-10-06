package com.ken.ms.example.module.notification;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import com.sendgrid.SendGrid;

@SpringBootApplication
@EnableAsync
public class NotifServiceApplication {
	
	@Value("${pool.size:1}")
    private int poolSize;
	
	@Value("${max.pool.size:10}")
    private int maxPoolSize;
	
	@Value("${queue.capacity:200}")
    private int queueCapacity;

	public static void main(String[] args) {
		SpringApplication.run(NotifServiceApplication.class, args);
		
	}
	
	
	@Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(poolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("NotificationService-");
        executor.initialize();
        return executor;
    }
	
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		// enable Autowired restTemplate
	   return builder.build();
	}
	
	@Bean
	public SendGrid sendGrid(RestTemplateBuilder builder) {
		// enable Autowired sendGrid
	   return new SendGrid(System.getenv("SENDGRID_API_KEY"));
	}
}

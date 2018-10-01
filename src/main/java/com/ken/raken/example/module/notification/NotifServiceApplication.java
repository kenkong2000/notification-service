package com.ken.raken.example.module.notification;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

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
}

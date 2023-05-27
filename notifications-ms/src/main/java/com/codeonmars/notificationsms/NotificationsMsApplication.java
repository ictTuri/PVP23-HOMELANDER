package com.codeonmars.notificationsms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NotificationsMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationsMsApplication.class, args);
	}

}

package com.codeonmars.filesms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FilesMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilesMsApplication.class, args);
	}

}

package com.codeonmars.issuesms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IssuesMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssuesMsApplication.class, args);
	}

}

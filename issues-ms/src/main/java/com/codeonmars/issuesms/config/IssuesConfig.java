package com.codeonmars.issuesms.config;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableMongock
@EnableDiscoveryClient
public class IssuesConfig {
}

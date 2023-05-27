package com.codeonmars.issuesms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public UserHeadersInterceptor securityInterceptor(){
        return new UserHeadersInterceptor();
    }
}

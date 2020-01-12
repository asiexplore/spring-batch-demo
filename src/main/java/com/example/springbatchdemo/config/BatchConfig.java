package com.example.springbatchdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BatchConfig {
	@Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

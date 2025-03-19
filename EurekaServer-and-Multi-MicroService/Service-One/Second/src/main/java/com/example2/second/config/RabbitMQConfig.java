package com.example2.second.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration  // ✅ Fix: Mark as a configuration class
public class RabbitMQConfig {

    @Bean
    @LoadBalanced  // Allows calling services via Eureka names
    public RestTemplate restTemplate() {
	   return new RestTemplate();
    }

}

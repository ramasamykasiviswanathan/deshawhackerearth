package com.deshaw.deshawhackerearth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages="com.deshaw")
public class DeshawhackerearthApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeshawhackerearthApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplateConfigurer(RestTemplateBuilder builder)
	{
		return builder.build();
	}
}

package com.company.apirest.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.company.apirest.backend.service.ISumaService;
import com.company.apirest.backend.service.SumaServiceImpl;

@SpringBootApplication
public class ApirestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirestApplication.class, args);
	}
	
	 @Bean
	    public ISumaService sumaService() {
	        return new SumaServiceImpl();
	    }

}

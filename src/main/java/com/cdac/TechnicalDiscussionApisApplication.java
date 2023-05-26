package com.cdac;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@ComponentScan("com.cdac.repositories")

@SpringBootApplication
public class TechnicalDiscussionApisApplication {

	public static void main(String[] args) {
		System.out.println("inside main method");
		SpringApplication.run(TechnicalDiscussionApisApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}

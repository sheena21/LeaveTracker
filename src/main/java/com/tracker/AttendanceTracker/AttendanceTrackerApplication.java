package com.tracker.AttendanceTracker;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class AttendanceTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceTrackerApplication.class, args);
	}
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		return  new BCryptPasswordEncoder();
	}

	@Bean
	public ModelMapper modelMapper()
	{
		return  new ModelMapper();
	}
}

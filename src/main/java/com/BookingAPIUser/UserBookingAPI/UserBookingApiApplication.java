package com.BookingAPIUser.UserBookingAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "BOOK MY_SLOT USER",version = "1.0" , description= "API for book my slot"))
public class UserBookingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserBookingApiApplication.class, args);
	}

}

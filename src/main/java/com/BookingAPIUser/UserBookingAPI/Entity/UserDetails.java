package com.BookingAPIUser.UserBookingAPI.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Document(collection = "DataByUser")
@Data
@NoArgsConstructor
@AllArgsConstructor




public class UserDetails {
	
	@Id
	private String userId;
	private String fname;
	private String lname;
	private int minutesSaved;
	private vehicleDetails vDetails;
	private Tickets tickets;
	
	
	}


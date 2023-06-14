package com.BookingAPIUser.UserBookingAPI.Entity;

import java.util.List;

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
	private Boolean isTowed;
	private int message;
	
	private List<vehicleDetails> vDetails;
	private DefaultVehicle defaultvehicle;
    private List<Tickets> tickets;
	
	
	}


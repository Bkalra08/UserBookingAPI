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
	private int bookingID;
	private String VehicleType;
	private String Date;
	private String Time;
	private int PlaceId;
	


}

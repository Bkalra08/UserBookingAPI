package com.BookingAPIUser.UserBookingAPI.Entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Tickets {
	
	@Id
	private String ticketId;
	private boolean ActiveStatus;
	private String ticketIdCheckout;
	private String ticketIdCheckin;
	private String ownerId;
	private String bookingtime;
	private String checkinTime;
	private String checkoutTime;
	private int duration;
	

}

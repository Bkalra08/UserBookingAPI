package com.BookingAPIUser.UserBookingAPI.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Tickets {
	
	
	private String ticketId;
	private boolean ActiveStatus;
	private String ticketIdCheckout;
	private String ticketIdCheckin;
	private String ownerId;
	private String bookingtime;
	private String CheckinTime;
	private String CheckoutTime;
	private int duration;
	

}

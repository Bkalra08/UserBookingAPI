package com.BookingAPIUser.UserBookingAPI.Services;

import com.BookingAPIUser.UserBookingAPI.Entity.UserDetails;

public interface serviceInterface {

	public UserDetails addDetails(UserDetails details);
	public UserDetails getDetails(int bookingID);
	public UserDetails findById(int bookingID);
	void DeleteUserData(int bookingID);

}

package com.BookingAPIUser.UserBookingAPI.Services;
import com.BookingAPIUser.UserBookingAPI.Entity.UserDetails;

public interface serviceInterface {

	public UserDetails addDetails(UserDetails details);
	public UserDetails getDetails(String userId);
	public UserDetails findById(String userId);
	void DeleteUserData(String userId);
	public void updateTicket(String ownerId , UserDetails udetails);
	public void updateVehicle(String ownerId, UserDetails usdetails);


}

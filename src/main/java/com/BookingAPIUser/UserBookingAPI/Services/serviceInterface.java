package com.BookingAPIUser.UserBookingAPI.Services;
import com.BookingAPIUser.UserBookingAPI.Entity.Tickets;
import com.BookingAPIUser.UserBookingAPI.Entity.UserDetails;
import com.BookingAPIUser.UserBookingAPI.Entity.UserDetailsResponse;

public interface serviceInterface {

	public UserDetails addDetails(UserDetails details);
	public UserDetails getDetails(String userId);
	public UserDetails findById(String userId);
	void DeleteUserData(String userId);
	public void updateTicket(String ownerId , UserDetails udetails);
	public void updateVehicle(String ownerId, UserDetails usdetails);
	UserDetailsResponse getUserDetails(String userId);
	void setDefaultVehicle(String ownerId, UserDetails userDetails);
	Tickets getSpecificTicket(String userId, String ticketId);
	Tickets getLatestTicket(String userId);
	Boolean updateIsTowed(String userId, UserDetails userDetails);
	Boolean updateMessage(String userId, UserDetails userDetails);
	void updateTicket(String ownerId, String ticketId, Tickets updatedTicket);
//	Tickets getTicketById(String ticketId);



}

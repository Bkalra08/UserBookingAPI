package com.BookingAPIUser.UserBookingAPI.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BookingAPIUser.UserBookingAPI.Daolayer.DaoLayer;
import com.BookingAPIUser.UserBookingAPI.Entity.UserDetails;



@Service
public class Servicess implements serviceInterface {
	
	@Autowired
	private DaoLayer repo;

	@Override
	public UserDetails addDetails(UserDetails details) {
		repo.save(details);
		return details;
	}

	@Override
	public UserDetails getDetails(int bookingID) {
		return repo.findById(bookingID);
	}

	@Override
	public UserDetails findById(int bookingID) {
		return repo.findById(bookingID);
	}

	@Override
	public void DeleteUserData(int bookingID) {
		repo.deleteById(bookingID);
		
	}



}

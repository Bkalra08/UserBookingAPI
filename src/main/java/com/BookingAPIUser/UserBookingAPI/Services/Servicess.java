package com.BookingAPIUser.UserBookingAPI.Services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BookingAPIUser.UserBookingAPI.Daolayer.DaoLayer;
import com.BookingAPIUser.UserBookingAPI.Entity.UserDetails;
import com.BookingAPIUser.UserBookingAPI.Entity.UserDetailsResponse;



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
	public UserDetails getDetails(String userId) {
		return repo.findById(userId).orElseThrow(() -> new RuntimeException("AdminDetails not found"));
	}

	@Override
	public UserDetails findById(String userId) {
		return repo.findById(userId).orElseThrow(() -> new RuntimeException("AdminDetails not found"));
	}

	@Override
	public void DeleteUserData(String userId) {
		repo.deleteById(userId);
		
	}

	@Override
	public void updateTicket(String ownerId, UserDetails udetails) {
		Optional<UserDetails>UserById  = Optional.of(repo.findById(ownerId)).orElseThrow(() -> new RuntimeException("AdminDetails not found"));
		UserDetails UserDetails = UserById.get();
		UserDetails.setTickets(udetails.getTickets());
		repo.save(UserDetails);
		
	}
//	@Override
//	public void updateVehicle(String ownerId, UserDetails usdetails) {
//		Optional<UserDetails>UserById  = Optional.of(repo.findById(ownerId)).orElseThrow(() -> new RuntimeException("AdminDetails not found"));
//		UserDetails UserDetails = UserById.get();
//		UserDetails.setVDetails(usdetails.getVDetails());
//		repo.save(UserDetails);
//		
//	}
	@Override
	public void updateVehicle(String ownerId, UserDetails usdetails) {
	    Optional<UserDetails> userById = repo.findById(ownerId);
	    if (userById.isPresent()) {
	        UserDetails userDetails = userById.get();
	        userDetails.setVDetails(usdetails.getVDetails());
	        repo.save(userDetails);
	    } else {
	        throw new RuntimeException("UserDetails not found");
	    }
	}
	@Override
	public UserDetailsResponse getUserDetails(String userId) {
	    Optional<UserDetails> userDetails = repo.findById(userId);
	    if (userDetails.isPresent()) {
	        UserDetails userDetailsObj = userDetails.get();
	        return new UserDetailsResponse(userDetailsObj.getVDetails());
	    } else {
	        throw new RuntimeException("User not found with id: " + userId);
	    }
	}

//	@Override
//	public UserDetails addDetails(UserDetails details) {
//		repo.save(details);
//		return details;
//	}
////
////	@Override
////	public UserDetails getDetails(String userId) {
////		return repo.findById(userID);
////	}
//	@Override
//	public UserDetails getuserDetails(String userId) {
//		return repo.findById(userId).orElseThrow(() -> new RuntimeException("AdminDetails not found"));
//	}
//
//	@Override
//	public Optional<UserDetails> findById(String userId) {
//		return repo.findById(userId);
//	}
//
//	@Override
//	public void DeleteUserData(String userId) {
//		repo.deleteById(userId);
//		
//	}
//
//	@Override
//	public UserDetails getDetails(String userId) {
//		return repo.findById(userId);
//	}

	
	


}

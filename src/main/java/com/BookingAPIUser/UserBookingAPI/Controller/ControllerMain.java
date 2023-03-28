package com.BookingAPIUser.UserBookingAPI.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BookingAPIUser.UserBookingAPI.Entity.Tickets;
import com.BookingAPIUser.UserBookingAPI.Entity.UserDetails;
import com.BookingAPIUser.UserBookingAPI.Entity.UserDetailsResponse;
import com.BookingAPIUser.UserBookingAPI.Entity.vehicleDetails;
import com.BookingAPIUser.UserBookingAPI.Services.Servicess;

@RestController
@RequestMapping("/UserBooking")

public class ControllerMain {
	
	
	@Autowired
	private Servicess service;
	
	@PostMapping("/AddData")
	public UserDetails addDetails(@RequestBody UserDetails details) {
		return this.service.addDetails(details);
	}
	@GetMapping("/GetById/{userId}")
	public UserDetails getDetails(@PathVariable String userId) {
		return this.service.getDetails(userId);
	}
	@DeleteMapping("/DeleteUser/{userId}")
	public ResponseEntity<?>DeleteData(@PathVariable String userId){
		service.DeleteUserData(service.findById(userId).getUserId());
		return new ResponseEntity("DATA DELETED SUCCESSFULLY" , HttpStatus.OK);
	} 
	
	@PutMapping("/Ticket/{userId}")
	public ResponseEntity<?>updateticket(@PathVariable String userId, @RequestBody UserDetails udetails){
		
		service.updateTicket(userId , udetails);
		return new ResponseEntity<>("Ticket Updated " + userId +" ",HttpStatus.OK);
	}
	@PutMapping("/VehicleDetails/{userId}")
	public ResponseEntity<?>updatevehicle(@PathVariable String userId, @RequestBody UserDetails usdetails){
		
		service.updateVehicle(userId , usdetails);
		return new ResponseEntity<>("Vehicle Updated " + userId +" ",HttpStatus.OK);
	}
	@GetMapping("/{userId}/vehicle")
	public vehicleDetails getUserVehicleDetails(@PathVariable String userId) {
	    UserDetails userDetails = service.getDetails(userId);
	    return userDetails.getVDetails();
	}
	@GetMapping("/{userId}/tickets")
	public Tickets getUserticketDetails(@PathVariable String userId) {
	    UserDetails userDetails = service.getDetails(userId);
	    return userDetails.getTickets();
	}


}

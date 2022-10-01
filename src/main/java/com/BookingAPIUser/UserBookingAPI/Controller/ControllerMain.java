package com.BookingAPIUser.UserBookingAPI.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BookingAPIUser.UserBookingAPI.Entity.UserDetails;
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
	@GetMapping("/GetById/{bookingID}")
	public UserDetails getDetails(@PathVariable String bookingID) {
		return this.service.getDetails(Integer.parseInt(bookingID));
	}
	
	@DeleteMapping("/DeletebyID/{bookingID}")
	public ResponseEntity<?>DeleteData(@PathVariable int bookingID){
		service.DeleteUserData(service.findById(bookingID).getBookingID());
		return new ResponseEntity("Data deleted successfully", HttpStatus.OK);
	}

}

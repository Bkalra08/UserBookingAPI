package com.BookingAPIUser.UserBookingAPI.Controller;
import java.util.List;

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

import com.BookingAPIUser.UserBookingAPI.Entity.DefaultVehicle;
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
    public ResponseEntity<?> updateticket(@PathVariable String userId, @RequestBody List<Tickets> tickets) {
        UserDetails userDetails = service.getDetails(userId);
        userDetails.setTickets(tickets);
        service.updateTicket(userId, userDetails);
        return new ResponseEntity<>("Tickets Updated for User: " + userId, HttpStatus.OK);
    }
    
    @PutMapping("/VehicleDetails/{userId}")
    public ResponseEntity<?> updatevehicle(@PathVariable String userId, @RequestBody List<vehicleDetails> vehicleDetailsList) {
        UserDetails userDetails = service.getDetails(userId);
        userDetails.setVDetails(vehicleDetailsList);
        service.updateVehicle(userId, userDetails);
        return new ResponseEntity<>("Vehicle Details Updated for User: " + userId, HttpStatus.OK);
    }
    @PutMapping("/SetDefaultVehicle/{userId}")
    	public ResponseEntity<?> setDefaultVehicle(@PathVariable String userId, @RequestBody DefaultVehicle defaultvehicle){
    		UserDetails userDetails = service.getDetails(userId);
    		userDetails.setDefaultvehicle(defaultvehicle);
    		service.setDefaultVehicle(userId,userDetails);
    		return new ResponseEntity<>("Default vehicle for user : " + userId, HttpStatus.OK);
    	}
    
    
	@GetMapping("/{userId}/vehicle")
    public List<vehicleDetails> getUserVehicleDetails(@PathVariable String userId) {
        UserDetails userDetails = service.getDetails(userId);
        return userDetails.getVDetails();
    }
    
    @GetMapping("/{userId}/tickets")
    public List<Tickets> getUserticketDetails(@PathVariable String userId) {
        UserDetails userDetails = service.getDetails(userId);
        return userDetails.getTickets();
    }
    @GetMapping("/GetTicket/{userId}/{ticketId}")
    public ResponseEntity<?> getSpecificTicket(@PathVariable String userId, @PathVariable String ticketId) {
        Tickets ticket = service.getSpecificTicket(userId, ticketId);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }
    @GetMapping("/{userId}/latest-ticket")
    public ResponseEntity<Tickets> getLatestTicket(@PathVariable String userId) {
        Tickets latestTicket = service.getLatestTicket(userId);
        return new ResponseEntity<>(latestTicket, HttpStatus.OK);
    }




}

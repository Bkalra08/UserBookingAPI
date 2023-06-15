package com.BookingAPIUser.UserBookingAPI.Controller;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
//	@PatchMapping("/{userId}/{ticketId}")
//    public void updateTicketFields(@PathVariable String userId, @PathVariable String ticketId, @RequestBody Tickets updatedFields) {
//        Tickets ticket = service.getSpecificTicket(userId, ticketId);
//
//        // Copy non-null properties from updatedFields to ticket
//        BeanUtils.copyProperties(updatedFields, ticket, getNullPropertyNames(updatedFields));
//
//        // Call the updateTicket method to save the updated ticket
//        service.updateTicket(userId, ticketId, ticket);
//    }
//
//    // Helper method to get null property names from an object
//    private String[] getNullPropertyNames(Object source) {
//        final BeanWrapper src = new BeanWrapperImpl(source);
//        PropertyDescriptor[] descriptors = src.getPropertyDescriptors();
//        List<String> nullProperties = new ArrayList<>();
//        for (PropertyDescriptor descriptor : descriptors) {
//            String propertyName = descriptor.getName();
//            if (src.getPropertyValue(propertyName) == null) {
//                nullProperties.add(propertyName);
//            }
//        }
//        return nullProperties.toArray(new String[0]);
//    }
	@GetMapping("/GetById/{userId}")
	public UserDetails getDetails(@PathVariable String userId) {
		return this.service.getDetails(userId);
	}
	@GetMapping("/{userId}/isTowed")
	public ResponseEntity<Boolean> getIsTowed(@PathVariable String userId) {
	    UserDetails userDetails = service.getDetails(userId);
	    Boolean isTowed = userDetails.getIsTowed();
	    return new ResponseEntity<>(isTowed, HttpStatus.OK);
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
    
    @PatchMapping("/{userId}/isTowed")
    public ResponseEntity<?> updateIsTowed(@PathVariable String userId, @RequestBody Boolean isTowed) {
        UserDetails userDetails = service.getDetails(userId);
        userDetails.setIsTowed(isTowed);
        service.updateIsTowed(userId, userDetails);
        return new ResponseEntity<>("isTowed field updated for user: " + userId, HttpStatus.OK);
    }
    
    @PatchMapping("/{userId}/message")
    public ResponseEntity<?> updateMessage(@PathVariable String userId, @RequestBody Integer message) {
        UserDetails userDetails = service.getDetails(userId);
        userDetails.setMessage(message);
        service.updateMessage(userId, userDetails);
        return new ResponseEntity<>("message field updated for user: " + userId, HttpStatus.OK);
    }
    @PatchMapping("/UpdateTicket/{userId}/{ticketId}")
    public ResponseEntity<?> updateTicket(@PathVariable String userId, @PathVariable String ticketId, @RequestBody Tickets updatedTicket) {
        service.updateTicket(userId, ticketId, updatedTicket);
        return new ResponseEntity<>("Ticket updated successfully", HttpStatus.OK);
    }
//

    
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
    @GetMapping("/{userId}/message")
    public ResponseEntity<Integer> getMessage(@PathVariable String userId) {
        UserDetails userDetails = service.getDetails(userId);
        Integer message = userDetails.getMessage();
        return new ResponseEntity<>(message, HttpStatus.OK);
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

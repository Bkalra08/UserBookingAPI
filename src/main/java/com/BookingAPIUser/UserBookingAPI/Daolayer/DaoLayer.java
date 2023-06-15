package com.BookingAPIUser.UserBookingAPI.Daolayer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.BookingAPIUser.UserBookingAPI.Entity.Tickets;
import com.BookingAPIUser.UserBookingAPI.Entity.UserDetails;

@Repository
public interface DaoLayer extends MongoRepository<UserDetails, String> {
	
	Optional<UserDetails> findById(String userId);

	Optional<UserDetails> findByTicketsTicketId(String ticketId);

	void save(List<Tickets> ticketList);
	
	

}

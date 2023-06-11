package com.BookingAPIUser.UserBookingAPI.Services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BookingAPIUser.UserBookingAPI.Daolayer.DaoLayer;
import com.BookingAPIUser.UserBookingAPI.Entity.Tickets;
import com.BookingAPIUser.UserBookingAPI.Entity.UserDetails;
import com.BookingAPIUser.UserBookingAPI.Entity.UserDetailsResponse;
import com.BookingAPIUser.UserBookingAPI.Entity.vehicleDetails;

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
        return repo.findById(userId).orElseThrow(() -> new RuntimeException("UserDetails not found"));
    }

    @Override
    public UserDetails findById(String userId) {
        return repo.findById(userId).orElseThrow(() -> new RuntimeException("UserDetails not found"));
    }

    @Override
    public void DeleteUserData(String userId) {
        repo.deleteById(userId);
    }
    @Override
    public void updateTicket(String ownerId, UserDetails udetails) {
        Optional<UserDetails> userById = repo.findById(ownerId);
        if (userById.isPresent()) {
            UserDetails userDetails = userById.get();
            List<Tickets> ticketsList = userDetails.getTickets();
            ticketsList.add(udetails.getTickets().get(0)); // Assuming only one ticket is added
            userDetails.setTickets(ticketsList);
            repo.save(userDetails);
        } else {
            throw new RuntimeException("UserDetails not found");
        }
    }
    @Override
    public void updateVehicle(String ownerId, UserDetails usdetails) {
        Optional<UserDetails> userById = repo.findById(ownerId);
        if (userById.isPresent()) {
            UserDetails userDetails = userById.get();
            List<vehicleDetails> vehicleList = userDetails.getVDetails();
            vehicleList.add(usdetails.getVDetails().get(0)); // Assuming only one vehicle is added
            userDetails.setVDetails(vehicleList);
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
}

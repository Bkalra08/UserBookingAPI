package com.BookingAPIUser.UserBookingAPI.Services;
import java.util.Comparator;
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
    public Boolean updateIsTowed(String userId, UserDetails userDetails) {
        Optional<UserDetails> userById = repo.findById(userId);
        if (userById.isPresent()) {
            UserDetails existingUser = userById.get();
            existingUser.setIsTowed(userDetails.getIsTowed());
            repo.save(existingUser);
            return true;
        } else {
            throw new RuntimeException("UserDetails not found with ID: " + userId);
        }
    }


    @Override
    public UserDetails findById(String userId) {
        return repo.findById(userId).orElseThrow(() -> new RuntimeException("UserDetails not found"));
    }
//    @Override
//    public Tickets getTicketById(String ticketId) {
//        Optional<UserDetails> userDetails = repo.findByTicketsTicketId(ticketId);
//        if (userDetails.isPresent()) {
//            UserDetails userDetailsObj = userDetails.get();
//            List<Tickets> ticketList = userDetailsObj.getTickets();
//            Optional<Tickets> ticket = ticketList.stream()
//                    .filter(t -> t.getTicketId().equals(ticketId))
//                    .findFirst();
//            return ticket.orElseThrow(() -> new RuntimeException("Ticket not found with ID: " + ticketId));
//        } else {
//            throw new RuntimeException("User not found with ticket ID: " + ticketId);
//        }
//    }


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
    public Tickets getSpecificTicket(String userId, String ticketId) {
        Optional<UserDetails> userById = repo.findById(userId);
        if (userById.isPresent()) {
            UserDetails userDetails = userById.get();
            List<Tickets> ticketList = userDetails.getTickets();
            Optional<Tickets> specificTicket = ticketList.stream()
                    .filter(ticket -> ticket.getTicketId().equals(ticketId))
                    .findFirst();
            return specificTicket.orElseThrow(() -> new RuntimeException("Ticket not found with ID: " + ticketId));
        } else {
            throw new RuntimeException("UserDetails not found with ID: " + userId);
        }
    }

    @Override
    public Tickets getLatestTicket(String userId) {
        Optional<UserDetails> userById = repo.findById(userId);
        if (userById.isPresent()) {
            UserDetails userDetails = userById.get();
            List<Tickets> ticketList = userDetails.getTickets();
            int lastIndex = ticketList.size() - 1;
            if (!ticketList.isEmpty()) {
                return ticketList.get(lastIndex); // Get the first (latest) ticket
            } else {
                throw new RuntimeException("No tickets found for user with ID: " + userId);
            }
        } else {
            throw new RuntimeException("UserDetails not found with ID: " + userId);
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
    @Override
    public void updateTicket(String userId, String ticketId, Tickets updatedTicket) {
        Optional<UserDetails> userById = repo.findById(userId);
        if (userById.isPresent()) {
            UserDetails userDetails = userById.get();
            List<Tickets> tickets = userDetails.getTickets();
            Optional<Tickets> ticketToUpdate = tickets.stream()
                    .filter(ticket -> ticket.getTicketId().equals(ticketId))
                    .findFirst();
            if (ticketToUpdate.isPresent()) {
                Tickets existingTicket = ticketToUpdate.get();
                // Update the necessary fields of the ticket
                existingTicket.setActiveStatus(updatedTicket.isActiveStatus());
                existingTicket.setTicketIdCheckout(updatedTicket.getTicketIdCheckout());
                existingTicket.setTicketIdCheckin(updatedTicket.getTicketIdCheckin());
                existingTicket.setBookingtime(updatedTicket.getBookingtime());
                existingTicket.setCheckinTime(updatedTicket.getCheckinTime());
                existingTicket.setCheckoutTime(updatedTicket.getCheckoutTime());
                existingTicket.setDuration(updatedTicket.getDuration());
                
                repo.save(userDetails); // Save the updated UserDetails object
            } else {
                throw new RuntimeException("Ticket not found with ID: " + ticketId);
            }
        } else {
            throw new RuntimeException("UserDetails not found with ID: " + userId);
        }
    }
    
    @Override
    public void patchTicket(String userId, Tickets updatedTicket) {
        Optional<UserDetails> userDetails = repo.findById(userId);
        if (userDetails.isPresent()) {
            UserDetails userDetailsObj = userDetails.get();
            List<Tickets> ticketList = userDetailsObj.getTickets();
            for (Tickets ticket : ticketList) {
                if (ticket.getTicketId().equals(updatedTicket.getTicketId())) {
                    if (updatedTicket.getTicketIdCheckout() != null ) {
                        ticket.setTicketIdCheckout(updatedTicket.getTicketIdCheckout());
                    }
                    if (updatedTicket.getTicketIdCheckin() != null) {
                        ticket.setTicketIdCheckin(updatedTicket.getTicketIdCheckin());
                    }
                    if ( updatedTicket.getCheckoutTime() != null) {
                        ticket.setCheckoutTime(updatedTicket.getCheckoutTime());
                    }
                    if (updatedTicket.getCheckinTime() != null) {
                    	ticket.setCheckinTime(updatedTicket.getCheckinTime());
                    }
//      
                    

                    repo.save(userDetailsObj); // Save the updated UserDetails object
                    return;
                }
            }
            throw new RuntimeException("Ticket not found with ID: " + updatedTicket.getTicketId());
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

//    
//    @Override
//    public void patchTicket(String userId, String ticketId, Tickets updatedFields) {
//        Optional<UserDetails> userById = repo.findById(userId);
//        if (userById.isPresent()) {
//            UserDetails userDetails = userById.get();
//            List<Tickets> tickets = userDetails.getTickets();
//            Optional<Tickets> ticketToUpdate = tickets.stream()
//                    .filter(ticket -> ticket.getTicketId().equals(ticketId))
//                    .findFirst();
//            if (ticketToUpdate.isPresent()) {
//                Tickets existingTicket = ticketToUpdate.get();
//
//                // Update only the specified fields if they are not null
//                if (updatedFields.isActiveStatus() != null) {
//                    existingTicket.setActiveStatus(updatedFields.isActiveStatus());
//                }
//                if (updatedFields.getTicketIdCheckout() != null) {
//                    existingTicket.setTicketIdCheckout(updatedFields.getTicketIdCheckout());
//                }
//                if (updatedFields.getTicketIdCheckin() != null) {
//                    existingTicket.setTicketIdCheckin(updatedFields.getTicketIdCheckin());
//                }
//                // Add more fields to update if needed
//
//                repo.save(userDetails); // Save the updated UserDetails object
//            } else {
//                throw new RuntimeException("Ticket not found with ID: " + ticketId);
//            }
//        } else {
//            throw new RuntimeException("UserDetails not found with ID: " + userId);
//        }
//    }




    @Override
    public void setDefaultVehicle(String ownerId, UserDetails userDetails) {
        Optional<UserDetails> userById = repo.findById(ownerId);
        if (userById.isPresent()) {
            UserDetails user = userById.get();
            user.setDefaultvehicle(userDetails.getDefaultvehicle());
            repo.save(user);
        } else {
            throw new RuntimeException("UserDetails not found");
        }
    }

	@Override
	public Boolean updateMessage(String userId, UserDetails userDetails) {
		Optional<UserDetails> userById = repo.findById(userId);
	    if (userById.isPresent()) {
	        UserDetails existingUser = userById.get();
	        existingUser.setMessage(userDetails.getMessage());
	        repo.save(existingUser);
	        return true;
	    } else {
	        throw new RuntimeException("UserDetails not found with ID: " + userId);
	    }
	}

}

package com.BookingAPIUser.UserBookingAPI.Entity;

import java.util.List;

public class UserDetailsResponse {
	private List<vehicleDetails> vDetails;

    public UserDetailsResponse(List<vehicleDetails> vDetails) {
        this.vDetails = vDetails;
    }

    public List<vehicleDetails> getvDetails() {
        return vDetails;
    }

    public void setvDetails(List<vehicleDetails> vDetails) {
        this.vDetails = vDetails;
    }
}

package com.BookingAPIUser.UserBookingAPI.Entity;
public class UserDetailsResponse {
	private vehicleDetails vDetails;

	public UserDetailsResponse(vehicleDetails vDetails) {
		this.vDetails = vDetails;
	}

	public vehicleDetails getvDetails() {
		return vDetails;
	}

	public void setvDetails(vehicleDetails vDetails) {
		this.vDetails = vDetails;
	}
}

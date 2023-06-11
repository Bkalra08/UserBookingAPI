package com.BookingAPIUser.UserBookingAPI.Entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class vehicleDetails {
	@Id
	private String vehicleNo;
	private String VehicleId;
	private String vehicleType;
	private String VehicleName;
}

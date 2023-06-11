package com.BookingAPIUser.UserBookingAPI.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DefaultVehicle {

	private String VehicleId;
	private boolean isDefault;
}

package com.booking.carbooking;

import com.booking.car.CarDAO;

import java.util.ArrayList;
import java.util.List;

public class CarBookingDAO {
    private static final List<CarBooking> carBookings;

    static {
        carBookings = new ArrayList<CarBooking>();
    }
    public List<CarBooking> getCarBookings(){
        return carBookings;
    }

    public void book(CarBooking carBooking){
        carBookings.add(carBooking);
    }
}

package com.booking.car;

import java.math.BigDecimal;

public class CarDAO {
    private static final Car[] CARS = {
            new Car("245",new BigDecimal("90.00"),Brand.AUDI,false),
            new Car("155",new BigDecimal("100.00"),Brand.BMW,false),
            new Car("278",new BigDecimal("95.00"),Brand.MERCEDES,false),
            new Car("001",new BigDecimal("88.00"),Brand.TESLA,true),
    };
    public Car[] getCars(){
        return CARS;
    }

}

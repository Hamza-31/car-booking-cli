package com.booking.car;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class CarDAO {
    private static final List<Car> CARS = Arrays.asList(

            new Car("245",new BigDecimal("90.00"),Brand.AUDI,false),
            new Car("155",new BigDecimal("100.00"),Brand.BMW,false),
            new Car("278",new BigDecimal("95.00"),Brand.MERCEDES,false),
            new Car("001",new BigDecimal("88.00"),Brand.TESLA,true)
    );
    public List<Car> getCars(){
        return CARS;
    }

}

package net.likelion.bebc25.spring.componentscan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Driver {
    @Autowired
    private Car car;

    public void driverCar(int maxSpd) {
        car.startEngine();
        car.drive();
        car.stopEngine();
    }
}

package net.likelion.bebc25.spring;

public class Driver {
    private Car car;

    public Driver(Car car) {
        this.car = car;
    }

    public void driverCar() {
        car.startEngine();
        car.drive();
        car.stopEngine();
    }
}

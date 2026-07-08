package net.likelion.bebc25.oop.after;

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

package net.likelion.bebc25.spring.aop.springaop;

public class Driver {
    private Car car;

    public Driver(Car car) {
        System.out.println("called Constructor Injection: " + car);
        this.car = car;
    }

    public void driverCar(int maxSpd) {
        car.startEngine();
        car.drive();
        car.stopEngine();
    }
}

package net.likelion.bebc25.spring;

public class HybridCar implements Car {
    public void startEngine() {
        System.out.println("(Hybrid) Engine Start");
    }

    public void drive() {
        System.out.println("(Hybrid) now Driving");
    }

    public void stopEngine() {
        System.out.println("(Hybrid) Engine stopped");
    }
}
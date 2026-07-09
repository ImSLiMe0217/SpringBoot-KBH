package net.likelion.bebc25.spring.aop.dynamicproxy;

public class GasolineCar implements Car {
    public void startEngine() {System.out.println("(Gasoline) Engine Start");}

    public void drive() {System.out.println("(Gasoline) now Driving");}

    public void stopEngine() {System.out.println("(Gasoline) Engine stopped");}
}
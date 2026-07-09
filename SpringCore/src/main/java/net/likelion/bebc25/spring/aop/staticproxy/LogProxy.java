package net.likelion.bebc25.spring.aop.staticproxy;

public class LogProxy implements Car{
    private final Car target;

    public LogProxy(Car target) {
        this.target = target;
    }

    @Override
    public void startEngine() {
        System.out.println("[Before Start] Engine Check");
        target.startEngine();
    }

    @Override
    public void drive() {
        System.out.println("[Before Departure] SeatBelt On");
        target.drive();
        System.out.println("[After Arrival] SeatBelt Off");
    }

    @Override
    public void stopEngine() {
        target.stopEngine();
        System.out.println("[After stop] Gef off");
    }
}

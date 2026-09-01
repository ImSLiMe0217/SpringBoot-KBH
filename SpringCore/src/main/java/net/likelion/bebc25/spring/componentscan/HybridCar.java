package net.likelion.bebc25.spring.componentscan;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary    // 동일 타입의 여러 빈 중에 메인으로 지정
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
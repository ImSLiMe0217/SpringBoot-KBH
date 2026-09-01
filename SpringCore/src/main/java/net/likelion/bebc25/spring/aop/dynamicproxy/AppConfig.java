package net.likelion.bebc25.spring.aop.dynamicproxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

// 스프링 컨테이너에 알려주는 앱 설정 클래스
@Configuration
public class AppConfig {
    @Bean   // 스프링 Bean으로 등록 (메서드명인 car가 Bean의 이름이 됨)
    public Car car() {
//        Car target = new GasolineCar();
        Car target = new HybridCar();
        Car proxyCar = (Car) Proxy.newProxyInstance(
                Car.class.getClassLoader(),
                new Class[] {Car.class},
                new TimeCheckInvocationHandler(target)
        );
        return proxyCar;
    }

    @Bean
    public Driver driver(Car car) {
        return new Driver(car);
    }
}

// 스프링 컨테이너가 하는 일
// AppConfig config = new AppConfig();
// Car car = config.car();
// Driver driver = config.driver(car);

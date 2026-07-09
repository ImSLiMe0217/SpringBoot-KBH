package net.likelion.bebc25.spring.di.setter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 스프링 컨테이너에 알려주는 앱 설정 클래스
@Configuration
public class AppConfig {
    @Bean   // 스프링 Bean으로 등록 (메서드명인 car가 Bean의 이름이 됨)
    public Car car() {
//        return new GasolineCar();
        return new HybridCar();
    }

    @Bean
    public Driver driver(Car car) {
        Driver driver = new Driver();
        driver.setCar(car);
        return driver;
    }
}

// 스프링 컨테이너가 하는 일
// AppConfig config = new AppConfig();
// Car car = config.car();
// Driver driver = config.driver(car);

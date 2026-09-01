package net.likelion.bebc25.intellij;

import aQute.bnd.annotation.metatype.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.module.Configuration;

@SpringBootApplication
public class SpringBootIntellijApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootIntellijApplication.class, args);
        Driver driver = context.getBean(Driver.class);
        driver.driverCar(80);
        context.close();
    }

}

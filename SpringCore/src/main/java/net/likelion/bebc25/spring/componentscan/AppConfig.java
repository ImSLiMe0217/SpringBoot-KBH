package net.likelion.bebc25.spring.componentscan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// 스프링 컨테이너에 알려주는 앱 설정 클래스
@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class AppConfig {
  
}

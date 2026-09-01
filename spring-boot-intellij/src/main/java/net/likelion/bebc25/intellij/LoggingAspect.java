package net.likelion.bebc25.intellij;

import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect // 횡단 관심사
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* net.likelion.bebc25.intellij.Driver.*(..))")
    private void driverClass() {}

    @Before("driverClass()")
    public void logBefore(JoinPoint joinPoint) {   // 메서드 수행 전 로그 출력
        log.info("[AOP 로그 Before] 메서드 실행 전 처리해야할 코드");
    }

    @After("driverClass()")
    public void logAfter(JoinPoint joinPoint) {    // 메서드 수행 후 로그 출력
        log.info("[AOP 로그 After ] 메서드 실행 후 처리해야할 코드");
    }

    @Around("driverClass()")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {   // 메서드 수행 전/후 로그 출력
        log.debug("[AOP 로그 Around] 메서드 실행 전 처리해야할 코드");
        joinPoint.proceed(); // 대상메서드를 호출한다
        log.debug("[AOP 로그 Around] 메서드 실행 후 처리해야할 코드");
    }
}

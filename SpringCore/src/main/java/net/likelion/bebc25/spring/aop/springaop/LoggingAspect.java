package net.likelion.bebc25.spring.aop.springaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect // 횡단 관심사
public class LoggingAspect {
    @Pointcut("execution(* net.likelion.bebc25.spring.aop.springaop.Driver.*(..))")
    private void springaopPackageMethod() {}

    @Before("springaopPackageMethod()")
    public void logBefore(JoinPoint joinPoint) {   // 메서드 수행 전 로그 출력
        System.out.println("[AOP 로그 Before] 메서드 실행 전 처리해야할 코드");
        Object[] args = joinPoint.getArgs();
        System.out.println(Arrays.toString(args));
    }

    @After("springaopPackageMethod()")
    public void logAfter(JoinPoint joinPoint) {    // 메서드 수행 후 로그 출력
        System.out.println("[AOP 로그 After ] 메서드 실행 후 처리해야할 코드");
    }

    @Around("springaopPackageMethod()")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {   // 메서드 수행 전/후 로그 출력
        System.out.println("[AOP 로그 Around] 메서드 실행 전 처리해야할 코드");
        joinPoint.proceed(); // 대상메서드를 호출한다
        System.out.println("[AOP 로그 Around] 메서드 실행 후 처리해야할 코드");
    }
}

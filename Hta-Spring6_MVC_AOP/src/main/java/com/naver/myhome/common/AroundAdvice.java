package com.naver.myhome.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/*
Advice : 횡단 관심에 해당하는 공통 기능을 의미하며 독립된 클래스의 메서드로 작성됩니다.
Advice 클래슨느 @Service annotation을 사용합니다.
@Asepct가 설정된 클래스에는 Pointcut과 Advice를 결합하는 설정이 있어야합니다.
* */
//@Aspect
//@Service
public class AroundAdvice {

    private static final Logger logger = LoggerFactory.getLogger(AroundAdvice.class);

    @Around("execution(* com.naver.myhome..*Impl.get*(..))")
    public Object aroundLog(ProceedingJoinPoint proceeding) throws Throwable {
        logger.info("======================================================");
        logger.info("[Around Advice의 before]: 비즈니스 메서드 수행 전입니다.");
        logger.info("======================================================");

        //이 코드의 이전과 이후에 공통 기능을 위한 코드를 위치시키면 됩니다.
        Object result = proceeding.proceed();

        logger.info("======================================================");
        logger.info("[Around Advice의 after]: 비즈니스 메서드 수행 후입니다.");
        logger.info("======================================================");
        return result;
    }
}

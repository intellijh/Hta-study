package com.naver.myhome.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Arrays;

//@Aspect
//@Service
public class AroundAdvice2 {

    private static final Logger logger = LoggerFactory.getLogger(AroundAdvice2.class);

    @Around("execution(* com.naver.myhome..*Impl.get*(..))")
    public Object aroundLog(ProceedingJoinPoint proceeding) throws Throwable {
        logger.info("======================================================");
        logger.info("[Around Advice의 before]: 비즈니스 메서드 수행 전입니다.");
        StopWatch sw = new StopWatch();
        sw.start();
        logger.info("======================================================");

        //이 코드의 이전과 이후에 공통 기능을 위한 코드를 위치시키면 됩니다.
        //대상 객체의 메서드 public Board getDetail(int num)를 호출합니다.
        Object result = proceeding.proceed();
        sw.stop();

        logger.info("======================================================");
        logger.info("[Around Advice의 after]: 비즈니스 메서드 수행 후입니다.");

        /*
        1. proceeding.getTarget().getClass().getSimpleName() : Target클래스의 이름을 가져옵니다.
        2. Object[] getArgs() : 클라이언트가 메서드를 호출할 때 넘겨준 인자 목록을 Object 배열로 리턴합니다.

        3. org.aspectj.lang.Signature 인터페이스는 호출되는 메서드와 관련된 정보를 제공합니다.
        4. sig.getName() : 메서드의 이름을 구합니다.
        * */
        Signature sig = proceeding.getSignature();
        logger.info("[Around Advice의 after] "
                + proceeding.getTarget().getClass().getSimpleName()
                + "." + sig.getName()
                + "(" + Arrays.toString(proceeding.getArgs()) + ")");

        logger.info("[Around Advice의 after] "
                + sig.getName() + "() 메소드 수행 시간 : "
                + sw.getTotalTimeMillis() + "(ms)초");

        logger.info("[Around Advice의 after] proceeding.proceed() 실행 후 반환값=" + result);
        logger.info("======================================================");

        return result;
    }
}

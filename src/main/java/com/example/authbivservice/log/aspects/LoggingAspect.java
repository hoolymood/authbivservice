package com.example.authbivservice.log.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("@within(com.example.authbivservice.log.Loggable) || @annotation(com.example.authbivservice.log.Loggable)")
    public Object loggingAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        log.info("{} is running. Args: {}", method, args);
        try {
            Object result = joinPoint.proceed();
            log.info("{} is finished. Args [{}]", method, result);
            return result;
        } catch (Throwable e) {
            log.error("{} is failed. Args: [{}] Message:  {}", method, args, e.getMessage());
            throw e.getClass().getConstructor(String.class).newInstance(e.getMessage());
        }
    }
}

package com.goldys.ticketservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/* Annotate this class with @Aspect and @Component */
@Aspect
@Component
public class LoggingAspect {

    /*
     * Write loggers for each of the methods of EnquiryController, any particular method
     * will have all the four aspectJ annotation
     * (@Before, @After, @AfterReturning, @AfterThrowing).
     */
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private static final String METHODNAME = "Method Name : {}";
    private static final String METHODARGS = "Method Args : {}";

    @Pointcut("execution (* com.goldys.ticketservice.controller.TicketController.*(..))")
    public void allControllerMethods() {
        /*  Pointcut method	 */
    }

    @Before("allControllerMethods()")
    public void beforeAdvice(JoinPoint joinPoint) {
        logger.info("************ @Before ************");
        logger.debug(METHODNAME, joinPoint.getSignature().getName());
        logger.debug(METHODARGS, Arrays.toString(joinPoint.getArgs()));
        logger.info("*********************************");
    }

    @After("allControllerMethods()")
    public void afterAdvice(JoinPoint joinPoint) {
        logger.info("************ @After ************");
        logger.debug(METHODNAME, joinPoint.getSignature().getName());
        logger.debug(METHODARGS, Arrays.toString(joinPoint.getArgs()));
        logger.info("*********************************");
    }

    @AfterReturning(value = "allControllerMethods()", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        logger.info("************ @AfterReturning ************");
        logger.debug(METHODNAME, joinPoint.getSignature().getName());
        logger.debug(METHODARGS, Arrays.toString(joinPoint.getArgs()));
        logger.debug("Return Value: {}", result);
        logger.info("*****************************************");
    }

    @AfterThrowing(value = "allControllerMethods()", throwing = "error")
    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable error) {
        logger.info("************ @AfterThrowing ************");
        logger.debug(METHODNAME, joinPoint.getSignature().getName());
        logger.debug(METHODARGS, Arrays.toString(joinPoint.getArgs()));
        logger.debug("Exception : {}", error);
        logger.info("****************************************");
    }
}
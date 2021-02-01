package com.goldys.enquiryservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* Annotate this class with @Aspect and @Component */
public class LoggingAspect {

    /*
     * Write loggers for each of the methods of EnquiryController, any particular method
     * will have all the four aspectJ annotation
     * (@Before, @After, @AfterReturning, @AfterThrowing).
     */
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	@Before("execution(* com.goldys.enquiryservice.controller.EnquiryController.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		logger.info("logBefore() is running!");
		logger.info("hijacked : " + joinPoint.getSignature().getName());
		logger.info("******");
	}

	@After("execution(* com.goldys.enquiryservice.controller.EnquiryController.*(..))")
	public void logAfter(JoinPoint joinPoint) {
		logger.info("logAfter() is running!");
		logger.info("hijacked : " + joinPoint.getSignature().getName());
		logger.info("******");
	}
	
	@AfterReturning(
			pointcut = "execution(* com.goldys.enquiryservice.controller.EnquiryController.*(..))",
			returning= "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		logger.info("logAfterReturning() is running!");
		logger.info("hijacked : " + joinPoint.getSignature().getName());
		logger.info("Method returned value is : " + result);
		logger.info("******");
	}
	
	@AfterThrowing(
			pointcut = "execution(* com.goldys.enquiryservice.controller.EnquiryController.*(..))",
			throwing= "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		logger.info("logAfterThrowing() is running!");
		logger.info("hijacked : " + joinPoint.getSignature().getName());
		logger.info("Exception : " + error);
		logger.info("******");
	}
}

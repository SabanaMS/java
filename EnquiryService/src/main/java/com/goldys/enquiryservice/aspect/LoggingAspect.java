package com.goldys.enquiryservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;

/* Annotate this class with @Aspect and @Component */
public class LoggingAspect {

    /*
     * Write loggers for each of the methods of EnquiryController, any particular method
     * will have all the four aspectJ annotation
     * (@Before, @After, @AfterReturning, @AfterThrowing).
     */
	@Before("execution(* com.goldys.enquiryservice.controller.EnquiryController.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("logBefore() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("******");
	}

	@After("execution(* com.goldys.enquiryservice.controller.EnquiryController.*(..))")
	public void logAfter(JoinPoint joinPoint) {
		System.out.println("logAfter() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("******");
	}
	
	@AfterReturning(
			pointcut = "execution(* com.goldys.enquiryservice.controller.EnquiryController.*(..))",
			returning= "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		System.out.println("logAfterReturning() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("Method returned value is : " + result);
		System.out.println("******");
	}
	
	@AfterThrowing(
			pointcut = "execution(* com.goldys.enquiryservice.controller.EnquiryController.*(..))",
			throwing= "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		System.out.println("logAfterThrowing() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("Exception : " + error);
		System.out.println("******");
	}
}

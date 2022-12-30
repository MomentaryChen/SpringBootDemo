package com.momentary.demo.aop;

import java.util.Arrays;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Aspect
@Order(1)
public class LogAspect {
	private static final Logger logger = LogManager.getLogger(LogAspect.class);
	
	@Pointcut("execution(* com.momentary.demo.service.product..*(..))")
	public void pointcut() {};
	
	@Before("pointcut()")
	public void before(JoinPoint joinpoint) {
		logger.info("==== LogAspect Before starts =====");
		
		logger.info(getMethodName(joinpoint));
		Arrays.stream(joinpoint.getArgs()).forEach(System.out::println);
		logger.info("==== LogAspect Before ends =====");
	}
	
	@AfterReturning(pointcut = "pointcut()", returning = "result")
	public void after(JoinPoint joinPoint, Object result) {
		logger.info("==== @AfterReturning After starts =====");
		if(!Objects.isNull(result)) {
			logger.info(result);
		}
		logger.info("==== @AfterReturning After ends =====");
	}
	
	@After("pointcut()")
	public void after() {
		logger.info("==== LogAspect After done =====");
	}
	
	@Around("pointcut()")
	public Object arount(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("==== arount After starts =====");
		long startTime = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long endTime = System.currentTimeMillis();
	
		logger.info(String.format("==== Time spent: %dms =====", endTime - startTime));
		logger.info("==== arount After ends =====");
		
		return result;
	}
	
	@AfterThrowing(pointcut = "pointcut()", throwing = "throwable")
	public void AfterThrowing(JoinPoint joinPoint, Throwable throwable) {
		logger.info("==== AfterThrowing starts =====");
		
		logger.info(getMethodName(joinPoint));
		Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
		
		logger.info(throwable.getMessage());
		logger.info("==== AfterThrowing ends =====");
	}
	
	
	
	private String getMethodName(JoinPoint joinpoint) {
		MethodSignature signature = (MethodSignature) joinpoint.getSignature();
		
		return signature.getName();
	}
}

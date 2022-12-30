package com.momentary.demo.aop;

import java.lang.reflect.Field;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.momentary.demo.annotation.SendMessage;
import com.momentary.demo.service.auth.UserIdentityService;
import com.momentary.demo.service.product.impl.ProductServiceImpl;

@Component
@Aspect
@Order(0)
public class SendMessageAspect {
	
	private static final Logger logger = LogManager.getLogger(SendMessageAspect.class);
	
	@Autowired
	private UserIdentityService userIdentityService;
	
	@Pointcut("@annotation(com.momentary.demo.annotation.SendMessage)")
	public void pointcut() {};
	
	@AfterReturning(pointcut = "pointcut()", returning = "result")
	public void sendMessage(JoinPoint joinPoint, Object result) {
		if(userIdentityService.isAnonymous()) {
			return;
		}
		SendMessage annotation = getAnnotation(joinPoint);
		String bodyString = composeBody(annotation, joinPoint, result);
		
		
		logger.info("Notify " + userIdentityService.getUsername());
		logger.info(bodyString);
		
	}
	
	private SendMessage getAnnotation(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		
		return signature.getMethod().getAnnotation(SendMessage.class);
	}
	
	private String composeBody(SendMessage annotation, JoinPoint joinPoint, Object entity) {
		String entityType = annotation.entityType().toString();
		String actionType = annotation.actionType().toString();
		
		int idParamIndex = annotation.idParamIndex();
//		String entityIdString = idParamIndex == -1 ? getEntityId(entity): (String) joinPoint.getArgs()[idParamIndex];
		
		return entityType + " " + actionType +  " " + entity;
	}
	
	private String getEntityId(Object obj) {
		
		try {
			System.out.println(obj.toString());
			Field filed = obj.getClass().getDeclaredField("id");
			filed.setAccessible(true);
			return (String) filed.get(obj);
		} catch(NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
					}
		return "";
	}
	
}

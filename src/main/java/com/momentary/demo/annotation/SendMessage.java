package com.momentary.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.momentary.demo.constant.ActionType;
import com.momentary.demo.constant.EntityType;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SendMessage {

	EntityType entityType();
	ActionType actionType();
	
	int idParamIndex() default -1;
}

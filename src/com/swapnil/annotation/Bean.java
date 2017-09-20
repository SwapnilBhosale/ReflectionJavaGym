package com.swapnil.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.swapnil.constant.DIType;

/**
 * @author Swapnil Bhosale
 * This is Bean annotation implementation
 * uses params as name of the bean and Dependency Injection type
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Bean {

	String name();
	DIType type() default DIType.DIByConstructore;
}

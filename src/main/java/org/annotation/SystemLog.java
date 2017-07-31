package org.annotation;

import java.lang.annotation.*;
/**
 * 日志注解
 * @author donald
 * 2017年7月29日
 * 下午3:59:28
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
	String description() default "";
}

package org.annotation;

import java.lang.annotation.*;
/**
 * 数据源注解
 * @author donald
 * 2017年7月29日
 * 下午3:59:09
 */
@Target({ElementType.TYPE,  ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
	String value() default "";
}

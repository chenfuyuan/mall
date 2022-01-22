package com.learn.project.common.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DateFormat {

    /**
     * 时间格式化
     */
    String pattern() default "yyyy-MM-dd HH:mm:ss";

}

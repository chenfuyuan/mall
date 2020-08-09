package com.learn.common.validation.annotaion;

import com.learn.common.validation.validator.ContainValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 校验value是否包含在list中
 * @author: Vito.Chen
 * @date: 2020-8-8 15:46
 */
@Documented
@Constraint(validatedBy = {ContainValidator.class})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface Contain {


    /**
     * @return 校验信息
     */
    String message() default "{com.learn.common.validation.annotaion.Contain.message}";

    /**
     * @return 分组
     */
    Class<?>[] groups() default {};

    /**
     * @return payload
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * @return 匹配的值
     */
    String[] vals() default {};

}

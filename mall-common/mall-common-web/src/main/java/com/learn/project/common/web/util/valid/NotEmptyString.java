package com.learn.project.common.web.util.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 不能是空字符串
 *
 * @author chenfuyuan
 * @date 2022/1/8 20:23
 */
@Documented
@Constraint(validatedBy = {NotEmptyStringConstraintValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface NotEmptyString {
    String message() default "{com.learn.project.common.web.util.valid.NotEmptyString.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

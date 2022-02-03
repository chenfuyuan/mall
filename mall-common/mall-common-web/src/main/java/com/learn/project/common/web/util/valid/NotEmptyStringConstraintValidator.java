package com.learn.project.common.web.util.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义规则校验器: 校验值不能为空
 *
 * @author chenfuyuan
 * @date 2022/1/8 20:28
 */
public class NotEmptyStringConstraintValidator implements ConstraintValidator<NotEmptyString,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !"".equals(value);
    }
}

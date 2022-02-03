package com.learn.project.common.web.util.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;

/**
 * 自定义校验器- 校验值包含在指定值列表中
 *
 * @author chenfuyuan
 * @date 2022/1/8 17:57
 */
public class ListValueIntegerConstraintValidator implements ConstraintValidator<ListValue,Integer> {
    private HashSet<Integer> set = new HashSet<>();

    @Override
    public void initialize(ListValue constraintAnnotation) {
        for (int val : constraintAnnotation.vals()) {
            set.add(val);
        }
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return set.contains(value);
    }
}

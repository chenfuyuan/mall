package com.learn.common.validation.validator;

import com.learn.common.utils.EmptyUtils;
import com.learn.common.validation.annotaion.Contain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName ListValueValidator
 * @Description 包含校验器，校验值是否保存在List中
 * @Author chenfuyuan
 * @Date 2020-8-8 14:57
 * @Version 1.0
 */
public class ContainValidator implements ConstraintValidator<Contain, String> {
    /**
     * 数据集
     */
    private Set<String> set = new HashSet<>();

    /**
     * 初始化
     * @author: Vito.Chen
     * @date: 2020-8-8 15:23
     * @param constraintAnnotation 注解信息
     * @return: void
     */
    @Override
    public void initialize(Contain constraintAnnotation) {
        //获取vals
        String[] vals = constraintAnnotation.vals();
        for (String val : vals) {
            //不为空时，set进集合
            if(EmptyUtils.isNotEmpty(val)){
                set.add(val);
            }
        }
    }

    /**
     * 校验值是否包含在List中
     * @param value 校验的值
     * @param context 上下文
     * @author: Vito.Chen
     * @date: 2020-8-8 15:06
     * @return: boolean {@code true}:校验成功 {@code false}:校验失败
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return set.contains(value);
    }


}

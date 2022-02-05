package com.learn.project.common.web.util.valid;

import com.learn.project.common.web.exception.BizException;
import com.uptool.core.util.EmptyUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Hibernate-validator校验工具类
 *
 * @author chenfuyuan
 * @date 2022/2/4 17:49
 */
public class ValidatorUtil {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws BizException 校验不通过，则报RRException异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws BizException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<Object> constraint : constraintViolations) {
                msg.append(constraint.getMessage()).append("<br>");
            }
            throw new BizException(msg.toString());
        }
    }

    /**
     * 遍历集合，并对每一项进行校验
     * @param collection 集合
     * @param groups 分组
     * @throws BizException 业务异常
     */
    public static void validateList(Collection<Object> collection, Class<?>... groups) throws BizException {
        if (EmptyUtil.isEmpty(collection)) {
            return;
        }

        collection.forEach(item->{
            validateEntity(item, groups);
        });
    }
}

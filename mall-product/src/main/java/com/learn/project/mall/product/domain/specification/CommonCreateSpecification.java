package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.common.Common;
import org.springframework.stereotype.Component;

/**
 * 通用测试-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Component
public class CommonCreateSpecification extends AbstractSpecification<Common> {

    @Override
    public boolean isSatisfiedBy(Common common) {
        return true;
    }
}

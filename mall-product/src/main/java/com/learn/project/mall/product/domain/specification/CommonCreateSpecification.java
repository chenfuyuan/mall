package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.common.Common;
import com.learn.project.mall.product.domain.model.common.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 通用测试-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Component
public class CommonCreateSpecification extends AbstractSpecification<Common> {

    /**
     * 仓储服务
     */
    private final CommonRepository commonRepository;

    @Autowired
    public CommonCreateSpecification(CommonRepository commonRepository) {
        this.commonRepository = commonRepository;
    }

    @Override
    public boolean isSatisfiedBy(Common common) {
        return true;
    }
}

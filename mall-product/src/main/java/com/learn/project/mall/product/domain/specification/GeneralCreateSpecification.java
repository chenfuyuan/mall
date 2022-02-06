package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.general.General;
import com.learn.project.mall.product.domain.model.general.GeneralRepository;
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
public class GeneralCreateSpecification extends AbstractSpecification<General> {

    /**
     * 仓储服务
     */
    private final GeneralRepository generalRepository;

    @Autowired
    public GeneralCreateSpecification(GeneralRepository generalRepository) {
        this.generalRepository = generalRepository;
    }

    @Override
    public boolean isSatisfiedBy(General general) {
        return true;
    }
}

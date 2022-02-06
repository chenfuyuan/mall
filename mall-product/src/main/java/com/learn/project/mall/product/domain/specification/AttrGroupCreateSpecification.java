package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.attrGroup.AttrGroup;
import com.learn.project.mall.product.domain.model.attrGroup.AttrGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 属性分组-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Component
public class AttrGroupCreateSpecification extends AbstractSpecification<AttrGroup> {

    /**
     * 仓储服务
     */
    private final AttrGroupRepository attrGroupRepository;

    @Autowired
    public AttrGroupCreateSpecification(AttrGroupRepository attrGroupRepository) {
        this.attrGroupRepository = attrGroupRepository;
    }

    @Override
    public boolean isSatisfiedBy(AttrGroup attrGroup) {
        return true;
    }
}

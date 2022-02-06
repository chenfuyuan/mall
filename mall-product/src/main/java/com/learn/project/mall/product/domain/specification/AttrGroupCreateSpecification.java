package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.attrGroup.AttrGroup;
import org.springframework.stereotype.Component;

/**
 * 属性分组-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Component
public class AttrGroupCreateSpecification extends AbstractSpecification<AttrGroup> {

    @Override
    public boolean isSatisfiedBy(AttrGroup attrGroup) {
        return true;
    }
}

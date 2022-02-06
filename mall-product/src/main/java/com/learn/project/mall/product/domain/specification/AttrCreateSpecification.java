package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.attr.Attr;
import org.springframework.stereotype.Component;

/**
 * 商品属性-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Component
public class AttrCreateSpecification extends AbstractSpecification<Attr> {

    @Override
    public boolean isSatisfiedBy(Attr attr) {
        return true;
    }
}

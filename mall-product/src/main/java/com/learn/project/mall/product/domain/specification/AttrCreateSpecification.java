package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.attr.Attr;
import com.learn.project.mall.product.domain.model.attr.AttrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 商品属性-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Component
public class AttrCreateSpecification extends AbstractSpecification<Attr> {

    /**
     * 仓储服务
     */
    private final AttrRepository attrRepository;

    @Autowired
    public AttrCreateSpecification(AttrRepository attrRepository) {
        this.attrRepository = attrRepository;
    }

    @Override
    public boolean isSatisfiedBy(Attr attr) {
        return true;
    }
}

package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.productAttrValue.ProductAttrValue;
import com.learn.project.mall.product.domain.model.productAttrValue.ProductAttrValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * spu属性值-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Component
public class ProductAttrValueCreateSpecification extends AbstractSpecification<ProductAttrValue> {

    /**
     * 仓储服务
     */
    private final ProductAttrValueRepository productAttrValueRepository;

    @Autowired
    public ProductAttrValueCreateSpecification(ProductAttrValueRepository productAttrValueRepository) {
        this.productAttrValueRepository = productAttrValueRepository;
    }

    @Override
    public boolean isSatisfiedBy(ProductAttrValue productAttrValue) {
        return true;
    }
}

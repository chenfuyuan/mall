package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.productAttrValue.ProductAttrValue;
import org.springframework.stereotype.Component;

/**
 * spu属性值-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Component
public class ProductAttrValueCreateSpecification extends AbstractSpecification<ProductAttrValue> {

    @Override
    public boolean isSatisfiedBy(ProductAttrValue productAttrValue) {
        return true;
    }
}

package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.skuSaleAttrValue.SkuSaleAttrValue;
import org.springframework.stereotype.Component;

/**
 * sku销售属性&值-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Component
public class SkuSaleAttrValueCreateSpecification extends AbstractSpecification<SkuSaleAttrValue> {

    @Override
    public boolean isSatisfiedBy(SkuSaleAttrValue skuSaleAttrValue) {
        return true;
    }
}

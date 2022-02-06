package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.skuSaleAttrValue.SkuSaleAttrValue;
import com.learn.project.mall.product.domain.model.skuSaleAttrValue.SkuSaleAttrValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * sku销售属性&值-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Component
public class SkuSaleAttrValueCreateSpecification extends AbstractSpecification<SkuSaleAttrValue> {

    /**
     * 仓储服务
     */
    private final SkuSaleAttrValueRepository skuSaleAttrValueRepository;

    @Autowired
    public SkuSaleAttrValueCreateSpecification(SkuSaleAttrValueRepository skuSaleAttrValueRepository) {
        this.skuSaleAttrValueRepository = skuSaleAttrValueRepository;
    }

    @Override
    public boolean isSatisfiedBy(SkuSaleAttrValue skuSaleAttrValue) {
        return true;
    }
}

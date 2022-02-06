package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.skuInfo.SkuInfo;
import org.springframework.stereotype.Component;

/**
 * sku信息-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Component
public class SkuInfoCreateSpecification extends AbstractSpecification<SkuInfo> {

    @Override
    public boolean isSatisfiedBy(SkuInfo skuInfo) {
        return true;
    }
}

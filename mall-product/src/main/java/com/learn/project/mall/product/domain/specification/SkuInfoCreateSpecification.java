package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.skuInfo.SkuInfo;
import com.learn.project.mall.product.domain.model.skuInfo.SkuInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * sku信息-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Component
public class SkuInfoCreateSpecification extends AbstractSpecification<SkuInfo> {

    /**
     * 仓储服务
     */
    private final SkuInfoRepository skuInfoRepository;

    @Autowired
    public SkuInfoCreateSpecification(SkuInfoRepository skuInfoRepository) {
        this.skuInfoRepository = skuInfoRepository;
    }

    @Override
    public boolean isSatisfiedBy(SkuInfo skuInfo) {
        return true;
    }
}

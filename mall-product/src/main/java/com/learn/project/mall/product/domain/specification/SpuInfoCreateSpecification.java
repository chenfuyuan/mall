package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.spuInfo.SpuInfo;
import com.learn.project.mall.product.domain.model.spuInfo.SpuInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * spu信息-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Component
public class SpuInfoCreateSpecification extends AbstractSpecification<SpuInfo> {

    /**
     * 仓储服务
     */
    private final SpuInfoRepository spuInfoRepository;

    @Autowired
    public SpuInfoCreateSpecification(SpuInfoRepository spuInfoRepository) {
        this.spuInfoRepository = spuInfoRepository;
    }

    @Override
    public boolean isSatisfiedBy(SpuInfo spuInfo) {
        return true;
    }
}

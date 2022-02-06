package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.spuInfoDesc.SpuInfoDesc;
import com.learn.project.mall.product.domain.model.spuInfoDesc.SpuInfoDescRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * spu信息介绍-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Component
public class SpuInfoDescCreateSpecification extends AbstractSpecification<SpuInfoDesc> {

    /**
     * 仓储服务
     */
    private final SpuInfoDescRepository spuInfoDescRepository;

    @Autowired
    public SpuInfoDescCreateSpecification(SpuInfoDescRepository spuInfoDescRepository) {
        this.spuInfoDescRepository = spuInfoDescRepository;
    }

    @Override
    public boolean isSatisfiedBy(SpuInfoDesc spuInfoDesc) {
        return true;
    }
}

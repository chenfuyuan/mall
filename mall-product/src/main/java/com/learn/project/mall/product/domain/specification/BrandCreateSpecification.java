package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.brand.Brand;
import com.learn.project.mall.product.domain.model.brand.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 品牌-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Component
public class BrandCreateSpecification extends AbstractSpecification<Brand> {

    /**
     * 仓储服务
     */
    private final BrandRepository brandRepository;

    @Autowired
    public BrandCreateSpecification(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public boolean isSatisfiedBy(Brand brand) {
        return true;
    }
}

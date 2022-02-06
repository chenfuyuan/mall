package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.brand.Brand;
import org.springframework.stereotype.Component;

/**
 * 品牌-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Component
public class BrandCreateSpecification extends AbstractSpecification<Brand> {

    @Override
    public boolean isSatisfiedBy(Brand brand) {
        return true;
    }
}

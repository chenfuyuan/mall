package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.skuImages.SkuImages;
import com.learn.project.mall.product.domain.model.skuImages.SkuImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * sku图片-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Component
public class SkuImagesCreateSpecification extends AbstractSpecification<SkuImages> {

    /**
     * 仓储服务
     */
    private final SkuImagesRepository skuImagesRepository;

    @Autowired
    public SkuImagesCreateSpecification(SkuImagesRepository skuImagesRepository) {
        this.skuImagesRepository = skuImagesRepository;
    }

    @Override
    public boolean isSatisfiedBy(SkuImages skuImages) {
        return true;
    }
}

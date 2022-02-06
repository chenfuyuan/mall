package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.spuImages.SpuImages;
import com.learn.project.mall.product.domain.model.spuImages.SpuImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * spu图片-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Component
public class SpuImagesCreateSpecification extends AbstractSpecification<SpuImages> {

    /**
     * 仓储服务
     */
    private final SpuImagesRepository spuImagesRepository;

    @Autowired
    public SpuImagesCreateSpecification(SpuImagesRepository spuImagesRepository) {
        this.spuImagesRepository = spuImagesRepository;
    }

    @Override
    public boolean isSatisfiedBy(SpuImages spuImages) {
        return true;
    }
}

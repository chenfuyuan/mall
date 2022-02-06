package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.spuImages.SpuImages;
import org.springframework.stereotype.Component;

/**
 * spu图片-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Component
public class SpuImagesCreateSpecification extends AbstractSpecification<SpuImages> {

    @Override
    public boolean isSatisfiedBy(SpuImages spuImages) {
        return true;
    }
}

package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.spuComment.SpuComment;
import org.springframework.stereotype.Component;

/**
 * 商品评价-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Component
public class SpuCommentCreateSpecification extends AbstractSpecification<SpuComment> {

    @Override
    public boolean isSatisfiedBy(SpuComment spuComment) {
        return true;
    }
}

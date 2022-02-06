package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.spuComment.SpuComment;
import com.learn.project.mall.product.domain.model.spuComment.SpuCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 商品评价-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Component
public class SpuCommentCreateSpecification extends AbstractSpecification<SpuComment> {

    /**
     * 仓储服务
     */
    private final SpuCommentRepository spuCommentRepository;

    @Autowired
    public SpuCommentCreateSpecification(SpuCommentRepository spuCommentRepository) {
        this.spuCommentRepository = spuCommentRepository;
    }

    @Override
    public boolean isSatisfiedBy(SpuComment spuComment) {
        return true;
    }
}

package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.commentReplay.CommentReplay;
import com.learn.project.mall.product.domain.model.commentReplay.CommentReplayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 商品评价回复关系-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Component
public class CommentReplayCreateSpecification extends AbstractSpecification<CommentReplay> {

    /**
     * 仓储服务
     */
    private final CommentReplayRepository commentReplayRepository;

    @Autowired
    public CommentReplayCreateSpecification(CommentReplayRepository commentReplayRepository) {
        this.commentReplayRepository = commentReplayRepository;
    }

    @Override
    public boolean isSatisfiedBy(CommentReplay commentReplay) {
        return true;
    }
}

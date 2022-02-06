package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.commentReplay.CommentReplay;
import org.springframework.stereotype.Component;

/**
 * 商品评价回复关系-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Component
public class CommentReplayCreateSpecification extends AbstractSpecification<CommentReplay> {

    @Override
    public boolean isSatisfiedBy(CommentReplay commentReplay) {
        return true;
    }
}

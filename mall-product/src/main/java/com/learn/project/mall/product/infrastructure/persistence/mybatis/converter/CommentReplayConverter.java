package com.learn.project.mall.product.infrastructure.persistence.mybatis.converter;

import com.learn.project.common.mybatis.util.ConverterUtil;
import com.learn.project.mall.product.domain.model.commentReplay.CommentReplay;
import com.learn.project.mall.product.domain.model.commentReplay.CommentReplayId;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CommentReplayDo;
import com.uptool.core.util.EmptyUtil;

/**
 * 商品评价回复关系-转换器对象
 * 用于Do与领域对象的相互转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class CommentReplayConverter {

    /**
     * 将领域对象转化为数据对象
     * @param commentReplay 领域对象
     * @return
     */
    public static CommentReplayDo fromCommentReplay(CommentReplay commentReplay) {
        if (EmptyUtil.isNull(commentReplay)) {
            return null;
        }
        CommentReplayDo result = new CommentReplayDo();
        ConverterUtil.fromDomainModelCommonInfo(result,commentReplay,commentReplay);

        result.setCommentId(commentReplay.getCommentId());
        result.setReplyId(commentReplay.getReplyId());

        return result;
    }

    /**
     * 将数据对象转化为领域对象
     * @param commentReplayDo 数据对象
     * @return
     */
    public static CommentReplay toCommentReplay(CommentReplayDo commentReplayDo) {
        if (EmptyUtil.isNull(commentReplayDo)) {
            return null;
        }

        CommentReplay result = new CommentReplay();
        ConverterUtil.toDomainModelCommonInfo(result, commentReplayDo);

        result.setId(new CommentReplayId(commentReplayDo.getId()));

        result.setCommentId(commentReplayDo.getCommentId());
        result.setReplyId(commentReplayDo.getReplyId());
        return result;
    }
}

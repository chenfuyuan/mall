package com.learn.project.mall.product.domain.model.commentReplay;

import com.learn.project.common.core.domain.Entity;
import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.common.core.domain.HaveCommonInfo;
import lombok.Data;
import java.util.Date;

/**
 * 商品评价回复关系-领域对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
public class CommentReplay implements Entity<CommentReplay>, HaveCommonInfo {


    /**
    * 主键-id
    */
    private CommentReplayId id;
    /**
    * 评论id
    */
    private Long commentId;
    /**
    * 回复id
    */
    private Long replyId;

    /**
     * 时间信息
     */
    private CommonInfo commonInfo;

    @Override
    public EntityId getUniqueLabel() {
        return this.id;
    }
}

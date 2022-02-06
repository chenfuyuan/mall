package com.learn.project.mall.product.application.assembler;

import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.mall.product.application.command.CommentReplayCommand;
import com.learn.project.mall.product.application.dto.CommentReplayDto;
import com.learn.project.mall.product.domain.model.commentReplay.CommentReplay;
import com.learn.project.mall.product.domain.model.commentReplay.CommentReplayId;
import com.uptool.core.util.EmptyUtil;

/**
 * 商品评价回复关系-装配器
 * 用于将 应用层对象与业务层对象进行转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class CommentReplayAssembler {

    /**
     * 转换成领域对象
     * @param command 命令对象
     * @return
     */
    public static CommentReplay toCommentReplay(CommentReplayCommand command) {
        if (EmptyUtil.isEmpty(command)) {
            return null;
        }
        CommentReplay result = new CommentReplay();
        result.setId(command.getId() == null ? null: new CommentReplayId(command.getId()));

        result.setCommentId(command.getCommentId());
        result.setReplyId(command.getReplyId());

        result.setCommonInfo(CommonInfo.getInstance(command.getGmtCreate(), command.getGmtModified(), command.getIsDelete(), command.getUpdateVersion()));

        return result;
    }

    /**
     * 转换成数据传输对象
     * @param commentReplay 领域对象
     * @return
     */
    public static CommentReplayDto fromCommentReplay(CommentReplay commentReplay) {
        if (EmptyUtil.isEmpty(commentReplay)) {
            return null;
        }
        CommentReplayDto result = new CommentReplayDto();
        result.setId(EntityId.getId(commentReplay.getId()));

        result.setCommentId(commentReplay.getCommentId());
        result.setReplyId(commentReplay.getReplyId());

        if (commentReplay.getCommonInfo() != null) {
            result.setIsDelete(commentReplay.getCommonInfo().getIsDeleteEnum().getValue());
            result.setGmtCreate(commentReplay.getCommonInfo().getCreateTime());
            result.setGmtModified(commentReplay.getCommonInfo().getUpdateTime());
            result.setUpdateVersion(commentReplay.getCommonInfo().getVersion());
        }
        return result;
    }
}

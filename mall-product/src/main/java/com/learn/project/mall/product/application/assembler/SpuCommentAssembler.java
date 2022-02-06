package com.learn.project.mall.product.application.assembler;

import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.mall.product.application.command.SpuCommentCommand;
import com.learn.project.mall.product.application.dto.SpuCommentDto;
import com.learn.project.mall.product.domain.model.spuComment.SpuComment;
import com.learn.project.mall.product.domain.model.spuComment.SpuCommentId;
import com.uptool.core.util.EmptyUtil;

/**
 * 商品评价-装配器
 * 用于将 应用层对象与业务层对象进行转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class SpuCommentAssembler {

    /**
     * 转换成领域对象
     * @param command 命令对象
     * @return
     */
    public static SpuComment toSpuComment(SpuCommentCommand command) {
        if (EmptyUtil.isEmpty(command)) {
            return null;
        }
        SpuComment result = new SpuComment();
        result.setId(command.getId() == null ? null: new SpuCommentId(command.getId()));

        result.setSkuId(command.getSkuId());
        result.setSpuId(command.getSpuId());
        result.setSpuName(command.getSpuName());
        result.setMemberNickName(command.getMemberNickName());
        result.setStar(command.getStar());
        result.setMemberIp(command.getMemberIp());
        result.setCreateTime(command.getCreateTime());
        result.setShowStatus(command.getShowStatus());
        result.setSpuAttributes(command.getSpuAttributes());
        result.setLikesCount(command.getLikesCount());
        result.setReplyCount(command.getReplyCount());
        result.setResources(command.getResources());
        result.setContent(command.getContent());
        result.setMemberIcon(command.getMemberIcon());
        result.setCommentType(command.getCommentType());

        result.setCommonInfo(CommonInfo.getInstance(command.getGmtCreate(), command.getGmtModified(), command.getIsDelete(), command.getUpdateVersion()));

        return result;
    }

    /**
     * 转换成数据传输对象
     * @param spuComment 领域对象
     * @return
     */
    public static SpuCommentDto fromSpuComment(SpuComment spuComment) {
        if (EmptyUtil.isEmpty(spuComment)) {
            return null;
        }
        SpuCommentDto result = new SpuCommentDto();
        result.setId(EntityId.getId(spuComment.getId()));

        result.setSkuId(spuComment.getSkuId());
        result.setSpuId(spuComment.getSpuId());
        result.setSpuName(spuComment.getSpuName());
        result.setMemberNickName(spuComment.getMemberNickName());
        result.setStar(spuComment.getStar());
        result.setMemberIp(spuComment.getMemberIp());
        result.setCreateTime(spuComment.getCreateTime());
        result.setShowStatus(spuComment.getShowStatus());
        result.setSpuAttributes(spuComment.getSpuAttributes());
        result.setLikesCount(spuComment.getLikesCount());
        result.setReplyCount(spuComment.getReplyCount());
        result.setResources(spuComment.getResources());
        result.setContent(spuComment.getContent());
        result.setMemberIcon(spuComment.getMemberIcon());
        result.setCommentType(spuComment.getCommentType());

        if (spuComment.getCommonInfo() != null) {
            result.setIsDelete(spuComment.getCommonInfo().getIsDeleteEnum().getValue());
            result.setGmtCreate(spuComment.getCommonInfo().getCreateTime());
            result.setGmtModified(spuComment.getCommonInfo().getUpdateTime());
            result.setUpdateVersion(spuComment.getCommonInfo().getVersion());
        }
        return result;
    }
}

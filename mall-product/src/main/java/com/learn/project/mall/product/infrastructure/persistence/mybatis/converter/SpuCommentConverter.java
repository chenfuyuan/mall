package com.learn.project.mall.product.infrastructure.persistence.mybatis.converter;

import com.learn.project.common.mybatis.util.ConverterUtil;
import com.learn.project.mall.product.domain.model.spuComment.SpuComment;
import com.learn.project.mall.product.domain.model.spuComment.SpuCommentId;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SpuCommentDo;
import com.uptool.core.util.EmptyUtil;

/**
 * 商品评价-转换器对象
 * 用于Do与领域对象的相互转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public class SpuCommentConverter {

    /**
     * 将领域对象转化为数据对象
     * @param spuComment 领域对象
     * @return
     */
    public static SpuCommentDo fromSpuComment(SpuComment spuComment) {
        if (EmptyUtil.isNull(spuComment)) {
            return null;
        }
        SpuCommentDo result = new SpuCommentDo();
        ConverterUtil.fromDomainModelCommonInfo(result,spuComment,spuComment);

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

        return result;
    }

    /**
     * 将数据对象转化为领域对象
     * @param spuCommentDo 数据对象
     * @return
     */
    public static SpuComment toSpuComment(SpuCommentDo spuCommentDo) {
        if (EmptyUtil.isNull(spuCommentDo)) {
            return null;
        }

        SpuComment result = new SpuComment();
        ConverterUtil.toDomainModelCommonInfo(result, spuCommentDo);

        result.setId(new SpuCommentId(spuCommentDo.getId()));

        result.setSkuId(spuCommentDo.getSkuId());
        result.setSpuId(spuCommentDo.getSpuId());
        result.setSpuName(spuCommentDo.getSpuName());
        result.setMemberNickName(spuCommentDo.getMemberNickName());
        result.setStar(spuCommentDo.getStar());
        result.setMemberIp(spuCommentDo.getMemberIp());
        result.setCreateTime(spuCommentDo.getCreateTime());
        result.setShowStatus(spuCommentDo.getShowStatus());
        result.setSpuAttributes(spuCommentDo.getSpuAttributes());
        result.setLikesCount(spuCommentDo.getLikesCount());
        result.setReplyCount(spuCommentDo.getReplyCount());
        result.setResources(spuCommentDo.getResources());
        result.setContent(spuCommentDo.getContent());
        result.setMemberIcon(spuCommentDo.getMemberIcon());
        result.setCommentType(spuCommentDo.getCommentType());
        return result;
    }
}

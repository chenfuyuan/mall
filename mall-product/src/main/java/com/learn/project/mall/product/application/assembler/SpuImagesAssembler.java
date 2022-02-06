package com.learn.project.mall.product.application.assembler;

import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.mall.product.application.command.SpuImagesCommand;
import com.learn.project.mall.product.application.dto.SpuImagesDto;
import com.learn.project.mall.product.domain.model.spuImages.SpuImages;
import com.learn.project.mall.product.domain.model.spuImages.SpuImagesId;
import com.uptool.core.util.EmptyUtil;

/**
 * spu图片-装配器
 * 用于将 应用层对象与业务层对象进行转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class SpuImagesAssembler {

    /**
     * 转换成领域对象
     * @param command 命令对象
     * @return
     */
    public static SpuImages toSpuImages(SpuImagesCommand command) {
        if (EmptyUtil.isEmpty(command)) {
            return null;
        }
        SpuImages result = new SpuImages();
        result.setId(command.getId() == null ? null: new SpuImagesId(command.getId()));

        result.setSpuId(command.getSpuId());
        result.setImgName(command.getImgName());
        result.setImgUrl(command.getImgUrl());
        result.setImgSort(command.getImgSort());
        result.setDefaultImg(command.getDefaultImg());

        result.setCommonInfo(CommonInfo.getInstance(command.getGmtCreate(), command.getGmtModified(), command.getIsDelete(), command.getUpdateVersion()));

        return result;
    }

    /**
     * 转换成数据传输对象
     * @param spuImages 领域对象
     * @return
     */
    public static SpuImagesDto fromSpuImages(SpuImages spuImages) {
        if (EmptyUtil.isEmpty(spuImages)) {
            return null;
        }
        SpuImagesDto result = new SpuImagesDto();
        result.setId(EntityId.getId(spuImages.getId()));

        result.setSpuId(spuImages.getSpuId());
        result.setImgName(spuImages.getImgName());
        result.setImgUrl(spuImages.getImgUrl());
        result.setImgSort(spuImages.getImgSort());
        result.setDefaultImg(spuImages.getDefaultImg());

        if (spuImages.getCommonInfo() != null) {
            result.setIsDelete(spuImages.getCommonInfo().getIsDeleteEnum().getValue());
            result.setGmtCreate(spuImages.getCommonInfo().getCreateTime());
            result.setGmtModified(spuImages.getCommonInfo().getUpdateTime());
            result.setUpdateVersion(spuImages.getCommonInfo().getVersion());
        }
        return result;
    }
}

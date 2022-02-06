package com.learn.project.mall.product.application.assembler;

import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.mall.product.application.command.SkuImagesCommand;
import com.learn.project.mall.product.application.dto.SkuImagesDto;
import com.learn.project.mall.product.domain.model.skuImages.SkuImages;
import com.learn.project.mall.product.domain.model.skuImages.SkuImagesId;
import com.uptool.core.util.EmptyUtil;

/**
 * sku图片-装配器
 * 用于将 应用层对象与业务层对象进行转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class SkuImagesAssembler {

    /**
     * 转换成领域对象
     * @param command 命令对象
     * @return
     */
    public static SkuImages toSkuImages(SkuImagesCommand command) {
        if (EmptyUtil.isEmpty(command)) {
            return null;
        }
        SkuImages result = new SkuImages();
        result.setId(command.getId() == null ? null: new SkuImagesId(command.getId()));

        result.setSkuId(command.getSkuId());
        result.setImgUrl(command.getImgUrl());
        result.setImgSort(command.getImgSort());
        result.setDefaultImg(command.getDefaultImg());

        result.setCommonInfo(CommonInfo.getInstance(command.getGmtCreate(), command.getGmtModified(), command.getIsDelete(), command.getUpdateVersion()));

        return result;
    }

    /**
     * 转换成数据传输对象
     * @param skuImages 领域对象
     * @return
     */
    public static SkuImagesDto fromSkuImages(SkuImages skuImages) {
        if (EmptyUtil.isEmpty(skuImages)) {
            return null;
        }
        SkuImagesDto result = new SkuImagesDto();
        result.setId(EntityId.getId(skuImages.getId()));

        result.setSkuId(skuImages.getSkuId());
        result.setImgUrl(skuImages.getImgUrl());
        result.setImgSort(skuImages.getImgSort());
        result.setDefaultImg(skuImages.getDefaultImg());

        if (skuImages.getCommonInfo() != null) {
            result.setIsDelete(skuImages.getCommonInfo().getIsDeleteEnum().getValue());
            result.setGmtCreate(skuImages.getCommonInfo().getCreateTime());
            result.setGmtModified(skuImages.getCommonInfo().getUpdateTime());
            result.setUpdateVersion(skuImages.getCommonInfo().getVersion());
        }
        return result;
    }
}

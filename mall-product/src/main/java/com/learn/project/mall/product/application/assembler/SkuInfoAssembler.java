package com.learn.project.mall.product.application.assembler;

import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.mall.product.application.command.SkuInfoCommand;
import com.learn.project.mall.product.application.dto.SkuInfoDto;
import com.learn.project.mall.product.domain.model.skuInfo.SkuInfo;
import com.learn.project.mall.product.domain.model.skuInfo.SkuInfoId;
import com.uptool.core.util.EmptyUtil;

/**
 * sku信息-装配器
 * 用于将 应用层对象与业务层对象进行转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public class SkuInfoAssembler {

    /**
     * 转换成领域对象
     * @param command 命令对象
     * @return
     */
    public static SkuInfo toSkuInfo(SkuInfoCommand command) {
        if (EmptyUtil.isEmpty(command)) {
            return null;
        }
        SkuInfo result = new SkuInfo();
        result.setSkuId(command.getSkuId() == null ? null: new SkuInfoId(command.getSkuId()));

        result.setSpuId(command.getSpuId());
        result.setSkuName(command.getSkuName());
        result.setSkuDesc(command.getSkuDesc());
        result.setCatalogId(command.getCatalogId());
        result.setBrandId(command.getBrandId());
        result.setSkuDefaultImg(command.getSkuDefaultImg());
        result.setSkuTitle(command.getSkuTitle());
        result.setSkuSubtitle(command.getSkuSubtitle());
        result.setPrice(command.getPrice());
        result.setSaleCount(command.getSaleCount());

        result.setCommonInfo(CommonInfo.getInstance(command.getGmtCreate(), command.getGmtModified(), command.getIsDelete(), command.getUpdateVersion()));

        return result;
    }

    /**
     * 转换成数据传输对象
     * @param skuInfo 领域对象
     * @return
     */
    public static SkuInfoDto fromSkuInfo(SkuInfo skuInfo) {
        if (EmptyUtil.isEmpty(skuInfo)) {
            return null;
        }
        SkuInfoDto result = new SkuInfoDto();
        result.setSkuId(EntityId.getId(skuInfo.getSkuId()));

        result.setSpuId(skuInfo.getSpuId());
        result.setSkuName(skuInfo.getSkuName());
        result.setSkuDesc(skuInfo.getSkuDesc());
        result.setCatalogId(skuInfo.getCatalogId());
        result.setBrandId(skuInfo.getBrandId());
        result.setSkuDefaultImg(skuInfo.getSkuDefaultImg());
        result.setSkuTitle(skuInfo.getSkuTitle());
        result.setSkuSubtitle(skuInfo.getSkuSubtitle());
        result.setPrice(skuInfo.getPrice());
        result.setSaleCount(skuInfo.getSaleCount());

        if (skuInfo.getCommonInfo() != null) {
            result.setIsDelete(skuInfo.getCommonInfo().getIsDeleteEnum().getValue());
            result.setGmtCreate(skuInfo.getCommonInfo().getCreateTime());
            result.setGmtModified(skuInfo.getCommonInfo().getUpdateTime());
            result.setUpdateVersion(skuInfo.getCommonInfo().getVersion());
        }
        return result;
    }
}

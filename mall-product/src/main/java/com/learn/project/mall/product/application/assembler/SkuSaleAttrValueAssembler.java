package com.learn.project.mall.product.application.assembler;

import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.mall.product.application.command.SkuSaleAttrValueCommand;
import com.learn.project.mall.product.application.dto.SkuSaleAttrValueDto;
import com.learn.project.mall.product.domain.model.skuSaleAttrValue.SkuSaleAttrValue;
import com.learn.project.mall.product.domain.model.skuSaleAttrValue.SkuSaleAttrValueId;
import com.uptool.core.util.EmptyUtil;

/**
 * sku销售属性&值-装配器
 * 用于将 应用层对象与业务层对象进行转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class SkuSaleAttrValueAssembler {

    /**
     * 转换成领域对象
     * @param command 命令对象
     * @return
     */
    public static SkuSaleAttrValue toSkuSaleAttrValue(SkuSaleAttrValueCommand command) {
        if (EmptyUtil.isEmpty(command)) {
            return null;
        }
        SkuSaleAttrValue result = new SkuSaleAttrValue();
        result.setId(command.getId() == null ? null: new SkuSaleAttrValueId(command.getId()));

        result.setSkuId(command.getSkuId());
        result.setAttrId(command.getAttrId());
        result.setAttrName(command.getAttrName());
        result.setAttrValue(command.getAttrValue());
        result.setAttrSort(command.getAttrSort());

        result.setCommonInfo(CommonInfo.getInstance(command.getGmtCreate(), command.getGmtModified(), command.getIsDelete(), command.getUpdateVersion()));

        return result;
    }

    /**
     * 转换成数据传输对象
     * @param skuSaleAttrValue 领域对象
     * @return
     */
    public static SkuSaleAttrValueDto fromSkuSaleAttrValue(SkuSaleAttrValue skuSaleAttrValue) {
        if (EmptyUtil.isEmpty(skuSaleAttrValue)) {
            return null;
        }
        SkuSaleAttrValueDto result = new SkuSaleAttrValueDto();
        result.setId(EntityId.getId(skuSaleAttrValue.getId()));

        result.setSkuId(skuSaleAttrValue.getSkuId());
        result.setAttrId(skuSaleAttrValue.getAttrId());
        result.setAttrName(skuSaleAttrValue.getAttrName());
        result.setAttrValue(skuSaleAttrValue.getAttrValue());
        result.setAttrSort(skuSaleAttrValue.getAttrSort());

        if (skuSaleAttrValue.getCommonInfo() != null) {
            result.setIsDelete(skuSaleAttrValue.getCommonInfo().getIsDeleteEnum().getValue());
            result.setGmtCreate(skuSaleAttrValue.getCommonInfo().getCreateTime());
            result.setGmtModified(skuSaleAttrValue.getCommonInfo().getUpdateTime());
            result.setUpdateVersion(skuSaleAttrValue.getCommonInfo().getVersion());
        }
        return result;
    }
}

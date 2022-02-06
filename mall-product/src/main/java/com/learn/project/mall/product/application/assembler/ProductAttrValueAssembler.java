package com.learn.project.mall.product.application.assembler;

import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.mall.product.application.command.ProductAttrValueCommand;
import com.learn.project.mall.product.application.dto.ProductAttrValueDto;
import com.learn.project.mall.product.domain.model.productAttrValue.ProductAttrValue;
import com.learn.project.mall.product.domain.model.productAttrValue.ProductAttrValueId;
import com.uptool.core.util.EmptyUtil;

/**
 * spu属性值-装配器
 * 用于将 应用层对象与业务层对象进行转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public class ProductAttrValueAssembler {

    /**
     * 转换成领域对象
     * @param command 命令对象
     * @return
     */
    public static ProductAttrValue toProductAttrValue(ProductAttrValueCommand command) {
        if (EmptyUtil.isEmpty(command)) {
            return null;
        }
        ProductAttrValue result = new ProductAttrValue();
        result.setId(command.getId() == null ? null: new ProductAttrValueId(command.getId()));

        result.setSpuId(command.getSpuId());
        result.setAttrId(command.getAttrId());
        result.setAttrName(command.getAttrName());
        result.setAttrValue(command.getAttrValue());
        result.setAttrSort(command.getAttrSort());
        result.setQuickShow(command.getQuickShow());

        result.setCommonInfo(CommonInfo.getInstance(command.getGmtCreate(), command.getGmtModified(), command.getIsDelete(), command.getUpdateVersion()));

        return result;
    }

    /**
     * 转换成数据传输对象
     * @param productAttrValue 领域对象
     * @return
     */
    public static ProductAttrValueDto fromProductAttrValue(ProductAttrValue productAttrValue) {
        if (EmptyUtil.isEmpty(productAttrValue)) {
            return null;
        }
        ProductAttrValueDto result = new ProductAttrValueDto();
        result.setId(EntityId.getId(productAttrValue.getId()));

        result.setSpuId(productAttrValue.getSpuId());
        result.setAttrId(productAttrValue.getAttrId());
        result.setAttrName(productAttrValue.getAttrName());
        result.setAttrValue(productAttrValue.getAttrValue());
        result.setAttrSort(productAttrValue.getAttrSort());
        result.setQuickShow(productAttrValue.getQuickShow());

        if (productAttrValue.getCommonInfo() != null) {
            result.setIsDelete(productAttrValue.getCommonInfo().getIsDeleteEnum().getValue());
            result.setGmtCreate(productAttrValue.getCommonInfo().getCreateTime());
            result.setGmtModified(productAttrValue.getCommonInfo().getUpdateTime());
            result.setUpdateVersion(productAttrValue.getCommonInfo().getVersion());
        }
        return result;
    }
}

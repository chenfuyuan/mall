package com.learn.project.mall.product.application.assembler;

import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.mall.product.application.command.CategoryBrandRelationCommand;
import com.learn.project.mall.product.application.dto.CategoryBrandRelationDto;
import com.learn.project.mall.product.domain.model.categoryBrandRelation.CategoryBrandRelation;
import com.learn.project.mall.product.domain.model.categoryBrandRelation.CategoryBrandRelationId;
import com.uptool.core.util.EmptyUtil;

/**
 * 品牌分类关联-装配器
 * 用于将 应用层对象与业务层对象进行转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class CategoryBrandRelationAssembler {

    /**
     * 转换成领域对象
     * @param command 命令对象
     * @return
     */
    public static CategoryBrandRelation toCategoryBrandRelation(CategoryBrandRelationCommand command) {
        if (EmptyUtil.isEmpty(command)) {
            return null;
        }
        CategoryBrandRelation result = new CategoryBrandRelation();
        result.setId(command.getId() == null ? null: new CategoryBrandRelationId(command.getId()));

        result.setBrandId(command.getBrandId());
        result.setCatelogId(command.getCatelogId());
        result.setBrandName(command.getBrandName());
        result.setCatelogName(command.getCatelogName());

        result.setCommonInfo(CommonInfo.getInstance(command.getGmtCreate(), command.getGmtModified(), command.getIsDelete(), command.getUpdateVersion()));

        return result;
    }

    /**
     * 转换成数据传输对象
     * @param categoryBrandRelation 领域对象
     * @return
     */
    public static CategoryBrandRelationDto fromCategoryBrandRelation(CategoryBrandRelation categoryBrandRelation) {
        if (EmptyUtil.isEmpty(categoryBrandRelation)) {
            return null;
        }
        CategoryBrandRelationDto result = new CategoryBrandRelationDto();
        result.setId(EntityId.getId(categoryBrandRelation.getId()));

        result.setBrandId(categoryBrandRelation.getBrandId());
        result.setCatelogId(categoryBrandRelation.getCatelogId());
        result.setBrandName(categoryBrandRelation.getBrandName());
        result.setCatelogName(categoryBrandRelation.getCatelogName());

        if (categoryBrandRelation.getCommonInfo() != null) {
            result.setIsDelete(categoryBrandRelation.getCommonInfo().getIsDeleteEnum().getValue());
            result.setGmtCreate(categoryBrandRelation.getCommonInfo().getCreateTime());
            result.setGmtModified(categoryBrandRelation.getCommonInfo().getUpdateTime());
            result.setUpdateVersion(categoryBrandRelation.getCommonInfo().getVersion());
        }
        return result;
    }
}

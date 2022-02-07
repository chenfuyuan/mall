package com.learn.project.mall.product.application.assembler;
import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.web.constant.ShowStatusEnum;

import com.learn.project.mall.product.domain.model.product.ProductCount;
import com.learn.project.mall.product.domain.model.category.CategoryLevelEnum;

import com.learn.project.mall.product.application.command.CategoryCommand;
import com.learn.project.mall.product.application.dto.CategoryDto;
import com.learn.project.mall.product.domain.model.category.Category;
import com.learn.project.mall.product.domain.model.category.CategoryId;

import com.uptool.core.util.ListUtil;

import java.util.List;

/**
 * 领域对象与DTO的互相转换
 *
 * @author chenfuyuan
 * @date 2022/2/4 15:29
 */
public class CategoryAssembler {


    public static CategoryDto fromCategory(Category category) {
        if (category == null) {
            return null;
        }
        final CategoryDto dto = new CategoryDto();
        dto.setCatId(category.getCategoryId()==null?null:category.getCategoryId().getId());
        dto.setName(category.getName());
        dto.setCatLevel(category.getCatLevel() == null ? null : category.getCatLevel().getValue());
        dto.setSort(category.getSort());
        dto.setProductCount(category.getProductCount()==null?null:category.getProductCount().getCount());
        dto.setProductUnit(category.getProductUnit());
        dto.setParentCid(category.getParentId()==null?null:category.getParentId().getId());

        if (category.getTimeInfo() != null) {
            dto.setIsDelete(category.getTimeInfo().getIsDeleteEnum().getValue());
            dto.setGmtCreate(category.getTimeInfo().getCreateTime());
            dto.setGmtModified(category.getTimeInfo().getUpdateTime());
            dto.setUpdateVersion(category.getTimeInfo().getVersion());
        }
        if (category.getSubCategorys() != null) {
            List<CategoryDto> subCategoryList = ListUtil.listMapCollectToList(category.getSubCategorys(), subCategory ->
                    fromCategory(subCategory)
            );
            dto.setSubCategorys(subCategoryList);
        }
        return dto;
    }

    public static Category toCategory(CategoryCommand command) {
        Category category = new Category();
        category.setCategoryId(command.getCatId()==null? null: new CategoryId(command.getCatId()));
        category.setName(command.getName());
        category.setParentId(command.getParentCid() == null ? null : new CategoryId(command.getParentCid()));
        category.setCatLevel(CategoryLevelEnum.getCategoryLevelEnum(command.getCatLevel()));
        category.setShowStatus(ShowStatusEnum.getShowStatusEnum(command.getShowStatus()));
        category.setSort(command.getSort() == null ? null : command.getSort());
        category.setIcon(command.getIcon());
        category.setProductUnit(command.getProductUnit());
        category.setProductCount(command.getProductCount() == null ? null : new ProductCount(command.getProductCount()));
        category.setTimeInfo(CommonInfo.getInstance(command.getGmtCreate(), command.getGmtModified(), command.getIsDelete(), command.getUpdateVersion()));
        return category;
    }

    public static List<Category> toCategoryList(List<CategoryCommand> categoryCommandList) {
        return ListUtil.listMapCollectToList(categoryCommandList, command -> toCategory(command));
    }
}

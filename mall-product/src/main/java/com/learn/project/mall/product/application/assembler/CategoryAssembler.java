package com.learn.project.mall.product.application.assembler;

import com.learn.project.mall.product.application.dto.CategoryDto;
import com.learn.project.mall.product.domain.model.category.Category;
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
            return CategoryDto.EMPTY;
        }
        final CategoryDto dto = new CategoryDto();
        dto.setCatId(category.getCategoryId()==null?null:category.getCategoryId().getId());
        dto.setName(category.getName());
        dto.setCatLevel(category.getCatLevel() == null ? null : category.getCatLevel().getValue());
        dto.setSort(category.getSort());

        if (category.getSubCategorys() != null) {
            List<CategoryDto> subCategoryList = ListUtil.listMapCollectToList(category.getSubCategorys(), subCategory ->
                    fromCategory(subCategory)
            );
            dto.setSubCategorys(subCategoryList);
        }
        return dto;
    }
}

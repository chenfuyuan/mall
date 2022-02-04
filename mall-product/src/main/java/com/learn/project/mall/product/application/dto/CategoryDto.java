package com.learn.project.mall.product.application.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.learn.project.mall.product.domain.model.category.Category;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分类DTO
 *
 * @author chenfuyuan
 * @date 2022/2/3 23:28
 */
@Data
public class CategoryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final CategoryDto EMPTY = new CategoryDto();

    /**
     * 分类id
     */
    private Long catId;
    /**
     * 分类名称
     */
    private String name;

    /**
     * 层级
     */
    private Integer catLevel;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 子菜单列表
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CategoryDto> subCategorys;
}

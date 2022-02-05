package com.learn.project.mall.product.application.dto;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.learn.project.common.web.util.jsonSerializer.DateFormat;
import com.learn.project.mall.product.domain.model.category.Category;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
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
     * 是否删除
     */
    private Integer isDelete;
    /**
     * 创建时间
     */

    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 更新版本
     */
    private Integer updateVersion;

    /**
     * 子菜单列表
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CategoryDto> subCategorys;
}

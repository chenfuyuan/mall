package com.learn.project.mall.product.application.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
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
     * 父分类id
     */
    private Long parentCid;
    /**
     * 层级
     */
    private Integer catLevel;
    /**
     * 是否显示[0-不显示，1显示]
     */
    private Integer showStatus;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 图标地址
     */
    private String icon;
    /**
     * 计量单位
     */
    private String productUnit;
    /**
     * 商品数量
     */
    private Integer productCount;

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

package com.learn.project.mall.product.application.dto;

import lombok.Data;

import java.util.Date;

/**
 * 品牌分类关联-数据传输对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Data
public class CategoryBrandRelationDto {

    /**
    * 
    */
    private Long id;
    /**
    * 品牌id
    */
    private Long brandId;
    /**
    * 分类id
    */
    private Long catelogId;
    /**
    * 
    */
    private String brandName;
    /**
    * 
    */
    private String catelogName;
    /**
    * 是否删除[0-未删除, 1-删除]
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
}

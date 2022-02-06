package com.learn.project.mall.product.application.dto;

import lombok.Data;

import java.util.Date;

/**
 * spu属性值-数据传输对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
public class ProductAttrValueDto {

    /**
    * id
    */
    private Long id;
    /**
    * 商品id
    */
    private Long spuId;
    /**
    * 属性id
    */
    private Long attrId;
    /**
    * 属性名
    */
    private String attrName;
    /**
    * 属性值
    */
    private String attrValue;
    /**
    * 顺序
    */
    private Integer attrSort;
    /**
    * 快速展示【是否展示在介绍上；0-否 1-是】
    */
    private Integer quickShow;
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

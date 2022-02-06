package com.learn.project.mall.product.application.dto;

import lombok.Data;

import java.util.Date;

/**
 * 品牌-数据传输对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Data
public class BrandDto {

    /**
    * 品牌id
    */
    private Long brandId;
    /**
    * 品牌名
    */
    private String name;
    /**
    * 品牌logo地址
    */
    private String logo;
    /**
    * 介绍
    */
    private String descript;
    /**
    * 显示状态[0-不显示；1-显示]
    */
    private Integer showStatus;
    /**
    * 检索首字母
    */
    private String firstLetter;
    /**
    * 排序
    */
    private Integer sort;
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

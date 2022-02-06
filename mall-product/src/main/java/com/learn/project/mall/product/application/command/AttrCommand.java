package com.learn.project.mall.product.application.command;


import lombok.Data;
import java.util.Date;

/**
 * 商品属性-命令对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Data
public class AttrCommand {

    /**
    * 属性id
    */
    private Long attrId;
    /**
    * 属性名
    */
    private String attrName;
    /**
    * 是否需要检索[0-不需要，1-需要]
    */
    private Integer searchType;
    /**
    * 属性图标
    */
    private String icon;
    /**
    * 可选值列表[用逗号分隔]
    */
    private String valueSelect;
    /**
    * 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
    */
    private Integer attrType;
    /**
    * 启用状态[0 - 禁用，1 - 启用]
    */
    private Long enable;
    /**
    * 所属分类
    */
    private Long catelogId;
    /**
    * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
    */
    private Integer showDesc;
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

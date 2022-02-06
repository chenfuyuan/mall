package com.learn.project.mall.product.domain.model.attr;

import com.learn.project.common.core.domain.Entity;
import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.common.core.domain.HaveCommonInfo;
import lombok.Data;
import java.util.Date;

/**
 * 商品属性-领域对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Data
public class Attr implements Entity<Attr>, HaveCommonInfo {


    /**
    * 主键-属性id
    */
    private AttrId attrId;
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
     * 时间信息
     */
    private CommonInfo commonInfo;

    @Override
    public EntityId getUniqueLabel() {
        return this.attrId;
    }
}

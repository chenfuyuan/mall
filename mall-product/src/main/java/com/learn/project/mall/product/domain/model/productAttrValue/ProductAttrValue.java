package com.learn.project.mall.product.domain.model.productAttrValue;

import com.learn.project.common.core.domain.Entity;
import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.common.core.domain.HaveCommonInfo;
import lombok.Data;
import java.util.Date;

/**
 * spu属性值-领域对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
public class ProductAttrValue implements Entity<ProductAttrValue>, HaveCommonInfo {


    /**
    * 主键-id
    */
    private ProductAttrValueId id;
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
     * 时间信息
     */
    private CommonInfo commonInfo;

    @Override
    public EntityId getUniqueLabel() {
        return this.id;
    }
}

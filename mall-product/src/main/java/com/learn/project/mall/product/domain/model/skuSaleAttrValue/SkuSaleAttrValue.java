package com.learn.project.mall.product.domain.model.skuSaleAttrValue;

import com.learn.project.common.core.domain.Entity;
import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.common.core.domain.HaveCommonInfo;
import lombok.Data;
import java.util.Date;

/**
 * sku销售属性&值-领域对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
public class SkuSaleAttrValue implements Entity<SkuSaleAttrValue>, HaveCommonInfo {


    /**
    * 主键-id
    */
    private SkuSaleAttrValueId id;
    /**
    * sku_id
    */
    private Long skuId;
    /**
    * attr_id
    */
    private Long attrId;
    /**
    * 销售属性名
    */
    private String attrName;
    /**
    * 销售属性值
    */
    private String attrValue;
    /**
    * 顺序
    */
    private Integer attrSort;

    /**
     * 时间信息
     */
    private CommonInfo commonInfo;

    @Override
    public EntityId getUniqueLabel() {
        return this.id;
    }
}

package com.learn.project.mall.product.domain.model.skuInfo;

import com.learn.project.common.core.domain.Entity;
import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.common.core.domain.HaveCommonInfo;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * sku信息-领域对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
public class SkuInfo implements Entity<SkuInfo>, HaveCommonInfo {


    /**
    * 主键-skuId
    */
    private SkuInfoId skuId;
    /**
    * spuId
    */
    private Long spuId;
    /**
    * sku名称
    */
    private String skuName;
    /**
    * sku介绍描述
    */
    private String skuDesc;
    /**
    * 所属分类id
    */
    private Long catalogId;
    /**
    * 品牌id
    */
    private Long brandId;
    /**
    * 默认图片
    */
    private String skuDefaultImg;
    /**
    * 标题
    */
    private String skuTitle;
    /**
    * 副标题
    */
    private String skuSubtitle;
    /**
    * 价格
    */
    private BigDecimal price;
    /**
    * 销量
    */
    private Long saleCount;

    /**
     * 时间信息
     */
    private CommonInfo commonInfo;

    @Override
    public EntityId getUniqueLabel() {
        return this.skuId;
    }
}

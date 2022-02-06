package com.learn.project.mall.product.application.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * sku信息-数据传输对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Data
public class SkuInfoDto {

    /**
    * skuId
    */
    private Long skuId;
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

package com.learn.project.mall.product.infrastructure.persistence.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.learn.project.common.mybatis.util.BaseDo;
import lombok.Data;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
/**
 * sku信息-数据库对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
@TableName("pms_sku_info")
public class SkuInfoDo extends BaseDo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * skuId
    */
    @TableId
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

    @Override
    public void inUniqueLabel(Long uniqueLabel) {
        this.skuId = uniqueLabel;
    }

    @Override
    public Long outUniqueLabel() {
        return this.skuId;
    }
}

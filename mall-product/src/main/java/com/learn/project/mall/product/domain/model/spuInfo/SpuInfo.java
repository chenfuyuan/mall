package com.learn.project.mall.product.domain.model.spuInfo;

import com.learn.project.common.core.domain.Entity;
import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.common.core.domain.HaveCommonInfo;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * spu信息-领域对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Data
public class SpuInfo implements Entity<SpuInfo>, HaveCommonInfo {


    /**
    * 主键-商品id
    */
    private SpuInfoId id;
    /**
    * 商品名称
    */
    private String spuName;
    /**
    * 商品描述
    */
    private String spuDescription;
    /**
    * 所属分类id
    */
    private Long catalogId;
    /**
    * 品牌id
    */
    private Long brandId;
    /**
    * 
    */
    private BigDecimal weight;
    /**
    * 上架状态[0 - 下架，1 - 上架]
    */
    private Integer publishStatus;
    /**
    * 
    */
    private Date createTime;
    /**
    * 
    */
    private Date updateTime;

    /**
     * 时间信息
     */
    private CommonInfo commonInfo;

    @Override
    public EntityId getUniqueLabel() {
        return this.id;
    }
}

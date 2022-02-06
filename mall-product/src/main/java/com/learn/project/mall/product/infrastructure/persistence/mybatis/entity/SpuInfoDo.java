package com.learn.project.mall.product.infrastructure.persistence.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.learn.project.common.mybatis.util.BaseDo;
import lombok.Data;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
/**
 * spu信息-数据库对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Data
@TableName("pms_spu_info")
public class SpuInfoDo extends BaseDo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 商品id
    */
    @TableId
    private Long id;
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

    @Override
    public void inUniqueLabel(Long uniqueLabel) {
        this.id = uniqueLabel;
    }

    @Override
    public Long outUniqueLabel() {
        return this.id;
    }
}

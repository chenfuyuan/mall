package com.learn.project.mall.product.application.command;


import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * spu信息-命令对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
public class SpuInfoCommand {

    /**
    * 商品id
    */
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

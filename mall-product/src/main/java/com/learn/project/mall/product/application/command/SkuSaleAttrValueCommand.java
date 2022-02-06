package com.learn.project.mall.product.application.command;


import lombok.Data;
import java.util.Date;

/**
 * sku销售属性&值-命令对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
public class SkuSaleAttrValueCommand {

    /**
    * id
    */
    private Long id;
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

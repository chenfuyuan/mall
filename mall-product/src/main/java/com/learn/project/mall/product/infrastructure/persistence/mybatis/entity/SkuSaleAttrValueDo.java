package com.learn.project.mall.product.infrastructure.persistence.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.learn.project.common.mybatis.util.BaseDo;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
/**
 * sku销售属性&值-数据库对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Data
@TableName("pms_sku_sale_attr_value")
public class SkuSaleAttrValueDo extends BaseDo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
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

    @Override
    public void inUniqueLabel(Long uniqueLabel) {
        this.id = uniqueLabel;
    }

    @Override
    public Long outUniqueLabel() {
        return this.id;
    }
}

package com.learn.project.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import com.learn.project.common.utils.DateFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sku销售属性&值Entity
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-01-21 20:15:57
 */
@Data
@TableName("pms_sku_sale_attr_value")
public class SkuSaleAttrValueEntity implements Serializable {
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
	/**
	 * 是否删除[0-未删除, 1-删除]
	 */
	@TableLogic
    private Integer isDelete;
	/**
	 * 创建时间
	 */
    @DateFormat
    private Date gmtCreate;
	/**
	 * 修改时间
	 */
    @DateFormat
    private Date gmtModified;
	/**
	 * 更新版本
	 */
    private Integer updateVersion;

}

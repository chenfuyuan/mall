package com.learn.project.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import com.learn.project.common.utils.DateFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sku信息Entity
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-01-21 20:15:57
 */
@Data
@TableName("pms_sku_info")
public class SkuInfoEntity implements Serializable {
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

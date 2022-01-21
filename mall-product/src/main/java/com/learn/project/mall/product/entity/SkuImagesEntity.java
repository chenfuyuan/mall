package com.learn.project.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import com.learn.project.common.utils.DateFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sku图片Entity
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-01-21 20:15:57
 */
@Data
@TableName("pms_sku_images")
public class SkuImagesEntity implements Serializable {
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
	 * 图片地址
	 */
    private String imgUrl;
	/**
	 * 排序
	 */
    private Integer imgSort;
	/**
	 * 默认图[0 - 不是默认图，1 - 是默认图]
	 */
    private Integer defaultImg;
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

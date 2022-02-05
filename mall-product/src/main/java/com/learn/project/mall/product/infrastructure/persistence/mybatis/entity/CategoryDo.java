package com.learn.project.mall.product.infrastructure.persistence.mybatis.entity;



import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.learn.project.common.mybatis.util.BaseDo;
import com.learn.project.common.web.util.jsonSerializer.DateFormat;
import lombok.Data;

/**
 * 商品三级分类Entity
 * 
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2021-12-12 14:35:18
 */
@Data
@TableName("pms_category")
public class CategoryDo extends BaseDo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类id
	 */
	@TableId
	private Long catId;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 父分类id
	 */
	private Long parentCid;
	/**
	 * 层级
	 */
	private Integer catLevel;
	/**
	 * 是否显示[0-不显示，1显示]
	 */
	private Integer showStatus;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 图标地址
	 */
	private String icon;
	/**
	 * 计量单位
	 */
	private String productUnit;
	/**
	 * 商品数量
	 */
	private Integer productCount;

}

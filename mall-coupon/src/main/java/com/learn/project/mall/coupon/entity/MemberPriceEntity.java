package com.learn.project.mall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品会员价格Entity
 * 
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2021-12-22 16:26:49
 */
@Data
@TableName("sms_member_price")
public class MemberPriceEntity implements Serializable {
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
	 * 会员等级id
	 */
	private Long memberLevelId;
	/**
	 * 会员等级名
	 */
	private String memberLevelName;
	/**
	 * 会员对应价格
	 */
	private BigDecimal memberPrice;
	/**
	 * 可否叠加其他优惠[0-不可叠加优惠，1-可叠加]
	 */
	private Integer addOther;
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

}

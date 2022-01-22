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
 * spu信息Entity
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-01-21 20:15:57
 */
@Data
@TableName("pms_spu_info")
public class SpuInfoEntity implements Serializable {
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
	/**
	 * 是否删除[0-未删除, 1-删除]
	 */
	@TableLogic
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

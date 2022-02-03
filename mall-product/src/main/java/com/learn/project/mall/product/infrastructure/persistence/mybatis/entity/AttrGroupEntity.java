package com.learn.project.mall.product.infrastructure.persistence.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;


import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 属性分组Entity
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-01-21 20:15:57
 */
@Data
@TableName("pms_attr_group")
public class AttrGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分组id
	 */
    @TableId
    private Long attrGroupId;
	/**
	 * 组名
	 */
    private String attrGroupName;
	/**
	 * 排序
	 */
    private Integer sort;
	/**
	 * 描述
	 */
    private String descript;
	/**
	 * 组图标
	 */
    private String icon;
	/**
	 * 所属分类id
	 */
    private Long catelogId;

	@TableField(exist = false)
	private Long[] categoryPath;

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

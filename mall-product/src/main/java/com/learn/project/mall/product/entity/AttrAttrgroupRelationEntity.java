package com.learn.project.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import com.learn.project.common.utils.DateFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 属性&属性分组关联Entity
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-01-21 20:15:57
 */
@Data
@TableName("pms_attr_attrgroup_relation")
public class AttrAttrgroupRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
    @TableId
    private Long id;
	/**
	 * 属性id
	 */
    private Long attrId;
	/**
	 * 属性分组id
	 */
    private Long attrGroupId;
	/**
	 * 属性组内排序
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

package com.learn.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.learn.common.validation.group.SaveGroup;
import com.learn.common.validation.group.UpdateGroup;
import com.learn.common.validation.annotaion.Status;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 品牌
 * 
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2020-07-26 13:41:52
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {

	private static final long serialVersionUID = -7501603595059528753L;
	/**
	 * 品牌id
	 */
	@NotNull(message = "修改必须指定品牌id",groups = {UpdateGroup.class})
	@Null(message = "新增时不允许指定品牌id",groups = {SaveGroup.class})
	@TableId
	private Long brandId;

	/**
	 * 品牌名
	 */
	@NotBlank(message = "品牌名不能为空",groups = {SaveGroup.class})
	private String name;
	/**
	 * 品牌logo地址
	 */
	@NotBlank(message = "logo不能为空",groups = {SaveGroup.class})
	@URL(message = "上传的log地址不合法",groups = {SaveGroup.class,UpdateGroup.class})
	@NotBlank(message="logo不能为空2")
	private String logo;
	/**
	 * 介绍
	 */
	@NotBlank(message = "介绍不能为空",groups = {SaveGroup.class})
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	@NotNull(message = "显示状态不能为空",groups = {SaveGroup.class})
	@Status(groups = {SaveGroup.class, UpdateGroup.class})
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@NotBlank(message = "首字母不能为空",groups = {SaveGroup.class})
	@Pattern(regexp = "^[a-zA-Z]$",groups = {SaveGroup.class,UpdateGroup.class})
	private String firstLetter;
	/**
	 * 排序
	 */
	@NotNull(message = "排序不能为空",groups = {SaveGroup.class})
	@Min(value = 0,message = "排序必须为大于0的整数",groups = {SaveGroup.class,UpdateGroup.class})
	private Integer sort;

}

package com.learn.project.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.learn.project.common.valid.ListValue;
import com.learn.project.common.valid.NotEmptyString;
import com.learn.project.common.valid.group.Add;
import com.learn.project.common.valid.group.Update;
import com.learn.project.common.valid.group.UpdateShowStatus;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

/**
 * 品牌Entity
 * 
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2021-12-22 14:53:22
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@TableId
	@Null(message="品牌id必须为空!",groups = {Add.class})
	@NotNull(message = "品牌id不能为空!",groups = {Update.class,UpdateShowStatus.class})
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotBlank(message="品牌名称不能为空!",groups = {Add.class,Update.class})
	private String name;
	/**
	 * 品牌logo地址
	 */
	@NotBlank(message = "logo不能为空!",groups = {Add.class})
	@NotEmptyString(message = "logo不能为空!",groups=Update.class)
	@URL(message="logo必须为URL格式!",groups = {Add.class,Update.class})
	private String logo;
	/**
	 * 介绍
	 */
	@NotNull(message="介绍不能为空!",groups = {Add.class})
	@NotEmptyString(message="介绍不能为空!",groups = {Update.class})
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	@NotNull(message="显示状态不能为空!",groups = {Add.class,UpdateShowStatus.class})
	@ListValue(vals = {0,1},message = "显示状态必须为0或1!",groups = {Add.class,Update.class, UpdateShowStatus.class})
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@NotBlank(message = "检索首字母不能为空!",groups = {Add.class})
	@Pattern(regexp = "^[a-zA-Z]$",message = "检索首字母必须为字母且只能有一位!",groups = {Add.class,Update.class})
	private String firstLetter;
	/**
	 * 排序
	 */
	@NotNull(message = "排序不能为空!",groups = {Add.class})
	@Min(value = 0,message = "排序必须为正整数")
	private Integer sort;
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
	/**
	 * 更新版本
	 */
	@Null(message="添加时，更新版本必须为空!",groups = Add.class)
	private Integer updateVersion;

}

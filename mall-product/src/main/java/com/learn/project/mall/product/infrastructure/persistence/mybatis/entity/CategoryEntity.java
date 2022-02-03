package com.learn.project.mall.product.infrastructure.persistence.mybatis.entity;



import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.learn.project.common.web.util.jsonSerializer.DateFormat;
import com.learn.project.mall.product.infrastructure.constant.PmsConstant;
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
public class CategoryEntity implements Serializable {
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

	/**
	 * 子菜单列表
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@TableField(exist = false)
	private List<CategoryEntity> subCategorys;

	/**
	 * 获取一个排序比较器
	 * @return
	 */
	public static Comparator<CategoryEntity> getSortComparator(){
		return (category01, category02) -> {
			return (category01.getSort()==null?0:category01.getSort()) - (category02.getSort()==null?0: category02.getSort());
		};
	}


	/**
	 * 将线性结构列表 转换成 树形结构
	 * @param source 需要转化的数据
	 * @return
	 */
	public static List<CategoryEntity> listToTree(List<CategoryEntity> source) {
		//过滤出一级分类
		List<CategoryEntity> result = source.stream().filter(category ->
				category.getCatLevel() == PmsConstant.CATEGORT_LEVEL_ONE
		).map(category -> {
			//填充子分类
			category.setSubCategorys(buildSubCategorysByList(category, source));
			return category;
		}).sorted(CategoryEntity.getSortComparator()).collect(Collectors.toList());
		return result;
	}

	/**
	 * 根据传过来的source获取其中以root为父级分类的所有分类集合
	 * @param root 根节点
	 * @param source 所有分类列表
	 * @return
	 */
	private static List<CategoryEntity> buildSubCategorysByList(CategoryEntity root, List<CategoryEntity> source) {
		if (root == null) {
			return null;
		}

		return source.stream().filter(category ->
				root.getCatId().equals(category.getParentCid())
		).map(category -> {
			category.setSubCategorys(buildSubCategorysByList(category, source));
			return category;
		}).sorted(CategoryEntity.getSortComparator()).collect(Collectors.toList());
	}
}

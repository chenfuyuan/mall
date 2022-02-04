package com.learn.project.mall.product.domain.model.category;


import com.learn.project.common.core.constant.ShowStatusEnum;
import com.learn.project.common.core.domain.Entity;
import com.learn.project.mall.product.domain.model.product.ProductCount;
import com.learn.project.mall.product.infrastructure.constant.PmsConstant;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CategoryDO;
import com.uptool.core.constant.EmptyConstant;
import com.uptool.core.util.EmptyUtil;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分类对象
 *
 * @author chenfuyuan
 * @date 2022/2/3 23:39
 */
@Data
public class Category implements Entity<Category>,Comparable<Category> {

    /**
     * 唯一标识
     */
    private CategoryId categoryId;

    /**
     * 名字
     */
    private String name;

    /**
     * 父类分类
     */
    private Category parent;

    /**
     * 父类分类ID
     */
    private CategoryId parentId;

    /**
     * 分类等级
     */
    private CategoryLevelEnum catLevel;

    /**
     * 是否展示
     */
    private ShowStatusEnum showStatus;

    /**
     * 排序
     */
    private int sort;

    /**
     * 图标
     */
    private String icon;

    /**
     * 商品单位
     */
    private String productUnit;

    /**
     * 商品数量
     */
    private ProductCount productCount;

    /**
     * 子分类
     */
    private List<Category> subCategorys;

    public Category(CategoryId categoryId, String name, Category parent, CategoryLevelEnum catLevel, ShowStatusEnum showStatus, int sort, String icon, String productUnit, ProductCount productCount, List<Category> subCategorys) {
        this.categoryId = categoryId;
        this.name = name;
        this.parent = parent;
        this.catLevel = catLevel;
        this.showStatus = showStatus;
        this.sort = sort;
        this.icon = icon;
        this.productUnit = productUnit;
        this.productCount = productCount;
        this.subCategorys = subCategorys;
    }

    public Category() {

    }

    @Override
    public boolean sameIdentityAs(Category other) {
        return other != null && categoryId.sameValueAs(other.categoryId);
    }

    @Override
    public int compareTo(Category o) {
        return this.getSort() -  o.getSort();
    }

    /**
     * 将线性结构列表 转换成 树形结构
     * @param source 需要转化的数据
     * @return
     */
    public static List<Category> listToTree(List<Category> source) {
        if (EmptyUtil.isEmpty(source)) {
            return null;
        }

        //过滤出一级分类
        List<Category> result = source.stream().filter(category ->
                category.getCatLevel() == CategoryLevelEnum.ONE
        ).map(category -> {
            //填充子分类
            category.setSubCategorys(buildSubCategorysByList(category, source));
            return category;
        }).sorted().collect(Collectors.toList());
        return result;
    }

    /**
     * 从source资源中，查找对应的子分类，构建子分类列表
     * @param root 父分类
     * @param source 所能寻找的资源
     * @return
     */
    private static List<Category> buildSubCategorysByList(Category root, List<Category> source) {
        if (root==null || root.getCategoryId() == null) {
            return null;
        }

        return source.stream().filter(category ->
                //过滤出子分类
                root.getCategoryId().sameValueAs(category.getParentId())
        ).map(category -> {
            category.setParent(root);
            //递归寻找子分类
            category.setSubCategorys(buildSubCategorysByList(category, source));
            return category;
        }).collect(Collectors.toList());
    }
}

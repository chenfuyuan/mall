package com.learn.project.mall.product.domain.model.category;


import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.common.mybatis.util.BaseDo;
import com.learn.project.common.web.constant.ShowStatusEnum;
import com.learn.project.common.core.domain.Entity;
import com.learn.project.mall.product.domain.model.product.ProductCount;
import com.uptool.core.util.EmptyUtil;
import lombok.Data;

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
    private Integer sort;

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
     * 时间，版本等相关信息
     */
    private CommonInfo timeInfo;

    /**
     * 子分类
     */
    private List<Category> subCategorys;

    public Category() {

    }


    @Override
    public boolean sameIdentityAs(Category other) {
        return other != null && categoryId.sameValueAs(other.categoryId);
    }

    @Override
    public EntityId getUniqueLabel() {
        return this.categoryId;
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
        }).sorted().collect(Collectors.toList());
    }

    public void setTimeInfo(CommonInfo timeInfo) {
        this.timeInfo = timeInfo;
    }

    public void setTimeInfo(BaseDo baseDo) {
        this.timeInfo = BaseDo.getCommonInfo(baseDo);
    }

}

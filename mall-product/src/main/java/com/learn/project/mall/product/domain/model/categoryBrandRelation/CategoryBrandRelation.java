package com.learn.project.mall.product.domain.model.categoryBrandRelation;

import com.learn.project.common.core.domain.Entity;
import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.common.core.domain.HaveCommonInfo;
import lombok.Data;
import java.util.Date;

/**
 * 品牌分类关联-领域对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Data
public class CategoryBrandRelation implements Entity<CategoryBrandRelation>, HaveCommonInfo {


    /**
    * 主键-
    */
    private CategoryBrandRelationId id;
    /**
    * 品牌id
    */
    private Long brandId;
    /**
    * 分类id
    */
    private Long catelogId;
    /**
    * 
    */
    private String brandName;
    /**
    * 
    */
    private String catelogName;

    /**
     * 时间信息
     */
    private CommonInfo commonInfo;

    @Override
    public EntityId getUniqueLabel() {
        return this.id;
    }
}

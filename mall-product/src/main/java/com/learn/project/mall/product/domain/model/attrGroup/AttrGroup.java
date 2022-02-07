package com.learn.project.mall.product.domain.model.attrGroup;

import com.learn.project.common.core.domain.Entity;
import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.common.core.domain.HaveCommonInfo;
import com.learn.project.mall.product.domain.model.category.CategoryPath;
import lombok.Data;
import java.util.Date;

/**
 * 属性分组-领域对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
public class AttrGroup implements Entity<AttrGroup>, HaveCommonInfo {


    /**
    * 主键-分组id
    */
    private AttrGroupId attrGroupId;
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

    /**
     * 时间信息
     */
    private CommonInfo commonInfo;

    /**
     * 所属分类路径
     */
    private CategoryPath categoryPath;

    @Override
    public EntityId getUniqueLabel() {
        return this.attrGroupId;
    }
}

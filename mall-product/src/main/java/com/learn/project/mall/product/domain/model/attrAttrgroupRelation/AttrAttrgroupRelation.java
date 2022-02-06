package com.learn.project.mall.product.domain.model.attrAttrgroupRelation;

import com.learn.project.common.core.domain.Entity;
import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.common.core.domain.HaveCommonInfo;
import lombok.Data;
import java.util.Date;

/**
 * 属性&属性分组关联-领域对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
public class AttrAttrgroupRelation implements Entity<AttrAttrgroupRelation>, HaveCommonInfo {


    /**
    * 主键-id
    */
    private AttrAttrgroupRelationId id;
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
     * 时间信息
     */
    private CommonInfo commonInfo;

    @Override
    public EntityId getUniqueLabel() {
        return this.id;
    }
}

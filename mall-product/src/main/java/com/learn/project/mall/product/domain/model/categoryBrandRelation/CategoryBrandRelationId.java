package com.learn.project.mall.product.domain.model.categoryBrandRelation;

import com.learn.project.common.core.domain.EntityId;
import com.learn.project.common.web.exception.NoBizException;
import com.uptool.core.util.EmptyUtil;


/**
 * 品牌分类关联-领域对象-id
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public class CategoryBrandRelationId implements EntityId<CategoryBrandRelationId> {

    private final Long id;


    public CategoryBrandRelationId(final Long id) {
        if (EmptyUtil.isEmpty(id)) {
            throw new NoBizException("不能为空!");
        }
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}

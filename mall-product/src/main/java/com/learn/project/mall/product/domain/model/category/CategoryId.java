package com.learn.project.mall.product.domain.model.category;

import com.learn.project.common.core.domain.ValueObject;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.NumberUtil;
import org.apache.commons.lang3.StringUtils;

import java.security.Permission;

/**
 *
 * @author chenfuyuan
 * @date 2022/2/3 23:43
 */
public class CategoryId implements ValueObject<CategoryId> {

    private Long id;


    public CategoryId(final Long id) {
        if (EmptyUtil.isEmpty(id)) {
            throw new IllegalArgumentException("分类Id不能为空");
        }
        this.id = id;
    }

    @Override
    public boolean sameValueAs(CategoryId other) {
        return other != null && NumberUtil.isEqual(this.id,other.id);
    }

    @Override
    public String toString() {
        return id+"";
    }

    public Long getId() {
        return id;
    }
}

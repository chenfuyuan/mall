package com.learn.project.mall.product.domain.model.category;

import com.learn.project.common.core.domain.ValueObject;
import com.learn.project.common.web.exception.NoBizException;
import com.uptool.core.able.EmptyAble;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.NumberUtil;
import lombok.Getter;

import java.util.Objects;

/**
 * 分类路径
 * @author chenfuyuan
 * @date 2022/2/4 16:40
 */
@Getter
public class CategoryPath implements ValueObject<CategoryPath>, EmptyAble {

    private final Long[] path;

    public CategoryPath(Long[] path) {
        if (path == null) {
            throw new NoBizException("创建分类路径对象时失败，分类路径不能为空!");
        }
        this.path = path;
    }

    @Override
    public boolean isEmpty() {
        return path == null || path.length == 0;
    }

    @Override
    public boolean sameValueAs(CategoryPath other) {
        if (other == null || EmptyUtil.isEmpty(other.getPath())) {
            return false;
        }

        int forLength = Math.min(this.getPath().length, other.getPath().length);

        for (int i = 0; i < forLength; i++) {
            if (!NumberUtil.isEqual(this.path[i], other.path[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CategoryPath) {
            return sameValueAs((CategoryPath) obj);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (Long value : path) {
            hashCode += Objects.hashCode(value);
        }
        return hashCode;
    }
}

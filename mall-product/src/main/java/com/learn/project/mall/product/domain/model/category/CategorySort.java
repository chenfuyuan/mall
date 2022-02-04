package com.learn.project.mall.product.domain.model.category;

import com.learn.project.common.core.domain.ValueObject;
import lombok.Data;
import lombok.Getter;
import org.apache.logging.log4j.util.PropertySource;

import java.util.Comparator;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/2/4 0:13
 */
@Getter
public class CategorySort implements ValueObject<CategorySort> {

    private int sort;

    public CategorySort(int sort) {
        this.sort = sort;
    }

    public CategorySort(Integer sort) {
        this.sort = sort == null ? 0 : sort;
    }

    @Override
    public boolean sameValueAs(CategorySort other) {
        return other != null && other.sort == this.sort;
    }
}

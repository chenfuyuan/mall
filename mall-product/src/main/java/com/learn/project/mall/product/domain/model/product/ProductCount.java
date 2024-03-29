package com.learn.project.mall.product.domain.model.product;

import com.learn.project.common.core.domain.ValueObject;
import com.learn.project.common.web.exception.BizException;
import lombok.Getter;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/2/4 0:48
 */
@Getter
public class ProductCount implements ValueObject<ProductCount> {
    private int count;

    public ProductCount(Integer count) {
        if (count == null) {
            count = Integer.valueOf(0);
        }
        if (count < 0) {
            throw new BizException("商品数量不能小于0!");
        }
        this.count = count;
    }



    @Override
    public boolean sameValueAs(ProductCount other) {
        return other != null && other.count == count;
    }
}

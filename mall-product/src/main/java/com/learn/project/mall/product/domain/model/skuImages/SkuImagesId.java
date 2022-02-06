package com.learn.project.mall.product.domain.model.skuImages;

import com.learn.project.common.core.domain.EntityId;
import com.learn.project.common.web.exception.NoBizException;
import com.uptool.core.util.EmptyUtil;


/**
 * sku图片-领域对象-id
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public class SkuImagesId implements EntityId<SkuImagesId> {

    private final Long id;


    public SkuImagesId(final Long id) {
        if (EmptyUtil.isEmpty(id)) {
            throw new NoBizException("id不能为空!");
        }
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}

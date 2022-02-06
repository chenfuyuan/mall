package com.learn.project.mall.product.domain.model.skuInfo;

import com.learn.project.common.core.domain.EntityId;
import com.learn.project.common.web.exception.NoBizException;
import com.uptool.core.util.EmptyUtil;


/**
 * sku信息-领域对象-id
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class SkuInfoId implements EntityId<SkuInfoId> {

    private final Long id;


    public SkuInfoId(final Long id) {
        if (EmptyUtil.isEmpty(id)) {
            throw new NoBizException("skuId不能为空!");
        }
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}

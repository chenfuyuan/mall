package com.learn.project.mall.product.domain.model.attr;

import com.learn.project.common.core.domain.EntityId;
import com.learn.project.common.web.exception.NoBizException;
import com.uptool.core.util.EmptyUtil;


/**
 * 商品属性-领域对象-id
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class AttrId implements EntityId<AttrId> {

    private final Long id;


    public AttrId(final Long id) {
        if (EmptyUtil.isEmpty(id)) {
            throw new NoBizException("属性id不能为空!");
        }
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}

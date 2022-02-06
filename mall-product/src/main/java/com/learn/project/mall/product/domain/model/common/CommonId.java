package com.learn.project.mall.product.domain.model.common;

import com.learn.project.common.core.domain.EntityId;
import com.learn.project.common.web.exception.NoBizException;
import com.uptool.core.util.EmptyUtil;


/**
 * 通用测试-领域对象-id
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public class CommonId implements EntityId<CommonId> {

    private final Long id;


    public CommonId(final Long id) {
        if (EmptyUtil.isEmpty(id)) {
            throw new NoBizException("通用id不能为空!");
        }
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}

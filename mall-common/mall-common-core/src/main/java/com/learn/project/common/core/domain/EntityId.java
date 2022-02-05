package com.learn.project.common.core.domain;

import com.uptool.core.util.NumberUtil;

/**
 * 领域对象唯一标识
 *
 * @author chenfuyuan
 * @date 2022/2/5 16:39
 */
public interface EntityId<T extends EntityId> extends ValueObject<T>{

    Long getId();

    @Override
    default boolean sameValueAs(T other){
        return other!=null && NumberUtil.isEqual(other.getId(),getId());
    }

    static Long getId(EntityId entityId) {
        return entityId == null ? null : entityId.getId();
    }
}

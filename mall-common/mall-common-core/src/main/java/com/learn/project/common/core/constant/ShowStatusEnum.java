package com.learn.project.common.core.constant;

import com.learn.project.common.core.domain.ValueObject;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/2/4 0:42
 */
public enum ShowStatusEnum implements ValueObject<ShowStatusEnum> {

    SHOW(1),
    HIDE(0);

    private int value;

    ShowStatusEnum(int value) {
        this.value = value;
    }

    public ShowStatusEnum getShowStatusEnum(int value) {
        return value == SHOW.value ? SHOW : HIDE;
    }


    @Override
    public boolean sameValueAs(ShowStatusEnum other) {
        return this.equals(other);
    }
}

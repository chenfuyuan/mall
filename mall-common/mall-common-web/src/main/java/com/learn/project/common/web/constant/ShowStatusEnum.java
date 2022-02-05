package com.learn.project.common.web.constant;

import com.learn.project.common.core.domain.ValueObject;
import com.learn.project.common.web.exception.BizException;

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

    public static ShowStatusEnum getShowStatusEnum(Integer value) {
        if (value == null) {
            throw new BizException("创建显示状态时错误，显示状态为空!");
        }
        return value == SHOW.value ? SHOW : HIDE;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public boolean sameValueAs(ShowStatusEnum other) {
        return this.equals(other);
    }
}

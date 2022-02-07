package com.learn.project.mall.product.domain.model.category;

import com.learn.project.common.core.domain.ValueObject;
import com.learn.project.common.web.exception.BizException;

import javax.sound.midi.Track;
import java.util.List;


/**
 *
 * @author chenfuyuan
 * @date 2022/2/4 0:33
 */
public enum CategoryLevelEnum implements ValueObject<CategoryLevelEnum> {

    ONE(1),
    TWO(2),
    THREE(3);

    private int value;


    CategoryLevelEnum(int value) {
        this.value = value;
    }

    public static CategoryLevelEnum getCategoryLevelEnum(Integer value) {
        if (value == null) {
            return null;
        }
        for (CategoryLevelEnum level : CategoryLevelEnum.values()) {
            if (value == level.value) {
                return level;
            }
        }
        throw new BizException("获取分类菜单失败，菜单等级错误!");
    }

    @Override
    public boolean sameValueAs(CategoryLevelEnum other) {
        return this.equals(other);
    }

    public int getValue() {
        return this.value;
    }

    public static int getMinLevel() {
        return ONE.getValue();
    }

    public static int getMaxLevel() {
        return THREE.getValue();
    }

    public static boolean isRight(int value) {
        return value >= getMinLevel() && value <= getMaxLevel();
    }
}

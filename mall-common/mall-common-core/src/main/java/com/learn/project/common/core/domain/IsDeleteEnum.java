package com.learn.project.common.core.domain;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/2/5 0:47
 */
public enum IsDeleteEnum implements ValueObject<IsDeleteEnum>{
    DELETE(1,"删除"),
    NORMAL(0,"正常");

    private int value;
    private String msg;

    IsDeleteEnum(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }

    public static IsDeleteEnum getEnum(Integer value) {
        if (value == null) {
            value = 0;
        }

        return value == DELETE.value ? DELETE : NORMAL;
    }

    public static Integer getValue(IsDeleteEnum isDeleteEnum){
        return isDeleteEnum == null ? null : isDeleteEnum.getValue();
    }

    @Override
    public boolean sameValueAs(IsDeleteEnum other) {
        return other != null && other == this;
    }
}

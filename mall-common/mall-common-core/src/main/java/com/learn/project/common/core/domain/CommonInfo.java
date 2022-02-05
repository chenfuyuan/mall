package com.learn.project.common.core.domain;

import lombok.Data;

import java.util.Date;

/**
 * 时间信息
 *
 * @author chenfuyuan
 * @date 2022/2/5 0:45
 */
@Data
public class CommonInfo implements ValueObject<CommonInfo>{

    private Date createTime;

    private Date updateTime;

    private IsDeleteEnum isDeleteEnum;

    private Integer version;

    public CommonInfo(Date createTime, Date updateTime, IsDeleteEnum isDeleteEnum, Integer version) {
        this.createTime = createTime;
        this.updateTime = updateTime;
        if (isDeleteEnum == null) {
            isDeleteEnum = IsDeleteEnum.NORMAL;
        }
        this.isDeleteEnum = isDeleteEnum;
        this.version = version;
    }

    public static CommonInfo getDefault(){
        return new CommonInfo(new Date(), new Date(), IsDeleteEnum.NORMAL, 0);
    }

    public static CommonInfo getInstance(Date createTime, Date updateTime, Integer isDelete, Integer version) {
        return new CommonInfo(createTime, updateTime, IsDeleteEnum.getEnum(isDelete), version);
    }


    @Override
    public boolean sameValueAs(CommonInfo other) {
        return false;
    }
}

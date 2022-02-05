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
public class TimeInfo implements ValueObject<TimeInfo>{

    private Date createTime;

    private Date updateTime;

    private IsDeleteEnum isDeleteEnum;

    private Integer version;

    public TimeInfo(Date createTime, Date updateTime, IsDeleteEnum isDeleteEnum, Integer version) {
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isDeleteEnum = isDeleteEnum;
        this.version = version;
    }

    public static TimeInfo getDefault(){
        return new TimeInfo(new Date(), new Date(), IsDeleteEnum.NORMAL, 0);
    }

    public static TimeInfo getInstance(Date createTime, Date updateTime, Integer isDelete, Integer version) {
        return new TimeInfo(createTime, updateTime, IsDeleteEnum.getEnum(isDelete), version);
    }

    @Override
    public boolean sameValueAs(TimeInfo other) {
        return false;
    }
}

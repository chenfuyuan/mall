package com.learn.project.common.mybatis.util;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.learn.project.common.core.domain.IsDeleteEnum;
import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.web.util.jsonSerializer.DateFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 *
 *
 * @author chenfuyuan
 * @date 2022/2/3 23:31
 */
@Data
public abstract class BaseDo implements Serializable {

    /**
     * 是否删除[0-未删除, 1-删除]
     */
    @TableLogic
    private Integer isDelete;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 更新版本
     */
    private Integer updateVersion;

    public static CommonInfo getCommonInfo(BaseDo baseDo) {
        if (baseDo == null) {
            return null;
        }
        return new CommonInfo(baseDo.gmtCreate, baseDo.gmtModified, IsDeleteEnum.getEnum(baseDo.isDelete), baseDo.updateVersion);
    }

    public abstract void inUniqueLabel(Long uniqueLabel);

    public abstract Long outUniqueLabel();

}

package com.learn.project.common.mybatis.util;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
public class BaseDO implements Serializable {

    /**
     * 是否删除[0-未删除, 1-删除]
     */
    @TableLogic
    private Integer isDelete;
    /**
     * 创建时间
     */
    @DateFormat
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @DateFormat
    private Date gmtModified;
    /**
     * 更新版本
     */
    private Integer updateVersion;

}

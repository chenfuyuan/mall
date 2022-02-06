package com.learn.project.mall.product.infrastructure.persistence.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.learn.project.common.mybatis.util.BaseDo;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
/**
 * 通用测试-数据库对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Data
@TableName("pms_common")
public class CommonDo extends BaseDo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 通用id
    */
    @TableId
    private Long commonId;
    /**
    * 名称
    */
    private String name;

    @Override
    public void inUniqueLabel(Long uniqueLabel) {
        this.commonId = uniqueLabel;
    }

    @Override
    public Long outUniqueLabel() {
        return this.commonId;
    }
}

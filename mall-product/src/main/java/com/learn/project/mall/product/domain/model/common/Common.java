package com.learn.project.mall.product.domain.model.common;

import com.learn.project.common.core.domain.Entity;
import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.common.core.domain.HaveCommonInfo;
import lombok.Data;
import java.util.Date;

/**
 * 通用测试-领域对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Data
public class Common implements Entity<Common>, HaveCommonInfo {


    /**
    * 主键-通用id
    */
    private CommonId commonId;
    /**
    * 名称
    */
    private String name;

    /**
     * 时间信息
     */
    private CommonInfo commonInfo;

    @Override
    public EntityId getUniqueLabel() {
        return this.commonId;
    }
}

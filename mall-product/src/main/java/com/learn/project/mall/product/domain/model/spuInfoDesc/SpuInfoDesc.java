package com.learn.project.mall.product.domain.model.spuInfoDesc;

import com.learn.project.common.core.domain.Entity;
import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.common.core.domain.HaveCommonInfo;
import lombok.Data;
import java.util.Date;

/**
 * spu信息介绍-领域对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
public class SpuInfoDesc implements Entity<SpuInfoDesc>, HaveCommonInfo {


    /**
    * 主键-商品id
    */
    private SpuInfoDescId spuId;
    /**
    * 商品介绍
    */
    private String decript;

    /**
     * 时间信息
     */
    private CommonInfo commonInfo;

    @Override
    public EntityId getUniqueLabel() {
        return this.spuId;
    }
}

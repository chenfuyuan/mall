package com.learn.project.mall.product.domain.model.skuImages;

import com.learn.project.common.core.domain.Entity;
import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.common.core.domain.HaveCommonInfo;
import lombok.Data;
import java.util.Date;

/**
 * sku图片-领域对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
public class SkuImages implements Entity<SkuImages>, HaveCommonInfo {


    /**
    * 主键-id
    */
    private SkuImagesId id;
    /**
    * sku_id
    */
    private Long skuId;
    /**
    * 图片地址
    */
    private String imgUrl;
    /**
    * 排序
    */
    private Integer imgSort;
    /**
    * 默认图[0 - 不是默认图，1 - 是默认图]
    */
    private Integer defaultImg;

    /**
     * 时间信息
     */
    private CommonInfo commonInfo;

    @Override
    public EntityId getUniqueLabel() {
        return this.id;
    }
}

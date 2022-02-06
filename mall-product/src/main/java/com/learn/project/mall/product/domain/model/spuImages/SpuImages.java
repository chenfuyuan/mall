package com.learn.project.mall.product.domain.model.spuImages;

import com.learn.project.common.core.domain.Entity;
import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.common.core.domain.HaveCommonInfo;
import lombok.Data;
import java.util.Date;

/**
 * spu图片-领域对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
public class SpuImages implements Entity<SpuImages>, HaveCommonInfo {


    /**
    * 主键-id
    */
    private SpuImagesId id;
    /**
    * spu_id
    */
    private Long spuId;
    /**
    * 图片名
    */
    private String imgName;
    /**
    * 图片地址
    */
    private String imgUrl;
    /**
    * 顺序
    */
    private Integer imgSort;
    /**
    * 是否默认图
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

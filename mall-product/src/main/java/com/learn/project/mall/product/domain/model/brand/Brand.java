package com.learn.project.mall.product.domain.model.brand;

import com.learn.project.common.core.domain.Entity;
import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.common.core.domain.HaveCommonInfo;
import lombok.Data;
import java.util.Date;

/**
 * 品牌-领域对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
public class Brand implements Entity<Brand>, HaveCommonInfo {


    /**
    * 主键-品牌id
    */
    private BrandId brandId;
    /**
    * 品牌名
    */
    private String name;
    /**
    * 品牌logo地址
    */
    private String logo;
    /**
    * 介绍
    */
    private String descript;
    /**
    * 显示状态[0-不显示；1-显示]
    */
    private Integer showStatus;
    /**
    * 检索首字母
    */
    private String firstLetter;
    /**
    * 排序
    */
    private Integer sort;

    /**
     * 时间信息
     */
    private CommonInfo commonInfo;

    @Override
    public EntityId getUniqueLabel() {
        return this.brandId;
    }
}

package com.learn.project.mall.product.infrastructure.persistence.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.learn.project.common.mybatis.util.BaseDo;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
/**
 * 品牌-数据库对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Data
@TableName("pms_brand")
public class BrandDo extends BaseDo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 品牌id
    */
    @TableId
    private Long brandId;
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

    @Override
    public void inUniqueLabel(Long uniqueLabel) {
        this.brandId = uniqueLabel;
    }

    @Override
    public Long outUniqueLabel() {
        return this.brandId;
    }
}

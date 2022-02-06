package com.learn.project.mall.product.infrastructure.persistence.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.learn.project.common.mybatis.util.BaseDo;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
/**
 * sku图片-数据库对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
@TableName("pms_sku_images")
public class SkuImagesDo extends BaseDo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;
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

    @Override
    public void inUniqueLabel(Long uniqueLabel) {
        this.id = uniqueLabel;
    }

    @Override
    public Long outUniqueLabel() {
        return this.id;
    }
}

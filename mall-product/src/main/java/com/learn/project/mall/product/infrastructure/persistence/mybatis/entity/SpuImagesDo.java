package com.learn.project.mall.product.infrastructure.persistence.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.learn.project.common.mybatis.util.BaseDo;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
/**
 * spu图片-数据库对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Data
@TableName("pms_spu_images")
public class SpuImagesDo extends BaseDo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;
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

    @Override
    public void inUniqueLabel(Long uniqueLabel) {
        this.id = uniqueLabel;
    }

    @Override
    public Long outUniqueLabel() {
        return this.id;
    }
}

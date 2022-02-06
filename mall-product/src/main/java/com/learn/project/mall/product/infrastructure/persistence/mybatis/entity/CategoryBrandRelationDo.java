package com.learn.project.mall.product.infrastructure.persistence.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.learn.project.common.mybatis.util.BaseDo;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
/**
 * 品牌分类关联-数据库对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Data
@TableName("pms_category_brand_relation")
public class CategoryBrandRelationDo extends BaseDo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 
    */
    @TableId
    private Long id;
    /**
    * 品牌id
    */
    private Long brandId;
    /**
    * 分类id
    */
    private Long catelogId;
    /**
    * 
    */
    private String brandName;
    /**
    * 
    */
    private String catelogName;

    @Override
    public void inUniqueLabel(Long uniqueLabel) {
        this.id = uniqueLabel;
    }

    @Override
    public Long outUniqueLabel() {
        return this.id;
    }
}

package com.learn.project.mall.product.infrastructure.persistence.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.learn.project.common.mybatis.util.BaseDo;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
/**
 * spu信息介绍-数据库对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
@TableName("pms_spu_info_desc")
public class SpuInfoDescDo extends BaseDo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 商品id
    */
    @TableId
    private Long spuId;
    /**
    * 商品介绍
    */
    private String decript;

    @Override
    public void inUniqueLabel(Long uniqueLabel) {
        this.spuId = uniqueLabel;
    }

    @Override
    public Long outUniqueLabel() {
        return this.spuId;
    }
}

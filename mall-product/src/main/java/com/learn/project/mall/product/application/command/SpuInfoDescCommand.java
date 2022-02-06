package com.learn.project.mall.product.application.command;


import lombok.Data;
import java.util.Date;

/**
 * spu信息介绍-命令对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
public class SpuInfoDescCommand {

    /**
    * 商品id
    */
    private Long spuId;
    /**
    * 商品介绍
    */
    private String decript;
    /**
    * 是否删除[0-未删除, 1-删除]
    */
    private Integer isDelete;
    /**
    * 创建时间
    */
    private Date gmtCreate;
    /**
    * 修改时间
    */
    private Date gmtModified;
    /**
    * 更新版本
    */
    private Integer updateVersion;
}

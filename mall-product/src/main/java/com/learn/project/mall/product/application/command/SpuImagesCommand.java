package com.learn.project.mall.product.application.command;


import lombok.Data;
import java.util.Date;

/**
 * spu图片-命令对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
public class SpuImagesCommand {

    /**
    * id
    */
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

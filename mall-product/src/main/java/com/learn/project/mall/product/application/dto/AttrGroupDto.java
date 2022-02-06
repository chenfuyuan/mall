package com.learn.project.mall.product.application.dto;

import lombok.Data;

import java.util.Date;

/**
 * 属性分组-数据传输对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Data
public class AttrGroupDto {

    /**
    * 分组id
    */
    private Long attrGroupId;
    /**
    * 组名
    */
    private String attrGroupName;
    /**
    * 排序
    */
    private Integer sort;
    /**
    * 描述
    */
    private String descript;
    /**
    * 组图标
    */
    private String icon;
    /**
    * 所属分类id
    */
    private Long catelogId;
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

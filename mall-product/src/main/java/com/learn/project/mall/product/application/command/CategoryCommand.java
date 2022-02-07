package com.learn.project.mall.product.application.command;

import com.learn.project.common.web.util.valid.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 分类Command类
 *
 * @author chenfuyuan
 * @date 2022/2/4 17:46
 */
@Data
public class CategoryCommand {
    /**
     * 分类id
     */
    @NotNull(groups = UpdateGroup.class,message = "分类Id不能为空!")
    private Long catId;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 父分类id
     */
    private Long parentCid;
    /**
     * 层级
     */
    private Integer catLevel;
    /**
     * 是否显示[0-不显示，1显示]
     */
    private Integer showStatus;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 图标地址
     */
    private String icon;
    /**
     * 计量单位
     */
    private String productUnit;
    /**
     * 商品数量
     */
    private Integer productCount;

    /**
     * 是否删除
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

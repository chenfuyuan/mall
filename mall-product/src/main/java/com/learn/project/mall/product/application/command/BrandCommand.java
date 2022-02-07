package com.learn.project.mall.product.application.command;


import com.baomidou.mybatisplus.annotation.TableId;
import com.learn.project.common.web.util.valid.ListValue;
import com.learn.project.common.web.util.valid.NotEmptyString;
import com.learn.project.common.web.util.valid.group.AddGroup;
import com.learn.project.common.web.util.valid.group.UpdateGroup;
import com.learn.project.common.web.util.valid.group.UpdateShowStatusGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 品牌-命令对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
public class BrandCommand {
    /**
     * 品牌id
     */
    @TableId
    @Null(message="品牌id必须为空!",groups = {AddGroup.class})
    @NotNull(message = "品牌id不能为空!",groups = {UpdateGroup.class, UpdateShowStatusGroup.class})
    private Long brandId;
    /**
     * 品牌名
     */
    @NotBlank(message="品牌名称不能为空!",groups = {AddGroup.class,UpdateGroup.class})
    private String name;
    /**
     * 品牌logo地址
     */
    @NotBlank(message = "logo不能为空!",groups = {AddGroup.class})
    @NotEmptyString(message = "logo不能为空!",groups=UpdateGroup.class)
    @URL(message="logo必须为URL格式!",groups = {AddGroup.class,UpdateGroup.class})
    private String logo;
    /**
     * 介绍
     */
    @NotNull(message="介绍不能为空!",groups = {AddGroup.class})
    @NotEmptyString(message="介绍不能为空!",groups = {UpdateGroup.class})
    private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
    @NotNull(message="显示状态不能为空!",groups = {AddGroup.class,UpdateShowStatusGroup.class})
    @ListValue(vals = {0,1},message = "显示状态必须为0或1!",groups = {AddGroup.class,UpdateGroup.class, UpdateShowStatusGroup.class})
    private Integer showStatus;
    /**
     * 检索首字母
     */
    @NotBlank(message = "检索首字母不能为空!",groups = {AddGroup.class})
    @Pattern(regexp = "^[a-zA-Z]$",message = "检索首字母必须为字母且只能有一位!",groups = {AddGroup.class,UpdateGroup.class})
    private String firstLetter;
    /**
     * 排序
     */
    @NotNull(message = "排序不能为空!",groups = {AddGroup.class})
    @Min(value = 0,message = "排序必须为正整数")
    private Integer sort;
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
    @Null(message="添加时，更新版本必须为空!",groups = AddGroup.class)
    private Integer updateVersion;
}

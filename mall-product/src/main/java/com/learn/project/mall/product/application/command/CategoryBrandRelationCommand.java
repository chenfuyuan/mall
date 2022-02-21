package com.learn.project.mall.product.application.command;


import com.learn.project.common.web.util.valid.group.AddGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 品牌分类关联-命令对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
public class CategoryBrandRelationCommand {

    /**
    * 品牌id
    */
    @NotNull(groups = {AddGroup.class},message = "新增品牌分类关联时，品牌Id不能为空!")
    private Long brandId;
    /**
    * 分类id
    */
    @NotNull(groups = {AddGroup.class},message = "新增品牌分类关联时，分类Id不能为空!")
    private Long catelogId;
}

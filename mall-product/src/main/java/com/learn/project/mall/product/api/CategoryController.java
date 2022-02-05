package com.learn.project.mall.product.api;

import java.util.Arrays;
import java.util.List;

import com.learn.project.common.web.util.valid.ValidatorUtil;
import com.learn.project.common.web.util.valid.group.AddGroup;
import com.learn.project.mall.product.application.CategoryCommandService;
import com.learn.project.mall.product.application.command.CategoryCommand;
import com.learn.project.mall.product.application.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CategoryDo;
import com.learn.project.mall.product.application.CategoryQueryService;
import com.learn.project.common.web.util.R;




/**
 * 商品三级分类Controller
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2021-12-12 14:35:18
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private CategoryQueryService categoryQueryService;

    @Autowired
    private CategoryCommandService categoryCommandService;

    /**
     * 获取当前分类及其子分类。并将其组合成树状结构进行返回
     */
    @RequestMapping("/list/tree")
    public R listByTree(){
        List<CategoryDto> listByTree = categoryQueryService.queryAllToTree();
        return R.ok().put("data", listByTree);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    public R info(@PathVariable("catId") Long catId){
		CategoryDto category = categoryQueryService.getById(catId);
        return R.ok().put("category", category);
    }

    @RequestMapping("/path/{catId}")
    public R findPath(@PathVariable("catId") Long catId) {
        Long[] path = categoryQueryService.findCategoryPathByCatId(catId);
        return R.ok().put("path", path);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CategoryCommand category){
        ValidatorUtil.validateEntity(category, AddGroup.class);
        categoryCommandService.saveOrUpdate(category);
        //返回添加成功后的catId
        return R.ok().put("saveCatId",category.getCatId());
    }

    @RequestMapping("/batch/update")
    public R update(@RequestBody List<CategoryCommand> categoryCommandList){
        categoryCommandService.saveOrUpdate(categoryCommandList);
        return R.ok();
    }
    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CategoryCommand categoryCommand){
        categoryCommandService.saveOrUpdate(categoryCommand);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] catIds){
        //根据业务要求，删除指定分类
        boolean result = categoryCommandService.removeCategoryByIds(Arrays.asList(catIds));
        return result ? R.ok() : R.error("删除失败!");
    }

}

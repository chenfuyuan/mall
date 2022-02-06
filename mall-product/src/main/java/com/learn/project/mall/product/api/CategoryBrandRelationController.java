package com.learn.project.mall.product.api;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.web.util.R;
import com.learn.project.common.web.util.valid.ValidatorUtil;
import com.learn.project.common.web.util.valid.group.AddGroup;
import com.learn.project.common.web.util.valid.group.UpdateGroup;

import com.learn.project.mall.product.application.CategoryBrandRelationCommandService;
import com.learn.project.mall.product.application.CategoryBrandRelationQueryService;
import com.learn.project.mall.product.application.command.CategoryBrandRelationCommand;
import com.learn.project.mall.product.application.dto.CategoryBrandRelationDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联-Controller
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationQueryService categoryBrandRelationQueryService;

    @Autowired
    private CategoryBrandRelationCommandService categoryBrandRelationCommandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryBrandRelationQueryService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CategoryBrandRelationDto categoryBrandRelationDto = categoryBrandRelationQueryService.getById(id);
        return R.ok().put("categoryBrandRelation", categoryBrandRelationDto);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CategoryBrandRelationCommand categoryBrandRelationCommand){
        ValidatorUtil.validateEntity(categoryBrandRelationCommand, AddGroup.class);
        Long saveId = categoryBrandRelationCommandService.saveOrUpdate(categoryBrandRelationCommand);
        return R.ok().put("saveId", saveId);
    }
    
    /**
     * 批量保存
     */
    @RequestMapping("/batchSave")
    public R batchSave(@RequestBody List<CategoryBrandRelationCommand> categoryBrandRelationCommandList) {
        ValidatorUtil.validateEntity(categoryBrandRelationCommandList, AddGroup.class);
        Long[] saveIds = categoryBrandRelationCommandService.batchSaveOrUpdate(categoryBrandRelationCommandList);
        return R.ok().put("saveIds", saveIds);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CategoryBrandRelationCommand categoryBrandRelationCommand){
        ValidatorUtil.validateEntity(categoryBrandRelationCommand, UpdateGroup.class);
        Long updateId = categoryBrandRelationCommandService.saveOrUpdate(categoryBrandRelationCommand);
        return R.ok().put("updateId", updateId);
    }

    /**
     * 批量修改
     */
    @RequestMapping("/batchUpdate")
    public R batchUpdate(@RequestBody List<CategoryBrandRelationCommand> categoryBrandRelationCommandList) {
        ValidatorUtil.validateEntity(categoryBrandRelationCommandList, UpdateGroup.class);
        Long[] updateIds = categoryBrandRelationCommandService.batchSaveOrUpdate(categoryBrandRelationCommandList);
        return R.ok().put("updateIds",updateIds);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        categoryBrandRelationCommandService.delete(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        categoryBrandRelationCommandService.delete(id);
        return R.ok();
    }

}

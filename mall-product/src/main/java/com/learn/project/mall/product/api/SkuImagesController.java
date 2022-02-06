package com.learn.project.mall.product.api;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.web.util.R;
import com.learn.project.common.web.util.valid.ValidatorUtil;
import com.learn.project.common.web.util.valid.group.AddGroup;
import com.learn.project.common.web.util.valid.group.UpdateGroup;

import com.learn.project.mall.product.application.SkuImagesCommandService;
import com.learn.project.mall.product.application.SkuImagesQueryService;
import com.learn.project.mall.product.application.command.SkuImagesCommand;
import com.learn.project.mall.product.application.dto.SkuImagesDto;

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
 * sku图片-Controller
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@RestController
@RequestMapping("product/skuimages")
public class SkuImagesController {
    @Autowired
    private SkuImagesQueryService skuImagesQueryService;

    @Autowired
    private SkuImagesCommandService skuImagesCommandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = skuImagesQueryService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        SkuImagesDto skuImagesDto = skuImagesQueryService.getById(id);
        return R.ok().put("skuImages", skuImagesDto);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SkuImagesCommand skuImagesCommand){
        ValidatorUtil.validateEntity(skuImagesCommand, AddGroup.class);
        Long saveId = skuImagesCommandService.saveOrUpdate(skuImagesCommand);
        return R.ok().put("saveId", saveId);
    }
    
    /**
     * 批量保存
     */
    @RequestMapping("/batchSave")
    public R batchSave(@RequestBody List<SkuImagesCommand> skuImagesCommandList) {
        ValidatorUtil.validateEntity(skuImagesCommandList, AddGroup.class);
        Long[] saveIds = skuImagesCommandService.batchSaveOrUpdate(skuImagesCommandList);
        return R.ok().put("saveIds", saveIds);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SkuImagesCommand skuImagesCommand){
        ValidatorUtil.validateEntity(skuImagesCommand, UpdateGroup.class);
        Long updateId = skuImagesCommandService.saveOrUpdate(skuImagesCommand);
        return R.ok().put("updateId", updateId);
    }

    /**
     * 批量修改
     */
    @RequestMapping("/batchUpdate")
    public R batchUpdate(@RequestBody List<SkuImagesCommand> skuImagesCommandList) {
        ValidatorUtil.validateEntity(skuImagesCommandList, UpdateGroup.class);
        Long[] updateIds = skuImagesCommandService.batchSaveOrUpdate(skuImagesCommandList);
        return R.ok().put("updateIds",updateIds);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        skuImagesCommandService.delete(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        skuImagesCommandService.delete(id);
        return R.ok();
    }

}

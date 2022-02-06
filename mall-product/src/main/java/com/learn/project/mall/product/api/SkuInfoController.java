package com.learn.project.mall.product.api;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.web.util.R;
import com.learn.project.common.web.util.valid.ValidatorUtil;
import com.learn.project.common.web.util.valid.group.AddGroup;
import com.learn.project.common.web.util.valid.group.UpdateGroup;

import com.learn.project.mall.product.application.SkuInfoCommandService;
import com.learn.project.mall.product.application.SkuInfoQueryService;
import com.learn.project.mall.product.application.command.SkuInfoCommand;
import com.learn.project.mall.product.application.dto.SkuInfoDto;

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
 * sku信息-Controller
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@RestController
@RequestMapping("product/skuinfo")
public class SkuInfoController {
    @Autowired
    private SkuInfoQueryService skuInfoQueryService;

    @Autowired
    private SkuInfoCommandService skuInfoCommandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = skuInfoQueryService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{skuId}")
    public R info(@PathVariable("skuId") Long skuId){
        SkuInfoDto skuInfoDto = skuInfoQueryService.getById(skuId);
        return R.ok().put("skuInfo", skuInfoDto);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SkuInfoCommand skuInfoCommand){
        ValidatorUtil.validateEntity(skuInfoCommand, AddGroup.class);
        Long saveId = skuInfoCommandService.saveOrUpdate(skuInfoCommand);
        return R.ok().put("saveId", saveId);
    }
    
    /**
     * 批量保存
     */
    @RequestMapping("/batchSave")
    public R batchSave(@RequestBody List<SkuInfoCommand> skuInfoCommandList) {
        ValidatorUtil.validateEntity(skuInfoCommandList, AddGroup.class);
        Long[] saveIds = skuInfoCommandService.batchSaveOrUpdate(skuInfoCommandList);
        return R.ok().put("saveIds", saveIds);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SkuInfoCommand skuInfoCommand){
        ValidatorUtil.validateEntity(skuInfoCommand, UpdateGroup.class);
        Long updateId = skuInfoCommandService.saveOrUpdate(skuInfoCommand);
        return R.ok().put("updateId", updateId);
    }

    /**
     * 批量修改
     */
    @RequestMapping("/batchUpdate")
    public R batchUpdate(@RequestBody List<SkuInfoCommand> skuInfoCommandList) {
        ValidatorUtil.validateEntity(skuInfoCommandList, UpdateGroup.class);
        Long[] updateIds = skuInfoCommandService.batchSaveOrUpdate(skuInfoCommandList);
        return R.ok().put("updateIds",updateIds);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] skuIds){
        skuInfoCommandService.delete(Arrays.asList(skuIds));
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{skuId}")
    public R delete(@PathVariable("skuId") Long skuId) {
        skuInfoCommandService.delete(skuId);
        return R.ok();
    }

}

package com.learn.project.mall.product.api;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.web.util.R;
import com.learn.project.common.web.util.valid.ValidatorUtil;
import com.learn.project.common.web.util.valid.group.AddGroup;
import com.learn.project.common.web.util.valid.group.UpdateGroup;

import com.learn.project.mall.product.application.SkuSaleAttrValueCommandService;
import com.learn.project.mall.product.application.SkuSaleAttrValueQueryService;
import com.learn.project.mall.product.application.command.SkuSaleAttrValueCommand;
import com.learn.project.mall.product.application.dto.SkuSaleAttrValueDto;

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
 * sku销售属性&值-Controller
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@RestController
@RequestMapping("product/skusaleattrvalue")
public class SkuSaleAttrValueController {
    @Autowired
    private SkuSaleAttrValueQueryService skuSaleAttrValueQueryService;

    @Autowired
    private SkuSaleAttrValueCommandService skuSaleAttrValueCommandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = skuSaleAttrValueQueryService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        SkuSaleAttrValueDto skuSaleAttrValueDto = skuSaleAttrValueQueryService.getById(id);
        return R.ok().put("skuSaleAttrValue", skuSaleAttrValueDto);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SkuSaleAttrValueCommand skuSaleAttrValueCommand){
        ValidatorUtil.validateEntity(skuSaleAttrValueCommand, AddGroup.class);
        Long saveId = skuSaleAttrValueCommandService.saveOrUpdate(skuSaleAttrValueCommand);
        return R.ok().put("saveId", saveId);
    }
    
    /**
     * 批量保存
     */
    @RequestMapping("/batchSave")
    public R batchSave(@RequestBody List<SkuSaleAttrValueCommand> skuSaleAttrValueCommandList) {
        ValidatorUtil.validateEntity(skuSaleAttrValueCommandList, AddGroup.class);
        Long[] saveIds = skuSaleAttrValueCommandService.batchSaveOrUpdate(skuSaleAttrValueCommandList);
        return R.ok().put("saveIds", saveIds);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SkuSaleAttrValueCommand skuSaleAttrValueCommand){
        ValidatorUtil.validateEntity(skuSaleAttrValueCommand, UpdateGroup.class);
        Long updateId = skuSaleAttrValueCommandService.saveOrUpdate(skuSaleAttrValueCommand);
        return R.ok().put("updateId", updateId);
    }

    /**
     * 批量修改
     */
    @RequestMapping("/batchUpdate")
    public R batchUpdate(@RequestBody List<SkuSaleAttrValueCommand> skuSaleAttrValueCommandList) {
        ValidatorUtil.validateEntity(skuSaleAttrValueCommandList, UpdateGroup.class);
        Long[] updateIds = skuSaleAttrValueCommandService.batchSaveOrUpdate(skuSaleAttrValueCommandList);
        return R.ok().put("updateIds",updateIds);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        skuSaleAttrValueCommandService.delete(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        skuSaleAttrValueCommandService.delete(id);
        return R.ok();
    }

}

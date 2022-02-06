package com.learn.project.mall.product.api;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.web.util.R;
import com.learn.project.common.web.util.valid.ValidatorUtil;
import com.learn.project.common.web.util.valid.group.AddGroup;
import com.learn.project.common.web.util.valid.group.UpdateGroup;

import com.learn.project.mall.product.application.BrandCommandService;
import com.learn.project.mall.product.application.BrandQueryService;
import com.learn.project.mall.product.application.command.BrandCommand;
import com.learn.project.mall.product.application.dto.BrandDto;

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
 * 品牌-Controller
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandQueryService brandQueryService;

    @Autowired
    private BrandCommandService brandCommandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandQueryService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    public R info(@PathVariable("brandId") Long brandId){
        BrandDto brandDto = brandQueryService.getById(brandId);
        return R.ok().put("brand", brandDto);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BrandCommand brandCommand){
        ValidatorUtil.validateEntity(brandCommand, AddGroup.class);
        Long saveId = brandCommandService.saveOrUpdate(brandCommand);
        return R.ok().put("saveId", saveId);
    }
    
    /**
     * 批量保存
     */
    @RequestMapping("/batchSave")
    public R batchSave(@RequestBody List<BrandCommand> brandCommandList) {
        ValidatorUtil.validateEntity(brandCommandList, AddGroup.class);
        Long[] saveIds = brandCommandService.batchSaveOrUpdate(brandCommandList);
        return R.ok().put("saveIds", saveIds);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BrandCommand brandCommand){
        ValidatorUtil.validateEntity(brandCommand, UpdateGroup.class);
        Long updateId = brandCommandService.saveOrUpdate(brandCommand);
        return R.ok().put("updateId", updateId);
    }

    /**
     * 批量修改
     */
    @RequestMapping("/batchUpdate")
    public R batchUpdate(@RequestBody List<BrandCommand> brandCommandList) {
        ValidatorUtil.validateEntity(brandCommandList, UpdateGroup.class);
        Long[] updateIds = brandCommandService.batchSaveOrUpdate(brandCommandList);
        return R.ok().put("updateIds",updateIds);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] brandIds){
        brandCommandService.delete(Arrays.asList(brandIds));
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{brandId}")
    public R delete(@PathVariable("brandId") Long brandId) {
        brandCommandService.delete(brandId);
        return R.ok();
    }

}

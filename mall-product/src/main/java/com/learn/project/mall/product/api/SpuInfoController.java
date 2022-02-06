package com.learn.project.mall.product.api;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.web.util.R;
import com.learn.project.common.web.util.valid.ValidatorUtil;
import com.learn.project.common.web.util.valid.group.AddGroup;
import com.learn.project.common.web.util.valid.group.UpdateGroup;

import com.learn.project.mall.product.application.SpuInfoCommandService;
import com.learn.project.mall.product.application.SpuInfoQueryService;
import com.learn.project.mall.product.application.command.SpuInfoCommand;
import com.learn.project.mall.product.application.dto.SpuInfoDto;

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
 * spu信息-Controller
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@RestController
@RequestMapping("product/spuinfo")
public class SpuInfoController {
    @Autowired
    private SpuInfoQueryService spuInfoQueryService;

    @Autowired
    private SpuInfoCommandService spuInfoCommandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = spuInfoQueryService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        SpuInfoDto spuInfoDto = spuInfoQueryService.getById(id);
        return R.ok().put("spuInfo", spuInfoDto);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SpuInfoCommand spuInfoCommand){
        ValidatorUtil.validateEntity(spuInfoCommand, AddGroup.class);
        Long saveId = spuInfoCommandService.saveOrUpdate(spuInfoCommand);
        return R.ok().put("saveId", saveId);
    }
    
    /**
     * 批量保存
     */
    @RequestMapping("/batchSave")
    public R batchSave(@RequestBody List<SpuInfoCommand> spuInfoCommandList) {
        ValidatorUtil.validateEntity(spuInfoCommandList, AddGroup.class);
        Long[] saveIds = spuInfoCommandService.batchSaveOrUpdate(spuInfoCommandList);
        return R.ok().put("saveIds", saveIds);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SpuInfoCommand spuInfoCommand){
        ValidatorUtil.validateEntity(spuInfoCommand, UpdateGroup.class);
        Long updateId = spuInfoCommandService.saveOrUpdate(spuInfoCommand);
        return R.ok().put("updateId", updateId);
    }

    /**
     * 批量修改
     */
    @RequestMapping("/batchUpdate")
    public R batchUpdate(@RequestBody List<SpuInfoCommand> spuInfoCommandList) {
        ValidatorUtil.validateEntity(spuInfoCommandList, UpdateGroup.class);
        Long[] updateIds = spuInfoCommandService.batchSaveOrUpdate(spuInfoCommandList);
        return R.ok().put("updateIds",updateIds);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        spuInfoCommandService.delete(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        spuInfoCommandService.delete(id);
        return R.ok();
    }

}

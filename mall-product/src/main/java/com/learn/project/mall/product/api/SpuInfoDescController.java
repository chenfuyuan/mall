package com.learn.project.mall.product.api;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.web.util.R;
import com.learn.project.common.web.util.valid.ValidatorUtil;
import com.learn.project.common.web.util.valid.group.AddGroup;
import com.learn.project.common.web.util.valid.group.UpdateGroup;

import com.learn.project.mall.product.application.SpuInfoDescCommandService;
import com.learn.project.mall.product.application.SpuInfoDescQueryService;
import com.learn.project.mall.product.application.command.SpuInfoDescCommand;
import com.learn.project.mall.product.application.dto.SpuInfoDescDto;

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
 * spu信息介绍-Controller
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@RestController
@RequestMapping("product/spuinfodesc")
public class SpuInfoDescController {
    @Autowired
    private SpuInfoDescQueryService spuInfoDescQueryService;

    @Autowired
    private SpuInfoDescCommandService spuInfoDescCommandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = spuInfoDescQueryService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{spuId}")
    public R info(@PathVariable("spuId") Long spuId){
        SpuInfoDescDto spuInfoDescDto = spuInfoDescQueryService.getById(spuId);
        return R.ok().put("spuInfoDesc", spuInfoDescDto);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SpuInfoDescCommand spuInfoDescCommand){
        ValidatorUtil.validateEntity(spuInfoDescCommand, AddGroup.class);
        Long saveId = spuInfoDescCommandService.saveOrUpdate(spuInfoDescCommand);
        return R.ok().put("saveId", saveId);
    }
    
    /**
     * 批量保存
     */
    @RequestMapping("/batchSave")
    public R batchSave(@RequestBody List<SpuInfoDescCommand> spuInfoDescCommandList) {
        ValidatorUtil.validateEntity(spuInfoDescCommandList, AddGroup.class);
        Long[] saveIds = spuInfoDescCommandService.batchSaveOrUpdate(spuInfoDescCommandList);
        return R.ok().put("saveIds", saveIds);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SpuInfoDescCommand spuInfoDescCommand){
        ValidatorUtil.validateEntity(spuInfoDescCommand, UpdateGroup.class);
        Long updateId = spuInfoDescCommandService.saveOrUpdate(spuInfoDescCommand);
        return R.ok().put("updateId", updateId);
    }

    /**
     * 批量修改
     */
    @RequestMapping("/batchUpdate")
    public R batchUpdate(@RequestBody List<SpuInfoDescCommand> spuInfoDescCommandList) {
        ValidatorUtil.validateEntity(spuInfoDescCommandList, UpdateGroup.class);
        Long[] updateIds = spuInfoDescCommandService.batchSaveOrUpdate(spuInfoDescCommandList);
        return R.ok().put("updateIds",updateIds);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] spuIds){
        spuInfoDescCommandService.delete(Arrays.asList(spuIds));
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{spuId}")
    public R delete(@PathVariable("spuId") Long spuId) {
        spuInfoDescCommandService.delete(spuId);
        return R.ok();
    }

}

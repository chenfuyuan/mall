package com.learn.project.mall.product.api;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.web.util.R;
import com.learn.project.common.web.util.valid.ValidatorUtil;
import com.learn.project.common.web.util.valid.group.AddGroup;
import com.learn.project.common.web.util.valid.group.UpdateGroup;

import com.learn.project.mall.product.application.SpuImagesCommandService;
import com.learn.project.mall.product.application.SpuImagesQueryService;
import com.learn.project.mall.product.application.command.SpuImagesCommand;
import com.learn.project.mall.product.application.dto.SpuImagesDto;

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
 * spu图片-Controller
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@RestController
@RequestMapping("product/spuimages")
public class SpuImagesController {
    @Autowired
    private SpuImagesQueryService spuImagesQueryService;

    @Autowired
    private SpuImagesCommandService spuImagesCommandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = spuImagesQueryService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        SpuImagesDto spuImagesDto = spuImagesQueryService.getById(id);
        return R.ok().put("spuImages", spuImagesDto);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SpuImagesCommand spuImagesCommand){
        ValidatorUtil.validateEntity(spuImagesCommand, AddGroup.class);
        Long saveId = spuImagesCommandService.saveOrUpdate(spuImagesCommand);
        return R.ok().put("saveId", saveId);
    }
    
    /**
     * 批量保存
     */
    @RequestMapping("/batchSave")
    public R batchSave(@RequestBody List<SpuImagesCommand> spuImagesCommandList) {
        ValidatorUtil.validateEntity(spuImagesCommandList, AddGroup.class);
        Long[] saveIds = spuImagesCommandService.batchSaveOrUpdate(spuImagesCommandList);
        return R.ok().put("saveIds", saveIds);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SpuImagesCommand spuImagesCommand){
        ValidatorUtil.validateEntity(spuImagesCommand, UpdateGroup.class);
        Long updateId = spuImagesCommandService.saveOrUpdate(spuImagesCommand);
        return R.ok().put("updateId", updateId);
    }

    /**
     * 批量修改
     */
    @RequestMapping("/batchUpdate")
    public R batchUpdate(@RequestBody List<SpuImagesCommand> spuImagesCommandList) {
        ValidatorUtil.validateEntity(spuImagesCommandList, UpdateGroup.class);
        Long[] updateIds = spuImagesCommandService.batchSaveOrUpdate(spuImagesCommandList);
        return R.ok().put("updateIds",updateIds);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        spuImagesCommandService.delete(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        spuImagesCommandService.delete(id);
        return R.ok();
    }

}

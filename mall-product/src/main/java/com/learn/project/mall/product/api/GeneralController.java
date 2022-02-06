package com.learn.project.mall.product.api;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.web.util.R;
import com.learn.project.common.web.util.valid.ValidatorUtil;
import com.learn.project.common.web.util.valid.group.AddGroup;
import com.learn.project.common.web.util.valid.group.UpdateGroup;

import com.learn.project.mall.product.application.GeneralCommandService;
import com.learn.project.mall.product.application.GeneralQueryService;
import com.learn.project.mall.product.application.command.GeneralCommand;
import com.learn.project.mall.product.application.dto.GeneralDto;

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
 * 通用测试-Controller
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@RestController
@RequestMapping("product/general")
public class GeneralController {
    @Autowired
    private GeneralQueryService generalQueryService;

    @Autowired
    private GeneralCommandService generalCommandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = generalQueryService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        GeneralDto generalDto = generalQueryService.getById(id);
        return R.ok().put("general", generalDto);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GeneralCommand generalCommand){
        ValidatorUtil.validateEntity(generalCommand, AddGroup.class);
        Long saveId = generalCommandService.saveOrUpdate(generalCommand);
        return R.ok().put("saveId", saveId);
    }
    
    /**
     * 批量保存
     */
    @RequestMapping("/batchSave")
    public R batchSave(@RequestBody List<GeneralCommand> generalCommandList) {
        ValidatorUtil.validateEntity(generalCommandList, AddGroup.class);
        Long[] saveIds = generalCommandService.batchSaveOrUpdate(generalCommandList);
        return R.ok().put("saveIds", saveIds);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody GeneralCommand generalCommand){
        ValidatorUtil.validateEntity(generalCommand, UpdateGroup.class);
        Long updateId = generalCommandService.saveOrUpdate(generalCommand);
        return R.ok().put("updateId", updateId);
    }

    /**
     * 批量修改
     */
    @RequestMapping("/batchUpdate")
    public R batchUpdate(@RequestBody List<GeneralCommand> generalCommandList) {
        ValidatorUtil.validateEntity(generalCommandList, UpdateGroup.class);
        Long[] updateIds = generalCommandService.batchSaveOrUpdate(generalCommandList);
        return R.ok().put("updateIds",updateIds);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        generalCommandService.delete(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        generalCommandService.delete(id);
        return R.ok();
    }

}

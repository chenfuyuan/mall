package com.learn.project.mall.product.api;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.web.util.R;
import com.learn.project.common.web.util.valid.ValidatorUtil;
import com.learn.project.common.web.util.valid.group.AddGroup;
import com.learn.project.common.web.util.valid.group.UpdateGroup;

import com.learn.project.mall.product.application.AttrCommandService;
import com.learn.project.mall.product.application.AttrQueryService;
import com.learn.project.mall.product.application.command.AttrCommand;
import com.learn.project.mall.product.application.dto.AttrDto;

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
 * 商品属性-Controller
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrQueryService attrQueryService;

    @Autowired
    private AttrCommandService attrCommandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrQueryService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId){
        AttrDto attrDto = attrQueryService.getById(attrId);
        return R.ok().put("attr", attrDto);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrCommand attrCommand){
        ValidatorUtil.validateEntity(attrCommand, AddGroup.class);
        Long saveId = attrCommandService.saveOrUpdate(attrCommand);
        return R.ok().put("saveId", saveId);
    }
    
    /**
     * 批量保存
     */
    @RequestMapping("/batchSave")
    public R batchSave(@RequestBody List<AttrCommand> attrCommandList) {
        ValidatorUtil.validateEntity(attrCommandList, AddGroup.class);
        Long[] saveIds = attrCommandService.batchSaveOrUpdate(attrCommandList);
        return R.ok().put("saveIds", saveIds);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrCommand attrCommand){
        ValidatorUtil.validateEntity(attrCommand, UpdateGroup.class);
        Long updateId = attrCommandService.saveOrUpdate(attrCommand);
        return R.ok().put("updateId", updateId);
    }

    /**
     * 批量修改
     */
    @RequestMapping("/batchUpdate")
    public R batchUpdate(@RequestBody List<AttrCommand> attrCommandList) {
        ValidatorUtil.validateEntity(attrCommandList, UpdateGroup.class);
        Long[] updateIds = attrCommandService.batchSaveOrUpdate(attrCommandList);
        return R.ok().put("updateIds",updateIds);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrIds){
        attrCommandService.delete(Arrays.asList(attrIds));
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{attrId}")
    public R delete(@PathVariable("attrId") Long attrId) {
        attrCommandService.delete(attrId);
        return R.ok();
    }

}

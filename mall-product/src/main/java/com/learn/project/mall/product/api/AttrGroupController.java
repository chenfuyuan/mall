package com.learn.project.mall.product.api;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.web.util.R;
import com.learn.project.common.web.util.valid.ValidatorUtil;
import com.learn.project.common.web.util.valid.group.AddGroup;
import com.learn.project.common.web.util.valid.group.UpdateGroup;

import com.learn.project.mall.product.application.AttrGroupCommandService;
import com.learn.project.mall.product.application.AttrGroupQueryService;
import com.learn.project.mall.product.application.command.AttrGroupCommand;
import com.learn.project.mall.product.application.dto.AttrGroupDto;

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
 * 属性分组-Controller
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupQueryService attrGroupQueryService;

    @Autowired
    private AttrGroupCommandService attrGroupCommandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrGroupQueryService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
        AttrGroupDto attrGroupDto = attrGroupQueryService.getById(attrGroupId);
        return R.ok().put("attrGroup", attrGroupDto);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrGroupCommand attrGroupCommand){
        ValidatorUtil.validateEntity(attrGroupCommand, AddGroup.class);
        Long saveId = attrGroupCommandService.saveOrUpdate(attrGroupCommand);
        return R.ok().put("saveId", saveId);
    }
    
    /**
     * 批量保存
     */
    @RequestMapping("/batchSave")
    public R batchSave(@RequestBody List<AttrGroupCommand> attrGroupCommandList) {
        ValidatorUtil.validateEntity(attrGroupCommandList, AddGroup.class);
        Long[] saveIds = attrGroupCommandService.batchSaveOrUpdate(attrGroupCommandList);
        return R.ok().put("saveIds", saveIds);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrGroupCommand attrGroupCommand){
        ValidatorUtil.validateEntity(attrGroupCommand, UpdateGroup.class);
        Long updateId = attrGroupCommandService.saveOrUpdate(attrGroupCommand);
        return R.ok().put("updateId", updateId);
    }

    /**
     * 批量修改
     */
    @RequestMapping("/batchUpdate")
    public R batchUpdate(@RequestBody List<AttrGroupCommand> attrGroupCommandList) {
        ValidatorUtil.validateEntity(attrGroupCommandList, UpdateGroup.class);
        Long[] updateIds = attrGroupCommandService.batchSaveOrUpdate(attrGroupCommandList);
        return R.ok().put("updateIds",updateIds);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrGroupIds){
        attrGroupCommandService.delete(Arrays.asList(attrGroupIds));
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{attrGroupId}")
    public R delete(@PathVariable("attrGroupId") Long attrGroupId) {
        attrGroupCommandService.delete(attrGroupId);
        return R.ok();
    }

}

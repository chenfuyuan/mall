package com.learn.project.mall.product.api;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.web.util.R;
import com.learn.project.common.web.util.valid.ValidatorUtil;
import com.learn.project.common.web.util.valid.group.AddGroup;
import com.learn.project.common.web.util.valid.group.UpdateGroup;

import com.learn.project.mall.product.application.AttrAttrgroupRelationCommandService;
import com.learn.project.mall.product.application.AttrAttrgroupRelationQueryService;
import com.learn.project.mall.product.application.command.AttrAttrgroupRelationCommand;
import com.learn.project.mall.product.application.dto.AttrAttrgroupRelationDto;

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
 * 属性&属性分组关联-Controller
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@RestController
@RequestMapping("product/attrattrgrouprelation")
public class AttrAttrgroupRelationController {
    @Autowired
    private AttrAttrgroupRelationQueryService attrAttrgroupRelationQueryService;

    @Autowired
    private AttrAttrgroupRelationCommandService attrAttrgroupRelationCommandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrAttrgroupRelationQueryService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        AttrAttrgroupRelationDto attrAttrgroupRelationDto = attrAttrgroupRelationQueryService.getById(id);
        return R.ok().put("attrAttrgroupRelation", attrAttrgroupRelationDto);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrAttrgroupRelationCommand attrAttrgroupRelationCommand){
        ValidatorUtil.validateEntity(attrAttrgroupRelationCommand, AddGroup.class);
        Long saveId = attrAttrgroupRelationCommandService.saveOrUpdate(attrAttrgroupRelationCommand);
        return R.ok().put("saveId", saveId);
    }
    
    /**
     * 批量保存
     */
    @RequestMapping("/batchSave")
    public R batchSave(@RequestBody List<AttrAttrgroupRelationCommand> attrAttrgroupRelationCommandList) {
        ValidatorUtil.validateEntity(attrAttrgroupRelationCommandList, AddGroup.class);
        Long[] saveIds = attrAttrgroupRelationCommandService.batchSaveOrUpdate(attrAttrgroupRelationCommandList);
        return R.ok().put("saveIds", saveIds);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrAttrgroupRelationCommand attrAttrgroupRelationCommand){
        ValidatorUtil.validateEntity(attrAttrgroupRelationCommand, UpdateGroup.class);
        Long updateId = attrAttrgroupRelationCommandService.saveOrUpdate(attrAttrgroupRelationCommand);
        return R.ok().put("updateId", updateId);
    }

    /**
     * 批量修改
     */
    @RequestMapping("/batchUpdate")
    public R batchUpdate(@RequestBody List<AttrAttrgroupRelationCommand> attrAttrgroupRelationCommandList) {
        ValidatorUtil.validateEntity(attrAttrgroupRelationCommandList, UpdateGroup.class);
        Long[] updateIds = attrAttrgroupRelationCommandService.batchSaveOrUpdate(attrAttrgroupRelationCommandList);
        return R.ok().put("updateIds",updateIds);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        attrAttrgroupRelationCommandService.delete(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        attrAttrgroupRelationCommandService.delete(id);
        return R.ok();
    }

}

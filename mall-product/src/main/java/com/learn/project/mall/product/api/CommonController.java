package com.learn.project.mall.product.api;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.web.util.R;
import com.learn.project.common.web.util.valid.ValidatorUtil;
import com.learn.project.common.web.util.valid.group.AddGroup;
import com.learn.project.common.web.util.valid.group.UpdateGroup;

import com.learn.project.mall.product.application.CommonCommandService;
import com.learn.project.mall.product.application.CommonQueryService;
import com.learn.project.mall.product.application.command.CommonCommand;
import com.learn.project.mall.product.application.dto.CommonDto;

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
@RequestMapping("product/common")
public class CommonController {
    @Autowired
    private CommonQueryService commonQueryService;

    @Autowired
    private CommonCommandService commonCommandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = commonQueryService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{commonId}")
    public R info(@PathVariable("commonId") Long commonId){
        CommonDto commonDto = commonQueryService.getById(commonId);
        return R.ok().put("common", commonDto);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CommonCommand commonCommand){
        ValidatorUtil.validateEntity(commonCommand, AddGroup.class);
        Long saveId = commonCommandService.saveOrUpdate(commonCommand);
        return R.ok().put("saveId", saveId);
    }
    
    /**
     * 批量保存
     */
    @RequestMapping("/batchSave")
    public R batchSave(@RequestBody List<CommonCommand> commonCommandList) {
        ValidatorUtil.validateEntity(commonCommandList, AddGroup.class);
        Long[] saveIds = commonCommandService.batchSaveOrUpdate(commonCommandList);
        return R.ok().put("saveIds", saveIds);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CommonCommand commonCommand){
        ValidatorUtil.validateEntity(commonCommand, UpdateGroup.class);
        Long updateId = commonCommandService.saveOrUpdate(commonCommand);
        return R.ok().put("updateId", updateId);
    }

    /**
     * 批量修改
     */
    @RequestMapping("/batchUpdate")
    public R batchUpdate(@RequestBody List<CommonCommand> commonCommandList) {
        ValidatorUtil.validateEntity(commonCommandList, UpdateGroup.class);
        Long[] updateIds = commonCommandService.batchSaveOrUpdate(commonCommandList);
        return R.ok().put("updateIds",updateIds);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] commonIds){
        commonCommandService.delete(Arrays.asList(commonIds));
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{commonId}")
    public R delete(@PathVariable("commonId") Long commonId) {
        commonCommandService.delete(commonId);
        return R.ok();
    }

}

package com.learn.project.mall.product.api;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.web.util.R;
import com.learn.project.common.web.util.valid.ValidatorUtil;
import com.learn.project.common.web.util.valid.group.AddGroup;
import com.learn.project.common.web.util.valid.group.UpdateGroup;

import com.learn.project.mall.product.application.SpuCommentCommandService;
import com.learn.project.mall.product.application.SpuCommentQueryService;
import com.learn.project.mall.product.application.command.SpuCommentCommand;
import com.learn.project.mall.product.application.dto.SpuCommentDto;

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
 * 商品评价-Controller
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@RestController
@RequestMapping("product/spucomment")
public class SpuCommentController {
    @Autowired
    private SpuCommentQueryService spuCommentQueryService;

    @Autowired
    private SpuCommentCommandService spuCommentCommandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = spuCommentQueryService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        SpuCommentDto spuCommentDto = spuCommentQueryService.getById(id);
        return R.ok().put("spuComment", spuCommentDto);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SpuCommentCommand spuCommentCommand){
        ValidatorUtil.validateEntity(spuCommentCommand, AddGroup.class);
        Long saveId = spuCommentCommandService.saveOrUpdate(spuCommentCommand);
        return R.ok().put("saveId", saveId);
    }
    
    /**
     * 批量保存
     */
    @RequestMapping("/batchSave")
    public R batchSave(@RequestBody List<SpuCommentCommand> spuCommentCommandList) {
        ValidatorUtil.validateEntity(spuCommentCommandList, AddGroup.class);
        Long[] saveIds = spuCommentCommandService.batchSaveOrUpdate(spuCommentCommandList);
        return R.ok().put("saveIds", saveIds);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SpuCommentCommand spuCommentCommand){
        ValidatorUtil.validateEntity(spuCommentCommand, UpdateGroup.class);
        Long updateId = spuCommentCommandService.saveOrUpdate(spuCommentCommand);
        return R.ok().put("updateId", updateId);
    }

    /**
     * 批量修改
     */
    @RequestMapping("/batchUpdate")
    public R batchUpdate(@RequestBody List<SpuCommentCommand> spuCommentCommandList) {
        ValidatorUtil.validateEntity(spuCommentCommandList, UpdateGroup.class);
        Long[] updateIds = spuCommentCommandService.batchSaveOrUpdate(spuCommentCommandList);
        return R.ok().put("updateIds",updateIds);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        spuCommentCommandService.delete(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        spuCommentCommandService.delete(id);
        return R.ok();
    }

}

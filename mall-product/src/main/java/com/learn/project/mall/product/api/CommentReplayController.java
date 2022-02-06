package com.learn.project.mall.product.api;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.web.util.R;
import com.learn.project.common.web.util.valid.ValidatorUtil;
import com.learn.project.common.web.util.valid.group.AddGroup;
import com.learn.project.common.web.util.valid.group.UpdateGroup;

import com.learn.project.mall.product.application.CommentReplayCommandService;
import com.learn.project.mall.product.application.CommentReplayQueryService;
import com.learn.project.mall.product.application.command.CommentReplayCommand;
import com.learn.project.mall.product.application.dto.CommentReplayDto;

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
 * 商品评价回复关系-Controller
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@RestController
@RequestMapping("product/commentreplay")
public class CommentReplayController {
    @Autowired
    private CommentReplayQueryService commentReplayQueryService;

    @Autowired
    private CommentReplayCommandService commentReplayCommandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = commentReplayQueryService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CommentReplayDto commentReplayDto = commentReplayQueryService.getById(id);
        return R.ok().put("commentReplay", commentReplayDto);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CommentReplayCommand commentReplayCommand){
        ValidatorUtil.validateEntity(commentReplayCommand, AddGroup.class);
        Long saveId = commentReplayCommandService.saveOrUpdate(commentReplayCommand);
        return R.ok().put("saveId", saveId);
    }
    
    /**
     * 批量保存
     */
    @RequestMapping("/batchSave")
    public R batchSave(@RequestBody List<CommentReplayCommand> commentReplayCommandList) {
        ValidatorUtil.validateEntity(commentReplayCommandList, AddGroup.class);
        Long[] saveIds = commentReplayCommandService.batchSaveOrUpdate(commentReplayCommandList);
        return R.ok().put("saveIds", saveIds);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CommentReplayCommand commentReplayCommand){
        ValidatorUtil.validateEntity(commentReplayCommand, UpdateGroup.class);
        Long updateId = commentReplayCommandService.saveOrUpdate(commentReplayCommand);
        return R.ok().put("updateId", updateId);
    }

    /**
     * 批量修改
     */
    @RequestMapping("/batchUpdate")
    public R batchUpdate(@RequestBody List<CommentReplayCommand> commentReplayCommandList) {
        ValidatorUtil.validateEntity(commentReplayCommandList, UpdateGroup.class);
        Long[] updateIds = commentReplayCommandService.batchSaveOrUpdate(commentReplayCommandList);
        return R.ok().put("updateIds",updateIds);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        commentReplayCommandService.delete(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        commentReplayCommandService.delete(id);
        return R.ok();
    }

}

package com.learn.project.mall.product.api;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.web.util.R;
import com.learn.project.common.web.util.valid.ValidatorUtil;
import com.learn.project.common.web.util.valid.group.AddGroup;
import com.learn.project.common.web.util.valid.group.UpdateGroup;

import com.learn.project.mall.product.application.ProductAttrValueCommandService;
import com.learn.project.mall.product.application.ProductAttrValueQueryService;
import com.learn.project.mall.product.application.command.ProductAttrValueCommand;
import com.learn.project.mall.product.application.dto.ProductAttrValueDto;

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
 * spu属性值-Controller
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@RestController
@RequestMapping("product/productattrvalue")
public class ProductAttrValueController {
    @Autowired
    private ProductAttrValueQueryService productAttrValueQueryService;

    @Autowired
    private ProductAttrValueCommandService productAttrValueCommandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productAttrValueQueryService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ProductAttrValueDto productAttrValueDto = productAttrValueQueryService.getById(id);
        return R.ok().put("productAttrValue", productAttrValueDto);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ProductAttrValueCommand productAttrValueCommand){
        ValidatorUtil.validateEntity(productAttrValueCommand, AddGroup.class);
        Long saveId = productAttrValueCommandService.saveOrUpdate(productAttrValueCommand);
        return R.ok().put("saveId", saveId);
    }
    
    /**
     * 批量保存
     */
    @RequestMapping("/batchSave")
    public R batchSave(@RequestBody List<ProductAttrValueCommand> productAttrValueCommandList) {
        ValidatorUtil.validateEntity(productAttrValueCommandList, AddGroup.class);
        Long[] saveIds = productAttrValueCommandService.batchSaveOrUpdate(productAttrValueCommandList);
        return R.ok().put("saveIds", saveIds);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ProductAttrValueCommand productAttrValueCommand){
        ValidatorUtil.validateEntity(productAttrValueCommand, UpdateGroup.class);
        Long updateId = productAttrValueCommandService.saveOrUpdate(productAttrValueCommand);
        return R.ok().put("updateId", updateId);
    }

    /**
     * 批量修改
     */
    @RequestMapping("/batchUpdate")
    public R batchUpdate(@RequestBody List<ProductAttrValueCommand> productAttrValueCommandList) {
        ValidatorUtil.validateEntity(productAttrValueCommandList, UpdateGroup.class);
        Long[] updateIds = productAttrValueCommandService.batchSaveOrUpdate(productAttrValueCommandList);
        return R.ok().put("updateIds",updateIds);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        productAttrValueCommandService.delete(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        productAttrValueCommandService.delete(id);
        return R.ok();
    }

}

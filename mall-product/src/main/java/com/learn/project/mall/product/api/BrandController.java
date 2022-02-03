package com.learn.project.mall.product.api;

import java.util.Arrays;
import java.util.Map;


import com.learn.project.common.web.util.valid.group.Add;
import com.learn.project.common.web.util.valid.group.Update;
import com.learn.project.common.web.util.valid.group.UpdateShowStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.BrandEntity;
import com.learn.project.mall.product.application.BrandService;
import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.web.util.R;




/**
 * 品牌Controller
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2021-12-12 14:35:18
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody @Validated({Add.class}) BrandEntity brand){
		brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody @Validated(Update.class) BrandEntity brand){
		brandService.updateById(brand);

        return R.ok();
    }

    @RequestMapping("/update/showStatus")
    public R updateShowStatus(@RequestBody @Validated(UpdateShowStatus.class) BrandEntity brand){
        BrandEntity updateBrand = new BrandEntity();
        updateBrand.setBrandId(brand.getBrandId());
        updateBrand.setShowStatus(brand.getShowStatus());
        updateBrand.setUpdateVersion(brand.getUpdateVersion());
        brandService.updateById(updateBrand);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}

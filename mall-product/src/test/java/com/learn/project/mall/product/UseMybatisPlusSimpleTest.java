package com.learn.project.mall.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.BrandEntity;
import com.learn.project.mall.product.application.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 测试Mybatis-plus使用
 *
 * @author chenfuyuan
 * @date 2021/12/7 15:41
 */
@SpringBootTest
public class UseMybatisPlusSimpleTest {

    @Autowired
    private BrandService brandService;

    @Test
    public void test_add() {
        BrandEntity brand = new BrandEntity();
        brand.setName("HuaWei");
        brandService.save(brand);
        System.out.println(brand.getBrandId());
        System.out.println("保存成功。");
    }

    @Test
    public void test_list() {
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<>();
        //设置查询条件
        queryWrapper.like("name", "HuaWei");
        List<BrandEntity> list = brandService.list(queryWrapper);
        System.out.println(list);
    }

    @Test
    public void test_delete() {
        QueryWrapper<BrandEntity> deleteWrapper = new QueryWrapper<>();
        //设置匹配条件
        deleteWrapper.eq("name", "HuaWei");
        brandService.remove(deleteWrapper);
    }

    @Test
    public void testUpdate() {
        UpdateWrapper<BrandEntity> updateWrapper = new UpdateWrapper<>();
        //设置匹配条件
        updateWrapper.eq("name", "Apple");

        //设置修改值
        updateWrapper.set("name", "HuaWei");

        brandService.update(updateWrapper);

    }

}

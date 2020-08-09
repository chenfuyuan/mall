package com.learn.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.common.utils.PageUtils;
import com.learn.common.utils.Query;
import com.learn.common.utils.TreeUtils;
import com.learn.gulimall.product.dao.CategoryDao;
import com.learn.gulimall.product.entity.CategoryEntity;
import com.learn.gulimall.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );
        return new PageUtils(page);
    }

    /**
     * 1. 找出一级菜单
     * 2. 递归填充子菜单
     *
     * @return
     */
    @Override
    public List<CategoryEntity> listWithTree() {
        //1. 查询所有数据
        List<CategoryEntity> all = baseMapper.selectList(null);

        //创建树形转换工具类
        TreeUtils<CategoryEntity> treeUtils = new TreeUtils<>();
        //将集合通过一定的规则转换成树形结构
        List<CategoryEntity> treeList = treeUtils.listToTree(all);

        return  treeList;
    }

    /**
     * 逻辑删除符合规定的Menus
     *
     * @param asList
     * @author: Vito.Chen
     * @date: 2020-8-2 8:38
     * @return: void
     */
    @Override
    public void removeMenuByIds(List<Long> asList) {
        //TODO 1、检查当前菜单是否被引用

        baseMapper.deleteBatchIds(asList);
    }
}
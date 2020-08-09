package com.learn.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.common.utils.EmptyUtils;
import com.learn.common.utils.PageUtils;
import com.learn.common.utils.Query;
import com.learn.common.utils.TreeUtils;
import com.learn.gulimall.product.dao.CategoryDao;
import com.learn.gulimall.product.entity.CategoryEntity;
import com.learn.gulimall.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    /**
     * 搜索分类的完整路径
     * @author: Vito.Chen
     * @date: 2020-8-9 13:07
     * @param catelogId
     * @return: java.lang.Long[]
     */
    @Override
    public Long[] findCategoryPath(Long catelogId) {
        List<Long> parentPath =  new ArrayList<>();
        this.findParentPathByCatId(catelogId,parentPath);
        Collections.reverse(parentPath);
        return parentPath.toArray(new Long[parentPath.size()]);

    }

    /**
     * 根据catId寻找完整路径，并填入parentPath集合中
     * @param catelogId 分类id
     * @param parentPath 路径集合
     */
    private void findParentPathByCatId(Long catelogId, List<Long> parentPath) {
        //根据id获取分类
        CategoryEntity categoryEntity = this.getById(catelogId);
        if(EmptyUtils.isNotEmpty(categoryEntity)){
            parentPath.add(categoryEntity.getCatId());
            if (categoryEntity.getParentCid()!=null && categoryEntity.getParentCid()!=0) {
                //递归寻找父路径
                findParentPathByCatId(categoryEntity.getParentCid(), parentPath);
            }
        }


    }
}
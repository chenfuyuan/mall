package com.learn.project.mall.product.application.impl;

import com.learn.project.common.mybatis.util.Query;
import com.learn.project.mall.product.application.CategoryService;
import com.learn.project.mall.product.infrastructure.constant.PmsConstant;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.common.mybatis.util.PageUtils;

import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.AttrGroupDao;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.AttrGroupEntity;
import com.learn.project.mall.product.application.AttrGroupService;

/**
 * 属性分组Service实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2021-12-12 14:35:18
 */
@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    private CategoryService categoryService;

    @Override
    public PageUtils queryPageByCatId(Map<String, Object> params, String catId) {
        IPage<AttrGroupEntity> pageQueryCondition = new Query<AttrGroupEntity>().getPage(params);
        QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        //搜索关键字 attrGroupId和attrGroupName
        if (EmptyUtil.isNotEmpty(key)) {
            queryWrapper.and((obj)->{
                obj.eq("attr_group_id", key).or().like("attr_group_name", key);
            });
        }

        //判断是查询所有函数查询指定三级分类
        if (!PmsConstant.QUERY_ALL_LABLE.equals(catId)) {
            queryWrapper.eq("catelog_id", catId);
        }

        return new PageUtils(this.page(pageQueryCondition, queryWrapper));
    }

    @Override
    public AttrGroupEntity getInfoById(Long attrGroupId) {
        AttrGroupEntity attrGroup = this.getById(attrGroupId);
        if (EmptyUtil.isNotEmpty(attrGroup) && !NumberUtil.isEmptyOrZero(attrGroup.getCatelogId())) {
            Long[] categoryPath = categoryService.findCategoryPathByCatId(attrGroup.getCatelogId());
            attrGroup.setCategoryPath(categoryPath);
        }

        return attrGroup;
    }

}
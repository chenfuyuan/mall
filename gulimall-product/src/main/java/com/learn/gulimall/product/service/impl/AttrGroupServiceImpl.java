package com.learn.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.common.utils.PageUtils;
import com.learn.common.utils.Query;
import com.learn.gulimall.product.dao.AttrGroupDao;
import com.learn.gulimall.product.entity.AttrGroupEntity;
import com.learn.gulimall.product.service.AttrGroupService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>().like("attr_group_name",params.get("key"))
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByCatId(Map<String, Object> params, Integer catId) {
        //构建查询条件
        QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<>();
        //查询对应分类的 属性分组
        queryWrapper.eq("catelog_id",catId);
        queryWrapper.like("attr_group_name", params.get("key"));
        //构建page对象，传入params和查询条件
        IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params),queryWrapper);
    return new PageUtils(page);
    }


}
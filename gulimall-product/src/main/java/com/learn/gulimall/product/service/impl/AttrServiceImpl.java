package com.learn.gulimall.product.service.impl;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.common.utils.EmptyUtils;
import com.learn.common.utils.PageUtils;
import com.learn.common.utils.Query;
import com.learn.gulimall.product.Vo.AttrVo;
import com.learn.gulimall.product.dao.AttrDao;
import com.learn.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.learn.gulimall.product.entity.AttrEntity;
import com.learn.gulimall.product.service.AttrAttrgroupRelationService;
import com.learn.gulimall.product.service.AttrService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 根据分类id获取对应规格参数
     * @param params
     * @param catId
     * @return
     */
    @Override
    public PageUtils queryPageByCatId(Map<String, Object> params, Long catId) {
        //构建查询条件
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<AttrEntity>();
        //构建快速查询条件
        if (EmptyUtils.isNotEmpty(params.get("key"))) {
            queryWrapper.eq("attr_name", params.get("key"));
        }
        if (EmptyUtils.isNotEmpty(catId) && catId != 0) {
            queryWrapper.eq("catelog_id", catId);
        }
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), queryWrapper);
        return new PageUtils(page);
    }

    /**
     * 20200910  保存属性
     * @param attr
     */
    @Override
    public void saveAttr(AttrVo attr) {
        //保存属性信息
        AttrEntity attrEntity = new AttrEntity();
        //调用Spring提供的BeanUtils,保存属性信息
        BeanUtils.copyProperties(attr, attrEntity);
        this.save(attrEntity);

        //保存分组与属性的关系
        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = buildAttrGroupRelationEntity(attr);
    }

    /**
     * 构建分组 与 属性的关联关系 对象
     * @author: Vito.Chen
     * @date: 2020-9-10 23:38
     * @param attr
     * @return: com.learn.gulimall.product.entity.AttrAttrgroupRelationEntity
     */
    private AttrAttrgroupRelationEntity buildAttrGroupRelationEntity(AttrVo attr) {
        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
        attrAttrgroupRelationEntity.setAttrId(attr.getAttrId());
        attrAttrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId());
        return attrAttrgroupRelationEntity;
    }


    private static final long serialVersionUID = 1L;

    /**
     * 属性id
     */
    @TableId
    private Long attrId;
    /**
     * 属性名
     */
    private String attrName;
    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    private Integer searchType;
    /**
     * 属性图标
     */
    private String icon;
    /**
     * 可选值列表[用逗号分隔]
     */
    private String valueSelect;
    /**
     * 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
     */
    private Integer attrType;
    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    private Long enable;
    /**
     * 所属分类
     */
    private Long catelogId;
    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    private Integer showDesc;
}
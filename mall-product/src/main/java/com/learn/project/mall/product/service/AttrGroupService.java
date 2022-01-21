package com.learn.project.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.project.common.utils.PageUtils;
import com.learn.project.mall.product.entity.AttrGroupEntity;

import java.util.Map;

/**
 * 属性分组Service接口
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2021-12-12 14:35:18
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    /**
     * 分页查询，通过分类Id
     * @param params
     * @param catId
     * @return
     */
    PageUtils queryPageByCatId(Map<String, Object> params, String catId);
}


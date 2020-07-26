package com.learn.gulimail.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.common.utils.PageUtils;
import com.learn.gulimail.product.entity.SpuInfoEntity;

import java.util.Map;

/**
 * spu信息
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2020-07-26 13:41:52
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


package com.learn.gulimail.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.common.utils.PageUtils;
import com.learn.gulimail.ware.entity.WareOrderTaskEntity;

import java.util.Map;

/**
 * 库存工作单
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2020-07-27 04:42:49
 */
public interface WareOrderTaskService extends IService<WareOrderTaskEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


package com.learn.project.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.project.common.utils.PageUtils;
import com.learn.project.mall.coupon.entity.HomeSubjectEntity;

import java.util.Map;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2021-12-07 16:07:17
 */
public interface HomeSubjectService extends IService<HomeSubjectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


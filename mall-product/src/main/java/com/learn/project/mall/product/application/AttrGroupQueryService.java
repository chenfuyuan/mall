package com.learn.project.mall.product.application;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.mall.product.application.dto.AttrGroupDto;

import java.util.Map;

/**
 * 属性分组-查询服务接口
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface AttrGroupQueryService {
    /**
     * 分页查询数据
     * @param params 查询参数
     * @return 分页结果
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 通过attrGroupId获取指定数据
     * @param attrGroupId attrGroupId
     * @return 数据
     */
     AttrGroupDto getById(Long attrGroupId);
}

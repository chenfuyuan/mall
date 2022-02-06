package com.learn.project.mall.product.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.mybatis.util.Query;
import com.learn.project.mall.product.application.CommonQueryService;
import com.learn.project.mall.product.application.assembler.CommonAssembler;
import com.learn.project.mall.product.application.dto.CommonDto;
import com.learn.project.mall.product.domain.model.common.Common;
import com.learn.project.mall.product.domain.model.common.CommonId;
import com.learn.project.mall.product.domain.model.common.CommonRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CommonDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 通用测试-查询服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Service("commonQueryService")
public class CommonQueryServiceImpl implements CommonQueryService {

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private CommonRepository commonRepository;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //填充查询条件
        QueryWrapper<CommonDo> queryWrapper = new QueryWrapper<>();

        //查询
        IPage<CommonDo> page = commonMapper.selectPage(new Query().getPage(params),queryWrapper);

        //封装分页
        return new PageUtils(page);
    }

    @Override
    public CommonDto getById(Long commonId) {
        Common common = commonRepository.find(new CommonId(commonId));
        return CommonAssembler.fromCommon(common);
    }


}

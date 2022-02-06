package com.learn.project.mall.product.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.mybatis.util.Query;
import com.learn.project.mall.product.application.GeneralQueryService;
import com.learn.project.mall.product.application.assembler.GeneralAssembler;
import com.learn.project.mall.product.application.dto.GeneralDto;
import com.learn.project.mall.product.domain.model.general.General;
import com.learn.project.mall.product.domain.model.general.GeneralId;
import com.learn.project.mall.product.domain.model.general.GeneralRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.GeneralDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.GeneralMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 通用测试-查询服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Service("generalQueryService")
public class GeneralQueryServiceImpl implements GeneralQueryService {

    @Autowired
    private GeneralMapper generalMapper;

    @Autowired
    private GeneralRepository generalRepository;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //填充查询条件
        QueryWrapper<GeneralDo> queryWrapper = new QueryWrapper<>();

        //查询
        IPage<GeneralDo> page = generalMapper.selectPage(new Query().getPage(params),queryWrapper);

        //封装分页
        return new PageUtils(page);
    }

    @Override
    public GeneralDto getById(Long id) {
        General general = generalRepository.find(new GeneralId(id));
        return GeneralAssembler.fromGeneral(general);
    }


}

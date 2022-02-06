package com.learn.project.mall.product.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.mall.product.domain.model.general.General;
import com.learn.project.mall.product.domain.model.general.GeneralId;
import com.learn.project.mall.product.domain.model.general.GeneralRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.converter.GeneralConverter;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.GeneralDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.GeneralMapper;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 通用测试-仓储服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Repository
public class GeneralRepositoryImpl extends ServiceImpl<GeneralMapper, GeneralDo> implements GeneralRepository, IService<GeneralDo> {


    @Override
    public General find(GeneralId id) {
        GeneralDo generalDo = this.getById(id.getId());
        if (EmptyUtil.isNull(generalDo)) {
            return null;
        }
        return GeneralConverter.toGeneral(generalDo);
    }

    @Override
    public GeneralId store(General general) {
        GeneralDo generalDo = GeneralConverter.fromGeneral(general);
        this.saveOrUpdate(generalDo);
        return new GeneralId(generalDo.getId());
    }

    @Override
    public boolean remove(Collection<GeneralId> generalIdList) {
        List<Long> ids = new ArrayList<>();
        generalIdList.forEach(generalId -> ids.add(generalId.getId()));
        return this.removeByIds(ids);
    }

    @Override
    public boolean remove(GeneralId generalId) {
        return this.removeById(generalId.getId());
    }

    @Override
    public Set<GeneralId> store(List<General> generalList) {
        List<GeneralDo> generalDoList = ListUtil.listMapCollectToList(generalList, general -> GeneralConverter.fromGeneral(general));
        this.saveOrUpdateBatch(generalDoList);
        return generalDoList.stream().map(generalDo -> new GeneralId(generalDo.getId())).collect(Collectors.toSet());
    }

}

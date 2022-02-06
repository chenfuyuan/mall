package com.learn.project.mall.product.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.mall.product.domain.model.common.Common;
import com.learn.project.mall.product.domain.model.common.CommonId;
import com.learn.project.mall.product.domain.model.common.CommonRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.converter.CommonConverter;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CommonDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.CommonMapper;
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
public class CommonRepositoryImpl extends ServiceImpl<CommonMapper, CommonDo> implements CommonRepository, IService<CommonDo> {


    @Override
    public Common find(CommonId commonId) {
        CommonDo commonDo = this.getById(commonId.getId());
        if (EmptyUtil.isNull(commonDo)) {
            return null;
        }
        return CommonConverter.toCommon(commonDo);
    }

    @Override
    public CommonId store(Common common) {
        CommonDo commonDo = CommonConverter.fromCommon(common);
        this.saveOrUpdate(commonDo);
        return new CommonId(commonDo.getCommonId());
    }

    @Override
    public boolean remove(Collection<CommonId> commonIdList) {
        List<Long> ids = new ArrayList<>();
        commonIdList.forEach(commonId -> ids.add(commonId.getId()));
        return this.removeByIds(ids);
    }

    @Override
    public boolean remove(CommonId commonId) {
        return this.removeById(commonId.getId());
    }

    @Override
    public Set<CommonId> store(List<Common> commonList) {
        List<CommonDo> commonDoList = ListUtil.listMapCollectToList(commonList, common -> CommonConverter.fromCommon(common));
        this.saveOrUpdateBatch(commonDoList);
        return commonDoList.stream().map(commonDo -> new CommonId(commonDo.getCommonId())).collect(Collectors.toSet());
    }

}

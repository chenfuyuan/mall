package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.attrAttrgroupRelation.AttrAttrgroupRelation;
import com.learn.project.mall.product.domain.model.attrAttrgroupRelation.AttrAttrgroupRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 属性&属性分组关联-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Component
public class AttrAttrgroupRelationCreateSpecification extends AbstractSpecification<AttrAttrgroupRelation> {

    /**
     * 仓储服务
     */
    private final AttrAttrgroupRelationRepository attrAttrgroupRelationRepository;

    @Autowired
    public AttrAttrgroupRelationCreateSpecification(AttrAttrgroupRelationRepository attrAttrgroupRelationRepository) {
        this.attrAttrgroupRelationRepository = attrAttrgroupRelationRepository;
    }

    @Override
    public boolean isSatisfiedBy(AttrAttrgroupRelation attrAttrgroupRelation) {
        return true;
    }
}

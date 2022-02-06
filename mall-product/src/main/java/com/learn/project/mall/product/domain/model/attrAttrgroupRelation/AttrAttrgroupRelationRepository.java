package com.learn.project.mall.product.domain.model.attrAttrgroupRelation;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 属性&属性分组关联-仓储服务
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public interface AttrAttrgroupRelationRepository {

    /**
     * 通过id查找
     * @param id id
     * @return
     */
    AttrAttrgroupRelation find(AttrAttrgroupRelationId id);

    /**
     * 存储领域对象
     * @param attrAttrgroupRelation
     */
    AttrAttrgroupRelationId store(AttrAttrgroupRelation attrAttrgroupRelation);

    /**
     * 根据传递过来的id进行删除
     * @param attrAttrgroupRelationIdList id列表
     * @return
     */
    boolean remove(Collection<AttrAttrgroupRelationId> attrAttrgroupRelationIdList);

    /**
     * 根据id进行删除
     * @param attrAttrgroupRelationId id
     * @return
     */
    boolean remove(AttrAttrgroupRelationId attrAttrgroupRelationId);

    Set<AttrAttrgroupRelationId> store(List<AttrAttrgroupRelation> attrAttrgroupRelationList);
}

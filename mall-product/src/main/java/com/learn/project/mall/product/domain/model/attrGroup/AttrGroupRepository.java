package com.learn.project.mall.product.domain.model.attrGroup;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 属性分组-仓储服务
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface AttrGroupRepository {

    /**
     * 通过attrGroupId查找
     * @param attrGroupId id
     * @return
     */
    AttrGroup find(AttrGroupId attrGroupId);

    /**
     * 存储领域对象
     * @param attrGroup
     */
    AttrGroupId store(AttrGroup attrGroup);

    /**
     * 根据传递过来的id进行删除
     * @param attrGroupIdList id列表
     * @return
     */
    boolean remove(Collection<AttrGroupId> attrGroupIdList);

    /**
     * 根据id进行删除
     * @param attrGroupId id
     * @return
     */
    boolean remove(AttrGroupId attrGroupId);

    Set<AttrGroupId> store(List<AttrGroup> attrGroupList);
}

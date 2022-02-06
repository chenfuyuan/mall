package com.learn.project.mall.product.domain.model.attr;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 商品属性-仓储服务
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface AttrRepository {

    /**
     * 通过attrId查找
     * @param attrId id
     * @return
     */
    Attr find(AttrId attrId);

    /**
     * 存储领域对象
     * @param attr
     */
    AttrId store(Attr attr);

    /**
     * 根据传递过来的id进行删除
     * @param attrIdList id列表
     * @return
     */
    boolean remove(Collection<AttrId> attrIdList);

    /**
     * 根据id进行删除
     * @param attrId id
     * @return
     */
    boolean remove(AttrId attrId);

    Set<AttrId> store(List<Attr> attrList);
}

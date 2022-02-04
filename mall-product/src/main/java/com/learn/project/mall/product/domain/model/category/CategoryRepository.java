package com.learn.project.mall.product.domain.model.category;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CategoryDO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 *
 * @author chenfuyuan
 * @date 2022/2/3 23:38
 */
public interface CategoryRepository extends IService<CategoryDO> {

    /**
     * 查询列表
     *
     * @param params
     * @return
     */
    List<Category> queryList(Map<String, Object> params,  QueryWrapper<CategoryDO> wrapper);

    /**
     * 查找分类-通过Id
     * @param categoryId 分类Id
     * @return
     */
    Category find(CategoryId categoryId);

    /**
     * 查找分类路径-通过Id
     * @param categoryId
     * @return
     */
    CategoryPath getCategoryPath(CategoryId categoryId);
}

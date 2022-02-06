package com.learn.project.mall.product.infrastructure.persistence.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.learn.project.common.mybatis.util.BaseDo;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
/**
 * 属性分组-数据库对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
@TableName("pms_attr_group")
public class AttrGroupDo extends BaseDo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 分组id
    */
    @TableId
    private Long attrGroupId;
    /**
    * 组名
    */
    private String attrGroupName;
    /**
    * 排序
    */
    private Integer sort;
    /**
    * 描述
    */
    private String descript;
    /**
    * 组图标
    */
    private String icon;
    /**
    * 所属分类id
    */
    private Long catelogId;

    @Override
    public void inUniqueLabel(Long uniqueLabel) {
        this.attrGroupId = uniqueLabel;
    }

    @Override
    public Long outUniqueLabel() {
        return this.attrGroupId;
    }
}

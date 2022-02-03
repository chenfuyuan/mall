package com.learn.project.common.mybatis.constant;

/**
 * 分页常量类
 *
 * @author chenfuyuan
 * @date 2022/2/3 21:13
 */
public interface PageConstant {
    /**
     * 当前页码
     */
    String PAGE = "page";

    /**
     * 每页显示记录数
     */
    String LIMIT = "limit";

    /**
     * 排序字段
     */
    String ORDER_FIELD = "sidx";

    /**
     * 排序方式
     */
    String ORDER = "order";

    /**
     * 升序
     */
    String ASC = "asc";
}

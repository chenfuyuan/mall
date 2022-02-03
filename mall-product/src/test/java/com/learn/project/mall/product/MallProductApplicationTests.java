package com.learn.project.mall.product;

import com.learn.project.mall.product.application.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@Slf4j
@SpringBootTest
class MallProductApplicationTests {

    @Autowired
    private CategoryService categoryService;

    @Test
    void testGetCategoryPath() {
        Long[] categoryPath = categoryService.findCategoryPathByCatId(1110L);
        log.info("完整路径为:{}",Arrays.toString(categoryPath));
    }

}

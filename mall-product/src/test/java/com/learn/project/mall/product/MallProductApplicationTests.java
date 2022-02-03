package com.learn.project.mall.product;

import com.learn.project.mall.product.entity.CategoryEntity;
import com.learn.project.mall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Locale;

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

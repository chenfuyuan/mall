package com.learn.gulimall.product;

import com.learn.gulimall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @ClassName CategoryTest
 * @Description 测试Category Service方法
 * @Author chenfuyuan
 * @Date 2020-8-9 13:18
 * @Version 1.0
 */
@SpringBootTest
@Slf4j
public class CategoryTest {
    @Autowired
    private CategoryService categoryServicea;

    @Test
    public void testFindPath(){
        Long[] path = categoryServicea.findCategoryPath(225L);

        log.info("path = {}", Arrays.toString(path));

    }
}

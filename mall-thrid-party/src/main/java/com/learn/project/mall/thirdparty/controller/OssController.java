package com.learn.project.mall.thirdparty.controller;

import com.learn.project.common.web.util.R;
import com.learn.project.mall.thirdparty.domain.OssSignature;
import com.learn.project.mall.thirdparty.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OSSController
 *
 * @author chenfuyuan
 * @date 2022/1/6 14:46
 */
@RestController
public class OssController {

    @Autowired
    private OssService ossService;


    @RequestMapping("/oss/signature")
    public R singnature(){
        OssSignature signature = ossService.getSignature();
        return R.ok().put("signature", signature);
    }
}

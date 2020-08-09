package com.learn.gulimall.product.exception;

import com.learn.common.constant.BizCode;
import com.learn.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName gulimallException
 * @Description 统一异常处理
 * @Author chenfuyuan
 * @Date 2020-8-7 23:31
 * @Version 1.0
 */
//开启日志
@Slf4j
//basePackages="拦截路径"
@RestControllerAdvice(basePackages = "com.learn.gulimall.product")
public class GulimallExceptionControllerAdvice {

    /**
     * 处理参数校验异常
     * @author: Vito.Chen
     * @date: 2020-8-8 14:18
     * @param e 校验异常信息
     * @return: com.learn.common.utils.R
     */
        @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        //处理异常
        BindingResult bindingResult = e.getBindingResult();
        //get error message
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        Map<String, String> errorMap = new HashMap<>();
        //遍历添加异常信息
        fieldErrors.forEach(item->{
            errorMap.put(item.getField(), item.getDefaultMessage());
        });
        //返回错误响应信息
        return R.error(BizCode.VALID_EXCEPTION.getCode(),BizCode.VALID_EXCEPTION.getMessage()).put("data",errorMap);
    }

    /**
     * 处理所有未知异常
     * @author: Vito.Chen
     * @date: 2020-8-8 14:37
     * @param e 异常信息
     * @return: com.learn.common.utils.R 响应信息
     */
    @ExceptionHandler(value = Exception.class)
    public R handleException(Exception e){
        return R.error(BizCode.UNKNOW_EXCEPTION.getCode(),BizCode.UNKNOW_EXCEPTION.getMessage()).put("data",e.getMessage());
    }

}

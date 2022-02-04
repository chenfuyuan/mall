package com.learn.project.common.web.util;


import com.learn.project.common.web.constant.ResponseCodeEnum;
import com.learn.project.common.web.exception.BizException;
import com.learn.project.common.web.exception.NoBizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * 处理自定义的业务异常
	 * @param req
	 * @param e
	 * @return
	 */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
	public R bizExceptionHandler(HttpServletRequest req, BizException e){
    	log.error("发生业务异常！原因是：{}",e.getErrorMsg());
    	return R.error(e.getErrorCode(),e.getErrorMsg());
    }

	/**
	 * 处理空指针的异常
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value =NullPointerException.class)
	@ResponseBody
	public R exceptionHandler(HttpServletRequest req, NullPointerException e){
		log.error("发生空指针异常！原因是:",e);
		return R.error(ResponseCodeEnum.BODY_NOT_MATCH);
	}

	@ExceptionHandler(value = NoBizException.class)
	@ResponseBody
	public R exceptionHandler(HttpServletRequest req, NoBizException e) {
		log.error("系统发送错误! 原因是{}", e.getErrorMsg());
		return R.error(ResponseCodeEnum.INTERNAL_SERVER_ERROR);
	}

    /**
        * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
	@ResponseBody
	public R exceptionHandler(HttpServletRequest req, Exception e){
    	log.error("未知异常！原因是:",e);
       	return R.error(ResponseCodeEnum.INTERNAL_SERVER_ERROR);
    }

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseBody
	public R exceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
		log.error("数据校验出现问题{},异常类型:{}", e.getMessage(), e.getClass());
		BindingResult bindingResult = e.getBindingResult();
		Map<String, String> errorMap = new HashMap<>();
		bindingResult.getFieldErrors().forEach((error)->{
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return R.error(ResponseCodeEnum.DATA_VALIDATE_ERROR).put("error", errorMap);
	}
}

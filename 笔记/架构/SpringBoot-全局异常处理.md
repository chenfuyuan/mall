# SpringBoot-全局异常处理
## 1. 创建公共模块统一进行全局异常处理
创建Maven子模块 ``**-common``存放全局异常处理代码
## 2. 声明异常枚举类
接口``BaseErrorInfoInterface``
```java
public interface BaseErrorInfoInterface {  
    /** 错误码*/  
 String getResultCode();  
     
   /** 错误描述*/  
 String getResultMsg();  
}
```

枚举类``ResponseCodeEnum``
```java
public enum ResponseCodeEnum implements BaseErrorInfoInterface {  
   // 数据操作错误定义  
 SUCCESS("200", "成功!"),  
   BODY_NOT_MATCH("400","请求的数据格式不符!"),  
   SIGNATURE_NOT_MATCH("401","请求的数字签名不匹配!"),  
   NOT_FOUND("404", "未找到该资源!"),  
   INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),  
   SERVER_BUSY("503","服务器正忙，请稍后再试!"),  
   DATA_VALIDATE_ERROR("405","数据校验失败!")  
   ;  
  
   /** 错误码 */  
 private String resultCode;  
  
   /** 错误描述 */  
 private String resultMsg;  
  
   ResponseCodeEnum(String resultCode, String resultMsg) {  
      this.resultCode = resultCode;  
      this.resultMsg = resultMsg;  
   }  
  
   @Override  
 public String getResultCode() {  
      return resultCode;  
   }  
  
   @Override  
 public String getResultMsg() {  
      return resultMsg;  
   }  
  
}
```

## 3. 业务异常类
业务异常类``BizException``，运行时异常，主要处理运行时遇到的业务异常。
```java
public class BizException extends RuntimeException {  
  
   private static final long serialVersionUID = 1L;  
  
   /**  
 * 错误码  
 */  
 protected String errorCode;  
   /**  
 * 错误信息  
 */  
 protected String errorMsg;  
  
   public BizException() {  
      super();  
   }  
  
   public BizException(BaseErrorInfoInterface errorInfoInterface) {  
      super(errorInfoInterface.getResultCode());  
      this.errorCode = errorInfoInterface.getResultCode();  
      this.errorMsg = errorInfoInterface.getResultMsg();  
   }  
     
   public BizException(BaseErrorInfoInterface errorInfoInterface, Throwable cause) {  
      super(errorInfoInterface.getResultCode(), cause);  
      this.errorCode = errorInfoInterface.getResultCode();  
      this.errorMsg = errorInfoInterface.getResultMsg();  
   }  
     
   public BizException(String errorMsg) {  
      super(errorMsg);  
      this.errorMsg = errorMsg;  
   }  
     
   public BizException(String errorCode, String errorMsg) {  
      super(errorCode);  
      this.errorCode = errorCode;  
      this.errorMsg = errorMsg;  
   }  
  
   public BizException(String errorCode, String errorMsg, Throwable cause) {  
      super(errorCode, cause);  
      this.errorCode = errorCode;  
      this.errorMsg = errorMsg;  
   }  
     
  
   public String getErrorCode() {  
      return errorCode;  
   }  
  
   public void setErrorCode(String errorCode) {  
      this.errorCode = errorCode;  
   }  
  
   public String getErrorMsg() {  
      return errorMsg;  
   }  
  
   public void setErrorMsg(String errorMsg) {  
      this.errorMsg = errorMsg;  
   }  
  
   @Override  
 public String getMessage() {  
      return errorMsg;  
   }  
  
   @Override  
 public Throwable fillInStackTrace() {  
      return this;  
   }  
  
}
```

## 4. 全局异常管理
全局异常控制类``GlobalExceptionHandler``，处理程序运行时发送的各类异常。
```java
@Slf4j  
@ControllerAdvice  
public class GlobalExceptionHandler {  
     
   /**  
 * 处理自定义的业务异常  
 * @param req  
 * @param e  
 * @return  
 */ @ExceptionHandler(value = BizException.class)  
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
 */ @ExceptionHandler(value =NullPointerException.class)  
   @ResponseBody  
 public R exceptionHandler(HttpServletRequest req, NullPointerException e){  
      log.error("发生空指针异常！原因是:",e);  
      return R.error(ResponseCodeEnum.BODY_NOT_MATCH);  
   }  
  
  
    /**  
 * 处理其他异常  
 * @param req  
 * @param e  
 * @return  
 */ @ExceptionHandler(value =Exception.class)  
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
```
- ``@ControllerAdvice``，作用是给Controller控制器添加统一的操作或处理。用于对Controller进行切面环绕的，而具体的业务织入方式则是通过结合其他的注解来实现的
- ``@ExceptionHandler(value="xxxException.class")``定义处理的异常类型
- ``@ResponseBody``，标记转化成Json格式

## 5. 发现全局异常配置
在``resources``目录下，创建``META-INF``文件夹，并创建``spring.factories``文件
spring.factories文件
```properties
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\  
 com.learn.project.common.exception.GlobalExceptionHandler, \  
 com.learn.project.common.config.WebServerConfig
```
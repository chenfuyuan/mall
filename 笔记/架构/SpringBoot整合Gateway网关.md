# SpringBoot整合Gateway网关

官网文档[https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/)

## 1. 引入依赖
```xml
<!--gateway网关-->  
<dependency>  
    <groupId>org.springframework.cloud</groupId>  
    <artifactId>spring-cloud-starter-gateway</artifactId>  
</dependency>
```

## 2. 配置网关

```yaml
spring:
    gateway:
      routes:
        - id: coupon
          uri: lb://coupon
          predicates:
            - Path=/coupon/**

        - id: member
          uri: lb://member
          predicates:
            - Path=/member/**

        - id: order
          uri: lb://order
          predicates:
            - Path=/order/**

        - id: product
          uri: lb://product
          predicates:
            - Path=/product/**

        - id: ware
          uri: lb://ware
          predicates:
            - Path=/ware/**

        - id: admin
          uri: lb://admin
          predicates:
            - Path=/api/**
          filters:
            - RewritePath=/api/?(?<segment>.*), /renren-fast/$\{segment}
```

``spring.gateway.routes``配置网关，存放数组

- id: 标识
- uri: 匹配成功后，转换的链接
  - lb: 表示当前的注册中心。其后跟 注册的服务名
- predicates: 谓语。判断。用于匹配路径
  - Path: 路径匹配
- filters: 过滤器。用于修改传入的HTTP请求



## 3. 运行

通过网关会对传入的HTTP进行转换到相关的服务及端口。

如 ``localhost:88/api/captcha.jpg``会被id为``admin``的网关匹配到。会通过过滤器对请求进行处理为``localhost:8080/renren-fast/captcha.jpg``


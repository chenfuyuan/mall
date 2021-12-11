# SpringCloud Gateway解决跨域问题
## 解决方案
### 配置类方式
```java
@Configuration
public class GlobalCorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        //请求头
        configuration.addAllowedHeader("*");
        //方法
        configuration.addAllowedMethod("*");
        //源
        //configuration.addAllowedOrigin("*"); 不能包含特殊值*,它不能在Access-Control-Allow-Origin被设置。应该换成addAllowedOriginPattern("*")
        //configuration.addAllowedOrigin("*");
        configuration.addAllowedOriginPattern("*");
        //cookie
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return new CorsWebFilter(source);
    }
}

```
### application方式

```properties
#--------gateway配置--------
#跨域配置
spring.cloud.gateway.globalcors.corsConfigurations.[/**].allowedOriginPattern=*
spring.cloud.gateway.globalcors.corsConfigurations.[/**].allowedHeaders=*
spring.cloud.gateway.globalcors.corsConfigurations.[/**].allowedMethods=*
spring.cloud.gateway.globalcors.corsConfigurations.[/**].allowCredentials=true
```

### yml方式

```yaml
spring:
  cloud:
    gateway:
      globalcors:
        corsConfigurations:
          '[/**]':
            allowCredentials: true
            allowedHeaders: '*'
            allowedMethods: '*'
            allowedOriginPattern: '*'
```


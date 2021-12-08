# OpenFeign问题
## 整合OpenFeign启动报错(Did you forget to include spring-cloud-starter-loadbalancer)
### 问题
整合OpenFeign时，启动报错.异常提示``Did you forget to include spring-cloud-starter-loadbalancer``

### 原因
由于``SpringCloud Feign``在``Hoxton.M2 RELEASED``版本之后不再使用``Ribbon``而是使用``spring-cloud-loadbalancer``，所以不引入``spring-cloud-loadbalancer``会报错。

### 解决方案
加入``spring-cloud-loadbalancer``依赖 并且在nacos中排除ribbon依赖，不然loadbalancer无效。

#### 加入``spring-cloud-loadbalancer``依赖
```xml
        <!--loadbalancer-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-loadbalancer</artifactId>
        </dependency>
```

#### 在``nacos``中排除``ribbon``依赖
```xml
        <!--nacos-discovery-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.cloud</groupId>
                    <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
```
# SpringBoot整合Nacos作注册中心
## 1. 引入依赖
引入spring-cloud-alibaba依赖管理

```xml
    <dependencyManagement>
        <dependencies>
            <!--spring-cloud-alibaba-->
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>${spring-cloud-alibaba.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
    
        </dependencies>
	</dependencyManagement>
```

引入nacos-discovery依赖

```xml
        <!--nacos-discovery-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
```

## 2. 配置配置文件

在``application.yml``中进行配置

```yaml
spring:
  application:
    name: xxx
  cloud:
    nacos:
      discovery:
        server-addr: ip地址:端口(8848)
```

## 3. 开启注册发现

在``**Application.java``启动类中，添加注解``@EnableDiscoveryClient``

```java
@MapperScan("com.learn.project.mall.product.dao")
@EnableDiscoveryClient    //开启服务注册与发现
@SpringBootApplication
public class MallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallProductApplication.class, args);
    }

}

```


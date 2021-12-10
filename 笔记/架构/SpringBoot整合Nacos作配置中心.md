# SpringBoot整合Nacos作配置中心

参考文档[https://github.com/alibaba/spring-cloud-alibaba/wiki/Nacos-config](https://github.com/alibaba/spring-cloud-alibaba/wiki/Nacos-config)

## 1. 引入依赖

### 1.1 引入nacos-config依赖

版本由``spring-cloud-alibaba-dependencies``控制

```xml
<!--nacos-config 配置中心-->  
<dependency>  
    <groupId>com.alibaba.cloud</groupId>  
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>  
</dependency>
```

## 2. 配置

新建``resource/bootstrap.properties``或``resource/bootstrap.yml``配置文件，进行``nacos.config``配置

配置内容

```properties
spring.application.name=coupon
spring.cloud.nacos.config.server-addr=192.168.249.4:8848
#spring.profiles.active=develop
```

- spring.application.name: 应用名。nacos将自动从配置中心读取``应用名.properties``配置文件
- spring.cloud.nacos.config.server-addr:nacos的访问ip及端口

- spring.profiles.active:环境。如果配置了这个nacos将自动从配置中心读取``应用名.环境.proerties``配置文件(也会读取``应用名.properties``配置文件)

### 3. 配置细粒化

nacos通过**命名空间(namespace)**、**配置集(group)**、**配置文件ID(Data ID)**进行划分。

### 3.1 命名空间

![image-20211210155938572](https://chenfuyuan-markdown-img.oss-cn-shenzhen.aliyuncs.com/20211210155940.png)

操作:登录nacos -> 命名空间 -> 新建命名空间 

推荐:将各个环境通过命名空间进行区隔。如使用``dev``作为开发环境，``test``作为测试环境，``production``作为生产环境



### 3.2 配置集

在配置管理->配置列表->新建配置中

![image-20211210160227124](https://chenfuyuan-markdown-img.oss-cn-shenzhen.aliyuncs.com/20211210160228.png)

Group代表配置集。

推荐:通过配置集区隔各个服务配置。如``product``作为商品服务配置，``coupon``作为优惠券服务配置

### 3.3 配置文件ID

![image-20211210160455598](https://chenfuyuan-markdown-img.oss-cn-shenzhen.aliyuncs.com/20211210160456.png)

Data ID即为配置文件ID。用于区分不同配置文件。同一个Group不可以有相同Data ID的配置文件。不同Group可以有相同Data ID的文件。

Data ID可以用来区分不同配置。如``mybatis.yml``用于存放Mybatis的配置文件.``datasouce.yml``用于存放数据源配置文件等。



## 3. 读取多配置

在``resource/bootstrap.properties``文件中进行配置。相关配置信息如下

```yaml
spring:
  application:
    name: coupon
  cloud:
    nacos:
      config:
        shared-configs:
          - dataId: mybatis.yml
            group: global
            refresh: true
        extension-configs:
          - dataId: other.properties
            group: coupon
            refresh: true
          - dataId: datasouce.yml
            group: coupon
            refresh: true
```

- shared-configs:共享配置
  - dataId: dataId
  - group: 配置集
  - refresh: 是否动态刷新
- extension-configs:扩展配置



## 4. 配置文件优先级

### 4.1 配置文件读取顺序

1.  bootstrap.properties/yml
2. bootstrap-``profile``.properties/yml
3. nacos ``shared-configs``配置(按照索引顺序进行读取)
4. nacos ``extension-configs``配置(按照索引顺序进行读取)
5. ``应用名.properties/yml``
6. ``应用名.环境.properties/yml``

### 4.2 优先级

文件读取顺序，从下往上。后读取的如果遇到相同配置，会覆盖之前的配置。


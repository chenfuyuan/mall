# Mybatis-Plus整合p6spy实现sql分析

## 1. 引入依赖

```xml
        <!-- https://mvnrepository.com/artifact/p6spy/p6spy 用于分析sql -->
        <dependency>
            <groupId>p6spy</groupId>
            <artifactId>p6spy</artifactId>
            <version>${p6spy.version}</version>
        </dependency>
```

## 2. 配置``application.yml``文件

```yaml
spring:
  datasource:
    driver-class-name: com.p6spy.engine.spy.P6SpyDriver
    url: jdbc:p6spy:mysql://192.168.249.4:3306/mall_pms?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    username: root
    password: 123456

logging:
  level:
    com.kaven.mybatisplus.dao: trace
  pattern:
    console: '%p%m%n'
```

- 将数据库驱动替换为``com.p6spy.engine.spy.P6SpyDriver``
- 修改url,前缀由``jdbc:``修改为``jdbc:p6spy:``
- 设置日志``logging.level.com.kaven.mybatisplus.dao=trace``和``logging.pattern.console='%p%m%n'``

## 3. 配置``spy.properties``文件
该配置文件，目前测试无法从nacos上读取，必须放在``resource\``目录下[[TODO]] 

```properties
#3.2.1以上使用
modulelist=com.baomidou.mybatisplus.extension.p6spy.MybatisPlusLogFactory,com.p6spy.engine.outage.P6OutageFactory
#3.2.1以下使用或者不配置
#modulelist=com.p6spy.engine.logging.P6LogFactory,com.p6spy.engine.outage.P6OutageFactory
# 自定义日志打印
logMessageFormat=com.baomidou.mybatisplus.extension.p6spy.P6SpyLogger
#日志输出到控制台
appender=com.baomidou.mybatisplus.extension.p6spy.StdoutLogger
# 使用日志系统记录 sql
#appender=com.p6spy.engine.spy.appender.Slf4JLogger
# 设置 p6spy driver 代理
deregisterdrivers=true
# 取消JDBC URL前缀
useprefix=true
# 配置记录 Log 例外,可去掉的结果集有error,info,batch,debug,statement,commit,rollback,result,resultset.
excludecategories=info,debug,result,commit,resultset
# 日期格式
dateformat=yyyy-MM-dd HH:mm:ss
# 实际驱动可多个
#driverlist=org.h2.Driver
# 是否开启慢SQL记录
outagedetection=true
# 慢SQL记录标准 2 秒
outagedetectioninterval=2
```


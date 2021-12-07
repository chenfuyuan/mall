# SpringBoot整合Mybatis-Plus
## 1. 引入依赖
在pom.xml中引入依赖

### 1.1 引入mybatis-plus

```
<!--Mybatis-plus-->  
<dependency>  
    <groupId>com.baomidou</groupId>  
    <artifactId>mybatis-plus-boot-starter</artifactId>  
    <version>${mybatis-plus.version}</version>  
</dependency>
```
### 1.2 引入mysql-connect -java

该依赖用于连接mysql数据库

```
<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
	<groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>${mysql-connector-java.version}</version>
</dependency>
```

## 2. 配置
### 2.1 配置数据源

在``resources``目录下创建配置文件``application.yml``

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://192.168.249.4:3306/mall_pms?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    username: root
    password: 123456
```

### 2.2 配置Mybatis-Plus

#### 配置扫描器

在启动类``**Application.java``中，添加``@MapperScan("扫描路径")``注解

```java
@MapperScan("com.learn.project.mall.product.dao")
@SpringBootApplication
public class MallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallProductApplication.class, args);
    }

}
```

#### 其他配置

在``application.yml``中进行配置

```yaml
mybatis-plus:
  #配置mapper.xml扫描路径
  mapper-locations: classpath*:/mapper/**/*.xml
  global-config:
    db-config:
      #配置数据库主键 自增
      id-type: auto
```

- **mybatis-plus.mapper-locations**:配置``**mapper.xml``扫描路径。``**mapper.xml``用于存放自定义的sql
  - ``classpath``:只扫描当前模块目录下
  - ``classpath*``:扫描当前模块及所依赖的包
- **mybatis-plus.global-config.db-config.id-type**:用于制定主键规则
  - auto:代表主键自增

## 3. 使用

### 3.1 添加

```java
    @Test
    public void test_add() {
        BrandEntity brand = new BrandEntity();
        brand.setName("HuaWei");
        brandService.save(brand);
        System.out.println(brand.getBrandId());
        System.out.println("保存成功。");
    }
```

### 3.2 查询

```java
    @Test
    public void test_list() {
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "HuaWei");
        List<BrandEntity> list = brandService.list(queryWrapper);
        System.out.println(list);
    }

```

### 3.3 删除

```java
    @Test
    public void test_delete() {
        QueryWrapper<BrandEntity> deleteWrapper = new QueryWrapper<>();
        //设置匹配条件
        deleteWrapper.eq("name", "HuaWei");
        brandService.remove(deleteWrapper);
    }
```

### 3.4 更新

```java
    @Test
    public void testUpdate() {
        UpdateWrapper<BrandEntity> updateWrapper = new UpdateWrapper<>();
        //设置匹配条件
        updateWrapper.eq("name", "Apple");

        //设置修改值
        updateWrapper.set("name", "HuaWei");

        brandService.update(updateWrapper);

    }
```

需要设置查询条件和修改值，不然会报错


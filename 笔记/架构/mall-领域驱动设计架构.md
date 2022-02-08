# mall项目架构
**参考网站**
1. [https://gitee.com/xtoon/xtoon-cloud](https://gitee.com/xtoon/xtoon-cloud)

## 项目包组织架构
项目总共分为四层架构: api(接口层)、application(应用层)、domain(领域层)、infrastructure(基础设施层)

```
project
├──api    接口层
│    ├──controller    web端访问后端的controller存放处
│    ├──feign    远程调用feign接口，存放处
├──application    应用层
|    ├──assembler    装配器(model->dto、command->model)
|    ├──command     命令对象
|    ├──dto    数据访问对象
|    ├──service    服务
├──domain
|    ├──model    领域对象
|    ├──specification    规格类
|    ├──service     领域服务
├──infrastructure    基础设施
|    ├──constant    常量
|    ├──persistence    持久化
|           ├──mybatis    mybatis框架代码存放处
|                  ├──do    数据对象
|                  ├──mapper    mybatie接口
|                  ├──convert    转换器(将do->model,model->do)
|           ├──redis    存放redis代码
|    ├──util    工具类
|    ├──config    配置
```



### 1.api
该层主要存放向外界提供的接口。该层包含controller和远程调用的feign

### 2. application

该层主要存放 assembler(装配器)、command(命令)、dto(数据交换对象)、service(服务)、serviceImpl(服务实现类)

#### assembler

装配器

1. 用于将前端传递过来的command(命令对象)转换成对应的领域对象(model)
2. 将领域对象转换成对应的dto(数据传输对象)

#### command

命令对象，用于接收前端传递过来的命令(command)。

项目采用[[CQRS架构]]，将命令和查询分离。

接收到前端的命令后，通过Assembler装配器将command对象转换成领域对象，进行业务处理。

#### dto

数据传输对象

来源:

1. 直接通过仓储服务(repository)查询DO，组装成DTO返回给前端。
2. 通过领域对象查询后，借助Assembler对象组装成dto返回给前端。

#### service

服务,分为查询服务(QueryService)和命令服务(CommandService)。分别作用与查询和命令。

### 3. domain

领域层，主要包含model、specification、service

#### model

领域对象，基于领域对象进行操作。

#### specification

规范校验。校验传递过来的命令command是否符合要求。

可以传入repository，进行唯一性校验等操作

### 4. infrastructure

基础设施层，主要为其他层提供服务。constant(常量类包)、persistance(持久化层)、util(工具类)

#### persistance

持久化层，用于存放访问数据库的类。如各类数据库访问框架，mybatis、hibernate等。也可以存访问redis、mongoDB的类。

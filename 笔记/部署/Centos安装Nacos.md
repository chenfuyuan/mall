# Centos安装Nacos

参考博客[https://www.jianshu.com/p/9944e9f4771f](https://www.jianshu.com/p/9944e9f4771f)

## 1. 下载
Nacos下载地址:[https://github.com/alibaba/nacos/releases](https://github.com/alibaba/nacos/releases)

选择``nacos-server-*.*.*.tar.gz``下载



## 2. 解压

将下载后的tar包上传到Centos服务器中，并打开到上传目录进行解压。

```
tar -zxvf nacos-server-2.0.3.tar.gz -C /usr/soft/
```

我解压到``/usr/soft/``目录下



## 3. 编辑配置文件

```
vim /usr/soft/nacos/conf/application.properties
```

编辑内容:将以下内容注释去掉，并修改内容

```properties
spring.datasource.platform=mysql
db.num=1
db.url.0=jdbc:mysql://127.0.0.1:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
#数据库用户名密码根据情况输入自己的
db.user=xxx
db.password=xxx
```

## 4. 初始化数据库

数据库初始化脚本在``/usr/soft/nacos/conf/nacos-mysql.sql``

### 4.1 建立数据库

进入mysql

```mysql
mysql -uroot -p****
```

创建数据库

```mysql
Create database if not exists nacos;
```

### 4.2 执行sql脚本

进入nacos数据库,并执行sql脚本

```mysql
> use nacos;
> source /usr/soft/nacos/conf/nacos-mysql.sql
```



## 5. 启动nacos

### 5.1 单机模式启动

需要首先有jdk环境。[[Centos安装jdk]]

```shell
sh /usr/soft/nacos/bin/startup.sh -m standalone
```

浏览器访问地址: ``http://ip:8848/nacos``

输入默认账号密码: nacos/nacos

启动日志存放位置:``/安装目录/logs/start.out``

### 5.2 集群模式启动

新建集群模式配置文件

``/usr/soft/nacos/conf/cluster.conf``

编辑格式如下

```
//IP:PORT
192.168.249.4:8848
192.168.249.5:8848
192.168.249.6:8848
```

启动

```shell
# 集群版启动命令 不带任何参数
sh /usr/local/nacos/bin/startup.sh
```


## 6. 配置防火墙
[[Centos防火墙操作#firwall#5、查询、开放、关闭端口]]
```
开启8848端口
firewall-cmd --permanent --add-port=8848/tcp

#重启防火墙生效配置
firewall-cmd --reload
```

## 7. 设置开机自启

将nacos添加到守护进程并设置其开机自启

```shell
# 新建并编辑 /etc/systemd/system/nacos.service 文件
vim /etc/systemd/system/nacos.service

# 添加内容如下
[Unit]
Description=nacos
After=network.target

[Service]
Type=forking

# 集群版 见后面 -m standalone 去掉即可
ExecStart=/usr/soft/nacos/bin/startup.sh -m standalone
ExecReload=/usr/soft/nacos/bin/shutdown.sh
ExecStop=/usr/soft/nacos/bin/shutdown.sh
PrivateTmp=true

[Install]
WantedBy=multi-user.target
```


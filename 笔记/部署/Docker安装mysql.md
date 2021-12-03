# Docker安装mysql
## 1. 下载镜像
上镜像仓库Docker Hub[https://hub.docker.com/](https://hub.docker.com/)查找mysql支持的版本。

使用``docker pull 镜像名``命令下载镜像

```
docker pull mysql:5.7.36
```

## 2. 根据镜像创建实例

创建代码：
```
docker run -p 3306:3306 --name mysql \
-v /mydata/mysql/data:/var/lib/data \
-v /mydata/mysql/log:/var/log/mysql \
-v /mydata/mysql/conf:/etc/mysql \
-e MYSQL_ROOT_PASSWORD=root \
-d mysql:5.7.36
```

参数说明:

- ``-p 3306:3306``: 将容器的3306端口映射到主机的3306端口
- ``-v /mydata/mysql/data:/var/lib/data``: 将数据挂载到主机
- ``-v /mydata/mysql/log:/var/log/mysql``:将日志文件挂载到主机
- ``-v /mydata/mysql/conf:/etc/mysql``将配置文件挂载到主机
- ``-e MYSQL_ROOT_PASSWORD=root``初始化root密码为``root``

可能发生``Error response from daemon: cannot start a stopped process: unknown.``错误

解决方案参考:[[docker启动实例报错#Error response from daemon cannot start a stopped process unknown]]

解决方案为``yum install -y libseccomp-devel``

## 3. 配置数据库
在挂载目录``/mydata/mysql/conf``中，创建``my.cnf``配置文件
```
vim /mydata/mysql/conf/my.cnf
```

my.cnf文件内容:
```
[client]
port = 3306
default-character-set=utf8

[mysql]
default-character-set=utf8

[mysqld]
port = 3306
init_connect='SET collation_connection = utf8_unicode_ci'
init_connect='SET NAMES utf8'
character-set-server=utf8
collation-server=utf8_unicode_ci
skip-character-set-client-handshake
skip-name-resolve

```

## 问题:
- [[mysql连接慢问题]]
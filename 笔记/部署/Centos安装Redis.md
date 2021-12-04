# Centos安装Redis
## 安装
### 1. 使用``dnf``安装
```
dnf install redis -y
```
### 2. 从官网下载
官网地址[https://redis.io/](https://redis.io/)

#### 2.1 解压

```shell
tar -zvxf redis-6.2.6.tar.gz -C /usr/soft/
```

解压到``/usr/soft/``目录下



#### 2.2 编译并安装

```
cd /usr/soft/redis-6.2.6/src

make
make install PREFIX=/var/lib/redis
```

PREFIX指定安装路径



make可能报错，因为缺失gcc等依赖。参考笔记[[make报错]]



依赖安装

```shell
yum install cpp
yum install binutils
yum install glibc
yum install glibc-kernheaders
yum install glibc-common
yum install glibc-devel
yum install gcc
yum install make
```

如果之前编译失败过时，需要清理原来编译的文件

```
make distclean

make
```



安装成功后，需要将配置文件移动到安装后的路径中,配置文件存放位置为``/usr/soft/redis-6.2.6/redis.conf``，需要将其移动到``redis的安装路径中``

```
cp /usr/soft/redis-6.2.6/redis.conf /usr/soft/redis/bin/ 
```



## 使用

```
systemctl start redis #启动
systemctl stop redis #停止
systemctl status redis #查看 redis 状态
```

使用``dnf``安装的可直接使用。

### 配置Systemd启动

使用压缩包安装的需要配置。Systemd的使用教程[https://zhuanlan.zhihu.com/p/29217941](https://zhuanlan.zhihu.com/p/29217941)



需要在目录``/usr/lib/systemd/system/``，手动创建文件``redis.service``

```
vim /usr/lib/systemd/system/redis.service
```

``redis.service``文件内容

```
[Unit]
Description=Redis
After=network.target

[Service]
Type=forking    
ExecStart=/usr/soft/redis/bin/redis-server /usr/soft/redis/bin/redis.conf
KillSignal=SIGTERM
KillMode=mixed
[Install]
WantedBy=multi-user.target
```

- ExecStart中的路径信息需要修改为你的redis安装路径
- Type为程序运行类型
  - 如果ExecStart后面跟的程序，为在前台持续运行，则Type=simple
  - 如果ExecStart后面跟的程序，为在后台持续运行，则Type=forking
  - 如果ExecStart后面跟的程序，为在前台运行一下就退出，则Type=oneshot

设置完后，需要运行重载配置并设置开机自启

```
systemctl daemon-reload #重载服务列表
systemctl enable redis.service #添加开机自启
```

``journalctl -u xxxx.service``可查看对应服务的``systemctl``运行日志

## 修改配置
官方文档[https://redis.io/topics/config](https://redis.io/topics/config)
```
bind 127.0.0.1 #默认只允许本机访问，如果需要开放外网，则注释掉这行
protected-mode yes #保护模式，如果需要开放外网，则改为 no
port 6379 #可修改端口
requirepass password #登录密码，该配置默认是注释的，放开之后在登录时需要使用如下方式登录
daemonize yes #修改为yes，支持后台运行
logfile "/data/redis/logs/redis.log"    #设置log日志存放位置
dir /data/redis/data/    #设置数据存放位置
appendonly yes     # 开启aof持久化
```



## 配置环境变量

修改``/etc/profile``文件,在文件末尾添加当前信息

```
#Redis环境变量
export REDIS_HOME=/usr/soft/redis
export PATH=$REDIS_HOME/bin:$PATH
```

- REDIS_HOME:为redis安装地址

使环境变量生效

```
source /etc/profile
```


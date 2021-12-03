# Centos安装Mysql

## 1. 下载Mysql

下载地址[https://dev.mysql.com/downloads/mysql/5.7.html#downloads](https://dev.mysql.com/downloads/mysql/5.7.html#downloads)

![image-20211203145306305](https://chenfuyuan-markdown-img.oss-cn-shenzhen.aliyuncs.com/20211203145307.png)

或使用网络下载

```
wget -O ./mysql-5.7.36.tar.gz https://dev.mysql.com/get/Downloads/MySQL-5.7/mysql-5.7.36-linux-glibc2.12-x86_64.tar.gz
```

## 2. 解压

```
tar -zxvf mysql-5.7xxxxxxxx.tar.gz -C 解压路径
tar -zxvf mysql-5.7.36-linux-glibc2.12-x86_64.tar.gz -C /usr/soft/
```

## 3. 更改解压后的文件名

更改解压后的文件名为``mysql``

```
mv mysql-5.7.36-linux-glibc2.12-x86_64 mysql
```

## 4. 创建mysql用户组和用户，并修改权限

```
groupadd mysql
useradd -r -g mysql mysql
```

接着创建数据目录并赋予权限，使用的命令如下（注意必须使用mysql用户，不能使用root用户，否则会由于文件从属关系导致mysql启动失败）：

```
mkdir -p  /data/mysql              #创建目录
chown mysql:mysql -R /data/mysql   #赋予权限
```

## 5. 创建配置文件my.cnf

```
vim /etc/my.cnf
```

my.cnf文件内容

```
[mysqld]
bind-address=0.0.0.0
port=3306
user=mysql
basedir=/usr/soft/mysql  # mysql安装目录
datadir=/data/mysql  # 数据存放目录
socket=/tmp/mysql.sock
log-error=/data/mysql/mysql.err
pid-file=/data/mysql/mysql.pid
#character config
character_set_server=utf8mb4
symbolic-links=0
explicit_defaults_for_timestamp=true
```

## 6. 初始化数据库

进入mysql的bin目录。``/usr/soft/mysql/bin``，执行代码(其中两个路径需要与my.cnf配置文件中的配置相匹配)

```
./mysqld --defaults-file=/etc/my.cnf --basedir=/usr/soft/mysql/ --datadir=/data/mysql/ --user=mysql --initialize
```

可能会出现，看不到启动日志的情况。要想从日志中查看初始密码，需要查看``/data/mysql/mysql.err``

## 7. 启动mysql服务

将``support-files/mysql.server``放置到``/etc/init.d/mysql``中，可以让dameon来管理Mysql的启动(即也就是service,Centos7中是syetemctl)

```
cp /usr/soft/mysql/support-files/mysql.server /etc/init.d/mysql
```

启动mysql

```
service mysql start
```

可能会报错，[参考博客](https://blog.csdn.net/eagle89/article/details/79813405)

查看是否启动成功

```
ps -ef|grep mysql
```

## 8. 修改数据库密码

进入``/usr/soft/mysql/bin``目录。使用初始化数据库时生成的随机密码，进入数据库

```
./mysql -uroot -p临时密码
```

可能会出现``mysql: error while loading shared libraries: libncurses.so.5: cannot open shared object fil``错误

解决方案:

安装相关依赖

```shell
yum install libncurses*
```

进入数据库界面后，使用下列三行代码，修改密码

```mysql
SET PASSWORD = PASSWORD('修改后的密码');
ALTER USER 'root'@'localhost' PASSWORD EXPIRE NEVER;
FLUSH PRIVILEGES; 
```

## 9. 开放访问IP端口

```mysql
use mysql;     #访问mysql库
update user set host = '%' where user = 'root';   #使root用户能在任何IP进行访问
FLUSH PRIVILEGES;
```

## 10.添加mysql快捷指令

```
ln -s  /usr/soft/mysql/bin/mysql    /usr/bin
```

配置后，即可使用``mysql -uroot -p密码``直接运行，不需要跑到``/mysql/bin``目录下运行



## 可能会出现的问题

可能会出现[[连接远程mysql报10060Error]]

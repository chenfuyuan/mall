# Docker安装Redis
## 1. 下载
```
docker pull redis
```

## 2. 安装

在安装前，需要先创建``redis.conf``文件，如果没有创建，在挂载目录时，会把``redis.conf``当成一个目录而不是文件。

```
mkdir -p /mydata/redis/conf/
touch /mydata/redis/conf/redis.conf
```

安装redis

```
docker run -p 6379:6379 --name redis -v /mydata/redis/data:/data \
> -v /mydata/redis/conf/redis.conf:/etc/redis/redis.conf \
> -d redis redis-server /etc/redis/redis.conf
```

## 3. 配置Redis

安装后的redis配置文件为空，需自行去配置。

配置文件为``/mydata/redis/conf/redis.conf``，具体配置可参考[[Centos安装Redis#修改配置]]

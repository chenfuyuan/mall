# Centos安装Docker
参考文档:[https://docs.docker.com/engine/install/centos/](https://docs.docker.com/engine/install/centos/)

## 1. 卸载Docker
```shell
sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
```

## 2. 使用库安装
Install using the repository



### 2.1 设置repository

安装``yum-utils``

```
 sudo yum install -y yum-utils
 
 sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
```

### 2.2 install Docker Engine

```
sudo yum install --allowerasing docker-ce docker-ce-cli containerd.io 
```

需要添加``--allowerasing``

此命令会安装 Docker，但不会启动 Docker。它还会创建一个 `docker`组，但是，默认情况下它不会向该组添加任何用户。

#### 如果需要安装指定版本

- 查看存储库中可安装的版本

  ```
  yum list docker-ce --showduplicates | sort -r
  ```

- 安装指定版本

  ```
  sudo yum install docker-ce-<VERSION_STRING> docker-ce-cli-<VERSION_STRING> containerd.io
  ```



## 3. 启动

```
sudo systemctl start docker
```



## 4. 配置镜像加速器

配置阿里云镜像加速器[https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors](https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors)

```
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://ieulj2q9.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```

- ``sudo systemctl daemon-reload``重新加载配置文件


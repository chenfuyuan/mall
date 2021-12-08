# 连接远程的Nacos报错
## 1. 服务无法注册到nacos，抛异常Client not connected,current status:STARTING
参考博客[https://blog.csdn.net/weixin_42781291/article/details/120960525](https://blog.csdn.net/weixin_42781291/article/details/120960525)
### 问题
运行windows本地的nacos可以正常注册服务。但是使用远程的Linux服务器，则会抛出异常``Client not connected,current ``。

### 原因
2.X之后的nacos版本增加了grpc远程通信端口(**9848**)，也需要由docker一并映射出来，否则就会到时无法初始化连接。

### 解决方案
防火墙开启9848端口
```
firewall-cmd --permanent --add-port=9848/tcp

firewall-cmd --reload
```
[Linux防火墙配置 - 知乎](https://zhuanlan.zhihu.com/p/290709030)

CentOS7防火墙默认使用的是firewall，Centos 6.x使用iptables。

## iptables

### 1. 查看防火墙状态

```text
service iptables status  
```

### 2. 停止防火墙

```text
service iptables stop  
```

### 3. 启动防火墙

```text
service iptables start
```

### 4. 重启防火墙

```text
service iptables restart  
```

### 5. 永久关闭防火墙

```
chkconfig iptables off  
```

### 6. 开启某个端口

```
vim /etc/sysconfig/iptables
```

加入代码

```
-A INPUT -m state --state NEW -m tcp -p tcp --dport 要打开的端口 -j ACCEPT
```

保存后，重启防火墙

```
service iptables restart
```



## firwall

### 1. 查看服务状态

```
systemctl status firewalld
```

出现Active: active (running)切高亮显示则表示是启动状态。

出现 Active: inactive (dead)灰色表示停止，看单词也行。

### **2、查看firewall的状态**

```text
firewall-cmd --state
```

### **3、开启、重启、关闭、firewalld.service服务**

开启

```text
service firewalld start
```

重启

```text
service firewalld restart
```

关闭

```text
service firewalld stop
```

### **4、查看防火墙规则**

```text
firewall-cmd --list-all 
```

### **5、查询、开放、关闭端口**

查询端口是否开放

```text
firewall-cmd --query-port=8080/tcp
```

开放80端口

```text
firewall-cmd --permanent --add-port=80/tcp
```

移除端口

```text
firewall-cmd --permanent --remove-port=8080/tcp
```

重启防火墙(修改配置后要重启防火墙)

```text
firewall-cmd --reload
```

#### 参数解释

- firwall-cmd：是Linux提供的操作firewall的一个工具；

- --permanent：表示设置为持久； 
- --add-port：标识添加的端口；


# 连接远程mysql报10060Error
## 可能原因
### 1. 数据库权限未允许其他ip访问

进入mysql数据库页面

```
mysql -uroot -p密码
```

查看数据库是否允许当前访问ip

```mysql
mysql> use mysql
Database changed

mysql> select host,user from user;
+-----------+---------------+
| host      | user          |
+-----------+---------------+
| %         | root          |
| localhost | mysql.session |
| localhost | mysql.sys     |
+-----------+---------------+
3 rows in set (0.00 sec)
```

如果``root``对应的host不是%，则需要修改

```
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '密码' WITH GRANT OPTION;
```

``%``位置也可以输入需要允许的ip地址

### 2. 防火墙未开发3306端口
参考[[Centos防火墙操作]]

#### 开放3306端口
查看3306端口是否打开
```text
firewall-cmd --query-port=3306/tcp
```

如果没有打开，则开放3306端口

```
firewall-cmd --permanent --add-port=3306/tcp
```

重启防火墙

```text
firewall-cmd --reload
```

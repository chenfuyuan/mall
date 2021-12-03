# mysql连接慢问题
在配置文件``my.cnf``中添加配置
```
[mysqld]
skip-name-resolve
```
skip-name-resolve:跳过域名解析

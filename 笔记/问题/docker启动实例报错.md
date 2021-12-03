# docker 启动实例报错
##  ``Error response from daemon: cannot start a stopped process: unknown.``
### 问题:
缺失 ``libseccomp-devel`` 依赖包
### 解决方案:
```
yum install -y libseccomp-devel
```



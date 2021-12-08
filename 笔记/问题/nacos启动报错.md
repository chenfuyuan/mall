# nacos启动报错
## Centos启动找不到jdk
### 问题:
使用``sh start.sh``启动时找不到jdk
```yml
# 编辑 nacos的启动脚本
vim /usr/soft/nacos/bin/startup.sh
#修改前
[ ! -e "$JAVA_HOME/bin/java" ] && JAVA_HOME=$HOME/jdk/java
[ ! -e "$JAVA_HOME/bin/java" ] && JAVA_HOME=/usr/java
[ ! -e "$JAVA_HOME/bin/java" ] && JAVA_HOME=/opt/taobao/java
[ ! -e "$JAVA_HOME/bin/java" ] && unset JAVA_HOME\



# 将JAVA_HOME修改为本机的JDK安装路径 并将下面的三行用# 注销
[ ! -e "$JAVA_HOME/bin/java" ] && JAVA_HOME=/usr/soft/java8/jdk1.8.0_202
# [ ! -e "$JAVA_HOME/bin/java" ] && JAVA_HOME=/usr/java
# [ ! -e "$JAVA_HOME/bin/java" ] && JAVA_HOME=/opt/taobao/java
# [ ! -e "$JAVA_HOME/bin/java" ] && unset JAVA_HOME\
```
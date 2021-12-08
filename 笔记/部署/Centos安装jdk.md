# Centos安装Jdk

参考博客[https://cloud.tencent.com/developer/article/1412643](https://cloud.tencent.com/developer/article/1412643)

## 1. 安装
### 1.1通过yum安装open jdk
检索包含java的列表
```
[root@localhost jre]# yum list java*
Repository AppStream is listed more than once in the configuration
Repository extras is listed more than once in the configuration
Repository PowerTools is listed more than once in the configuration
Repository centosplus is listed more than once in the configuration
上次元数据过期检查：0:12:59 前，执行于 2021年12月07日 星期二 23时44分35秒。
可安装的软件包
java-1.8.0-openjdk.x86_64                                         1:1.8.0.312.b07-2.el8_5                                  AppStream
java-1.8.0-openjdk-accessibility.x86_64                           1:1.8.0.312.b07-2.el8_5                                  AppStream
java-1.8.0-openjdk-demo.x86_64                                    1:1.8.0.312.b07-2.el8_5                                  AppStream
java-1.8.0-openjdk-devel.x86_64                                   1:1.8.0.312.b07-2.el8_5                                  AppStream
java-1.8.0-openjdk-headless.x86_64                                1:1.8.0.312.b07-2.el8_5                                  AppStream
java-1.8.0-openjdk-headless-slowdebug.x86_64                      1:1.8.0.312.b07-2.el8_5                                  AppStream
java-1.8.0-openjdk-javadoc.noarch                                 1:1.8.0.312.b07-2.el8_5                                  AppStream
java-1.8.0-openjdk-javadoc-zip.noarch                             1:1.8.0.312.b07-2.el8_5                                  AppStream
java-1.8.0-openjdk-slowdebug.x86_64                               1:1.8.0.312.b07-2.el8_5                                  AppStream
java-1.8.0-openjdk-src.x86_64                                     1:1.8.0.312.b07-2.el8_5                                  AppStream
java-11-openjdk.x86_64                                            1:11.0.13.0.8-3.el8_5                                    AppStream
java-11-openjdk-demo.x86_64                                       1:11.0.13.0.8-3.el8_5                                    AppStream
java-11-openjdk-devel.x86_64                                      1:11.0.13.0.8-3.el8_5                                    AppStream
java-11-openjdk-headless.x86_64                                   1:11.0.13.0.8-3.el8_5                                    AppStream
java-11-openjdk-javadoc.x86_64                                    1:11.0.13.0.8-3.el8_5                                    AppStream
java-11-openjdk-javadoc-zip.x86_64                                1:11.0.13.0.8-3.el8_5                                    AppStream
java-11-openjdk-jmods.x86_64                                      1:11.0.13.0.8-3.el8_5                                    AppStream
java-11-openjdk-src.x86_64                                        1:11.0.13.0.8-3.el8_5                                    AppStream
java-11-openjdk-static-libs.x86_64                                1:11.0.13.0.8-3.el8_5                                    AppStream
java-17-openjdk.x86_64                                            1:17.0.1.0.12-2.el8_5                                    AppStream
java-17-openjdk-demo.x86_64                                       1:17.0.1.0.12-2.el8_5                                    AppStream
java-17-openjdk-devel.x86_64                                      1:17.0.1.0.12-2.el8_5                                    AppStream
java-17-openjdk-headless.x86_64                                   1:17.0.1.0.12-2.el8_5                                    AppStream
java-17-openjdk-javadoc.x86_64                                    1:17.0.1.0.12-2.el8_5                                    AppStream
java-17-openjdk-javadoc-zip.x86_64                                1:17.0.1.0.12-2.el8_5                                    AppStream
java-17-openjdk-jmods.x86_64                                      1:17.0.1.0.12-2.el8_5                                    AppStream
java-17-openjdk-src.x86_64                                        1:17.0.1.0.12-2.el8_5                                    AppStream
java-17-openjdk-static-libs.x86_64                                1:17.0.1.0.12-2.el8_5                                    AppStream
java-atk-wrapper.x86_64                                           0.33.2-6.el8                                             AppStream
javapackages-filesystem.noarch                                    5.3.0-1.module_el8.0.0+11+5b8c10bd                       AppStream
javapackages-tools.noarch                                         5.3.0-1.module_el8.0.0+11+5b8c10bd                       AppStream
```
选择需要的JDK版本yum命令安装。

要安装``java-*.*.*-openjdk-devel.x86_64``版本的。如果安装不带``devel``的版本。将没有``javac``等操作，只有基础的``jre``运行环境。

```
yum install -y java-1.8.0-openjdk-devel.x86_64
```

安装后路径为

``/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.312.b07-2.el8_5.x86_64``

### 1.2 从Oracle官网下载jdk

下载地址[https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

上传至所在服务器，进行解压



## 2. 环境变量配置

编辑配置文件``/etc/profile``，添加下列内容

```
#jdk环境变量
#openjdk安装路径
#export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.312.b07-2.el8_5.x86_64

#从官网下载解压后的路径
export JAVA_HOME=/usr/soft/jdk1.8.0_311
export CLASSPATH=.:$JAVA_HOME/jre/lib/rt.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export PATH=$PATH:$JAVA_HOME/bin
```

使环境变量修改生效

```
source /etc/profile
```


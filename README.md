# MomiaAdmin

## 编译、打包

```
# 进入项目的根目录，然后执行:
mvn clean package
```

执行完打包命令后，会在MomiaAdmin/target目录下生成一个momia-admin-web-{version}-release.tar.gz文件

### 运行

#### 环境依赖
Linux系统
JDK 6 或更高版本

```
# 解压
tar xvf momia-admin-web-{version}-release.tar.gz

# 设置数据库环境
# 在momia-admin-web-{version}/src/main/resources/jdbc.properties中配置数据库连接信息
# 在momia-admin-web-{version}/src/main/resources/jdbc.properties中配置图片地址前缀picUrl的值，格式如：http://s.momia.cn或http://localhost:9090

# 进入bin目录运行启动脚本
cd momia-admin-web-{version}/bin
./app.sh start
```

bin目录下有3个脚本文件:

1. env.sh: jvm参数等环境配置
2. app.sh: 系统start/stop脚本
3. server.sh: 系统start/stop脚本，以nobody用户运行，只是对app.sh的调用，这个脚本用于线上服务start/stop

### 在IDE中运行
在cn.momia.admin.web.MomiaAdminWeb类中有一个Main函数，是系统的入口，运行项目之前，查看MomiaAdmin/lib中是否存在json.jar、ueditor-1.1.1.jar的包，如果不存在请手动导入,直接在IDE中运行这个main函数即可

### 在浏览器中运行
打开浏览器在地址栏输入：http://ip:port/user/login进入登录页面

>注意：运行main函数前，要在IDE的运行设置中，将Working Directory指向momia-admin-web目录，不然无法正确启动
# TourismWork - README

关于本次 TourismWork 版本的更新内容，请查阅 [changes.txt](changes.txt) 文件。

---

## 快速入门

1. 在您的数据库中创建 TourismWork 相关表
   - 数据库表结构定义（DDL）请查看 `database/schema` 文件夹
2. 在 `tourismwork.properties` 配置文件中更新您的数据库连接信息。
3. 部署程序包。
4. 登录账号：admin，密码：gingkgo

---

## 发布说明

- **发布人：** Steven Zhang(zhangsitao@gmail.com)
- **问题反馈：** https://www.opentravelsoft.cn

### 版本及使用说明

此次制作 All-Package，这个压缩包已经包括了 Tomcat6.0.20，MySQL Server 5.1，j2sdk1.5，GingkgoTravel 的发布文件（注意，仅能在 Windows 环境运行）。欢迎大家提出宝贵意见。

### 建议运行环境

| 项目 | 配置要求 |
|------|---------|
| 硬件 | CPU主频2G，内存1G以上 |
| 操作系统 | Windows XP pro / 2003 / Vista |

---

## GingkgoTravel-1.0-beta 启动方法

1. 解压本压缩包到你的工作目录
2. 进入解压后的目录，用鼠标双击 `startup.bat` 运行
3. 打开浏览器，在地址栏键入：http://localhost:8080/portal；至此，将进入 Portal 页面
4. 已经建好的 Portal 用户帐号有：
   - 超级管理员：admin / gingkgo
   - 普通用户：user / user
5. 打开浏览器，在地址栏键入：http://localhost:8080/manage；至此，将进入后台管理页面
6. 已经建好的用户帐号有：
   - 管理员：admin / gingkgo

## 终止方法

1. 进入解压后的目录，用鼠标双击 `shutdown.bat` 运行
2. 关闭 dos 窗口

---

## 常见问题

### 1. MySQL 启动出错

**答：** 查看是否本地已经安装了 MySQL 并正在运行，如果是这样，那么先关闭以前的 MySQL，再重新运行启动文件。

### 2. Tomcat 启动出错

**答：** 如果 Tomcat 窗口显示端口 8080 被占用，那么需要查找 Windows 当前占用端口的程序，并终止它，然后再重新运行启动文件。通常占用 8080 端口的程序有 Tomcat，Oracle 的 XML 服务引擎，JBoss，或者其它服务器程序选用了 8080 作为监听端口。

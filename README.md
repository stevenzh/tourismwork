GingkgoTravel（Windows版）发布说明

发布时间:2010-3-3

发布人：Steven Zhang(zhangsitao@gmail.com)

发布网站：http://sourceforge.net/projects/gingkgo/

问题反馈：http://www.opentravelmsoft.com

版本及使用说明：
此次制作All-Package，这个压缩包已经包括了Tomcat6.0.20，
MySQL Server 5.1，j2sdk1.5，GingkgoTravel的发布文件
（注意，仅能在windows环境运行）。
欢迎大家提出宝贵意见。

建议运行环境：
硬件：CPU主频2G，内存1G以上
操作系统：Windows XP pro/2003/Vista

-----------GingkgoTravel-1.0-beta------------------
启动方法：
1.解压本压缩包到你的工作目录
2.进入解压后的目录，用鼠标双击startup.bat运行
3.打开浏览器，在地址栏键入：http://localhost:8080/portal; 至此,将进入Portal 页面
4.已经建好的Portal用户帐号有：
超级管理员：admin/gingkgo
普通用户：user/user

5.打开浏览器，在地址栏键入：http://localhost:8080/manage; 至此,将进入后台管理页面
6.已经建好的用户帐号有：
管理员：admin/gingkgo

终止方法：
1.进入解压后的目录，用鼠标双击shutdown.bat运行
2.关闭dos窗口

-----------GingkgoTravel-1.0-beta ------------------

常见问题：
1.MySQL启动出错
答：查看是否本地已经安装了MySQL并正在运行，如果是这样，那么先关闭
    以前的MySQL，再重新运行启动文件
2.Tomcat启动出错
答：如果Tomcat窗口显示端口8080被占用，那么需要查找Widows当前占用端
    口的程序，并终止它，然后再重新运行启动文件。通常占用8080端口
    的程序有Tomcat，Oracle的XML服务引擎，JBoss，或者其它服务器程
    序选用了8080作为监听端口
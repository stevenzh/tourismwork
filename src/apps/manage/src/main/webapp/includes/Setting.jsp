<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<title>参数设置</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="参数设置首页">
</head>

<body>
<div class="indexPage">
<ul>
    <li><a href="<s:url action='showDistrict' namespace='/setting'/>">景区 </a></li>
    <li><a href="<s:url action='showSights' namespace='/setting'/>">景点 </a></li>
    <li><a href="<s:url action='listAirways' namespace='/setting'/>">航空公司 </a></li>
    <li><a href="<s:url action='showAirport' namespace='/setting'/>">机场</a></li>
    <li><a href="<s:url action='listFlight' namespace='/setting'/>">航班</a></li>
    <li><a href="<s:url action='showCategoryTree' namespace='/setting'/>">产品分类</a></li>
    <li><a href="<s:url action='FITType' namespace='/setting'/>">FIT类型</a></li>
    <li><a href="<s:url action='showDepartment' namespace='/setting'/>">部门管理</a></li>
    <li><a href="<s:url action='listTeam' namespace='/setting'/>" >团队管理</a></li>
    <li><a href="<s:url action='listUser' namespace='/setting'/>">用户管理</a></li>
    <li><a href="<s:url action='listGuide' namespace='/setting'/>">导陪档案</a></li>
    <li><a href="<s:url action='listModule' namespace='/setting'/>" >模块列表 </a></li>
    <li><a href="<s:url action='listRole' namespace='/setting'/>" >安全角色 </a></li>
    <li><a href="<s:url action='listConfig' namespace='/setting'/>">系统设置</a></li>
    <li><a href="<s:url action='showList' namespace='/setting'/>">数据列表</a></li>
</ul>
</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<title>客户/供应商</title>
<meta name="menu" content="AccountMenu"/>
<meta name="heading" content="客户/供应商首页">
</head>

<body>
<div class="indexPage">
<ul>
    <li><a href="<s:url action='showAgentListInput' namespace='/crm'/>">客户</a></li>
    <li><a href="<s:url action='showGroupSupplier' namespace='/crm'/>">计调对应供应商</a></li>
    <li><a href="<s:url action='contactActionInput' namespace='/crm'/>">联系人</a></li>
</ul>
</div>
</body>
</html>

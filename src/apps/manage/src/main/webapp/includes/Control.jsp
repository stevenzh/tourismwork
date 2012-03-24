<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<title>预警中心</title>
<meta name="menu" content="ControlMenu"/>
<meta name="heading" content="预警中心首页">
</head>

<body>
<div class="indexPage">
<ul>
  <li><a href="<s:url action='showOperatorAlert' namespace='/sales'/>">计调预警</a></li>
  <li><a href="<s:url action='showFinanceAlert' namespace='/finance'/>">财务预警</a></li>
</ul>
</div>
</body>
</html>

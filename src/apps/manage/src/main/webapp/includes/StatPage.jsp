<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<title>统计报表</title>
<meta name="menu" content="StatMenu"/>
<meta name="heading" content="统计报表首页">
</head>

<body>
<div class="indexPage">
<ul>
  <li><a href='<s:url namespace="/sales" action="showIn" />'>按地区统计</a></li>
  <li><a href='<s:url namespace="/sales" action="getIn" />'>按目的地统计</a></li>
</ul>
</div>
</body>
</html>

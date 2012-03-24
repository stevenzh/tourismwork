<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<title>配送管理</title>
<meta name="menu" content="ExpressMenu"/>
<meta name="heading" content="配送管理首页">
</head>

<body>
    <s:url id="expressTask" namespace="/express" action="expressTasks"/>
    <sj:div href="%{expressTask}">Initial Content</sj:div>
</body>
</html>

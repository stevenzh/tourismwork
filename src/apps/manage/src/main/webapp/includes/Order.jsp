<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<title>订单管理</title>
<meta name="menu" content="OrderMenu"/>
<meta name="heading" content="订单管理首页">
</head>

<body>
<sj:tabbedpanel id="opTab"> 
  <sj:tab href="/sales/showOperatorAlert.jspa" label="未读订单"></sj:tab>
  <sj:tab href="#" label="未付款订单" />
</sj:tabbedpanel>

</body>
</html>

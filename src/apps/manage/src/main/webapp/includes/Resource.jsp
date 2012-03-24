<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<title>产品资源</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="产品资源首页">
</head>

<body>
	<sj:tabbedpanel id="productTab"> 
	   <sj:tab href="/operator/operatorRunPlan.jspa" label="最近出团计划"></sj:tab>
	   <sj:tab label="最近修改的线路"></sj:tab>
	</sj:tabbedpanel>
</body>
</html>

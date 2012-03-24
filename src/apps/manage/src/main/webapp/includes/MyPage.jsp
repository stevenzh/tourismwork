<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<head>
<title>我的桌面</title>
<meta name="menu" content="MainMenu"/>
<meta name="heading" content="我的桌面">
</head>
<body>
<sj:tabbedpanel id="taskTab"> 
   <sj:tab href="/myTasks.jspa" label="我的工作任务"></sj:tab>
   <sj:tab label="我的组工作任务"></sj:tab>
</sj:tabbedpanel> 

</body>

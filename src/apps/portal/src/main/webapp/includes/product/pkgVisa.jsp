<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>线路搜索--Search</title>
<meta name="menu" content="VisaMenu" />
</head>

  <div class="w250 main_left left">
   <div class="left_box">
    <div class="left_box_top">
      <h2>旅游度假</h2>
    </div>
      <s:action name="lineSearch" namespace="/view" executeResult="true" />
   </div>
  </div>
  
  <div class="w660 main_middle left">
	  <s:action name="visaDetail" namespace="/view" executeResult="true"></s:action>
  </div>
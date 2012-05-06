<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>度假产品</title>
<meta name="menu" content="VacationMenu" />
<meta name="heading" content='度假产品' />
</head>

  <div class="w250 main_left left">
   <div class="left_box">
    <div class="left_box_top">
      <h2>旅游度假</h2>
    </div>
      <s:action name="lineSearch" namespace="/product" executeResult="true" />
   </div>
  </div>
  
  <div class="w660 main_middle left">
    <s:action name="lineDetail" namespace="/product" executeResult="true" />
  </div>

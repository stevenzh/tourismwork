<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="/common/meta.jsp" %>
    <title><decorator:title/> | <fmt:message key="webapp.name"/></title>
    <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/${appConfig["csstheme"]}/theme.css'/>" />
    <link rel="stylesheet" type="text/css" media="print" href="<c:url value='/styles/${appConfig["csstheme"]}/print.css'/>" />
    <script type="text/javascript" src="<c:url value='/scripts/global.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/scripts/jquery-1.7.1.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/scripts/My97DatePicker/WdatePicker.js'/>"></script>
    <decorator:head/>
  </head>

<body<decorator:getProperty property="body.id" writeEntireProperty="true"/><decorator:getProperty property="body.class" writeEntireProperty="true"/>>
  <div class="wrapper w920">
    <div class="header">
      <jsp:include page="/common/header.jsp"/>
    </div> <!--header-->

    <div class="nav">
	  <div class="nav_left left"></div>
	  <div class="nav_middle left">
	   <ul>
	     <li><a href="/main.html">首页</a><span>|</span></li>
	     <li><a href="/product/vacations.html">出境游</a><span>|</span></li>
	     <li><a href="/product/visas.html">签证</a><span>|</span></li>
	     <li><a href="/product/hotels.html">酒店</a><span>|</span></li>
	     <li>国内机票<span>|</span></li>
	     <li>国际机票<span>|</span></li>
	     <li>旅游景点<span>|</span></li>
	     <li>开心社区<span>|</span></li>
	     <li>会员中心<span>|</span></li>
	     <li>关于我们</li>
	   </ul>
	  </div>
	  <div class="nav_right left"></div>
    </div> <!-- nav -->

    <%@ include file="/common/messages.jsp" %>
    <div class="main">

		  <div class="w250 main_left left">
		   <div class="left_box">
		    <div class="left_box_top">
		      <h2>我的菜单</h2>
		    </div>
		    <div>
		    <ul>
		      <li><a href="/editProfile.html">我的信息</a></li>
		      <li><a href="/accounts/showMyBooking.html">我的订单</a></li>
		    </ul>
		    </div>
		   </div>

       <div class="left_box">
        <div class="left_box_top">
          <h2>管理员菜单</h2>
        </div>
        <div>
        <ul>
          <li><a href="/search/CbSearch.html">查询</a></li>
          <li><a href="/search/CbAdvancedSearchInput.html">查询</a></li>
        </ul>
        </div>
       </div>
		  </div> <!--main_left-->
  
	    <div class="w660 main_middle left">
	      <decorator:body/>
	    </div>
	 </div>

    <div class="footer">
      <jsp:include page="/common/footer.jsp"/>
    </div>
  </div>
</body>
</html>

<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<title>Data Access Error</title>

<head>
  <meta name="heading" content="Data Access Failure"/>
  <meta name="menu" content="AdminMenu"/>
</head>

<p>
  <c:out value="${requestScope.exception.message}"/>
</p>

<!--
<% 
((Exception) request.getAttribute("exception")).printStackTrace(new java.io.PrintWriter(out)); 
%>
-->

<a href="main.html" onclick="history.back();return false">&#171; Back</a>

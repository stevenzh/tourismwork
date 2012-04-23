<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.web.access.AccessDeniedHandlerImpl" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title>错误页面</title>
<META HTTP-EQUIV="Content-type" CONTENT="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/stylesheet/global.css" type="text/css" media="all">
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/main.js"></script>
</head>
<body>

<table style="width: 100%">
  <tr valign="top">
    <td>
    <table style="width: 100%">
      <tr>
        <th align="center">页面错误</th>
      </tr>
      <tr>
        <td align="right"><a href="#" onclick="javascript:window.history.back();">返回前一个页面</a></td>
      </tr>
      <tr>
        <td>异常: <s:property value="exception" /></td>
      </tr>
      <tr>
        <td>错误原因:<pre><s:property value="exceptionStack" /></pre> </td>
      </tr>
      <tr>
        <td height="18" align="center">
        </td>
      </tr>
    </table>
    </td>
  </tr>
</table>
<p>
权限访问异常:<%= request.getAttribute(AccessDeniedHandlerImpl.SPRING_SECURITY_ACCESS_DENIED_EXCEPTION_KEY)%>

<% Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   if (auth != null) { %>
     登陆用户权限属性: <%= auth.toString() %><BR><BR>
<% } %>

<div align="center">
<button type="button" NAME="btnGo" style="width: 160px;" OnClick="javascript:window.history.back();">返回</button>
</div>
</body>
</html>


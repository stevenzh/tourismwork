<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="sitemesh-page" prefix="page" %>
<%@ taglib uri="sitemesh-decorator" prefix="decorator" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<table style="width: 100%; height:50px;border: 0px;">
  <tr>
    <td width="400"><img src="<s:url value="/images/tourwork-4.gif" />"></td>
    <td>&nbsp;</td>
    <td width="230">用户名：<s:property value="#session['EBIZ_USER'].userName" />
     部门：<s:property value="#session['EBIZ_USER'].departmentName" />
    <!-- <br> 职位级别：<s:property value="#session['EBIZ_USER'].jobKey" /> -->
    </td>
    <td width="90">
      <a href="<s:url action='HelpAction' namespace='/'/>">帮助</a>
      <a href="<s:url value="/j_spring_security_logout" />">安全退出</a>
    </td>
  </tr>
</table>


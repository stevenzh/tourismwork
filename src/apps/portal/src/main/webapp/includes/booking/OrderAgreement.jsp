<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title>旅游预订协议</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<table align="center" border="0" cellpadding="0" cellspacing="1" width="660">
  <tr>
    <td>预订前,请您仔细阅读以下服务条款:</td>
  </tr>
  <tr>
    <td><iframe src="<s:url value='/html/CUS_MEMBER_ORDER_AGREE_PLATFORM.htm' />" frameborder="0" height="340" width="100%"></iframe></td>
  </tr>
  <tr>
    <td align="right">
     <input type="button" value="我同意以上条款" onclick="window.top.location.href='<s:url action='showOrderBooking' namespace='/accounts' includeParams='get'></s:url>';">
     <input type="button" value="不同意以上条款" onclick="history.back();">
    </td>
  </tr>
</table>

</body>
</html>


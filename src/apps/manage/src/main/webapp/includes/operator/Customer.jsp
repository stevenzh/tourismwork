<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客人资料</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="客人资料">
</head>

<body>
<s:form action="" namespace="/operator" method="post" theme="simple">
<table border="0" cellpadding="0" width="100%" bgcolor="#EEECE6">
	<tr align="center" valign="middle" onmouseover="this.bgColor='#F9F8F4';" onmouseout="this.bgColor='#EEECE6';">
		<td align="left">姓:</td>
		<td align="left"><s:textfield name="tcustomer.familyName" disabled="true"/></td>
		<td align="left">名:</td>
		<td align="left"><s:textfield name="tcustomer.givenName" disabled="true"/></td>
		<td align="left">姓名:</td>
		<td align="left"><s:textfield name="tcustomer.userName" disabled="true"/></td>
		<td align="left">拼音:</td>
		<td align="left"><s:textfield name="tcustomer.pinYin" disabled="true"/></td>
	</tr>
	<tr align="center" valign="middle" onmouseover="this.bgColor='#F9F8F4';" onmouseout="this.bgColor='#EEECE6';">
		<td align="left">证件种类:</td>
		<td align="left"><s:textfield name="tcustomer.cardType" disabled="true"/></td>
		<td align="left">证件号码:</td>
		<td align="left"><s:textfield name="tcustomer.card" disabled="true"/></td>
		<td align="left">性别:</td>
		<td align="left"><s:textfield name="forSex" disabled="true"/></td>
		<td align="left">出生日期:</td>
		<td align="left"><s:textfield name="tcustomer.birthday" disabled="true"/></td>
	</tr>
</table>
<center>
  <input type="button" value="返回" onClick="javascript:history.go(-1);">
</center>
</s:form>
</body>
</html>
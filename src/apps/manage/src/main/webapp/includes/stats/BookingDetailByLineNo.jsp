<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>目的地订单情况一览</title>
</head>
<body>

<table align="center" border="0" style="width: 100%">
  <tr>
    <td class="header">目的地订单情况一览</td>
  </tr>
</table>

<s:form action="search" namespace="/sales" method="post" theme="simple">
<table align="center" border="0" style="width: 100%">
  <tr>
 		<td>
 			<FONT color="red"><s:date name="startDate" format="yyyy-MM-dd" /> 至 <s:date name="endDate" format="yyyy-MM-dd" /> 统计情况</FONT>
		</td>
	</tr>
  <tr bgcolor="#b9c0ff">
    <td class="lstidx">No.</td>
    <td class="lstidx">订单号</td>
    <td class="lstidx">出团时间</td>
    <td class="lstidx">客户</td>
    <td class="lstidx">销售员</td>
    <td class="lstidx">人数</td>
    <td class="lstidx">团款</td>
   </tr>
  <s:iterator value="bookList" status="rowcounter">
  <tr>
    <td align="center"><s:property value="#rowcounter.count"/></td>
    <td><s:property value="recordNo"/></td>
    <td><s:date name="outDate" format="yyyy-MM-dd"/></td>
    <td><s:property value="customer.name"/></td>
    <td><s:property value="salesman.userName"/></td>
    <td align="center"><s:property value="pax"/></td>
    <td align="right"><s:property value="dbamt"/></td>
  </tr>
  </s:iterator>
</table>
</s:form>


</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>签证信息</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="签证信息">
</head>
<body>

<s:form action="listVisa" namespace="/product" method="post" theme="simple">
<s:hidden name="country"></s:hidden>

<table style="width: 100%" border="0">
  <tr>
    <td class="idx">国家/地区：</td>
    <td class="data" colspan="5"><s:property value="visaHelp.cnName" /></td>
  </tr>
  <tr>
    <td class="idx">项目（中文）：</td>
    <td class="data" colspan="5"><s:property value="visaHelp.subject" /></td>
  </tr>
  <tr>
    <td class="idx">办签要求：</td>
    <td class="data" colspan="5"><s:property escape="false" value="visaHelp.note" /></td>
  </tr>
  <tr>
    <td class="idx">成本价：</td>
    <td class="data">RMB <s:property value="visaHelp.costPrice" /></td>
    <td class="idx">同行价：</td>
    <td class="data">RMB <s:property value="visaHelp.quotedPrice" /></td>
    <td class="idx">直客价：</td>
    <td class="data">RMB <s:property value="visaHelp.marketPrice" /></td>
  </tr>
  <tr>
    <td class="idx">计量单位-中文：</td>
    <td class="data" colspan="5"><s:property value="visaHelp.unit" /></td>
  </tr>
  <tr>
    <td class="idx">有效日期：</td>
    <td class="data" colspan="5"><s:date name="visaHelp.startDate" format="yyyy/MM/dd" />-<s:date name="visaHelp.endDate" format="yyyy/MM/dd" /></td>
  </tr>
</table>
<div align="center">
 <s:submit action="listVisa" value="返回"/>
</div>
</s:form>
</body>
</html>

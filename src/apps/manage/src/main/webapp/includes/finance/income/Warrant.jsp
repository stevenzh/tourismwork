<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>担保</title>
<meta name="menu" content="FinanceMenu"/>
<meta name="heading" content="担保">
</head>

<body>

<s:form action="warrantsubmit" namespace="/finance" method="POST" theme="simple">
<s:hidden name="kenDepartmentId"></s:hidden>
<table border="1" style="width: 100%">
  <tr>
  	<td class="idx">团号</td>
  	<td><s:property value="tourNo"/><s:hidden name="tourNo"/></td>
  </tr>
  <tr>
    <td class="idx">选择需要担保的客户</td>
    <td colspan="3">
    	<s:select name="kenCustomerId"
    			  list="agentList"
    			  listKey="agentId"
    			  listValue="name"
    			  headerKey="-1"
    			  headerValue="请选择">
    	</s:select>
    </td>
  </tr>
  <tr>
    <td class="idx">担保日期</td>
    <td colspan="3">
    <s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" name="warrantDate" maxDate="%{sysdate}">
    </s:textfield>
    </td>
  </tr>
  <tr>
    <td class="idx">最后付款日期</td>
    <td colspan="3">
    <s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" name="lastPayDate">
    </s:textfield>
    </td>
  </tr>
    <tr>
    <td class="idx">担保金额</td>
    <td colspan="3">
    <s:textfield name="warrant.warratMoney"></s:textfield>
    </td>
  </tr>
  <tr>
    <td class="idx">担保人</td>
    <td colspan="3">
    <s:textfield name="warrant.warrantBy"></s:textfield>
    </td>
  </tr>
</table>
<div align="center">
 <s:submit value="提交担保"/>
 <input type="button" value="返回" onClick="javascript:history.go(-1);">
</div>

</s:form>

</body>
</html>

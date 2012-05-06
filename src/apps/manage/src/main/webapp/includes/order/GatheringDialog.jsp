<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单收款</title>
<meta name="menu" content="OrderMenu"/>
<meta name="heading" content="订单收款">
<s:head />
</head>

<body>
<s:form action="submitBookGathering" namespace="/sales" method="post" theme="simple">
  <s:hidden name="payment.bookingNo" value="%{bookingNo}" />

  <table border="1"  align="center" style="width: 80%">
    <tr bgcolor="#f2f2f2">
      <td>付款方式</td>
      <td>
      <s:select name="payment.payMode"
                list="types"
                listKey="label"
                listValue="value"
                emptyOption="true">
      </s:select><font color="#ff0000">*</font>
      </td>
    </tr>
    <tr>
      <td>付款类别</td>
      <td>
      <s:select name="payment.useType"
                list="paymentType"
                listKey="value"
                listValue="label"
                emptyOption="true">
      </s:select><font color="#ff0000">*</font>
      </td>
    </tr>
    <tr>
      <td>收款人</td>
      <td>
        <s:textfield name="payment.receiver"></s:textfield>
      </td>
    </tr>
    <tr>
      <td>金额</td>
      <td>
        <s:textfield id="pay2"
                     name="payment.amount"
                     value="%{book.dbamt - book.payCosts}">
        </s:textfield>
      </td>
    </tr>
    <tr>
      <td>摘要</td>
      <td><input name="payment.note" size="40" class="editbox" type="text"><font color="#ff0000">*</font></td>
    </tr>
    <tr>
      <td colspan="2"><s:submit value="提交"></s:submit></td>
    </tr>
  </table>
</s:form>

</body>
</html>

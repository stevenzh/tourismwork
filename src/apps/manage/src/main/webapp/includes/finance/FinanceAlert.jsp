<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询内容</title>
<meta name="heading" content="财务预警"/>
<meta name="menu" content="ControlMenu"/>
</head>

<body>
<s:form action="showOperatorAlert" namespace="/sales" method="post" theme="simple">
<s:if test="outcoemInband.size() > 0">
<table border="0" cellpadding="2" cellspacing="2" width="80%">
  <tr>
    <td colspan="5"><STRONG>国内应付 (未读付款申请, 团出发前n天未付款申请)</STRONG></td>
  </tr>
  <tr>
    <td class="lstidx">团号</td>
    <td class="lstidx">线路名</td>
    <td class="lstidx">出发日期</td>
    <td class="lstidx">客户名称</td>
    <td class="lstidx">未付款金额</td>
  </tr>
  <s:iterator value="outcoemInband" status="rowcounter">
  <tr>
    <td class="data"><s:property value="tourNo"/></td>
    <td class="data"><s:property value="line.lineName"/></td>
    <td class="cdata"><s:date name="outDate" format="yyyy-MM-dd"/></td>
    <td class="data"><s:property value="customerName"/></td>
    <td class="cdata"><s:property value="dbamt"/></td>
  </tr>
  </s:iterator>
</table>
<br>
</s:if>

<s:if test="billheadInBand.size() > 0">
<table border="0" cellpadding="2" cellspacing="2" width="80%">
  <tr>
    <td colspan="6"><STRONG>国内未读付款申请书</STRONG></td>
  </tr>
  <tr>
    <td class="lstidx">付款申请书编号</td>
    <td class="lstidx">团号</td>
    <td class="lstidx">线路名</td>
    <td class="lstidx">出发日期</td>
    <td class="lstidx">客户名称</td>
    <td class="lstidx">未付款金额</td>
  </tr>
  <s:iterator value="billheadInBand" status="rowcounter">
  <tr>
    <td class="cdata"><A href="#" onclick="javascript:showBillhead('<s:property value="outcomeId"/>')"><s:property value="outcomeId"/></A></td>
    <td class="data"><s:property value="tourNo"/></td>
    <td class="data"><s:property value="line.lineName"/></td>
    <td class="cdata"><s:date name="outDate" format="yyyy-MM-dd"/></td>
    <td class="data"><s:property value="customerName"/></td>
    <td class="cdata"><s:property value="dbamt"/></td>
  </tr>
  </s:iterator>
</table>
<br>
</s:if>

<s:if test="incomeInband.size() > 0">
<table border="0" cellpadding="2" cellspacing="2" width="80%"> 
  <tr>
    <td colspan="6"><STRONG>国内应收 团出发前n天 未收款客户提醒</STRONG></td>
  </tr>
  <tr>
    <td class="lstidx">订单号</td>
    <td class="lstidx">团号</td>
    <td class="lstidx">线路名</td>
    <td class="lstidx">出发日期</td>
    <td class="lstidx">客户名称</td>
    <td class="lstidx">未付款金额</td>
  </tr>
  <s:iterator value="incomeInband" status="rowcounter">
  <tr>
    <td class="cdata"><A href="#" onclick="javascript:detail('<s:property value='bookingNo'/>')"><s:property value="bookingNo"/></A></td>
    <td class="data"><s:property value="tourNo"/></td>
    <td class="data"><s:property value="line.lineName"/></td>
    <td class="cdata"><s:date name="outDate" format="yyyy-MM-dd"/></td>
    <td class="data"><s:property value="customerName"/></td>
    <td class="cdata"><s:property value="dbamt"/></td>
  </tr>
  </s:iterator>
</table>
<br>
</s:if>

<table border="0" cellpadding="0" width="80%">
  <tr>
    <td colspan="4" class="header">出境应付</td>
  </tr>
</table>
<s:if test="outcomeAirOutBand.size() > 0">
<table border="0" cellpadding="2" cellspacing="2" width="80%">
  <tr>
    <td colspan="5"><STRONG>应付出境机票 (未读付款申请, 团出发前n天未付款申请)</STRONG></td>
  </tr>
  <tr>
    <td class="lstidx">团号</td>
    <td class="lstidx">线路名</td>
    <td class="lstidx">出发日期</td>
    <td class="lstidx">客户名称</td>
    <td class="lstidx">未付款金额</td>
  </tr>
  <s:iterator value="outcomeAirOutBand" status="rowcounter">
  <tr>
    <td class="data"><s:property value="tourNo"/></td>
    <td class="data"><s:property value="line.lineName"/></td>
    <td class="cdata"><s:date name="outDate" format="yyyy-MM-dd"/></td>
    <td class="data"><s:property value="customerName"/></td>
    <td class="cdata"><s:property value="dbamt"/></td>
  </tr>
  </s:iterator>
</table>
</s:if>

<s:if test="billheadAirOutBand.size() > 0">
<br>
<table border="0" cellpadding="2" cellspacing="2" width="80%">
  <tr>
    <td colspan="6"><STRONG>未阅读付款申请书</STRONG></td>
  </tr>
  <tr>
    <td class="lstidx">付款申请书编号</td>
    <td class="lstidx">团号</td>
    <td class="lstidx">线路名</td>
    <td class="lstidx">出发日期</td>
    <td class="lstidx">客户名称</td>
    <td class="lstidx">未付款金额</td>
  </tr>
  <s:iterator value="billheadAirOutBand" status="rowcounter">
  <tr>
    <td class="cdata"><A href="#" onclick="javascript:showBillhead('<s:property value="outcomeId"/>')"><s:property value="outcomeId"/></A></td>
    <td class="data"><s:property value="tourNo"/></td>
    <td class="data"><s:property value="line.lineName"/></td>
    <td class="cdata"><s:date name="outDate" format="yyyy-MM-dd"/></td>
    <td class="data"><s:property value="customerName"/></td>
    <td class="cdata"><s:property value="dbamt"/></td>
  </tr>
  </s:iterator>
</table>
</s:if>

<s:if test="outcomeTravOutBand.size() > 0">
<br>
<table border="0" cellpadding="2" cellspacing="2" width="80%">
  <tr>
    <td colspan="5"><STRONG>应付出境地接 (未读付款申请, 团出发前n天未付款申请)</STRONG></td>
  </tr>
  <tr>
    <td class="lstidx">团号</td>
    <td class="lstidx">线路名</td>
    <td class="lstidx">出发日期</td>
    <td class="lstidx">客户名称</td>
    <td class="lstidx">未付款金额</td>
  </tr>
  <s:iterator value="outcomeTravOutBand" status="rowcounter">
  <tr>
    <td class="data"><s:property value="tourNo"/></td>
    <td class="data"><s:property value="line.lineName"/></td>
    <td class="cdata"><s:date name="outDate" format="yyyy-MM-dd"/></td>
    <td class="data"><s:property value="customerName"/></td>
    <td class="cdata"><s:property value="dbamt"/></td>
  </tr>
  </s:iterator>
</table>
<br>
</s:if>


<s:if test="billheadTravOutBand.size() > 0">
<table border="0" cellpadding="2" cellspacing="2" width="80%">
  <tr>
    <td colspan="6"><STRONG>未阅读付款申请书</STRONG></td>
  </tr>
  <tr>
    <td class="lstidx">付款申请书编号</td>
    <td class="lstidx">团号</td>
    <td class="lstidx">线路名</td>
    <td class="lstidx">出发日期</td>
    <td class="lstidx">客户名称</td>
    <td class="lstidx">未付款金额</td>
  </tr>
  <s:iterator value="billheadTravOutBand" status="rowcounter">
  <tr>
    <td class="cdata"><A href="#" onclick="javascript:showBillhead('<s:property value="outcomeId"/>')"><s:property value="outcomeId"/></A></td>
    <td class="data"><s:property value="tourNo"/></td>
    <td class="data"><s:property value="line.lineName"/></td>
    <td class="cdata"><s:date name="outDate" format="yyyy-MM-dd"/></td>
    <td class="data"><s:property value="customerName"/></td>
    <td class="cdata"><s:property value="dbamt"/></td>
  </tr>
  </s:iterator>
</table>
<br>
</s:if>

<s:if test="incomeCashOutBand.size() > 0">
<table border="0" cellpadding="2" cellspacing="2" width="80%">
  <tr>
    <td colspan="6"><STRONG>出境应收现结 (团出发前n天,未收款客户提醒)</STRONG></td>
  </tr>
  <tr>
    <td class="lstidx">订单号</td>
    <td class="lstidx">团号</td>
    <td class="lstidx">线路名</td>
    <td class="lstidx">出发日期</td>
    <td class="lstidx">客户名称</td>
    <td class="lstidx">未付款金额</td>
  </tr>
  <s:iterator value="incomeCashOutBand" status="rowcounter">
  <tr>
    <td class="cdata"><A href="#" onclick="javascript:detail('<s:property value='bookingNo'/>')"><s:property value="bookingNo"/></A></td>
    <td class="data"><s:property value="tourNo"/></td>
    <td class="data"><s:property value="line.lineName"/></td>
    <td class="cdata"><s:date name="outDate" format="yyyy-MM-dd"/></td>
    <td class="data"><s:property value="customerName"/></td>
    <td class="cdata"><s:property value="dbamt"/></td>
  </tr>
  </s:iterator>
</table>
</s:if>

</s:form>

<s:form action="detailPayRequisition" namespace="/finance" method="post" theme="simple">
<s:hidden name="outcomeId"></s:hidden>
</s:form>
</body>
</html>

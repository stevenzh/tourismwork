<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审核付款申请书</title>
<meta name="menu" content="FinanceMenu"/>
<meta name="heading" content="审核付款申请书">
</head>

<body>
<script language="JavaScript">
<!--//

function balanceDetail(tourNo)
{
   var fm=document.SingleBalanceDetail;
   fm.tourNo.value=tourNo;
   fm.submit();
}

function readBillhead()
{
  var fm = document.detailPayRequisition;
  fm.action="<s:url action='readPayRequisition' namespace='/finance'/>";
  fm.submit();
}

function auditing(param)
{
   var fm=document.detailPayRequisition;
   fm.action="<s:url action='auditPayRequisition' namespace='/finance'/>";
   fm.outcomeId.value=param;
   fm.submit();
}

function goBack()
{
   var fm=document.detailPayRequisition;
   fm.action="<s:url action='listPayRequisition' namespace='/finance'/>";
   fm.submit();
}
//-->
</script>

<s:form action="detailPayRequisition" namespace="/finance" method="post" theme="simple">
  <s:hidden name="kenDepartment"></s:hidden>
  <s:hidden name="kenMaker"></s:hidden>
  <s:hidden name="kenStartDate"></s:hidden>
  <s:hidden name="kenEndDate"></s:hidden>
  <s:hidden name="kenStartOutDate"></s:hidden>
  <s:hidden name="kenEndOutDate"></s:hidden>
  <s:hidden name="kenTourNo"></s:hidden>
  <s:hidden name="kenAudit"></s:hidden>
  <s:hidden name="kenPay"></s:hidden>
  <s:hidden name="outcomeId"></s:hidden>

 <h3>应付客户：<s:property value="billhead.supplierName"/></h3>
 <table border="1" cellpadding="0" cellspacing="0" width="100%">
   <tr>
     <td class="data" colspan="9"><s:property value="billhead.note"/>&nbsp;</td>
   </tr>
   <tr>
          <td class="lstidx">No.</td>
          <td class="lstidx">团号</td>
          <td class="lstidx">内容</td>
          <td class="lstidx">应付款</td>
          <td class="lstidx">已付款</td>
          <td class="lstidx">未付款</td>
		      <td class="lstidx">币种</td>
		      <td class="lstidx">计调提交汇率</td>
		      <td class="lstidx">申请金额(对应币种)</td>
		      <td class="lstidx">财务结算汇率</td>
		      <td class="lstidx">申请金额折合人民币</td>
        </tr>
   
 <s:if test="billhead.payNoticeList.isEmpty() == false">     
  <s:iterator value="billhead.payNoticeList" status="rowcounter">
  <tr>
    <td class="cdata"><s:property value="#rowcounter.count"/></td>
    <td class="data"><a href="#" title="查看单团核算表" onclick="javascript:balanceDetail('<s:property value="tourNo"/>');"><s:property value="tourNo"/></a></td>
    <td class="data"><s:property value="description"/>&nbsp;</td>
    <td class="rdata"><s:property value="payment"/></td>
    <td class="rdata"><s:property value="endPayment"/></td>
    <td class="rdata"><s:property value="nopayPayment"/></td>
    <td class="rdata"><s:property value="currency"/></td>
    <td class="rdata"><s:property value="opRoe"/></td>
    <td class="rdata"><s:property value="fcNowpayPayment"/></td>
    <td class="rdata"><s:property value="roe"/></td>
    <td class="rdata"><s:property value="nowpayPayment"/></td>
  </tr>
  </s:iterator>
  
  </s:if>
  </table>
  
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tr>
    <td class="data">付款方式：<s:property value="billhead.payModeName"/></td>
    <td class="data">付款日期：<s:date name="billhead.payDate" format="yyyy-MM-dd"/></td>
    <td class="rdata"">
       此次付款合计：<s:property value="billhead.amount"/>
    </td>
  </tr>
  <tr>
    <td colspan="3" align="center">
       <s:if test="billhead.frReadFlag = 'N'">
       <input type="button" onclick="javascript:readBillhead();" value="标记为已读">
       </s:if>
       <input type="button" value="审核" onClick="javascript:auditing('<s:property value="billhead.outcomeId"/>');">  
       <input type="button" value="返回" onClick="javascript:history.go(-1);">
    </td>
  </tr>
  </table>
</s:form>

<s:form action="SingleBalanceDetail" namespace="/finance" method="post" theme="simple">
  <s:hidden name="tourNo"></s:hidden>
</s:form>

</body>
</html>
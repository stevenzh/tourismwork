<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审核付款申请书</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="审核付款申请书">
</head>

<body>
<script type="text/javascript">

function goBack()
{
   var fm=document.auditPayRequisition;
   fm.action="<s:url action='listPayRequisition' namespace='/operator'/>";
   fm.submit();
}

function del()
{
  var fm = document.auditPayRequisition;

  if (confirm("是否真的删除?") == false)
  {
    return;
  }
  fm.action = "<s:url action='deletePayRequisition' namespace='/operator'/>"
  fm.submit();
}

</script>
<s:form action="auditPayRequisition" namespace="/operator" method="POST" theme="simple">
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

  <table border="0" width="100%">
    <tr>
      <td class="lstidx" width="15%">应付客户:</td>
      <td colspan="6">
        <s:textfield name="outcome.supplierId" disabled="true"></s:textfield>
     </td>
    </tr>
  </table>

  <table border="0" width="100%">
    <tr>
      <td align="center" class="lstidx">No.</td>
      <td align="center" class="lstidx">团号</td>
      <td align="center" class="lstidx">内容</td>
      <td align="center" class="lstidx">应付款</td>
      <td align="center" class="lstidx">已付款</td>
      <td align="center" class="lstidx">未付款</td>
      <td align="center" class="lstidx">现付款</td>
    </tr>

    <s:iterator value="outcome.payNoticeList" status="rowcounter">
    <tr>
      <td class="cdata"><s:property value="#rowcounter.count" /></td>
      <td class="data"><s:property value="tourNo" /></td>
      <td class="data"><s:property value="description" /></td>
      <td class="rdata"><s:property value="payment" /></td>
      <td class="rdata"><s:property value="endPayment" /></td>
      <td class="rdata"><s:property value="nopayPayment" /></td>
      <td class="rdata"><s:property value="nowpayPayment"/></td>
    </tr>
    </s:iterator>
  </table>
    
  <table border="0" width="100%">
    <tr>
      <td class="lstidx" width="20%">此次付款合计：</td>
      <td>
        <input type="text" name="textfield2">
      </td>
    </tr>
    <tr>
      <td class="lstidx">付款说明</td>
      <td colspan="6">
        <s:property value="outcome.note"/></td>
    </tr>
    <tr>
    	<td class="lstidx">票务配送</td>
    	<td>
　    	  <s:radio name="outcome.carryTicket"
           list="#{ 0:'否', 1: '是' }" 
           listKey="key" 
           listValue="value"
           disabled="true">
    	  </s:radio>
     	</td>
    </tr>
    <tr>
      <td class="lstidx">配送时限</td>
      <td colspan="6">
        <s:date name="outcome.carryLastDate" format="yyyy-MM-dd"/></td>
    </tr>
    <tr>
      <td class="lstidx">配送说明</td>
      <td colspan="6">
        <s:property value="outcome.carryNote"/></td>
    </tr>
	  <tr>
      <td colspan="4">
      <s:submit action="editPayRequisition" value="修改"></s:submit>
      <input type="button" onclick="javascript:del();"  value="删除" />
      <s:submit value="审核"></s:submit>
      <input type="button" value="返回" onclick="javascript:goBack();">
	    </td>
    </tr>
  </table>
</s:form>

</body>
</html>
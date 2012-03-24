<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改付款申请书</title>
<meta name="menu" content="FinanceMenu"/>
<meta name="heading" content="修改付款申请书">
</head>

<body>
<script type="text/javascript">
<!--//

function goBack()
{
   var fm=document.savePayRequisition;
   fm.action="<s:url action='listPayRequisition' namespace='/finance'/>";
   fm.submit();
}

var count = 0;
var totalpay = 0;
function applychange(target)
{
  if(target > count)
    count = target;

  document.getElementById("amount").value = totalchange();
}

function totalchange()
{
  totalpay = 0;
  for(i=1;i<=count;i++)
  {
    var roe = document.getElementById("roe" + i);
    var pb = document.getElementById("fcnowpay" + i);
    var nowp = document.getElementById("nowpay" + i);

    if(pb != null)
    {
      if(pb.value != null)
      {
        var temp = new Number(pb.value);
        var valRoe = new Number(roe.value);
        nowp.value = temp * valRoe;
        totalpay = totalpay + temp * valRoe;
      }
    }
  }
  return totalpay;
}

//-->
</script>

<s:form action="savePayRequisition" namespace="/finance" method="POST" theme="simple">
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
    
  <table border="0" cellpadding="" width="100%">
    <tr>
      <td class="idx" width="20%">类型</td>
      <td class="data">&nbsp;<s:property value="resource"/></td>
    </tr>
    <tr>
      <td class="idx">应付客户</td>
      <td class="data">&nbsp;<s:property value="outcome.supplierName"/></td>
    </tr>
   </table>
   <br>
   
   <table border="0" width="100%">
    <tr>
      <td colspan="7">付款申请明细</td>
    </tr>
    <tr>
      <td class="lstidx" width="30">No.</td>
      <td class="lstidx">团号</td>
      <td class="lstidx">内容</td>
      <td class="lstidx">应付款</td>
      <td class="lstidx">已付款</td>
      <td class="lstidx">未付款</td>
      <td class="lstidx">币种</td>
      <td class="lstidx">计调提交汇率</td>
      <td class="lstidx">申请金额<br>(对应币种)</td>
      <td class="lstidx">财务结算汇率</td>
      <td class="lstidx">申请金额折合人民币</td>
    </tr>
    
    <s:iterator value="outcome.payNoticeList" status="rowcounter">
      <tr>
        <td class="cdata">
          <s:property value="#rowcounter.count" />
          <s:hidden name="paynoticeList(%{id}).outcomeAcctId" value="%{outcomeAcctId}"/>
        </td>
        <td class="data"><s:property value="tourNo" /></td>
        <td class="data"><s:property value="description" /></td>
        <td class="rdata"><s:property value="payment" /></td>
        <td class="rdata"><s:property value="endPayment" /></td>
        <td class="rdata"><s:property value="nopayPayment" /></td>
        <td class="rdata"><s:property value="currency" /></td>
        <td class="rdata"><s:property value="opRoe" /></td>
        <td class="rdata">
        <s:textfield id="fcnowpay%{id}"
                     name="paynoticeList(%{id}).fcNowpayPayment"
                     value="%{fcNowpayPayment}"
                     cssStyle="text-align: right"
                     onchange="javascript:applychange('%{outcome.payNoticeList.size()}')">
        </s:textfield>
        </td>
        <td class="rdata">
          <s:textfield id="roe%{id}"
                       name="paynoticeList(%{id}).roe"
                       value="%{roe}"
                       cssStyle="text-align: right"
                       size="8"
                       onchange="javascript:applychange('%{outcome.payNoticeList.size()}')">
          </s:textfield>
        </td>

        <td class="rdata">
        <s:textfield id="nowpay%{id}"
                     name="paynoticeList(%{id}).nowpayPayment"
                     size="10" 
                     maxlength="10"
                     cssStyle="text-align: right;background-color: menu;"
                     value="%{nowpayPayment}"
                     readonly="true">
        </s:textfield>
        </td>
      </tr>
    </s:iterator>
    
    </table>
    <table border="0" width="100%">
    <tr>
      <td class="lstidx" width="20%">申请付款合计</td>
        <td>
         <s:textfield 
          id="amount"
             name="outcome.amount"
               size="20" 
               maxlength="15"
               readonly="readonly"></s:textfield>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <hr>
      </td>
    </tr>
    <tr>
      <td class="lstidx">支付方式</td>
      <td>
      <s:radio name="outcome.payMode"
             list="paymentList"
             listKey="value"
             listValue="label">
        </s:radio>
      </td>
    </tr>
    <tr>
    　　<td class="lstidx">票务配送</td>
    	<td>
    	  <s:radio name="outcome.carryTicket"
    	           list="#{ 0:'否', 1: '是' }" 
                   listKey="key" 
                   listValue="value">
    	  </s:radio>
     	</td>
    </tr>
    <tr>
      <td class="lstidx">说明</td>
      <td colspan="6"><s:textarea name="outcome.note" cols="100" rows="3"></s:textarea></td>
    </tr>
    </table>
  <div align="center"><s:submit value="保存修改" />
    <input type="button" value="返回" onClick="javascript:goBack();">
  </div>  
</s:form>
</body>
</html>
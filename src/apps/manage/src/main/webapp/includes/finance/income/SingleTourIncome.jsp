<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单团收款</title>
<meta name="menu" content="FinanceMenu"/>
<meta name="heading" content="单团收款">
</head>

<body>
<script type="text/javascript">
<!--//

function change()
{
	var fm = document.submitIncome;
  fm.action = "<s:url action='showBooks' namespace='/finance' />"
	fm.submit();
}

function dbchange(target)
{
  if (target> count)
    count = target;

  var tt = document.getElementById("unPay" + target);
  var tc = document.getElementById("payBack" + target);
  tc.value = tt.value;
  document.getElementById("offSetMon").value = totalpayment();
}

function chchange(target)
{
  if (target> count)
    count = target;
  document.getElementById("offSetMon").value = totalpayment();
}

var count = 0;
var payment = 0;
var totalpay = 0;

function changepay()
{
  payment = document.getElementById("incomeMon").value;
}

function totalpayment()
{
  totalpay = 0;

  for(i=0; i<=count; i++ )
  {
    var tc = document.getElementById("payBack" + i);
    if (tc != null)
    {
      if (tc.value != null)
      {
        var temp = new Number(tc.value);
        totalpay = totalpay + temp;
      }
    }
  }
  
  return totalpay;
}

function submitPay()
{
  var fm = document.SingleTourIncomeSubmit;
  changepay();
  totalpayment();
  if (payment < totalpay)
  {
    alert("核销金额大于收款金额。");
    return ;
  }
  fm.submit(); 
}

function submitInvoice(param)
{
  var fm = document.showInvoice;
  fm.incomeId.value = param;
  fm.submit(); 
}

function printInv(param, cnamount)
{
  var fm = document.reportRun;
  document.getElementById('paramid').value = param;
  document.getElementById('cnAmount').value = cnamount;
  fm.submit();
}

//-->
</script>
<s:form action="SingleTourIncomeSubmit" namespace="/finance" method="POST" theme="simple">
<table border="1" style="width: 100%">
  <tr>
  	<td class="idx">团号</td>
  	<td colspan="3"><s:property value="tourNo"/></td>
  </tr>
  <tr>
    <td class="idx">收款金额</td>
    <td>
      <s:textfield id="incomeMon" name="gathering.amount" />
    </td>
    <td class="idx">核销金额</td>
    <td>
      <s:textfield id="offSetMon" name="gathering.offSetAmount" />
    </td>
  </tr>
  <tr>
    <td class="idx">摘要</td>
    <td colspan="3">
      <s:textarea name="gathering.note" cols="100" rows="3">
      </s:textarea><font color="red">*</font></td>
  </tr>
</table>

<br>

<table style="width: 100%">
  <tr>
	  <td class="lstidx" width="30">NO.</td>
      <td class="lstidx">客户</td>
      <td class="lstidx">类型</td>
      <td class="lstidx">销售员</td>
      <td class="lstidx">预订人数</td>
      <td class="lstidx">应收款</td>
      <td class="lstidx">已收款</td>
      <td class="lstidx">未收款</td>
	  <td class="lstidx">现收款</td>
      <td class="lstidx">支付方式</td>
      <td class="lstidx">收款人</td>
      <td class="lstidx">收款日期</td>
	  <td class="lstidx">操作</td>
	</tr>

	<s:iterator value="bookList" status="rowCounter">
	  <s:hidden name="bookList(%{id}).recordNo" value="%{recordNo}"></s:hidden>
    <s:hidden name="bookList(%{id}).customerId" value="%{customerId}"></s:hidden>
	  <tr>
        <td class="cdata"><s:property value="#rowCounter.count" /></td>
        <td class="data"><s:property value="customer.name" />&nbsp;</td>
        <td class="data"><FONT color="red"><s:property value="clearingCycle"/></FONT></td>
        <td class="cdata"><s:property value="salesman.userName" />&nbsp;</td>
        <td class="rdata"><s:property value="pax" /></td>
        <td class="cdata" align="right">
          <s:property value="dbamt"/>
          <s:hidden id="expense%{id}"
                    name="bookList(%{id}).dbamt"
                    value="%{dbamt}" />
        </td>
        <td class="cdata" align="right">
          <s:property value="payCosts" />
          <s:hidden name="bookList(%{id}).payCosts" value="%{payCosts}"/>
        </td>
        <td class="cdata" align="right">
        	<s:property value="unPay" />
            <s:hidden id="unPay%{id}"
                      name="bookList(%{id}).unPay"
                      value="%{unPay}" />
        </td>
        <td class="cdata" class="rdata">
        <s:textfield id="payBack%{id}" 
                     name="bookList(%{id}).payBack"
                     ondblclick="javascript:dbchange('%{id}')"
                     onchange="javascript:chchange('%{bookList.size()}')"/>
        </td>
        <!-- 支付方式  -->
        <td class="cdata" class="rdata">
        <s:select list="payModeList"
                  listKey="value" 
                  listValue="label"
                  name="bookList(%{id}).paymentType">
        </s:select>
        </td>
        <!-- 支付方式  -->
        <td class="cdata" class="rdata">
      		<s:select list="salesManList"
          				  name="bookList(%{id}).receiver"
          				  listKey="userId"
          				  listValue="userName"
          				  headerKey="-1"
          				  headerValue="请选择">
      		</s:select>
        </td>

        <td class="cdata" class="rdata">
          <s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" name="bookList(%{id}).incomeDate">
          </s:textfield>
        </td>
        <td class="cdata" class="rdata">
        <s:iterator value="gatherings">
        <a href="#" onclick='submitInvoice(<s:property value="incomeId"/>)'>填写发票</a><br>
        </s:iterator>

        <s:iterator value="invices"><a href="#" onclick="javascript:printInv('<s:property value="recordNo"/>','<s:property value="amountChinese"/>')"><s:property value="recordNo"/> 打印</a><br>
        </s:iterator>
        </td>
     </tr>
    </s:iterator>

    <s:if test="bookList.isEmpty() == false">
    <tr bgcolor="#b9c0ff">
      <td align="center" colspan="4"><b>合计</b></td>
      <td align="right"><b><s:property value="totalPax"/></b></td>
      <td align="right"><b><s:property value="totalExpense"/></b></td>
      <td align="right"><b><s:property value="totalPayCosts"/></b></td>
      <td align="right"><b><s:property value="totalUnPay"/></b></td>
      <td align="right" colspan="2">&nbsp;</td>
    </tr>
  </s:if>
</table>

<div align="center">
 <input type="button" onclick="javascript:submitPay();" value="提交">
 <input type="button" value="返回" onClick="javascript:history.go(-1);">
</div>

</s:form>

<s:form action="showInvoice" namespace="/finance" method="POST" theme="simple">
  <s:hidden name="incomeId"></s:hidden>
</s:form>

<s:form action="reportRun" namespace="/" method="POST" theme="simple">
  <s:hidden name="parameters(0).name" value="INV_NO"></s:hidden>
  <s:hidden id="paramid" name="parameters(0).data"></s:hidden>
  <s:hidden name="parameters(1).name" value="PRINT_LINE"></s:hidden>
  <s:hidden name="parameters(1).data" value="0"></s:hidden>
  <s:hidden name="parameters(2).name" value="CHINESE_AMOUNT"></s:hidden>
  <s:hidden id="cnAmount" name="parameters(2).data"></s:hidden>
  <s:hidden name="reportId" value="1"></s:hidden>
</s:form>
</body>
</html>

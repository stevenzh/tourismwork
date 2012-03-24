<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改收款记录</title>
<meta name="menu" content="FinanceMenu"/>
<meta name="heading" content="修改收款记录">
</head>

<body>
<script type="text/javascript">
<!--//

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
  var fm = document.editIncomes;
  changepay();
  totalpayment();
  if (payment < totalpay)
  {
    alert("核销金额大于收款金额。");
    return ;
  }
  fm.submit(); 
}

function submitInvoice()
{
  var fm = document.showInvoice;
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
<s:form action="editIncomes" namespace="/finance" method="POST" theme="simple">

<s:hidden name="departmentNo"></s:hidden>
<s:hidden name="regionId"></s:hidden>
<s:hidden name="companyId"></s:hidden>
<s:hidden name="kenStartDate"></s:hidden>
<s:hidden name="kenEndDate"></s:hidden>
<s:hidden name="kenStartMon"></s:hidden>
<s:hidden name="kenEndMon"></s:hidden>

<s:hidden name="gathering.incomeId"></s:hidden>

<table border="1" cellpadding="0" cellspacing="0" width="100%">
  <!-- 
  <tr>
    <td class="idx">工作组</td>
    <td colspan="3">

    <s:select name="gathering.incomeDept"
              list="teamList"
              listKey="teamId"
              listValue="name"
              disabled="true">
    </s:select>
    </td>
  </tr>
  -->

  <tr>
    <td class="idx">客户</td>
    <td colspan="3">
    </td>
  </tr>
  <tr>
  	<td class="idx">方式</td>
  	<td colspan="3">
    <s:radio name="gathering.payMode"
			 list="payModeList" 
 			 listKey="value" 
 			 listValue="label">
    </s:radio>
    </td>
  </tr>
  <tr>
  	<td class="idx">摘要</td>
  	<td colspan="3"><s:textfield name="gathering.note"/></td>
  </tr>
    <tr>
      <td class="idx">收款日期</td>
      <td>
      <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="gathering.incomeDate">
      </sj:datepicker>
      </td>
      <td class="idx">收款人</td>
      <td>
        <s:select list="salesManList"
          				name="gathering.receiver"
          				listKey="userId"
          				listValue="userName">
  		  </s:select>
      </td>
    </tr>
  <tr>
    <td class="idx">收款金额</td>
    <td><s:textfield id="incomeMon" name="gathering.amount" /></td>
    <td class="idx">核销金额</td>
    <td><s:textfield id="offSetMon" name="gathering.offSetAmount"/></td>
  </tr>
  <tr>
    <td class="idx">登记日期</td>
    <td>&nbsp;<s:date name="gathering.opDate" format="yyyy-MM-dd HH:mm" /></td>
    <td class="idx">登记人</td>
    <td>&nbsp;<s:property value="gathering.opUser" /></td>
  </tr>
  <tr>
    <td class="idx">修改日期</td>
    <td>&nbsp;<s:date name="gathering.upDate" format="yyyy-MM-dd HH:mm" /></td>
    <td class="idx">修改人</td>
    <td>&nbsp;<s:property value="gathering.upUser" /></td>
  </tr>
</table>


<table border="1" cellpadding="0" cellspacing="0" width="100%">
  <tr>
  	<td colspan="10"><s:property value="customerName" />未付清款项</td>
  </tr>
  <tr>
    <td class="lstidx">NO.</td>
    <td class="lstidx">团号</td>
    <td class="lstidx">线路</td>
    <td class="lstidx">出发日期</td>
    <td class="lstidx">人数</td>
    <td class="lstidx">销售员</td>
    <td class="lstidx">应收款</td>
    <td class="lstidx">已收款</td>
    <td class="lstidx">未收款</td>
    <td class="lstidx">现收款</td>
  </tr>

  <s:iterator value="bookList" status="rowcounter">
  <s:hidden name="bookList(%{id}).recordNo" value="%{recordNo}"></s:hidden>
  <tr>
    <td class="cdata"><s:property value="#rowcounter.count" /></td>
    <td><s:property value="tourNo" /></td>
    <td><s:property value="line.lineName" /></td>
    <td><s:date name="outDate" format="yyyy-MM-dd" /></td>
    <td class="cdata"><s:property value="pax" /></td>
    <td><s:property value="salesman.userName" /></td>
    <td align="right">
    <s:property value="dbamt"/>
    <s:hidden id="expense%{id}"
              name="bookList(%{id}).dbamt"
              value="%{dbamt}" />
    </td>
    <td align="right">
      <s:property value="payCosts" />
      <s:hidden name="bookList(%{id}).payCosts" value="%{payCosts}"/>
    </td>
    <td align="right">
    <FONT color="red"><s:property value="unPay" /></FONT>
    <s:hidden id="unPay%{id}"
              name="bookList(%{id}).unPay"
              value="%{unPay}" />
    </td>
    <td>
    <s:textfield id="payBack%{id}"
                 name="bookList(%{id}).payBack"
                 value="%{payBack}"
                 cssStyle="text-align: right"
                 onchange="javascript:chchange(%{bookList.size()});"/>
    </td>
  </tr>
  </s:iterator>
  <tr>
  	<td>&nbsp;</td>
  	<td colspan="3"><center>合计</center></td>
  	<td><s:property value="totalPax"/></td>
  	<td>&nbsp;</td>
  	<td align="right">
  		<s:property value="totalExpense" />
  	</td>
  	<td align="right">
  		<s:property value="totalPayCosts"/>
  	</td>
  	<td align="right">
		<FONT color="red"><s:property value="totalUnPay"/></FONT>
  	</td>
  	<td>&nbsp;</td>
  </tr>
</table>

<table border="1" cellpadding="0" cellspacing="0" width="100%">
  <tr>
  	<td colspan="10">已填写发票记录</td>
  </tr>
  <tr>
    <td class="lstidx">发票号码.</td>
    <td class="lstidx">金额</td>
    <td class="lstidx">填写日期</td>
    <td class="lstidx">操作</td>
  </tr>

  <s:iterator value="gathering.invices">
  <tr>
    <td class="cdata"><s:property value="recordNo"/>&nbsp;</td>
    <td class="cdata"><s:property value="amount"/>&nbsp;</td>
    <td class="cdata"><s:date name="crateDate" format="yyyy-MM-dd"/>&nbsp;</td>
    <td class="cdata"><a href="#" onclick="javascript:printInv('<s:property value="recordNo"/>','<s:property value="amountChinese"/>')"> 打印</a>&nbsp;</td>
  </tr>
  </s:iterator>
</table>

<div align="center">
<input type="button" onclick="javascript:submitPay();" value="提交">
<input type="button" onclick="javascript:submitInvoice();" value="填写发票">
<input type="button" value="返回" onClick="javascript:history.go(-1);">
</div>
<br>
</s:form>

<s:form action="showInvoice" namespace="/finance" method="POST" theme="simple">
  <s:hidden name="incomeId" value="%{gathering.incomeId}"></s:hidden>
</s:form>

<form name="reportRun" action='<s:property value="#application['shop_title']" />/reportRun.action' method="POST">
  <s:hidden name="parameters(0).name" value="INV_NO"></s:hidden>
  <s:hidden id="paramid" name="parameters(0).data"></s:hidden>
  <s:hidden name="parameters(1).name" value="PRINT_LINE"></s:hidden>
  <s:hidden name="parameters(1).data" value="0"></s:hidden>
  <s:hidden name="parameters(2).name" value="CHINESE_AMOUNT"></s:hidden>
  <s:hidden id="cnAmount" name="parameters(2).data"></s:hidden>
  <s:hidden name="reportId" value="1"></s:hidden>
</form>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收款</title>
<meta name="menu" content="FinanceMenu"/>
<meta name="heading" content="收款">
</head>

<body>
<script type="text/javascript">
<!--//

//省份修改时，刷新客户列表(autocomplate)
function prochange(param) {
	$.ajax({
	   type: "post",
	   url: '<s:url value="/json/getAccountsByRegion.jspa" encode="false" includeParams="none"/>' ,
	   timeout: 20000,
	   data: {province: $("#regionCode").val()},
	   error: function(){
	      // alert('服务器错误');
	   },
	   async: false,
	   success: function(data){
	       $.each(data, function(i, n){
	       if (i == 'map')
	       {
	         $("#agentCode").autocomplete(n, {
	             width: 320,
	             max: 4,
	             highlight: false,
			        		matchContains: true,
			        		minChars: 0,
	             scroll: true,
	             scrollHeight: 300,
	             formatItem: function(row, i, max) {
	               return  row.value +"->["+ row.label + "]";
	             },
	             formatMatch: function(row, i, max) {
	               return row.value;
	             },
	             formatResult: function(row, value) {
	               return row.value;
	             }
	           }).result(function(event, row) {
	             $("#agentCode").val(row.label);
	             changeAgent();
	           });
	       }
	   });
	 }
	});
	$("#agentCode").focus();
}


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
  var fm = document.submitIncome;
  changepay();
  totalpayment();
  if (payment < totalpay)
  {
    alert("核销金额大于收款金额。");
    return ;
  }
  fm.submit(); 
}

//-->
</script>
<s:form action="submitIncome" namespace="/finance" method="POST" theme="simple">
<table border="1" cellpadding="0" cellspacing="0" width="100%">
	<tr>
  	<td class="idx">省份</td>
    <td colspan="3">
      <s:select id="regionCode"
                name="book.region"
                list="regions"
                listKey="label"
                listValue="value"
                emptyOption="true"
                onchange="javascript:prochange();"
                theme="simple">
      </s:select>
    </td>
	</tr>
  <tr>
    <td class="idx">客户</td>
    <td colspan="3">
    <s:select id="agentCode"
    		      name="kenCustomerId"
              list="agentList" 
              listKey="label"
              listValue="value"
              emptyOption="true"
              headerKey=""
              headerValue="所有"
              onchange="javascript:change()">
    </s:select>
    </td>
  </tr>
  <tr>
    <s:if test='agent.clearingCycle == "N"'>
	  <td colspan="4">    
    	此用户为现结客户
      </td>
    </s:if>
    <s:elseif test='agent.clearingCycle == "M"'>
      <td colspan="4">
    	此用户为月结用户
      </td>
    </s:elseif>
  </tr>
  <tr>
    <td class="idx">联系人</td>
    <td>
    	&nbsp;<s:property value="agent.contact"/>
    </td>
    <td class="idx">联系电话</td>
    <td>
    	&nbsp;<s:property value="agent.contactTel"/>
    </td>
  </tr>
   <tr>
  	<td colspan="4" height="30">&nbsp;</td>
  </tr>
</table>

<br>

<table border="1" cellpadding="0" cellspacing="0" width="100%">
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
    <td class="lstidx">现收款(双击填充)</td>
  </tr>

  <s:iterator value="bookings" status="rowcounter">
  <s:hidden name="bookings(%{id}).recordNo" value="%{recordNo}"></s:hidden>
  <tr>
    <td class="cdata"><s:property value="#rowcounter.count" /></td>
    <td>&nbsp;<s:property value="tourNo" /><s:hidden name="bookings(%{id}).tourNo" value="%{tourNo}" /></td>
    <td>&nbsp;<s:property value="line.lineName" /><s:hidden name="bookings(%{id}).routeName" value="%{line.lineName}" /></td>
    <td>&nbsp;<s:date name="outDate" format="yyyy-MM-dd"/><s:hidden name="bookings(%{id}).outDate" value="%{outDate}" /></td>
    <td>&nbsp;<s:property value="pax" /><s:hidden name="bookings(%{id}).pax" value="%{pax}" /></td>
    <td>&nbsp;<s:property value="salesman.userName" /><s:hidden name="bookings(%{id}).salesman.userId" value="%{salesman.userId}" /></td>
    <td align="right">
      <s:property value="dbamt"/>
      <s:hidden id="expense%{id}"
                name="bookings(%{id}).dbamt"
                value="%{dbamt}" />
    </td>
    <td align="right"><s:property value="payCosts" />
    <s:hidden name="bookings(%{id}).payCosts" value="%{payCosts}"/></td>
    <td align="right"><s:property value="unPay" />
    <s:hidden id="unPay%{id}"
              name="bookings(%{id}).unPay"
              value="%{unPay}" /></td>
    <td>
    <s:textfield id="payBack%{id}"
                 name="bookings(%{id}).payBack"
                 value="%{payBack}"
                 ondblclick="javascript:dbchange('%{id}')"
                 onchange="javascript:chchange('%{bookings.size()}')"
                 cssStyle="text-align: right"/>
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
  		<s:property value="totalUnPay"/>
  	</td>
  	<td>&nbsp;</td>
  </tr>
</table>

<table border="1" cellpadding="0" cellspacing="0" width="100%">
  <tr>
    <td class="idx">收款日期</td>
    <td colspan="3">
    <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="gathering.incomeDate">
    </sj:datepicker>
    </td>
  </tr>
  <tr>
    <td class="idx">工作组</td>
    <td>
    <s:select name="departmentNo"
              list="teamList"
              listKey="teamId"
              listValue="name">
    </s:select>
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
    <td>
      <s:textfield id="incomeMon" name="gathering.amount" />
    </td>
    <td class="idx">核销金额</td>
    <td>
      <s:textfield id="offSetMon" name="gathering.offSetAmount" />
    </td>
  </tr>
  <tr>
  	<td class="idx">支付方式</td>
  	<td colspan="3">
      <s:radio list="payModeList" 
  		  	     name="gathering.payMode" 
  				     listKey="value" 
  				     listValue="label">
  		 </s:radio>
      </td>
  </tr>
  <tr>
    <td class="idx">摘要</td>
    <td colspan="3">
      <s:textfield name="gathering.note" size="30"/><font color="red">*</font></td>
  </tr>
</table>
<div align="center">
 <input type="button" onclick="javascript:submitPay();" value="提交">
 <input type="button" value="返回" onClick="javascript:history.go(-1);">
</div>

</s:form>

</body>
</html>

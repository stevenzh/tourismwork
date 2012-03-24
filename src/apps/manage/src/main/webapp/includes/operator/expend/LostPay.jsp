<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>未付款部分</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="付款申请书">
</head>

<body>
<script type="text/javascript">
<!--//
function SupplierByTypeChanged()
{
  var combo = document.getElementById("supplierId")
  while (combo.options.length>0) {
      combo.remove(0);
  }
  combo.disabled=false;

  $.ajax({
      type: "post",
      url: '<s:url value="/json/getSuppliersByType.jspa" encode="false" includeParams="none"/>',
      timeout: 20000,
      error: function(){
          // alert('服务器错误');
      },
      data: {resource: $("#st").val(), teamId: $("#teamId").val()},
      async: false,
      success: function(data){
          $.each(data, function(i, n){

          if (typeof(n)=='object')
          {
              combo.options.add(new Option("全部", ""));
              
              $.each(n,function(j,m){
            	  combo.options.add(new Option(m.value, m.label));
              });
          }
      });
      }
  });

  // 设置如果是机票
  var st = document.getElementById("st");
  var ticketT = document.getElementById("carryTicket1")
  var ticketF = document.getElementById("carryTicket0");
  if (st.value == '2')
    ticketT.checked = true;
  //else
  //  ticketF.checked = true;
}

function ticketchange()
{
  var st = document.getElementById("st");
  var ticketT = document.getElementById("carryTicket1");
  var ticketF = document.getElementById("carryTicket0");
  
  if (st.value == '2')
    ticketT.checked = true;
  //else
  //  ticketF.checked = true;
  
}

var count = 0;
var totalpay = 0;
function applychange(target,id)
{
  var noAppPayment =  document.getElementById("noAppPayment" + id);
  var fcnowpay = document.getElementById("fcnowpay"+id);
  var roep = document.getElementById("roe" + id);
  var temp = new Number(fcnowpay.value) * new Number(roep.value);
  if(temp.toFixed(2) > new Number(noAppPayment.value))
  {
      alert("你申请的金额已超出申请范围!");
      fcnowpay.value=0;
      return;
  }
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

<s:form action="submitPayRequisition" namespace="/operator" method="POST" theme="simple">

  <table border="0" width="100%">
    <tr>
    	<td class="idx" width="200">工作组</td>
    	<td>
    	<s:select id="teamId"
	    				  name="kenTeamId"
	    				  list="teamList"
	    				  listKey="teamId"
	    				  listValue="name"
	    				  onchange="javascript:SupplierByTypeChanged()">
    	</s:select>
    	</td>
    </tr>
    <tr>
    	<td class="idx" width="200">类型</td>
    	<td>
    	<s:select id="st"
	    				  name="kenSupplierType"
	    				  list="supplierTypeList"
	    				  listKey="value"
	    				  listValue="label"
	    				  headerKey=""
	    				  headerValue="请选择"
	    				  onchange="javascript:SupplierByTypeChanged()">
    	</s:select>
    	</td>
    </tr>
    <tr>
			<td class="idx">应付供应商 <font color="red">*</font></td>
			<td>
        <s:select id="supplierId"
                  name="kenSupplierId"
                  list="supplierList"
                  listKey="supplierId"
                  listValue="Name"
                  emptyOption="true">
        </s:select>
      </td>
    </tr>
    <tr>
			<td class="idx">团号 <font color="red">*</font></td>
			<td>
			<sj:autocompleter name="kenTourNo"
			                  list="stnList"
			                  listKey="value"
			                  listValue="label">
			</sj:autocompleter>
    </tr>
    <tr>
      <td>
    	<s:submit action="refreshPayRequisition" value="%{getText('common.forms.search')}"></s:submit>
      </td>
	  </tr>
  </table>

   <br>
   
   <table border="0" width="100%">
    <tr>
    	<td><font color="red">以下为该团单团核算明细表</font></td>
    </tr>
    <tr>
      <td align="center" class="lstidx">No.</td>
      <td align="center" class="lstidx">团号</td>
      <td align="center" class="lstidx">内容</td>
      <td align="center" class="lstidx">应付款</td>
      <td align="center" class="lstidx">已付款</td>
      <td align="center" class="lstidx">已申请款</td>
      <td align="center" class="lstidx">未申请款</td>
      <td class="lstidx">币种</td>
      <td class="lstidx">汇率</td>
      <td class="lstidx">申请金额<br>(对应币种)</td>
      <td class="lstidx">折合人民币</td>
    </tr>
    
    <s:iterator value="paynoticeList" status="rowcounter">
			<tr>
			  <td class="cdata">
          <s:property value="#rowcounter.count" />
          <s:hidden name="paynoticeList(%{id}).acctId" value="%{acctId}"/>
          <s:hidden name="paynoticeList(%{id}).tourNo" value="%{tourNo}"/>
        </td>
				<td class="data"><s:property value="tourNo" /></td>
				<td class="data"><s:property value="description" /></td>
				<td class="rdata"><s:property value="payment" /></td>
				<td class="rdata"><s:property value="endPayment" /></td>
				<td class="rdata"><s:property value="alAppPayment" /></td>
				<td class="rdata">
				   <s:property value="noAppPayment" />
				   <s:hidden id="noAppPayment%{id}" name="paynoticeList(%{id}).noAppPayment" value="%{noAppPayment}"/>
				</td>
        <td class="rdata"><s:property value="currency" /></td>
        <td class="rdata">
          <s:property value="opRoe" />
          <s:hidden id="roe%{id}" name="paynoticeList(%{id}).opRoe" value="%{opRoe}">
          </s:hidden>
        </td>

        <td class="rdata">
        <s:textfield id="fcnowpay%{id}"
                     name="paynoticeList(%{id}).fcNowpayPayment"
                     size="10" 
                     maxlength="10"
                     cssStyle="text-align: right"
                     value="%{fcNowpayPayment}" 
                     onchange="javascript:applychange('%{paynoticeList.size()}','%{id}')">
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
      <td class="lstidx" width="20%">此次付款合计</td>
        <td>
        <input id="amount"
        	     name="outcome.amount"
               size="20" 
               maxlength="15"
               readonly="readonly"
               style="background-color: menu;"/>
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
      <td class="lstidx">说明</td>
      <td colspan="6"><textarea name="outcome.note" cols="100" rows="3"></textarea></td>
    </tr>
    <TR>
      <TD colspan="7"><HR /></TD>
    </TR>
    <tr>
    	<td class="lstidx">票务配送</td>
    	<td>
    	  <s:radio id="carryTicket"
                   name="outcome.carryTicket"
    	           list="#{ 0:'否', 1: '是' }" 
                   listKey="key" 
                   listValue="value"
                   onclick="ticketchange()">
    	  </s:radio>
     	</td>
    </tr>
    <tr>
    	<td class="lstidx">取票时限</td>
    	<td>
          <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="outcome.carryLastDate">
          </sj:datepicker>
     	</td>
    </tr>
    <tr>
    	<td class="lstidx">配送说明(总价,张数,联系人)</td>
    	<td>
    	  <textarea name="outcome.carryNote" cols="100" rows="3"></textarea>
     	</td>
    </tr>
    </table>
	  <div align="center">
    审核并提交财务
    <s:checkbox name="audit" ></s:checkbox>&nbsp;
    <s:submit value="保存" />
    </div>  
</s:form>

</body>
</html>
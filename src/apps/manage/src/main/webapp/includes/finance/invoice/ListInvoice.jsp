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
function DeleteRow(param)
{					
   var fm=document.listInvoice;
   fm.inviceId.value=param;
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='deleteInvoice' namespace='/finance'/>";
    fm.submit();
}

function submitInvoice()
{
  var fm = document.addInvoice;
  fm.submit(); 
}

function printInv(param, cnamount)
{
  var fm = document.reportRun;
  document.getElementById('paramid').value = param;
  document.getElementById('cnAmount').value = cnamount;
  fm.submit();
}

function _getlist(type)
{
  var frm = document.listInvoice;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}
//-->
</script>
<s:form action="listInvoice" namespace="/finance" method="POST" theme="simple">
<s:hidden name="inviceId"></s:hidden>

<table border="1" cellpadding="0" cellspacing="0" width="80%">
    <tr>
      <td class="idx">登记日期开始</td>
      <td>
      <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="startDate">
      </sj:datepicker>
      </td>
      <td class="idx">登记日期截止</td>
      <td>
      <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="endDate">
      </sj:datepicker>
      </td>
    </tr>
  <tr>
    <td class="idx">发票金额</td>
    <td><s:textfield name="minAmount" /></td>
    <td class="idx">发票金额</td>
    <td><s:textfield name="maxAmount"/></td>
  </tr>
  <tr>
    <td class="idx">登记人</td>
    <td>&nbsp;<s:property value="gathering.opUser" /></td>
    <td colspan="2"><s:submit value="%{getText('common.forms.search')}"></s:submit></td>
  </tr>
</table>
<br>
<br>
<table border="1" cellpadding="0" cellspacing="0" width="100%">
  <tr>
    <td class="lstidx">发票号码.</td>
    <td class="lstidx">客户</td>
    <td class="lstidx">金额</td>
    <td class="lstidx">填写人</td>
    <td class="lstidx">填写日期</td>
    <td class="lstidx">操作</td>
  </tr>

  <s:iterator value="invoiceList" status="rowcounter">
  <s:if test="#rowcounter.count > fromRecord">
  <s:if test="#rowcounter.count <= toRecord">
  <tr>
    <td class="cdata"><s:property value="recordNo"/>&nbsp;</td>
    <td class="data"><s:property value="customer"/>&nbsp;</td>
    <td class="cdata"><s:property value="amount"/>&nbsp;</td>
    <td class="cdata"><s:property value="createUser"/>&nbsp;</td>
    <td class="cdata"><s:date name="crateDate" format="yyyy-MM-dd"/>&nbsp;</td>
    <td class="cdata">
    <a href="javascript:DeleteRow('<s:property value="recordNo" />')">删除</a>&nbsp;
    <a href="#" onclick="javascript:printInv('<s:property value="recordNo"/>','<s:property value="amountChinese"/>')"> 打印</a>&nbsp;</td>
  </tr>
  </s:if>
  </s:if>
  </s:iterator>
  
  <tr>
    <td colspan="10">
      <s:if test="invoiceList.isEmpty() == false">
      <%@ include file="/includes/PagerTable.jsp" %>
      </s:if>
    </td>
  </tr>
</table>

<div align="center">
<input type="button" onclick="javascript:submitInvoice();" value="填写发票">
</div>
<br>
</s:form>

<s:form action="addInvoice" namespace="/finance" method="POST" theme="simple">
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

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<s:url value='/styles/layout.css' encode='false' includeParams='none'/>" type="text/css">
<title>同业付款查询--Search</title>
<style type="text/css">
<!--
.style2 {font-size: 14px}
-->
</style>
</head>

<body>
<script type="text/javascript">
<!--//
  function paymentDetail(param)
  {
     var fm=document.PaymentDetail;
     document.getElementById("incomeId").value=param;
     fm.submit();
     
  }
//-->
</script>
<br>
<table style="width: 100%"  border="0" bgcolor="#b9c0ff">
  <tr>
    <td align="left" height="25"><b>搜索成果</b></td>
    <td align="right" >共搜索到符合条件的查询&nbsp;<b><s:property value="gatheringList.size"/></b>&nbsp;个</td>
  </tr>
</table>

<!-- 第二个Table开始 -->
<s:form action="PaymentSearchSubmit" namespace="/accounts" method="post" theme="simple">
<table width="700" border="0">
  <tr> 
    <td height="25" bordercolor="0"><font size="-1">付款日期：</font></td>
    <td height="25" colspan="2" bordercolor="0">
    <input name="paymentDateStart" size="10" type="text" onClick="WdatePicker()"/> 至
    <input name="paymentDateEnd" size="10" type="text" onClick="WdatePicker()"/>
		</td>
	</tr>
	<tr> 
    <td height="25" bordercolor="0"><font size="-1">金额范围：</font></td>
    <td height="25" colspan="2" bordercolor="0"><font size="-1">
	    <s:textfield id="payGatherStart" name="payGatherStart" size="12">
	    </s:textfield> - 
	    <s:textfield id="payGatherEnd" name="payGatherEnd" size="12">
	    </s:textfield><font color="red">当输入一个金额时搜索等于该金额的记录</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    <s:submit value="%{getText('common.forms.search')}"></s:submit></font>
    </td>
  </tr>
</table>

</s:form>

<table align="left" width="700" border="1" bordercolor="#000000">
  <tr bgcolor="#b9c0ff"> 
    <th height="25" align="center"><font size="-1">No.</font></th>
    <th align="center" width="70"><font size="-1">收款日期</font></th>
    <th align="center"><font size="-1">方式</font></th>
    <th align="center"><font size="-1">摘要</font></th>
    <th align="center"><font size="-1">付款金额</font></th>
    <th align="center"><font size="-1">核销金额</font></th>
    <th align="center"><font size="-1">未销金额</font></th>
  </tr>

  <s:iterator value="gatheringList" status="rowcounter">
  <tr onMouseOver="this.style.backgroundColor='#CCFFCC'"  onmouseout="this.style.backgroundColor='#ffffff'"> 
     <td align="center"><s:property value="#rowcounter.count"/></td>
     <td align="center">&nbsp;<s:date name="incomeDate" format="yyyy-MM-dd"/></td>
     <td align="center">&nbsp;<s:property value="incomeModeShow"/></td>
     <td align="left">&nbsp;<a href="<s:url namespace='/accounts' action='PaymentDetail'/>?incomeId=<s:property value='incomeId'/>" title="点击查看详细信息！" target="_blank"><s:property value="note"/></a></td>
     <td align="right">&nbsp;<s:property value="amount"/></td>
     <td align="right">&nbsp;<s:property value="offSetAmount"/></td>
     <td align="right">&nbsp;<s:property value="unOffSetMon"/></td>
  </tr>
  </s:iterator>

  <tr bgcolor="#b9c0ff"> 
    <td height="25" align="center">&nbsp;</td>
    <td align="center" colspan="3"><strong>合 计</strong></td>
    <td align="right"><s:property value="totalExpense"/></td>
    <td align="right"><s:property value="totalPayCosts"/></td>
    <td align="right"><s:property value="totalUnPay"/></td>
  </tr>
</table>
    
<s:form action="PaymentDetail" namespace="/accounts" method="post" theme="simple">
  <s:hidden id="incomeId" name="incomeId"></s:hidden>
</s:form>

</body>
</html>

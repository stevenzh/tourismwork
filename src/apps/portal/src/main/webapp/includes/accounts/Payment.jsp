<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<s:url value='/styles/layout.css' encode='false' includeParams='none'/>" type="text/css">
<title>付款详细内容--Search</title>
<style type="text/css">
<!--
.style2 {font-size: 14px}
-->
</style>
</head>
<body>
<br>
<div align="right"><font size="-1">
  <font color="blue">
    内部用户 部门：<s:property value="#session['EBIZ_USER'].departmentName"/>
  &nbsp;&nbsp;&nbsp;<s:property value="#session['EBIZ_USER'].userName" /></font>&nbsp;
  <b>订单查询</b>&nbsp;
  <s:url var="url" namespace="/accounts" action="PaymentSearch" /><s:a href="%{url}">付款查询</s:a>&nbsp;
  <s:url var="url" namespace="/accounts" action="Logout" /><s:a href="%{url}">退出</s:a>&nbsp;
  <a href="<s:url action='Home' namespace='' includeParams='none'/>">首页</a>
  </font>
  &nbsp;&nbsp;
</div>
<div align="center"><img src="<s:url value='/images/logo.png' encode='false' includeParams='none'/>" ></div>

<table width="800" border="0" align="center">
  <tr>
  <td align="center"><table style="width: 100%"  border="0" bgcolor="#b9c0ff">
  <tr>
    <td align="left" height="25"><b>付款书</b></td>
    <td align="right" ></td>
  </tr>
</table>
</td>
  </tr>
  <tr>
  <td align="center">

<table width="760" border="0" align="center" bordercolor="#000000" >
  <tr> 
    <td > 

   	<!-- 第一个Table开始 -->
	    <table style="width: 100%" border="0">
          <tr> 
            <td height="30" bgcolor="#b9c0ff" class="title" align="center"><strong>付 款 书</strong></td>
      </tr>
	</table>
	</td></tr>
		<tr><td>&nbsp;</td></tr>
   	    <!-- 第一个Table结束 -->
        <!-- 第二个Table开始 -->
		<tr><td>
		<table align="center" style="width: 100%" border="1" bordercolor="#000000">
          <tr> 
            <td height="25" nowrap align="center"><strong>客&nbsp;&nbsp;&nbsp;&nbsp;户</strong></td>
            <td colspan="3">&nbsp;<s:property value="gathering.customer.name"/></td>
          </tr>

		  <tr> 
            <td height="25" nowrap align="center"><strong>摘&nbsp;&nbsp;&nbsp;&nbsp;要</strong></td>
            <td colspan="3">&nbsp;<s:property value="gathering.note"/></td>
          </tr>

		  <tr> 
            <td height="25" width="10%" align="center"><strong>收款日期</strong></td>
            <td width="40%"align="left">&nbsp;<s:date name="gathering.incomeDate" format="yyyy-MM-dd"/></td>
            <td width="9%" align="center"><strong>方&nbsp;&nbsp;&nbsp;&nbsp;式</strong></td>
            <td width="41%">&nbsp;<s:property value="gathering.incomeModeShow"/></td>
          </tr>

		  <tr> 
            <td height="25" width="10%" align="center"><strong>金&nbsp;&nbsp;&nbsp;&nbsp;额</strong></td>
            <td width="40%"align="left">&nbsp;<s:property value="gathering.amount"/></td>
            <td width="9%" align="center"><strong>核销金额</strong></td>
            <td width="41%">&nbsp;<s:property value="gathering.offSetAmount"/></td>
          </tr>
        </table>
		
	  </td>
	 </tr>


<!-- 第二个Table结束 -->

		<tr><td>
		<table align="center" style="width: 100%" border="1" bordercolor="#000000">
		  <tr bgcolor="#b9c0ff"> 
            <td width="30" height="25" align="center"><strong>No.</strong></td>
            <td align="center"><strong>团号</strong></td>
            <td align="center"><strong>线路</strong></td>
            <td align="center" width="70"><strong>出发日期</strong></td>
            <td align="center"><strong>人数</strong></td>

            <td align="center"><strong>应付款</strong></td>
            <td align="center"><strong>已付款</strong></td>
            <td align="center"><strong>未付款</strong></td> 
            <td align="center"><strong>现付款</strong></td>

          </tr>

         <s:iterator value="gathering.bookList" status="rowcounter">
          <tr onMouseOver="this.style.backgroundColor='#CCFFCC'" onMouseOut="this.style.backgroundColor='#ffffff'"> 
            <td height="25" align="center"><s:property value="#rowcounter.count"/></td>
			<td align="left"><a href="javascript:ShowB('001070700009');" title=""><s:property value="tourNo"/>&nbsp;</a></td>
            <td><s:property value="lineName"/>&nbsp;</td>
			<td align="center">&nbsp;<s:date name="gathering.incomeDate" format="yyyy-MM-dd"/></td>
			<td align="center">&nbsp;<s:property value="importPax"/></td>
            <td align="right">&nbsp;<s:property value="finalExpense"/></td>
            <td align="right">&nbsp;<s:property value="payCosts"/></td>
            <td align="right">&nbsp;<s:property value="unPay"/></td>
            <td align="right">&nbsp;<s:property value="payBack"/></td>
          </tr>
         </s:iterator>


          <tr bgcolor="#b9c0ff"> 
            <td height="25" >&nbsp;</td>
            <td align="center" colspan="3">合计</td>
            <td align="center">&nbsp;<s:property value="gathering.pax"/></td>
            <td align="right">&nbsp;<s:property value="gathering.finalExpense"/></td>
            <td align="right">&nbsp;<s:property value="gathering.payCosts"/></td>
            <td align="right">&nbsp;<s:property value="gathering.unPay"/></td>
            <td align="right">&nbsp;<s:property value="gathering.payBack"/></td>
          </tr>
        </table>
	</td></tr>

</table>
	</td>
  </tr>

</table>S
<div align="center">
<s:if test="#session['EBIZ_USER'].userKey eq \"N\"">
<font size="-1">客服热线 : XXX-XXXXXXX</font>
</s:if>&nbsp;&nbsp; <font size="-1">版权所有 : opentravelsoft.com &nbsp;&nbsp; 沪ICP备XXXXXXX号</font></div>

</body>
</html>

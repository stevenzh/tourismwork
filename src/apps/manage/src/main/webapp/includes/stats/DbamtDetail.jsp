<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>代理商应收团款情况一览</title>
<meta name="menu" content="OrderMenu"/>
</head>

<body>
<s:form action="search" namespace="/sales" method="post" theme="simple">
  <s:hidden name="startDate"></s:hidden>
  <s:hidden name="endDate"></s:hidden>

  <table style="width: 100%">
    <tr>
      <td>
  	  <table align="center" border="0" style="width: 100%">
  	  	<tr>
  	  		<td>
  	  			<FONT color="red"><s:date name="startDate" format="yyyy-MM-dd" /> 至 <s:date name="endDate" format="yyyy-MM-dd" /> 统计情况</FONT>
  	  		</td>
  	  	</tr>
  	  	<tr bgcolor="#b9c0ff">
          <td class="lstidx">No.</td>
          <td class="lstidx">订单号</td>
          <td class="lstidx">客户</td>
          <td class="lstidx">联系人</td>
          <td class="lstidx">线路</td>
          <td class="lstidx">销售员</td>
          <td class="lstidx">出发日期</td>
          <td class="lstidx">人数</td>
          <td class="lstidx">应收团款</td>
          <td class="lstidx">应付团款</td>
          <td class="lstidx">未收团款</td>
        </tr>
          <s:iterator value="bookList" status="rowcounter">
        <tr>
          <td class="cdata"><s:property value="#rowcounter.count"/></td>
          <td class="data"><s:property value="bookingNo"/></td>
          <td class="data"><s:property value="customer.name"/></td>
          <td class="data"><s:property value="contact"/></td>
          <td class="data"><s:property value="lineName"/></td>
          <td class="data"><s:property value="salesman.userName"/></td>
          <td class="data"><s:property value="outDate"/></td>
          <td class="data"><s:property value="pax"/></td>
          <td class="rdata"><s:property value="dbamt"/></td>
          <td class="rdata"><s:property value="payCosts"/></td>
          <td class="rdata"><s:property value="unPay"/></td>
        </tr>
          </s:iterator>
        <tr>
          <td colspan="6" align="center">合计</td>
          <td align="left"><s:property value="totalpax"/></td>
          <td align="right"><s:property value="totalDbamt"/></td>
          <td align="right"><s:property value="totalCramt"/></td>
          <td align="right"><s:property value="totalUnpay"/></td>
	    </tr>
  	  </table>
      </td>
    </tr>
    </table>
</s:form>
</body>
</html>

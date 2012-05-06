<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单查询</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="订单查询">
</head>
<body>
<script language="javascript">

function ShowDetail(name_no)
{
  var fm = document.SalesBookDetail;
  fm.reserveNo.value = name_no;
  fm.submit();
}

function cancel(){
  var fm = document.showPlanBooking;
  fm.action = "<s:url action='TourPlanSearch' namespace='/operator'/>"
  fm.submit();
}

</script>
<s:form action="showPlanBooking" namespace="/operator" method="POST">
	<s:hidden name="kenRrouteName"></s:hidden>
	<s:hidden name="kenDepartment"></s:hidden>
	<s:hidden name="kenPrincipal"></s:hidden>
	<s:hidden name="kenStartDate"></s:hidden>
	<s:hidden name="kenEndDate"></s:hidden>

	<table align="center" border="0" style="width: 100%">
		<tr>
			<td class="idx" width="200">线路名称：</td>
			<td><s:property value="plan.line.lineName" /></td>
		</tr>
		<tr>
			<td class="idx">出发日期：</td>
			<td><s:date name="plan.outDate" format="yyyy-MM-dd" /></td>
		</tr>
		<tr>
			<td class="idx">团号：</td>
			<td class="data"><s:property value="plan.tourNo" /></td>
		</tr>
		<tr>
			<td class="idx">可收：</td>
			<td class="data"><s:property value="plan.pax1" /></td>
		</tr>
		<tr>
			<td class="idx">已收 ：</td>
			<td class="data"><s:property value="plan.pax2" /></td>
		</tr>
		<tr>
			<td class="idx">可用名额：</td>
			<td class="data"><s:property value="plan.pax3" /></td>
		</tr>
		<tr>
			<td class="idx">占位：</td>
			<td class="data"><s:property value="plan.pax4" /></td>
		</tr>
	</table>

	<br>
	<table align="center" border="0" style="width: 100%">
		<tr>
			<td valign="top">
			<table border="1" bordercolor="#b9c0ff" cellpadding="0"	cellspacing="0" style="width: 100%">
				<tr bgcolor="#b9c0ff">
					<td class="lstidx" width="24">No.</td>
					<td class="lstidx" width="70">订单号</td>
					<td class="lstidx">客户</td>
					<td class="lstidx">销售员</td>
					<td class="lstidx" width="70">预订日期</td>
					<td class="lstidx" width="30">预订人数</td>
					<td class="lstidx" width="30">确认人数</td>
					<td class="lstidx" width="30">名单人数</td>
					<td class="lstidx" width="70">应收款</td>
					<td class="lstidx" width="70">已收款</td>
					<td class="lstidx" width="70">未收款</td>
					<td class="lstidx" width="70">结算款</td>
					<td class="lstidx" width="70">状态</td>
					<td class="lstidx" width="49">审核否</td>
				</tr>

				<s:iterator value="bookings" status="rowcounter">
					<s:if test='delkey == "Y"'>
						<tr bgcolor="#FFDDDD">
							<td align="center"><s:property value="#rowcounter.count" /></td>
							<td align="center"><a
								href="javascript:ShowDetail('<s:property value='recordNo'/>');"
								title="点击查看该订单的详细内容！"><s:property value='recordNo' /></a></td>
							<td><s:property value="customer.name" />&nbsp;</td>
							<td align="center"><s:property value="salesman.userName" />&nbsp;</td>
							<td align="center"><s:date name="reserveDate"
								format="yyyy-MM-dd" />&nbsp;</td>
							<td align="right"><s:property value="pax" /></td>
							<td align="right"><s:property value="confirmPax" /></td>
							<td align="right"><s:property value="importPax" />&nbsp;</td>
							<td align="right"><s:property value="dbamt" /></td>
							<td align="right"><s:property value="payCosts" /></td>
							<td align="right"><s:property value="%{dbamt -payCosts}" /></td>
							<td align="right">&nbsp;</td>
							<td align="center">取消</td>
							<td align="center"><s:if test='confirmStatus == "1"'>已占位</s:if>
							<s:else>未占位</s:else></td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td class="cdata"><s:property value="#rowcounter.count" /></td>
							<td class="cdata"><a
								href="javascript:ShowDetail('<s:property value='recordNo'/>');"
								title="点击查看该订单的详细内容！"><s:property value='recordNo' /></a></td>
							<td class="data"><s:property value="customer.name" />&nbsp;</td>
							<td class="cdata"><s:property value="salesman.userName" />&nbsp;</td>
							<td class="cdata"><s:date name="reserveDate"
								format="yyyy-MM-dd" />&nbsp;</td>
							<td class="rdata"><s:property value="pax" /></td>
							<td align="right"><s:property value="confirmPax" /></td>
							<td class="rdata"><s:property value="importPax" />&nbsp;</td>
							<td class="rdata"><s:property value="dbamt" /></td>
							<td class="rdata"><s:property value="payCosts" /></td>
							<td class="rdata"><s:property value="%{dbamt -payCosts}" /></td>
							<td class="rdata">&nbsp;</td>
							<td class="rdata">&nbsp;</td>
							<td class="cdata"><s:if test='confirmStatus == "1"'>已占位</s:if>
							<s:else>未占位</s:else></td>
						</tr>
					</s:else>

				</s:iterator>

				<!-- 
        <tr bgcolor="#b9c0ff">
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>合计</td>
          <td>&nbsp;</td>
          <td>27</td>
          <td>18</td>
          <td align="right">&nbsp;</td>
          <td align="right">&nbsp;</td>
          <td align="right">&nbsp;</td>
          <td align="right">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
         -->
			</table>
			</td>
		</tr>
		<tr>
			<td><input type="button" value="返 回" onclick="javascript:cancel()"></td>
		</tr>
	</table>
</s:form>

<s:form action="SalesBookDetail" theme="simple" namespace="/sales" method="POST">
	<s:hidden name="reserveNo"></s:hidden>
</s:form>
</body>
</html>

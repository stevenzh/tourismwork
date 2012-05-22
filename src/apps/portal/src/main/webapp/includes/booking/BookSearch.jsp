<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>订单一览表</title>
</head>

<body>

<script language="JavaScript">
	function ShowDetail(name_no) {
		var fm = document.BookDetail;
		fm.reserveNo.value = name_no;
		fm.submit();
	}

	function ShowRoute(routeNo) {
		var fm = document.RouteDetail;
		fm.routeNo.value = routeNo;
		fm.submit();
	}
</script>

<table align="center" width="760">
	<tr>
		<td colspan="2">
		<table border="0" style="width: 100%">
			<tr>
				<td>订单管理</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td><s:form action="BookSearchSubmit" namespace="/distribution"
			method="post" theme="simple">
			<table border="0" cellpadding="1" style="width: 100%">
				<tr>
					<td class="idx" width="71">分类：</td>
					<td class="data"><s:radio list="classTypeList" listKey="value"
						listValue="label" name="classType">
					</s:radio></td>
				</tr>
				<tr>
					<td class="idx">线路：</td>
					<td class="data"><s:textfield name="lineName" size="40"
						maxlength="80"></s:textfield> [输入线路名称，例如：普吉，则列出线路名称带普吉订单]</td>
				</tr>
				<tr>
					<td class="idx">出发日期：</td>
					<td class="data">
	        <input name="startDatePeriod" size="10" type="text" onClick="WdatePicker()"/> 至
	        <input name="endDatePeriod" size="10" type="text" onClick="WdatePicker()"/>
          </td>
				</tr>
				<tr>
					<td class="idx">预订日期：</td>
					<td class="data">
          <input name="reserveStartDatePeriod" size="10" type="text" onClick="WdatePicker()"/> 至
          <input name="reserveEndDatePeriod" size="10" type="text" onClick="WdatePicker()"/>
          </td>
				</tr>
				<tr>
					<td class="idx">合同号：</td>
					<td class="data"><s:textfield name="contractNo" size="16"
						maxlength="16"></s:textfield></td>
				</tr>
				<tr>
					<td class="idx">发票号：</td>
					<td class="data"><s:textfield name="invoiceNo" size="16"
						maxlength="16"></s:textfield></td>
				</tr>
				<tr>
					<td class="idx">客人姓名：</td>
					<td class="data"><s:textfield name="touristName" size="10"
						maxlength="10"></s:textfield> [输入客人姓名，例如：张，则列出客人姓名中带张字的订单]</td>
				</tr>
				<tr>
					<td class="idx">状态：</td>
					<td class="data"><s:radio list="bookStateList" listKey="value"
						listValue="label" name="bookState">
					</s:radio></td>
				</tr>
				<tr>
					<td class="idx">取消否：</td>
					<td class="data"><s:radio list="cancelStateList"
						listKey="value" listValue="label" name="cancelState">
					</s:radio>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<s:submit value="%{getText('common.forms.search')}"></s:submit></td>
				</tr>
			</table>
		</s:form></td>
	</tr>
	<tr>
		<td>
		<table border="1" bordercolor="#b9c0ff"
			cellspacing="0" width="760">
			<tr bgcolor="#b9c0ff">
				<td class="lstidx" width="24">No.</td>
				<td class="lstidx">订单号</td>
				<td class="lstidx">线路</td>
				<td class="lstidx">预订日期</td>
				<td class="lstidx">出发日期</td>
				<td class="lstidx">预订人数</td>
				<td class="lstidx">名单人数</td>
				<td class="lstidx">应收款</td>
				<td class="lstidx">已收款</td>
				<td class="lstidx">未收款</td>
				<td class="lstidx">结算款</td>
				<td class="lstidx">审核否</td>
			</tr>

			<s:iterator value="bookList" status="rowcounter">
				<s:if test='delkey eq "Y"'>
					<tr bgcolor="#FFDDEE">
						<td align="center"><s:property value="#rowcounter.count" /></td>
						<td align="center"><a
							href="javascript:ShowDetail('<s:property value='recordNo'/>');"
							title="点击查看该订单的详细内容！"><s:property value='recordNo' /></a></td>
						<td><a
							href="javascript:ShowRoute('<s:property value='lineNo'/>');"
							title="点击查看该线路的详细内容！"><s:property value="lineName" /></a></td>
						<td align="center"><s:date name="reserveDate"
							format="yyyy-MM-dd" /></td>
						<td align="center"><s:date name="outDate" format="yyyy-MM-dd" /></td>
						<td align="right"><s:property value="pax" /></td>
						<td align="right"><s:property value="importPax" />&nbsp;</td>
						<td align="right"><s:property value="dbamt" /></td>
						<td align="right"><s:property value="payCosts" /></td>
						<td align="right"><s:property value="%{dbamt -payCosts}" /></td>
						<td align="right">&nbsp;</td>
						<td align="center">取消&nbsp;</td>
					</tr>
				</s:if>

				<s:else>
					<tr>
						<td class="cdata"><s:property value="#rowcounter.count" /></td>
						<td class="cdata"><a
							href="javascript:ShowDetail('<s:property value='recordNo'/>');"
							title="点击查看该订单的详细内容！"><s:property value='recordNo' /></a></td>
						<td class="data"><a
							href="javascript:ShowRoute('<s:property value='lineNo'/>');"
							title="点击查看该线路的详细内容！"><s:property value="lineName" /></a></td>
						<td class="cdata"><s:date name="reserveDate"
							format="yyyy-MM-dd" /></td>
						<td class="cdata"><s:date name="outDate" format="yyyy-MM-dd" /></td>
						<td class="rdata"><s:property value="pax" /></td>
						<td class="rdata"><s:property value="importPax" />&nbsp;</td>
						<td class="rdata"><s:property value="dbamt" /></td>
						<td class="rdata"><s:property value="payCosts" /></td>
						<td class="rdata"><s:property value="%{dbamt -payCosts}" /></td>
						<td class="rdata">&nbsp;</td>
						<td class="cdata"><s:property value="cfmKey" />&nbsp;</td>
					</tr>
				</s:else>
			</s:iterator>
		</table>
		</td>
	</tr>
</table>

<s:form action="BookDetail" theme="simple" namespace="/distribution" method="POST">
	<s:hidden name="reserveNo"></s:hidden>
</s:form>
<s:form id="RouteDetail" action="RouteDetail" method="post"	namespace="/distribution">
	<s:hidden name="routeNo"></s:hidden>
</s:form>
</body>

</html>

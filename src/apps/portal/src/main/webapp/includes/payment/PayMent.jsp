<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<table width="760" border="0" align="center" cellspacing="1">
	<tr>
		<td valign="top" width="584">
	<form action="https://pay3.chinabank.com.cn/PayGate" method="POST" name="E_FORM">
			
	<s:hidden name="v_md5info" value="%{entity.md5info}"></s:hidden>
    
    <s:hidden name="v_mid" value="%{entity.mid}"></s:hidden>
    <s:hidden name="v_oid" value="%{entity.oid}"></s:hidden>
    <s:hidden name="v_amount" value="%{entity.amount}"></s:hidden>
    <s:hidden name="v_moneytype" value="%{entity.moneytype}"></s:hidden>
    <s:hidden name="v_url" value="%{entity.url}"></s:hidden>
    
    <!--以下几项项为网上支付完成后，随支付反馈信息一同传给信息接收页 -->
    <s:hidden name="v_remark1" value="%{entity.remark1}"></s:hidden>
    <s:hidden name="v_remark2" value="%{entity.remark2}"></s:hidden>
    
    <!--以下几项只是用来记录客户信息，可以不用，不影响支付 -->
    <s:hidden name="v_rcvname" value="%{entity.rcvname}"></s:hidden>
    <s:hidden name="v_rcvaddr" value="%{entity.rcvaddr}"></s:hidden>
    <s:hidden name="v_rcvtel" value="%{entity.rcvtel}"></s:hidden>
    <s:hidden name="v_rcvpost" value="%{entity.rcvpost}"></s:hidden>
    <s:hidden name="v_rcvemail" value="%{entity.rcvemail}"></s:hidden>
    <s:hidden name="v_rcvmobile" value="%{entiy.rcvmobile}"></s:hidden>
    
    <s:hidden name="v_ordername" value="%{entiy.ordername}"></s:hidden>
    <s:hidden name="v_orderaddr" value="%{entiy.orderaddr}"></s:hidden>
    <s:hidden name="v_ordertel" value="%{entiy.ordertel}"></s:hidden>
    <s:hidden name="v_orderpost" value="%{entiy.orderpost}"></s:hidden>
    <s:hidden name="v_orderemail" value="%{entiy.orderemail}"></s:hidden>
    <s:hidden name="v_ordermobile" value="%{entiy.ordermobile}"></s:hidden>
			
			
		<table style="width: 100%" border="0" align="center" cellpadding="3"
			cellspacing="0">
			<tr>
				<td width="30%"><STRONG>订单搜索</STRONG></td>
				<td width="70%" align="right">&nbsp;</td>
			</tr>
			<tr>
				<td align="right">付款人姓名：&nbsp;</td>
				<td><s:property value="#session.payer.payerName" /></td>
			</tr>
			<tr>
				<td align="right">联系电话：&nbsp;</td>
				<td><s:property value="#session.payer.payerPhone" /></td>
			</tr>
			<tr>
				<td align="right">旅游线路：&nbsp;</td>
				<td><s:property value="#session.payer.tourRoute" /></td>
			</tr>
			<tr>
				<td align="right">金额：&nbsp;</td>
				<td><s:property value="#session.payer.payAmount" /></td>
			</tr>
			<tr>
				<td align="right">备注：&nbsp;</td>
				<td><s:textarea name="#session.payer.remark" rows="10"
					cols="30" disabled="true"></s:textarea></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><s:submit value="网上支付" method="submit" theme="simple"></s:submit>&nbsp;</td>
			</tr>
		</table>
		</form>
		</td>
	</tr>
</table>
</body>
</html>


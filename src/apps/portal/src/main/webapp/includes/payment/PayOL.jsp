<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
<table width="760" border="0" align="center" cellpadding="0"
	cellspacing="1">
	<tr>
		<td valign="top" width="172" height="25">
		<!-- 左侧栏目 -->
		<script type="text/javascript">
		function check(){
		 var a=document.all.Amount.value;
		if(isNaN(a)){
		alert("请输入有效数字");
		return false;
				}
		return true;
		}
		</script>

		<!-- 左侧结束 --></td>
		<td valign="top" width="584">
		<s:form namespace="/" action="affirmPay" method="post" theme="simple" onsubmit="return check()" id="pay">
			<table width="100%" border="0" align="center" cellpadding="3" cellspacing="0">

				<tr>
					<td width="30%"><STRONG>订单搜索</STRONG></td>
					<td width="70%" align="right">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2"><font color="red"><s:fielderror/></font></td>
				</tr>
				<tr>
					<td align="right">付款人姓名：&nbsp;</td>
					<td><s:textfield name="tblUserPayOl.payerName"></s:textfield></td>
				</tr>
				<tr>
					<td align="right">联系电话：&nbsp;</td>
					<td><s:textfield name="tblUserPayOl.payerPhone"></s:textfield>
					</td>
				</tr>
				<tr>
					<td align="right">旅游线路：&nbsp;</td>
					<td><s:textfield name="tblUserPayOl.tourRoute"></s:textfield>
					</td>
				</tr>
				<tr>
					<td align="right">金额：&nbsp;</td>
					<td><s:textfield name="tblUserPayOl.payAmount" id="Amount"></s:textfield>
					</td>
				</tr>
				<tr>
					<td align="right">备注：&nbsp;</td>
					<td><s:textarea name="tblUserPayOl.remark" rows="10" cols="30"></s:textarea></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><s:submit value="提交"></s:submit>&nbsp;</td>
				</tr>
			</table>
		</s:form></td>
	</tr>
</table>
</body>
</html>


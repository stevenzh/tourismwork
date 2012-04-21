<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>联系人一览表</title>
<meta name="menu" content="AccountMenu" />
<meta name="heading" content="联系人一览表">
</head>
<body>

<script type="text/javascript">
	function SubmitForm(param, target) {
		var fm = document.contactActionSearch;
		fm.contactId.value = target;
		if (param == 'delete') {
			if (confirm("是否真的删除?") == false) {
				return;
			}
			fm.action = "__tag_26$17_"
		}
		if (param == 'modify') {
			fm.action = "__tag_30$17_"
		}
		fm.submit();
	}
</script>

<s:form action="contactActionSearch" namespace="/crm" method="POST"
	theme="simple">
	<s:hidden name="contactId"></s:hidden>
	<table>
		<tr>
			<td class="idx">姓名：</td>
			<td><s:textfield name="kenName"></s:textfield></td>
			<td class="idx">手机：</td>
			<td><s:textfield name="kenMobile"></s:textfield></td>
		</tr>
		<tr>
			<td class="idx">团队:</td>
			<td>
			  <s:select name="kenTeamId"
			            list="teamList"
			            listKey="teamId"
				          listValue="name"
				          headerKey=""
				          headerValue="所有">
			  </s:select>
			</td>
		</tr>
		<tr>
			<td><s:submit value="%{getText('common.forms.search')}"></s:submit></td>
			<td><s:submit value="%{getText('common.forms.add')}" action="contactActionAddInput" /></td>
		</tr>
	</table>

	<table style="width: 100%">
		<tr>
			<td class="lstidx">NO.</td>
			<td class="lstidx">联系人</td>
			<td class="lstidx">email</td>
			<td class="lstidx">电话</td>
			<td class="lstidx">传真</td>
			<td class="lstidx">MSN</td>
			<td class="lstidx">操作</td>
		</tr>

		<s:if test="contactList!=null">
			<s:iterator value="contactList">
				<tr>
					<td class="data"><s:property value="contactId" /></td>
					<td class="data"><s:property value="name" /></td>
					<td class="data"><s:property value="email" /></td>
					<td class="data"><s:property value="phone" /></td>
					<td class="data"><s:property value="fax" /></td>
					<td class="data"><s:property value="msn" /></td>
					<td class="data">
					  <a href="#" onClick="javascript:SubmitForm('modify','<s:property value="customerId" />');">修改</a>&nbsp;&nbsp;
					  <a href="#" onClick="javascript:SubmitForm('delete','<s:property value="customerId" />');">删除</a></td>
				</tr>
			</s:iterator>
		</s:if>
	</table>

</s:form>
</body>
</html>

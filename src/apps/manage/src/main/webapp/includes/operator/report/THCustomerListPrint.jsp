<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>同行名单表打印</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="同行名单表打印">
</head>
<script type="text/javascript">
	function printTHList(param){
		document.getElementById('paramid').value = param;
		var fm = document.fopReport;
		fm.submit();
	}
</script>
<body>
<s:form namespace="/operator" method="post" theme="simple">
	<s:hidden name="recordNo"></s:hidden>

	<table border="0" style="width: 100%">
		<tr>
			<td>团号：</td>
			<td><s:property value="tour.tourNo" /></td>
		</tr>
		<tr>
			<td>线路：</td>
			<td><s:property value="tour.line.lineName" /></td>
		</tr>
		<tr>
			<td>出发日期：</td>
			<td><s:property value="tour.outDate" /></td>
		</tr>
	</table>
	<br>

	<table border="0" style="width: 100%">
	<tr><td>
	<input type="button" value="打印" onclick="javascript:printTHList('<s:property value='tour.tourNo' />')" />
	</td></tr>
	</table>
</s:form>

<s:form action="fopReport" namespace="/" method="POST" theme="simple">
  <s:hidden name="parameters(0).name" value="TOUR_NO"></s:hidden>
  <s:hidden id="paramid" name="parameters(0).data"></s:hidden>
  <s:hidden name="reportId" value="6"></s:hidden>
</s:form>
</body>
</html>

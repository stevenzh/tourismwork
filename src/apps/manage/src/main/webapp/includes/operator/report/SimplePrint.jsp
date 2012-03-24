<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>简单打印</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="简单打印">
</head>

<body>
<script type="text/javascript">
function printRoute(param)
{
  var fm = document.fopReport;
  document.getElementById('paramid').value = param;
  document.getElementById('simp').value = document.getElementById('simpleprint1').value;
  fm.submit();
}

function generateXLS(param)
{
  var fm = document.jxlsReport;
  document.getElementById('paramid1').value = param;
  document.getElementById('paramid2').value = document.getElementById('simpleprint1').value;
  fm.submit();
}
</script>
<s:form namespace="/operator" method="post"	theme="simple">
	<s:hidden name="recordNo"></s:hidden>

	<table border="0" cellpadding="0" width="100%">
		<tr>
			<td>团号：</td>
			<td><s:property value="tour.tourNo" /></td>
		</tr>
		<tr>
			<td>线路：</td>
			<td><s:property value="tour.line.lineName" /></td>
		</tr>
		<tr>
			<td>出境口岸：</td>
			<td><s:property value="tour.outCity" /></td>
		</tr>
		<tr>
			<td>入境口岸：</td>
			<td><s:property value="tour.inCity" /></td>
		</tr>
		<tr>
			<td>出境日期：</td>
			<td><s:property value="tour.outDate" /></td>
		</tr>
		<tr>
			<td>入境日期：</td>
			<td><s:property value="tour.inDate" /></td>
		</tr>
		<tr>
			<td>总人数：</td>
			<td colspan="3"><s:property value="tour.pax" />人&nbsp;[男:<s:property
				value="tour.malePax" />人、 女:<s:property value="tour.famalePax" />人、领队:<s:property
				value="tour.leadPax" />人]</td>
		</tr>
		<tr>
			<td></td>
			<td align="left">
				<s:textarea id="simpleprint1" name="sp" cols="50" rows="6">
				</s:textarea>
			</td>
		</tr>
	</table>
	<br>

	<table border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td>
			<input type="button" value="打印" onclick="javascript:printRoute('<s:property value='tour.tourNo' />')" />
		</td>
		<td>
			<input type="button" value="生成EXCEL" onclick="javascript:generateXLS('<s:property value='tour.tourNo' />')" />
		</td>
	</tr>
	</table>
</s:form>

<s:form action="fopReport" namespace="/" method="POST" theme="simple">
  <s:hidden name="parameters(0).name" value="TOUR_NO"></s:hidden>
  <s:hidden name="parameters(0).data" id="paramid" ></s:hidden>
  <s:hidden name="parameters(1).name" value="SIMPLE_PRINT"></s:hidden>
  <s:hidden name="parameters(1).data" id="simp"></s:hidden>
  <s:hidden name="reportId" value="3"></s:hidden>
</s:form>
<s:form action="jxlsReport" namespace="/" method="POST" theme="simple">
  <s:hidden name="parameters(0).name" value="TOUR_NO"></s:hidden>
  <s:hidden id="paramid1" name="parameters(0).data"></s:hidden>
  <s:hidden name="parameters(1).name" value="SIMPLE_PRINT"></s:hidden>
  <s:hidden id="paramid2" name="parameters(1).data"></s:hidden>
  
  <s:hidden name="reportId" value="15"></s:hidden>
</s:form>
</body>
</html>

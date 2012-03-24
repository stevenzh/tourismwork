<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>打印客人名单</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="修改客人信息">
</head>

<body>
<script type="text/javascript">

function printCustomerList(param)
{
  var fm = document.fopReport;
  document.getElementById('paramid').value = param;
  document.getElementById('title').value = document.getElementById('title1').value;
  document.getElementById('remark').value = document.getElementById('remark1').value;
  document.getElementById('receive').value = document.getElementById('receive1').value;
  document.getElementById('send').value = document.getElementById('send1').value;
  fm.submit();
}

function generateXLS(param)
{
  var fm = document.jxlsReport;
  document.getElementById('paramid').value = param;
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
			<td>领队：</td>
			<td><s:property value="" /></td>
		</tr>
		<tr>
			<td>收件人：</td>
			<td><s:textfield id="receive1" name="receive1"/></td>
			<td>发件人：</td>
			<td><s:textfield id="send1" name="send1"/></td>
		</tr>
		<tr>
			<td>页头说明：</td>
			<td><s:textarea id="title1" name="title1" cols="50" rows="6"/></td>
		</tr>
		<tr>
			<td>细目：</td>
			<td>
			<s:checkbox name="" value="">姓名</s:checkbox>|
			<s:checkbox name="" value="">拼音</s:checkbox>|
			<s:checkbox name="" value="">性别</s:checkbox>|
			<s:checkbox name="" value="">出生日期</s:checkbox>|
			<s:checkbox name="" value="">出生地</s:checkbox><br>
			<s:checkbox name="" value="">护照种类</s:checkbox>|
			<s:checkbox name="" value="">护照号</s:checkbox>|
			<s:checkbox name="" value="">签发日期</s:checkbox>|
			<s:checkbox name="" value="">签发地</s:checkbox>|
			<s:checkbox name="" value="">备注</s:checkbox>
			</td>
		</tr>
		<tr>
			<td>备注说明：</td>
			<td align="left">
				<s:textarea id="remark1" name="remark1" cols="50" rows="6"></s:textarea>
			</td>
		</tr>
	</table>
	<br>

	<table border="0" cellpadding="2" cellspacing="2" width="100%">
	  <tr>
	    <td>
	      <input type="button" value="生成PDF后打印" onclick="javascript:printCustomerList('<s:property value='tour.tourNo' />')" />
	    </td>
	    <td>
	      <input type="button" value="生成EXCEL" onclick="javascript:generateXLS('<s:property value='tour.tourNo' />')" />
	    </td>
	  </tr>
	  
	</table>
</s:form>

<s:form action="fopReport" namespace="/" method="POST" theme="simple">
  <s:hidden name="parameters(0).name" value="TOUR_NO"></s:hidden>
  <s:hidden id="paramid" name="parameters(0).data"></s:hidden>
  <s:hidden name="parameters(1).name" value="TITLE"></s:hidden>
  <s:hidden id="title" name="parameters(1).data"></s:hidden>
  <s:hidden name="parameters(2).name" value="REMARK"></s:hidden>
  <s:hidden id="remark" name="parameters(2).data"></s:hidden>
  <s:hidden name="parameters(3).name" value="RECEIVE"></s:hidden>
  <s:hidden id="receive" name="parameters(3).data"></s:hidden>
  <s:hidden name="parameters(4).name" value="SEND"></s:hidden>
  <s:hidden id="send" name="parameters(4).data"></s:hidden>
  
  <s:hidden name="reportId" value="4"></s:hidden>
</s:form>


<s:form action="jxlsReport" namespace="/" method="POST" theme="simple">
  <s:hidden name="parameters(0).name" value="TOUR_NO"></s:hidden>
  <s:hidden id="paramid" name="parameters(0).data"></s:hidden>
  <s:hidden name="reportId" value="14"></s:hidden>
</s:form>
</body>
</html>

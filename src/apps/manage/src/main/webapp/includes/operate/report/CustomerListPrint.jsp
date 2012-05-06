<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>打印客人名单</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="打印客人名单">
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
  document.getElementById('filetitle').value = document.getElementById('filetitle1').value;
  
  if (document.getElementById('printname1').checked == true)
    document.getElementById('printname').value = "true";
  else 
    document.getElementById('printname').value = "false";
    
  if (document.getElementById('printpinyin1').checked == true)
    document.getElementById('printpinyin').value = "true";
  else 
    document.getElementById('printpinyin').value = "false";
  
  if (document.getElementById('printsex1').checked == true)
    document.getElementById('printsex').value = "true";
  else 
    document.getElementById('printsex').value = "false";
    
  if (document.getElementById('printage1').checked == true)
    document.getElementById('printage').value = "true";
  else 
    document.getElementById('printage').value = "false";
    
  if (document.getElementById('printagent1').checked == true)
    document.getElementById('printagent').value = "true";
  else 
    document.getElementById('printagent').value = "false";
    
  if (document.getElementById('printidcard1').checked == true)
    document.getElementById('printidcard').value = "true";
  else 
    document.getElementById('printidcard').value = "false";
  
  if (document.getElementById('printPassportType1').checked == true)
    document.getElementById('printPassportType').value = "true";
  else 
    document.getElementById('printPassportType').value = "false";
    
  if (document.getElementById('printPassportNo1').checked == true)
    document.getElementById('printPassportNo').value = "true";
  else 
    document.getElementById('printPassportNo').value = "false";
    
  if (document.getElementById('printPassportDate1').checked == true)
    document.getElementById('printPassportDate').value = "true";
  else 
    document.getElementById('printPassportDate').value = "false";

  if (document.getElementById('printPassportExpiry1').checked == true)
    document.getElementById('printPassportExpiry').value = "true";
  else 
    document.getElementById('printPassportDate').value = "false";

  if (document.getElementById('printPassportPla1').checked == true)
    document.getElementById('printPassportPla').value = "true";
  else 
    document.getElementById('printPassportPla').value = "false";

  if (document.getElementById('printPassportAnnotation1').checked == true)
    document.getElementById('printPassportAnnotation').value = "true";
  else 
    document.getElementById('printPassportAnnotation').value = "false";
  
    
  fm.submit();
}

function generateXLS(param)
{
  var fm = document.jxlsReport;
  document.getElementById('paramid1').value = param;
  fm.submit();
}
</script>

<s:form namespace="/operator" method="post"	theme="simple">
	<s:hidden name="recordNo"></s:hidden>

	<table border="0" style="width: 100%">
		<tr>
			<td>团号：</td>
			<td><s:property value="tour.tourNo" /></td>
		</tr>
		<tr>
			<td>线路：</td>
			<td><s:property value="tour.line.lineName" />&nbsp;&nbsp;&nbsp;领队：<s:property value="tour.leadPax" /></td>
			<td></td>
		</tr>
		<tr>
			<td>收件人:</td>
			<td><s:textfield id="receive1" name="receive1"/>发件人:<s:textfield id="send1" name="send1"/></td>
		</tr>
		<tr>
			<td>文件标题：</td>
			<td><s:textfield id="filetitle1" name="filetitle1"/></td>
		</tr>
		<tr>
			<td>页头说明：</td>
			<td><s:textarea id="title1" name="title1" cols="50" rows="6"/></td>
		</tr>
		<tr>
			<td>细目：</td>
			<td>
			姓名<input type="checkbox" id="printname1" name="printname1" checked="checked"/>|
			拼音<input type="checkbox" id="printpinyin1" name="printpinyin1" checked="checked"/>|
			性别<input type="checkbox" id="printsex1" name="printsex1" checked="checked"/>|
			年龄<input type="checkbox" id="printage1" name="printage1" checked="checked"/>|
			代理商<input type="checkbox" id="printagent1" name="printagent1" checked="checked"/>|
			身份证<input type="checkbox" id="printidcard1" name="printidcard1" checked="checked"/><br>
			护照种类<input type="checkbox" id="printPassportType1" name="printPassportType1" checked="checked"/>|
			护照号<input type="checkbox" id="printPassportNo1" name="printPassportNo1" checked="checked"/>|
			签发日期<input type="checkbox" id="printPassportDate1" name="printPassportDate1" checked="checked"/>|
              护照有效期<input type="checkbox" id="printPassportExpiry1" name="printPassportExpiry1"/>|
			签发地<input type="checkbox" id="printPassportPla1" name="printPassportPla1" checked="checked"/><br>
			护照说明<input type="checkbox" id="printPassportAnnotation1" name="printPassportAnnotation1"/>
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

	<table border="0" style="width: 100%">
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
  <s:hidden name="parameters(5).name" value="FILETITLE"></s:hidden>
  <s:hidden id="filetitle" name="parameters(5).data"></s:hidden>
  
  <s:hidden name="parameters(6).name" value="PRINTNAME"></s:hidden>
  <s:hidden id="printname" name="parameters(6).data"></s:hidden>
  <s:hidden name="parameters(7).name" value="PRINTPINYIN"></s:hidden>
  <s:hidden id="printpinyin" name="parameters(7).data"></s:hidden>
  <s:hidden name="parameters(8).name" value="PRINTSEX"></s:hidden>
  <s:hidden id="printsex" name="parameters(8).data"></s:hidden>
  <s:hidden name="parameters(9).name" value="PRINTAGE"></s:hidden>
  <s:hidden id="printage" name="parameters(9).data"></s:hidden>
  <s:hidden name="parameters(10).name" value="PRINTAGENT"></s:hidden>
  <s:hidden id="printagent" name="parameters(10).data"></s:hidden>
  <s:hidden name="parameters(11).name" value="PRINTIDCARD"></s:hidden>
  <s:hidden id="printidcard" name="parameters(11).data"></s:hidden>
  
  <s:hidden name="parameters(12).name" value="PRINTPASSPORTTYPE"></s:hidden>
  <s:hidden id="printPassportType" name="parameters(12).data"></s:hidden>
  <s:hidden name="parameters(13).name" value="PRINTPASSPORTNO"></s:hidden>
  <s:hidden id="printPassportNo" name="parameters(13).data"></s:hidden>
  <s:hidden name="parameters(14).name" value="PRINTPASSPORTDATE"></s:hidden>
  <s:hidden id="printPassportDate" name="parameters(14).data"></s:hidden>
  <s:hidden name="parameters(15).name" value="PRINTPASSPORTPLA"></s:hidden>
  <s:hidden id="printPassportPla" name="parameters(15).data"></s:hidden>
  <s:hidden name="parameters(16).name" value="PRINTPASSPORTANNOTATION"></s:hidden>
  <s:hidden id="printPassportAnnotation" name="parameters(16).data"></s:hidden>

  <s:hidden name="parameters(17).name" value="PRINTPASSPORTEXPIRY"></s:hidden>
  <s:hidden id="printPassportExpiry" name="parameters(17).data"></s:hidden>
  
  <s:hidden name="reportId" value="4"></s:hidden>
</s:form>

<s:form action="jxlsReport" namespace="/" method="POST" theme="simple">
  <s:hidden name="parameters(0).name" value="TOUR_NO"></s:hidden>
  <s:hidden id="paramid1" name="parameters(0).data"></s:hidden>
  <s:hidden name="reportId" value="14"></s:hidden>
</s:form>
</body>
</html>

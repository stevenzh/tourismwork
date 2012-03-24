<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出境游客人名单表打印</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="港澳游客人名单表打印">
</head>

<body>
<script type="text/javascript">
function printCustomerList()
{
  var fm = document.fopReport;
  var str="";
  if(document.getElementById('tourSerialNumber1').value=="")
    {
      str=str+"组团社序号不能为空！　";
    }
  if(document.getElementById('tourNo1').value=="")
    {
      str=str+"旅游团队编号不能为空！　";
    }
  if(document.getElementById('year1').value=="")
    {
      str=str+"年份不能为空！　";
    }
  if(document.getElementById('routeName1').value=="")
    {
      str=str+"线路不能为空！　";
    }
  if(document.getElementById('localTname1').value=="")
    {
      str=str+"组团社名称不能为空！　";
    }
  if(document.getElementById('localEcontact1').value=="")
    {
      str=str+"本社联络人员姓名及电话不能为空！　";
    }
  if(document.getElementById('receptionTour1').value=="")
    {
      str=str+"接待社名称不能为空！　";
    }
  if(document.getElementById('receptionConnecter1').value=="")
    {
      str=str+"接待社联络人员姓名及电话不能为空！　";
    }
  
  if(str!="")
  {
     alert(str);
     return;
  }
  
  
  if(document.getElementById('leaders').value=="")
    {
      alert("请选择领队!");
      return;
    }
  if(document.getElementById('leaderKey1').checked==true)
    document.getElementById('leaderKey').value="True";
  else
    document.getElementById('leaderKey').value="False";
  
  
  if(document.getElementById('outInKey1').checked==true)
    document.getElementById('outInKey').value = "True";
  else 
    document.getElementById('outInKey').value = "False";
  
  
  document.getElementById('tourNo').value = document.getElementById('tourNo1').value;
  document.getElementById('year').value = document.getElementById('year1').value;
  document.getElementById('routeName').value = document.getElementById('routeName1').value;
  document.getElementById('localTname').value = document.getElementById('localTname1').value;
  document.getElementById('tourSerialNumber').value = document.getElementById('tourSerialNumber1').value;
  document.getElementById('localEcontact').value = document.getElementById('localEcontact1').value;
  document.getElementById('receptionTour').value = document.getElementById('receptionTour1').value;
  document.getElementById('receptionConnecter').value = document.getElementById('receptionConnecter1').value;
  document.getElementById('leader').value=document.getElementById('leaders').value;
    
  fm.submit();
}
</script>
<s:form namespace="/operator" method="post" theme="simple">
	<table border="0" cellpadding="0" width="100%">
		<tr>
			<td class="idx">组团社序号：</td>
			<td bgcolor="white" colspan="2"><s:textfield id="tourSerialNumber1" name="tour.tourSerialNumber" value="L-SH-GJ00070"/>&nbsp;<font color="red">*</font></td>
		</tr>
		<tr>
			<td class="idx">旅游团队编号：</td>
			<td bgcolor="white" colspan="2">
        <s:textfield id="tourNo1" name="tour.tourNo"/>&nbsp;<font color="red">*</font>
      </td>
		</tr>
		<tr>
			<td class="idx">年份：</td>
			<td bgcolor="white" colspan="2"><s:textfield id="year1" name="tour.year"/>&nbsp;<font color="red">*</font></td>
		</tr>
		<tr>
			<td class="idx">线路：</td>
			<td bgcolor="white" colspan="2"><s:textfield id="routeName1" name="tour.line.lineName"/>&nbsp;<font color="red">*</font></td>
		</tr>
		<tr>
			<td class="idx">总人数：</td>
			<td bgcolor="white" colspan="2"><s:property value="tour.pax" />&nbsp;&nbsp;(其中&nbsp;男：<s:property
				value="tour.malePax" />&nbsp;&nbsp;女：<s:property value="tour.femalePax" />)
             &nbsp;<font color="red">*</font></td>
		</tr>
		<tr>
			<td rowspan="2" class="idx">本社信息</td>
            <td bgcolor="white" width="120">组&nbsp;&nbsp;团&nbsp;&nbsp;社&nbsp;&nbsp;名&nbsp;&nbsp;称：</td>
			<td bgcolor="white"  align="left"><s:textfield id="localTname1" value="旅行社" size="50"/>&nbsp;<font color="red">*</font></td>
		</tr>
        <tr>
          <td bgcolor="white">联络人员姓名及电话：</td>
          <td bgcolor="white"><s:textfield size="50" id="localEcontact1" name="tour.localEcontact"/>&nbsp;<font color="red">*</font></td>
        </tr>
		<tr>
			<td rowspan="2" class="idx">接待社信息</td>
            <td bgcolor="white">接&nbsp;&nbsp;待&nbsp;&nbsp;社&nbsp;&nbsp;名&nbsp;&nbsp;称：</td>
			<td bgcolor="white" align="left"><s:textarea id="receptionTour1" name="rout.receptionTour" cols="50" rows="6"/>&nbsp;<font color="red">*</font></td>
		</tr>
        <tr>
          <td bgcolor="white">联络人员姓名及电话：</td>
          <td bgcolor="white" align="left"><s:textarea id="receptionConnecter1" name="rout.receptionConnecter" cols="50" rows="6"/>&nbsp;<font color="red">*</font></td>
        </tr>
        <tr>
          <td class="idx">出入境日期和口岸</td>
          <td bgcolor="white" colspan="2"><s:checkbox id="outInKey1" name="tour.outInKey"></s:checkbox>取消出入境日期和口岸</td>
        </tr>
        <tr>
          <td class="idx">表头中含领队</td>
          <td bgcolor="white" colspan="2"><s:checkbox id="leaderKey1" name="tour.leaderKey" value="true"></s:checkbox>表头中含领队</td>
        </tr>
        <tr>
          <td class="idx">选择领队</td>
          <td bgcolor="white" colspan="2">
          <s:select id="leaders"
                    name="tour.leader"
                    list="leaders"
                    listKey="value"
                    listValue="label"
                    emptyOption="true">
        </s:select>&nbsp;<font color="red">*</font></td>
        </tr>
	</table>
	<br>

	<table border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr><td>
		<input type="button" value="打印出境名单"
			onclick="javascript:printCustomerList()" />
	</td></tr>
	</table>
</s:form>

<s:form action="fopReport" namespace="/" method="POST" theme="simple">
    <s:hidden name="tourNum"></s:hidden>
	<s:hidden name="parameters(0).name" value="TOUR_NO"></s:hidden>
	<s:hidden id="tourNo" name="parameters(0).data"></s:hidden>
	<s:hidden name="parameters(1).name" value="YEAR"></s:hidden>
	<s:hidden id="year" name="parameters(1).data"></s:hidden>
	<s:hidden name="parameters(2).name" value="ROUTENAME"></s:hidden>
	<s:hidden id="routeName" name="parameters(2).data"></s:hidden>
	<s:hidden name="parameters(3).name" value="LOCALTNAME"></s:hidden>
	<s:hidden id="localTname" name="parameters(3).data"></s:hidden>
    <s:hidden name="parameters(4).name" value="TOURSERIALNUMBER"></s:hidden>
    <s:hidden id="tourSerialNumber" name="parameters(4).data"></s:hidden>
    <s:hidden name="parameters(5).name" value="LOCALECONTACT"></s:hidden>
    <s:hidden id="localEcontact" name="parameters(5).data"></s:hidden>
    <s:hidden name="parameters(6).name" value="RECEPTIONTOUR"></s:hidden>
    <s:hidden id="receptionTour" name="parameters(6).data"></s:hidden>
    <s:hidden name="parameters(7).name" value="RECEPTIONCONNECTER"></s:hidden>
    <s:hidden id="receptionConnecter" name="parameters(7).data"></s:hidden>
    <s:hidden name="parameters(8).name" value="PAX"></s:hidden>
    <s:hidden id="pax" name="parameters(8).data" value="%{tour.pax}"></s:hidden>
    <s:hidden name="parameters(9).name" value="MALEPAX"></s:hidden>
    <s:hidden id="malePax" name="parameters(9).data" value="%{tour.malePax}"></s:hidden>
    <s:hidden name="parameters(10).name" value="FEMALEPAX"></s:hidden>
    <s:hidden id="femalePax" name="parameters(10).data" value="%{tour.femalePax}"></s:hidden>
    <s:hidden name="parameters(11).name" value="LEADER"></s:hidden>
    <s:hidden id="leader" name="parameters(11).data" ></s:hidden>
    <s:hidden name="parameters(12).name" value="LEADERKEY"></s:hidden>
    <s:hidden id="leaderKey" name="parameters(12).data" ></s:hidden>
    
    <s:hidden name="parameters(13).name" value="OUTDATE"></s:hidden>
    <s:hidden id="outDate" name="parameters(13).data" value="%{tour.printOutDate}"></s:hidden>
    <s:hidden name="parameters(14).name" value="OUTCITY"></s:hidden>
    <s:hidden id="outCity" name="parameters(14).data" value="%{tour.outCity}" ></s:hidden>
    <s:hidden name="parameters(15).name" value="INDATE"></s:hidden>
    <s:hidden id="inDate" name="parameters(15).data" value="%{tour.printInDate}" ></s:hidden>
    <s:hidden name="parameters(16).name" value="INCITY"></s:hidden>
    <s:hidden id="inCity" name="parameters(16).data" value="%{tour.inCity}" ></s:hidden>
    <s:hidden name="parameters(17).name" value="OUTINKEY"></s:hidden>
    <s:hidden id="outInKey" name="parameters(17).data" ></s:hidden>

	<s:hidden name="reportId" value="7"></s:hidden>
</s:form>
</body>
</html>

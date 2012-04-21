<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>安排领队</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="安排领队">
</head>

<body>
<script language="javascript">
<!--//
function conv()
{
  var fm = document.submitArrangeLeader;
  var field = document.submitArrangeLeader.nameKey;
  var isChecked=-1;
  if(null!=field.length)
 {
   for(var i = 0; i< field.length; i++)
  {
    if(field[i].checked)
    {
      isChecked = i;
      break;
    } 
  }
  }else if(field.checked)
 {
   isChecked = 0;
 }

  if (isChecked == -1)
  {
    alert("请选择您所需要的领队！");
    return;
  }

  if (confirm("您确定选他们做为此团领队吗？") == true)
  {  	
  	fm.submit();  	
  }
}

function dropLeader()
{
  var fm = document.submitArrangeLeader;
  var field = document.submitArrangeLeader.nameKey;
  var isChecked=-1;
 if(null!=field.length)
 {
   for(var i = 0; i< field.length; i++)
  {
    if(field[i].checked)
    {
      isChecked = i;
      break;
    } 
  }
  }else if(field.checked)
 {
   isChecked = 0;
 }

  if (isChecked == -1)
  {
    alert("请选择您所需要取消的领队！");
    return;
  }

  if (confirm("您确定要取消他们做为本团领队吗？") == true)
  { 
    fm.action = "<s:url action='CancelLeader' namespace='/operator'/>"
  	fm.submit();  	
  }
}

function showCustomer(target)
{
  var fm = document.CustomerDetail;
  fm.nmno.value=target;
  fm.submit();
}

//-->
</script>

<s:form action="submitArrangeLeader" namespace="/operator" method="POST" theme="simple">
<s:hidden name="tourNo" value="%{tour.tourNo}"></s:hidden>

<table align="center" border="0" bordercolor="#000000" style="width: 100%">
  <tr>
    <td class="idx" width="15%">团号:</td>
    <td colspan="3"><s:property value="tour.tourNo"/></td>
  </tr>
  <tr>
    <td class="idx" width="15%">线路：</td>
    <td colspan="3"><s:property value="tour.line.lineName"/></td>
  </tr>
  <tr>
    <td class="idx">出境日期：</td>
    <td><s:date name="tour.outDate" format="yyyy-MM-dd"/></td>
    <td class="idx" width="15%">出境口岸：</td>
    <td><s:property value="tour.outCity" /></td>
  </tr>
  <tr>
    <td class="idx">入境日期：</td>
    <td><s:date name="tour.inDate" format="yyyy-MM-dd"/></td>
    <td class="idx">入境口岸：</td>
    <td>
    <s:select name="tour.inCity"
              list="portCitys"
              listKey="citycd"
              listValue="citynm"
              emptyOption="true"
              disabled="true">
    </s:select></td>
  </tr>
  <tr>
    <td class="idx">聚合城市</td>
    <td colspan="3">
    <s:select name="tour.venue"
              list="portCitys"
              listKey="citycd"
              listValue="citynm"
              emptyOption="true"
              disabled="true">
    </s:select></td>
  </tr>
  <tr>
    <td class="idx">总人数：</td>
    <td colspan="3"><s:property value="tour.pax"/>人&nbsp;（男:<s:property value="tour.malePax"/>人、
     女:<s:property value="tour.femalePax"/>人、领队:<s:property value="tour.leadPax"/>人）</td>
  </tr>
  <tr>
    <td class="idx">订房数：</td>
    <td colspan="3">
      双人间:<s:property value="tour.doubleRoom" />&nbsp;
      单人间:<s:property value="tour.singleRoom" />&nbsp;
      加床:<s:property value="tour.extraBedRoom" />
    </td>
  </tr>
  <tr>
    <td class="idx">修改人:</td>
    <td><s:property value="tour.opUser"/>&nbsp;</td>
    <td class="idx">修改时间:</td>
    <td><s:date name="tour.opDate"/>&nbsp;</td>
  </tr>

  <tr>
    <td class="idx">备注：</td>
    <td colspan="3"><s:textarea name="tour.remarks" cols="80" rows="4" disabled="true"/></td>
  </tr>

  <tr>
    <td colspan="4">
    <table bordercolor="#000000" border="0" style="width: 100%">
        <tr>
          <td class="lstidx">NO.</td>
          <td class="lstidx">选择</td>
          <td class="lstidx">姓名</td>
          <td class="lstidx">性别</td>
          <td class="lstidx">出生日期</td>
          <td class="lstidx">出生地</td>
          <td class="lstidx">护照种类</td>
          <td class="lstidx">护照号码</td>
          <td class="lstidx">签发日期</td>
          <td class="lstidx">签发地点</td>
          <td class="lstidx">护照说明</td>
          <td class="lstidx">领队</td>
          <td class="lstidx">备注</td>
        </tr>

	    <s:iterator value="tour.customerList" status="rowccount">
        <tr>
          <td class="cdata"><s:property value="#rowccount.count"/></td>
          <td class="cdata"><s:checkbox  name="nameKey"  fieldValue="%{nmno}"/></td>
          <td class="cdata"><a href="#" title="显示客户明细" onclick="javascript:showCustomer('<s:property value="nmno"/>');"><s:property value="userName"/></a>&nbsp;</td>
          <td class="data"><s:property value="sex"/>&nbsp;</td>
          <td class="cdata"><s:date name="birthday" format="yyyy-MM-dd"/>&nbsp;</td>
          <td class="data"><s:property value="birthplaceName"/>&nbsp;</td>
          <td class="data"><s:property value="passportType" />&nbsp;</td>
          <td class="data"><s:property value="passportNo"/>&nbsp;</td>
          <td class="cdata"><s:date name="passportDate" format="yyyy-MM-dd"/>&nbsp;</td>
          <td class="data"><s:property value="passportPlaceName"/>&nbsp;</td>
          <td class="data"><s:date name="passportAnnotation"/>&nbsp;</td>
          <td class="data"><s:property value="leaderKey"/>&nbsp;</td>
          <td class="data"><s:property value="remarks"/>&nbsp;</td>
        </tr>
        </s:iterator>
    </table>
    </td>
  </tr>
  <tr>
    <td colspan="4" align="right">
    <input type="button" value="取消该领队" onclick="javascript:dropLeader();">
    <input type="button" value="客人转为领队" onclick="javascript:conv();">
    <s:submit action="chooseLeader" value="选择领队"></s:submit> 
  </td>
  </tr>
</table>
</s:form>

<s:form action="CustomerDetail" namespace="/operator" method="post" theme="simple">
  <s:hidden name="nmno"></s:hidden>
</s:form>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品计划列表</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="产品计划列表">
</head>

<body>
<script language="JavaScript">
<!--//

function SubmitForm(param, target)
{
  var fm = document.showRoutePlan;
  fm.recordNo.value = target;

  if (param =='delete') {
    if(confirm('确定删除吗？') == false){
      return;
    }
      var note =window.prompt("备注","请认真填写删除原因");
      fm.note.value=note;
      fm.action = "<s:url action='deletePlan' namespace='/product'/>"
  } else if (param == 'change') {
    fm.action = "<s:url action='editPlan' namespace='/product' />"
  }
  fm.submit();
}

function _getlist(type)
{
  var frm = document.showRoutePlan;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}

function showAdd(){
	var fm = document.showRoutePlan;
	fm.action = "<s:url action='showPlanAdd' namespace='/product'/>"
	fm.submit();
}
 
//-->
</script>

<s:form action="showRoutePlan" namespace="/product" method="post" theme="simple">
<s:hidden name="recordNo"></s:hidden>
<s:hidden name="note"></s:hidden>

<table border="0" cellpadding="2" cellspacing="2" width="100%">
  <tr>
    <td class="lstidx">NO.</td>
    <td class="lstidx">&nbsp;</td>
    <td class="lstidx">团号</td>
    <td class="lstidx">工作组(操作)</td>
    <td class="lstidx">操作员</td>
    <td class="lstidx">出发日期</td>
    <td class="lstidx">同业价</td>
    <td class="lstidx">直客价</td>
    <td class="lstidx">单人房差</td>
    <td class="lstidx">可收</td>
    <td class="lstidx">已收</td>
    <td class="lstidx">剩余</td>
    <td class="lstidx">占位</td>
    <td class="lstidx">最小成团</td>
    <td class="lstidx">出发地</td>
    <td class="lstidx">操作</td>
  </tr>

  <s:iterator value="plans" status="rowcounter">
    <s:if test="#rowcounter.count > fromRecord">
    <s:if test="#rowcounter.count <= toRecord">
    <tr>
      <td class="cdata"><s:property value="#rowcounter.count"/></td>
      <td class="cdata"><s:if test='isBuildup eq "Y"'><font color="green">★</font></s:if></td>
      <td class="data"><a href="javascript:SubmitForm('change','<s:property value='recordNo'/>');"><s:property value="tourNo"/></a></td>
      <td class="cdata"><s:property value="team.name"/></td>
      <td class="cdata"><s:property value="assigned.userName"/></td>
      <td class="cdata"><strong><s:date name="outDate" format="yyyy-MM-dd"/></strong></td>
      <td class="rdata"><s:property value="packagePrice.priceOther"/>&nbsp;</td>
      <td class="rdata"><s:property value="packagePrice.price"/>&nbsp;</td>
      <td class="rdata"><s:property value="packagePrice.priceContrast"/>&nbsp;</td>
      <td class="rdata"><s:property value="pax1"/>&nbsp;</td>
      <td class="rdata"><s:property value="pax2"/>&nbsp;</td>
      <td class="rdata"><s:property value="pax3"/>&nbsp;</td>
      <td class="rdata"><s:property value="pax4"/>&nbsp;</td>
      <td class="rdata"><s:property value="pax5"/>&nbsp;</td>
      <td class="cdata"><s:property value="line.outCity.citynm"/></td>
      <td>
      <a href="javascript:SubmitForm('change','<s:property value='recordNo'/>');">修改</a>
      <a href="javascript:SubmitForm('delete','<s:property value='recordNo'/>');">删除</a>
      </td>
    </tr>
    </s:if>
    </s:if>
  </s:iterator>
</table>

<s:if test="plans.isEmpty() == false">
<%@ include file="/includes/PagerTable.jsp" %>
</s:if>

<s:submit action="showPlanAdd" value="添加计划" />

</s:form>

</body>
</html>

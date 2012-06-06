<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改开班计划</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="修改开班计划">
</head>

<body>
<script language="JavaScript">
<!--
function cancel(){
  var fm = document.TourPlanChangeSubmit;
  fm.action = "<s:url action='TourPlanSearch' namespace='/operator'/>"
  fm.submit();
}

function change()
{
  var pax1 = document.getElementById("plan_pax1");
  var pax1_value = new Number(pax1.value);
  var pax2_value = new Number(document.getElementById("plan_pax2").value);
  var pax3 = document.getElementById("plan_pax3");
  var pax3_value = new Number(pax3.value);
  var pax4 = document.getElementById("plan_pax4");
  var pax4_value = new Number(pax4.value);
  
  if (pax4_value < 0)
  {
    pax4_value = 0;
    pax4.value = 0;
  }

  if (pax1_value < pax4_value)
  {
    pax1.value = pax4_value;
  }
  
  pax3.value = pax1_value - pax2_value - pax4_value;
}

function changePax5()
{
  var pax5 = document.getElementById("plan_pax5");
  var pax5_value = pax5.value;
  
  if (pax5_value < 1)
  {
    pax5.value = 1;
  }
}

function SubmitDeleteFlight(param, target)
{
  var fm = document.TourPlanChangeSubmit;
  fm.idx.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='TourPlanDelFlight' namespace='/product'/>"
  }
  fm.submit();
}


function isRecount(param)
{
    var isReCount = document.getElementById(param);
    isReCount.value=1;
}

//-->
</script>
<s:form action="TourPlanChangeSubmit" method="POST" namespace="/operator" theme="simple">
  <s:hidden name="kenRrouteName"></s:hidden>
  <s:hidden name="kenDepartment"></s:hidden>
  <s:hidden name="kenPrincipal"></s:hidden>
  <s:hidden name="kenStartDate"></s:hidden>
  <s:hidden name="kenEndDate"></s:hidden>

  <s:hidden name="plan.opDate"></s:hidden>
  <s:hidden name="plan.recordNo"></s:hidden>
  <s:hidden name="plan.outDate"></s:hidden>
  <s:hidden name="recordNo" value="%{plan.recordNo}"></s:hidden>
  <s:hidden name="plan.line.lineNo"></s:hidden>

  <table cellpadding="2">
    <tr>
      <td class="idx">线路名</td>
      <td><s:property value="plan.line.lineName" /></td>
    </tr>
    <tr>
      <td class="idx">出发日期</td>
      <td><s:date name="plan.outDate" format="yyyy-MM-dd" /></td>
    </tr>
    <tr>
      <td class="idx">旅游团队编号（团号）<span class="required">*</span>：</td>
      <td class="data"><s:textfield name="plan.tourNo" size="80" maxlength="100">
      </s:textfield>
      </td>
    </tr>
    <tr>
      <td class="idx">旅游团队名称</td>
      <td class="data"><s:textfield name="plan.tourNm" size="80" maxlength="100">
      </s:textfield>
      </td>
    </tr>
    <tr>
      <td class="idx">工作组(操作)</td>
      <td class="data">
        <s:select id="productTeamId"
                  name="plan.team.teamId"
                  list="teamList"
                  listKey="teamId"
                  listValue="name"
                  required="true"
                  requiredposition="right"
                  onchange="javascript:change();">
        </s:select>
      </td>
      <td class="idx">操作员</td>
      <td class="data">
        <s:select id="userId"
                  name="plan.assigned.userId"
                  list="employeeList"
                  listKey="userId"
                  listValue="userName"
                  required="true"
                  requiredposition="right">
        </s:select>
      </td>
    </tr>
    <tr>
      <td class="idx">可收<span class="required">*</span>：</td>
      <td class="data">
      <s:textfield id="plan_pax1" name="plan.planPax" size="5" maxlength="5" onchange="javascript:change();">
      </s:textfield>
      </td>
    </tr>
    <tr>
      <td class="idx">已收：</td>
      <td class="data">
      <s:textfield id="plan_pax2" name="plan.pax2" size="5" maxlength="5" readonly="true">
      </s:textfield>
       &nbsp;&nbsp;&nbsp;&nbsp;
       <s:checkbox name="ReCount" onclick="javascript:isRecount('isReCount');"></s:checkbox>是否重新计算人数
       <s:hidden id="isReCount" name="isReCount"></s:hidden>
      </td>
    </tr>
    <tr>
      <td class="idx">可用名额</td>
      <td class="data">
      <s:textfield id="plan_pax3" name="plan.pax3" size="5" maxlength="5" readonly="true">
      </s:textfield>
      </td>
    </tr>
    <tr>
      <td class="idx">占位<span class="required">*</span>：</td>
      <td class="data">
      <s:textfield id="plan_pax4" name="plan.holdPax" size="5" maxlength="5" onchange="javascript:change();">
      </s:textfield>
      </td>
    </tr>

    <tr>
      <td class="idx">最小成团人数<span class="required">*</span>：</td>
      <td class="data">
      <s:textfield id="plan_pax5" name="plan.buildMinPax" size="5" maxlength="5"
                   onchange="javascript:changePax5();">
      </s:textfield>
      </td>
    </tr>

    <tr>
      <td class="idx">报名截止日期<span class="required">*</span>：</td>
      <td class="data">
      <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="plan.deadline">
      </sj:datepicker>
      </td>
    </tr>
    <tr>
      <td class="idx">是否公开</td>
      <td class="data">
      <s:radio name="plan.deployFlag" list="deploys" listKey="value" listValue="label">
      </s:radio>
      </td>
    </tr>
    <tr>
    	<td class="idx">计划性质</td>
    	<td class="data">
    	<s:radio name="plan.singleFlag" list="singleFlag" listKey="value" listValue="label"/>
    	</td>
    </tr>
    <tr>
      <td class="idx"><span class="required">推荐方式 *</span>:</td>
      <td>
        <s:select name="plan.traitId"
                  list="traitList"
                  listKey="label"
                  listValue="value"
                  headerKey="0"
                  headerValue="普通">
        </s:select>
      </td>
    </tr>
   <!--   <tr>
      <td class="idx">特惠线路</td>
      <td class="data">
        <s:radio name="plan.favourable"
                 list="favourables"
                 listKey="value"
                 listValue="label">
        </s:radio>
      </td>
    </tr> -->
    <tr>
      <td class="idx">控制名额否</td>
      <td class="data">
      <s:radio list="paxkey" name="plan.paxkey" listKey="value" listValue="label">
      </s:radio>
      </td>
    </tr>

    <tr>
      <td class="idx">备注：</td>
      <td class="data"><s:textfield name="plan.remarks" maxLength="60" size="60">
      </s:textfield>
      </td>
    </tr>
    <tr>
      <td class="idx">弹出提醒：</td>
      <td class="data"><s:textarea name="plan.message" cols="50" rows="10">
      </s:textarea></td>
    </tr>
  </table>

  <table cellspacing="2">
    <tr>
      <td colspan="8" class="header">选择线路报价</td>
    </tr>
    <tr>
      <td class="lstidx">选择</td>
      <td class="lstidx">默认</td>
      <td class="lstidx">描述</td>
      <td class="lstidx">同行价</td>
      <td class="lstidx">直客价</td>
      <td class="lstidx">成本价</td>
      <td class="lstidx">单人房差</td>
      <td class="lstidx">有效期</td>
    </tr>
    <s:if test="priceList.size > 0">
    <s:iterator value="priceList" status="rowcounter">
      <tr>
        <td class="cdata">
          <s:checkbox name="select"
                      fieldValue="%{recNo}"
                      value="%{select}">
          </s:checkbox>
        </td>
        <td class="cdata">
          <s:if test="defaultPrice == true">
          <input name="plan.packagePrice.recNo"
                 type="radio"
                 value="<s:property value="recNo"/>"
                 checked="checked">
           </s:if>
           <s:else>
          <input name="plan.packagePrice.recNo"
                 type="radio"
                 value="<s:property value="recNo"/>">
           </s:else>
        </td>
        <td class="cdata"><s:property value="type" /></td>
        <td class="cdata"><s:property value="priceOther" /></td>
        <td class="cdata"><s:property value="price" /></td>
        <td class="cdata"><s:property value="priceCost" /></td>
        <td class="cdata"><s:property value="priceContrast" /></td>
        <td class="cdata"><s:date name="startDate" format="yyyy-MM-dd"/>至<s:date name="endDate" format="yyyy-MM-dd"/></td>
      </tr>
    </s:iterator>
   </s:if>
   <s:else>当前日期下暂无报价</s:else>
   <tr>
     <td>&nbsp;</td>
   </tr>
  </table>

  <table border="0" style="width: 100%">
    <tr>
      <td align="center">
        <s:submit value="提 交"></s:submit>
        <input type="button" value="返 回" onclick="javascript:cancel()"></td>
    </tr>
  </table>
</s:form>
</body>
</html>

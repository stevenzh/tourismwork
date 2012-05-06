<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改开班计划</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="修改开班计划">
</head>

<body>
<script language="JavaScript">
<!--//
function cancel(){
  var fm = document.TourPlanDetail;
  fm.action = "<s:url action='TourPlanSearch' namespace='/operator'/>"
  fm.submit();
}

function del(){
  var fm=document.TourPlanDetail;
  fm.action = "<s:url action='TourPlanDelete' namespace='/operator'/>"
  if (confirm("确定要删除此分类吗？") == false) {
      return;}
  fm.submit();
}
//-->
</script>
<table border="0" style="width: 100%">
  <tr>
    <td class="header">修改开班计划 </td>
  </tr>
</table>

<s:form action="TourPlanDetail" method="POST" namespace="/operator" theme="simple">
<s:hidden name="kenRrouteName"></s:hidden>
<s:hidden name="kenDepartment"></s:hidden>
<s:hidden name="kenPrincipal"></s:hidden>
<s:hidden name="kenStartDate"></s:hidden>
<s:hidden name="kenEndDate"></s:hidden>

<s:hidden name="plan.opDate" value="%{plan.opDate}"></s:hidden>
<s:hidden name="plan.recordNo" value="%{plan.recordNo}"></s:hidden>
<s:hidden name="plan.line.lineNo" value="%{plan.line.lineNo}"></s:hidden>
<s:hidden name="plan.outDate" value="%{plan.outDate}"></s:hidden>

  <table cellpadding="2">
    <tr>
      <td class="idx">线路名：</td>
      <td><s:property value="plan.line.lineName"/></td>
    </tr>
    <tr>
      <td class="idx">出发日期：</td>
      <td class="data"><s:date name="plan.outDate" format="yyyy-MM-dd"/>
      </td>
    </tr>
    <tr>
      <td class="idx">团号：</td>
      <td class="data"><s:property value="plan.tourNo"/></td>
    </tr>
    <tr>
      <td class="idx">可收：</td>
      <td class="data"><s:property value="plan.pax1"/></td>
    </tr>
    <tr>
      <td class="idx">已收：</td>
      <td class="data"><s:property value="plan.pax2"/></td>
    </tr>
    <tr>
      <td class="idx">可用名额：</td>
      <td class="data"><s:property value="plan.pax3"/></td>
    </tr>
    <tr>
      <td class="idx">占位：</td>
      <td class="data"><s:property value="plan.pax4"/></td>
    </tr>
    <tr>
      <td class="idx">报名截止日期：</td>
      <td class="data"><s:date name="plan.deadline" format="yyyy-MM-dd"/>
    </tr>
    <tr>
      <td class="idx">网上发布：</td>
      <td class="data">
      <s:radio name="plan.deployFlag"
               value="%{plan.deployFlag}"
               list="deploys"
               listKey="value"
               listValue="label">
      </s:radio>
      </td>
    </tr>
    <tr>
      <td class="idx">特惠线路：</td>
      <td class="data">
      <s:radio name="plan.favourable"
               value="%{plan.favourable}"
               list="favourables"
               listKey="value"
               listValue="label">
      </s:radio>
      </td>
    </tr>
    <tr>
      <td class="idx">航空公司：</td>
      <td class="data">
      <s:textfield name="plan.airlineCompany"
                   value="%{plan.airlineCompany}"
                   size="50"
                   maxlength="50">
      </s:textfield>
      </td>
    </tr>
    <tr>
      <td class="idx">航班号：</td>
      <td class="data">
      <s:textfield name="plan.flightNumber"
                   value="%{plan.flightNumber}"
                   size="50"
                   maxlength="50">
      </s:textfield>
      </td>
    </tr>
    <tr>
      <td class="idx">备注：</td>
      <td class="data"><s:property value="plan.remarks"/></td>
    </tr>
    <tr>
      <td class="idx">弹出提醒：</td>
      <td class="data"><s:property value="plan.message"/></td>
    </tr>
  </table>

  <table border="0" style="width: 100%">
    <tr>
      <td align="center">
      <input type="button" value="删除" onclick="javascript:del()" >
      <input type="button" value="返回" onclick="javascript:cancel()"></td>
    </tr>
  </table>
</s:form>
</body>
</html>

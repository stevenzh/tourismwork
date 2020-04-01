<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<title>计调操作</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="计调操作首页">
</head>
<body>
<script type="text/javascript">
function SubmitForm(param, target)
{
  var fm = document.TourPlanSearch;
  fm.recordNo.value = target;
  if (param =='delete') {
    if(confirm('确定删除吗？') == false){
      return;
    }
    fm.action = "<s:url action='TourPlanDelete' namespace='/operator'/>"
  } else if (param == 'change') {
      fm.action = "<s:url action='TourPlanChange' namespace='/operator' />"
  } else if (param == 'booking') {
      fm.action = "<s:url action='showPlanBooking' namespace='/operator' />"
  }
  fm.submit();
}

function showRouteJourney(obj)
{
    var fm=document.ShowRouteJourney;
    fm.lineNo.value=obj;
    fm.action="<s:url action='ShowRouteJourney' namespace='/product' />";
    fm.submit();  
}

</script>
<sj:tabbedpanel id="opTourTab"> 
  <sj:tab href="/operator/operatorRunPlan.jspa" label="最近出团计划"></sj:tab>
</sj:tabbedpanel>
<br />
<s:url id="operatorTask" namespace="/operator" action="operatorTasks"/>
<sj:div href="%{operatorTask}">Initial Content</sj:div>


<s:form action="TourPlanSearch" namespace="/operator" method="post" theme="simple">
  <s:hidden name="recordNo"></s:hidden>
</s:form>
<s:form action="ShowRouteJourney" namespace="/product" method="post" theme="simple">
  <s:hidden name="lineNo"></s:hidden>
</s:form>

</body>
</html>

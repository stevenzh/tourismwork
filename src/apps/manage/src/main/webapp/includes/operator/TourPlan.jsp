<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出团计划</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="出团计划">
</head>

<body>
<script language="JavaScript">
<!--//

function changedp()
{
  var fm = document.TourPlanSearch;
  var departments = document.getElementById("kenDepartment");
  var combo = document.getElementById("kenPrincipal")
  var sdpt_no = departments.value;

  //更换目的地的内容
  //将原表中的内容清空
  while (combo.options.length>0) {
      combo.remove(0);
  }
  combo.disabled=false;
  
  $.ajax({
      type: "post",
      url: '<s:url value="/json/listEmployee.jspa" encode="false" includeParams="none"/>',
      timeout: 20000,
      error: function(){
          // alert('服务器错误');
      },
      data: {groupId: sdpt_no},
      async: false,
      success: function(data){
          $.each(data, function(i, n){

          if (typeof(n)=='object')
          {
              var kOption = document.createElement('OPTION');
              kOption.text = "全部";
              kOption.value = "";
              combo.options.add(kOption);
              
              $.each(n,function(j,m){
                  var oOption = document.createElement('OPTION');
                  oOption.text = m ;
                  oOption.value = j ;
                  combo.options.add(oOption);
              });
          }
      });
      }
  });
}

function _getlist(type)
{
  var frm = document.TourPlanSearch;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}

function modifyRoute(param)
{
  var fm = document.RouteChange;
  var ro = document.getElementById('RouteChange_routeNo');
  ro.value = param;
  fm.submit();
}

function SubmitForm(param, target)
{
  var fm = document.TourPlanSearch;
  fm.recordNo.value = target;
  if (param =='delete') {
  	if(confirm('确定删除吗？') == false){
   	 	return;
    }
  	fm.note.value = window.prompt("备注","请认真填写删除原因");
   	fm.action = "<s:url action='TourPlanDelete' namespace='/operator'/>"
  } else if (param == 'change') {
   	fm.action = "<s:url action='TourPlanChange' namespace='/operator' />"
  } else if (param == 'booking') {
    fm.action = "<s:url action='showPlanBooking' namespace='/operator' />"
  } else if (param == 'build') {
    fm.action = "<s:url action='buildTour' namespace='/operator' />"
  }
  fm.submit();
}

function generateXLS()
{
  var fm = document.jxlsReport;
  document.getElementById('paramid1').value =getRadioValue('kenClassType');
  document.getElementById('paramid2').value =document.getElementById('kenRrouteName').value;
  document.getElementById('paramid3').value =document.getElementById('kenDepartment').value;
  document.getElementById('paramid4').value =document.getElementById('kenPrincipal').value;
  var fields = document.getElementById('kenStartDate');
  document.getElementById('paramid5').value = fields.children[1].value;
  var efields = document.getElementById('kenEndDate');
  document.getElementById('paramid6').value = efields.children[1].value;
  fm.submit();
}
  
function showRouteJourney(obj)
{
    var fm=document.TourPlanSearch;
    fm.lineNo.value=obj;
    fm.action="<s:url action='ShowRouteJourney' namespace='/product' />";
    fm.submit();  
}
	
function generateXLS2()
{
  var fm = document.jxlsReport2;
  document.getElementById('paramid7').value =getRadioValue('kenClassType');
  document.getElementById('paramid8').value =document.getElementById('kenRrouteName').value;
  document.getElementById('paramid9').value =document.getElementById('kenDepartment').value;
  document.getElementById('paramid10').value =document.getElementById('kenPrincipal').value;
  var fields = document.getElementById('kenStartDate');
  document.getElementById('paramid11').value = fields.children[1].value;
  var efields = document.getElementById('kenEndDate');
  document.getElementById('paramid12').value = efields.children[1].value;
  fm.submit();
}

function detail(no)
{
  var fm = document.TourPlanSearch;
  fm.tourNo.value = no;
  fm.action="<s:url action='showTourOperate' namespace='/operator' includeParams='none'/>"
  fm.submit();
}

//-->
</script>

<s:form action="TourPlanSearch" namespace="/operator" method="post" theme="simple">
<s:hidden name="recordNo"></s:hidden>
<s:hidden name="note"></s:hidden>
<s:hidden name="lineNo"></s:hidden>
<s:hidden name="tourNo"></s:hidden>

<table border="0" style="width: 100%">
  <tr>
    <td class="idx">线路名称:</td>
    <td colspan="2">
    <s:textfield  id="kenRrouteName"  name="kenRrouteName" size="42"/>
    </td>
  </tr>
  <tr>
    <td class="idx">工作组:</td>
    <td colspan="2">
    <authz:authorize ifAnyGranted="ROLE_SUPERUSER,ROLE_SUPEROPERATOR">
    <s:select id="kenDepartment"
              name="kenDepartment"
              list="teamList"
              listKey="teamId"
              listValue="name"
              headerKey="0"
              headerValue="全部"
              onchange="javascript:changedp();">
    </s:select>
    </authz:authorize>

    <authz:authorize ifNotGranted="ROLE_SUPERUSER,ROLE_SUPEROPERATOR">
    <s:select list="teamList"
              listKey="teamId"
              listValue="name"
              onchange="javascript:changedp();"
              value="%{kenDepartment}"
              disabled="true">
    </s:select>
    <s:hidden name="kenDepartment"></s:hidden>
    </authz:authorize>
    </td>
  </tr>
  <tr>
    <td class="idx">专管员：</td>
    <td colspan="2">
    <authz:authorize ifAnyGranted="ROLE_SUPERUSER,ROLE_SUPEROPERATOR,ROLE_GROUPOPERATOR">
    <s:select id="kenPrincipal"
              name="kenPrincipal"
              list="employees"
              listKey="userId"
              listValue="userName"
              headerKey="0"
              headerValue="全部">
    </s:select>
    </authz:authorize>
    
    <authz:authorize ifNotGranted="ROLE_SUPERUSER,ROLE_SUPEROPERATOR,ROLE_GROUPOPERATOR">
    <s:select list="employees"
              listKey="userId"
              listValue="userName"
              value="%{kenPrincipal}"
              disabled="true">
    </s:select>
    <s:hidden name="kenPrincipal"></s:hidden>
    </authz:authorize>
    </td>
  </tr>
  <tr>
    <td class="idx">出发日期:</td>
    <td>
    <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" id="kenStartDate" name="kenStartDate">
    </sj:datepicker>&nbsp;至
    <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" id="kenEndDate"  name="kenEndDate"></sj:datepicker>
    </td>
    <td>
    <s:submit value="%{getText('common.forms.search')}"></s:submit>
    </td>
  </tr>
</table>

<br>

<table border="0" style="width: 100%">
  <tr>
    <td class="lstidx">NO.</td>
    <td class="lstidx">&nbsp;</td>
    <td class="lstidx">团号</td>
    <td class="lstidx">线路名</td>
    <td class="lstidx">工作组</td>
    <td class="lstidx">专管员</td>
    <td class="lstidx">出发日期</td>
    <td class="lstidx">直客价</td>
    <td class="lstidx">同业价</td>
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
  <tr>
    <td class="cdata"><s:property value="#rowcounter.count"/></td>
    <td class="cdata"><s:if test='isBuildup eq "Y"'><font color="green">★</font></s:if></td>
    <s:if test='delKey eq "N"'>
      <td class="data"><A href="#" onclick="javascript:detail('<s:property value='tourNo'/>')"><s:property value="tourNo"/></A></td>
    </s:if>
    <s:else>
      <td class="data"><s:property value='tourNo'/><font color="red">(团已取消)</font></td>
    </s:else>
    <td class="data">
    <a href="#" onclick="javascript:showRouteJourney('<s:property value='line.lineNo'/>')"><s:property value="line.lineName"/></a>
    </td>
    <td class="cdata"><s:property value="team.name"/></td>
    <td class="cdata"><s:property value="assigned.userName"/></td>
    <td class="cdata"><s:date name="outDate" format="yyyy-MM-dd [E]"/></td>
    <td class="rdata">
    <s:text name="format.money">
      <s:param value="packagePrice.price" />
    </s:text>&nbsp;
    </td>
    <td class="rdata">
    <s:text name="format.money">
      <s:param value="packagePrice.priceOther" />
    </s:text>&nbsp;
    </td>
    <td class="rdata">
    <s:text name="format.money">
      <s:param value="packagePrice.priceCost" />
    </s:text>&nbsp;</td>
    
    <td class="rdata"><s:property value="pax1"/>&nbsp;</td>
    <td class="rdata"><s:property value="pax2"/>&nbsp;</td>
    <td class="rdata"><s:property value="pax3"/>&nbsp;</td>
    <td class="rdata"><s:property value="pax4"/>&nbsp;</td>
    <td class="rdata"><s:property value="pax5"/>&nbsp;</td>
    <td class="cdata"><s:property value="line.outCity.citynm"/></td>
    <td>
    <a href="#" onclick="javascript:modifyRoute('<s:property value='line.lineNo'/>')">修改线路</a>
    <a href="javascript:SubmitForm('change','<s:property value='recordNo'/>');">修改计划</a>
    <a href="javascript:SubmitForm('delete','<s:property value='recordNo'/>');">删除</a>
    <s:if test="isEnter > 0">
    <a href="javascript:SubmitForm('booking','<s:property value='recordNo'/>');">订单</a>
    <s:if test='isBuildup neq "Y"'><a href="javascript:SubmitForm('build','<s:property value='recordNo'/>');">成团</a></s:if>
    </s:if>
    </td>
  </tr>
  </s:iterator>
</table>

<s:if test="plans.isEmpty() == false">
<%@ include file="/includes/PagerTable.jsp" %>
</s:if>

<authz:authorize ifAnyGranted="ROLE_PRODUCT">
<div>
 <input type="button" value="生成报价" onclick="javascript:generateXLS()">
 <input type="button" value="生成成本分析" onclick="javascript:generateXLS2()">
</div>
</authz:authorize>
</s:form>

<s:form action="RouteChange" namespace="/product">
  <s:hidden id="RouteChange_routeNo" name="routeNo"></s:hidden>
</s:form>

<s:form action="jxlsReport" namespace="/" method="POST" theme="simple">	
  <s:hidden name="parameters(0).name" value="kenClassType"></s:hidden>
  <s:hidden id="paramid1" name="parameters(0).data"></s:hidden>
  <s:hidden name="parameters(1).name" value="kenRrouteName"></s:hidden>
  <s:hidden id="paramid2" name="parameters(1).data"></s:hidden>
  <s:hidden name="parameters(2).name" value="kenDepartment"></s:hidden>
  <s:hidden id="paramid3" name="parameters(2).data"></s:hidden>
  <s:hidden name="parameters(3).name" value="kenPrincipal"></s:hidden>
  <s:hidden id="paramid4" name="parameters(3).data"></s:hidden>
  <s:hidden name="parameters(4).name" value="kenStartDate"></s:hidden>
  <s:hidden id="paramid5" name="parameters(4).data"></s:hidden>
  <s:hidden name="parameters(5).name" value="kenEndDate"></s:hidden>
  <s:hidden id="paramid6" name="parameters(5).data"></s:hidden>
  <s:hidden name="reportId" value="24"></s:hidden>
</s:form>

<s:form action="jxlsReport2" namespace="/" method="POST" theme="simple">	
  <s:hidden name="parameters(6).name" value="kenClassType"></s:hidden>
  <s:hidden id="paramid7" name="parameters(6).data"></s:hidden>
  <s:hidden name="parameters(7).name" value="kenRrouteName"></s:hidden>
  <s:hidden id="paramid8" name="parameters(7).data"></s:hidden>
  <s:hidden name="parameters(8).name" value="kenDepartment"></s:hidden>
  <s:hidden id="paramid9" name="parameters(8).data"></s:hidden>
  <s:hidden name="parameters(9).name" value="kenPrincipal"></s:hidden>
  <s:hidden id="paramid10" name="parameters(9).data"></s:hidden>
  <s:hidden name="parameters(10).name" value="kenStartDate"></s:hidden>
  <s:hidden id="paramid11" name="parameters(10).data"></s:hidden>
  <s:hidden name="parameters(11).name" value="kenEndDate"></s:hidden>
  <s:hidden id="paramid12" name="parameters(11).data"></s:hidden>
  <s:hidden name="reportId" value="25"></s:hidden>
</s:form> 

</body>
</html>

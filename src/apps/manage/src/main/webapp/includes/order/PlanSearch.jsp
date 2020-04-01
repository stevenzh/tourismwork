<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查找出团计划</title>
<meta name="menu" content="OrderMenu"/>
<meta name="heading" content="查找出团计划">
</head>

<body>
<script language="javascript">
function AddBooking(pid)
{
  var fm = document.showOrder;
  fm.recordNo.value = pid;
  fm.submit();
}

function change()
{
  var fm = document.submitPlanSearch;
  var departments = document.getElementById("departmentNo");
  var combo = document.getElementById("userId")
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
  var frm = document.submitPlanSearch;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}

function showRoute(param)
{
  var fm = document.RouteDetail;
  fm.routeNo.value = param;
  fm.submit();
}
</script>

<s:form action="submitPlanSearch" namespace="/sales" method="post" theme="simple">
  <s:hidden name="recordNo"></s:hidden>
  <table style="width: 100%">
    <tr>
      <td>
      <!-- 查询区开始 -->
      <table align="left" border="0" style="width: 100%">
        <tr>
          <td class="idx">工作组(操作)：</td>
          <td>
          <s:select id="departmentNo"
                    name="kenDepartmentNo"
                    list="teamList"
                    listKey="teamId"
                    listValue="name"
                    headerKey="0"
                    headerValue="全部"
                    onchange="javascript:change();">
          </s:select>
          </td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td class="idx">操作员：</td>
          <td>
          <s:select id="userId"
                    name="kenUserId"
                    list="employeeList"
                    listKey="userId"
                    listValue="userName"
                    headerKey="0"
                    headerValue="全部">
          </s:select>
          </td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td class="idx">线路名称：</td>
          <td>
          <s:textfield name="kenRouteName" size="42"/>
          </td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td class="idx">出发日期：</td>
          <td>
          <s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenStartDatePeriod">
          </s:textfield>&nbsp;至
          <s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenEndDatePeriod">
          </s:textfield>
          </td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td colspan="2"></td>
          <td>
          <s:submit value="%{getText('common.forms.search')}"></s:submit>
          </td>
        </tr>
      </table>
      <!-- 查询区结束 -->
      </td>
    </tr>
  </table>

  <table style="width: 100%">
    <tr>
      <td class="lstidx">NO.</td>
      <td class="lstidx">&nbsp;</td>
      <td class="lstidx">团号</td>
      <td class="lstidx">工作组(计划)</td>
      <td class="lstidx">线路名</td>
      <td class="lstidx">出发日期</td>
      <td class="lstidx">团队性质</td>
      <td class="lstidx" width="48">直客价</td>
      <td class="lstidx" width="48">同业价</td>
      <td class="lstidx" width="48">单房差</td>
      <td class="lstidx" width="40">可收</td>
      <td class="lstidx" width="40">已收</td>
      <td class="lstidx" width="40">剩余</td>
      <td class="lstidx" width="40">占位</td>
      <td class="lstidx">出发地</td>
      <td class="lstidx">操作</td>
    </tr>

    <s:iterator value="plans" status="rowcounter">
    <s:if test="#rowcounter.count > fromRecord">
    <s:if test="#rowcounter.count <= toRecord">
    <tr>
      <td class="cdata"><s:property value="#rowcounter.count"/></td>
      <td class="cdata"><s:if test='isBuildup eq "Y"'><font color="green">★</font></s:if></td>
      <s:if test='shareFlight eq "Y"'>
     	<td class="data"><s:property value="tourNo"/><font color="RED">(共享号<s:property value="shareFlightId"/>)</font></td>
      </s:if>
      <s:else>
      	<td class="data"><s:property value="tourNo"/></td>
      </s:else>
      <td class="data"><s:property value="team.name"/></td>
      <td class="data">
      <a href="http://localhost:8080/RouteDetail.action?routeNo=<s:property value='line.lineNo'/>" target="_bank"><s:property value="line.lineName"/></a>
      </td>
      <td class="data" nowrap="nowrap"><s:date name="outDate" format="yyyy-MM-dd [E]"/></td>
      <td class="data" nowrap="nowrap"><STRONG><s:property value="singleShow"/></STRONG></td>
      <td class="rdata"><s:property value="packagePrice.price"/>&nbsp;</td>
      <td class="rdata"><s:property value="packagePrice.priceOther"/>&nbsp;</td>
      <td class="rdata"><s:property value="packagePrice.priceCost"/>&nbsp;</td>
      <td class="rdata"><s:property value="planPax"/>&nbsp;</td>
      <td class="rdata"><s:property value="pax2"/>&nbsp;</td>
      <td class="rdata"><s:property value="pax3"/>&nbsp;</td>
      <td class="rdata"><s:property value="holdPax"/>&nbsp;</td>
      <td class="data"><s:property value="line.outCity.citynm"/></td>
      <td class="data">
      <s:if test="enter">
      <a href="javascript:AddBooking('<s:property value="recordNo"/>');">预订</a>
      </s:if>
      <authz:authorize ifAnyGranted="ROLE_SUPERUSER">
      <a href="javascript:AddBooking('<s:property value="recordNo"/>');">补订单</a>
      </authz:authorize>
      </td>
    </tr>
    </s:if>
    </s:if>
    </s:iterator>
  </table>
  <%@ include file="/includes/PagerTable.jsp" %>
</s:form>

<s:form action="showOrder" method="post" namespace="/sales">
  <s:hidden name="recordNo"></s:hidden>
</s:form>

<s:form action="RouteDetail" method="POST" namespace="/">
  <s:hidden name="routeNo"></s:hidden>
</s:form>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查找团</title>
<meta name="menu" content="FinanceMenu"/>
<meta name="heading" content="查找团">
</head>

<body>
<script language="JavaScript">
<!--//

function changedp()
{
  var fm = document.SingleTourBalanceSearch;
  var departments = document.getElementById("kenDepartmentId");
  var combo = document.getElementById("kenEmployeeId")
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
  var frm = document.SingleTourBalanceSearch;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}

function auditing(param)
{
   var fm=document.SingleTourBalanceSearch;
   fm.tourNo.value=param;
   
   fm.action="<s:url action='SingleBalanceAuditingInput' namespace='/finance' includeParams='none'/>";
   fm.submit();
}

function modify(param)
{
   var fm=document.SingleTourBalanceSearch;
   fm.tourNo.value=param;
   
   fm.action="<s:url action='SingleTourBalanceModifyInput' namespace='/finance' includeParams='none'/>";
   fm.submit();
}

function detail(no)
{
  var fm = document.TourDetail;
  fm.tourNo.value = no;
  fm.submit();
}

function get(param)
{
	var fm = document.SingleTourBalanceSearch;
	fm.tourNo.value=param;
	fm.action="<s:url action='SingleTourIncome' namespace='/finance' includeParams='none'/>";
	fm.submit();
}

function warrant(param)
{
	var fm = document.SingleTourBalanceSearch;
	fm.tourNo.value=param;
	fm.action="<s:url action='warrantInput' namespace='/finance' includeParams='none'/>";
	fm.submit();
}

function printBalance(tourNo)
{
   var fm=document.fopReport;
   document.getElementById("paramid").value=tourNo;
   fm.submit();
}

//-->
</script>

<table border="0" style="width: 100%">
  <tr>
    <td class="header">[单团核算]--查找团</td>
  </tr>
</table>

<s:form action="SingleTourBalanceSearch" namespace="/finance" method="post" theme="simple">
<s:hidden name="tourNo"></s:hidden>

<table border="0" style="width: 100%">
  <tr>
    <td class="idx">工作组:</td>
    <td colspan="2">
    <s:select id="kenDepartmentId"
              name="kenDepartmentId"
              list="teamList"
              listKey="teamId"
              listValue="name"
              headerKey="0"
              headerValue="全部"
              onchange="javascript:changedp();">
    </s:select>
    </td>
  </tr>
  <tr>
    <td class="idx">专管员：</td>
    <td colspan="2">
    <s:select id="kenEmployeeId"
              name="kenEmployeeId"
              list="employees"
              listKey="userId"
              listValue="userName"
              headerKey=""
              headerValue="全部">
    </s:select>
    </td>
  </tr>
  <tr>
    <td class="idx">线路名称:</td>
    <td colspan="2">
    <s:textfield name="kenRouteName" size="42"/>
    </td>
  </tr>
  <tr>
    <td class="idx">出发日期:</td>
    <td>
    <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenStartDate">
    </sj:datepicker>&nbsp;至
    <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenEndDate">
    </sj:datepicker>
    </td>
    <td>
    <s:submit action="SingleTourBalanceSearch" value="%{getText('common.forms.search')}"></s:submit>
    </td>
  </tr>
</table>

<br>

<table border="0" style="width: 100%">
  <tr>
    <td class="lstidx">NO.</td>
    <td class="lstidx" nowrap="nowrap">团号</td>
    <td class="lstidx">线路</td>
    <td class="lstidx">出发日期</td>
    <td class="lstidx">人数</td>
    <td class="lstidx">总收入</td>
    <td class="lstidx">纯团费</td>
    <td class="lstidx">已收款</td>
    <td class="lstidx">成本</td>
    <td class="lstidx">毛利</td>
    <td class="lstidx">毛利率</td>
    <td class="lstidx">操作</td>
  </tr>

  <s:iterator value="tours" status="rowcounter">
  <s:if test="#rowcounter.count > fromRecord">
  <s:if test="#rowcounter.count <= toRecord">
  <tr>
    <td class="cdata"><s:property value="#rowcounter.count"/></td>
    <td class="data"><a href="#" title="点击查看团信息" onclick="javascript:detail('<s:property value='tourNo'/>')"><s:property value="tourNo" /></a></td>
    <td class="data"><s:property value="line.lineName"/><s:hidden name="line.lineName"/></td>
    <td class="cdata"><s:date name="outDate" format="yyyy-MM-dd"/></td>
    <td class="rdata"><s:property value="pax-leaderPax"/>+<s:property value="leaderPax"/></td>
    <td class="rdata"><s:property value="amount"/></td>
    <td class="rdata"><s:property value="tourAmount"/></td>
    <td class="rdata"><s:property value="alAmount"/></td>
    <td class="rdata"><s:property value="cost"/></td>
    <td class="rdata"><s:property value="grossAmount"/></td>
    <td class="rdata"><s:property value="grossAmountRate"/>%</td>
    <td class="cdata">
     <s:if test="frChecked eq 'N'">
      <a href="#" title="修改单团核算" onclick="javascript:modify('<s:property value="tourNo"/>');">修改</a>
      <a href="#" title="审核单团核算" onclick="javascript:auditing('<s:property value="tourNo"/>');">审核</a>
     </s:if>
     <s:else>
      <a href="#" title="查看单团核算" onclick="javascript:auditing('<s:property value="tourNo"/>');">已审核</a>
     </s:else>
     <a href="#" title="单团收款销帐" onclick="javascript:get('<s:property value="tourNo"/>');">收款</a>
     <a href="#" title="担保" onclick="javascript:warrant('<s:property value="tourNo"/>');">担保</a>
     <a href="#" title="打印单团核算表" onclick="javascript:printBalance('<s:property value="tourNo" />');">打印</a>
    </td>
  </tr>
  
  </s:if>
  </s:if>
  </s:iterator>
  <s:if test="tours.isEmpty() == false">
  <tr bgcolor="#b9c0ff">
    <td align="center" colspan="4"><b>合计</b></td>
    <td align="right"><b><s:property value="allPax-allLeaderPax"/>+<s:property value="allLeaderPax"/></b></td>
    <td align="right"><b><s:property value="allAmount"/></b></td>
    <td align="right"><b><s:property value="allTourAmount"/></b></td>
    <td align="right"><b><s:property value="allAlAmount"/></b></td>
    <td align="right"><b><s:property value="allCostAmount"/></b></td>
    <td align="right"><b><s:property value="allGrossAmount"/></b></td>
    <td align="right"><b><s:property value="allGrossAmountRate"/>%</b></td>
    <td align="right">&nbsp;</td>
  </tr>
  
  </s:if>
</table>

<s:if test="tours.isEmpty() == false">
<%@ include file="/includes/PagerTable.jsp" %>
</s:if>
</s:form>

<s:form action="TourDetail" namespace="/finance" method="post" theme="simple">
  <s:hidden name="tourNo"></s:hidden>
</s:form>

<s:form action="fopReport" namespace="/" method="POST" theme="simple">
  <s:hidden name="parameters(0).name" value="TOUR_NO"></s:hidden>
  <s:hidden id="paramid" name="parameters(0).data"></s:hidden>
  
  <s:hidden name="reportId" value="13"></s:hidden>
</s:form>

</body>
</html>

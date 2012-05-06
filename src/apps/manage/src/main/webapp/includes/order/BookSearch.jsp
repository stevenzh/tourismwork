<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单查询</title>
<meta name="menu" content="OrderMenu"/>
<meta name="heading" content="订单查询">
</head>

<body>
<script language="JavaScript">
<!--//

function changedp()
{
  var fm = document.ConfirmBookSearch;
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
  var frm = document.ConfirmBookSearch;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}

function detail(no)
{
  var fm = document.ConfirmBookSearch;
  fm.recordNo.value = no;
  fm.action="<s:url action='ConfirmBookInput' namespace='/operator' includeParams='none'/>";
  fm.submit();
}

//-->
</script>

<s:form action="ConfirmBookSearch" namespace="/sales" method="post" theme="simple">
<s:hidden name="recordNo"></s:hidden>
<table style="width: 80%">
  <tr>
    <td class="idx">工作组(操作):</td>
    <td colspan="2">
    <s:select id="kenDepartmentId"
              name="kenDepartmentId"
              list="teamList"
              listKey="teamId"
              listValue="name"
              onchange="javascript:changedp();"
              headerKey="0"
              headerValue="全部">
    </s:select>
    </td>
  </tr>
  <tr>
    <td class="idx">专管员：</td>
    <td colspan="2">
    
    <authz:authorize ifAnyGranted="ROLE_SUPERUSER,ROLE_SUPEROPERATOR,ROLE_GROUPOPERATOR">
    <s:select id="kenEmployeeId"
              name="kenEmployeeId"
              list="employees"
              listKey="userId"
              listValue="userName"
              headerKey=""
              headerValue="全部">
    </s:select>
    </authz:authorize>
    
    <authz:authorize ifNotGranted="ROLE_SUPERUSER,ROLE_SUPEROPERATOR,ROLE_GROUPOPERATOR">
    <s:select list="employees"
              listKey="userId"
              listValue="userName"
              value="%{kenEmployeeId}"
              disabled="true">
    </s:select>
    <s:hidden name="kenEmployeeId"></s:hidden>
    </authz:authorize>
    </td>
  </tr>
  <tr>
    <td class="idx">线路名称:</td>
    <td colspan="2">
    <s:textfield name="kenRrouteName" size="42"/>
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
    <s:submit value="%{getText('common.forms.search')}"></s:submit>
    </td>
  </tr>
</table>

<br>

<table style="width: 100%">
  <tr>
    <td class="lstidx" nowrap="nowrap">订单番号</td>
    <td class="lstidx">线路名</td>
    <td class="lstidx">出发日期</td>
    <td class="lstidx">客户</td>
    <td class="lstidx">联系人</td>
    <td class="lstidx">联系电话</td>
    <td class="lstidx">预订名额</td>
    <td class="lstidx">确认名额</td>
    <td class="lstidx">名单名额</td>
    <td class="lstidx">预订日期</td>
  </tr>

  <s:iterator value="books" status="rowcounter">
  <s:if test="#rowcounter.count > fromRecord">
  <s:if test="#rowcounter.count <= toRecord">
  <tr>
    <td class="cdata"><A href="#" onclick="javascript:detail('<s:property value='recordNo'/>')"><s:property value="recordNo"/></A></td>
    <td class="data"><s:property value="lineName"/></td>
    <td class="cdata"><s:date name="outDate" format="yyyy-MM-dd"/></td>
    <td class="data"><s:property value="customer.name"/></td>
    <td class="data"><s:property value="contact"/></td>
    <td class="data"><s:property value="phone"/></td>
    <td class="rdata"><s:property value="pax"/>&nbsp;</td>
    <td class="rdata"><s:property value="confirmPax"/>&nbsp;</td>
    <td class="rdata"><s:property value="importPax"/>&nbsp;</td>
    <td class="cdata"><s:date name="reserveDate" format="yyyy-MM-dd"/></td>
  </tr>
  </s:if>
  </s:if>
  </s:iterator>
</table>

<s:if test="books.isEmpty() == false">
<%@ include file="/includes/PagerTable.jsp" %>
</s:if>

</s:form>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>线路查询</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="线路查询">
</head>

<body>
<script type="text/javascript">
<!--//
function SubmitForm(param, target)
{
  var fm = document.searchRoute;
  fm.routeNo.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='RouteDelete' namespace='/product'/>"
  }
  else if(param =='modify')
  {
    fm.action = "<s:url action='RouteChange' namespace='/product'/>"
  }
  else if(param == 'print')
  {
    fm.action = "<s:url action='RouteReport' namespace='/product'/>"
  }
  else if(param == 'copy')
  {
    fm.action = "<s:url action='showCopyRoute' namespace='/product'/>"
  }
  fm.submit();
}

function change()
{
  var combo = document.getElementById("userId")
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
      data: {groupId: $("#departmentNo").val()},
      async: false,
      success: function(data){
          $.each(data, function(i, n){

          if (typeof(n)=='object')
          {
              combo.options.add(new Option("全部", ""));
              
              $.each(n,function(j,m){
            	  combo.options.add(new Option(m, j));
              });
          }
      });
      }
  });
}

function _getlist(type)
{
  var frm = document.searchRoute;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}

function OpenWindow(url) 
{ window.open(url,null,"height=500,width=800,status=no,toolbar=no,menubar=no,location=no"); 
} 

function generateXLS(param)
{
  var fm = document.jxlsReport;
  document.getElementById('paramid').value = param;
  fm.submit();
}

//-->
</script>
<s:form action="searchRoute" namespace="/product" method="POST" theme="simple" cssClass="yform">
  <s:hidden name="routeNo" />

  <table cellpadding="2" cellspacing="2" width="80%">
    <tr>
      <td class="idx">工作组：</td>
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
      <td class="idx">专管员：</td>
      <td>
      <authz:authorize ifAnyGranted="ROLE_SUPERUSER,ROLE_GROUPPRODUCT,ROLE_SUPERPRODUCT">
      <s:select id="userId"
                name="kenUserId"
                list="employeeList"
                listKey="userId"
                listValue="userName"
                headerKey="0"
                headerValue="全部">
      </s:select>
      </authz:authorize>
      
      <authz:authorize ifNotGranted="ROLE_SUPERUSER,ROLE_GROUPPRODUCT,ROLE_SUPERPRODUCT">
      <s:select list="employeeList"
                listKey="userId"
                listValue="userName"
                value="%{kenUserId}"
                disabled="true">
      </s:select>
      <s:hidden name="kenUserId"></s:hidden>
      </authz:authorize>
      </td>
    </tr>
    <tr>
      <td class="idx">线路名称：</td>
      <td>
        <s:textfield id="kenRouteName" name="kenRouteName" />
      </td>
      <td class="idx">目的地 ：</td>
      <td>
      <s:select name="kenDestination"
                list="destinationList"
                listKey="code"
                listValue="%{code +' '+ cnName}"
                headerKey=""
                headerValue="全部">
      </s:select>
      </td>
    </tr>
    <tr>
      <td class="idx">状态：</td>
      <td colspan="3">
        <s:radio list="closekeyList"
                 name="kenClosekey"
                 listKey="value"
                 listValue="label">
        </s:radio>
      </td>
     </tr>
  </table>
  <br>
  <div>
  <s:submit value="%{getText('common.forms.search')}" />
  <s:submit action="RouteAdd" value="%{getText('common.forms.add')}" /> 
  </div>
  <table style="width: 100%">
    <tr>
      <td class="lstidx">NO.</td>
      <td class="lstidx">线路名称</td>
      <td class="lstidx">天数</td>
      <td class="lstidx">工作组</td>
      <td class="lstidx">专管员</td>
      <td class="lstidx">目的地</td>
      <td class="lstidx">出发城市</td>
      <td class="lstidx">状态</td>
      <td class="lstidx" width="120">操作</td>
    </tr>

    <s:iterator value="routeList" status="rowcounter">
    <s:if test="isActive=true">
      <tr>
    </s:if>
    <s:else>
      <tr bgcolor="#C7ACBE">
    </s:else>
        <td align="center">&nbsp;<s:property value="#rowcounter.count" /></td>
        <td>&nbsp;<s:property value="lineName" /></td>
        <td>&nbsp;<s:property value="lineDay" /></td>
        <td>&nbsp;<s:property value="team.name" /></td>
        <td>&nbsp;<s:property value="assigned.userName" /></td>
        <td>&nbsp;<s:property value="destination.cnName" /></td>
        <td>&nbsp;<s:property value="outCity.citynm" /></td>
        <td align="center"><s:if test="isActive==true">可用</s:if><s:else>停用</s:else></td>
        <td align="center">
          <img title="打印" src="<s:url value='/images/manage/btnPrint.gif' />"
               width="18" height="18"
               onclick="javascript:generateXLS('<s:property value="lineNo" />')"/>
          <img title="修改" src="<s:url value='/images/manage/btnEdit.gif' />"
               width="18" height="18"
               onclick="javascript:SubmitForm('modify','<s:property value="lineNo" />')"/>
          <img title="复制" src="<s:url value='/images/manage/btnCopy.gif' />"
               width="18" height="18"
               onclick="javascript:SubmitForm('copy','<s:property value="lineNo" />')"/>
          <img title="删除" src="<s:url value='/images/manage/btnDel.gif' />"
               width="18" height="18"
               onclick="javascript:SubmitForm('delete','<s:property value="lineNo" />')"/>
          <img title="链接地址复制到剪贴板" src="<s:url value='/images/manage/btnPaste.gif' />"
               width="18" height="18"
               onclick="javascript:copyToClipboard('http://localhost:8080/RouteDetail.action?routeNo=<s:property value="routeNo" />')" />
        </td>
      </tr>
    </s:iterator>

  </table>

  <s:if test="routeList.isEmpty() ==false">
  <%@ include file="/includes/PagerTable.jsp" %>
  </s:if>

</s:form>

<s:form action="jxlsReport" namespace="/" method="POST" theme="simple"> 
  <s:hidden name="parameters(0).name" value="ROUTE_NO"></s:hidden>
  <s:hidden id="paramid" name="parameters(0).data"></s:hidden>
  <s:hidden name="reportId" value="21"></s:hidden>
</s:form>

</body>
</html>

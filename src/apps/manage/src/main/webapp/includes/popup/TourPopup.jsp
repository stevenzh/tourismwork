<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询内容</title>
</head>

<body>
<script language="JavaScript">
<!--//

function changedp()
{
  var fm = document.submitTourSearch;
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
  var frm = document.submitTourSearch;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}

function detail(no)
{
  var fm = document.submitTourSearch;
  fm.tourNo.value = no;
  fm.action="<s:url action='showTourOperate' namespace='/operator' includeParams='none'/>"
  fm.submit();
}



function showRouteJourney(obj)
{
    var fm=document.submitTourSearch;
    fm.lineNo.value=obj;
    fm.action="<s:url action='ShowRouteJourney' namespace='/product' />";
    fm.submit();  
}

//-->
</script>

<table border="0" style="width: 100%">
  <tr>
    <td class="header">查找团</td>
  </tr>
</table>

<s:form action="submitTourSearch" namespace="/operator" method="post" theme="simple">
<s:hidden name="tourNo"></s:hidden>
<s:hidden name="lineNo"></s:hidden>
<table border="0" style="width: 100%">
  <tr>
    <td class="idx">工作组:</td>
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
    <s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenStartDate">
    </s:textfield>&nbsp;至
    <s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenEndDate">
    </s:textfield>
    </td>
    <td>
    <s:submit value="%{getText('common.forms.search')}"></s:submit>
    </td>
  </tr>
</table>

<br>

<table border="0" style="width: 100%">
  <tr>
    <td class="lstidx" nowrap="nowrap">团号</td>
    <td class="lstidx">行程</td>
    <td class="lstidx">团类型</td>
    <td class="lstidx">人数</td>
    <td class="lstidx">出发日期</td>
  </tr>

  <s:iterator value="tours" status="rowcounter">
  <s:if test="#rowcounter.count > fromRecord">
  <s:if test="#rowcounter.count <= toRecord">
  <tr>
  <s:if test='delKey eq "N"'>
    <td class="data"><A href="#" onclick="javascript:detail('<s:property value='tourNo'/>')"><s:property value="tourNo"/></A></td>
  </s:if>
  <s:else>
    <td class="data"><s:property value='tourNo'/><font color="red">(团已取消)</font></td>
  </s:else>
    <td class="data">
    <a href="#" onclick="javascript:showRouteJourney('<s:property value='line.lineNo'/>')"><s:property value="lineName"/></a>
    </td>
    <td class="data"><s:property value="tourKey"/></td>
    <td class="rdata"><s:property value="pax"/>&nbsp;</td>
    <td class="cdata"><s:date name="outDate" format="yyyy-MM-dd"/></td>
  </tr>
  </s:if>
  </s:if>
  </s:iterator>
</table>

<s:if test="tours.isEmpty() == false">
<%@ include file="/includes/PagerTable.jsp" %>
</s:if>

</s:form>

</body>
</html>

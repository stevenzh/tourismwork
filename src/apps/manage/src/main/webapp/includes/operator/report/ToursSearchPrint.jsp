<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出境游名单打印：查找团</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="出境游名单打印：查找团">
</head>

<body>
<script language="JavaScript">
<!--//

function changedp()
{
  var fm = document.ToursSearchOutBand;
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
  var frm = document.ToursSearchOutBand;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}

function checkOut()
{
   var fm = document.ToursSearchOutBand;
   var field = fm.tourNos;
   var isChecked=-1;
   
   if(field.length != null)
   {
     for(var i=0;i<field.length;i++)
     {
       if(field[i].checked)
       {
         isChecked = i;
         break;
       }
     }
   }
   else if(field.checked)
          {
            isChecked=1;
          }
   
   
   if(isChecked == -1)
   {
      alert("你没有选择任何记录！");
      return;
   }
   
   fm.action = "<s:url action='ToursSearchOutBandNext' namespace='/operator'/>";
   fm.submit();
   
   
}


//-->
</script>
<s:form action="ToursSearchOutBand" namespace="/operator" method="post" theme="simple">
<s:hidden name="tourNos"></s:hidden>
<s:hidden name="tourNum"></s:hidden>
<table border="0" style="width: 100%">
  <tr>
    <td class="idx">工作组:</td>
    <td colspan="2">
    <s:select id="kenDepartmentId"
              name="kenDepartmentId"
              list="teamList"
              listKey="teamId"
              listValue="name"
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
    <s:submit action="ToursSearchOutBand" value="%{getText('common.forms.search')}"></s:submit>
    </td>
  </tr>
</table>

<br>

<table border="0" style="width: 100%">
  <tr>
    <td class="lstidx">NO.</td>
    <td class="lstidx">选择</td>
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
    <td class="cdata"><s:property value="#rowcounter.count"/></td>
    <td class="cdata"><s:checkbox  name="tourNos" fieldValue="%{tourNo}"/></td>
    <td class="data"><s:property value="tourNo"/></td>
    <td class="data"><s:property value="line.lineName"/></td>
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
<input type="button" value="下一步" onClick="javascript:checkOut();">
</s:if>

</s:form>

</body>
</html>

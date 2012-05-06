<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择领队</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="选择领队">
</head>

<body>

<script language="JavaScript">
<!--//

function conv(param,param1)
{
  var fm = document.submitChooseLeader;
  var field = document.submitChooseLeader.nameKey;
  var isChecked=-1;
  if(null!=field.length)
 {
   for(var i = 0; i< field.length; i++)
  {
    if(field[i].checked)
    {
      isChecked = i;
      break;
    } 
  }
  }else if(field.checked)
 {
   isChecked = 0;
 }
       
   if(isChecked == -1)
     {
        alert("你没有选择要并团的人,请选择!");
        return;
     }

  	fm.action = "<s:url action='submitChooseLeader' namespace='/operator'/>";
  	fm.submit();  	
}

function _getlist(type)
{
  var frm = document.submitChooseLeader;
  frm.action = "<s:url action='chooseLeader' namespace='/operator' />"
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}

//-->
</script>
<s:form action="submitChooseLeader" namespace="/operator" method="post" theme="simple">

    <table border="0" style="width: 100%">
        <tr>
          <td class="lstidx">NO.</td>
          <td class="lstidx">选择</td>
          <td class="lstidx">姓名</td>
          <td class="lstidx">性别</td>
          <td class="lstidx">出生地</td>
          <td class="lstidx">职业</td>
          <td class="lstidx">护照类型</td>
          <td class="lstidx">护照号码</td>
          <td class="lstidx">办证地</td>
          <td class="lstidx">护照有效期</td>
          <td class="lstidx">手机号码</td>
          <td class="lstidx">电话号码</td>
          <td class="lstidx">领队号码</td>
          <td class="lstidx">备注</td>
        </tr>

	    <s:iterator value="leaderList" status="rowcounter">
	    <s:if test="#rowcounter.count > fromRecord">
        <s:if test="#rowcounter.count <= toRecord">
        <tr>
          <td class="cdata"><s:property value="#rowcounter.count"/></td>
          <td class="cdata">
            <s:checkbox id="nameKey%{#rowcounter.count-1}" name="nameKey" fieldValue="%{userId}"/>
         </td>
          <td class="data"><a href="#" title="显示客户明细"><s:property value="userName"/></a>&nbsp;</td>
          <td class="data">
            <s:property value="sex"/>&nbsp;
          </td>
          <td class="data">
            <s:property value="birthplaceName"/>&nbsp;
          </td>
          <td class="data"><s:property value="vocation" />&nbsp;</td>
          <td class="data"><s:property value="passportType" />&nbsp;</td>
          <td class="data"><s:property value="passportNo" />&nbsp;</td>
          <td class="data">
            <s:property value="passportPlaceName"/>&nbsp;
          </td>
          <td class="cdata"><s:date name="passportDate" format="yyyy-MM-dd"/></td> 
          <td class="data"><s:property value="mobile" />&nbsp;</td>
          <td class="data"><s:property value="phone" />&nbsp;</td>        
          <td class="data"><s:date name="leadCard"/>&nbsp;</td>
          <td class="data"><s:property value="remarks"/>&nbsp;</td>
        </tr>
        </s:if>
        </s:if>
        </s:iterator>
        <tr>
        <td colspan="14">
	
	<s:if test="leaderList.isEmpty() == false ">
  <%@ include file="/includes/PagerTable.jsp" %>
	</s:if>

  </td>
  </tr>
  <tr>
    <td colspan="14" align="right">
    <input type="button" value="确定" onclick="javascript:conv(<s:property value="leaderList.size()" />,<s:property value="currentPage" />);">
    <input type="button" value="返回" onClick="javascript:history.go(-1);">
  </td>
  </tr>
</table>
</s:form>


</body>
</html>

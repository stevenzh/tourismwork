<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>航班信息</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="航班信息">
<s:head/>
</head>

<body>
<script language="javascript">
function SubmitForm(param, target)
{
  var fm = document.listFlight;
  fm.flightNo.value=target;
  if (param =='modify') {
    fm.action = "<s:url action='editFlight' namespace='/resource'/>"
  } else if (param == 'info') {
    fm.action = "<s:url action='FlightDetail' namespace='/resource'/>"
  } else if (param == 'del') {
    fm.action = "<s:url action='deleteFlight' namespace='/resource'/>"
    if (confirm("确定要删除此分类吗？") == false) {
      return;
    }
  }
  fm.submit();
}

function _getlist(type)
{
  var frm = document.listFlight;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}

//-->
</script>
<s:form action="listFlight" namespace="/resource" method="post" theme="simple">
  <s:hidden name="flightNo"></s:hidden>
  <table style="width: 100%">
    <tr>
      <td nowrap>航空公司：</td>
      <td colspan="2">
      <s:select name="kenAirways"
                list="airways"
                listKey="code"
                listValue="name"
                headerKey=""
                headerValue="全部">
      </s:select>
      </td>
      </tr>
      <tr>
      <td>出发机场：</td>
      <td colspan="2">
        <s:select name="kenLvAirport"
                  list="airports"
                  listKey="code"
                  listValue="name"
                  headerKey=""
                  headerValue="全部">
        </s:select>
      </td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>抵达机场： </td>
      <td>
      <s:select name="kenGoAirport"
               list="airports"
               listKey="code"
               listValue="name"
               headerKey=""
               headerValue="全部">
      </s:select>
      </td>
      <td><s:submit value="%{getText('common.forms.search')}"></s:submit></td>
    </tr>
  </table>
  <br>
  <table border="0" style="width: 100%">
    <tr>
      <td class="lstidx">No.</td>
      <td class="lstidx">航班号</td>
      <td class="lstidx">航空公司</td>
      <td class="lstidx">出发机场</td>
      <td class="lstidx">抵达机场</td>
      <td class="lstidx">编码</td>
      <td colspan="2" class="lstidx">操作</td>
    </tr>
    <s:iterator value="flightList" status="rowcounter">
    <s:if test="#rowcounter.count > fromRecord">
    <s:if test="#rowcounter.count <= toRecord">
      <tr>
        <td class="cdata"><s:property value="#rowcounter.count"/> </td>
        <td class="cdata"><a href="#" onClick="javascript:SubmitForm('info','<s:property value="recNo"/>')"><s:property value="flightNo"/></a></td>
        <td class="cdata"><s:property value="airways.name"/></td>
        <td class="cdata"><s:property value="leavingAirport"/></td>
        <td class="cdata"><s:property value="goingAirport"/></td>
        <td class="cdata"><s:property value="recNo"/></td>
        <td align="center"><a href="#" onClick="javascript:SubmitForm('modify','<s:property value="recNo"/>')">修改</a>
        <a href="#" onClick="javascript:SubmitForm('del','<s:property value="recNo"/>')">删除</a></td>
      </tr>
     </s:if>
     </s:if>
    </s:iterator>
    <tr>
      <td colspan="8">
        <s:if test="flightList.isEmpty() == false">
        <%@ include file="/includes/PagerTable.jsp" %>
        </s:if>
      </td>
    </tr>
  </table>
  <center><input type="button" class="button" value="<s:text name="common.forms.add" />" onclick="javascript:SubmitForm('modify','')"></center>
</s:form>

</body>
</html>



<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>航班信息</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="航班信息">
</head>
<body>
<script language="JavaScript">
<!--//
function back()
{
  var fm=document.FlightDetail;
  fm.action = "<s:url action='showFlight' namespace='/resource'/>"
  fm.submit();
}

function del()
{
  var fm=document.FlightDetail;
  fm.action = "<s:url action='FlightDelete' namespace='/resource'/>"
  if (confirm("确定要删除此分类吗？") == false) {
      return;}
  fm.submit();
}
</script>

<s:form action="FlightDetail" namespace="/resource" method="POST">
  <table>
    <tr>
      <td>
      <table>
        <tr>
          <td class="idx">编码：&nbsp;</td>
          <td class="cdata"><s:property value="flight.recNo"/></td>
        </tr>
        <tr>
          <td class="idx">航班号：&nbsp;</td>
          <td class="cdata"><s:property value="flight.flightNo"/></td>
        </tr>
        <tr>
          <td class="idx">航空公司：&nbsp;</td>
          <td class="cdata"><s:property value="flight.airline"/></td>
        </tr>
      </table>
      </td>
    </tr>
  </table>
  
  <div align="center">
  <input type="button" value="删 除" onclick="javaScript:del()">
  <input type="button" value="返 回" onclick="javaScript:back()">
  </div>
</s:form>
</body>
</html>

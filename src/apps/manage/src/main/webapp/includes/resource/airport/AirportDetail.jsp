<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>机场三字码分类表</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="机场三字码分类表">
</head>

<body>
<script language="JavaScript">
<!--//
function back()
{
  var fm=document.AirportDetail;
  fm.action = "<s:url action='listAirport' namespace='/resource'/>"
  fm.submit();
}

function del()
{
  var fm=document.AirportDetail;
  fm.action = "<s:url action='deleteAirport' namespace='/resource'/>"
  if (confirm("确定要删除此分类吗？") == false) {
      return;}
  fm.submit();
}
</script>

<s:form action="AirportDetail" namespace="/resource" method="POST">
  <s:hidden name="airport.code" value="%{aireport.code}" />
  <table cellpadding="0" cellspacing="0">
    <tr>
      <td>
      <table>
        <tr>
          <td class="idx">代码：&nbsp;</td>
          <td class="cdata"><s:property value="airport.code"/></td>
        </tr>
        <tr>
          <td class="idx">名称(中文)：&nbsp;</td>
          <td class="cdata"><s:property value="airport.name"/></td>
        </tr>
        <tr>
          <td class="idx">名称(英文)：&nbsp;</td>
          <td class="cdata"><s:property value="airport.enName"/></td>
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

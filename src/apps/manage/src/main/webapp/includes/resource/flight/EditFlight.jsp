<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改航班信息</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="修改航班信息">
</head>

<body>
<script language="JavaScript">
<!--//
function cancel()
{
  var fm = document.submitFlight;
  fm.action = "<s:url action='listFlight' namespace='/resource'/>"
  fm.submit();
}
//-->
</script>

<s:form action="submitFlight" method="POST" namespace="/resource" theme="simple">
  <s:hidden name="kenAirways"></s:hidden>
  <s:hidden name="kenLvAirport"></s:hidden>
  <s:hidden name="kenGoAirport"></s:hidden>
  <table cellpadding="2">   
    <tr>
      <td class="idx">编码：</td>
      <td class="data"><s:textfield name="flight.recNo" readonly="true" size="3" 
          maxlength="3"></s:textfield><font color="red">*</font></td>
    </tr>
    <tr>
      <td class="idx">航班号：</td>
      <td class="data"><s:textfield name="flight.flightNo" size="40" 
          maxlength="40"></s:textfield><font color="red">*</font></td>
    </tr>
    <tr>
      <td class="idx">航空公司：</td>
      <td class="data"><s:textfield name="flight.airline" size="80" 
          maxlength="80"></s:textfield></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;<font color="red">*</font>&nbsp;为必填写项目！</td>
    </tr>
  </table>
  <br>
  <table border="0" cellpadding="2" cellspacing="0" width="100%">
    <tr>
      <td align="center">
      <s:submit value="提 交"></s:submit>
      <input type="button" value="返 回" onclick="javascript:cancel()"></td>
    </tr>
  </table>
</s:form>

</body>
</html>

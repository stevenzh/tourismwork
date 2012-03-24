<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>航空公司</title>
<meta name="menu" content="SettingMenu"/>
<s:head/>
</head>
<body>
<script language="JavaScript">
function cancel(){
  var fm = document.submitAirways;
  fm.action = "<s:url action='listAirways' namespace='/resource'/>"
  fm.submit();
}

</script>

<table border="0" cellpadding="2" cellspacing="0" width="100%">
  <tr>
    <td class="header">
    <s:if test='opKey eq "A"'>添加航空公司</s:if>
    <s:else>修改航空公司</s:else>
    </td>
  </tr>
</table>

<s:form action="submitAirways" method="POST" namespace="/resource" theme="simple">
  <s:hidden name="airways.airwaysId"></s:hidden>
  <table cellpadding="0" cellspacing="2">
    <tr>
      <td class="idx">代码:</td>
      <td class="data">
      <s:textfield name="airways.code" size="2" maxlength="2"></s:textfield>
      </td>
    </tr>
    <tr>
      <td class="idx">名称:</td>
      <td class="data">
      <s:textfield name="airways.name" size="20" maxlength="20"></s:textfield>
      </td>
    </tr>
    <tr>
      <td class="idx">全名:</td>
      <td class="data">
      <s:textfield name="airways.fullname" size="40" maxlength="40"></s:textfield>
      </td>
    </tr>
    <tr>
      <td class="idx">联系人:</td>
      <td class="data">
      <s:textfield name="airways.contact" size="20" maxlength="20"></s:textfield>
      </td>
    </tr>
    <tr>
      <td class="idx">联系电话:</td>
      <td class="data">
      <s:textfield name="airways.phone" size="20" maxlength="20"></s:textfield>
      </td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;<font color="red">*</font>&nbsp;为必填写项目！</td>
    </tr>
    <tr>
      <td colspan="2">
      <div align="center">
      <s:submit value="保存"></s:submit>&nbsp;&nbsp;
      <s:submit value="返 回" onclick="javascript:cancel()"></s:submit>
      </div>
      </td>
    </tr>
  </table>
</s:form>

</body>
</html>

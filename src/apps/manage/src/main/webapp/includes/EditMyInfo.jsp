<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>用户修改</title>
<meta name="menu" content="MainMenu"/>
<meta name="heading" content="用户修改">
</head>

<body>
<s:form action="submitMyInfo" namespace="/manage" method="POST" theme="simple">
  <s:hidden name="employee.userId"></s:hidden>
  <table border="0" style="width: 100%">
    <tr>
      <td class="idx" width="100">用户代码：</td>
      <td class="data"><s:property value="employee.uid"/></td>
    </tr>
    <tr>
      <td class="idx">用户姓名：</td>
      <td class="data"><s:property value="employee.userName"/></td>
    </tr>
    <tr>
      <td class="idx">旧密码&nbsp;<font color="#ff0000">*</font>：</td>
      <td class="data"><s:password name="oldPwd" showPassword="true" maxlength="20"/></td>
    </tr>
    <tr>
      <td class="idx">新密码：</td>
      <td class="data"><s:password name="newPwd" showPassword="true" maxlength="20"/></td>
    </tr>
    <tr>
      <td class="idx">确认密码：</td>
      <td class="data"><s:password name="confirmPwd" showPassword="true" maxlength="20"/></td>
    </tr>
    <tr>
      <td class="idx">电&nbsp;&nbsp;&nbsp;&nbsp;话：</td>
      <td class="data"><s:textfield name="employee.phone" /></td>
    </tr>
    <tr>
      <td class="idx">传&nbsp;&nbsp;&nbsp;&nbsp;真：</td>
      <td class="data"><s:textfield name="employee.fax" /></td>
    </tr>
    <tr>
      <td align="center"><s:submit value="确定"></s:submit></td>
    </tr>
  </table>
</s:form>
</body>
</html>

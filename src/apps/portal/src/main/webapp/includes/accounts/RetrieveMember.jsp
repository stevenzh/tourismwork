<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title>索取密码</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:head />
</head>

<body>

<s:form action="submitRetrieveMember" namespace="/" method="POST" theme="simple">
  <table align="center" border="0" width="280">
    <tr>
      <td>
      <table align="center" border="0" cellpadding="4" cellspacing="2"
        style="width: 100%">
        <tr>
          <td colspan="2"><b style="font-size: 10pt">手机索取密码</b></td>
        </tr>
        <tr>
          <td>您的手机:</td>
          <td><s:textfield name="mobileNo"
                           size="20"
                          >
          </s:textfield></td>
        </tr>
        <tr>
          <td colspan="2"><s:submit value="取回密码">
          </s:submit></td>
        </tr>
        <tr>
          <td colspan="2">注意：密码将通过短信免费发送到您的手机每天只能查询1次。</td>
        </tr>

      </table>
      </td>
    </tr>
  </table>
</s:form>

</body>
</html>


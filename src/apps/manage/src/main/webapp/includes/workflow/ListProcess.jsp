<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>各国签证须知</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>

<s:form action="deployProcess" namespace="/" theme="simple" method="POST">
  <table>
    <tr>
      <td>
      <s:textfield name="srcClasspath"></s:textfield>
      <s:submit value="发布新流程"/>
      </td>
    </tr>
  </table>
  <table width="760" border="0" cellpadding="2" cellspacing="1">
    <tr bgcolor="#FFFFFF">
      <th align="center">Name</th>
      <th align="center">Key</th>
      <th align="center">Version</th>
      <th align="center">Description</th>
      <th align="center">DeploymentId</th>
    </tr>
  
    <s:iterator value="definitions">
      <tr bgcolor="#FFFFFF">
        <td><s:property value="name"/>&nbsp;</td>
        <td><s:property value="key"/>&nbsp;</td>
        <td><s:property value="version"/>&nbsp;</td>
        <td><s:property value="description"/>&nbsp;</td>
        <td><s:property value="deploymentId"/>&nbsp;</td>
      </tr>
    </s:iterator>
  </table>

</s:form>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<s:url value='/stylesheet/layout.css' encode='false' includeParams='none'/>" type="text/css" media="all">
<title>Search</title>
</head>
<body>

<s:form action="CbIndexAction" namespace="/search" method="post" theme="simple">
  <table width="57%" border="0" align="center">
    <tr>
      <td><s:textfield name="routeNo" value="07090273"></s:textfield>
      </td>
      <td>
        <s:submit value="索引"></s:submit>
        <s:submit action="AllIndexAction" value="全部索引"></s:submit>
        <s:submit action="RegionIndexAction" value="目的地索引"></s:submit>
      </td>
    </tr>
  </table>
</s:form>

</body>
</html>
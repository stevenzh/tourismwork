<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>错误页面</title>
<META HTTP-EQUIV="Content-type" CONTENT="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/stylesheet/global.css" type="text/css" media="all">
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/main.js"></script>
</head>
<body>
<table border="1" style="width: 100%">
  <tr valign="top">
    <td>
    <table border="0" style="width: 100%">
      <tr>
        <th valign="middle" align="center">错误信息</th>
      </tr>
      <tr>
        <td>无权使用，或重新登陆&nbsp;</td>
      </tr>
    </table>
    </td>
  </tr>
</table>

<div align="center">
<button type="button" NAME="btnGo" style="width: 160px;" OnClick="javascript:window.history.back();">取消</button>
</div>
</body>
</html>


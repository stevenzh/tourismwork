<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>错误页面</title>
<META HTTP-EQUIV="Content-type" CONTENT="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/stylesheet/global.css" type="text/css" media="all">
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/main.js"></script>
<script type="text/javascript">

function abc()
{
  parent.parent.window.location = "<%=request.getContextPath() %>/LoginManage.do";
}
</script>
</head>
<body>
<table border="1" width="100%">
  <tr valign="top">
    <td>
    <table border="0" width="100%">
      <tr>
        <th valign="middle" align="center">错误信息</th>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="18" align="center"></td>
      </tr>
    </table>
    </td>
  </tr>
</table>
<div align="center">
<button type="button" NAME="btnGo" style="width: 160px;" onclick="javascript:abc();">重新登陆</button>
</div>
</body>
</html>


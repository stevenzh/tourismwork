<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加FIT线路分类</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="添加FIT线路分类">
</head>
<body>
<script language="JavaScript">
function cancel(){
  var fm = document.FITTypeAddSubmit;
  fm.action = "<s:url action='FITType' namespace='/product'/>"
  fm.submit();
}
</script>
<table cellpadding="2" cellspacing="2">
  <s:form action="FITTypeAddSubmit" method="POST" namespace="/product" theme="simple">
    <tr>
      <td class="idx">代码：</td>
      <td class="data"><s:textfield name="fitType.code" size="2" 
          maxlength="2"></s:textfield><font color="red">*</font></td>
    </tr>
    <tr>
      <td class="idx">名称：</td>
      <td class="data"><s:textfield name="fitType.cnName" size="40" 
          maxlength="40"></s:textfield><font color="red">*</font></td>
    </tr>
    <tr>
      <td class="idx">图片文件地址：</td>
      <td class="data"><s:textfield name="fitType.mapFile" size="50" 
          maxlength="50"></s:textfield></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;<font color="red">*</font>&nbsp;为必填写项目!</td>
    </tr>
    <tr>
      <td colspan="2">
      <div align="center">
      <s:submit value="添 加"></s:submit>&nbsp;&nbsp;
      <s:submit value="返 回" onclick="javascript:cancel()"></s:submit>
      </div>
      </td>
    </tr>
  </s:form>
</table>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改FIT线路分类</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="修改FIT线路分类">
</head>
<body>
<script language="JavaScript">
<!--//
function cancel(){
  var fm = document.FITTypeChangeSubmit;
  fm.action = "<s:url action='FITType' namespace='/product'/>"
  fm.submit();
}
//-->
</script>

<s:form action="FITTypeChangeSubmit" method="POST" namespace="/product" theme="simple">
<s:hidden name="code"></s:hidden>
  <table cellpadding="2">
    <tr>
      <td class="idx">代码：</td>
      <td class="data">
        <s:textfield name="fitType.code" readonly="true" size="2"
           maxlength="2"></s:textfield><font color="red">*</font>
      </td>
    </tr>
    <tr>
      <td class="idx">名称：</td>
      <td class="data"><s:textfield name="fitType.cnName" size="40" 
          maxlength="40"></s:textfield><font color="red">*</font></td>
    </tr>
    <tr>
      <td class="idx" nowrap="nowrap">图文件地址：</td>
      <td class="data"><s:textfield name="fitType.mapFile" size="50" 
          maxlength="50"></s:textfield></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;<font color="red">*</font>&nbsp;为必填写项目！</td>
    </tr>
  </table>
  <table border="0" style="width: 100%">
    <tr>
      <td align="center">
       <s:submit value="提 交"></s:submit>
      <input type="button" value="返 回" onclick="javascript:cancel()"></td>
    </tr>
  </table>
</s:form>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FIT线路分类</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="FIT线路分类">
</head>
<body>
<script language="JavaScript">
<!--//
function SubmitForm(param, target)
{
  var fm = document.FITType;
  fm.code.value=target;
  if (param =='info') {
    fm.action = "<s:url action='FITTypeDetail' namespace='/product'/>"
  } else if (param == 'modify') {
    fm.action = "<s:url action='FITTypeChange' namespace='/product'/>"
  } else if (param == 'del') {
    fm.action = "<s:url action='FITTypeDelete' namespace='/product'/>"
    if (confirm("确定要删除此分类吗？") == false) {
      return;
    }
  }
  fm.submit();
}

function Add()
{
  var fm = document.FITType;
  fm.action = "<s:url action='FITTypeAdd' namespace='/product'/>"
  fm.submit();
}
//-->
</script>

<s:form action="FITType" namespace="/product" method="POST" >
<s:hidden name="code"></s:hidden>
<table style="width: 100%">
  <tr>
    <td class="lstidx">No.</td>
    <td class="lstidx">代码</td>
    <td class="lstidx">中文名称</td>
    <td colspan="2" class="lstidx">操作</td>
  </tr>
  <s:iterator value="fitTypeList" status="rowcounter">
    <tr>
      <td class="cdata"><s:property value="#rowcounter.count"/></td>
      <td class="cdata"><s:property value="code"/></td>
      <td class="data"><a href="#" onClick="javascript:SubmitForm('info','<s:property value="code"/>')"><s:property value="cnName"/></a></td>
      <td align="center"><a href="#" onClick="javascript:SubmitForm('modify','<s:property value="code"/>')">修改</a>
          <a href="#" onClick="javascript:SubmitForm('del','<s:property value="code"/>')">删除</a></td>
    </tr>
  </s:iterator>
</table>
<center><input type="button" class="button" value="添 加"  onclick="javascript:Add()"></center>
</s:form>
</body>
</html>

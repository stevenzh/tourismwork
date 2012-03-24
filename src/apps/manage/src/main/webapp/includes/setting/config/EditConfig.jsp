<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改系统设置</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="修改系统设置">
</head>

<body>
<script type="text/javascript">
<!--//

function back()
{
  var fm=document.updateConfig;
  fm.action = "<s:url action='listConfig' namespace='/setting'/>"
  fm.submit();
}

function SubmitDeleteFlight(param, target)
{
  var fm = document.updateConfig;
  fm.itemId.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='deleteConfig' namespace='/setting'/>"
  }
  fm.submit();
}
//-->
</script>
<s:form action="updateConfig" namespace="/setting" method="POST" theme="simple">
  <s:hidden name="listName"></s:hidden>
  <s:hidden name="config.id" />
  <s:hidden name="config.storeRange" />
  <table>
    <tr>
      <td class="idx">名称:</td>
      <td class="data"><s:textfield name="config.name" /></td>
    </tr>
    <tr>
      <td class="idx">类别:</td>
      <td class="data"><s:textfield name="config.category" /></td>
    </tr>
    <tr>
      <td class="idx">类型:</td>
      <td class="data"><s:textfield name="config.type" /></td>
    </tr>
    <tr>
      <td class="idx">说明:</td>
      <td class="data"><s:textarea name="config.value" cols="50" rows="6" /></td>
    </tr>
    <tr>
    <td colspan="2">
      <s:submit value="修改"></s:submit>&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="button" value="返回" onclick="javascript:back()">
    </td>
    </tr>
  </table>

</s:form>

</body>
</html>

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
  var fm=document.updateShortcut;
  fm.action = "<s:url action='searchShortcut' namespace='/setting'/>"
  fm.submit();
}

function SubmitDeleteFlight(param, target)
{
  var fm = document.updateShortcut;
  fm.itemId.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='deleteShortcut' namespace='/setting'/>"
  }
  fm.submit();
}
//-->
</script>
<s:form action="updateShortcut" namespace="/setting" method="POST" theme="simple">
  <s:hidden name="kenModuleName"></s:hidden>
  <s:hidden name="shortcut.id" />
  <table>
    <tr>
      <td class="idx">名称:</td>
      <td class="data"><s:textfield name="shortcut.displayName" /></td>
    </tr>
    <tr>
      <td class="idx">类别:</td>
      <td class="data">
        <s:select name="shortcut.moduleName"
                  list="moduleList"
                  listKey="moduleName"
                  listValue="moduleName">
      </s:select>
      </td>
    </tr>
    <tr>
      <td class="idx">URL路径:</td>
      <td class="data"><s:textfield name="shortcut.relativePath" /></td>
    </tr>
    <tr>
      <td class="idx">是否可用:</td>
      <td class="data"><s:checkbox name="shortcut.shortcutEnabled"></s:checkbox></td>
    </tr>
    <tr>
      <td class="idx">图片:</td>
      <td class="data"><s:textfield name="shortcut.imageName" /></td>
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

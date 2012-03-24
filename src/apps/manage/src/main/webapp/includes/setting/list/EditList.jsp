<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统列表</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="系统列表">
</head>

<body>
<script type="text/javascript">
<!--//

function back()
{
  var fm=document.updateList;
  fm.action = "<s:url action='searchList' namespace='/setting'/>"
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
    fm.action = "<s:url action='delList' namespace='/setting'/>"
  }
  fm.submit();
}
//-->
</script>

<s:form action="updateList" namespace="/setting" method="POST" theme="simple">
  <s:hidden name="listName"></s:hidden>
  <s:hidden name="list.entryId"></s:hidden>
  <table>
    <s:if test='list.listName == ""'>
    <tr>
      <td class="idx">类型:</td>
      <td class="data"><s:textfield name="list.listName" /></td>
    </tr>
    </s:if>
    <s:else>
    <s:hidden name="list.listName"></s:hidden>    
    </s:else>
    <tr>
      <td class="idx">名称:</td>
      <td class="data"><s:textfield name="list.text" /></td>
    </tr>
    <tr>
      <td class="idx">顺序:</td>
      <td class="data"><s:textfield name="list.sortOrder" /></td>
    </tr>
    <tr>
      <td class="idx">值:</td>
      <td class="data"><s:textfield name="list.value" /></td>
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

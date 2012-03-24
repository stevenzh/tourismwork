<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色列表</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="角色列表">
</head>
<body>

<script type="text/javascript"><!--
//
function SubmitForm(param, target)
{
  var fm = document.listRole;
  fm.roleId.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='delRole' namespace='/setting'/>"      
  }
  if(param =='modify'){
    fm.action = "<s:url action='editRole' namespace='/setting'/>"
  }
  fm.submit();
}

function add(){
  var fm = document.listRole;
  fm.action = "<s:url action='editRole' namespace='/setting'/>"
  fm.submit();
}

//-->
</script>

<s:form action="listRole" namespace="/setting" method="POST" theme="simple" >
  <s:hidden name="roleId"></s:hidden>
  <table style="width: 100%">
    <tr>
      <td class="lstidx">角色KEY</td>
      <td class="lstidx">角色名称</td>
      <td class="lstidx">描述</td>
      <td class="lstidx">状态</td>
      <td class="lstidx">操作</td>
    </tr>

    <s:iterator value="roleList" status="rowcounter">
        <tr>
          <td class="data"><s:property value="roleCode" />&nbsp;</td>
          <td class="data"><s:property value="roleName" />&nbsp;</td>
          <td class="data"><s:property value="roleDesc" />&nbsp;</td>
          <td class="cdata"><s:if test='isActive = 1'>可用</s:if>&nbsp;</td>
          <td class="cdata">
          <a href="#" onClick="javascript:SubmitForm('modify','<s:property value="roleId" />');">修改</a>&nbsp;&nbsp;
          <a href="#" onClick="javascript:SubmitForm('delete','<s:property value="roleId" />');">删除</a>
          </td>
        </tr>
    </s:iterator>

  </table>
</s:form>

</body>
</html>

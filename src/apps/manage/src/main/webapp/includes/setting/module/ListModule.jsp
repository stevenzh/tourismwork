<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模块定义</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="模块定义">
</head>

<body>
<script type="text/javascript">
<!--//
function SubmitForm(param, target)
{
  var fm = document.editModule;
  fm.moduleId.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='delModule' namespace='/setting'/>"      
  }
  if(param =='modify'){
    fm.action = "<s:url action='editModule' namespace='/setting'/>"
  }
  fm.submit();
}

function add(){
  var fm = document.editModule;
  fm.action = "<s:url action='editModule' namespace='/setting'/>"
  fm.submit();
}
//-->
</script>
<s:form action="editModule" namespace="/setting" method="POST" theme="simple" >
  <s:hidden name="moduleId"></s:hidden>
  <table style="width: 100%">
    <tr>
      <td class="lstidx">名称</td>
      <td class="lstidx">说明</td>
      <!-- 
      <td class="lstidx">版本</td>
       -->
      <td class="lstidx">权重</td>
      <td class="lstidx">状态</td>
      <td class="lstidx" width="80">操作</td>
    </tr>

    <s:iterator value="moduleList" status="rowcounter">
        <tr>
          <td class="data"><s:property value="moduleName" /><s:hidden value="moduleId"></s:hidden> </td>
          <td class="data"><s:property value="description" /></td>
          <!-- 
          <td class="data">&nbsp;</td> -->
          <td class="data"><s:property value="sortOrder" />&nbsp;</td>
          <td class="data"><s:property value="isActive" />&nbsp;</td>
          <td class="data">
          <a href="#" onClick="javascript:SubmitForm('modify','<s:property value="moduleId" />');">修改</a>&nbsp;&nbsp;
          <a href="#" onClick="javascript:SubmitForm('delete','<s:property value="moduleId" />');">删除</a>
          </td>
        </tr>
    </s:iterator>

  </table>
</s:form>

</body>
</html>

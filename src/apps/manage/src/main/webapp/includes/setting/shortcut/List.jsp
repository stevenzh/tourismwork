<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>快捷菜单</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="快捷菜单">
</head>

<body>
<script type="text/javascript">
<!--//
function SubmitForm(param, target)
{
  var fm = document.searchShortcut;
  fm.shortcutId.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='deleteShortcut' namespace='/setting'/>"
  }
  if(param=='modify')
  {
    fm.action = "<s:url action='editShortcut' namespace='/setting'/>"
  }
  fm.submit();
}

function _getlist(type)
{
  var frm = document.searchShortcut;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}
//-->
</script>
<s:form action="searchShortcut" namespace="/setting" method="POST" theme="simple">
  <s:hidden name="shortcutId"></s:hidden>
  <table style="width: 100%">
    <tr>
      <td colspan="6" align="right">
      <s:submit action="editShortcut" value="添加新的快捷菜单"></s:submit>
      <s:select name="kenModuleName"
                list="moduleList"
                listKey="moduleName"
                listValue="moduleName"
                emptyOption="true">
      </s:select><s:submit value="搜索"></s:submit>
      </td>
    </tr>
    <tr>
      <td class="lstidx">NO.</td>
      <td class="lstidx">名称</td>
      <td class="lstidx">模块名</td>
      <td class="lstidx">路径</td>
      <td class="lstidx">可用</td>
      <td class="lstidx">操作</td>
    </tr>

    <s:iterator value="shortcutList" status="rowcounter">
      <s:if test="#rowcounter.count > fromRecord">
        <s:if test="#rowcounter.count <= toRecord">
          <tr>
            <td class="cdata"><s:property value="#rowcounter.count" />&nbsp;</td>
            <td class="data"><s:property value="displayName" />&nbsp;</td>
            <td class="cdata"><s:property value="moduleName" />&nbsp;</td>
            <td class="data"><s:property value="relativePath" />&nbsp;</td>
            <td class="data"><s:property value="shortcutEnabled" />&nbsp;</td>
            <td class="cdata"><a href="#" onClick="javascript:SubmitForm('modify','<s:property value="id" />')">修改</a>&nbsp;&nbsp;
            <a href="#" onClick="javascript:SubmitForm('delete','<s:property value="id" />')">取消</a></td>
          </tr>
        </s:if>
      </s:if>
    </s:iterator>

    <tr>
      <td colspan="6">
        <s:if test="shortcutList.isEmpty() == false">
        <%@ include file="/includes/PagerTable.jsp" %>
        </s:if>
      </td>
    </tr>

  </table>
</s:form>
</body>
</html>

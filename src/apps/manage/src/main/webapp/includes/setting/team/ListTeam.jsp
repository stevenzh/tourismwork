<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>团队列表</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="团队列表">
</head>

<body>
<script type="text/javascript">
<!--//
function SubmitForm(param, target)
{
  var fm = document.listTeam;
  $('#teamId').val(target);
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='delTeam' namespace='/setting'/>"
  }
  if(param=='modify')
  {
    fm.action = "<s:url action='editTeam' namespace='/setting'/>"
  }
  fm.submit();
}

function _getlist(type)
{
  var frm = document.listTeam;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}
//-->
</script>

<s:form action="listTeam" namespace="/setting" method="POST" theme="simple">
  <s:hidden id="teamId" name="team.teamId" ></s:hidden>
  <table style="width: 100%">
    <tr>
      <td colspan="6" align="right"><s:submit action="editTeam" value="添加新的工作组"></s:submit></td>
    </tr>
    <tr>
      <td class="lstidx">NO.</td>
      <td class="lstidx">ID</td>
      <td class="lstidx">名称</td>
      <td class="lstidx">类别</td>
      <td class="lstidx">状态</td>
      <td class="lstidx">操作</td>
    </tr>

    <s:iterator value="teamList" status="rowcounter">
    <s:if test='active == true'>
    <tr>
      <td class="cdata"><s:property value="%{fromRecord + #rowcounter.count}"/>&nbsp;</td>
      <td class="data"><s:property value="id" />&nbsp;</td>
      <td class="data"><s:property value="name" />&nbsp;</td>
      <td class="cdata">
        <s:select name="type"
                  list="#{0:'普通',1:'产品',2:'销售',3:'计调',4:'财务'}"
                  listKey="key"
                  listValue="value"
                  disabled="true">
        </s:select>
      </td>
      <td class="data">Active&nbsp;</td>
      <td class="cdata"><a href="#" onClick="javascript:SubmitForm('modify','<s:property value="teamId" />')">修改</a>&nbsp;&nbsp;
      <a href="#" onClick="javascript:SubmitForm('delete','<s:property value="teamId" />')">取消</a></td>
    </tr>
    </s:if>
    <s:else>
    <tr bgcolor="#C7ACBE">
      <td align="center"><s:property value="%{fromRecord + #rowcounter.count}"/>&nbsp;</td>
      <td><s:property value="id" />&nbsp;</td>
      <td><s:property value="name" />&nbsp;</td>
      <td align="center">
        <s:select name="type"
                  list="#{0:'普通',1:'产品',2:'销售',3:'计调',4:'财务'}"
                  listKey="key"
                  listValue="value"
                  disabled="true">
        </s:select>
      </td>
      <td>Terminated&nbsp;</td>
      <td align="center"><a href="#" onClick="javascript:SubmitForm('modify','<s:property value="teamId" />')">修改</a>&nbsp;&nbsp;
      <a href="#" onClick="javascript:SubmitForm('delete','<s:property value="teamId" />')">取消</a></td>
    </tr>
    </s:else>
    </s:iterator>

    <tr>
      <td colspan="6">
        <s:if test="teamList.isEmpty() == false">
        <%@ include file="/includes/PagerTable.jsp" %>
        </s:if>
      </td>
    </tr>

  </table>
</s:form>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户一览表</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="用户一览表">
</head>
<body>

<script type="text/javascript">
<!--//
function SubmitForm(param, target)
{
  var fm = document.listUser;
  fm.userId.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='deleteUser' namespace='/setting'/>"      
  }
  if(param =='modify'){
    fm.action = "<s:url action='editUser' namespace='/setting'/>"
  }
  fm.submit();
}

function add(){
  var fm = document.listUser;
  fm.userId.value=0;
  fm.action = "<s:url action='editUser' namespace='/setting'/>"
  fm.submit();
}

function _getlist(type)
{
  var frm = document.listUser;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}
//-->
</script>
<s:form action="listUser" namespace="/setting" method="POST" theme="simple" >
  <s:hidden id="userId" name="userId"></s:hidden>
  <table>
    <tr>
      <td class="idx">部门:</td>
      <td class="idx">
        <s:select list="departmentList"
                  name="groupId"
                  listKey="groupId"
                  listValue="name"
                  headerKey=""
                  headerValue="所有">
        </s:select>
      </td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td class="idx">姓名：</td>
      <td class="idx"><s:textfield name="kenUserName" /></td>
      <td><s:submit value="%{getText('common.forms.search')}"></s:submit>
      <input type="button" class="button" value="<s:text name="common.forms.add" />" onclick="javascript:add()">
      </td>
    </tr>
  </table>
  <table style="width: 100%">
    <tr>
      <td class="lstidx">No.</td>
      <td class="lstidx">用户代码</td>
      <td class="lstidx">姓名</td>
      <td class="lstidx">部门</td>
      <td class="lstidx">管理员</td>
      <td class="lstidx">状态</td>
      <td class="lstidx">操作</td>
    </tr>

    <s:iterator value="employeeList" status="rowcounter">
      <s:if test="#rowcounter.count > fromRecord">
      <s:if test="#rowcounter.count <= toRecord">
      <s:if test='isActive == true'>
        <tr>
          <td class="cdata"><s:property value="#rowcounter.count" /></td>
          <td class="data"><s:property value="uid" /></td>
          <td class="data"><s:property value="userName" /></td>
          <td class="data"><s:property value="group.name" /></td>
          <td class="cdata"><s:property value="isAdmin" /></td>
          <td class="cdata">Active</td>
          <td class="cdata">
          <a href="#" onClick="javascript:SubmitForm('modify','<s:property value="userId" />');">修改</a>&nbsp;&nbsp;
          <a href="#" onClick="javascript:SubmitForm('delete','<s:property value="userId" />');">删除</a>
          </td>
        </tr>
      </s:if>
      <s:else>
        <tr bgcolor="#C7ACBE">
          <td align="center"><s:property value="#rowcounter.count" /></td>
          <td><s:property value="uid" /></td>
          <td><s:property value="userName" /></td>
          <td><s:property value="group.name" /></td>
          <td align="center"><s:property value="isAdmin" /></td>
          <td align="center">Terminated</td>
          <td align="center">
          <a href="#" onClick="javascript:SubmitForm('modify','<s:property value="userId" />');">修改</a>
          </td>
        </tr>
      </s:else>
      </s:if>
      </s:if>
    </s:iterator>

    <tr>
      <td colspan="7">
        <s:if test="employeeList.isEmpty() == false">
        <%@ include file="/includes/PagerTable.jsp" %>
        </s:if>
      </td>
    </tr>
  </table>
</s:form>

</body>
</html>

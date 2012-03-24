<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>航空公司</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="航空公司一览表">
<s:head/>
</head>
<body>
<script language="javascript">

function SubmitForm(param, target)
{
  var fm = document.listAirways;
  fm.airwaysId.value = target;
  if (param == 'modify') {
    fm.action = "<s:url action='editAirways' namespace='/resource'/>"
  } else if (param == 'add') {
    fm.opKey.value = "A";
    fm.action = "<s:url action='editAirways' namespace='/resource'/>"
  } else if (param == 'del') {
    fm.action = "<s:url action='deleteAirways' namespace='/resource'/>"
    if (confirm("确定要删除此分类吗？") == false) {
      return;
    }
  }
  fm.submit();
}

function _getlist(type)
{
  var frm = document.listAirways;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}

//-->
</script>

<s:form action="listAirways" namespace="/resource" method="post" theme="simple">
  <s:hidden name="airwaysId"></s:hidden>
  <s:hidden name="opKey" value="M"></s:hidden>
  <table style="width: 100%">
    <tr>
      <td class="lstidx">No.</td>
      <td class="lstidx">编码</td>
      <td class="lstidx">名称</td>
      <td class="lstidx">全称</td>
      <td class="lstidx">联系人</td>
      <td class="lstidx">联系电话</td>
      <td colspan="2" class="lstidx">操作</td>
    </tr>
    
    <s:iterator value="airwaysList" status="rowcounter">
      <tr>
        <td class="cdata"><s:property value="%{fromRecord + #rowcounter.count}"/> </td>
        <td class="cdata"><s:property  value="code"/></td>
        <td class="data"><s:property value="name"/>&nbsp;</td>
        <td class="data"><s:property value="fullname"/>&nbsp;</td>
        <td class="data"><s:property value="contact"/>&nbsp;</td>
        <td class="data"><s:property value="phone"/>&nbsp;</td>
        <td class="cdata">
          <a href="#" onClick="javascript:SubmitForm('modify','<s:property value="code"/>')">修改</a>
          <a href="#" onClick="javascript:SubmitForm('del','<s:property value="code"/>')">删除</a>
        </td>
      </tr>
    </s:iterator>
    <tr>
      <td colspan="8">
        <s:if test="airwaysList.isEmpty() == false">
        <%@ include file="/includes/PagerTable.jsp" %>
        </s:if>
      </td>
    </tr>
  </table>
  <input type="button" class="button" value="<s:text name="common.forms.add" />" onclick="javascript:SubmitForm('add','')">
</s:form>
</body>
</html>



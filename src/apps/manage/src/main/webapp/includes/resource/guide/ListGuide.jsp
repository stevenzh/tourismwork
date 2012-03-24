<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导游领队一览表</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="导游领队一览表">
</head>

<body>

<script language="JavaScript">
<!--//
function SubmitForm(param, target)
{
  var fm = document.listGuide;
  fm.accCd.value=target;
  
  if (param == 'modify') {
    fm.action = "<s:url action='editGuide' namespace='/resource'/>"
  } else if (param == 'add') {
    fm.action = "<s:url action='editGuide' namespace='/resource'/>"
  } else if (param == 'del') {
    fm.action = "<s:url action='deleteGuide' namespace='/resource'/>"
    if (confirm("确定要删除吗？") == false) {
      return;
    }
  }
  fm.submit();
}

function SubmitQuery()
{
  var fm = document.listGuide;
  fm.action = "<s:url action='queryGuide' namespace='/resource'/>"
  fm.submit();
}

function _getlist(type)
{
  var frm = document.listGuide;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}
//-->
</script>

<s:form action="listGuide" namespace="/resource" method="post" theme="simple">
<s:hidden name="accCd"></s:hidden>

<table style="width: 100%">
  <tr>
    <td align="right">请输入姓名:<s:textfield name="accNm"></s:textfield>
    <input type="button" value="搜 索" onclick="javascript:SubmitQuery();">
  </td>
  </tr>
</table>

<table border="0" cellpadding="0" cellspacing="2" width="100%">

  <tr>
    <td class="lstidx">NO.</td>
    <td class="lstidx">陪同代码</td>
    <td class="lstidx">姓名</td>
    <td class="lstidx">性别</td>
    <td class="lstidx">工作组</td>
    <td class="lstidx">身份证</td>
    <td class="lstidx">电话</td>
    <td class="lstidx">手机</td>
    <td class="lstidx">E-mail</td>
    <td colspan="2" class="lstidx">操作</td>
  </tr>
  
  <s:iterator value="guideList" status="rowcounter">
    <s:if test="#rowcounter.count > fromRecord">
    <s:if test="#rowcounter.count <= toRecord">
      <tr>
        <td class="cdata"><s:property value="#rowcounter.count"/> </td>
        <td class="cdata"><s:property  value="accCd"/></td>
        <td class="cdata"><s:property value="accNm"/>&nbsp;</td>
        <td class="cdata"><s:property value="accSex"/>&nbsp;</td>
        <td class="cdata"><s:property value="dptNo"/>&nbsp;</td>
        <td class="cdata"><s:property value="card"/>&nbsp;</td>
        <td class="cdata"><s:property value="tel"/>&nbsp;</td>
        <td class="cdata"><s:property value="mobile"/>&nbsp;</td>
        <td class="cdata"><s:property value="EMail"/>&nbsp;</td>
        <td align="center"><a href="javascript:SubmitForm('modify','<s:property value="accCd"/>')" >修改</a>
        <a href="javascript:SubmitForm('del','<s:property value="accCd"/>')">删除</a></td>
      </tr>
     </s:if>
     </s:if>
    </s:iterator>
    <tr>
      <td colspan="12">
        <s:if test="guideList.isEmpty() == false">
        <%@ include file="/includes/PagerTable.jsp" %>
        </s:if>
      </td>
    </tr>
</table>
<center><input type="button" class="button" value="添 加" onclick="javascript:SubmitForm('add','')"></center>
</s:form>
</body>
</html>

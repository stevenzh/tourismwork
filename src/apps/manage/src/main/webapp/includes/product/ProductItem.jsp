<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改产品包含项目</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="修改产品包含项目">
</head>

<body>
<script type="text/javascript">
<!--//
function SubmitForm(param, target)
{
  var fm = document.listProductItem;
  fm.itemId.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='deleteProductItem' namespace='/product'/>"
  }
  else if(param =='modify')
  {
    fm.action = "<s:url action='editProductItem' namespace='/product'/>"
  }
  fm.submit();
}

//-->
</script>

<s:form action="listProductItem" namespace="/product" method="POST" theme="simple">
  <s:hidden name="itemId" />

  <table style="width: 80%">
    <tr>
      <td class="idx">工作组：</td>
      <td>
      <s:select name="category"
                list="categorys"
                listKey="catId"
                listValue="name"
                headerKey="0"
                headerValue="全部">
      </s:select>
      </td>
    </tr>
  </table>
  <br>
  <table style="width: 100%">
    <tr>
      <td class="lstidx">NO.</td>
      <td class="lstidx">类型ID</td>
      <td class="lstidx">ItemCode</td>
      <td class="lstidx">Name</td>
      <td class="lstidx">Is Active</td>
      <td class="lstidx">Show in Portal</td>
      <td class="lstidx">Has Template</td>
      <td class="lstidx">Is Text</td>
      <td class="lstidx">Has Title</td>
      <td class="lstidx">操作</td>
    </tr>

    <s:iterator value="items" status="rowcounter">
      <tr>
        <td>&nbsp;<s:property value="#rowcounter.count" /></td>
        <td>&nbsp;<s:property value="category.name" /></td>
        <td>&nbsp;<s:property value="itemCode" /></td>
        <td>&nbsp;<s:property value="itemName" /></td>
        <td>&nbsp;<s:property value="isActive" /></td>
        <td>&nbsp;<s:property value="showInPortal" /></td>
        <td>&nbsp;<s:property value="isTmpl" /></td>
        <td>&nbsp;<s:property value="isText" /></td>
        <td>&nbsp;<s:property value="hasTitle" /></td>
        <td><a href="#" onclick="javascript:SubmitForm('modify','<s:property value="itemId" />')">修改</a></td>
      </tr>
    </s:iterator>

  </table>

  <div align="left"><s:submit action="RouteAdd" value="添加" />
  </div>

</s:form>

</body>
</html>

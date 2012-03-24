<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑产品项目信息</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="编辑产品项目信息">
</head>

<body>
<s:form action="submitProductItem" namespace="/product" method="post">
  <s:hidden name="category" />

  <s:hidden name="itemId" />
  <s:hidden name="item.itemId" />

  <s:textfield label="编码"
               name="item.itemCode"
               required="true"
               requiredposition="right">
  </s:textfield>

  <s:textfield label="名称"
               name="item.itemName"
               required="true"
               requiredposition="right">
  </s:textfield>
  <s:textfield label="权重"
               name="item.sortOrder"
               required="true"
               requiredposition="right">
  </s:textfield>

  <s:textfield label="Link URL"
               name="item.linkUrl"
               size="60"
               required="true"
               requiredposition="right">
  </s:textfield>
  
  <s:select name="item.category.catId"
            list="categorys"
            listKey="catId"
            listValue="name">
  </s:select>
  <s:checkbox label="活动"
              name="item.isActive">
  </s:checkbox>
  <s:checkbox label="Portal显示"
              name="item.showInPortal">
  </s:checkbox>
  <s:checkbox label="模板"
              name="item.isTmpl">
  </s:checkbox>
  <s:checkbox label="简单文字"
              name="item.isText">
  </s:checkbox>
  <s:checkbox label="简单标题"
              name="item.hasTitle">
  </s:checkbox>
  <tr>
    <td colspan="4"><s:submit value="提交"></s:submit></td>
  </tr>
</s:form>

</body>
</html>

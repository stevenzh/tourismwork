<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="saveCategory" namespace="/product" method="POST" theme="simple">
  <table style="width: 400px">
    <tr>
      <td>分类ID:</td>
      <td>
          <s:property value="category.destId"/>
          <s:hidden name="category.destId"></s:hidden>
          <s:hidden name="category.level"></s:hidden>
      </td>
    </tr>
    <tr>
      <td>编码:</td>
      <td><s:textfield name="category.code"></s:textfield></td>
    </tr>
    <tr>
      <td>分类简称:</td>
      <td><s:textfield name="category.cnName"></s:textfield></td>
    </tr>
    <tr>
      <td>分类全称:</td>
      <td><s:textfield name="category.fullName"></s:textfield></td>
    </tr>
    <tr>
      <td>关键字(空格分隔)：</td>
      <td><s:textfield name="category.keywords"></s:textfield></td>
    </tr>
    <tr>
      <td>说明：</td>
      <td><s:textfield name="category.destDesc"></s:textfield></td>
    </tr>
    <tr>
      <td>Meta 关键字：</td>
      <td><s:textfield name="category.metaKeywords"></s:textfield></td>
    </tr>
    <tr>
      <td>Meta 说明：</td>
      <td><s:textfield name="category.metaDesc"></s:textfield></td>
    </tr>
    <tr>
      <td>上一级分类：</td>
      <td>
        <s:select name="category.parent.destId"
                  list="catList"
                  listKey="destId"
                  listValue="cnName">
        </s:select>
      </td>
    </tr>
  </table>
  <div align="left">
  <s:submit value="保存"/>&nbsp;&nbsp;
  <input value="添加子分类" type="button" onclick="javascript:addCategory();">
  <input value="删除" type="button" onclick="javascript:deleteCategory();">
  </div>
</s:form>




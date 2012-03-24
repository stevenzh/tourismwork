<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="submitDepartment" namespace="/setting" method="POST" theme="simple">
  <s:hidden name="department.groupId"></s:hidden>
  <table width="300">
    <tr>
      <td>名称:</td>
      <td><s:textfield name="department.name"></s:textfield></td>
    </tr>
    <tr>
      <td>全称：</td>
      <td><s:textfield name="department.fullName"></s:textfield></td>
    </tr>
    <tr>
      <td>联系人：</td>
      <td><s:textfield name="department.contact"></s:textfield></td>
    </tr>
    <tr>
      <td>电话：</td>
      <td><s:textfield name="department.phone"></s:textfield></td>
    </tr>
    <tr>
      <td>传真：</td>
      <td><s:textfield name="department.fax"></s:textfield></td>
    </tr>

    <tr>
      <td>上一级部门：</td>
      <td>
        <s:select name="department.parentId"
                  list="dptList"
                  listKey="groupId"
                  listValue="name"
                  headerKey="0"
                  headerValue="旅行社[顶级]">
        </s:select>
      </td>
    </tr>
  </table>
  <div align="left">
  <s:submit value="保存"/>&nbsp;&nbsp;
  <input type="button" value="添加下属部门" onclick="javascript:addGroup()" />
  
  </div>
</s:form>




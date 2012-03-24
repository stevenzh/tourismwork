<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:form action="showReadBooking" namespace="/sales" theme="simple" method="POST">
</s:form>

<table width="100%">
  <tr>
    <td class="lstidx">No.</td>
    <td class="lstidx">名称</td>
    <td class="lstidx">说明</td>
    <td class="lstidx">状态</td>
    <td class="lstidx">分配用户</td>
    <td class="lstidx">开始时间</td>
    <td class="lstidx">操作</td>
  </tr>
  <s:iterator value="tasks" status="rowcounter">
    <s:if test="#rowcounter.count < 11">
      <tr>
        <td class="cdata"><s:property value="#rowcounter.count" /></td>
        <td class="data"><s:property value="name" />&nbsp;</td>
        <td class="data"><s:property value="description" />&nbsp;</td>
        <td class="data"><s:property value="state" />&nbsp;</td>
        <td class="data"><s:property value="assignee" />&nbsp;</td>
        <td class="cdata"><s:date name="createTime" format="yyyy-MM-dd" />&nbsp;</td>
        <td class="cdata">&nbsp;</td>
      </tr>
    </s:if>
  </s:iterator>
  <s:if test="bookList.size > 10">
    <tr>
      <td colspan="7" align="right">更多</td>
    </tr>
  </s:if>
</table>

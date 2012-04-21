<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table border="0" cellspacing="3" style="width: 100%">
  <tr>
    <th align="center">签证国家</th>
    <th align="center">签证种类</th>
    <th align="center">签证费</th>
    <th align="center">签证办理时间</th>
    <th align="center">停留天数</th>
  </tr>

  <s:iterator value="visas">
    <tr>
      <td><s:property value="cnName"/></td>
      <td>
        <a href="<s:url action="visaDetail" namespace="/product"/>?visaId=<s:property value="recordNo" />">
        <s:property value="subject" />
        </a>
      </td>
      <td align="right"><s:property value="price" />元&nbsp;</td>
      <td>&nbsp;<s:property value="transactDays"/></td>
      <td>&nbsp;<s:property value="stayDays"/></td>
    </tr>
  </s:iterator>
</table>


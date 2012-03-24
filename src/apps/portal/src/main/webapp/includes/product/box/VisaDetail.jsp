<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h2><s:property value="visaItem.cnName" /> - <s:property value="visaItem.subject" />-签证须知</h2>

<div class="middle_box">
  <div class="middle_box_top">
    <h2>办签要求</h2>
  </div>
  <s:property escape="false" value="visaItem.note" />
</div>

<div class="middle_box">
  <div class="middle_box_top">
    <h2>附件列表</h2>
  </div>
  <table width="100%" border="0">
    <s:iterator value="visaItem.fileItems" status="rowCounter">
    <tr>
      <td>
      <s:property value="#rowCounter.count" />
      <a href="<s:property value="filePath"/>" target="_blank" /><s:property value="note" /></a>
      </td>
    </tr>
    </s:iterator>
  </table>
</div>
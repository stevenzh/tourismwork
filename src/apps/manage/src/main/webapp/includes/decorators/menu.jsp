<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div style="background:#C9EDCC;padding:5px;width:100%;">
  <s:iterator value="#application['EBIZ_APP_MAIN_MENU']" status="rowCounter" var="vm">
	  <a href="javascript:void(0)" class="easyui-menubutton" menu='#mm<s:property value="#rowCounter.count" />'><s:property value="%{#vm.displayName}" /></a>
	  <div id='mm<s:property value="#rowCounter.count" />' style="width:150px;">
	  <s:iterator value="#vm.child" var="im">
	    <div><a href="<s:property value='%{#im.relativePath}'/>"><s:property value="%{#im.displayName}" /></a></div>
	  </s:iterator>
		</div>
  </s:iterator>
</div>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="custom-menu">
  <s:iterator value="#application['EBIZ_APP_MAIN_MENU']" status="rowCounter" var="vm">
    <div class="menu-item">
	  <a href="javascript:void(0)" class="easyui-menubutton" menu='#mm<s:property value="#rowCounter.count" />'><s:property value="%{#vm.displayName}" /></a>
	  <div id='mm<s:property value="#rowCounter.count" />' class="sub-menu">
	  <s:iterator value="#vm.child" var="im">
	    <div><a href="<s:property value='%{#im.relativePath}'/>"><s:property value="%{#im.displayName}" /></a></div>
	  </s:iterator>
		</div>
    </div>
  </s:iterator>
</div>
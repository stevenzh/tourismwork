<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="menuitem">
<ul>
  <s:iterator value="shortcutList">
  <li><a href="<s:property value='relativePath'/>"><s:property value="displayName"/></a></li>
  </s:iterator>
</ul>
</div>
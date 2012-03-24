<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<ul>
  <s:iterator value="lines">
    <li><a target="_bank" href="<s:url action="pkgVacation" namespace="/product"><s:param name="lineNo" value="line.lineNo" /></s:url>" title="<s:property value="line.lineName" />">
		<s:property value="line.lineName" /></a>
		<span>￥<s:property value="packagePrice.price" />起</span><br>
		</li>
  </s:iterator>
</ul>
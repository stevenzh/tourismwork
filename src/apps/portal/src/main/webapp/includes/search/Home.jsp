<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
</head>

<body>
<center>
<img src="<s:url value='/images/logo.png' encode='false' includeParams='none'/>" border="0" usemap="#Map2">
<map name="Map2">
  <area shape="rect" coords="160,56,176,71" href="<s:url action='IndexInput' namespace='/search' />">
</map><br>
<br>

<table border="0" width="560">
  <tr>
    <td align="center">
    <font size="-1">
    <s:if test="searchType == 1"><strong>出境旅游</strong></s:if>
    <s:else>
    <s:url var="url" action="Home" includeParams="none"/><s:a href="%{url}?searchType=1">出境旅游</s:a></s:else>&nbsp;&nbsp;&nbsp;&nbsp;
    <s:if test="searchType == 3"><strong>国内长线</strong></s:if>
    <s:else>
    <s:url var="url" action="Home" includeParams="none"/><s:a href="%{url}?searchType=3">国内长线</s:a></s:else>&nbsp;&nbsp;&nbsp;&nbsp;
    <s:if test="searchType == 4"><strong>国内短线</strong></s:if>
    <s:else>
    <s:url var="url" action="Home" includeParams="none"/><s:a href="%{url}?searchType=4">国内短线</s:a></s:else>
    </font>
    </td>

  </tr>
</table>

<s:form name="f" action="Search"  namespace="/search" method="get" theme="simple">
  <s:hidden name="searchType"></s:hidden>
	<table width="500" border="0">
	 <tr>
	   <td height="48">&nbsp;</td>
	   <td align="center" valign="top" nowrap><font>
	     <s:textfield id="queryString"
	                  name="queryString"
	                  size="55"
	                  maxlength="100"
	                  onfocus="tips('queryString','请输入目的地国家或城市')"
	                  onblur="outtips()">
	     </s:textfield>
	     <div id="tips" style="position:static; font-size:12px; border:1px solid #ccc;padding:0px 3px; color:#f00; display:none; height:20px; width:60%; line-height:20px; background:#fcfcfc">
       </div>
	    </font>
	  </td>
	  <td align="center" valign="top" nowrap="nowrap">
	  <font size="-1"> <a href="<s:url namespace='/search' action='AdvancedSearchInput' />">高级搜索</a><br>
	      <a href="<s:url namespace='/search' action='ClassifySearchInput'/>">分类搜索</a><br>
	      <a href="/html/help.htm" target="_blank">搜索指南</a><br>
    <s:if test="#session['EBIZ_USER'] != null">
    <s:url var="url" namespace="/accounts" action="PriceList" /><s:a href="%{url}">报价管理</s:a>
    </s:if>
    </font>        
	  </td>
	</tr>
	<tr>
	  <td colspan="3" align="center" valign="bottom">
	    <input type="submit" value="S搜索" onClick="javascript:checkOutQuery();">
	    <br>
	    <font size="-1">
	     <s:radio name="outCity" list="outCityList" listKey="value" listValue="label">
	     </s:radio>
	   </font>
	   <br><br>
	   </td>
	</tr>
	</table>
</s:form>
</center>

</body>
</html>

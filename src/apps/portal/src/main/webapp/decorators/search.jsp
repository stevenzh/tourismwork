<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page import="java.lang.*,java.util.List,com.opentravelsoft.entity.Line"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<s:url value='/stylesheet/layout.css' encode='false' includeParams='none'/>" type="text/css" media="all">
<title>线路搜索--Search</title>
<style type="text/css">
<!--
.style1 {color: #1A50B8;font-size: 16px;font-weight: bold;}
.highlight { color: red;   background: yellow;}
.highlight1 { color: red;}
-->
</style>
<script type="text/javascript" src="<s:url value='/scripts/lucene/luceneQueryEscaper.js' encode='false' includeParams='none'/>"></script>
<script type="text/javascript">
function tab(param)
{
    fieldF=document.getElementById("queryString");
    var queryStr=doEscapeQuery(fieldF);
    if(queryStr=="")
     {
        alert("请输入查询字符！");
        return;
     }else
     {
        fieldF.value = queryStr;
     }
  var fm = document.f1;
  document.getElementById("st").value = param;
  fm.submit();
}

function _getlist(type)
{
   var frm = document.SearchNext;
   var movePage = document.getElementById("movePage");
   movePage.value = type ;
   frm.submit();
}
function _numGetlist(currentP)
{
   var fm = document.SearchNext;
   var movePage = document.getElementById("movePage");
   movePage.value ="" ;
   var currentpage = document.getElementById("currentPage")
   currentpage.value = currentP;
   fm.submit();
}

function checkOutQuery()
{
    fm=document.Search;
    fieldF=document.getElementById("queryString");
    var queryStr=doEscapeQuery(fieldF);
    if(queryStr=="")
     {
        alert("请输入查询字符！");
        return;
     }else
     {
        fieldF.value = queryStr;
     }
    fm.submit();
}
</script>

<decorator:head/>
</head>

<body>
<BR>
<div style="padding: 0pt 0pt 4px; font-size: 84%; width: 100%" align="right"><font size="-1">
<s:if test="#session['EBIZ_USER'] == null">
  <a href="<s:url action='Login' namespace='/accounts' includeParams='none'/>">登录</a>
</s:if>
<s:else>
  <font size="-1" color="blue">
    部门:<s:property value="#session['EBIZ_USER'].departmentName"/>&nbsp;|
    <a href="<s:url action='Home' namespace='/' includeParams='none'/>">主页</a>&nbsp;
    <s:property value="#session['EBIZ_USER'].userName" /></font>&nbsp;|&nbsp;
  <s:url var="url" namespace="/accounts" action="ManageAccount" /><s:a href="%{url}">我的帐户</s:a>&nbsp;|
  <a href="<s:url action='Home' namespace='/' includeParams='none'/>">主页</a>&nbsp;|
  <s:url var="url" namespace="/accounts" action="Logout" /><s:a href="%{url}">退出</s:a>&nbsp;
  </s:else>
  </font>
</div>

<s:form name="f1" action="Search" namespace="/search" method="get" theme="simple">
  <s:hidden id="st" name="searchType" value="0"></s:hidden>
  <table border="0">
    <tr>
      <td width="17%" rowspan="3"><img src="<s:url value='/images/logo_small.gif' encode='false' includeParams='none'/>"></td>
      <td width="83%"><font size="-1">
        <!--  
        <s:if test="searchType == 0"><b>所有类型</b></s:if>
        <s:else><s:url var="url" action="Search" includeParams="none"/><s:a onclick="tab(0)" href="#">所有类型</s:a></s:else>&nbsp;&nbsp;&nbsp;&nbsp;
        -->
        <s:if test="searchType == 1"><b>出境旅游</b></s:if>
        <s:else><s:url var="url" action="Search" includeParams="none"/><s:a onclick="tab(1)" href="#">出境旅游</s:a></s:else>&nbsp;&nbsp;&nbsp;&nbsp;
        FIT自由行
        <!-- 
        <s:if test="searchType == 2"><b>FIT自由行</b></s:if>
        <s:else><s:url var="url" action="Search" includeParams="none"/><s:a onclick="tab(2)" href="%{url}">FIT自由行</s:a></s:else> -->
        &nbsp;&nbsp;&nbsp;&nbsp; 
        <s:if test="searchType == 3"><b>国内长线</b></s:if>
        <s:else><s:url var="url" action="Search" includeParams="none"/><s:a onclick="tab(3)" href="#">国内长线</s:a></s:else>&nbsp;&nbsp;&nbsp;&nbsp;
        <s:if test="searchType == 4"> <b>国内短线</b></s:if>
        <s:else><s:url var="url" action="Search" includeParams="none"/><s:a onclick="tab(4)" href="#">国内短线</s:a></s:else>&nbsp;&nbsp;&nbsp;&nbsp;
       </font>
       </td>
    </tr>
    <tr>
      <td><font size="-1">
        <s:textfield id="queryString" size="54" maxlength="100" name="queryString"></s:textfield>
          <s:submit value="搜索"></s:submit>
          <s:url var="url" namespace="/search" action="AdvancedSearchInput" />
          <s:a href="%{url}">高级搜索</s:a>
          <s:url var="url1" namespace="/search" action="ClassifySearchInput" />
          <s:a href="%{url1}">分类搜索</s:a>
        <a href="/html/help.htm" target="_blank">搜索指南</a></font>
      </td>
    </tr>
    <tr>
      <td><font size="-1">
      <s:radio name="outCity" list="outCityList" listKey="value" listValue="label">
      </s:radio></font>
      </td>
    </tr>
  </table>
</s:form>

<decorator:body/>

<br />
<s:form name="f2" action="Search" namespace="/search" method="post" theme="simple">
  <s:hidden name="outCity" value="All"></s:hidden>
  <s:hidden name="searchType" value="0"></s:hidden>
  <table border="0">
    <tr>
      <td valign="middle"><img src="<s:url value='/images/logo_small.gif' encode='false' includeParams='none'/>">
      <font size="-1">
        <s:textfield id="queryString1" size="54" maxlength="100" name="queryString"></s:textfield>
          <s:submit value="搜索"></s:submit></font>
      </td>
    </tr>
  </table>
</s:form>

<center>
<br><br>
<div align="center">
<s:if test="#session['EBIZ_USER'] == null">
<font size="-1">客服热线 : XXXXX-XXXX</font>
</s:if>
<s:else>
<font size="-1">客服热线 : XXXX-XXXXX</font>
</s:else>&nbsp;&nbsp; <font size="-1">版权所有 : opentravelsoft.com &nbsp;&nbsp; 沪ICP备XXXXXXXX号</font></div>
<br><br>
</center>
</body>
</html>

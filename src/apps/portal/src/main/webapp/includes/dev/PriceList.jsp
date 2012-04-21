<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<s:url value='/styles/layout.css' encode='false' includeParams='none'/>" type="text/css">
<title>报价管理--Search</title>
</head>
<body>
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
function down(param)
{
  var fm = document.download;
  fm.fileId.value = param;
  fm.submit();
}

function _getlist(type)
{
  var frm = document.PriceList;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  document.getElementById("movePage").value = type ;
  frm.submit();
}

</script>
<s:form name="f1" action="Search" namespace="/search" method="get" theme="simple">
  <s:hidden id="st" name="searchType" value="0"></s:hidden>
  <table border="0">
    <tr>
      <td width="17%" rowspan="3"><img src="<s:url value='/images/logo_small.gif' encode='false' includeParams='none'/>"></td>
      <td width="83%"><font size="-1">
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
          <s:submit value="S搜索"></s:submit>
          <s:url var="url" namespace="/search" action="AdvancedSearchInput" />
        <s:a href="%{url}">高级搜索</s:a>
        <a href="/html/help.htm" target="_blank">搜索指南</a></font>
      </td>
    </tr>
    <tr>
      <td><font size="-1">
      <s:radio name="outCity" list="outCityList" listKey="value" listValue="label" value="All">
      </s:radio></font>
      </td>
    </tr>
  </table>
</s:form>
<br>

<s:form namespace="/accounts" action="PriceList" method="POST">
<table style="width: 100%"  border="0" bgcolor="#b9c0ff">
  <tr>
    <td align="left" height="25"><b>搜索成果</b></td>
    <td align="right" >共搜索到当期报价<b><s:property value="fileList.size()"/></b>份，以下是第1-<s:property value="fileList.size()"/>项</td>
  </tr>
</table>

<table width="800" border="0">
  <s:iterator value="fileList" status="rowcounter">
  <s:if test="#rowcounter.count > fromRecord">
  <s:if test="#rowcounter.count <= toRecord">
    <div style="height: 40px;">
    <a href="http://localhost:8080<s:property value="filePath"/>"><s:property value="fileName"/></a><font size="-1">
     上传时间 <s:date name="created" format="yyyy-MM-dd[E]"/></font>
    </div>
  </s:if>
  </s:if>
  </s:iterator>
  <tr>
    <td align="center">
      <s:hidden name="countInPage"></s:hidden>
      <s:hidden name="currentPage"></s:hidden>
      <s:hidden name="sortId"></s:hidden>
      <s:hidden id="movePage" name="movePage"></s:hidden>
    <font size="-1"><a href="#" onclick="javascript:_getlist('prev')">上一页</a> 1 <a href="#" onclick="javascript:_getlist('next')">下一页</a></font>
    </td>
  </tr>
</table>
</s:form>

<br><br>

<s:form action="download" namespace="/accounts">
  <s:hidden name="fileId"></s:hidden>
  <s:hidden name="name" value="e"></s:hidden>
</s:form>

</body>
</html>

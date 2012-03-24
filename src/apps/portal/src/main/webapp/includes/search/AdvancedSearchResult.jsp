<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="java.lang.*,java.util.List,com.opentravelsoft.entity.Line"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>高级搜索结果--Search</title>
<link rel="stylesheet" href="<s:url value='/stylesheet/layout.css' encode='false' includeParams='none'/>" type="text/css" media=screen>
<script type="text/javascript" src="<s:url value='/scripts/lucene/luceneQueryEscaper.js' encode='false' includeParams='none'/>"></script>
<style type="text/css">
<!--
.style1 {
	color: #1A50B8;
	font-size: 16px;
	font-weight: bold;
}

.highlight {
    color: red;
    background: yellow;
}
.highlight1 {
    color: red;
}

-->
</style>
</head>

<body>
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
   var frm = document.AdvancedSearchNext;
   var movePage = document.getElementById("movePage");
   movePage.value = type ;
   frm.submit();
}
function _numGetlist(currentP)
{
   var fm = document.AdvancedSearchNext;
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
    
<%
    int length = (Integer)request.getAttribute("length");
    String currentPage = (String)request.getAttribute("currentPage");
    int current = Integer.parseInt(currentPage);
    int startRecord = (current-1)*10 +1;
    int endRecord =0;
    if(current*10 >=length)
      endRecord = length;
    else
        endRecord = current*10;
%>
<table width="100%" border="0" bgcolor="#b9c0ff">
  <tr>
    <td width="10"></td>
    <td align="left" height="25"><b>搜索成果</b></td>
    <td align="right">
      <s:if test="length == 0">
         没有找到项符合<b><s:property value="queryString"/></b>的查询结果!
      </s:if>
      <s:else>
          约有<b><s:property value="length"/></b>项符合条件的查询结果，以下是第<%=startRecord%>-<%=endRecord%>项
      </s:else>
    </td>
  </tr>
</table>

<table border="0" width="100%" cellpadding="2" cellspacing="0">
<tr>
<td valign="top" width="10"></td>
<td valign="top">
<s:form action="pkgVacation" namespace="/product" method="post" theme="simple">
  <s:hidden id="routeNo" name="lineNo"></s:hidden>
  <table border="0" width="600" cellpadding="2" cellspacing="0">
  <%
    List<Line> RouteIndexList = (List<Line>)request.getAttribute("RouteIndexList");
    Line routeIndex;
    for(int i=0;i<RouteIndexList.size();i++)
    {
      routeIndex = RouteIndexList.get(i);
      String routeNo = routeIndex.getLineNo();
      String routeName = routeIndex.getLineName();
      String outCityName = routeIndex.getOutCity().getCitynm();
      String routeTrait = routeIndex.getRouteTrait();
      String districtName = routeIndex.getDistrictName();
      if(null==districtName || districtName.trim().equals(""))
    	  districtName="(暂无目的地)";
      String sightName = routeIndex.getSightName();
      if(null==sightName || sightName.equals(""))
    	  sightName="(暂无景点)";
      String outDate = routeIndex.getOutDateStr();
      if(null==outDate ||outDate.trim().equals(""))
        outDate = "暂无出团计划";
      else
        outDate = "最近出发日期：" + outDate;
      String price1 = routeIndex.getPrice1Str();
      String price2 = routeIndex.getPrice2Str();
      if(null == price1)
        price1 = "";
      if(null == price2)
    	  price2 = "";
      int planPax=0;
      if(null!=routeIndex.getPlanPax())
    	  planPax = new Integer(routeIndex.getPlanPax());
      String strPax = "";
      if(planPax>1)
    	  strPax = "……";
    %>
    <tr>
        <td><a href="<s:url namespace='/product' action='pkgVacation' includeParams='none'/>?lineNo=<%=routeNo %>" title="本线路详细信息" target="_blank"><%=routeName %></a></td>
      </tr>
    <tr>
      <td><font size="-1">
         <b>出发地：</b><%=outCityName %><br>
         <b>目的地：</b><%=districtName %><br>
         <b>景　点：</b><%=sightName %><br>
         <%=routeTrait %>...<br>
         <s:if test="#session['EBIZ_USER'] == null">
           <%=outDate %><%=strPax %>；报价：<%=price1 %>元起；...
         </s:if>
         <s:else>
           <%=outDate %><%=strPax %>；同行价：<%=price2 %>元起；...
         </s:else>
        </font>
      </td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <%} %>
    <tr>
      <td>
         <div align="center"><font size="-1">
  第<s:property value="currentPage"/>页 共<s:property value="totalPage"/>页 共<s:property value="totalRecord"/>条记录
<a href="#" onClick="javascript:_getlist('first')">首页</a>
<a href="#" onClick="javascript:_getlist('prev')">上一页</a>
<%
   int i=1;
   int j=1;
   for(i=(current-1)/10 * 10;i<=(length-1)/10 && j<=10;i++,j++)
   {
	   if(current!=i+1)
	   {
%>
   <a href="#" onClick="javascript:_numGetlist('<%=i+1%>')"><%=i+1%></a>
<%
	   }
	   else
	   {
%><%=i+1%><%
	   }
   }
%>
<a href="#" onClick="javascript:_getlist('next')">下一页</a>
<a href="#" onClick="javascript:_getlist('last')">尾页</a></font>
</div>
      </td>
    </tr>
  </table>
</s:form>
</td>

<td valign="top" align="right">
   <table border="0" width="250" cellpadding="2" cellspacing="0">
    <%
    List<Line> preferRouteIndexList = (List<Line>)request.getAttribute("preferRouteIndexList");
    Line preferRouteIndex;
    for(int a=0;a<5 && a<preferRouteIndexList.size();a++)
    {
      preferRouteIndex = preferRouteIndexList.get(a);
      String routeNo = preferRouteIndex.getLineNo();
      String routeName = preferRouteIndex.getLineName();
      String outCityName = preferRouteIndex.getOutCity().getCitynm();
      String routeTrait = preferRouteIndex.getRouteTrait();
      if(routeTrait.length()>30)
          routeTrait = preferRouteIndex.getRouteTrait().substring(0,30);
      String districtName = preferRouteIndex.getDistrictName();
      String outDate = preferRouteIndex.getOutDateStr();
      if(null==outDate ||outDate.trim().equals(""))
        outDate = "暂无出团计划";
      else
        outDate = "最近出发日期：" + outDate;
      String price1 = preferRouteIndex.getPrice1Str();
      String price2 = preferRouteIndex.getPrice2Str();
      if(null == price1)
        price1 = "";
      if(null == price2)
    	  price2 = "";
      int planPax=0;
      if(null!=preferRouteIndex.getPlanPax())
    	  planPax = new Integer(preferRouteIndex.getPlanPax());
      String strPax = "";
      if(planPax>1)
    	  strPax = "……";
    %>
    <tr>
        <td><a href="<s:url namespace='/view' action='lineDetail' includeParams='none'/>?routeNo=<%=routeNo %>" title="本线路详细信息" target="_blank"><%=routeName %></a></td>
        <td width="10"></td>
      </tr>
    <tr>
      <td><font size="-1">
         <b>出发地：</b><%=outCityName %><br>
         <b>目的地：</b><%=districtName %><br>
         <%=routeTrait %>...<br>
         <s:if test="#session['EBIZ_USER'] == null">
           <%=outDate %><%=strPax %>；报价：<%=price1 %>元起；...
         </s:if>
         <s:else>
           <%=outDate %><%=strPax %>；同行价：<%=price2 %>元起；...
         </s:else>
        </font>
      </td>
      <td width="10"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td width="10"></td>
    </tr>
    <%} %>
   
   </table>
</td>
</tr>
</table>

<s:form action="AdvancedSearchNext" namespace="/search" method="post" theme="simple">
  <s:hidden name="outCity"></s:hidden>
  <s:hidden name="searchType"></s:hidden>
  <s:hidden name="s_date"></s:hidden>
  <s:hidden name="e_date"></s:hidden>
  <s:hidden name="price1"></s:hidden>
  <s:hidden name="price2"></s:hidden>
  <s:hidden name="country"></s:hidden>
  <s:hidden name="province"></s:hidden>
  <s:hidden name="district"></s:hidden>
</s:form>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.lang.*,java.util.List,com.opentravelsoft.entity.Line"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>同业线路搜索--Search</title>
<link rel="stylesheet" href="<s:url value='/styles/layout.css' encode='false' includeParams='none'/>" type="text/css">
<style type="text/css">
<!--
.style1 {
	color: #1A50B8;
	font-size: 16px;
	font-weight: bold;
}

.style2 {
	font-size: 14px
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

<table width="800" border="0" align="center">
  <tr>
    <td>
    <s:form action="CbSearch" namespace="/search" method="post" theme="simple">
      <s:hidden name="searchType"></s:hidden>
      <table width="100%" border="0">
        <tr>
          <td>
          <s:textfield id="queryString" size="54" maxlength="100" name="queryString"></s:textfield>
          <s:submit value="搜索"></s:submit>
          </td>
        </tr>
        <tr>
          <td>
          <s:radio name="outCity" list="outCityList"  listKey="value" listValue="label"></s:radio>
          </td>
        </tr>
      </table>
    </s:form>
    </td>
  </tr>
  <tr>
    <td align="center">
    <%
         int length = (Integer)request.getAttribute("length");
         int current = (Integer)request.getAttribute("currentPage");
         int startRecord = (current-1)*10 +1;
         int endRecord =0;
         if(current*10 >=length)
      	   endRecord = length;
         else
           endRecord = current*10;
		%>
    <table width="100%" border="0" bgcolor="#b9c0ff">
      <tr>
        <td align="left" height="25"><b>搜索成果</b></td>
        <td align="right">
        <s:if test="length == 0">
		     没有找到项符合<b><s:property value="queryString" /></b>的查询结果!
		  </s:if> <s:else>
		      约有<b><s:property value="length" /></b>项符合<b><s:property value="queryString" /></b>的查询结果，以下是第<%=startRecord%>-<%=endRecord%>项
		  </s:else></td>
      </tr>
    </table>
    </td>
  </tr>

  <tr>
    <td>
    <s:form action="pkgVacation" namespace="/product" method="post" theme="simple">
      <s:hidden id="routeNo" name="lineNo"></s:hidden>

      <table border="0" width="100%" align="center">
        <%
  	        List<Line> RouteIndexList = (List<Line>)request.getAttribute("RouteIndexList");
  	        Line routeIndex;
  	        for(int i=0;i<RouteIndexList.size();i++)
  	        {
  	        	routeIndex = RouteIndexList.get(i);
  	        	String lineNo = routeIndex.getLineNo();
  		        String lineName = routeIndex.getLineName();
  		        String outCityName = routeIndex.getOutCity().getCitynm();
  		        String routeTrait = routeIndex.getRouteTrait();
  		        String districtName = routeIndex.getDistrictName();
  		        String outDate = routeIndex.getOutDateStr();
  		        if(null==outDate ||outDate.trim().equals(""))
  		        	outDate = "暂无出团计划";
  		        else
  		        	outDate = "最近出发日期：" + outDate;
  		        String price2 = routeIndex.getPrice2Str();
  		        if(null == price2)
  		        	price2 = "";
	      %>
        <tr>
          <td>
          <a href="<s:url namespace='/product' action='pkgVacation'/>?lineNo=<%=lineNo %>" title="本线路详细信息" target="_blank"><%=lineName %></a>
          </td>
        </tr>
        <tr>
          <td><b>目的地：</b><%=districtName %><br>
          <b>出发地：</b><%=outCityName %><br>
          <%=routeTrait %>等等......；<%=outDate %>；同行价：<%=price2 %>元起；......
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <%} %>
      </table>
    </s:form></td>
  </tr>

  <tr>
    <td>
    <script type="text/javascript">
         function _getlist(type)
         {
           var frm = document.CbSearchNext;
           var movePage = document.getElementById("movePage");
           movePage.value = type ;
           frm.submit();
        }
        function _numGetlist(currentP)
        {
           var fm = document.CbSearchNext;
           var movePage = document.getElementById("movePage");
           movePage.value ="" ;
           var currentpage = document.getElementById("currentPage")
           currentpage.value = currentP;
           fm.submit();
        }
     </script>
    <div class="style2" align="center">第<s:property
      value="currentPage" />页 共<s:property value="totalPage" />页 共<s:property
      value="totalRecord" />条记录 <a href="#"
      onclick="javascript:_getlist('first')">首页</a> <a href="#"
      onclick="javascript:_getlist('prev')">上一页</a> <%
               int i=1;
               int j=1;
               for(i=(current-1)/10 * 10;i<=(length-1)/10 && j<=10;i++,j++)
               {
            	   if(current!=i+1)
            	   {
            %> <a href="#" onclick="javascript:_numGetlist('<%=i+1%>')"><%=i+1%></a>
    <%
                   }
            	   else
            	   {
            %><%=i+1%>
    <%
                   }
               }
            %> <a href="#" onclick="javascript:_getlist('next')">下一页</a>
    <a href="#" onclick="javascript:_getlist('last')">尾页</a></div>

    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>

</body>
</html>

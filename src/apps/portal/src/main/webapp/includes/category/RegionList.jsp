<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*,org.apache.struts.util.*"%>

<script language="JavaScript">
<!--

function region(reg)
{
  var fm = document.RegionPlanList;
  fm.regionId.value = reg;
  fm.submit();
}

//-->
</script>


<!-- 目的地导航 - 开始 -->
<table height="14" cellspacing="1" cellpadding="0" width="100%" bgcolor="#6493d9" border="0">
  <tr bgcolor="#6493d9">
    <td colspan="4">目的地导航</td>
  </tr>
  <tr>
    <td bgcolor="#6493d9">
    <table cellspacing="1" cellpadding="1" width="100%" bgcolor="#ffffff" border="0">
      <%
      List regionList = (List)request.getAttribute("regions");
      
      for(int i=0; i < regionList.size(); i++) {
        List list = (List)regionList.get(i);
        for(int j=0; j<list.size(); j++) {
          LabelValueBean lvb = (LabelValueBean)list.get(j);
          if (j==0) {
            %>
      <tr>
        <td width="31%" height="20">&nbsp;
        <img src="<s:url value='/images/icon.gif' encode='false' includeParams='none'/>" height="7" width="9"/>
        &nbsp;-&nbsp; <a href="#" onclick="javascript:region(<%=lvb.getLabel() %>)">
        <font color="#0000ff" size="2"><%=lvb.getValue() %></font></a>
        </td>
            <%
          } else if ((j % 3 == 0) && j == list.size()-1 ) {
            %>
            <td valign="top" width="167" height="20"><a href="#" onclick="javascript:region(<%=lvb.getLabel() %>)"><%=lvb.getValue() %></a></td>
            </tr>
            <%
            } else if (j % 3 == 0) {
              %>
            <td valign="top" width="167" height="20"><a href="#" onclick="javascript:region(<%=lvb.getLabel() %>)"><%=lvb.getValue() %></a></td>
            </tr>
            <tr>
            <td>&nbsp;</td>
              <%
            } else {
            %>
            <td valign="top" width="167" height="20"><a href="#" onclick="javascript:region(<%=lvb.getLabel() %>)"><%=lvb.getValue() %></a></td>
          <%
          }
        }
      }
      %>
    </table>
    </td>
  </tr>
</table>


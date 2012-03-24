<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>按照目的地统计</title>
</head>

<body>
<script language="javascript">
function showDetail(routeNo)
{
	var fm = document.findOut;
	document.getElementById("routeNo").value = routeNo;
	fm.action = "<s:url action='getByLineNo' namespace='/sales' includeParams='none' />";
	fm.submit();
}

function _getlist(type)
{
  var frm = document.findOut;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  document.getElementById("movePage").value = type ;
  frm.submit();
}

</script>

<s:form action="findOut" namespace="/sales" method="post" theme="simple">
  <s:hidden id="routeNo" name="routeNo"/>
  <s:hidden name="startDate"></s:hidden>
  <s:hidden name="endDate"></s:hidden>
  <!-- 查询区结束 -->
  <table style="width: 90%">
  	<tr>
  		<td>
  			<FONT color="red"><s:property value="startDate"/> 至 <s:property value="endDate"/> 统计情况</FONT>
  		</td>
  	</tr>
  	<tr bgcolor="#b9c0ff">
      <td class="lstidx">No.</td>
      <td class="lstidx">出团日期</td>
      <td class="lstidx">线路</td>
      <td class="lstidx">人数</td>
      <td class="lstidx">团费</td>
    </tr>
      <s:iterator value="routeList" status="rowcounter">
    <tr>
      <td class="data"><s:property value="#rowcounter.count"/></td>
      <td class="data"><s:date name="outDate" format="yyyy-MM-dd"/></td>
      <td class="data"><a href="javascript:showDetail('<s:property value="line.lineNo"/>');"><s:property value="line.lineName"/></a></td>
      <td class="data"><s:property value="sumPax"/></td>
      <td class="data"><s:property value="sumDbamt"/></td>
    </tr>
    </s:iterator>
    </table>
</s:form>
</body>
</html>

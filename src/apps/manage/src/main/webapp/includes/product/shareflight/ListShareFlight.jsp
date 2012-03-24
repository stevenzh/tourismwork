<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>共享机位列表</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="共享机位列表">
</head>
<body>

<script type="text/javascript">

function SubmitForm(param,target)
{
  var fm = document.listShareFlight;
  fm.shFlightId.value = target;
  if (param =='delete') 
  {
   if(confirm('确定删除吗？') == false)
   {
     return;
   }
  fm.action = "<s:url action='deleteShareFlight' namespace='/resource'/>"
  } 
  else if (param == 'modify') 
  {
  fm.action = "<s:url action='editShareFlight' namespace='/resource' />"
  }
  fm.submit();
}

</script>
<table align="left" border="0" cellpadding="0" width="100%">
  <s:form method="post" action="listShareFlight" namespace="/resource" theme="simple">
 <s:hidden name="shFlightId"></s:hidden>

    <tr>
      <td>
      <table border="0" cellpadding="0" width="100%">
        <tr bgcolor="#f3f3f3">
          <td class="lstidx">共享机位序号</td>
          <td class="lstidx" align="center">航空公司</td>
          <td class="lstidx" align="center">航班号</td>
          <td class="lstidx" align="center">出发日期</td>
          <td class="lstidx" align="center">座位数</td>
          <td class="lstidx" align="center">可用座位数</td>
          <td class="lstidx" align="center">备注</td>
          <td class="lstidx" colspan="2" align="center">操 作</td>
        </tr>

        <s:iterator value="shareFlight" status="rowcounter">
          <tr>
            <td class="cdata"><s:property value="shareFlightId" /></td>
            <td class="cdata"><s:property value="airwaysName" /></td>
            <td class="cdata"><s:property value="flightNo" /></td>
            <td class="cdata"><s:property value="departureDate" /></td>
            <td class="cdata"><s:property value="seating" /></td>
            <td class="cdata"><s:property value="handle" /></td>
            <td class="cdata"><s:property value="note" /></td>
            <td align="center"><a href="javascript:SubmitForm('modify','<s:property value="shareFlightId"/>')">修改</a>
             <a href="javascript:SubmitForm('delete','<s:property value="shareFlightId"/>')">删除</a></td>
          </tr>
        </s:iterator>
      </table>
      <div align="center"><br>
      <input type="button" value="添加" onclick="javascript:SubmitForm('modify','<s:property value="shareFlightId"/>')"">
      </div>
      </td>
    </tr>
  </s:form>
</table>
</body>
</html>
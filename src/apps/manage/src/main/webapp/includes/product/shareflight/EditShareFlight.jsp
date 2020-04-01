<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加共享机位</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="添加共享机位">
</head>

<body>
<s:form action="submitShareFlight" namespace="/resource" method="post" theme="simple">
  <table style="width: 100%" border="0" align="center">
    <s:if test='tblShareFlight.shareFlightId > 0 '>
     <tr>
    	<td class="idx">共享机位序号：</td>
        <td class="data">
         <s:property value="tblShareFlight.shareFlightId"/>
        <s:hidden name="tblShareFlight.shareFlightId"></s:hidden>
        </td>
      </tr>
    </s:if>	
    <tr>
      <td class="idx">航空公司：</td>
      <td class="data">
      <s:select name="tblShareFlight.airwaysCode"
                  list="airways"
                  listKey="code"
                  listValue="name"
                  emptyOption="true">
      </s:select>
      <font color="ff0000">*</font></td>
    </tr>
    <tr>
      <td class="idx">航班号：</td>
      <td class="data"><s:textfield name="tblShareFlight.flightNo" /><font color="ff0000">*</font></td>
    </tr>
    <tr>
      <td class="idx">出发日期：</td>
      <td class="data">
      <s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" name="tblShareFlight.departureDate">
     </s:textfield>
     <font color="ff0000">*</font>
     </td>
    </tr>
    <tr>
      <td class="idx">座位数：</td>
      <td class="data"><s:textfield name="tblShareFlight.seating" /><!--<font color="ff0000">*</font>--></td>
    </tr>
    <tr>
      <td class="idx">可用座位数：</td>
      <td class="data"><s:textfield name="tblShareFlight.handle" /><!--<font color="ff0000">*</font>--></td>
    </tr>
    <tr>
      <td class="idx">备注：</td>
      <td class="data"><s:textarea name="tblShareFlight.note" cols="60" rows="3"></s:textarea><!--<font color="ff0000">*</font>--></td>
    </tr>
    <tr>
    <td>
      <font color="ff0000">*</font>为必填项!</td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <s:submit value="提  交"/>
        <s:submit action="listShareFlight" value="返  回"/>
      </td>
    </tr>
  </table>
</s:form>
</body>
</html>


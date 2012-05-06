<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>

<script language="JavaScript">
<!--//

function detail(no)
{
  var fm = document.findOperatorAlert;
  fm.bookingNo.value = no;
  fm.submit();
}

//-->
</script>

<s:form action="showReadBooking" namespace="/sales" method="post" theme="simple">
<s:hidden name="bookingNo"></s:hidden>

<table style="width: 100%">
  <tr>
    <td class="lstidx" nowrap="nowrap">订单号</td>
    <td class="lstidx">线路名</td>
    <td class="lstidx">出发日期</td>
    <td class="lstidx">代理商</td>
    <td class="lstidx">联系人</td>
    <td class="lstidx">联系电话</td>
    <td class="lstidx">预订名额</td>
    <td class="lstidx">确认名额</td>
    <td class="lstidx">预订日期</td>
    <td class="lstidx">状态</td>
  </tr>

  <s:iterator value="bookList" status="rowcounter">
  <s:if test="#rowcounter.count > fromRecord">
  <s:if test="#rowcounter.count <= toRecord">
  <tr>
    <td class="cdata"><A href="#" onclick="javascript:detail('<s:property value='bookingNo'/>')"><s:property value="bookingNo"/></A></td>
    <td class="data"><s:property value="plan.line.lineName"/></td>
    <td class="cdata"><s:date name="plan.outDate" format="yyyy-MM-dd"/></td>
    <td class="data"><s:property value="customer.name"/></td>
    <td class="data"><s:property value="contact"/></td>
    <td class="data"><s:property value="phone"/></td>
    <td class="rdata"><s:property value="pax"/>&nbsp;</td>
    <td class="rdata"><s:property value="confirmPax"/>&nbsp;</td>
    <td class="cdata"><s:date name="reserveDate" format="yyyy-MM-dd"/></td>
    <td class="cdata">
    	<s:if test='delkey == "Y"'>
    		<font color="red">订单已取消</font>
    	</s:if>
    	<s:else>
    		&nbsp;
    	</s:else>
    </td>
  </tr>
  </s:if>
  </s:if>
  </s:iterator>

  <s:if test="bookList.isEmpty() == true">
  <tr>
    <td colspan="10">没有新的订单.</td>
  </tr>
  </s:if>
</table>

</s:form>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>

<%
    request.setAttribute("decorator", "none");
    response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<script type="text/javascript">
function showBooking(param)
{
    var fm = document.showReadBooking;
    fm.bookingNo.value = param;
    fm.submit();
}

function showExpress(param)
{
    var fm = document.showExpressDetail;
    fm.expressId.value = param;
    fm.submit();
}

function assignExpress(param)
{
    var fm = document.showAssignExpress;
    fm.expressId.value = param;
    fm.submit();
}

function showBillhead(param)
{
    var fm = document.detailPayRequisition;
    fm.outcomeId.value = param;
    fm.submit();
}
</script>
<s:form action="showReadBooking" namespace="/sales" theme="simple" method="POST">
<s:hidden name="bookingNo"></s:hidden>
<s:iterator value="remind">
未读订单：<a href="#" onclick="javascript:showBooking('<s:property value="bookingNo"/>')"><s:property value="customer.name"/> <s:property value="line.lineName"/> <s:property value="pax"/>人</a><br>  
</s:iterator>
</s:form>

<s:iterator value="income">
未收帐款：<a href="#" class="warnlink"><s:property value="tourNo"/>&nbsp;&nbsp;<s:property value="customer.name"/> <s:property value="expense"/> 元</a><br>
</s:iterator>

<s:iterator value="outcome">
未付帐款：<a href="#" class="warnlink"><s:property value="tourNo"/>&nbsp;&nbsp;<s:property value="customer.name"/> <s:property value="expense"/> 元</a><br>
</s:iterator>

<s:form action="detailPayRequisition" namespace="/finance" method="post" theme="simple">
<s:hidden name="outcomeId"></s:hidden>
<s:iterator value="billhead">
未读付款申请：<a href="#" class="warnlink" onclick="javascript:showBillhead('<s:property value="outcomeId"/>')"><s:property value="tourNo"/>&nbsp;&nbsp;<s:property value="customer.name"/> <s:property value="expense"/> 元</a><br>
</s:iterator>
</s:form>

<s:form action="showAssignExpress" namespace="/express" theme="simple" method="POST">
<s:hidden name="expressId"></s:hidden>
<authz:authorize ifAnyGranted="ROLE_TRANSPORT">
<s:iterator value="assignExpress">
待安排配送：<a href="#" class="warnlink" onclick="javascript:assignExpress('<s:property value="expressId"/>')"><s:property value="tourNo"/>&nbsp;&nbsp;<s:property value="context"/></a><br>
</s:iterator>
</authz:authorize>
</s:form>

<s:form action="showExpressDetail" namespace="/express" theme="simple" method="POST">
<s:hidden name="expressId"></s:hidden>
<authz:authorize ifAnyGranted="ROLE_CALLCENTER_SUPPORT">
<s:iterator value="checkExpress">
待审核配送：<a href="#" class="warnlink" onclick="javascript:showExpress('<s:property value="expressId"/>')"><s:property value="tourNo"/>&nbsp;&nbsp;<s:property value="context"/></a><br>
</s:iterator>

<s:iterator value="accountExpress">
待交款配送：<a href="#" class="warnlink" onclick="javascript:showExpress('<s:property value="expressId"/>')"><s:property value="tourNo"/>&nbsp;&nbsp;<s:property value="context"/></a><br>
</s:iterator>
</authz:authorize>
<authz:authorize ifAnyGranted="ROLE_VISA_EXAMINE">
<s:iterator value="examineExpress">
待材料审核配送：<a href="#" class="warnlink" onclick="javascript:showExpress('<s:property value="expressId"/>')"><s:property value="tourNo"/>&nbsp;&nbsp;<s:property value="context"/></a><br>
</s:iterator>
</authz:authorize>
<authz:authorize ifAnyGranted="ROLE_CALLCENTER">
<s:iterator value="againExpress">
待再次配送申请：<a href="#" class="warnlink" onclick="javascript:showExpress('<s:property value="expressId"/>')"><s:property value="tourNo"/>&nbsp;&nbsp;<s:property value="context"/></a><br>
</s:iterator>
</authz:authorize>
</s:form>


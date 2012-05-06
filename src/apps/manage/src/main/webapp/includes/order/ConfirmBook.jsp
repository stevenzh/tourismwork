<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审核订单</title>
<meta name="menu" content="OrderMenu"/>
<meta name="heading" content="审核订单">
</head>

<body>
<script language="javascript">
<!--//

function cancel()
{
  var fm = document.ConfirmBook;
  fm.action="<s:url action='ConfirmBookSearch' namespace="/sales" includeParams='none' />"
  fm.submit();
}
//-->
</script>

<s:form action="ConfirmBook" namespace="/sales" method="POST" theme="simple">
<s:hidden name="kenDepartmentId"></s:hidden>
<s:hidden name="kenEmployeeId"></s:hidden>
<s:hidden name="kenRouteName"></s:hidden>
<s:hidden name="kenStartDate"></s:hidden>
<s:hidden name="kenEndDate"></s:hidden>

<!-- 计划编号 -->
<s:hidden name="recordNo" value="%{book.plan.planNo}"></s:hidden>
<s:hidden name="book.plan.planNo"></s:hidden>
<s:hidden name="book.bookingNo"></s:hidden>
<s:hidden name="book.plan.line.lineNo"></s:hidden>
<s:hidden name="book.plan.outDate"></s:hidden>

<table style="width: 100%">
  <tr>
    <td width="20%" class="idx">订单号：</td>
    <td colspan="2"><s:property value="book.recordNo" /></td>
    <td>状态:
    <STRONG>
    <s:if test='book.confirmStatus == "1"'>已占位</s:if>
    <s:else>未占位</s:else></STRONG>
    </td>
  </tr>
  <tr>
    <td class="idx" width="8%">线路：</td>
    <td colspan="2"><s:property value="book.plan.line.lineName"/></td>
    <td width="50%">&nbsp;</td>
  </tr>
  <tr>
    <td class="idx">出发日期：</td>
    <td><s:date name="book.plan.outDate" format="yyyy-MM-dd"/></td>
    <td class="idx" width="10%">团号：</td>
    <td><s:property value="book.plan.tourNo" /></td>
  </tr>
  <tr>
    <td class="idx">联系人：</td>
    <td><s:property value="book.contact" /></td>
    <td width="20%" class="idx">联系电话：</td>
    <td><s:property value="book.phone" /></td>
  </tr>
  <tr>
    <td class="idx">参团要求：</td>
    <td colspan="3">无</td>
  </tr>
  <tr>
    <td class="idx">预订人数：</td>
    <td colspan="3"><s:property value="book.pax"/>人&nbsp;&nbsp;&nbsp;&nbsp;
    [确认人数：<s:textfield name="book.confirmPax" size="5" theme="simple"/>人、
     名单人数：<s:property value="book.importPax"/>人]</td>
  </tr>
  <tr>
    <td class="idx">备注：</td>
    <td colspan="3"><s:property value="book.remarks"/></td>
  </tr>

  <tr>
    <td colspan="4">
    <table style="width: 100%">
        <tr>
          <td class="lstidx">No.</td>
          <td class="lstidx">状态</td>
          <td class="lstidx">姓名</td>
          <td class="lstidx">身份证</td>
          <td class="lstidx">性别</td>
          <td class="lstidx">出生日期</td>
          <td class="lstidx">住房要求</td>
          <td class="lstidx">同住序号</td>
          <td class="lstidx">合同号</td>
          <td class="lstidx">发票号</td>
          <td class="lstidx">报价</td>
          <td class="lstidx">应收团款</td>
        </tr>

	      <s:iterator value="book.customerList" status="rowccount">
	      <s:hidden name="customerList(%{id}).nmno" value="%{nmno}"></s:hidden>
	      <s:hidden name="customerList(%{id}).del" value="%{del}"></s:hidden>

        <s:if test='del == "N"'>
        <tr>
          <td class="cdata"><s:property value="#rowccount.count"/></td>
          <td class="cdata">
          <s:if test="confirmStatus eq \"1\"">未审核</s:if>
          <s:else>已审核</s:else>
          </td>
          <td class="data"><a href="javascript:showName(form1,'0611X03618');" title="点击查看客人的详细内容！"><s:property value="userName"/></a></td>
          <td class="data"><s:property value="idCard"/>&nbsp;</td>
          <td class="cdata">
          <s:if test="sex eq \"M\"">男</s:if>
          <s:elseif test="sex eq \"F\"">女</s:elseif>
          <s:else>不明</s:else>
          </td>
          <td class="data"><s:date name="birthday" format="yyyy-MM-dd"/>&nbsp;</td>
          <td class="data"><s:property value="roomTypeName"/>&nbsp;</td>
          <td class="cdata"><s:property value="roomNo"/>&nbsp;</td>
          <td class="rdata"><s:property value="contractNo"/>&nbsp;</td>
          <td class="rdata"><s:property value="invoiceNo"/>&nbsp;</td>
          <td class="rdata"><s:property value="price" /></td>
          <td class="rdata">
          <s:textfield name="customerList(%{id}).receivables"
                   value="%{receivables}"
                   size="8"
                   maxlength="8"
                   theme="simple">
           </s:textfield></td>
        </tr>
        </s:if>
        
        <s:else>
        <tr bgcolor="#FFDDDD">
          <td align="center"><s:property value="#rowccount.count"/></td>
          <td align="center">已取消</td>
          <td><a href="javascript:showName(form1,'0611X03618');" title="点击查看客人的详细内容！"><s:property value="userName"/></a></td>
          <td><s:property value="idCard"/>&nbsp;</td>
          <td align="center">
          <s:if test="sex eq \"M\"">男</s:if>
          <s:elseif test="sex eq \"F\"">女</s:elseif>
          <s:else>不明</s:else>
          </td>
          <td><s:date name="birthday" format="yyyy-MM-dd"/>&nbsp;</td>
          <td><s:property value="roomTypeName"/>&nbsp;</td>
          <td align="center""><s:property value="roomNo"/>&nbsp;</td>
          <td align="right"><s:property value="contractNo"/>&nbsp;</td>
          <td align="right"><s:property value="invoiceNo"/>&nbsp;</td>
          <td align="right"><s:property value="price" /></td>
          <td align="right">&nbsp;</td>
        </tr>
        </s:else>
        </s:iterator>

        <tr bgcolor="#b9c0ff">
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td colspan="8" align="center">小 计</td>
          <td align="right">&nbsp;</td>
          <td align="right">&nbsp;</td>
          <td align="right">&nbsp;</td>
          <td class="idx"><font color="#ff0000">&nbsp;</font></td>
        </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td colspan="4" align="right">
    <s:submit value="审核"></s:submit>
    <input type="button" onclick="javascript:cancel();" value="返回">
  </td>
  </tr>
</table>

</s:form>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title>订单一览表</title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
</head>

<body>

<SCRIPT type="text/javascript">

function change()
{
  var fm = document.BookDetail;
  fm.action = "<s:url action='EditBook' namespace='/distribution' includeParams='none' />"
  fm.submit();
}

function addInvoice(pay)
{
  var fm = document.showInvoice;
  fm.orderNo.value = pay;
  fm.submit();
}

function gathing()
{
  var fm = document.BookGathering;
  fm.reserveNo.value = document.BookDetail.reserveNo.value;
  fm.submit();
}

function printInv(param, cnamount)
{
  var fm = document.reportRun;
  document.getElementById('paramid').value = param;
  document.getElementById('cnAmount').value = cnamount;
  fm.submit();
}

function submitInvoice(param)
{
  var fm = document.showInvoice;
  fm.incomeId.value = param;
  fm.submit(); 
}
</SCRIPT>

<s:form action="BookDetail" namespace="/distribution" method="POST" theme="simple">
<s:hidden name="reserveNo" value="%{book.bookingNo}"></s:hidden>

<table align="center" border="0" width="760">
  <tr>
    <td colspan="4"><strong>查看订单</strong></td>
  </tr>
  <tr>
    <td rowspan="2">状态:
    <STRONG>
    <s:if test='book.confirmStatus == "1"'>已占位</s:if>
    <s:else>未占位</s:else>
    <s:if test='book.delkey == "Y"'>已取消</s:if></STRONG>
    </td>
    <td class="idx"></td>
    <td colspan="2"></td>
  </tr>
  <tr>
    <td class="idx">线路：</td>
    <td colspan="2"><s:property value="book.plan.line.lineName"/>&nbsp;</td>
  </tr>
  <tr>
    <td class="idx">出发日期：</td>
    <td><s:date name="book.plan.outDate" format="yyyy-MM-dd"/></td>
    <td class="idx">团号：</td>
    <td><s:property value="book.plan.tourNo" /></td>
  </tr>
  <tr>
    <td class="idx">联系人：</td>
    <td><s:property value="book.contact" /></td>
    <td class="idx">联系电话：</td>
    <td><s:property value="book.phone" /></td>
  </tr>
  <tr>
    <td class="idx">参团要求：</td>
    <td colspan="3"><s:property value="book.canSplit"/></td>
  </tr>
  <tr>
    <td class="idx">预订人数：</td>
    <td colspan="3"><s:property value="book.pax"/>人&nbsp;&nbsp;&nbsp;&nbsp;
       [确认人数：<s:property value="book.confirmPax"/>人、
        名单人数：<s:property value="book.importPax"/>人]</td>
  </tr>
  <tr>
    <td class="idx">备注：</td>
    <td colspan="3"><s:property value="book.remarks"/></td>
  </tr>

  <tr>
    <td colspan="4">
    <table border="1" style="width: 100%">
      <tr>
        <td class="lstidx">&nbsp;</td>
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
      <s:if test='del == "N"'>
        <tr>
          <td class="rdata"><s:checkbox name="selects" fieldValue="%{nmno}" theme="simple"></s:checkbox></td>
          <td class="rdata"><s:property value="#rowccount.count"/></td>
          <td class="data"><s:if test='UniteStatus == "T"'>已成团</s:if><s:else>团候</s:else>&nbsp;</td>
          <td class="data"><s:property value="userName"/></td>
          <td class="data"><s:property value="idCard"/>&nbsp;</td>
          <td class="data"><s:property value="sex"/></td>
          <td class="data"><s:date name="birthday" format="yyyy-MM-dd"/>&nbsp;</td>
          <td class="data"><s:property value="roomType"/>&nbsp;</td>
          <td class="rdata"><s:property value="roomNo"/>&nbsp;</td>
          <td class="rdata"><s:property value="contractNo"/>&nbsp;</td>
          <td class="rdata"><s:property value="invoiceNo"/>&nbsp;</td>
          <td class="rdata"><s:property value="price"/></td>
          <td class="rdata"><s:property value="receivables"/>&nbsp;</td>
        </tr>
      </s:if>
      <s:else>
        <tr bgcolor="#fdfdfd">
          <td class="rdata"><s:checkbox name="selects" fieldValue="%{nmno}" theme="simple"></s:checkbox></td>
          <td class="rdata"><s:property value="#rowccount.count"/></td>
          <td class="data">取消&nbsp;</td>
          <td class="data"><s:property value="userName"/></td>
          <td class="data"><s:property value="idCard"/>&nbsp;</td>
          <td class="data"><s:property value="sex"/></td>
          <td class="data"><s:date name="birthday" format="yyyy-MM-dd"/>&nbsp;</td>
          <td class="data"><s:property value="roomType"/>&nbsp;</td>
          <td class="rdata"><s:property value="roomNo"/>&nbsp;</td>
          <td class="rdata"><s:property value="contractNo"/>&nbsp;</td>
          <td class="rdata"><s:property value="invoiceNo"/>&nbsp;</td>
          <td class="rdata"><s:property value="price"/></td>
          <td class="rdata"><s:property value="receivables"/>&nbsp;</td>
        </tr>
      </s:else>
      </s:iterator>

      <tr bgcolor="#b9c0ff">
        <td colspan="2">&nbsp;</td>
        <td colspan="9" align="center">小 计</td>
        <td align="right">&nbsp;</td>
        <td align="right">&nbsp;</td>
        <td align="right">&nbsp;</td>
        <td class="idx"><font color="#ff0000">&nbsp;</font></td>
      </tr>
    </table>
    </td>
  </tr>
  
  <tr>
    <td colspan="4"><STRONG>付款记录</STRONG></td>
  </tr>
  <tr>
    <td colspan="4">
    <table border="1" style="width: 100%">
      <tr>
        <td class="lstidx">票据号</td>
        <td class="lstidx">说明</td>
        <td class="lstidx">付款方式</td>
        <td class="lstidx">金额</td>
        <td class="lstidx">操作人</td>
        <td class="lstidx">操作时间</td>
        <td class="lstidx">发票</td>
        <td class="lstidx">操作</td>
      </tr>

      <s:iterator value="book.payments" status="rowccount">
      <tr>
        <td class="cdata"><s:property value="orderNo"/>&nbsp;</td>
        <td class="data"><s:property value="note"/>&nbsp;</td>
        <td class="cdata"><s:property value="type"/>&nbsp;</td>
        <td class="rdata"><s:property value="amount"/>&nbsp;</td>
        <td class="cdata"><s:property value="operator"/>&nbsp;</td>
        <td class="cdata"><s:date name="schedule" format="yyyy-MM-dd HH:mm"/>&nbsp;</td>
        <td class="cdata">
        <s:iterator value="invices"><a href="#" onclick="javascript:printInv('<s:property value="recordNo"/>','<s:property value="amountChinese"/>')"><s:property value="recordNo"/> 打印</a><br>
        </s:iterator>
        </td>
        <td class="cdata"><a href="#" onclick="javascript:addInvoice('<s:property value="orderNo"/>')">填写发票</a></td>
      </tr>
      </s:iterator>

      <tr bgcolor="#b9c0ff">
        <td colspan="2" align="center">小 计</td>
        <td align="right">&nbsp;</td>
        <td align="right" colspan="5">&nbsp;</td>
      </tr>
    </table>
    </td>
  </tr>

  <tr>
    <td colspan="4" align="center">
    <s:if test='book.delkey eq "N"'>
    <s:if test='book.confirmStatus == "2"'>
    <s:submit value="订单取消" method="cancel"></s:submit>
    <s:submit value="游客取消" method="cancelCustomers"></s:submit>
    <input type="button" value="修改" onclick="javascript:change();">
    </s:if>
    </s:if>
    <s:if test="book.dbamt-book.payCosts > 0">
    <input type="button" value="收款" onclick="javascript:gathing();">
    </s:if>
    <!--
    <s:submit value="换团处理" method="cancel"></s:submit>
    <s:submit value="退款" method="gathing"></s:submit>
    <s:submit value="修补合同号" method="invoice"></s:submit>
     -->

  </td>
  </tr>

</table>
</s:form>

<s:form action="BookGathering" namespace="/distribution" method="POST" theme="simple">
  <s:hidden id="reserveNo" name="reserveNo"></s:hidden>
</s:form>

<s:form action="showInvoice" namespace="/distribution" method="POST" theme="simple">
  <s:hidden id="orderNo" name="orderNo"></s:hidden>
  <s:hidden name="reserveNo" value="%{book.recordNo}"></s:hidden>
</s:form>

<s:form action="reportRun" namespace="/" method="POST" theme="simple">
  <s:hidden name="parameters(0).name" value="INV_NO"></s:hidden>
  <s:hidden id="paramid" name="parameters(0).data"></s:hidden>
  <s:hidden name="parameters(1).name" value="PRINT_LINE"></s:hidden>
  <s:hidden name="parameters(1).data" value="0"></s:hidden>
  <s:hidden name="parameters(2).name" value="CHINESE_AMOUNT"></s:hidden>
  <s:hidden id="cnAmount" name="parameters(2).data"></s:hidden>
  <s:hidden name="reportId" value="1"></s:hidden>
</s:form>

<s:form action="showInvoice" namespace="/finance" method="POST" theme="simple">
  <s:hidden name="incomeId"></s:hidden>
</s:form>
</body>
</html>

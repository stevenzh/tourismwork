<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>添加客人名单</title>
<s:head />
</head>

<body>

<table align="center" width="760" cellspacing="2">
  <tr>
    <td>填写收/退款</td>
  </tr>
</table>

<s:form action="submitBookGathering" method="post" theme="simple">
  <s:hidden name="reserveNo"></s:hidden>

  <table align="center" width="760">
    <tr>
      <td class="idx" width="80">订单号：</td>
      <td colspan="2"><s:property value="book.bookingNo" /></td>
      <td rowspan="2">状态:<s:property value="book.confirmStatus"/> </td>
    </tr>
    <tr>
      <td class="idx">线路：</td>
      <td colspan="2"><s:property value="book.plan.line.lineName"/>&nbsp;</td>
    </tr>
    <tr>
      <td class="idx">出发日期：</td>
      <td><s:date name="book.plan.outDate" format="yyyy-MM-dd"/></td>
      <td class="idx" width="80">团号：</td>
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
  </table>

  <!--以下为订单之客人明细部分 -->
  <table border="1" align="center" width="760">
    <tr>
      <td class="lstidx">No.</td>
      <td class="lstidx">状态</td>
      <td class="lstidx">姓名</td>
      <td class="lstidx">身份证</td>
      <td class="lstidx">性别</td>
      <td class="lstidx">出生日期</td>
      <td class="lstidx">报价</td>
      <td class="lstidx">应收团款</td>
    </tr>

  <s:iterator value="book.customerList" status="rowccount">
  <s:if test='del == "N"'>
  <tr>
  </s:if>
  <s:else>
  <tr bgcolor="#fdfdfd">
  </s:else>
    <td class="rdata"><s:property value="#rowccount.count"/></td>
    <td class="data"><s:property value="del"/>&nbsp;</td>
    <td class="data"><a href="javascript:showName('###');" title="点击查看客人的详细内容！"><s:property value="userName"/></a></td>
    <td class="data"><s:property value="idCard"/>&nbsp;</td>
    <td class="data"><s:property value="sex"/></td>
    <td class="data"><s:date name="birthday" format="yyyy-MM-dd"/>&nbsp;</td>
    <td class="rdata"><s:property value="price"/></td>
    <td class="rdata"><a href="#" title=""><s:property value="receivables"/></a>&nbsp;</td>
  </tr>
  </s:iterator>

    <tr bgcolor="#f0f0f0">
      <td align="right">&nbsp;</td>
      <td colspan="8">小 计</td>
      <td align="right"><s:property value="book.dbamt"/>&nbsp;</td>
      <td align="right"><s:property value="book.payCosts"/>&nbsp;</td>
      <td align="right">
        <s:textfield id="pay1"
                     value="%{book.dbamt - book.payCosts}">
        </s:textfield>&nbsp;
      </td>
    </tr>
  </table>

  <br>

  <table border="1"  align="center" width="760">
    <tr>
      <td bgcolor="#f2f2f2">付 款 方 式</td>
      <td bgcolor="#f2f2f2">金 额</td>
      <td bgcolor="#f2f2f2">摘 要</td>
    </tr>
    <tr>
      <td>
      <s:select name="payment.type"
                list="types"
                listKey="label"
                listValue="value"
                emptyOption="true">
      </s:select><font color="#ff0000">*</font>
      </td>
      <td>
        <s:textfield id="pay2"
                     name="payment.amount"
                     value="%{book.dbamt - book.payCosts}">
        </s:textfield>
      </td>
      <td><input name="payment.note" size="40" type="text"></td>
    </tr>
    <tr>
      <td colspan="4"><s:submit value="提交"></s:submit></td>
    </tr>
  </table>
</s:form>

</body>
</html>

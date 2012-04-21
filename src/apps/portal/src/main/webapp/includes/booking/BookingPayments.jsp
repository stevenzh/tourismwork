<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title>填写旅游订单内容</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:head />
</head>
<body>

<s:form action="submitOrderBooking" namespace="/accounts" method="post">
  <table width="760" align="center" border="0" cellspacing="1">
    <tr>
      <td colspan="4" align="center">预订表单</td>
    </tr>
    <tr>
      <td colspan="4" align="center">状态：以确认，未付款，未生效（付款后订单正式生效） 现在付款</td>
    </tr>
    <tr>
      <td width="100">线路:</td>
      <td colspan="3"><s:property value="booking.lineName" /></td>
    </tr>

    <tr>
      <td>发团时间:</td>
      <td><s:property value="booking.outDate" /></td>
      <td width="100">预订人</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>出发地:</td>
      <td><s:property value="booking.tourNo" /></td>
      <td>天数</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>标准价格:</td>
      <td><s:date name="booking.outDate" format="yyyy-MM-dd" /></td>
      <td>座位类型</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>可报名额:</td>
      <td><s:property value="paxSum" />人</td>
      <td>付款方式</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>总金额</td>
      <td colspan="3">&nbsp;</td>
    </tr>
    <tr>
      <td>订单备注</td>
      <td colspan="3">&nbsp;</td>
    </tr>
  </table>
  <br />
  <table width="760" align="center" border="0" cellspacing="1">
    <tr>
      <td class="lstidx">No.</td>
      <td class="lstidx">姓名</td>
      <td class="lstidx">性别</td>
      <td class="lstidx">证件号码/出生日期</td>
      <td class="lstidx">价格</td>
      <td class="lstidx">备注</td>
    </tr>

    <s:iterator value="booking.customerList" status="rowcounter">
      <tr>
        <!-- 一个客人的信息开始 序号 -->
        <td><s:property value="#rowcounter.count" /></td>
        <!-- 姓名 -->
        <td><s:property value="userName"/></td>

        <!-- 身份证号码 -->
        <td><s:property value="sex"/></td>

        <!-- 性别 -->
        <td><s:property value="idCard"/></td>

        <td></td>
        <td></td>
        <!-- 一个客人的信息结束 -->
      </tr>
    </s:iterator>

  </table>
  <br>

  <table width="760" align="center" border="0" cellspacing="1">
    <tr>
      <td class="lstidx">支付时间</td>
      <td class="lstidx">支付金额（元）</td>
      <td class="lstidx">支付结果</td>
      <td class="lstidx">支付订单号</td>
    </tr>
    <s:iterator value="payments">
      <tr>
        <td><s:textfield name="userName" size="8" maxlength="10" theme="simple">
        </s:textfield></td>
        <td><s:textfield name="idCard" size="18" maxlength="18" theme="simple">
        </s:textfield></td>
        <td>
        <s:select name="sex"
                  list="sexList"
                  listKey="label"
                  listValue="value" theme="simple">
        </s:select>
        </td>
      </tr>
    </s:iterator>
  </table>
  <br>
  <div align="center">  
  <s:submit value="网上支付" method="submit" theme="simple"></s:submit>
  </div>

</s:form>

</body>
</html>


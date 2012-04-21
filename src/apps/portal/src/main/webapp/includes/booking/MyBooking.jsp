<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:head/>
</head>

<body>
<table style="width: 100%">
 <tr>
  <td bgcolor="#99ccff">
     <table align="center" border="0" style="width: 100%">
          <tr>
            <td align="center" width="80">
            <a href="<s:url action='showMyBooking' namespace='/accounts' includeParams='none' />">旅游订单</a>&nbsp;&nbsp;|
            </td>
            <td align="center" width="80">
            <a href="<s:url action='showMyBooking' namespace='/accounts' includeParams='none' />">酒店订单</a>&nbsp;&nbsp;|
            </td>
            <td align="center" width="80">
            <a href="<s:url action='showMyBooking' namespace='/accounts' includeParams='none' />">机票订单</a>&nbsp;&nbsp;|
            </td>
            <td align="center">&nbsp;</td>
          </tr>
        </table>
    </td>
  </tr>      

 <tr>
  <td valign="top" bgcolor="#aaaaee">
  <table style="width: 100%" align="center" border="0" cellspacing="1">
  
   <tr align="center">
    <td>订单号</td>
    <td>旅游线路</td>
    <td>开班日期</td>
    <td>人数</td>
    <td>应付金额</td>
    <td>预订时间</td>
    <td>订单状态</td>
    <td>操作</td>
  </tr>

  <s:iterator value="bookings">
  <tr>
    <td><s:property value="bookingNo"/></td>
    <td><s:property value="lineName"/></td>
    <td><s:date name="outDate" format="yyyy-MM-dd"/></td>
    <td><s:property value="pax"/></td>
    <td><s:property value="dbamt"/></td>
    <td><s:date name="reserveDate" format="MM-dd HH:ss"/></td>
    <td>以确认<br>未付款<br>已生效
    </td>
    <td>
    <a href="#">付款</a><br>
    <a href="<s:url action='showBookingDetail' namespace='/accounts' includeParams='none'/>?reserveNo=<s:property value="reserveNo"/>">详细</a><br>
    <a href="#">删除</a>
    </td>
  </tr>
  </s:iterator>

  </table>
  </td>
 </tr>
</table>
</body>
</html>


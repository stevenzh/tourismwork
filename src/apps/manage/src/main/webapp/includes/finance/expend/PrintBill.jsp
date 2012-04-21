<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>打印付款申请书</title>
<meta name="menu" content="FinanceMenu"/>
<meta name="heading" content="打印付款申请书">
</head>

<body>
<br>
<br>
<table border="0" width="800" align="center">
<tr>
  <td>
<table border="0" style="width: 100%">
   <tr>
     <td align="center" height="40"><h1>付款申请书</h1></td>
   </tr>
   <tr>
     <td height="40">致：<s:property value="billhead.supplierName"/></td>
   </tr>
   <tr>
     <td height="30">　　<s:property value="billhead.note"/>&nbsp;</td>
   </tr>
 </table>
 </td>
 </tr>
<tr>
  <td>
<table style="width: 100%" border="1" cellpadding="1">
   <tr align="center" height="30">
          <th width="30">No.</th>
          <th width="240">团号</th>
          <th>内容</th>
          <th width="100">现付款</th>
      </tr>
   
 <s:if test="billhead.payNoticeList.isEmpty() == false">     
  <s:iterator value="billhead.payNoticeList" status="rowcounter">
  <tr height="30">
    <td width="40" align="center"><s:property value="#rowcounter.count"/></td>
    <td align="center"><s:property value="tourNo"/>&nbsp;</td>
    <td><s:property value="description"/>&nbsp;</td>
    <td align="right">&nbsp;
      <s:property value="nowpayPayment"/></td>
  </tr>
  </s:iterator>
  
  </s:if>
  </table>
 </td>
 </tr>
 
 
 <tr>
   <td>
     <table border="0" style="width: 100%">
   <tr height="40">
     <td>付款合计：<s:property value="billhead.amount"/>元</td>
      <td>付款方式：<s:property value="billhead.payModeName"/></td>
   </table>
   </td>
 </tr>
 <tr>
   <td>
     <table border="0" style="width: 100%">
     <tr height="30">
     <td>经理：</td>
     <td>&nbsp;</td>
     <td>工作组主管：</td>
     <td>&nbsp;</td>
     <td>经办人：</td>
     <td>&nbsp;</td>
     <td>借款人：</td>
     <td>&nbsp;</td>
     <td>工作组：</td>
     <td>&nbsp;</td>
     <td>日期：</td>
     <td>&nbsp;</td>
   </tr>
   </table>
   </td>
 </tr>
</table>
  
</body>
</html>
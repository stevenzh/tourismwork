<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>港澳游名单打印 : 选择名单</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="港澳游名单打印 : 选择名单">
</head>

<body>
<s:form action="ToursSearchGANext" namespace="/operator" method="post" theme="simple">
<s:hidden name="kenDepartmentId"></s:hidden>
<s:hidden name="kenEmployeeId"></s:hidden>
<s:hidden name="kenRouteName"></s:hidden>
<s:hidden name="kenStartDate"></s:hidden>
<s:hidden name="kenEndDate"></s:hidden>
<s:hidden name="kenBuildStartDate"></s:hidden>
<s:hidden name="kenBuildEndDate"></s:hidden>
<s:hidden name="tourNos"></s:hidden>
<s:hidden name="tourNum"></s:hidden>

<s:iterator value="tours">
<table align="center" border="0" bordercolor="#000000" cellpadding="2" cellspacing="2" width="100%">
  <tr>
    <td class="idx" width="15%">团号:</td>
    <td colspan="2"><s:property value="tourNo"/></td>
    <td width="35%">&nbsp;</td>
  </tr>
  <tr>
    <td class="idx" width="15%">线路号：</td>
    <td colspan="2">
    <s:property value="routeNo"/></td>
    <td width="35%">&nbsp;</td>
  </tr>
  <tr>
    <td class="idx" width="15%">线路名：</td>
    <td colspan="2">
    <s:property value="line.lineName"/></td>
    <td width="35%">&nbsp;</td>
  </tr>
  <tr>
    <td class="idx">出境日期：</td>
    <td><s:date name="outDate" format="yyyy-MM-dd"/></td>
    <td class="idx" width="15%">出境口岸：</td>
    <td><s:property value="outCity"/></td>
  </tr>
  <tr>
    <td class="idx">入境日期：</td>
    <td><s:date name="inDate" format="yyyy-MM-dd"/></td>
    <td class="idx">入境口岸：</td>
    <td><s:property value="inCity"/></td>
  </tr>
  <tr>
    <td class="idx">聚合城市</td>
    <td><s:property value="venue"/></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td class="idx">总人数：</td>
    <td colspan="3"><s:property value="pax"/>人&nbsp;
    （男:<s:property value="malePax"/>人、女:<s:property value="femalePax"/>人、领队:<s:property value="leadPax"/>人)
     </td>
  </tr>
  <tr>
    <td class="idx">订房数：</td>
    <td colspan="3">
       双人间:<s:property value="doubleRoom"/>&nbsp;单人间:<s:property value="singleRoom"/>&nbsp;加床:<s:property value="extraBedRoom"/>
    </td>
  </tr>
  <tr>
    <td class="idx">备注：</td>
    <td colspan="3"><s:property value="remarks"/></td>
  </tr>

    
  
  <tr>
    <td colspan="4">
    <table bordercolor="#000000" border="0" cellpadding="0" cellspacing="1" width="100%">
        <tr>
          <td class="lstidx">No.</td>
          <td class="lstidx">选择</td>
          <td class="lstidx">姓名</td>
          <td class="lstidx">姓名拼音</td>
          <td class="lstidx">性别</td>
          <td class="lstidx">出生日期</td>
          <td class="lstidx">出生地</td>
          <td class="lstidx">护照种类</td>
          <td class="lstidx">护照号码</td>
          <td class="lstidx">签发日期、地点</td>
          <td class="lstidx">护照说明</td>
          <td class="lstidx">备注</td>
        </tr>

    <s:iterator value="customerList" status="rowccount">
        <tr>
          <td class="rdata"><s:property value="#rowccount.count"/></td>
          <td class="cdata"><s:checkbox  name="tourNum" fieldValue="%{nmno}" value="%{stauts}"/></td>
          <td class="data"><a href="#" title="显示客户明细" onclick="javascript:showCustomer('<s:property value="nmno"/>');"><s:property value="realName"/></a></td>
          <td class="data"><s:property value="pinYin"/></td>
          <td class="data"><s:property value="sex"/></td>
          <td class="data"><s:date name="birthday" format="yyyy-MM-dd"/>&nbsp;</td>
          <td class="data"><s:property value="birthplace"/>&nbsp;</td>
          <td class="data"><s:property value="passportType" /></td>
          <td class="data"><s:property value="passportNo"/></td>
          <td class="data"><s:date name="passportDate" format="yyyy-MM-dd"/> <s:property value="passportPlace"/></td>
          <td class="data"><s:date name="printPassportAnnotation"/>&nbsp;</td>
          <td class="data"><s:property value="passportAnnotation"/></td>
        </tr>
        </s:iterator>

    </table>
    </td>
  </tr>
</table>
</s:iterator>



<s:if test="tours.isEmpty() == false">
<s:submit action="ToursSearchGAConfirm" value="确定"></s:submit>
<s:submit action="ToursSearchGABack" value="再加入名单"></s:submit>
</s:if>
</s:form>

</body>
</html>

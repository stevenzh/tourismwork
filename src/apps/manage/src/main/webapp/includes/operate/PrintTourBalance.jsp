<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<s:url value='/stylesheet/layout.css' encode='false' includeParams='none'/>" type="text/css" media="all">
<link rel="stylesheet" href="<s:url value='/stylesheet/global.css' encode='false' includeParams='none'/>" type="text/css" media="all">
<title>打印单团核算单</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="打印单团核算单">
</head>

<body>
<table align="center" border="0" width="800">
<tr>
<td>

    <table border="0" style="width: 100%">
       <tr>
        <td align="center"><h1><s:property value="singleTourBalance.tourNo" />旅游团队结算帐单</h1></td>
      </tr>
      <tr>
        <td><h4>&nbsp;操作团队：<s:property value="singleTourBalance.team.name" /></h4></td>
      </tr>
    </table>

    <table border="1" style="width: 100%">
      <tr>
        <td class="idx">线路</td>
        <td colspan="5" class="data">&nbsp;<s:property
          value="singleTourBalance.line.lineName" />&nbsp;</td>
      </tr>
       <tr>
        <td class="idx">团号</td>
        <td colspan="5" class="data">&nbsp;<s:property
          value="singleTourBalance.tourNo" />&nbsp;</td>
      </tr>

      <tr>
        <td class="idx">出发日期</td>
        <td class="data">
          &nbsp;<s:date name="singleTourBalance.outDate" format="yyyy-MM-dd"/>&nbsp;
        </td>
        <td class="idx">天数</td>
        <td colspan="3" class="data">
          <s:property value="singleTourBalance.line.lineDay" />&nbsp;
        </td>
      </tr>

      <tr>
        <td class="idx" width="80">客人人数</td>
        <td class="data">&nbsp;<s:property
          value="singleTourBalance.allPax" />&nbsp;</td>
        <td class="idx" width="80">领 队 数</td>
        <td class="data">&nbsp;<s:property
          value="singleTourBalance.leaderPax" />&nbsp;</td>
        <td class="idx" width="80">领队名单</td>
        <td class="data">&nbsp;<s:property
          value="singleTourBalance.leaderName" />&nbsp;</td>
      </tr>

      <tr>
        <td class="idx">房间情况</td>
        <td colspan="5" class="data">&nbsp;双人间：<s:property
          value="singleTourBalance.doubleRoom" />&nbsp; &nbsp;单人间：<s:property
          value="singleTourBalance.singleRoom" />&nbsp; &nbsp;加床间：<s:property
          value="singleTourBalance.extraBedRoom" /></td>
      </tr>

      <tr>
        <td class="idx">应 收 款</td>
        <td class="data">&nbsp;<s:property value="singleTourBalance.muAmount" />&nbsp;</td>
        <td class="idx">已 收 款</td>
        <td class="data">&nbsp;<s:property value="singleTourBalance.alAmount" />&nbsp;</td>
        <td class="idx">未 收 款</td>
        <td class="data">&nbsp;<s:property value="singleTourBalance.wiAmount" />&nbsp;</td>
      </tr>

      <tr>
        <td class="idx">备　　注</td>
        <td colspan="5" class="data">&nbsp;<s:property
          value="singleTourBalance.remarks" />&nbsp;</td>
      </tr>
      </table>
      
      <h4>一、结算明细表</h4>
      
      <table border="1" style="width: 100%">
      <tr>
        <td class="idx" width="80">减免人数</td>
        <td class="data">&nbsp;<s:property
          value="singleTourBalance.exemptPax" />&nbsp;</td>
        <td class="idx" width="80">儿童人数</td>
        <td class="data">&nbsp;<s:property
          value="singleTourBalance.childPax" />&nbsp;</td>
        <td class="idx" width="80">结算人数</td>
        <td class="data">&nbsp;<s:property
          value="singleTourBalance.pax" />&nbsp;</td>
      </tr>

      <tr>
        <td class="idx">旅游团队总收入</td>
        <td class="data">&nbsp;<s:property
          value="singleTourBalance.amount" />&nbsp;</td>
        <td class="idx">纯 团 费</td>
        <td class="data">&nbsp;<s:property
          value="singleTourBalance.tourAmount" />&nbsp;</td>
        <td class="idx">成本合计</td>
        <td class="data">&nbsp;<s:property
          value="singleTourBalance.cost" />&nbsp;</td>
      </tr>

      <tr>
        <td class="idx">毛　　利</td>
        <td class="data">&nbsp;<s:property
          value="singleTourBalance.grossAmount" />&nbsp;</td>
        <td class="idx">毛 利 率</td>
        <td class="data">&nbsp;<s:property
          value="singleTourBalance.grossAmountRate" />%&nbsp;</td>
        <td class="idx">人均毛利</td>
        <td class="data">&nbsp;<s:property
          value="singleTourBalance.grossAmountAverage" />&nbsp;</td>
      </tr>
    </table>
    
    <h4>二、营业收入</h4>
    
    <table border="1" style="width: 100%">
            <tr>
              <td class="lstidx" width="30">NO.</td>
              <td class="lstidx">客户</td>
              <td class="lstidx">销售员</td>
              <td class="lstidx">预订人数</td>
              <td class="lstidx">应收款</td>
              <td class="lstidx">已收款</td>
              <td class="lstidx">未收款</td>
            </tr>
            <s:if test="bookList!=null">
              <s:iterator value="bookList" status="rowCounter">
                <tr>
                  <td class="cdata"><s:property value="#rowCounter.count" />&nbsp;</td>
                  <td class="data"><s:property value="customer.name" />&nbsp;</td>
                  <td class="cdata"><s:property value="salesman.userName" />&nbsp;</td>
                  <td class="rdata"><s:property value="pax" /></td>
                  <td class="rdata"><s:property value="dbamt + finalExpense" /></td>
                  <td class="rdata"><s:property value="payCosts" /></td>
                  <td class="rdata"><s:property value="unPay" /></td>
                </tr>
              </s:iterator>
            </s:if>
            <tr>
              <td class="data">&nbsp;</td>
              <td class="data" colspan="2">
              <center>合计</center>
              </td>
              <td class="rdata"><s:property value="singleTourBalance.totalPax" /></td>
              <td class="rdata"><s:property value="singleTourBalance.muAmount" /></td>
              <td class="rdata"><s:property value="singleTourBalance.alAmount" /></td>
              <td class="rdata"><s:property value="singleTourBalance.wiAmount" /></td>
            </tr>

          </table>
    
    <h4>三、营业成本</h4>
    
    <table border="1" style="width: 100%">
          <tr>
            <td class="lstidx" width="30">NO.</td>
            <td class="lstidx">费用类型</td>
            <td class="lstidx">应付款单位</td>
            <td class="lstidx">摘要</td>
            <td class="lstidx">汇率</td>
            <td class="lstidx">单价</td>
            <td class="lstidx">数量</td>
            <td class="lstidx">单位</td>
            <td class="lstidx">金额</td>
          </tr>
          <s:if test="singleTourBalance.costList!=null">
            <s:iterator value="singleTourBalance.costList"
              status="rowCounter">
              <tr>
                <td class="cdata"><s:property
                  value="#rowCounter.count" /></td>
                <td class="data"><s:property value="costType" />&nbsp;</td>
                <td class="data"><s:property value="supplier" />&nbsp;</td>
                <td class="data"><s:property value="description" />&nbsp;</td>
                <td class="rdata"><s:property value="roe" /></td>
                <td class="rdata"><s:property value="unitPrice" /></td>
                <td class="rdata"><s:property value="count" /></td>
                <td class="cdata"><s:property value="unit" /></td>
                <td class="rdata"><s:property value="amount" /></td>
              </tr>
            </s:iterator>
          </s:if>
    </table>
    <br>
    
    <table border="1" style="width: 100%">
     <tr>
       <td class="lstidx" width="10%">操作人</td>
       <td class="data" width="15%"><s:property value="singleTourBalance.oprateUser"/>&nbsp;</td>
       <td class="lstidx" width="10%">建团日期</td>
       <td class="data" width="15%"><s:date name="singleTourBalance.CTourDate" format="yyyy-MM-dd HH:mm"/>&nbsp;</td>
       <td class="lstidx" width="10%">制单人</td>
       <td class="data" width="15%"><s:property value="singleTourBalance.opUserName"/>&nbsp;</td>
       <td class="lstidx" width="10%">制单日期</td>
       <td class="data" width="15%"><s:date name="singleTourBalance.opDate" format="yyyy-MM-dd HH:mm"/>&nbsp;</td>
     </tr>
     <tr>
       <td class="lstidx">审核人</td>
       <td class="data"><s:property value="singleTourBalance.frUserName"/>&nbsp;</td>
       <td class="lstidx">审核日期</td>
       <td class="data"><s:date name="singleTourBalance.frDate" format="yyyy-MM-dd HH:mm"/>&nbsp;</td>
       <td class="data">&nbsp;</td>
       <td class="data">&nbsp;</td>
       <td class="data">&nbsp;</td>
       <td class="data">&nbsp;</td>
     </tr>
    </table>

</td>
</tr>
</table>
</body>
</html>
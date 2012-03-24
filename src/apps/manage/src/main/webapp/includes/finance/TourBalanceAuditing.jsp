<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审核单团核算单</title>
<meta name="menu" content="FinanceMenu"/>
<meta name="heading" content="查看单团核算单">
</head>

<body>
<script language="JavaScript">
<!--//

function goBack()
{
  var fm=document.SingleBalanceAuditing;
  fm.action="<s:url action='SingleTourBalanceSearch' namespace='/finance' includeParams='none'/>";
  fm.submit();
}

//-->
</script>
<s:if test='status!="U"'>
  <s:form action="SingleBalanceAuditing" namespace="/finance" method="post" theme="simple">
    <s:hidden name="tourNo"></s:hidden>

    <table border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td colspan="6" align="center"><h2><s:property value="singleTourBalance.tourNo" />旅游团队结算帐单</h2></td>
      </tr>
      <tr>
        <td colspan="6"><h4>&nbsp;操作工作组：<s:property value="singleTourBalance.team.name" /></h4></td>
      </tr>
    </table>

    <table border="1" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="idx">线路</td>
        <td colspan="5" class="data">&nbsp;<s:property value="singleTourBalance.line.lineName" />&nbsp;</td>
      </tr>
       <tr>
        <td class="idx">团号</td>
        <td colspan="5" class="data">&nbsp;<s:property value="singleTourBalance.tourNo" />&nbsp;</td>
      </tr>
  
      <tr>
        <td class="idx">出发日期</td>
        <td class="data">
          &nbsp;<s:date name="singleTourBalance.outDate" format="yyyy-MM-dd"/>&nbsp;
        </td>
        <td class="idx">天数</td>
        <td colspan="3" class="data">
          &nbsp;<s:property value="singleTourBalance.line.lineDay" />&nbsp;</td>
      </tr>
  
      <tr>
       <td class="idx" width="80">客人人数</td>
       <td class="data">&nbsp;<s:property value="singleTourBalance.allPax" />&nbsp;</td>
       <td class="idx" width="80">领 队 数</td>
       <td class="data">&nbsp;<s:property value="singleTourBalance.leaderPax" />&nbsp;</td>
       <td class="idx" width="80">领队名单</td>
       <td class="data">&nbsp;<s:property value="singleTourBalance.leaderName" />&nbsp;</td>
      </tr>
  
      <tr>
       <td class="idx">房间情况</td>
       <td colspan="5" class="data">
       &nbsp;双人间：<s:property value="singleTourBalance.doubleRoom" />&nbsp;
       &nbsp;单人间：<s:property value="singleTourBalance.singleRoom" />&nbsp;
       &nbsp;加床间：<s:property value="singleTourBalance.extraBedRoom" />
       </td>
      </tr>
  
      <tr>
        <td class="idx">应收款</td>
        <td class="data"><font color="red">&nbsp;<s:property value="singleTourBalance.muAmount" />&nbsp;</font></td>
        <td class="idx">已收款</td>
        <td class="data"><font color="red">&nbsp;<s:property value="singleTourBalance.alAmount" />&nbsp;</font></td>
        <td class="idx">未收款</td>
        <td class="data"><font color="red">&nbsp;<s:property value="singleTourBalance.wiAmount" />&nbsp;</font></td>
      </tr>
  
      <tr>
        <td class="idx">备注</td>
        <td colspan="5" class="data">&nbsp;<s:property value="singleTourBalance.remarks" />&nbsp;</td>
      </tr>
    </table>
    
    <h4>一、结算明细表</h4>
    
     <table border="1" cellpadding="0" cellspacing="0" width="100%">
     <tr>
       <td class="idx" width="80">减免人数</td>
       <td class="data">&nbsp;<s:property value="singleTourBalance.exemptPax" />&nbsp;</td>
       <td class="idx" width="80">儿童人数</td>
       <td class="data">&nbsp;<s:property value="singleTourBalance.childPax" />&nbsp;</td>
       <td class="idx" width="80">结算人数</td>
       <td class="data">&nbsp;<s:property value="singleTourBalance.pax" />&nbsp;</td>
     </tr>
  
     <tr>
       <td class="idx">总收入</td>
       <td class="data">&nbsp;<s:property value="singleTourBalance.amount" />&nbsp;</td>
       <td class="idx">纯团费</td>
       <td class="data">&nbsp;<s:property value="singleTourBalance.tourAmount" />&nbsp;</td>
       <td class="idx">成本合计</td>
       <td class="data">&nbsp;<s:property value="singleTourBalance.cost" />&nbsp;</td>
     </tr>
  
     <tr>
       <td class="idx">毛利</td>
       <td class="data">&nbsp;<s:property value="singleTourBalance.grossAmount" />&nbsp;</td>
       <td class="idx">毛利率</td>
       <td class="data">&nbsp;<s:property value="singleTourBalance.grossAmountRate" />%&nbsp;</td>
       <td class="idx">人均毛利</td>
       <td class="data">&nbsp;<s:property value="singleTourBalance.grossAmountAverage" />&nbsp;</td>
     </tr>
     <tr>
         <td class="idx">其它收入</td>
         <td class="data">&nbsp;<s:property value="singleTourBalance.extrIncome"></s:property></td>
         <td class="idx">收入说明</td>
         <td bgcolor="white" colspan="3">&nbsp;<s:property value="singleTourBalance.extrIncomeDec"/>
         </td>
       </tr>
   </table>
  
   <h4>二、营业收入</h4>
   
   <table border="1" cellpadding="0" cellspacing="0" width="100%">
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
           <td class="cdata"><s:property value="#rowCounter.count" /></td>
           <td class="data"><s:property value="customer.name" />&nbsp;</td>
           <td class="cdata"><s:property value="salesman.userName" />&nbsp;</td>
           <td class="rdata"><s:property value="pax" /></td>
           <td class="rdata"><s:property value="dbamt" /></td>
           <td class="rdata"><font color="red"><s:property value="payCosts" /></font></td>
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
       <td class="rdata"><font color="red"><s:property value="singleTourBalance.alAmount" /></font></td>
       <td class="rdata"><s:property value="singleTourBalance.wiAmount" /></td>
     </tr>
  
   </table>
   
   <h4>三、营业成本</h4>
   
   <table border="1" cellpadding="0" cellspacing="0" width="100%">
     <tr>
       <td class="lstidx" width="30">NO.</td>
       <td class="lstidx">费用类型</td>
       <td class="lstidx">应付款单位</td>
       <td class="lstidx">摘要</td>
       <td class="lstidx">币种</td>
       <td class="lstidx">汇率</td>
       <td class="lstidx">单价(外币)</td>
       <td class="lstidx">数量</td>
       <td class="lstidx">单位</td>
       <td class="lstidx">金额(人民币)</td>
     </tr>
     <s:if test="singleTourBalance.costList!=null">
       <s:iterator value="singleTourBalance.costList"
         status="rowCounter">
         <tr>
           <td class="cdata"><s:property
             value="#rowCounter.count" /></td>
           <td class="data"><s:property value="costType" />&nbsp;</td>
           <td class="data"><s:property value="supplier" />&nbsp;</td>
           <td class="data">&nbsp;<s:property value="description" /></td>
           <td class="rdata"><s:property value="currency" /></td>
           <td class="rdata"><s:property value="roe" /></td>
           <td class="rdata"><s:property value="unitPrice" /></td>
           <td class="rdata"><s:property value="count" /></td>
           <td class="cdata"><s:property value="unit" /></td>
           <td class="rdata"><s:property value="amount" /></td>
         </tr>
       </s:iterator>
     </s:if>
   </table>
   
   <h4>&nbsp;</h4>
   
   <table border="1" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <td class="lstidx">操作人</td>
      <td class="data"><s:property value="singleTourBalance.oprateUser"/>&nbsp;</td>
      <td class="lstidx">建团日期</td>
      <td class="data"><s:date name="singleTourBalance.CTourDate" format="yyyy-MM-dd HH:mm"/>&nbsp;</td>
      <td class="lstidx">制单人</td>
      <td class="data"><s:property value="singleTourBalance.opUserName"/>&nbsp;</td>
      <td class="lstidx">制单日期</td>
      <td class="data"><s:date name="singleTourBalance.opDate" format="yyyy-MM-dd HH:mm"/>&nbsp;</td>
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

  <table border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td colspan="6">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="6" align="center">
        <s:if test='status!="Y"'>
          <s:submit value="审核"></s:submit>
        </s:if>
        <input type="button" value="返回" onClick="javascript:goBack();">
        </td>
      </tr>
      <tr>
        <td colspan="6">&nbsp;</td>
      </tr>
    </table>

    <s:hidden name="kenDepartmentId"></s:hidden>
    <s:hidden name="kenEmployeeId"></s:hidden>
    <s:hidden name="kenRouteName"></s:hidden>
    <s:hidden name="kenStartDate"></s:hidden>
    <s:hidden name="kenEndDate"></s:hidden>
    <s:hidden name="kenBuildStartDate"></s:hidden>
    <s:hidden name="kenBuildEndDate"></s:hidden>

  </s:form>
</s:if>
<s:elseif test='status=="U"'>
  <div>
    <input type="button" value="返回" onClick="javascript:history.go(-1);">
  </div>
</s:elseif>

</body>
</html>
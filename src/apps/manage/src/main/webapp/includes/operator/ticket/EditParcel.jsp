<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>机票配送操作</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="机票配送操作">
</head>

<body>
<script language="JavaScript">
<!--//

function detail(no)
{
  var fm = document.TourDetail;
  fm.tourNo.value = no;
  fm.submit();
}

function start1(param)
{
  var fm=document.editParcelAction;
  fm.action="<s:url action='startParcelAction' namespace='/operator'/>";
  fm.outcomeId.value=param;
  fm.submit();
}

function complete1(param)
{
   var fm=document.editParcelAction;
   fm.action="<s:url action='completeParcelAction' namespace='/operator'/>";
   fm.outcomeId.value=param;
   fm.submit();
}

function goBack()
{
   var fm=document.editParcelAction;
   fm.action="<s:url action='listParcel' namespace='/operator'/>";
   fm.submit();
}
//-->
</script>

<s:form action="editParcelAction" namespace="/operator" method="post" theme="simple">
  <s:hidden name="kenDepartment"></s:hidden>
  <s:hidden name="kenMaker"></s:hidden>
  <s:hidden name="kenStartDate"></s:hidden>
  <s:hidden name="kenEndDate"></s:hidden>
  <s:hidden name="kenStartOutDate"></s:hidden>
  <s:hidden name="kenEndOutDate"></s:hidden>
  <s:hidden name="kenCarryStatus"></s:hidden>

  <s:hidden name="outcomeId"></s:hidden>

 <h3>应付客户：<s:property value="supplier.supplierName"/></h3>
 <table border="1" cellpadding="0" cellspacing="0" width="100%">
   <tr>
     <td class="lstidx">取票地址</td>
     <td class="data" colspan="3"><s:property value="supplier.address"/>&nbsp;</td>
   </tr>
   <tr>
     <td class="lstidx">联系人</td>
     <td class="data" colspan="3"><s:property value="supplier.contact"/>&nbsp;</td>
   </tr>
   <tr>
     <td class="lstidx">电话</td>
     <td class="data"><s:property value="billhead.phone"/>&nbsp;</td>
     <td class="lstidx">手机</td>
     <td class="data"><s:property value="billhead.contactMobile"/>&nbsp;</td>
   </tr>
   </table>
   <br>
   
 <table border="1" cellpadding="0" cellspacing="0" width="100%">
   <tr>
     <td class="lstidx">申请人</td>
     <td class="data"><s:property value="billhead.createdByName"/>&nbsp;</td>
   </tr>
   <tr>
     <td class="lstidx">配送说明</td>
     <td class="data" colspan="3"><s:property value="billhead.carryNote"/>&nbsp;</td>
   </tr>
   </table>
 <table border="1" cellpadding="0" cellspacing="0" width="100%">
   <tr>
      <td class="lstidx">No.</td>
      <td class="lstidx">团号</td>
      <td class="lstidx">内容</td>
      <td class="lstidx">申请金额</td>
 </tr>
   
 <s:if test="billhead.payNoticeList.isEmpty() == false">     
  <s:iterator value="billhead.payNoticeList" status="rowcounter">
  <tr>
    <td class="cdata"><s:property value="#rowcounter.count"/></td>
    <td class="data"><a href="#" title="查看出团信息" onClick="javascript:detail('<s:property value="tourNo"/>');"><s:property value="tourNo"/></a></td>
    <td class="data"><s:property value="description"/>&nbsp;</td>
    <td class="rdata"><s:property value="nowpayPayment"/></td>
  </tr>
  </s:iterator>
  </s:if>
  </table>
  
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <td class="data">配送时间：</td>
      <td>
        <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="billhead.carryLastDate" >
        </sj:datepicker>
      </td>
    </tr>
    <tr>
      <td class="data">配送人：</td>
      <td>
        <s:textfield name="billhead.carryWorker"></s:textfield>
      </td>
    </tr>
    <tr>
      <td colspan="2" align="center">
         <s:if test="billhead.frApprovedFlag eq 'Y'">
         <s:if test="billhead.carryStatus == 0">
         <input type="button" value="开始" onClick="javascript:start1('<s:property value="billhead.outcomeId"/>');">
         </s:if>
         <s:if test="billhead.carryStatus == 1">
         <input type="button" value="完成" onClick="javascript:complete1('<s:property value="billhead.outcomeId"/>');">
         </s:if>
         </s:if>
         <input type="button" value="返回" onClick="javascript:goBack();">
      </td>
    </tr>
  </table>
</s:form>

<s:form action="TourDetail" namespace="/finance" method="post" theme="simple">
  <s:hidden name="tourNo"></s:hidden>
</s:form>

</body>
</html>
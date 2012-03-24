<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改应收帐款</title>
<meta name="menu" content="FinanceMenu"/>
<meta name="heading" content="修改应收帐款">
</head>

<body>
<script language="JavaScript">
<!--//

function checkOut(para)
{
  var fm=document.MustPayModify;
  var adjustExpense;
  var adjustReason;
 
 // for(var i=0;i<para;i++)
 // {
   //  adjustExpense=document.getElementById('MustPayModify_bookList('+ i + ')_adjustExpense');
  //   adjustReason=document.getElementById('MustPayModify_bookList('+ i + ')_adjustReason');
   //  if(adjustExpense.value=="")
    // {
     //   alert("第"+(i+1)+"行，"+"调整为不能为空！");
     //   return;
     //}
   // if(adjustExpense.value!=0)
   //  if(adjustReason.value=="")
    // {
     //   alert("第"+(i+1)+"行，"+"调整原因不能为空！");
     //   return;
   //  }
    
 // }
    fm.action = "<s:url action='MustPayModify' namespace='/finance'/>";
    fm.submit();
}



//-->
</script>
<s:form action="MustPayModify" namespace="/finance"  method="post" theme="simple">
  <s:hidden name="tourNo"></s:hidden>
  
  <table border="0" cellpadding="0" width="100%">
    <tr>
      <td colspan="8"></td>
    </tr>
    <tr>
      <td class="lstidx">NO.</td>
      <td class="lstidx">客户</td>
      <td class="lstidx">人数</td>
      <td class="lstidx">原金额</td>
      <td class="lstidx">调整为</td>
      <td class="lstidx">调整原因</td>
      <td class="lstidx">上次调整人</td>
      <td class="lstidx">调整日期</td>
    </tr>
    
  <s:if test="bookList!=null">
    <s:iterator value="bookList" status="rowCounter">
    <tr>
      <td class="cdata"><s:property value="#rowCounter.count" /><s:hidden name="bookList(%{id}).recordNo" value="%{recordNo}"/></td>
      <td class="data"><s:property value="customer.name" /><s:hidden name="bookList(%{id}).customer.name" value="%{customer.name}"/></td>
      <td class="rdata"><s:property value="pax" /><s:hidden name="bookList(%{id}).pax" value="%{pax}"/></td>
      <td class="rdata"><s:property value="dbamt" /><s:hidden name="bookList(%{id}).finalExpense" value="%{finalExpense}"/><s:hidden name="bookList(%{id}).dbamt" value="%{dbamt}"/></td>
      <td class="cdata"><s:textfield cssClass="rdata" name="bookList(%{id}).adjustExpense" value="%{adjustExpense}"/></td>
      <td class="cdata"><s:textfield name="bookList(%{id}).adjustReason" value="%{adjustReason}"/></td>
      <td class="data"><s:property value="lastAdjustBy" /></td>
      <td class="cdata"><s:date name="lastAdjustDate" format="yyyy-MM-dd"/></td>
    </tr>
    </s:iterator>
  </s:if>
    <tr>
      <td colspan="8">&nbsp;</td>
    </tr>
    <tr>
      <td align="center" colspan="8">
        <input type="button" value="保存修改" onClick="javascript:checkOut('<s:property value="bookList.size()" />')"> 
        <s:submit action="SingleTourBalanceModifyInput" value="返回"></s:submit>
      </td>
    </tr>
    <tr>
      <td colspan="8">&nbsp;</td>
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

</body>
</html>
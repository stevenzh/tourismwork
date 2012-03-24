<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实付登记</title>
<meta name="menu" content="FinanceMenu"/>
<meta name="heading" content="实付登记">
</head>

<body>
<script type="text/javascript">
<!--//

function changedp()
{
  var fm = document.FactualRegister;
  var departments = document.getElementById("kenDepartmentId");
  var combo = document.getElementById("kenEmployeeId");
  var sdpt_no = departments.value;

  //更换目的地的内容
  //将原表中的内容清空
  while (combo.options.length>0) {
      combo.remove(0);
  }
  combo.disabled=false;
  
  $.ajax({
      type: "post",
      url: '<s:url value="/json/listEmployee.jspa" encode="false" includeParams="none"/>',
      timeout: 20000,
      error: function(){
          // alert('服务器错误');
      },
      data: {groupId: sdpt_no},
      async: false,
      success: function(data){
          $.each(data, function(i, n){

          if (typeof(n)=='object')
          {
              var kOption = document.createElement('OPTION');
              kOption.text = "全部";
              kOption.value = "";
              combo.options.add(kOption);
              
              $.each(n,function(j,m){
                  var oOption = document.createElement('OPTION');
                  oOption.text = m ;
                  oOption.value = j ;
                  combo.options.add(oOption);
              });
          }
      });
      }
  });
}



function checkOut()
{
   var fm = document.FactualRegister;
   var field = fm.outcomeIds;
   var isChecked=-1;
   
   if(field.length != null)
   {
     for(var i=0;i<field.length;i++)
     {
       if(field[i].checked)
       {
         isChecked = i;
         break;
       }
     }
   }
   else if(field.checked)
          {
            isChecked=1;
          }
   
   
   if(isChecked == -1)
   {
      alert("你没有选择任何记录！");
      return;
   }
   
   if(document.getElementById("billNo").value == "")
   {
      alert("水单号不能为空！");
      return;
   }
   
   fm.submit();
   
   
}

function index()
{
   var fm=document.CraftBrotherIndex;
   fm.submit();
}

function outcomeDetail(param)
{
   var fm=document.PayNoticeDetail;
   fm.outcomeId.value=param;
   fm.submit();
   
}

//-->
</script>
<s:form action="FactualRegisterSearch" namespace="/finance" method="post" theme="simple">
  
  <table border="0" cellpadding="0" width="100%">
  <tr>
    <td class="idx">工作组:</td>
    <td  colspan="2">
    <s:select id="kenDepartmentId"
              name="kenDepartmentId"
              list="teamList"
              listKey="teamId"
              listValue="name"
              onchange="javascript:changedp();">
    </s:select>
    </td>
  </tr>
  <tr>
    <td class="idx">制单人：</td>
    <td colspan="2">
    <s:select id="kenEmployeeId"
              name="kenEmployeeId"
              list="employees"
              listKey="userId"
              listValue="userName"
              headerKey=""
              headerValue="全部">
    </s:select>
    </td>
  </tr>
  <tr>
    <td class="idx">制单日期:</td>
    <td>
    <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenStartDate">
    </sj:datepicker>&nbsp;至
    <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenEndDate">
    </sj:datepicker>
    </td>
    <td>
      <s:submit action="FactualRegisterSearch" value="%{getText('common.forms.search')}"></s:submit>
    </td>
  </tr>
  <tr>
     <td class="idx">是否已登记</td>
     <td colspan="2">
       <s:radio name="register"
             list="registerList"
             listKey="value"
             listValue="label">
       </s:radio>
     </td>
  </tr>
  <tr>
    <td height="50" colspan="3">&nbsp;</td>
  </tr>
  </table>
</s:form>  

<s:form action="FactualRegister" namespace="/finance" method="post" theme="simple"> 
  <s:hidden name="kenDepartmentId"></s:hidden>
  <s:hidden name="kenEmployeeId"></s:hidden>
  <s:hidden name="kenStartDate"></s:hidden>
  <s:hidden name="kenEndDate"></s:hidden>
    <table border="0" cellpadding="0" width="100%">
        <tr>
          <td class="lstidx">No.</td>
          <td class="lstidx">付款申请号</td>
          <td class="lstidx">客户</td>
          <td class="lstidx">付款金额</td>
          <td class="lstidx">制单人</td>
          <td class="lstidx">制单日期</td>
          <td class="lstidx">选择</td>
        </tr>
   
 <s:if test="outcomeList.isEmpty() == false">     
  <s:iterator value="outcomeList" status="rowcounter">
  <s:if test='isRegister=="N"'>
	  <tr>
	    <td class="cdata"><s:property value="#rowcounter.count"/></td>
	    <td class="data"><a href="#" title="" onclick="javascript:outcomeDetail(<s:property value="outcomeId"/>);"><s:property value="outcomeId"/></a></td>
	    <td class="data"><s:property value="supplierName"/></td>
	    <td class="rdata"><s:property value="amount"/></td>
	    <td class="cdata"><s:property value="createdByName"/></td>
	    <td class="cdata"><s:date name="created" format="yyyy-MM-dd"/></td>
	    <td class="cdata">
	      <s:checkbox  name="outcomeIds" fieldValue="%{outcomeId}" value="false"/>
	    </td>
	  </tr>
  </s:if>
  <s:elseif test='isRegister=="Y"'>
      <tr>
	    <td class="cdata"><s:property value="#rowcounter.count"/></td>
	    <td class="data"><a href="#" title="" onclick="javascript:outcomeDetail(<s:property value="outcomeId"/>);"><s:property value="outcomeId"/></a></td>
	    <td class="data"><s:property value="supplierName"/></td>
	    <td class="rdata"><s:property value="amount"/></td>
	    <td class="cdata"><s:property value="createdByName"/></td>
	    <td class="cdata"><s:date name="created" format="yyyy-MM-dd"/></td>
	    <td class="cdata">已登记</td>
	  </tr>
  </s:elseif>
  </s:iterator>
  <s:if test='register=="N"'>
	  <tr>
	    <td colspan="7" class="cdata">
	      水单号：<s:textfield id="billNo" name="billNo"/><font color="red">*</font>&nbsp;&nbsp;
	      支付日期：<sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" id="payDate" name="payDate">
	             </sj:datepicker><font color="red">*</font>&nbsp;格式为：yyyy-MM-dd
	    </td>
	  </tr>
	  <tr>
	    <td colspan="6">&nbsp;</td>
	  </tr>
	  <tr>
	    <td align="center" colspan="6"><input type="button" value="提交" onClick="javascript:checkOut();"></td>
	  </tr>
  </s:if>
  
 </s:if>
</table>

</s:form>
<s:form action="PayNoticeDetail" namespace="/finance"method="post" theme="simple">
  <s:hidden name="outcomeId"></s:hidden>
</s:form>

</body>
</html>
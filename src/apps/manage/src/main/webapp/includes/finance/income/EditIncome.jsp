<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收款账单查询</title>
<meta name="menu" content="FinanceMenu"/>
<meta name="heading" content="收款账单查询">
</head>

<body>
<script type="text/javascript">
<!--//

function prochange(param) {
  $.ajax({
      type: "post",
      url: '<s:url value="/json/getAccountsByRegion.jspa" encode="false" includeParams="none"/>' ,
      timeout: 20000,
      data: {province: $("#regionCode").val()},
      error: function(){
         // alert('服务器错误');
      },
      async: false,
      success: function(data){
          $.each(data, function(i, n){
          if (i == 'map')
          {
            $("#agentList_name").autocomplete(n, {
                width: 320,
                max: 4,
                highlight: false,
		        		matchContains: true,
		        		minChars: 0,
                scroll: true,
                scrollHeight: 300,
                formatItem: function(row, i, max) {
                  return  row.value +"->["+ row.label + "]";
                },
                formatMatch: function(row, i, max) {
                  return row.value;
                },
                formatResult: function(row, value) {
                  return row.value;
                }
              }).result(function(event, row) {
                $("#companyId").val(row.label);
              });
          }
      });
    }
  });
  $("#agentList_name").focus();
}


function linked(param,target)
{
	var fm = document.showIncomes;
	var id = document.getElementById("incomeId");
	id.value = target;
	if(param == 'detail'){
		fm.action = "<s:url action='showIncomeDetail' namespace='/finance'/>"
	}
	if(param == 'delete'){
		fm.action = "<s:url action='deleteDetail' namespace='/finance'/>"
	}
	fm.submit();
}

//-->
</script>
<s:form action="showIncomes" namespace="/finance" method="POST" theme="simple">
  <table border="0" cellpadding="2" cellspacing="2" width="80%">
    <!-- 
    <tr>
      <td class="idx">工作组</td>
      <td colspan="2">
      <s:select name="departmentNo"
                list="teamList"
                listKey="teamId"
                listValue="name">
      </s:select>
      </td>
    </tr>
     -->
  	<tr>
	  	<td class="idx">省份</td>
	    <td colspan="3">
	      <s:select id="regionCode"
	                name="regionId"
	                list="regions"
	                listKey="label"
	                listValue="value"
	                emptyOption="true"
	                onchange="javascript:prochange();"
	                theme="simple">
	      </s:select>
	    </td>
  	</tr>
    <tr>
      <td class="idx">客户</td>
      <td colspan="3">
    	<s:hidden id="companyId"></s:hidden>
			<s:textfield id="agentList_name" 
									 theme="simple" 
									 size="50">
			</s:textfield>
      </td>
    </tr>
      <tr>
	      <td class="idx">收款日期</td>
	      <td colspan="2">
	      <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenStartDate">
	      </sj:datepicker>-<sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenEndDate">
	      </sj:datepicker>
	      </td>
      </tr>
    <tr>
      <td class="idx">收款金额</td>
      <td colspan="2">
        <s:textfield name="kenStartMon" size="11" maxlength="11" cssStyle="text-align: right"/> - 
        <s:textfield name="kenEndMon" size="11" maxlength="11" cssStyle="text-align: right"/>
    	  <s:submit action="showIncomes" value="%{getText('common.forms.search')}"></s:submit>
      </td>
    </tr>
  </table>
  <br>

  <table border="1" cellpadding="2" cellspacing="0" width="100%">
    <tr>
      <td class="idx">NO.</td>
      <td class="idx">团号</td>
      <td class="idx">订单号</td>
      <td class="idx">收款日期</td>
      <td class="idx">方式</td>
      <td class="idx">客户</td>
      <td class="idx">摘要</td>
      <td class="idx">金额</td>
      <td class="idx">操作</td>
    </tr>
    <s:iterator value="gathList" status="rowcounter">
    <s:hidden id="incomeId" name="incomeId"></s:hidden>
      <tr>
        <td>&nbsp;<s:property value="#rowcounter.count" /><s:hidden name="incomeId"/></td>
        <td>&nbsp;<s:property value="tour.tourNo" /></td>
        <td>&nbsp;<s:property value="bookingNo" /></td>
        <td>&nbsp;<s:date name="incomeDate" format="yyyy-MM-dd" /></td>
        <td>&nbsp;<s:property value="incomeModeShow" /></td>
        <td>&nbsp;<s:property value="customer.name" /></td>
        <td>&nbsp;<s:property value="note" /></td>
        <td align="right"><FONT color="red"><s:property value="amount" /></FONT></td>
        <td><a href="#" onclick="javascript:linked('detail','<s:property value='incomeId'/>')">修改</A>|
        	<a href="#" onclick="javascript:linked('delete','<s:property value='incomeId'/>')">删除</A>
        </td>
      </tr>
    </s:iterator>
  </table>
    <div align="center">
 		<input type="button" value="返回" onClick="javascript:history.go(-1);">
	</div>
</s:form>
</body>
</html>

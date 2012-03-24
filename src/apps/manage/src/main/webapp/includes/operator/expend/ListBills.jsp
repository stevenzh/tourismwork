<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>付款账单查询-(计调部)</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="付款账单查询-(计调部)">
</head>

<body>
<script language="JavaScript">
<!--//
function SubmitForm(param,target)
{
	var fm = document.listPayRequisition;
    fm.outcomeId.value=target;
    
    if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='deletePayRequisition' namespace='/operator'/>"
  }
  if (param == 'modify') {
    fm.action = "<s:url action='editPayRequisition' namespace='/operator'/>"
    }
    fm.submit();
}


function auditing(outcomeId)
{
   var fm=document.listPayRequisition;
   fm.outcomeId.value=outcomeId;
   fm.action="<s:url action='detailPayRequisition' namespace='/operator'/>";
   fm.submit();
}

function changedp()
{
  var fm = document.listPayRequisition;
  var departments = document.getElementById("kenDepartment");
  var combo = document.getElementById("kenMaker")
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
function _getlist(type)
{
  var frm = document.listPayRequisition;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}

   function print (outcomeId)
	{
	   var fm=document.fopReport;
	   document.getElementById("paramid").value=outcomeId;
	   fm.submit();
	}
   

//-->
</script>

<s:form action="listPayRequisition" namespace="/operator" method="post" theme="simple">
  <s:hidden name="outcomeId" ></s:hidden>

<table border="0" cellpadding="0" width="100%">
  <tr>
    <td class="idx">工作组:</td>
    <td colspan="2">
    <s:select id="kenDepartment"
              name="kenDepartment"
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
    <s:select id="kenMaker"
              name="kenMaker"
              list="employeList"
              listKey="userId"
              listValue="userName"
              headerKey=""
              headerValue="全部">
    </s:select>
    </td>
  </tr>
  <tr>
    <td class="idx">团号</td>
    <td>
    <sj:autocompleter name="kenTourNo"
                      list="stnList"
                      listKey="value"
                      listValue="label">
    </sj:autocompleter>
  </tr>

  <tr>
    <td class="idx">是否财务审核</td>
    <td>
    <s:radio name="kenAudit"
             list="auditList"
             listKey="value"
             listValue="label">
    </s:radio>
  </tr>
  <tr>
    <td class="idx">是否已付款</td>
    <td>
    <s:radio name="kenPay"
             list="payList"
             listKey="value"
             listValue="label">
    </s:radio>
  </tr>
  <tr>
    <td class="idx">制单日期:</td>
    <td colspan="2">
	    <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenStartDate" maxDate="%{sysdate}">
	    </sj:datepicker>&nbsp;至
	    <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenEndDate" maxDate="%{sysdate}">
	    </sj:datepicker>
    </td>
  </tr>

  <tr>
    <td class="idx">团出发日期:</td>
    <td>
      <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenStartOutDate">
      </sj:datepicker>&nbsp;至
      <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenEndOutDate">
      </sj:datepicker>
    </td>
    <td>
      <s:submit value="%{getText('common.forms.search')}"></s:submit>
    </td>
  </tr>
</table>

<br>

<table border="0" cellpadding="2" cellspacing="2" width="100%">
  <tr>
    <td class="lstidx">NO.</td>
    <td class="lstidx">申请书ID</td>
    <td class="lstidx">团号</td>
    <td class="lstidx">供应商</td>
    <td class="lstidx">付款金额</td>
    <td class="lstidx">制单人</td>
    <td class="lstidx">制单日期</td>
    <td class="lstidx">是否需要配送</td>
    <td class="lstidx">操作</td>
  </tr>
      
  <s:iterator value="outcomeList" status="rowcounter">
  <tr>
    <td class="cdata">
    <s:property value="#rowcounter.count" /><s:hidden value="outcomeId"></s:hidden></td>
    <td class="data"><s:property value="outcomeId" /></td>
    <td class="data"><s:property value="tourNo" /></td>
  	<td class="data"><s:property value="supplierName" /></td>
  	<td class="rdata"><s:property value="amount" /></td>
  	<td class="cdata"><s:property value="createdBy" /></td>
  	<td class="cdata"><s:date name="created" format="yyyy-MM-dd HH:mm:ss" /></td>
  	<td class="cdata"><s:if test="carryTicket == 1">是</s:if><s:else>否</s:else></td>
  	<td class="cdata">

    <s:if test="opApprovedFlag eq 'N'">
     <a href="javascript:SubmitForm('modify','<s:property value="outcomeId" />')">修改</a>|
     <a href="#" title="审核" onclick="javascript:auditing(<s:property value="outcomeId" />);">审核</a>|
     <a href="javascript:SubmitForm('delete','<s:property value="outcomeId" />')">取消</a>
    </s:if>
    <s:elseif test="opApprovedFlag eq 'Y'">
      <s:if test="frApprovedFlag eq 'Y'">
        财务已审核
      </s:if>
      <s:else>
        计调已审核
      </s:else>
    </s:elseif>
     <s:if test="payRegisterDate != null">
       已付款
     </s:if>
     <a href="#" title="打印" onclick="javascript:print(<s:property value="outcomeId" />);">打印</a>
  	</td>
  </tr>
  </s:iterator>
</table>
</s:form>

<s:form action="fopReport" namespace="/" method="POST" theme="simple">
  <s:hidden name="parameters(0).name" value="OUTCOME_ID"></s:hidden>
  <s:hidden id="paramid" name="parameters(0).data"></s:hidden>
  
  <s:hidden name="reportId" value="12"></s:hidden>
</s:form>

</body>
</html>

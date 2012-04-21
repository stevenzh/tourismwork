<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询付款账单</title>
<meta name="menu" content="FinanceMenu"/>
<meta name="heading" content="查询付款账单">
</head>

<body>
<script language="JavaScript">
<!--//
function changedp()
{
  var fm = document.listPayRequisition;
  var departments = document.getElementById("kenDepartmentId");
  var combo = document.getElementById("kenEmployeeId")
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


function gointo(supplierId,outcomeId)
{
   var fm=document.listPayRequisition;
   fm.outcomeId.value=outcomeId;
   fm.supplierId.value=supplierId;
   fm.action="<s:url action='detailPayRequisition' namespace='/finance'/>";
   fm.submit();
}

// 修改申请书
function modify(supplierId,outcomeId)
{
   var fm=document.listPayRequisition;
   fm.outcomeId.value=outcomeId;
   fm.supplierId.value=supplierId;
   fm.action="<s:url action='editPayRequisition' namespace='/finance'/>";
   fm.submit();
}

// 审核申请书
function auditing(outcomeId)
{
   var fm=document.listPayRequisition;
   fm.outcomeId.value=outcomeId;
   fm.action="<s:url action='detailPayRequisition' namespace='/finance'/>";
   fm.submit();
}

function print (outcomeId)
{
   var fm=document.fopReport;
   document.getElementById("paramid").value=outcomeId;
   fm.submit();
}


// 计调修改申请书
function opmodify(outcomeId)
{
   var fm=document.listPayRequisition;
   if(confirm("你确定要打回计调操作吗？"))
   {
	   fm.outcomeId.value=outcomeId;
	   fm.action="<s:url action='OpModifyPayAction' namespace='/finance'/>";
	   fm.submit();
   } else
   {
       return;
   }
}



//-->
</script>
<s:form action="listPayRequisition" namespace="/finance" method="post" theme="simple">
<s:hidden name="outcomeId" ></s:hidden>
<s:hidden name="supplierId" ></s:hidden>

<table border="0" style="width: 100%">
  <tr>
    <td class="idx">工作组:</td>
    <td  colspan="2">
    <s:select id="kenDepartmentId"
              name="kenDepartment"
              list="teamList"
              listKey="teamId"
              listValue="name"
              headerKey="0"
              headerValue="全部"
              onchange="javascript:changedp();">
    </s:select>
    </td>
  </tr>
  <tr>
    <td class="idx">申请人：</td>
    <td colspan="2">
    <s:select id="kenEmployeeId"
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
    <td class="idx">是否审核</td>
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
    <td class="idx">团号</td>
    <td>
    <s:textfield name="kenTourNo" size="80" maxlength="100"></s:textfield>
    </td>
  </tr>

  <tr>
    <td class="idx">团出发日期:</td> 
    <td colspan="2">
      <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenStartOutDate">
      </sj:datepicker>&nbsp;至
      <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenEndOutDate">
      </sj:datepicker>
    </td>
  </tr>
  <tr>
    <td class="idx">团属性（出境，国内）:</td>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td class="idx">供应商资源:</td>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td class="idx">计调提交日期:</td>
    <td>
    <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenStartDate" maxDate="%{sysdate}">
    </sj:datepicker>&nbsp;至
    <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenEndDate" maxDate="%{sysdate}">
    </sj:datepicker>
    </td>
    <td>
      <s:submit value="%{getText('common.forms.search')}"></s:submit>
    </td>
  </tr>
</table>

<br>

<table border="0" style="width: 100%">
  <tr>
    <td class="lstidx">NO.</td>
    <td class="lstidx">付款申请号</td>
    <td class="lstidx">团号</td>
    <td class="lstidx">供应商</td>
    <td class="lstidx">付款金额</td>
    <td class="lstidx">申请人</td>
    <td class="lstidx">计调提交日期</td>
    <td class="lstidx">是否需要配送</td>
    <td class="lstidx">操作</td>
  </tr>
      
  <s:iterator value="outcomeList" status="rowcounter">
  <tr>
    <td class="cdata">
    <s:property value="#rowcounter.count" /></td>
    <td class="cdata"><a href="#" title="" onclick="javascript:gointo(<s:property value="supplierId" />,<s:property value="outcomeId" />);"><s:property value="outcomeId"/></a></td>
    <td class="data"><s:property value="tourNo"/></td>
  	<td class="data"><s:property value="supplierName"/></td>
  	<td class="rdata"><s:property value="amount" /></td>
  	<td class="cdata"><s:property value="createdByName" /></td>
  	<td class="cdata"><s:date name="opApproved" format="yyyy-MM-dd HH:mm" /></td>
  	<td class="cdata"><s:if test="carryTicket == 1">是</s:if><s:else>否</s:else></td>
    <td class="cdata">
    <s:if test="frApprovedFlag eq 'N'">
       <a href="#" title="修改" onclick="javascript:modify(<s:property value="supplierId" />,<s:property value="outcomeId" />);">修改</a>
       <a href="#" title="审核" onclick="javascript:auditing(<s:property value="outcomeId" />);">审核</a>
       <a href="#" title="打回计调修改" onclick="javascript:opmodify(<s:property value="outcomeId" />);">打回计调</a>
    </s:if>
    <s:else>
    <authz:authorize ifAnyGranted="ROLE_SUPERUSER">
    <a href="#" title="打回计调修改" onclick="javascript:opmodify(<s:property value="outcomeId" />);">打回计调</a>
    </authz:authorize>
          已审核
    </s:else>

    <s:if test="payRegisterDate != null">已付款</s:if>
    <!--  
      <a href="<s:url namespace='/finance' action='printPayRequisition' includeParams='none'/>?outcomeId=<s:property value="outcomeId" />" title="打印" target="_blank">打印</a>
     -->
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

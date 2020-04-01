<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>票务配送列表</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="票务配送列表">
</head>

<body>
<script type="text/javascript">
<!--//

function changedp()
{
  var fm = document.listParcel;
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

// 审核申请书
function edit(outcomeId, param)
{
   var fm=document.listParcel;
   fm.outcomeId.value=outcomeId;
   fm.act.value = param;
   fm.action="<s:url action='editParcelAction' namespace='/operator'/>";
   fm.submit();
}

function print (outcomeId)
{
   var fm=document.fopReport;
   document.getElementById("paramid").value=outcomeId;
   fm.submit();
}
//-->
</script>

<s:form action="listParcel" namespace="/operator" method="post" theme="simple">
  <s:hidden name="outcomeId" ></s:hidden>
  <s:hidden name="act"></s:hidden>

  <table border="0" style="width: 100%">
    <tr>
      <td class="idx">工作组:</td>
      <td colspan="2">
      <s:select id="kenDepartmentId"
                name="kenDepartmentId"
                list="teamList"
                listKey="teamId"
                listValue="name"
                onchange="javascript:changedp();"
        emptyOption="true">
      </s:select></td>
    </tr>
    <tr>
      <td class="idx">制单人：</td>
      <td colspan="2"><s:select id="kenEmployeeId"
        name="kenEmployeeId" list="employees" listKey="userId"
        listValue="userName" headerKey="" headerValue="全部">
      </s:select></td>
    </tr>
    <tr>
      <td class="idx">制单日期:</td>
      <td colspan="2"><s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenStartDate">
      </s:textfield>&nbsp;至 <s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenEndDate">
      </s:textfield></td>
    </tr>
    <tr>
      <td class="idx">配送状态</td>
      <td colspan="2">
        <s:radio list="#{0:'未操作', 1:'开始',2:'暂停',3:'完成',4:'取消' }"
                 listKey="key"
                 listValue="value"
                 name="kenCarryStatus">
        </s:radio>
      </td>
    </tr>
    <tr>
      <td class="idx">航空公司</td>
      <td>
        <s:select list="supplierList"
                  listKey="id"
                  listValue="name"
                  name="kenSupplierId"
                  emptyOption="true">
        </s:select>
      </td>
      <td><s:submit value="%{getText('common.forms.search')}"></s:submit>
      </td>
    </tr>
  </table>
</s:form>
<br>

<s:form action="listParcel11" namespace="/operator" method="post"
  theme="simple">

  <table border="0" style="width: 100%">
    <tr>
      <td class="lstidx">付款申请号</td>
      <td class="lstidx">客户名称</td>
      <td class="lstidx">付款金额</td>
      <td class="lstidx">申请人</td>
      <!-- 
      <td class="lstidx">申请日期</td> -->
      <td class="lstidx">财务审核日期</td>
      <td class="lstidx">配送启动日期</td>
      <td class="lstidx">配送完成日期</td>
      <td class="lstidx">财务审核状态</td>
      <td class="lstidx">配送状态</td>
      <td class="lstidx">操作</td>
    </tr>

    <s:if test="parcels.isEmpty() == false">
      <s:iterator value="parcels" status="rowcounter">
        <tr>
          <td class="data">
            <a href="#" title="" onclick="javascript:outcomeDetail(<s:property value="outcomeId"/>);">
            <s:property value="outcomeId" /></a></td>
          <td class="data"><s:property value="supplierName" /></td>
          <td class="rdata"><s:property value="amount" /></td>
          <td class="cdata"><s:property value="createdByName" /></td>
          <!-- 
          <td class="cdata">
            <s:date name="created" format="yyyy-MM-dd" /></td> -->
          <td class="cdata">
            <s:date name="frApproved" format="yyyy-MM-dd" /></td>            
          <td class="cdata"><s:date name="carryStart" format="yyyy-MM-dd" /></td>
          <td class="cdata"><s:date name="carryComplete" format="yyyy-MM-dd" /></td>
          <td class="cdata">
            <s:if test="frApprovedFlag eq 'N'">未审核</s:if>
            <s:else>已审核</s:else>
          </td>
          <td class="cdata">
          <strong>
            <s:if test="carryStatus == 0">未操作</s:if>
            <s:elseif test="carryStatus == 1">开始</s:elseif>
            <s:elseif test="carryStatus == 2">暂停</s:elseif>
            <s:elseif test="carryStatus == 3">结束</s:elseif>
            <s:elseif test="carryStatus == 4">取消</s:elseif></strong>
          </td>
          <td class="rdata" >
          <s:if test="frApprovedFlag eq 'Y'">
          <s:if test="carryStatus == 0">
          <a href="#" title="开始" onclick="javascript:edit(<s:property value="outcomeId" />, 'start');">开始</a>
          </s:if>
          <s:elseif test="carryStatus == 1">
          <!-- <a>暂停</a> -->
          <a href="#" title="完成" onclick="javascript:edit(<s:property value="outcomeId" />, 'complete');">完成</a>
          <a>取消</a>
          </s:elseif>
          </s:if>
          <a href="#" title="查看团信息" onclick="javascript:edit(<s:property value="outcomeId" />, 'start');">查看团信息</a>
          <a href="#" title="打印" onclick="javascript:print(<s:property value="outcomeId" />);">打印</a>
          </td>
        </tr>
      </s:iterator>
    </s:if>
  </table>
</s:form>

<s:form action="fopReport" namespace="/" method="POST" theme="simple">
  <s:hidden name="parameters(0).name" value="OUTCOME_ID"></s:hidden>
  <s:hidden id="paramid" name="parameters(0).data"></s:hidden>
  <s:hidden name="reportId" value="12"></s:hidden>
</s:form>

</body>
</html>
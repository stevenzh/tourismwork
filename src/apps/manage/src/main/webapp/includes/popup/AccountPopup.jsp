<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title>Account List</title>
<link rel="stylesheet" href="<s:url value='/stylesheet/layout.css' encode='false' includeParams='none'/>" type="text/css" media="all">
<link rel="stylesheet" href="<s:url value='/stylesheet/global.css' encode='false' includeParams='none'/>" type="text/css" media="all">
<script type="text/javascript" src="<s:url value='/scripts/main.js' encode='false' includeParams='none'/>"></script>
<script type="text/javascript">
  // Trap the ENTER key at the document level so that the default action can be cancelled. 
  // Use Utils.RegisterEnterKeyPress() to enable the ENTER key in any simulated sub-form. 
  document.onkeypress = function()
  {
  	if ( (event.which ? event.which : event.keyCode) == 13 )
  	{
  		// We need to allow the ENTER key for multi-line edit controls. 
  		if ( event.srcElement.type == 'textarea' )
  		{
  			if ( event.srcElement.rows > 1 )
  				return;
  			// The ENTER key should work on buttons and images, so only block if on a textbox. 
  			event.returnValue = false;
  			event.cancel = true;
  		}
  	}
  }

  function ProvinceChanged()
  {
    var combo = document.getElementById("kenCity")
    while (combo.options.length>0) {
        combo.remove(0);
    }
    combo.disabled=false;

    $.ajax({
        type: "post",
        url: '<s:url value="/json/citysByProvince.jspa" encode="false" includeParams="none"/>',
        timeout: 20000,
        error: function(){
            // alert('服务器错误');
        },
        data: {provinceId: $("#kenProvince").val()},
        async: false,
        success: function(data){
            $.each(data, function(i, n){

            if (typeof(n)=='object')
            {
                combo.options.add(new Option("所有", ""));
                
                $.each(n,function(j,m){
              	  combo.options.add(new Option(m.value, m.label));
                });
            }
        });
        }
    });
  }

  </script>
</head>

<body style="margin: 10px">

<s:form id="submitPopupAccount" action="submitPopupAccount" namespace="/crm" method="POST" theme="simple">

<table cellspacing="0" cellpadding="0" border="0" style="width: 100%; border-collapse: collapse;">
  <tr>
    <td valign="top" width="15%">省份:</td>
    <td valign="top" width="35%">
      <s:select id="kenProvince"
                list="provinceList" 
                name="kenProvince"
                listKey="code"
                listValue="cnName"
                headerKey=""
                headerValue="所有"
                onchange="javascript:ProvinceChanged()">
      </s:select>
    </td>
    <td valign="top" width="15%">城市:</td>
    <td valign="top" width="35%">
      <s:select id="kenCity"
                list="cityList"
                name="kenCity"
                listKey="citycd"
                listValue="%{citycd + citynm}"
                headerKey=""
                headerValue="所有">
      </s:select>
    </td>
  </tr>
  <tr>
    <td valign="top" width="15%">名称（模糊）:</td>
    <td valign="top" width="35%"><s:textfield name="kenName" /></td>
    <td valign="top" width="15%">手机:</td>
    <td valign="top" width="35%"><s:textfield name="mobile" /></td>
  </tr>
  <tr>
    <td valign="top" width="15%">客户ID(精确):</td>
    <td valign="top" width="35%"><s:textfield name="customerCode" /></td>
    <td valign="top" width="15%">销售员:</td>
    <td valign="top" width="35%">
      <s:select id="salesId"
                name="salesId"
                list="saleList"
                listKey="userId"
                listValue="userName"
                headerKey="0"
                headerValue="所有">
      </s:select>
    </td>
  </tr>
</table>


<table cellspacing="0" cellpadding="0" border="0" style="width: 100%; border-collapse: collapse; padding-top: 8px;">
  <tr>
    <td>
      <input type="submit" value="搜索" id="ctl00_cntBody_ctlSearchView_btnSearch" accesskey="Q" title="Search [Alt]" class="button" />&nbsp;
      <input type="submit" value="清除查询条件" id="ctl00_cntBody_ctlSearchView_btnClear" accesskey="C" title="Clear [Alt]" class="button" />
    </td>
  </tr>
</table>
<BR>
<BR>

<script type="text/javascript"> 
function SelectAccount(sPARENT_ID, sPARENT_NAME)
{
	if ( window.opener != null && window.opener.ChangeAccount != null )
	{
		window.opener.ChangeAccount(sPARENT_ID, sPARENT_NAME);
		window.close();
	}
	else
	{
		alert('Original window has closed.  Account cannot be assigned.' + '\n' + sPARENT_ID + '\n' + sPARENT_NAME);
	}
}
function Clear()
{
	if ( window.opener != null && window.opener.ChangeAccount != null )
	{
		window.opener.ChangeAccount('', '');
		window.close();
	}
	else
	{
		alert('Original window has closed.  Account cannot be assigned.');
	}
}
function Cancel()
{
	window.close();
}

function _getlist(type)
{
  var frm = document.submitPopupAccount;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}
</script>

<table cellspacing="0" cellpadding="0" border="0" style="width: 100%; border-collapse: collapse; padding-bottom: 2px;">
  <tr>
    <td style="width: 10%;">
      <input type="submit" name="ctl00$cntBody$ctlDynamicButtons$btnCLEAR" value="  清除选择  " onclick="Clear(); return false;" id="ctl00_cntBody_ctlDynamicButtons_btnCLEAR" accesskey="C" title="Clear [Alt+C]" class="button" style="margin-right: 3px;" />
      <input type="submit" name="ctl00$cntBody$ctlDynamicButtons$btnCANCEL" value="  取消  " onclick="Cancel(); return false;" id="ctl00_cntBody_ctlDynamicButtons_btnCANCEL" accesskey="X" title="Cancel [Alt+X]" class="button" style="margin-right: 3px;" />
    </td>
  </tr>
</table>

<table cellspacing="0" cellpadding="3" rules="all" border="0" border="1" style="width: 100%; border-collapse: collapse;">
  <tr>
    <td class="lstidx">所在省份</td>
    <td class="lstidx">所在城市</td>
    <td class="lstidx">名称</td>
    <td class="lstidx">联系人</td>
    <td class="lstidx">地址</td>
    <td class="lstidx">销售员</td>
    <td class="lstidx">状态</td>
  </tr>

  <s:if test="agentList!=null">
    <s:iterator value="agentList" status="rowcounter">
    <s:if test="#rowcounter.count > fromRecord">
    <s:if test="#rowcounter.count <= toRecord">
    <s:if test='enabled eq "Y"'>
      <tr>
        <td class="data"><s:property value="provinceId" /></td>
        <td class="data"><s:property value="cityId" /></td>
        <td class="data"><a onclick="SelectAccount('<s:property value="agentId" />', '<s:property value="name" />')"><s:property value="name" /></a></td>
        <td class="data"><s:property value="contact" /></td>
        <td class="data"><s:property value="address" /></td>
        <td class="data"><s:property value="salesman.userName" /></td>
        <td class="data">已审核</td>
      </tr>
    </s:if>
    <s:else>
      <tr bgcolor="#DB9999">
        <td><s:property value="provinceId" /></td>
        <td><s:property value="cityId" /></td>
        <td><a onclick="SelectAccount('<s:property value="agentId" />', '<s:property value="name" />')"><s:property value="name" /></a></td>
        <td><s:property value="contact" /></td>
        <td><s:property value="address" /></td>
        <td><s:property value="salesman.userName" /></td>
        <td>未审核</td>
      </tr>
    </s:else>
    </s:if>
    </s:if>
    </s:iterator>

  <tr>
    <td colspan="10">
      <s:if test="agentList.isEmpty() == false">
      <%@ include file="/includes/PagerTable.jsp" %>
      </s:if>
    </td>
  </tr>
  </s:if>
</table>

</s:form>

</body>
</html>


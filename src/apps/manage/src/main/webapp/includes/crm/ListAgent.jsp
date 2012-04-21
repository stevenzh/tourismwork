<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户一览表</title>
<meta name="menu" content="AccountMenu"/>
<meta name="heading" content="客户一览表">
</head>
<body>

<script type="text/javascript">
<!--//
function SubmitForm(param, target)
{
  var fm = document.showAgentList;
  fm.agentId.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='deleteAgent' namespace='/crm'/>"
      
  }
  if (param =='modify')
  {    
    fm.action = "<s:url action='editAgent' namespace='/crm'/>"      
  }
  fm.submit();
}

function _getlist(type)
{
  var frm = document.showAgentList;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
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

function areaChange()
{
  var combo = document.getElementById("salesId")
  while (combo.options.length>0) {
      combo.remove(0);
  }
  combo.disabled=false;

  $.ajax({
      type: "post",
      url: '<s:url value="/json/salesByTeam.jspa" encode="false" includeParams="none"/>',
      timeout: 20000,
      error: function(){
          // alert('服务器错误');
      },
      data: {teamId: $("#teamId").val()},
      async: false,
      success: function(data){
          $.each(data, function(i, n){

          if (typeof(n)=='object')
          {
              combo.options.add(new Option("全部", ""));
              
              $.each(n,function(j,m){
            	  combo.options.add(new Option(m.value, m.label));
              });
          }
      });
      }
  });
}

//-->
</script>
<s:form action="showAgentList" namespace="/crm" method="POST" theme="simple">
  <s:hidden name="agentId"></s:hidden>
  <table>
    <tr>
	  	<td class="idx" width="100">国家</td>
	    <td>
	      <s:select name="kenCountryId"
	                list="countryList"
	                listKey="countryId"
	                listValue="name"
	                headerKey=""
	                headerValue="所有"
	                theme="simple">
	      </s:select>
	    </td>
      <td class="idx">所在省:</td>
      <td>
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
      <td class="idx">所在城市:</td>
      <td>
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
      <td class="idx">名称:</td>
      <td><s:textfield name="kenName" /></td>
      <td class="idx">结算方式：</td>
      <td>
       <s:select name="kenClearingCycle"
                list="comClearing"
                listKey="value"
                listValue="label"
                emptyOption="true">
      </s:select>
      </td>
      <td colspan="2">&nbsp;</td>
    </tr>
    <tr>
      <td class="idx">客户类型:</td>
      <td class="data" colspan="3">
       <s:select name="kenAccountType"
	               list="typeList"
	               listKey="value"
	               listValue="label"
	               emptyOption="true">
      </s:select>
      </td>
    </tr>
    <tr>
      <td class="idx">地接资源:</td>
      <td>
        <s:select list="resourceList"
                  name="kenSupplierResource"
                  listKey="value"
                  listValue="label"
                  headerKey=""
                  headerValue="所有">
        </s:select>
      </td>
      <td class="idx">线路地区:</td>
      <td colspan="3"><s:textfield name="kenDestination" />（例：塞班、欧洲、黄山）</td>
    </tr>

    <tr>
      <td class="idx">销售区域:</td>
      <td>
      <s:select id="teamId"
      			name="teamId"
      			list="maketorgList" 
            value="%{teamId}"
            listKey="teamId"
            listValue="name"
            headerKey="0"
            headerValue="所有区域"
            onchange="javascript:areaChange()">
      </s:select>
      </td>
      <td class="idx" width="100">销售员</td>
      <td colspan="3">
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
    <tr>
      <td class="idx" width="100">客户编号</td>
      <td>
      	<s:textfield name="customerCode"></s:textfield>
      </td>
      <td class="idx">状态:</td>
      <td width="190">
      <s:radio list="opKeyList"
               name="kenState"
               listKey="value"
               listValue="label" />
      </td>
      <td colspan="2">
        <s:submit value="%{getText('common.forms.search')}"></s:submit>&nbsp;&nbsp;
        <s:submit value="%{getText('common.forms.add')}" action="editAgent"/>
      </td>
  </table>
  <br>
  <table style="width: 100%">
    <tr>
      <td class="lstidx">NO.</td>
      <td class="lstidx">所在国家</td>
      <td class="lstidx">所在省份</td>
      <td class="lstidx">所在城市</td>
      <td class="lstidx">名称</td>
      <td class="lstidx">联系人</td>
      <td class="lstidx">联系电话</td>
      <td class="lstidx">地址</td>
      <td class="lstidx">销售员</td>
      <td class="lstidx">状态</td>
      <td class="lstidx">操作</td>
    </tr>

    <s:if test="agentList!=null">
      <s:iterator value="agentList" status="rowcounter">
      <s:if test="#rowcounter.count > fromRecord">
      <s:if test="#rowcounter.count <= toRecord">
      <s:if test='isActive eq "Y"'>
        <tr>
          <td class="cdata"><s:property value="#rowcounter.count" /></td>
          <td class="data"><s:property value="countryName" /></td>
          <td class="data"><s:property value="provinceName" /></td>
          <td class="data"><s:property value="city.citycd" /></td>
          <td class="data"><s:property value="name" /></td>
          <td class="data"><s:property value="contact" /></td>
          <td class="data"><s:property value="contactTel" /></td>
          <td class="data"><s:property value="address" /></td>
          <td class="data"><s:property value="sales.userName" /></td>
          <td class="data">已审核</td>
          <authz:authorize ifAnyGranted="ROLE_AGENT_MANAGER">
          <td class="data"><a href="#" onClick="javascript:SubmitForm('modify','<s:property value="customerId" />');">修改</a>&nbsp;&nbsp; <a href="#" onClick="javascript:SubmitForm('delete','<s:property value="customerId" />');">删除</a></td>
          </authz:authorize>
        </tr>
      </s:if>
      <s:else>
        <tr bgcolor="#DB9999">
          <td align="center"><s:property value="#rowcounter.count" /></td>
          <td><s:property value="countryName" /></td>
          <td><s:property value="provinceName" /></td>
          <td><s:property value="city.citycd" /></td>
          <td><s:property value="name" /></td>
          <td><s:property value="contact" /></td>
          <td><s:property value="contactTel" /></td>
          <td><s:property value="address" /></td>
          <td><s:property value="sales.userName" /></td>
          <td>未审核</td>
          <td><a href="#" onClick="javascript:SubmitForm('modify','<s:property value="customerId" />');">修改</a>&nbsp;&nbsp; <a href="#" onClick="javascript:SubmitForm('delete','<s:property value="customerId" />');">删除</a></td>
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

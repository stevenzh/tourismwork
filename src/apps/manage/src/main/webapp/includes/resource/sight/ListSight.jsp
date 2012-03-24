<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>景点列表</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="景点列表">
</head>

<body onload="javascript:OnLoad();">
<script type="text/javascript">
<!--//
function chageClassType()
{
  var combo = document.getElementById("kenDestination")
  while (combo.options.length>0) {
      combo.remove(0);
  }
  combo.disabled=false;
  
  if ($("#kenCountry").val() != 'CN')
	  {
	  $("#kenProvince").val("");
	  $("#kenProvince").hide();
	  }
  else
	  {
	  $("#kenProvince").show();
	  }

  $.ajax({
      type: "post",
      url: '<s:url value="/json/districtByRegion.jspa" encode="false" includeParams="none"/>',
      timeout: 20000,
      error: function(){
          // alert('服务器错误');
      },
      data: {countryNo: $("#kenCountry").val(), provinceNo: $("#kenProvince").val() },
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

function SubmitForm(param, target)
{
  var fm = document.listSights;
  fm.sightNo.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='deleteSight' namespace='/resource'/>"
  }
  if (param == 'modify') {
    fm.action = "<s:url action='editSight' namespace='/resource'/>"
  } else if (param == 'add') {
    fm.action = "<s:url action='editSight' namespace='/resource'/>"
  } 
  
  fm.submit();
}

function _getlist(type)
{
  var frm = document.listSights;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}
//-->
</script>
<s:form action="listSights" namespace="/resource" method="POST" theme="simple">
	<s:hidden name="sightNo"></s:hidden>

  <table style="width: 100%">
    <tr>
      <td class="idx">景点名称:</td>
      <td><s:textfield name="kenName" /></td>
      <td>&nbsp;</td>
    </tr>
  	<tr>
      <td class="idx" width="150">所在国家</td>
      <td nowrap="nowrap">
        <s:select id="kenCountry"
                  name="kenCountry"
                  list="countryList"
                  listKey="countryId"
                  listValue="%{countryId + name}"
                  emptyOption="true"
                  onchange="javascript:chageClassType();">
        </s:select> 所在省
        <s:select id="kenProvince"
                  name="kenProvince"
                  list="provinceList"
                  listKey="code"
                  listValue="cnName"
                  emptyOption="true"
                  onchange="javascript:chageClassType();">
        </s:select>
      </td>
      <td>&nbsp;</td>
  	</tr>
  	
  	<tr>
  		<td class="idx">景区</td>
  		<td>
        <s:select id="kenDestination"
                  name="kenDestination"
                  list="districtList"
                  listKey="districtNo"
                  listValue="cnName"
                  emptyOption="true">
        </s:select>
       </td>
       <td><s:submit value="%{getText('common.forms.search')}"></s:submit></td>
    </tr>
  </table>

  <table border="0" cellpadding="0" cellspacing="2" width="100%">
  	<tr>
      <td class="lstidx">NO.</td>
      <td class="lstidx">景点编号</td>
      <td class="lstidx">景点名称</td>
      <td class="lstidx">景点分类</td>
      <td class="lstidx">国家</td>
      <td class="lstidx">景区</td>
      <td class="lstidx">操作</td>
  	</tr>

    <s:iterator value="sightList" status="rowcounter">
  	<s:if test='#rowcounter.count > fromRecord'>
  	<s:if test="#rowcounter.count <= toRecord">
  	<tr>
  	    <td class="cdata"><s:property value="#rowcounter.count" /></td>
  			<td class="cdata"><s:property value="sightNo" /></td>
  			<td class="cdata"><s:property value="name" /></td>
  			<td class="cdata"><s:property value="sightType" /></td>
  			<td class="cdata"><s:property value="country.name" /></td>
        <td class="cdata"><s:property value="district.cnName" /></td>
  			<td class="cdata">
  			  <a href="#" onClick="javascript:SubmitForm('modify','<s:property value="sightNo" />')">修改</a>&nbsp;
      		<a href="#" onClick="javascript:SubmitForm('delete','<s:property value="sightNo" />')">取消</a>
  			</td>
  		</tr>
  	</s:if>
  	</s:if>
  	</s:iterator>

    <tr>
      <td colspan="8">
        <s:if test="sightList.isEmpty() == false">
        <%@ include file="/includes/PagerTable.jsp" %>
        </s:if>
      </td>
    </tr>
  </table>
  <input name="add" type="button" id="add" value="添加" onclick="javascript:SubmitForm('add','');">
</s:form>

</body>
</html>

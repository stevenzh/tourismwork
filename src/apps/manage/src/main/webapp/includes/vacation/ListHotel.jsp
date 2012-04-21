<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>酒店查询查询</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="酒店查询">
</head>

<body>
<script type="text/javascript">
<!--//
$(function () {
	$("#kenCountry").change(function() {
    $("#cityNo").empty()
    if ($("#kenCountry").val() == 'CN') {
      $("#kenProvince").show();
    } else {
    	$("#kenProvince").hide();
      $.ajax({
          type: "post",
          url: '<s:url value="/json/citysByNation.jspa" encode="false" includeParams="none"/>',
          timeout: 20000,
          error: function(){
              // alert('服务器错误');
          },
          data: {countryId: $("#kenCountry").val()},
          success: function(data){
              $.each(data, function(i, n){
              if (typeof(n)=='object') {
              	$("<option value=''>所有</option>").appendTo("#cityNo")
                $.each(n,function(j,m){
              	   $("<option value='"+m.label+"'>"+m.value+"</option>").appendTo("#cityNo")
                });
              }
          });
          }
      });
    }
  });

  $("#kenProvince").change(function() {
	    $("#cityNo").empty()
      $.ajax({
          type: "post",
          url: '<s:url value="/json/citysByProvince.jspa" encode="false" includeParams="none"/>',
          timeout: 20000,
          error: function(){
              // alert('服务器错误');
          },
          data: {provinceId: $("#kenProvince").val() },
          success: function(data){
              $.each(data, function(i, n){

              if (typeof(n)=='object')
              {
                $("<option value=''>所有</option>").appendTo("#cityNo")

                 $.each(n,function(j,m){
                	 $("<option value='"+m.label+"'>"+m.value+"</option>").appendTo("#cityNo")
                 });
              }
          });
          }
      });
  });
});
	

function _getlist(type)
{
  var frm = document.listHotel;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}

//-->
</script>
<s:form action="listHotel" namespace="/vacation" method="POST" theme="simple" cssClass="yform">
  <s:hidden name="hotelNo" />

  <table style="width: 80%">
    <tr>
      <td class="idx">酒店名称：</td>
      <td>
        <s:textfield id="hotelName" name="hotelName" />
      </td>
      <td class="idx">所在国家 ：</td>
      <td>
      <s:select id="kenCountry"
                name="countryNo"
                list="countryList"
                listKey="countryId"
                listValue="%{countryId + name}"
                headerKey=""
                headerValue="全部">
      </s:select>
      <s:select id="kenProvince"
                name="kenProvince"
                list="provinceList"
                listKey="code"
                listValue="cnName"
                emptyOption="true"
                cssStyle="display:none">
      </s:select>
      <select id="cityNo" name="cityNo">
      </select>
      </td>
    </tr>
  </table>

  <br>
  <div class="type-button">
  <s:submit value="%{getText('common.forms.search')}" />
  <s:submit action="EditHotel" value="%{getText('common.forms.add')}" /> 
  </div>
  <table style="width: 100%">
    <tr>
      <td class="lstidx"><input type="checkbox" id="chkall" ></td>
      <td class="lstidx">NO.</td>
      <td class="lstidx">所在城市</td>
      <td class="lstidx">名称</td>
      <td class="lstidx">联系电话</td>
      <td class="lstidx">酒店星级</td>
      <td class="lstidx">状态</td>
      <td class="lstidx" width="120">操作</td>
    </tr>

    <s:iterator value="hotelList" status="rowcounter">
      <tr>
        <td align="center">&nbsp;<s:property value="#rowcounter.count" /></td>
        <td>&nbsp;<s:property value="city.citynm" /></td>
        <td>&nbsp;<s:property value="hotJc" /></td>
        <td>&nbsp;<s:property value="cont" /></td>
        <td>&nbsp;<s:property value="level" /></td>
        <td>&nbsp;</td>
        <td align="center">
          <img title="修改" src="<s:url value='/images/manage/btnEdit.gif' />" width="18" height="18"
               onclick="javascript:SubmitForm('modify','<s:property value="lineNo" />')"/>
          <img title="删除" src="<s:url value='/images/manage/btnDel.gif' />" width="18" height="18"
               onclick="javascript:SubmitForm('delete','<s:property value="lineNo" />')"/>
        </td>
      </tr>
    </s:iterator>

  </table>

  <s:if test="hotelList.isEmpty() ==false">
  <%@ include file="/includes/PagerTable.jsp" %>
  </s:if>

</s:form>

</body>
</html>

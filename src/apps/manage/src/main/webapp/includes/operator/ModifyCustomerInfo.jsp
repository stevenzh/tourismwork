<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改客人信息</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="修改客人信息">
<s:head/>
</head>

<body>
<script type="text/javascript">

function birthPlaceChanged(param1, param2) {
	$.ajax({
	   type: "post",
	   url: '<s:url value="/json/HomePlace.jspa" encode="false" includeParams="none"/>' ,
	   timeout: 20000,
	   error: function(){
	      // alert('服务器错误');
	   },
	   async: false,
	   success: function(data){
	       $.each(data, function(i, n){
	       if (i == 'map')
	       {
	         $("#" + param2).autocomplete(n, {
	             width: 160,
	             highlight: false,
	             matchContains: true,
	             minChars: 0,
	             scroll: true,
	             scrollHeight: 300,
	             formatItem: function(row, i, max) {
	               return  row.label +"->["+ row.value + "]";
	             },
	             formatMatch: function(row, i, max) {
	               return row.label;
	             },
	             formatResult: function(row, value) {
	               return row.label;
	             }
	           }).result(function(event, row) {
	             $("#" + param1).val(row.value);
	           });
	       }
	   });
	 }
	});
}

function pinyin(param)
{
	  $.ajax({
	      type: "post",
	      url: '<s:url value="/json/getPinyin.jspa" encode="false" includeParams="none"/>' ,
	      timeout: 20000,
	      data: {name: $("#customerName"+param).val()},
	      error: function(){
	          // alert('服务器错误');
	      },
	      async: false,
	      success: function(data){
	          $.each(data, function(i, n){

	          if (i == 'pinyin')
	          {
	        	  $("#pinYin"+param).val(n);
	          }
	      });
	      }
	  });
}

function goAction()
{
  var fm=document.ModifyCustomerInfo;
  fm.note.value=window.prompt("备注","请认真填写修改原因");
  fm.submit();
}

</script>

<s:form action="ModifyCustomerInfo" namespace="/operator" method="post" theme="simple">
 <s:hidden name="note"></s:hidden>
 <table align="center" border="0" bordercolor="#000000" cellpadding="2" cellspacing="2" width="100%">
  <tr>
    <td class="idx" width="15%">团号:</td>
    <td colspan="3"><s:property value="tour.tourNo"/></td>
  </tr>
  <tr>
    <td class="idx" width="15%">线路：</td>
    <td colspan="3"><s:property value="tour.line.lineName"/></td>
  </tr>
  <tr>
    <td class="idx">出境日期：</td>
    <td><s:date name="tour.outDate" format="yyyy-MM-dd"/></td>
    <td class="idx" width="15%">出境口岸：</td>
    <td><s:property value="tour.outCity"></s:property></td>
  </tr>
  <tr>
    <td class="idx">入境日期：</td>
    <td><s:date name="tour.inDate" format="yyyy-MM-dd"/></td>
    <td class="idx">入境口岸：</td>
    <td><s:property value="tour.inCity"></s:property></td>
  </tr>
  <tr>
    <td class="idx">聚合城市</td>
    <td colspan="3"><s:property value="tour.venue"></s:property></td>
  </tr>
  <tr>
    <td class="idx">总人数：</td>
    <td colspan="3"><s:property value="tour.pax"/>人&nbsp;（男:<s:property value="tour.malePax"/>人、
     女:<s:property value="tour.femalePax"/>人、领队:<s:property value="tour.leadPax"/>人）</td>
  </tr>
  <tr>
    <td class="idx">订房数：</td>
    <td colspan="3">
      双人间:<s:property value="tour.doubleRoom"></s:property>&nbsp;
      单人间:<s:property value="tour.singleRoom"/>&nbsp;
      加床:<s:property value="tour.extraBedRoom"/>
    </td>
  </tr>

  <tr>
    <td class="idx">建团人:</td>
    <td><s:property value="tour.creator"/>&nbsp;</td>
    <td class="idx">创建时间:</td>
    <td><s:date name="tour.dateCreated"/>&nbsp;</td>
  </tr>
  <tr>
    <td class="idx">修改人:</td>
    <td><s:property value="tour.opUser"/>&nbsp;</td>
    <td class="idx">修改时间:</td>
    <td><s:date name="tour.opDate"/>&nbsp;</td>
  </tr>

  <tr>
    <td class="idx">备注：</td>
    <td colspan="3"><s:property value="tour.remarks" /></td>
  </tr>

  <tr>
   <td colspan="4">

   <table width="100%" border="0" cellpadding="0" cellspacing="1">
    <tr>
      <td class="lstidx">No.</td>
      <td class="lstidx">姓名<span class="required">*</span></td>
      <td class="lstidx">拼音</td>
      <td class="lstidx">身份证</td>
      <td class="lstidx">性别</td>
      <td class="lstidx">出生日期</td>
      <td class="lstidx">出生地</td>
      <td class="lstidx">护照号</td>
      <td class="lstidx">签发机关</td>
      <td class="lstidx">签发日期</td>
      <td class="lstidx">护照有效期</td>
      <td class="lstidx">备注</td>
    </tr>
    <s:iterator value="customerList" status="rowccount">
    <tr>
  
      <!-- 一个客人的信息开始 序号 -->
      <td>
        <s:property value="#rowccount.count"/>
        <s:hidden name="customerList(%{number}).nmno" value="%{nmno}"></s:hidden>
      </td>
      <!-- 姓名 -->
      <td>
      <s:textfield id="customerName%{number}"
                   name="customerList(%{number}).userName"
                   value="%{userName}"
                   size="8"
                   maxlength="10"
                   theme="simple"
                   onchange="javascript:pinyin('%{number}');">
      </s:textfield>
      </td>

      <!-- 拼音 -->
      <td>
      <s:textfield id="pinYin%{number}"
                   name="customerList(%{number}).pinYin"
                   value="%{pinYin}"
                   size="18"
                   maxlength="18"
                   theme="simple"
                   ondblclick="javascript:pinyin('%{number}');">
      </s:textfield>
      </td>

      <!-- 身份证号码 -->
      <td>
      <s:textfield name="customerList(%{number}).idCard"
                    value="%{idCard}"
                    size="18"
                    maxlength="18"
                    theme="simple">
      </s:textfield>
      </td>
  
      <!-- 性别 -->
      <td>
      <s:select name="customerList(%{number}).sex"
                 list="sexList"
                 listKey="label"
                 listValue="value"
                 value="%{sex}"
                 theme="simple">
      </s:select>
      </td>
  
      <!-- 出生日期 -->
      <td nowrap="nowrap" valign="middle">
      <sj:datepicker name="customerList(%{number}).birthday"
                     value="%{birthday}"
                     size="10"
                     displayFormat="yy-mm-dd"
                     maxlength="10">
      </sj:datepicker>
      </td>
      <!-- 出生地  -->
      <td>
      <s:hidden id="birthplace%{number}" name="customerList(%{number}).birthplace"></s:hidden>
      <s:textfield id="birthplaceName%{number}"
                   name="customerList(%{number}).birthplaceName"
                   theme="simple"
                   onfocus="javascript:birthPlaceChanged('birthplace%{number}','birthplaceName%{number}')"
                   size="8">
      </s:textfield>
      </td>

      <!-- 证件(护照)号码  -->
      <td>
      <s:textfield name="customerList(%{number}).passportNo"
                   value="%{passportNo}"
                   size="8"
                   maxlength="12"
                   theme="simple">
      </s:textfield>
      </td>
      <!-- 签发机关  -->
      <td>
      <!--  
      <s:select name="customerList(%{number}).passportPlace"
                list="passportPlaceList"
                listKey="label"
                listValue="value"
                value="%{passportPlace}"
                emptyOption="true"
                theme="simple">
      </s:select>
       --> 
      <s:hidden id="passportPlace%{number}" name="customerList(%{number}).passportPlace"></s:hidden>
      <s:textfield id="passportPlaceName%{number}"
                   name="customerList(%{number}).passportPlaceName"
                   theme="simple"
                   onfocus="javascript:birthPlaceChanged('passportPlace%{number}','passportPlaceName%{number}')"
                   size="8">
      </s:textfield>
      </td>
  
      <!-- 签发日期 -->
      <td nowrap="nowrap" valign="middle">
      <sj:datepicker name="customerList(%{number}).passportDate"
                   value="%{passportDate}"
                   displayFormat="yy-mm-dd"
                   size="10"
                   maxlength="10">
      </sj:datepicker>
      </td>
      
      <!-- 护照有效期 -->
      <td nowrap="nowrap" valign="middle">
      <sj:datepicker name="customerList(%{number}).passportExpiry"
                   value="%{passportExpiry}"
                   displayFormat="yy-mm-dd"
                   size="10"
                   maxlength="10">
      </sj:datepicker>
      </td>
      
      <td>
      <s:textfield id="customerName%{number}"
                   name="customerList(%{number}).remarks"
                   value="%{remarks}"
                   size="8"
                   maxlength="10"
                   theme="simple">
      </s:textfield>
      
      </td>
      
      <!-- 一个客人的信息结束 -->
    </tr>
    </s:iterator>

    <tr>
      <td colspan="14">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="14">
      <input type="button" value="提交" onclick="javascript:goAction();">
      <s:reset value="清除" theme="simple"></s:reset>
      </td>
    </tr>
  </table>
  </td>
 </tr>
 </table>

</s:form>
</body>
</html>
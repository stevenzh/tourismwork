<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>应付帐款查询</title>
<meta name="menu" content="FinanceMenu"/>
<meta name="heading" content="应付帐款查询">
</head>

<body>
<script type="text/javascript">
<!--//

function CountryChanged()
{
  var combo = document.getElementById("kenCity")
  while (combo.options.length>0) {
      combo.remove(0);
  }
  combo.disabled=false;

  $.ajax({
      type: "post",
      url: '<s:url value="/json/citysByNation.jspa" encode="false" includeParams="none"/>',
      timeout: 20000,
      error: function(){
          // alert('服务器错误');
      },
      data: {countryId: $("#kenCountry").val()},
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

//-->
</script>
<s:form action="OutcomeSearchActionSearch" namespace="/finance" method="POST" theme="simple">
  <s:hidden id="supplierId" name="supplierId"></s:hidden>
  
  <table border="0"">
   <tr>
        <td class="idx">所在国家:</td>
        <td>
        <s:select id="kenCountry"
                  list="countryList"
                  name="kenCountryId"
                  listKey="countryId"
                  listValue="name"
                  headerKey=""
                  headerValue="所有"
                  onchange="javascript:CountryChanged();">
        </s:select>
        </td>
        <td class="idx">所在城市:</td>
        <td>
        <s:select id="kenCity"
                  list="cityList"
                  name="kenCityId"
                  listKey="citycd"
                  listValue="%{citycd + citynm}"
                  headerKey=""
                  headerValue="所有">
      </s:select>
      </td>
    </tr>
    <tr>
      <td class="idx">地接资源:</td>
      <td>
        <s:select list="resourceList"
                  name="supplierType"
                  listKey="value"
                  listValue="label"
                  headerKey=""
                  headerValue="所有">
        </s:select>
      </td>
      <td class="idx">供应商名称:</td>
      <td><s:textfield id="supplierName" name="supplierName" /></td>
    </tr>
    <tr>
      <td class="idx">出团日期：</td>
      <td colspan="3">
       <s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" id="startDate" 
                          name="startDate"> 
                    </s:textfield>-
       <s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" id="endDate" 
                          name="endDate"> 
                    </s:textfield>           
      </td>
    </tr>
    <tr>
      <td colspan="4" align="right">
       <s:submit value="%{getText('common.forms.search')}"></s:submit>
      </td>
    </tr>
   </table>
   
   <table border="0" cellpadding="" style="width: 100%">
   <tr>
      <td class="lstidx">No.</td>
      <td class="lstidx">供应商名称</td>
      <td class="lstidx">项目</td>
      <td class="lstidx">团号</td>
      <td class="lstidx">数量</td>
      <td class="lstidx">应付款</td>
      <td class="lstidx">已付款</td>
      <td class="lstidx">未付款</td>
      <td class="lstidx">操作</td>
   </tr>
   
  <s:if test="outcomeList.isEmpty() == false">     
  <s:iterator value="outcomeList" status="rowcounter">
  <tr>
    <td class="cdata"><s:property value="#rowcounter.count"/></td>
    <td class="data"><s:property value="customer.name"/></td>
    <td class="data"><s:property value="costType"/></td>
    <td class="data"><s:property value="tour.tourNo"/></td>
    <td class="rdata"><s:property value="count"/></td>
    <td class="rdata"><s:property value="amount"/></td>
    <td class="rdata"><s:property value="payAmount"/></td>
    <td class="rdata"><s:property value="%{amount-payAmount}"/></td>
    <td class="rdata">付款</td>
  </tr>
  </s:iterator>
   <tr>
     <td class="cdata">&nbsp;</td>
    <td class="cdata">合计</td>
    <td class="rdata"><s:property value="outcome.pax"/></td>
    <td class="rdata"><s:property value="outcome.count"/></td>
    <td class="rdata"><s:property value="outcome.account"/></td>
    <td class="rdata"><s:property value="outcome.payoff"/></td>
    <td class="rdata"><s:property value="outcome.remainder"/></td>
    <td class="rdata" colspan="2">&nbsp;</td>
   </tr>
  
  </s:if>
   
   </table>
    
</s:form>
</body>
</html>
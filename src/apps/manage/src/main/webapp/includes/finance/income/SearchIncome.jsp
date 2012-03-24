<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>应收账款查询</title>
<meta name="menu" content="FinanceMenu"/>
<meta name="heading" content="应收账款查询">
</head>

<body>
<script type="text/javascript">
<!--//

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

//-->
</script>
<s:form action="searchIncome" namespace="/finance" method="POST" theme="simple">
<table border="1" cellpadding="0" cellspacing="0" width="100%">
	<tr>
  	<td class="idx">客户所在省份</td>
    <td colspan="3">
      <s:select id="kenProvince"
                name="kenProvince"
                list="provinceList"
                listKey="code"
                listValue="cnName"
                emptyOption="true"
                onchange="javascript:ProvinceChanged();"
                headerValue="所有">
      </s:select>
    </td>
	</tr>
	<tr>
      <td class="idx">客户所在城市</td>
      <td class="data">
      <s:select id="kenCity"
                name="kenCity"
                list="cityList"
                listKey="citycd"
                listValue="%{citycd + citynm}"
                emptyOption="true"
                headerValue="所有">
      </s:select>
      </td>
      </tr>
      <tr>
      	<td class="idx">出发日期</td>
      	<td><sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="stDate">
    		</sj:datepicker>-
    		<sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="endDate">
    	</sj:datepicker></td>
      </tr>
      <tr>
      	<td>
      	 <s:submit action="searchIncome" value="%{getText('common.forms.search')}"/>
      	</td>
      	<td>
      		&nbsp;
      	</td>
      </tr>
</table>

<br>

<table border="1" cellpadding="0" cellspacing="0" width="100%">
  <tr>
    <td class="lstidx">NO.</td>
    <td class="lstidx">团号</td>
    <td class="lstidx">客户</td>
    <td class="lstidx">销售员</td>
    <td class="lstidx">联系人</td>
    <td class="lstidx">预订人数</td>
    <td class="lstidx">应收款</td>
    <td class="lstidx">已收款</td>
    <td class="lstidx">未收款</td>
    <td class="lstidx">操作</td>
  </tr>
  <s:hidden id="customerId" name="customerId"/>
  <s:hidden id="name" name="name"></s:hidden>
  <s:iterator value="incomeList" status="rowcounter">
  <tr>
  	<td><s:property value="#rowcounter.count"/></td>
  	<td><s:property value="plan.tourNo"/></td>
  	<td><s:property value="customer.name"/></td>
  	<td><s:property value="salesman.userName"/></td>
  	<td><s:property value="contact"/></td>
  	<td><s:property value="pax"/></td>
  	<td><s:property value="dbamt"/></td>
  	<td><s:property value="cramt"/></td>
  	<td><s:property value="%{dbamt -cramt}"/></td>
  	<td>收款</td>
  </tr>
  </s:iterator>
  <tr>
  	<td colspan="2" align="center">合计</td>
  	<td><s:property value="totalpax" /></td>
  	<td><s:property value="totalDbamt" /></td>
  	<td><s:property value="totalCramt" /></td>
  	<td><s:property value="totalUnpay"/></td>
  	<td class="rdata" colspan="3">&nbsp;</td>
  </tr>
</table>
<s:submit value="打印"></s:submit>
</s:form>

</body>
</html>
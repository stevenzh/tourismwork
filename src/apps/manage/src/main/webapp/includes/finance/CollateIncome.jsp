<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户对帐单</title>
<meta name="menu" content="FinanceMenu"/>
<meta name="heading" content="客户对帐单">
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

<s:form action="collateIncomeAction" namespace="/finance" method="POST" theme="simple">
<table border="1" cellpadding="0" cellspacing="0" width="100%">
	<tr>
  	<td class="idx">省份</td>
    <td colspan="3">
      <s:select id="kenProvince"
                name="kenProvince"
                list="provinceList"
                listKey="code"
                listValue="cnName"
                emptyOption="true"
                onchange="javascript:ProvinceChanged();"
                theme="simple">
      </s:select>
    </td>
	</tr>
	<tr>
      <td class="idx">所在城市</td>
      <td class="data">
      <s:select id="kenCity"
                name="kenCity"
                list="cityList"
                listKey="citycd"
                listValue="%{citycd + citynm}"
                emptyOption="true">
      </s:select>
      </td>
      </tr>
      <tr>
      	<td class="idx">月结日期</td>
      	<td><s:textfield name="month"/></td>
      </tr>
      <tr>
      	<td>
      	 <s:submit value="%{getText('common.forms.search')}"/>
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
    <td class="lstidx">客户</td>
    <td class="lstidx">上月余额</td>
    <td class="lstidx">本月应付</td>
    <td class="lstidx">本月已付</td>
    <td class="lstidx">本月未付</td>
    <td class="lstidx">操作</td>
  </tr>
  <s:iterator value="" status="rowcounter">
  <tr>
  	<td>&nbsp;</td>
  	<td><s:property value=""/></td>
  	<td><s:property value=""/></td>
  	<td><s:property value=""/></td>
  	<td><s:property value=""/></td>
  	<td><s:property value=""/></td>
  	<td>&nbsp;</td>
  </tr>
  </s:iterator>
</table>
</s:form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>按照客户地区统计</title>
<meta name="menu" content="StatMenu"/>
<meta name="heading" content="按照客户地区统计">
</head>

<body>
<script language="javascript">
function showDetail(cusid)
{
	var fm = document.search;
	document.getElementById("customerId").value = cusid;
	fm.action = "<s:url action='detail' namespace='/sales' includeParams='none' />";
	fm.submit();
}

function _getlist(type)
{
  var frm = document.search;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  document.getElementById("movePage").value = type ;
  frm.submit();
}

function generateXLS()
{
	var fm = document.jxlsReport;
    var fields = document.getElementById('startDate');
    document.getElementById('paramid1').value = fields.children[1].value;
    
    var efields = document.getElementById('endDate');
    document.getElementById('paramid2').value = efields.children[1].value;
    document.getElementById('paramid3').value = document.getElementById('kenProvinceId').value;
    document.getElementById('paramid4').value = document.getElementById('kenSales').value;
    document.getElementById('paramid5').value = document.getElementById('pay').value;
    document.getElementById('paramid6').value = getRadioValue('area');
    
	fm.submit();
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

<s:form action="search" namespace="/sales" method="post" theme="simple">
  <table style="width: 100%">
    <tr>
      <td class="idx">出发日期：</td>
      <td>
        <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" id="startDate"
        				       name="startDate">
        </sj:datepicker>&nbsp;至
        <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" id="endDate"
        				       name="endDate">
        </sj:datepicker>
      </td>
      </tr>
      <tr>
      <td class="idx">客户所在省：</td>
      <td>
      	  <s:select id="kenProvinceId"
          	  			name="kenProvinceId"
          	  			list="provinceList"
          	  			listKey="code"
          	  			listValue="cnName"
          	  			headerKey=""
          	  			headerValue="所有..."
          	  			theme="simple"
      	  		      onchange="javascript:ProvinceChanged()">
      	  </s:select>	
      </td>
    </tr>
    <tr>
    	<td class="idx">客户所在城市:</td>
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
    	<td class="idx">销售员</td>
    	<td>
    		<s:select id="kenSales"
        				  name="kenSales"
        				  list="salesmans"
        				  listKey="userId"
        				  listValue="userName"
        				  headerKey=""
        				  headerValue="所有..."
        				  theme="simple">
    		</s:select>
    	</td>
    </tr>
    <tr>
    	<td class="idx">结算方式</td>
    	<td>
    		<s:select id="pay"
        				  name="pay"
        				  list="payment"
        				  listKey="value"
        				  listValue="label"
        				  headerKey=""
        				  headerValue="全部"
        				  theme="simple">
    		</s:select>
        <s:submit value="%{getText('common.forms.search')}"></s:submit>
    	</td>
    </tr>
  </table>
  <hr>

  <!-- 查询区结束 -->
  <table style="width: 100%">
  	<tr bgcolor="#b9c0ff">
      <td class="lstidx">No.</td>
      <td class="lstidx">地区</td>
      <td class="lstidx">代理商</td>
      <td class="lstidx">类型</td>
      <td class="lstidx">批数</td>
      <td class="lstidx">人数</td>
      <td class="lstidx">应收团款</td>
      <td class="lstidx">应付团款</td>
      <td class="lstidx">未收团款</td>
    </tr>
      <s:iterator value="bookList" status="rowcounter">
    <tr>
      	<td class="cdata"><s:property value="#rowcounter.count"/></td>
      	<td class="data"><s:property value="region"/></td>
      	<td class="data">
      	<a href="javascript:showDetail('<s:property value="customer.customerId"/>');">
      	<s:property value="customer.name"/>
      	<s:hidden id="customerId" name="customer.customerId"/>
      	</a>
      	</td>
      	<td class="data"><s:property value="paymentType"/></td>
      	<td class="rdata"><s:property value="batch"/></td>
        <td class="rdata"><s:property value="sumpax"/></td>
      	<td class="rdata"><s:property value="sumDbamt"/></td>
      	<td class="rdata"><s:property value="sumCramt"/></td>
      	<td class="rdata"><s:property value="sumUnpay"/></td>
    </tr>
      </s:iterator>
    <tr>
    	<td colspan="4" align="center">合计</td>
    	<td align="right"><s:property value="totalbatch"/></td>
    	<td align="right"><s:property value="totalpax"/></td>
    	<td align="right"><s:property value="totalDbamt"/></td>
    	<td align="right"><s:property value="totalCramt"/></td>
    	<td align="right"><s:property value="totalUnpay"/></td>
    </tr>
  	<tr>
	    <td>
	      <input type="button" value="生成EXCEL" onclick="javascript:generateXLS()" />
	    </td>
	  </tr>
    </table>
</s:form>

<s:form action="jxlsReport" namespace="/" method="POST" theme="simple">
  <s:hidden name="reportId" value="20"></s:hidden>
  <s:hidden name="parameters(0).name" value="START_DATE"></s:hidden>
  <s:hidden id="paramid1" name="parameters(0).data"></s:hidden>
  <s:hidden name="parameters(1).name" value="END_DATE"></s:hidden>
  <s:hidden id="paramid2" name="parameters(1).data"></s:hidden>
  <s:hidden name="parameters(2).name" value="PROVINCE"></s:hidden>
  <s:hidden id="paramid3" name="parameters(2).data"></s:hidden>
  <s:hidden name="parameters(3).name" value="SALESMAN"></s:hidden>
  <s:hidden id="paramid4" name="parameters(3).data"></s:hidden>
  <s:hidden name="parameters(4).name" value="PAY"></s:hidden>
  <s:hidden id="paramid5" name="parameters(4).data"></s:hidden>
  <s:hidden name="parameters(5).name" value="CLASSKEY1"></s:hidden>
  <s:hidden id="paramid6" name="parameters(5).data"></s:hidden>
</s:form>
</body>
</html>

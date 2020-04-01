<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>按目的地统计销售情况</title>
<meta name="menu" content="StatMenu"/>
<meta name="heading" content="按目的地统计销售情况">
</head>

<body>
<script language="javascript">

function showRoute(disNo)
{
	var fm = document.findOut;
	document.getElementById("districtNo").value = disNo;
	fm.action = "<s:url action='getRouteByDistrict' namespace='/sales' includeParams='none' />";
	fm.submit();
}

function _getlist(type)
{
  var frm = document.findOut;
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
    document.getElementById('paramid3').value = document.getElementById('country').value;
    var fields = document.getElementById('startDate');
    document.getElementById('paramid1').value = fields.children[1].value;
    
    var efields = document.getElementById('endDate');
    document.getElementById('paramid2').value = efields.children[1].value;
	fm.submit();
}
</script>

<s:form action="findOut" namespace="/sales" method="post" theme="simple">
  <s:hidden id="districtNo" name="districtNo"/>

  <table style="width: 100%">
    <tr>
      <td class="idx">出发日期：</td>
      <td>
        <s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" id="startDate" name="startDate">
        </s:textfield>&nbsp;至
        <s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" id="endDate" name="endDate">
        </s:textfield>
      </td>
      </tr>
      <tr>
        <td class="idx">国家：</td>
        <td>
          <s:select id="country"
            				name="country"
            				list="countryList"
            				listKey="code"
            				listValue="cnName"
            				headerKey=""
            				headerValue="所有..."/>
        <s:submit value="%{getText('common.forms.search')}"></s:submit>
        </td>
      </tr>
  </table>
  <hr>
  <!-- 查询区结束 -->
  <table style="width: 100%">
  	<tr bgcolor="#b9c0ff">
      <td class="lstidx">No.</td>
      <td class="lstidx">国家</td>
      <td class="lstidx">目的地</td>
      <td class="lstidx">人数</td>
    </tr>
      <s:iterator value="bookList" status="rowcounter">
    <tr>
      <td class="data"><s:property value="#rowcounter.count"/></td>
      <td class="data"><s:property value="country"/></td>
      <td class="data"><a href="javascript:showRoute('<s:property value="districtNo"/>');"><s:property value="district"/></a></td>
      <td class="rdata"><s:property value="sumpax"/></td>
    </tr>
      </s:iterator>
	  <tr>
	    <td>
	      <input type="button" value="生成EXCEL" onclick="javascript:generateXLS()" />
	    </td>
	  </tr>
  	  </table>
</s:form>

<s:form action="jxlsReport" namespace="/" method="POST" theme="simple">
  <s:hidden name="reportId" value="19"></s:hidden>
  <s:hidden name="parameters(0).name" value="START_DATE"></s:hidden>
  <s:hidden id="paramid1" name="parameters(0).data"></s:hidden>
  <s:hidden name="parameters(1).name" value="END_DATE"></s:hidden>
  <s:hidden id="paramid2" name="parameters(1).data"></s:hidden>
  <s:hidden name="parameters(2).name" value="COUNTRY"></s:hidden>
  <s:hidden id="paramid3" name="parameters(2).data"></s:hidden>
</s:form>
</body>
</html>

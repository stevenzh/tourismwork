<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品需要办理签证</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="产品需要办理签证">
</head>

<body>

<script type="text/javascript">

function changeCountry()
{
  var fm = document.showRouteVisa;
  fm.action = "<s:url action='changeRouteVisa' namespace='/product' />";
  fm.submit();
}
</script>
<s:form action="showRouteVisa" namespace="/product" method="post" theme="simple">
  <table>
    <tr>
      <td>国家:</td>
      <td>
      <s:select name="countryCode"
                list="countrys"
                listKey="countryId"
                listValue="name"
                onchange="javascript:changeCountry();"
                headerKey=""
                headerValue="全部">
      </s:select>
      </td>
    </tr>
  </table>
  <br/>
  
  <table width="100%">
    <tr>
      <td class="lstidx">选定</td>
      <td class="lstidx">国家</td>
      <td class="lstidx">项目</td>
      <td class="lstidx">内容</td>
      <td class="lstidx">单位</td>
    </tr>
    <s:if test="visaList.isEmpty() == false">
      <s:iterator value="visaList">
      <s:hidden name="visaList(%{refNo}).recNo" value="%{recNo}"/>
      <s:hidden name="visaList(%{refNo}).countryCode" value="%{countryCode}"></s:hidden>
      <s:hidden name="visaList(%{refNo}).unit" value="%{unit}"></s:hidden>
      <s:hidden name="visaList(%{refNo}).item" value="%{item}"></s:hidden>
      <s:hidden name="visaList(%{refNo}).description" value="%{description}"></s:hidden>
      <tr>
        <td class="data" valign="top">
        <s:checkbox name="visaList(%{refNo}).checked" value="%{checked}"></s:checkbox>
        </td>
        <td class="cdata" valign="top">
          <s:select name="countryCode"
	                list="countrys"
	                listKey="countryId"
	                listValue="name"
	                disabled="true">
        </s:select>
        </td>
        <td class="data" valign="top"><s:property value="item" /></td>
        <td class="data">
          <div style="width: 400;">
          <s:property escape="false" value="description" />
          </div>
        </td>
        <td class="data"><s:property value="unit" /></td>
      </tr>
      </s:iterator>
    </s:if>
  </table>
  <br>
  <s:submit action="saveRouteVisa" value="保存" />
</s:form>

</body>
</html>

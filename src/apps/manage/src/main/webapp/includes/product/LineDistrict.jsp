<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品途径目的地</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="产品途径目的地">
</head>
<body>

<script type="text/javascript">
<!--//

function modify(param)
{
  var fm = document.inputRouteDistrict;
  if (document.getElementById("countryNo") != null)
    document.getElementById("input_countryNo").value = document.getElementById("countryNo").value;
  else
    document.getElementById("input_countryNo").value ="";

  if (document.getElementById("provinceNo") != null)
    document.getElementById("input_provinceNo").value = document.getElementById("provinceNo").value;
  else
  document.getElementById("input_provinceNo").value = "";

  document.getElementById("input_districtNo").value = param;
  fm.submit();
}

function add()
{
  var fm = document.inputRouteDistrict;
  if (document.getElementById("countryNo") != null)
    document.getElementById("input_countryNo").value = document.getElementById("countryNo").value;
  else
    document.getElementById("input_countryNo").value ="";

  if (document.getElementById("provinceNo") != null)
    document.getElementById("input_provinceNo").value = document.getElementById("provinceNo").value;
  else
  document.getElementById("input_provinceNo").value = "";

  document.getElementById("input_districtNo").value = '';
  fm.submit();
}

function selectAll()
{
	var cb = document.all.check;
	
		for(i=0;i<cb.length;i++)
		{
			cb[i].checked = true;
		}
}

//-->
</script>

<s:form action="RouteDistrict" namespace="/product" method="post" theme="simple">
  <table style="width: 100%">
    <tr>
      <td width="300">
        <table>
          <tr>
            <td>国家/地区：</td>
            <td>
	            <s:select id="countryNo"
	                      name="countryNo"
	                      list="countryList"
	                      listKey="countryId"
	                      listValue="%{countryId +' '+ name}"
	                      emptyOption="true">
	            </s:select>
            </td>
          </tr>
          <tr>
            <td>省/直辖市：</td>
            <td>
              <s:select id="provinceNo"
                        name="provinceNo"
                        list="provinceList"
                        listKey="code"
                        listValue="cnName"
                        emptyOption="true">
              </s:select>
            </td>
          </tr>
        </table>
      </td>
      <td align="left"><s:submit action="searchRouteDistrict" value="%{getText('common.forms.search')}" /></td>
    </tr>
  </table>

  <table>
      <tr>
        <td class="lstidx">No.</td>
        <td class="lstidx">选定</td>
        <td class="lstidx" nowrap="nowrap">国家/地区</td>
        <td class="lstidx" nowrap="nowrap">目的地</td>
        <td class="lstidx">目的地描述</td>
        <td class="lstidx" style="width: 90px">操作</td>
      </tr>

      <s:iterator value="districtList" status="rowcounter">
        <tr>
          <td class="cdata" valign="top"><s:property value="#rowcounter.count" /></td>
          <td class="data" valign="top"><input type="checkbox" name="check" value="<s:property value="districtNo"/>" checked="checked"></td>
          <td class="data" valign="top"><s:property value="country.name" /></td>
          <td class="data" valign="top"><strong><s:property value="cnName" /></strong></td>
          <td class="data"><s:property value="cnNote" /></td>
          <td class="data"><a href="#" onclick="javascript:modify('<s:property value='districtNo'/>')">修改目的地描述</a></td>
        </tr>
      </s:iterator>

    <tr><td colspan="6">&nbsp;</td></tr>
    <tr>
      <td colspan="6">注意：下面列出的目的地需要补充 点击“添加目的地”地按钮
      <input type="button" value="添加目的地" onclick="javascript:add();">
      </td>
    </tr>

      <s:iterator value="searchList" status="rowcounter">
        <tr>
          <td class="cdata" valign="top"><s:property value="#rowcounter.count" /></td>
          <td class="data" valign="top"><input type="checkbox" name="check" value="<s:property value="districtNo"/>"></td>
          <td class="data" valign="top"><s:property value="country.name" /></td>
          <td class="data" valign="top"><strong><s:property value="cnName" /></strong></td>
          <td class="data"><s:property value="cnNote" /></td>
          <td class="data">&nbsp;</td>
        </tr>
      </s:iterator>

    <tr>
    <td><input type="button" onclick="javascript:selectAll()" value="全选"></td>
      <td colspan="4"><s:submit action="RouteDistrictSave" value="保存" /></td>
    </tr>
  </table>
</s:form>

<s:form action="inputRouteDistrict" namespace="/product" method="POST" theme="simple">
<s:hidden id="input_countryNo" name="countryNo"></s:hidden>
<s:hidden id="input_provinceNo" name="provinceNo"></s:hidden>
<s:hidden id="input_districtNo" name="districtNo"></s:hidden>
</s:form>

</body>
</html>

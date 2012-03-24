<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改目的地信息</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="修改目的地信息">
</head>

<body>
<script type="text/javascript">
<!--//

function back()
{
  var fm=document.submitRouteDistrict;
  fm.action = "<s:url action='searchRouteDistrict' namespace='/product'/>"
  fm.submit();
}
//-->
</script>

<s:form action="submitRouteDistrict" namespace="/product" method="POST" theme="simple">
  <s:hidden name="district.districtNo" />
  <s:hidden name="countryNo"></s:hidden>
  <s:hidden name="provinceNo"></s:hidden>

  <table>
    <tr>
      <td>
        <div>所在国家
            <s:select name="district.countryNo"
                      list="countryList"
                      listKey="countryId"
                      listValue="%{countryId +' '+ name}"
                      emptyOption="true">
            </s:select>
        </div>
        <div>所在省
            <s:select name="district.provinceNo"
                      list="provinceList"
                      listKey="code"
                      listValue="cnName"
                      emptyOption="true">
            </s:select>
        </div>
      </td>
     </tr>
    <tr>
      <td class="idx">目的地名称:</td>
      <td class="data"><s:textfield name="district.cnName" /></td>
    </tr>
    <tr>
      <td class="idx">目的地英文名:</td>
      <td class="data"><s:textfield name="district.enName" /></td>
    </tr>
    <tr>
      <td class="idx">目的地概况:</td>
      <td class="data">
        <s:textarea name="district.cnNote"
                    cols="50"
                    rows="6"/>
      </td>
    </tr>
    <tr>
      <td class="idx">目的地概况（英文）:</td>
      <td class="data"><s:textfield name="district.enNote" /></td>
    </tr>
    <tr>
      <td class="idx" nowrap="nowrap">目的地首图文件地址:</td>
      <td class="data"><s:textfield name="district.mapHead" /></td>
    </tr>
    <tr>
      <td class="idx">目的地地图文件地址:</td>
      <td class="data"><s:textfield name="district.mapAddress" /></td>
    </tr>

    <tr>
     <td colspan="2">
     <s:submit value="保存"></s:submit>&nbsp;&nbsp;&nbsp;&nbsp;
     <input type="button" value="返回" onclick="javascript:back()"></td>
    </tr>
  </table>
</s:form>

</body>
</html>

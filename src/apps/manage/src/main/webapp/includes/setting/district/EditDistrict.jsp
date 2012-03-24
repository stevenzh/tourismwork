<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改景区信息</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="修改景区信息">
</head>

<body>
<script type="text/javascript">
<!--//
function back()
{
  var fm=document.submitDistrict;
  fm.action = "<s:url action='searchDistrict' namespace='/setting'/>"
  fm.submit();
}
//-->
</script>
<s:form action="submitDistrict" namespace="/setting" method="POST" theme="simple">
  <s:hidden name="currentPage"></s:hidden>

  <s:hidden name="district.districtNo" />
  <s:hidden name="kenName"></s:hidden>
  <s:hidden name="kenCountry"></s:hidden>
  <s:hidden name="kenProvince"></s:hidden>
  <table>
    <tr>
      <td class="idx">景区名称:</td>
      <td class="data"><s:textfield name="district.cnName" /></td>
    </tr>
    <tr>
      <td class="idx">景区概况:</td>
      <td class="data">
        <s:textarea name="district.cnNote"
                    cols="100"
                    rows="8">
        </s:textarea>
      </td>
    </tr>
    <tr>
      <td class="idx" nowrap="nowrap">景区首图文件地址:</td>
      <td class="data"><s:textfield name="district.mapHead" /></td>
    </tr>
    <tr>
      <td class="idx">景区地图文件地址:</td>
      <td class="data"><s:textfield name="district.mapAddress" /></td>
    </tr>
    <tr>
      <td class="idx">所在国家</td>
      <td>
        <s:select name="district.country.countryId"
                  list="countryList"
                  listKey="countryId"
                  listValue="%{countryId +' '+ name}"
                  emptyOption="true">
        </s:select> 所在省
        <s:select name="district.province.code"
                  list="provinceList"
                  listKey="code"
                  listValue="cnName"
                  emptyOption="true">
        </s:select>
      </td>
     </tr>
    <tr>
     <td colspan="2">
       <s:submit value="修改"></s:submit>&nbsp;&nbsp;&nbsp;&nbsp;
       <input type="button" value="返回" onclick="javascript:back()">
     </td>
    </tr>
  </table>
</s:form>

</body>
</html>

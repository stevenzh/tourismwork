<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>景区一览表</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="景区一览表">
</head>

<body onload="javascript:OnLoad();">
<script type="text/javascript">
<!--//
function SubmitForm(param, target)
{
  var fm = document.searchDistrict;
  fm.districtNo.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='deleteDistrict' namespace='/setting'/>"
  }
  if(param=='modify')
  {
    fm.action = "<s:url action='editDistrict' namespace='/setting'/>"
  }
  fm.submit();
}

function _getlist(type)
{
  var frm = document.searchDistrict;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}
//-->
</script>
<s:form action="searchDistrict" namespace="/setting" method="POST" theme="simple">
  <s:hidden name="districtNo"></s:hidden>
  <table style="width: 100%">
    <tr>
      <td class="idx">名称:</td>
      <td><s:textfield name="kenName"/></td>
    </tr>
    <tr>
      <td class="idx">所在国家</td>
      <td>
        <s:select name="kenCountry"
                  list="countryList"
                  listKey="countryId"
                  listValue="%{countryId +' '+ name}"
                  headerKey=""
                  headerValue="全部">
          </s:select> 所在省
          <s:select name="kenProvince"
                    list="provinceList"
                    listKey="code"
                    listValue="cnName"
                    headerKey=""
                    headerValue="全部">
          </s:select>
      </td>
    </tr>
    <tr>
      <td>
      <input name="search" type="button" id="search"
             onclick="javascript:SubmitForm('search','')" value="<s:text name="common.forms.search" />">
      <input name="add" type="button" id="add" value="<s:text name="common.forms.add" />" onclick="javascript:SubmitForm('modify','');">
      </td>
    </tr>
  </table>

  <table width="100%" border="1" cellpadding="2" cellspacing="0">
    <tr>
      <td class="lstidx">NO.</td>
      <td class="lstidx">国家</td>
      <td class="lstidx">省份</td>
      <td class="lstidx">景区名称</td>
      <td class="lstidx">景区编号</td>
      <td class="lstidx">操作</td>
    </tr>
    <s:iterator value="districtList" status="rowcounter">
    <s:if test="#rowcounter.count > fromRecord">
    <s:if test="#rowcounter.count <= toRecord">
    <tr>
      <td class="cdata"><s:property value="#rowcounter.count" /></td>
      <td class="cdata"><s:property value="country.name" /></td>
      <td class="cdata"><s:property value="province.cnName" />&nbsp;</td>
      <td class="cdata"><s:property value="cnName" />&nbsp;</td>
      <td class="cdata"><s:property value="districtNo" /></td>
      <td class="cdata">
        <a href="#" onClick="javascript:SubmitForm('modify','<s:property value="districtNo" />')">修改</a>&nbsp;&nbsp;
        <a href="#" onClick="javascript:SubmitForm('delete','<s:property value="districtNo" />')">取消</a>
      </td>
    </tr>
    </s:if>
    </s:if>
    </s:iterator>
    <tr>
      <td colspan="7">
        <s:if test="districtList.isEmpty() == false">
        <%@ include file="/includes/PagerTable.jsp" %>
        </s:if>
      </td>
    </tr>
  </table>
</s:form>
</body>
</html>

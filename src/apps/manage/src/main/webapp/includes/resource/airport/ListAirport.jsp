<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>全球机场三字码一览表</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="全球机场三字码一览表">
<s:head/>
</head>
<body>
<script language="javascript">

function SubmitForm(param, target)
{
  var fm = document.searchAirport;
  var code = document.getElementById("airport_code");
  code.value=target;
  if (param =='info') {
    fm.action = "<s:url action='AirportDetail' namespace='/resource'/>"
  } else if (param == 'modify') {
    fm.action = "<s:url action='editAirport' namespace='/resource'/>"
  } else if (param == 'add') {
    fm.action = "<s:url value='editAirport' namespace='/resource'/>"
  } else if (param == 'del') {
    fm.action = "<s:url action='deleteAirport' namespace='/resource'/>"
    if (confirm("确定要删除此分类吗？") == false) {
      return;
    }
  }
  fm.submit();
}

function CountryChanged()
{
  var combo = document.getElementById("kencity")
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
      data: {countryId: $("#country").val()},
      async: false,
      success: function(data){
          $.each(data, function(i, n){

          if (typeof(n)=='object')
          {
              combo.options.add(new Option("", ""));
              
              $.each(n,function(j,m){
            	  combo.options.add(new Option(m.value, m.label));
              });
          }
      });
      }
  });
}


function _getlist(type)
{
  var frm = document.searchAirport;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}

//-->
</script>

<s:form action="searchAirport" namespace="/resource" method="post" theme="simple">
<s:hidden id="airport_code" name="airportId"></s:hidden>
  <table style="width: 100%">
    <tr>
      <td nowrap>所属国家/地区：</td>
      <td>
      <s:select id="country"
                name="kenCountryId"
                list="Countrys"
                listKey="countryId"
                listValue="name"
                headerKey=""
                headerValue="全部"
                onchange="javascript:CountryChanged();">
      </s:select>
      </td>
      </tr>
      <tr>
      <td>所在城市：</td>
      <td>
        <s:select id="kencity"
                  name="kenCityId"
                  list="citys"
                  listKey="citycd"
                  listValue="citynm"
                  headerKey=""
                  headerValue="全部">
        </s:select>
      </td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>删除否：
      <s:radio name="kenDelkey"
               list="delStates"
               listKey="value"
               listValue="label">
      </s:radio>
      </td>
      <td colspan="3">
      </td>
      <td>
      <s:submit value="%{getText('common.forms.search')}"></s:submit>
      </td>
    </tr>
  </table>
  <br/>
  <table style="width: 100%">
    <tr>
      <td class="lstidx">No.</td>
      <td class="lstidx">国家</td>
      <td class="lstidx">城市</td>
      <td class="lstidx">三字码</td>
      <td class="lstidx">名称（中文）</td>
      <td class="lstidx">名称（英文）</td>
      <td class="lstidx">状态</td>
      <td colspan="2" class="lstidx">操作</td>
    </tr>
    
    <s:iterator value="airportList" status="rowcounter">
      <tr>
        <td class="cdata"><s:property value="%{fromRecord + #rowcounter.count}"/> </td>
        <td class="data"><s:property value="countryName"/></td>
        <td class="data"><s:property value="city.citynm"/></td>
        <td class="data"><s:property  value="code"/></td>
        <td class="data"><s:property value="name"/>&nbsp;</td>
        <td class="data"><s:property value="enName"/>&nbsp;</td>
        <td class="cdata"><s:property value="isActive"/></td>
        <td class="cdata"><a href="#" onClick="javascript:SubmitForm('modify','<s:property value="code"/>')">修改</a>
        <a href="#" onClick="javascript:SubmitForm('del','<s:property value="code"/>')">删除</a></td>
      </tr>
    </s:iterator>
    <tr>
      <td colspan="8">
        <s:hidden name="totalRecord"></s:hidden>
        <%@ include file="/includes/PagerTable.jsp" %>
      </td>
    </tr>
  </table>
  <input type="button" class="button" value="<s:text name="common.forms.add" />" onclick="javascript:Add('add','')">
</s:form>
</body>
</html>



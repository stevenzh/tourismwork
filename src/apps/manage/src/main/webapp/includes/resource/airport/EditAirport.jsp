<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加机场三字代码分类</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="添加机场三字代码分类">
<s:head/>
</head>

<body>
<script language="JavaScript">
function cancel(){
  var fm = document.submitAirport;
  fm.action = "<s:url action='searchAirport' namespace='/resource'/>"
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

</script>

<s:form action="submitAirport" method="POST" namespace="/resource" theme="simple">
<s:hidden name="kenCityId"></s:hidden>
<s:hidden name="kenDelkey"></s:hidden>

	<table cellpadding="0" cellspacing="2">
    <tr>
      <td class="idx">所属国家/地区：</td>
      <td>
      <s:select id="country"
                name="kenCountryId"
                list="countrys"
                listKey="countryId"
                listValue="name"
                onchange="javascript:CountryChanged();">
      </s:select>
      </td>
    </tr>
    <tr>
      <td class="idx">所在城市：</td>
      <td>
       <s:select id="kencity"
                 name="airport.city.citycd"
                 list="citys"
                 listKey="citycd"
                 listValue="citynm"
                 headerKey=""
                 headerValue="全部">
        </s:select>
      </td>
    </tr>
    <tr>
      <td class="idx">代码：</td>
      <td class="data">
      <s:textfield name="airport.code" size="3" maxlength="3"></s:textfield>
      </td>
    </tr>
    <tr>
      <td class="idx">名称(中文)：</td>
      <td class="data">
      <s:textfield name="airport.name" size="20" maxlength="20"></s:textfield>
      </td>
    </tr>
    <tr>
      <td class="idx">名称(英文)：</td>
      <td class="data">
      <s:textfield name="airport.enName" size="50" maxlength="50"></s:textfield>
      </td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;<font color="red">*</font>&nbsp;为必填写项目！</td>
    </tr>
    <tr>
      <td colspan="2">
      <div align="center">
      <s:submit value="添 加" ></s:submit>&nbsp;&nbsp;
      <s:submit value="返 回" onclick="javascript:cancel()"></s:submit>
      </div>
      </td>
    </tr>
	</table>
</s:form>

</body>
</html>

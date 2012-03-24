<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改景点信息</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="修改景点信息">
</head>

<body>
<script type="text/javascript">
<!--//
function back()
{
  var fm=document.submitSight;
  fm.action = "<s:url action='listSights' namespace='/resource'/>"
  fm.submit();
}

function chageClassType()
{
  var combo = document.getElementById("sight_districtNo")
  while (combo.options.length>0) {
      combo.remove(0);
  }
  combo.disabled=false;

  $.ajax({
      type: "post",
      url: '<s:url value="/json/districtByRegion.jspa" encode="false" includeParams="none"/>',
      timeout: 20000,
      error: function(){
          // alert('服务器错误');
      },
      data: {countryNo: $("#kenCountry").val(), provinceNo: $("#kenProvince").val() },
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

<s:form action="submitSight" namespace="/resource" method="POST" theme="simple">
  <s:hidden name="countInPage"></s:hidden>
  <s:hidden name="currentPage"></s:hidden>

  <s:hidden name="kenCountry"></s:hidden>
  <s:hidden name="kenProvince"></s:hidden>
  <s:hidden name="kenDestination"></s:hidden>
  <s:hidden name="kenName"></s:hidden>
  <s:hidden name="sight.sightNo"></s:hidden>

  <table>
  	<tr>
      <td class="idx">景点编号:</td>
      <td class="data"><s:property value="sight.sightNo" /></td>
    </tr>
    <tr>
    	<td class="idx">景点名称:</td>
    	<td class="data"><s:textfield name="sight.name" />&nbsp;<font color="red">* </font></td>
    </tr>
    <tr>
    	<td class="idx">景点概况:</td>
    	<td class="data">
    	  <s:textarea name="sight.cnNote"
    	              cols="100"
    		            rows="8" />
      </td>
    </tr>
    <tr>
    	<td class="idx" nowrap="nowrap">景点首图文件地址:</td>
    	<td class="data"><s:textfield name="sight.headAdd" /></td>
    </tr>
    <tr>
    	<td class="idx">景点地图文件地址:</td>
    	<td class="data"><s:textfield name="sight.mapAdd" /></td>
    </tr>
    <tr>
    	<td class="idx">所在国家:</td>
    	<td>
        <s:select id="kenCountry"
                   name="sight.country.countryId"
                   list="countryList"
                   listKey="countryId"
                   listValue="%{countryId +' '+ name}"
                   emptyOption="true"
                   onchange="javascript:chageClassType();">
        </s:select> 所在省
        <s:select id="kenProvince"
                  name="sight.province.code"
                  list="provinceList"
                  listKey="code"
                  listValue="cnName"
                  emptyOption="true"
                  onchange="javascript:chageClassType();">
        </s:select>
    	</td>
    </tr>
    <tr>
      <td class="idx">景区 </td>
      <td>
        <s:select id="sight_districtNo"
                    name="sight.district.districtNo"
                    list="districtList"
                    listKey="districtNo"
                    listValue="cnName"
                    emptyOption="true">
        </s:select>&nbsp;<font color="red">* </font>
     </td>    
    </tr>
    <tr>
    	<td align="center">
    	<s:submit value="提 交">&nbsp;</s:submit> 
    	<input type="button" value="返 回" onclick="javascript:back()"></td>
    </tr>
  </table>
</s:form>

</body>
</html>

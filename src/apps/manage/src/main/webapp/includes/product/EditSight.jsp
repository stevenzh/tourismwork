<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加-景点</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="添加-景点">
</head>

<body>
<script type="text/javascript">
<!--//
function back()
{
  var fm=document.submitRouteSight;
  fm.action = "<s:url action='showRouteSights' namespace='/product'/>"
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
              combo.options.add(new Option("", ""));
              
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
<s:form action="submitRouteSight" namespace="/product" method="POST" theme="simple">
  <s:hidden name="sight.sightNo"></s:hidden>

	<table>
		<tr>
			<td class="idx">景点编号:</td>
			<td class="data"><s:property value="sight.sightNo" /></td>
		</tr>
		
		<tr>
			<td class="idx">景点名称:</td>
			<td class="data">
			  <s:textfield name="sight.name" />&nbsp;<font color="red">*</font> </td>
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
        </s:select>
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
      <td>景点</td>
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

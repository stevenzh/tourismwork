<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<s:url value='/stylesheet/layout.css' encode='false' includeParams='none'/>" type="text/css" media="all">
<title>高级搜索--Search</title>
<script type="text/javascript" src="<s:url value='/scripts/main.js' encode='false' includeParams='none'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/prototype.js' encode='false' includeParams='none'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/ws.js' encode='false' includeParams='none'/>"></script>
<script type="text/javascript">
<!--//

function CountryChanged(param1,param2){
  var fm = document.getElementById(param1);
  var province = fm.province;
  var district = fm.district;
  var sdpt_no = param2;

  //更换目的地的内容
  //将原表中的内容清空
  while (district.options.length>0) {
      district.remove(0);
  }
  if(province!=null && param2!="CN")
  {
      province.style.display="none";
  }
  district.disabled=false;
  
  var call = new WS.Call('<s:url value="/services/CbSearchService" encode="false" includeParams="none"/>'); 
  var nsuri = 'http://www.opentravelsoft.com';
  var qn_op = new WS.QName('getDistrictBycountry',nsuri);
  call.invoke_rpc(
    qn_op,
    new Array(
      {name:'name',value:sdpt_no}
    ),null,
    function(call,envelope) {
      var nodes = envelope.get_body().get_all_children()[0].get_all_children()[0].get_all_children();
      var kOption = document.createElement('OPTION');
      kOption.text = "所有";
      kOption.value = "";
      district.options.add(kOption);

	    for (var n = 0; n < nodes.length; n++) {
	      var empls = nodes[n].get_value().split(',');
	      var oOption = document.createElement('OPTION');
	      oOption.text = empls[1];
	      oOption.value = empls[0];
	      district.options.add(oOption);
	    }
    }
  );
} 



function provinceChanged(param1,param2){
  var fm = document.getElementById(param1);
  var district = fm.district;
  var sdpt_no = param2;

  //更换目的地的内容
  //将原表中的内容清空
  while (district.options.length>0) {
      district.remove(0);
  }
  district.disabled=false;
  
  var call = new WS.Call('<s:url value="/services/CbSearchService" encode="false" includeParams="none"/>'); 
  var nsuri = 'http://www.opentravelsoft.com';
  var qn_op = new WS.QName('getDistrictByprovince',nsuri);
  call.invoke_rpc(
    qn_op,
    new Array(
      {name:'name',value:sdpt_no}
    ),null,
    function(call,envelope) {
      var ret = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value();
      var empls = ret.split(',');

      if(empls.length > 0)
      {
        var kOption = document.createElement('OPTION');
        kOption.text = "所有";
        kOption.value = "";
        district.options.add(kOption);

        for(var i=0;i<empls.length -1;i=i+2)
        {
          var oOption = document.createElement('OPTION');
          oOption.text = empls[i+1];
          oOption.value = empls[i];
          district.options.add(oOption);
        }
      }
    }
  );
} 


function getAllProvince(param1,param2){
  var fm = document.getElementById(param1);
  var provice = fm.province;
  var sdpt_no = param2;

    //更换目的地的内容
  //将原表中的内容清空
  while (provice.options.length>0) {
      provice.remove(0);
  }
  provice.disabled=false;
  
  var call = new WS.Call('<s:url value="/services/CbSearchService" encode="false" includeParams="none"/>'); 
  var nsuri = 'http://www.opentravelsoft.com';
  var qn_op = new WS.QName('getAllProvince',nsuri);
  call.invoke_rpc(
    qn_op,
    new Array(
      {name:'name',value:sdpt_no}
    ),null,
    function(call,envelope) {
      var ret = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value();
      var empls = ret.split(',');

      if(empls.length > 0)
      {
        var kOption = document.createElement('OPTION');
        kOption.text = "所有省/直辖市";
        kOption.value = "";
        provice.options.add(kOption);

        for(var i=0;i<empls.length -1;i=i+2)
        {
          var oOption = document.createElement('OPTION');
          oOption.text = empls[i+1];
          oOption.value = empls[i];
          provice.options.add(oOption);
        }
      }
    }
  );
} 

function fitChange(param1,param2)
{
   var fm=document.getElementById(param1);
   if(param2=="CN")
     fm.province.style.display="block";
   CountryChanged(param1,param2);
}

 function op_type(otablex)
{
  if (otablex=="1" || otablex=="0")
  {
	  document.getElementById("tab_1").style.display="block";
	  document.getElementById("tab_2").style.display="none";
	  document.getElementById("tab_3").style.display="none";
	  document.getElementById("tab_4").style.display="none";
  }
  if (otablex=="2")
  {
	  document.getElementById("tab_1").style.display="none";
	  document.getElementById("tab_2").style.display="block";
	  document.getElementById("tab_3").style.display="none";
	  document.getElementById("tab_4").style.display="none";
	  getAllProvince("form2","p");
  }
if (otablex=="3")
  {
	  document.getElementById("tab_1").style.display="none";
	  document.getElementById("tab_2").style.display="none";
	  document.getElementById("tab_3").style.display="block";
	  document.getElementById("tab_4").style.display="none";
	  getAllProvince("form3","p");
  }

  if (otablex=="4")
  {
	  document.getElementById("tab_1").style.display="none";
	  document.getElementById("tab_2").style.display="none";
	  document.getElementById("tab_3").style.display="none";
	  document.getElementById("tab_4").style.display="block";
	  getAllProvince("form4","p");
  }
  document.getElementById("searchType0").value=otablex;
}


function outcity(param)
{
  document.getElementById("outCity0").value =param;
}

function goSearch(param)
{
  var fm=document.getElementById(param);
  var searchType0=document.getElementById("searchType0").value;
  var outCity0=document.getElementById("outCity0").value;
  fm.searchType.value=searchType0;
  fm.outCity.value=outCity0;
  fm.action = "<s:url action='AdvancedSearch' namespace='/search'/>";
  fm.submit();
}

//-->
</script>
</head>
<body>

<table style="width: 100%" border="0" cellpadding="2">
  <tr>
    <td width="75%" height="25" bgcolor="#DFE1FF"><font size="-1" style="font-weight: bold">高级搜索</font></td>
    <td width="8%" bgcolor="#DFE1FF"><font size="-1"><a href="/html/help.htm" target="_blank">搜索指南</a></font>
    </td>
  </tr>
</table>

<table style="width: 100%" border="0">
	<tr>
	  <td width="80"></td>
	  <td align="center">
	  <s:form id="form" action="AdvancedSearch" namespace="/search" method="post" theme="simple">
		<table style="width: 100%" border="0">
			<tr>
				<td height="25" valign="bottom"><font size="-1">出发地：</font></td>
				<td><font size="-1">
				   <s:radio name="outCity" list="outCityList" listKey="value" listValue="label" onclick="javascript:outcity(this.value);"></s:radio>
				   <s:hidden id="outCity0" name="outCity"></s:hidden></font>
				</td>
			</tr>
		</table>
		</s:form>
	
		<s:form id="form1" action="AdvancedSearch" namespace="/search" method="post" theme="simple">
		<s:hidden name="searchType"></s:hidden>
		<s:hidden name="outCity"></s:hidden>
		<table style="width: 100%" border="0" id="tab_1">
			<tr bgcolor="#FFFFFF">
				<td width="120" height="25"><font size="-1">目的地：</font></td>
				<td valign="bottom"><font size="-1">
				 <s:select id="country"
                   name="country"
                   list="countryList"
                   listKey="countryId"
                   listValue="%{countryId +' '+ name}"
                   headerValue="所有国家/地区" 
                   headerKey="" onchange="javascript:CountryChanged('form1',this.value);">
	       </s:select>&nbsp;&nbsp;&nbsp;
				 <s:select id="district"
                   name="district"
                   list="districtList"
                   listKey="districtNo"
                   listValue="cnName"
                   headerValue="所有目的地" 
                   headerKey="">
         </s:select>&nbsp;&nbsp;&nbsp;</font>
				</td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td width="120" height="25"><font size="-1">出发日期：</font></td>
				<td valign="bottom">
	      <input name="s_date" size="10" type="text" onClick="WdatePicker()"/> 至
	      <input name="e_date" size="10" type="text" onClick="WdatePicker()"/>
				</td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td width="120" height="25"><font size="-1">报价范围：</font></td>
				<td valign="bottom"><font size="-1">
				   <input name="price1" type="text" id="price1" size="10" value="0">&nbsp;至&nbsp;
				   <input name="price2" type="text" id="price2" size="10" value="0"></font>
				</td>
			</tr>
			
			<tr>
		      <td colspan="2">
		         <input type="submit" value="搜索" onclick="javascript:goSearch('form1');">
	          </td>
	      </tr>
		</table>
		</s:form>

	  <s:form id="form2" action="AdvancedSearch" namespace="/search" method="post" theme="simple">
	  <s:hidden name="searchType"></s:hidden>
		<s:hidden name="outCity"></s:hidden>
		<table style="width: 100%" border="0" id="tab_2" style="display: none">
		  <tr bgcolor="#FFFFFF">
			  <td width="120" height="25"><font size="-1">目的地：</font></td>
				<td valign="bottom"><font size="-1">
				   <s:select id="country"
                     name="country"
                     list="countryList"
                     listKey="countryId"
                     listValue="%{countryId +' '+ name}"
                     headerValue="所有国家/地区" 
                     headerKey="" onchange="javascript:fitChange('form2',this.value);">
	         </s:select>
	         <s:select id="province"
                     name="province"
                     list="provinceList"
                     listKey="code"
                     listValue="cnName"
                     headerValue="所有省/直辖市" 
                     headerKey=""  onchange="javascript:provinceChanged('form2',this.value);"  cssStyle="display: none">
	         </s:select>
				   <s:select id="district"
                     name="district"
                     list="districtList"
                     listKey="districtNo"
                     listValue="cnName"
                     headerValue="所有目的地" 
                     headerKey="">
	         </s:select></font>
				</td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td width="120" height="25"><font size="-1">出发日期：</font></td>
				<td valign="bottom">
          <input name="s_date" size="10" type="text" onClick="WdatePicker()"/>
          &nbsp;至&nbsp; 
          <input name="e_date" size="10" type="text" onClick="WdatePicker()"/>
				</td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td width="120" height="25"><font size="-1">报价范围：</font></td>
				<td valign="bottom"><font size="-1">
				   <input name="price1" type="text" id="price1" size="10" value="0">&nbsp;至&nbsp;
				   <input name="price2" type="text" id="price2" size="10" value="0"></font>
				</td>
			</tr>
			
			<tr>
		      <td colspan="2">
		         <input type="submit" value="搜索" onclick="javascript:goSearch('form2');">
	          </td>
	      </tr>
		</table>
	  </s:form>

	  <s:form id="form3" action="AdvancedSearch" namespace="/search" method="post" theme="simple">
	  <s:hidden name="searchType"></s:hidden>
		<s:hidden name="outCity"></s:hidden>
		<table style="width: 100%" border="0" id="tab_3" style="display: none">
			<tr bgcolor="#FFFFFF">
				<td width="120" height="25"><font size="-1">目的地：</font></td>
				<td valign="bottom"><font size="-1">
				<s:select id="province"
                  name="province"
                  list="provinceList"
                  listKey="code"
                  listValue="cnName"
                  headerValue="所有省/直辖市" 
                  headerKey="" onchange="javascript:provinceChanged('form3',this.value);">
        </s:select>&nbsp;&nbsp;&nbsp;
				<s:select id="district"
                  name="district"
                  list="districtList"
                  listKey="districtNo"
                  listValue="cnName"
                  headerValue="所有目的地" 
                  headerKey="">
        </s:select></font>
				</td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td width="120" height="25"><font size="-1">出发日期：</font></td>
				<td valign="bottom">
          <input name="s_date" size="10" type="text" onClick="WdatePicker()"/>
          &nbsp;至&nbsp; 
          <input name="e_date" size="10" type="text" onClick="WdatePicker()"/>
				</td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td width="120" height="25"><font size="-1">报价范围：</font></td>
				<td valign="bottom"><font size="-1">
				   <input name="price1" type="text" id="price1" size="10" value="0">&nbsp;至&nbsp;
				   <input name="price2" type="text" id="price2" size="10" value="0"></font>
				</td>
			</tr>
			
			<tr>
		      <td colspan="2">
		         <input type="submit" value="搜索" onclick="javascript:goSearch('form3');">
	          </td>
	      </tr>
		</table>
	</s:form>
	
	
	<s:form id="form4" action="AdvancedSearch" namespace="/search" method="post" theme="simple">
	  <s:hidden name="searchType"></s:hidden>
		<s:hidden name="outCity"></s:hidden>
		<table style="width: 100%" border="0" id="tab_4" style="display: none">
			<tr bgcolor="#FFFFFF">
				<td width="120" height="25"><font size="-1">目的地：</font></td>
				<td valign="bottom"><font size="-1">
				<s:select id="province"
                  name="province"
                  list="provinceList"
                  listKey="code"
                  listValue="cnName"
                  headerValue="所有省/直辖市" 
                  headerKey="" onchange="javascript:provinceChanged('form4',this.value);">
        </s:select>&nbsp;&nbsp;&nbsp;
				<s:select id="district"
                  name="district"
                  list="districtList"
                  listKey="districtNo"
                  listValue="%{districtNo +' '+ name}"
                  headerValue="所有目的地" 
                  headerKey="">
        </s:select></font>
				</td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td width="120" height="25"><font size="-1">出发日期：</font></td>
				<td valign="bottom">
				  <input name="s_date" size="10" type="text" onClick="WdatePicker()"/>
          &nbsp;至&nbsp; 
          <input name="e_date" size="10" type="text" onClick="WdatePicker()"/>
				</td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td width="120" height="25"><font size="-1">报价范围：</font></td>
				<td valign="bottom"><font size="-1">
				   <input name="price1" type="text" id="price1" size="10" value="0">&nbsp;至&nbsp;
				   <input name="price2" type="text" id="price2" size="10" value="0"></font>
				</td>
			</tr>
			
			<tr>
		      <td colspan="2">
		         <input type="submit" value="搜索" onclick="javascript:goSearch('form4');">
	          </td>
	      </tr>
		  </table>
	  </s:form>
		</td>
	</tr>
</table>

</body>
</html>

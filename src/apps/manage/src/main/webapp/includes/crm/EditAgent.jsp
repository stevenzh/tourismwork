<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户信息</title>
<meta name="menu" content="AccountMenu"/>
</head>
<body>

<script type="text/javascript">
<!--//

function ProvinceChanged()
{
  var combo = document.getElementById("kenCity1")
  while (combo.options.length>0) {
      combo.remove(0);
  }
  combo.disabled=false;

  $.ajax({
      type: "post",
      url: '<s:url value="/json/citysByProvince.jspa" encode="false" includeParams="none"/>',
      timeout: 20000,
      error: function(){
          // alert('服务器错误');
      },
      data: {provinceId: $("#kenProvince1").val()},
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


function SubmitDeleteContact(param,target)
{
  var fm = document.submitEditAgent;
  fm.idx.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='deleteAgentContact' namespace='/crm'/>"
  }
  fm.submit();
}

function cancel()
{
  var fm=document.submitEditAgent;
  fm.action="<s:url action='showAgentList' namespace='/crm' includeParams='none'/>"
  fm.submit();
}

function checkOut()
{
   var fm=document.submitEditAgent;
   var str="";
   
   if(document.getElementById("name").value=="")
     str=str + "客户名称必须输入!\n";
   if(document.getElementById("address").value=="")
     str=str + "客户地址必须输入!\n";
   if(document.getElementById("district").value=="")
     str=str + "所在区必须输入!\n";
   if(document.getElementById("zipCode").value=="")
     str=str + "邮政编码必须输入!\n";
   if(document.getElementById("contactTel").value=="")
     str=str + "客户电话必须输入!\n";
   if(document.getElementById("contactFax").value=="")
     str=str + "客户传真必须输入!\n";
   
   if(str != "")
   {
     alert(str);
       return;
     }
   fm.submit();
}

//-->
</script>
<table style="width: 100%">
  <tr>
    <td class="header">
    <s:if test="customerId == 0">添加客户信息</s:if>
    <s:else>修改客户信息</s:else>
    </td>
  </tr>
</table>

<s:form action="submitEditAgent" namespace="/crm" method="POST" theme="simple">
  <s:hidden name="kenName" />
  <s:hidden name="kenState" />
  <s:hidden name="kenClearingCycle" />
  <s:hidden name="salesId" />
  
  <s:hidden name="kenCountryId" />
  <s:hidden name="kenProvince"/>
  <s:hidden name="kenCity"/>
  <s:hidden name="teamId"/>
  <s:hidden name="currentPage"></s:hidden>

  <s:hidden name="agent.customerId"></s:hidden>

  <table style="width: 100%">
    <tr>
      <td class="idx" width="200">名称<span class="required">*</span>:</td>
      <td class="data" colspan="3">
        <s:textfield id="name" name="agent.name" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <td class="idx">地址<span class="required">*</span>:</td>
      <td class="data" colspan="3">
        <s:textfield id="address" name="agent.address" size="40" maxlength="40"/>
      </td>
    </tr>
    <tr>
      <td class="idx">所在国家<span class="required">*</span>:</td>
      <td class="data">
        <s:select id="kenCountry"
	                name="supplier.countryCd"
	                list="countryList"
	                listKey="countryId"
	                listValue="name"
	                onchange="javascript:CountryChanged();">
        </s:select>
      </td>
      <td class="idx">所在省<span class="required">*</span>:</td>
      <td class="data" colspan="3">
	      <s:select id="kenProvince1"
	                list="provinceList"
	                name="agent.provinceCd"
	                listKey="code"
	                listValue="cnName"
	                emptyOption="true"
	                headerKey=""
	                headerValue="请选择..."
	                onchange="javascript:ProvinceChanged();">
	       </s:select>
       </td>
    </tr>
    <tr>
      <td class="idx">所在城市<span class="required">*</span>:</td>
      <td class="data">
	      <s:select id="kenCity1"
	                list="cityList"
	                name="agent.city.citycd"
	                listKey="citycd"
	                listValue="%{citycd + citynm}"
	                headerKey=""
	                headerValue="请选择..."
	                emptyOption="true">
	      </s:select>
	      <s:hidden id="agent_city" name="agent.cityName"></s:hidden>
      </td>
      <td class="idx">所在区(例：长宁区)<span class="required">*</span>:</td>
      <td class="data">
        <s:textfield id="district" name="agent.district" size="20" maxLength="20"/>
      </td>
    </tr>
    <tr>
      <td class="idx">邮政编码<span class="required">*</span>:</td>
      <td class="data" colspan="3"><s:textfield id="zipCode" name="agent.zipCode" size="12" maxLength="12"/></td>
    </tr>

    <tr>
      <td class="idx">公司性质:</td>
      <td class="data">
      <s:select name="agent.structure"
                list="comStructure"
                listKey="value"
                listValue="label">
      </s:select>
      </td>
      <td class="idx"><span class="required">结算方式:</span></td>
      <td class="data">
      <s:select name="agent.clearingCycle"
                list="comClearing"
                listKey="value"
                listValue="label">
      </s:select>
      </td>
    </tr>
    <tr>
      <td class="idx">营业执照编号:</td>
      <td class="data"><s:textfield name="agent.bussId" /></td>
      <td class="idx">营业执照登记日期:</td>
      <td class="data">
      <s:textfield size="10" displayFormat="yy-mm-dd" maxlength="10" name="agent.bussDate">
       </s:textfield>
       </td>
    </tr>

    <tr>
      <td class="idx">信用额度:</td>
      <td class="data"><s:textfield name="agent.creditAmt2" />元</td>
      <td class="idx">超过额度百分比:</td>
      <td class="data"><s:textfield name="agent.stay" size="5" maxlength="5"/>%</td>
    </tr>
    <tr>
      <td class="idx">法人<span class="required">*</span>:</td>
      <td><s:textfield id="contactName" name="agent.contact" /></td>
      <td class="idx">Email<span class="required">*</span>：</td>
      <td><s:textfield name="agent.contactEmail" size="30" maxlength="30"/></td>
    </tr>
    <tr>
      <td class="idx">电话<span class="required">*</span>:</td>
      <td class="data"><s:textfield id="contactTel" name="agent.contactTel" size="20" maxLength="20" /></td>
      <td class="idx">传真<span class="required">*</span>:</td>
      <td class="data"><s:textfield id="contactFax" name="agent.contactFax" size="20" maxLength="20"/></td>
    </tr>

    <tr>
      <td class="idx">代理我们的产品<span class="required">*</span>：</td>
      <td class="data">
				<s:checkbox name="agent.isAgent" ></s:checkbox>
      </td>
      <td class="idx">提供产品资源<span class="required">*</span>：</td>
      <td class="data">
				<s:checkbox name="agent.isSupplier" ></s:checkbox>
      </td>
    </tr>
    <tr>
      <td class="idx">提供资源<span class="required">*</span>：</td>
      <td colspan="3">
      <s:select name="supplier.resource"
                list="resourceList"
                listKey="value"
                listValue="label">
      </s:select>
      </td>
    </tr>

    <tr>
      <td class="idx">销售员</td>
      <td class="data" colspan="3">
      <s:select name="agent.sales.userId"
                list="saleList"
                listKey="userId"
                listValue="userName"
                emptyOption="true">
      </s:select>
      </td>
    </tr>
    <tr>
      <td class="idx">登记人:</td>
      <td><s:property value="agent.createdBy" /></td>
      <td class="idx">登记时间:</td>
      <td><s:date name="agent.created" format="yyyy-MM-dd HH:mm:ss" /></td>
    </tr>
    <tr>
      <td class="idx">修改人：</td>
      <td><s:property value="agent.updatedBy" /></td>
      <td class="idx">修改时间:</td>
      <td><s:date name="agent.updated" format="yyyy-MM-dd HH:mm:ss" /></td>
    </tr>
    <tr>
      <td colspan="4">
      <br>
      <s:hidden name="idx" />
      <table style="width: 80%">
        <tr>
          <td colspan="10" class="header">其他联系人</td>
        </tr>
        <tr>
          <td class="lstidx">No.</td>
          <td class="lstidx">姓名</td>
          <td class="lstidx">职位</td>
          <td class="lstidx">电话</td>
          <td class="lstidx">传真</td>
          <td class="lstidx">移动电话</td>
          <td class="lstidx">E-Mail</td>
          <td class="lstidx">MSN</td>
          <td class="lstidx">腾讯QQ</td>
          <td class="lstidx" nowrap="nowrap">操作</td>
        </tr>
      
        <s:if test="contacts!=null">
        <s:iterator value="contacts" status="rowCounter">
    
        <tr> 
          <td>
            <s:property value="#rowCounter.count" />
          </td>
          <td class="data" valign="top">
            <s:hidden name="contacts(%{idx}).contactId" value="%{contactId}"></s:hidden>
            <s:textfield name="contacts(%{idx}).name"
                         value="%{name}"
                         size="15"
                         maxlength="15">
            </s:textfield>
         </td>
         <td>
            <s:textfield name="contacts(%{idx}).rank"
                         value="%{rank}"
                         size="15"
                         maxlength="15">
            </s:textfield>
         </td>
         <td class="data" valign="top">
            <s:textfield name="contacts(%{idx}).phone" 
                         value="%{phone}" 
                         size="20"
                         maxlength="20">
            </s:textfield>
         </td>
         <td class="data" valign="top">
            <s:textfield name="contacts(%{idx}).fax"
                         value="%{fax}"
                         size="20"
                         maxlength="20">
            </s:textfield>
         </td>
         <td class="data" valign="top">
            <s:textfield name="contacts(%{idx}).mobile"
                         value="%{mobile}"
                         size="11"
                         maxlength="20">
            </s:textfield>
         </td>
         <td class="data" valign="top">
            <s:textfield name="contacts(%{idx}).email" 
                         value="%{email}"
                         size="20"
                         maxlength="60">
            </s:textfield>
         </td>
         <td class="data" valign="top">
            <s:textfield name="contacts(%{idx}).msn" 
                         value="%{msn}"
                         size="20"
                         maxlength="60">
            </s:textfield>
         </td>
         <td class="data" valign="top">
            <s:textfield name="contacts(%{idx}).qq" 
                         value="%{qq}"
                         size="20"
                         maxlength="60">
            </s:textfield>
         </td>
         <td class="data" valign="top"><a href="javascript:SubmitDeleteContact('delete','<s:property value="idx" />')">删除</a></td>
        </tr> 
        </s:iterator>
        </s:if>
        <tr>
          <td colspan="5">
            <s:submit action="addAgentContact" value="增加联系人" />
          </td>
        </tr>
      </table>
      <br>
      </td>
    </tr>
    <tr>
      <td colspan="10">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2">
       <input type="button" value="保存" onclick="javascript:checkOut();">
       <authz:authorize ifAnyGranted="ROLE_AGENT_MANAGER">
       <s:submit action="checkedAgent" value="审核" />
       </authz:authorize>
       <input type="button" value="返回" onclick="javascript:cancel();" />
      </td>
    </tr>
  </table>

</s:form>

</body>
</html>

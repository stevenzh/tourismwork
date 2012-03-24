<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>计调组对应供应商</title>
<meta name="menu" content="AccountMenu"/>
<meta name="heading" content="计调组对应供应商">
</head>

<body>
<script type="text/javascript">
<!--//

function SubmitForm(param, target)
{
  var fm = document.showGroupSupplier;
  fm.supplierId.value=target;
  if (param =='modify')
  {    
    fm.action = "<s:url action='editGroupSupplier' namespace='/crm'/>"
  }　else if(param =='add')
  {    
    fm.action = "<s:url action='editGroupSupplier' namespace='/crm'/>"
  } else if (param == 'delete')
  {
  　　if (confirm("是否确定删除该供应商？") == false) {
      return;
    }
 　　fm.action = "<s:url action='deleteGroupSupplier' namespace='/crm'/>"
  }
    
  fm.submit();
}
//-->
</script>
<s:form action="showGroupSupplier" namespace="/crm" method="POST" theme="simple">
<s:hidden name="supplierId"></s:hidden>

<authz:authorize ifAnyGranted="ROLE_SUPPLIER_MANAGER">
<table border="0" cellpadding="0" cellspacing="2" width="100%">
	<tr>
		<td class="idx" width="100">计调组</td>
		<td width="250">
    
		  <s:select name="groupId"
			  		  	list="teamList"
			  	    	listKey="teamId"
			  		    listValue="name">
		  </s:select>

		  </td>		  <td>&nbsp;</td>
	</tr>
	<tr>
  	<td class="idx" width="100">国家</td>
    <td colspan="2">
      <s:select name="kenCountryId"
                list="countryList"
                listKey="countryId"
                listValue="name"
                headerKey=""
                headerValue="所有"
                theme="simple">
      </s:select>
    </td>
    <td>&nbsp;</td>
	</tr>
    <tr>
      <td class="idx">地接资源:</td>
      <td>
        <s:select list="resourceList"
                  name="kenSupplierResource"
                  listKey="value"
                  listValue="label"
                  headerKey=""
                  headerValue="所有">
        </s:select>
      </td>
    </tr>
  <tr>
    <td colspan="2" width="250">&nbsp;</td>
    <td><s:submit action="searchGroupSupplier" value="列出该国家的供应商"/></td>
  </tr>
</table>
</authz:authorize>

<br>
  
<input type="button" value="添加供应商" onclick="SubmitForm('add','')" />

<table width="100%">
  <tr>
	<td colspan="10">
  	<table border="1" cellpadding="0" cellspacing="0" width="100%">
  	<tr>
      <td class="lstidx">No.</td>
      <authz:authorize ifAnyGranted="ROLE_SUPPLIER_MANAGER">
      <td class="lstidx">选定</td>
      </authz:authorize>
      <td class="idx">客户</td>
      <td class="idx">联系人</td>
      <td class="idx">联系电话</td>
      <td class="idx">状态</td>
      <td class="idx">操作</td>
    </tr>

    <s:iterator value="listSupplier" status="rowcounter">
      <s:if test='isActive eq "Y"'>
	    <tr>
        <td class="cdata" valign="top"><s:property value="#rowcounter.count" /></td>
        <authz:authorize ifAnyGranted="ROLE_SUPPLIER_MANAGER">
        <td class="data" valign="top">
        <s:checkbox name="select"
                    value="%{checked}"
                    fieldValue="%{supplierId}">
        </s:checkbox>
        </td>
        </authz:authorize>
	      <td class="lst"><a href="#" onclick="SubmitForm('modify','<s:property value="supplierId" />')"><s:property value="name" /></a></td>
	      <td class="lst"><s:property value="contact" />&nbsp;</td>
	      <td class="lst"><s:property value="contactTel" />&nbsp;</td>
        <td>已审核</td>
        <td class="lst">&nbsp;
        <authz:authorize ifAnyGranted="ROLE_SUPPLIER_MANAGER">
        <a href="#" onclick="SubmitForm('delete','<s:property value="supplierId" />')">删除</a>
        </authz:authorize>
        </td>
	    </tr>
      </s:if>
      <s:else>
      <tr bgcolor="#DB9999">
        <td align="center"><s:property value="#rowcounter.count" /></td>
        <authz:authorize ifAnyGranted="ROLE_SUPPLIER_MANAGER">
        <td>
        <s:checkbox name="select"
                    value="%{checked}"
                    fieldValue="%{supplierId}">
        </s:checkbox>
        </td>
        </authz:authorize>
        <td><a href="#" onclick="SubmitForm('modify','<s:property value="supplierId" />')"><s:property value="name" /></a></td>
        <td><s:property value="contact" />&nbsp;</td>
        <td><s:property value="contactTel" />&nbsp;</td>
        <td>未审核</td>
        <td align="center"><a href="#" onclick="SubmitForm('delete','<s:property value="supplierId" />')">删除</a></td>
      </tr>
      </s:else>
    </s:iterator>
    
    <tr>
      <td colspan="8">&nbsp;</td>
    </tr>

    <s:iterator value="supplierList" status="rowcounter">
      <s:if test='isActive eq "Y"'>
      <tr>
        <td class="cdata" valign="top"><s:property value="#rowcounter.count" /></td>
        <authz:authorize ifAnyGranted="ROLE_SUPPLIER_MANAGER">
        <td class="data" valign="top">
        <s:checkbox name="select"
                    value="%{checked}"
                    fieldValue="%{supplierId}">
        </s:checkbox>
        </td>
        </authz:authorize>
        <td class="lst"><a href="#" onclick="SubmitForm('modify','<s:property value="supplierId" />')"><s:property value="name" /></a></td>
        <td class="lst"><s:property value="contact" />&nbsp;</td>
        <td class="lst"><s:property value="contactTel" />&nbsp;</td>
        <td>已审核</td>
        <td class="lst">&nbsp;
        <authz:authorize ifAnyGranted="ROLE_SUPPLIER_MANAGER">
        <a href="#" onclick="SubmitForm('delete','<s:property value="supplierId" />')">删除</a>
        </authz:authorize>
        </td>
      </tr>
      </s:if>
      <s:else>
      <tr bgcolor="#DB9999">
        <td align="center"><s:property value="#rowcounter.count" /></td>
        <authz:authorize ifAnyGranted="ROLE_SUPPLIER_MANAGER">
        <td>
        <s:checkbox name="select"
                    value="%{checked}"
                    fieldValue="%{supplierId}">
        </s:checkbox>
        </td>
        </authz:authorize>
        <td><a href="#" onclick="SubmitForm('modify','<s:property value="supplierId" />')"><s:property value="name" /></a></td>
        <td><s:property value="contact" />&nbsp;</td>
        <td><s:property value="contactTel" />&nbsp;</td>
        <td>未审核</td>
        <td align="center"><a href="#" onclick="SubmitForm('delete','<s:property value="supplierId" />')">删除</a></td>
      </tr>
      </s:else>
    </s:iterator>

  </table>
  </td>
  </tr>
  </table>

<authz:authorize ifAnyGranted="ROLE_SUPPLIER_MANAGER">
  <div align="center"><s:submit value="保存" action="submitGroupSupplier"></s:submit> </div>
</authz:authorize>

</s:form>

</body>
</html>

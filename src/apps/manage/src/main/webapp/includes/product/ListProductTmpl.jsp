<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品描述模板</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="产品描述模板">
</head>

<body>

<script language="JavaScript">
<!--//
function SubmitForm(param, target)
{
  var fm = document.showProductTmpl;
  fm.tmplId.value=target;
  
  if (param == 'modify') {
    fm.action = "<s:url action='editProductTmpl' namespace='/product'/>"
  } else if (param == 'add') {
    fm.action = "<s:url action='editProductTmpl' namespace='/product'/>"
  } else if (param == 'del') {
    fm.action = "<s:url action='deleteProductTmpl' namespace='/product'/>"
    if (confirm("确定要删除此分类吗？") == false) {
      return;
    }
  }
  fm.submit();
}

function SubmitQuery()
{
  var fm = document.showProductTmpl;
  fm.action = "<s:url action='queryProductTmpl' namespace='/product'/>"
  fm.submit();
}

function _getlist(type)
{
  var frm = document.showProductTmpl;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}
//-->
</script>

<s:form action="showProductTmpl" namespace="/product" method="post" theme="simple">
<s:hidden name="tmplId"></s:hidden>

<table border="0" style="width: 100%">
  <tr>
    <td class="idx">工作组：</td>
    <td>
	    <s:select name="teamId"
	              list="teamList"
	              listKey="teamId"
	              listValue="name"
	              headerKey="0"
	              headerValue="全部">
	    </s:select>
    </td>
    <td class="idx">请选择目的地:</td>
    <td>
			<s:select name="destCode"
			    		  list="destinationList"
			    		  listKey="code"
			    		  listValue="%{code +' '+ cnName}"
			    		  headerKey=""
			    		  headerValue="全部">
		   </s:select>
    </td>
  </tr>
  <tr>
    <td class="idx">描述项目:</td>
    <td>
		  <s:select name="itemId"
		            list="itemList"
		            listKey="itemId"
		            listValue="itemName1"
		            headerKey="0"
		            headerValue="全部">
		   </s:select>
    </td>
    <td class="header" colspan="2">
    <input type="button" value="搜索" onclick="javascript:SubmitQuery();">
    </td>
  </tr>
</table>
<br></br>
<table border="0" style="width: 100%">

  <tr>
    <td class="lstidx">NO.</td>
    <td class="lstidx">团队</td>
    <td class="lstidx">Item</td>
    <td class="lstidx">目的地</td>
    <td class="lstidx">标题</td>
    <td class="lstidx">内容</td>
    <td colspan="2" class="lstidx">操作</td>
  </tr>
  
  <s:iterator value="tmplList" status="rowcounter">
    <s:if test="#rowcounter.count > fromRecord">
    <s:if test="#rowcounter.count <= toRecord">
      <tr>
        <td class="cdata"><s:property value="#rowcounter.count"/> </td>
        <td class="cdata" nowrap="nowrap"><s:property  value="team.name"/></td>
        <td class="cdata" nowrap="nowrap"><s:property value="item.itemName"/></td>
        <td class="cdata">
	      <s:select list="destinationList"
				    		  listKey="code"
				    		  listValue="%{code +' '+ cnName}"
				    		  value="%{destCode}"
				    		  headerKey=""
				    		  headerValue="" disabled="true">
			  </s:select>
		    </td>
        <td class="data" nowrap="nowrap"><s:property value="subject"/>&nbsp;</td>
        <td class="data"><s:property value="content"/>&nbsp;</td>
        <td align="center" nowrap="nowrap"><a href="javascript:SubmitForm('modify','<s:property value="id"/>')" >修改</a>
        <a href="javascript:SubmitForm('del','<s:property value="id"/>')">删除</a></td>
      </tr>
     </s:if>
     </s:if>
    </s:iterator>

    <tr>
      <td colspan="12">
        <s:if test="tmplList.isEmpty() == false">
        <%@ include file="/includes/PagerTable.jsp" %>
        </s:if>
      </td>
    </tr>
</table>
<center><input type="button" class="button" value="添加"  onclick="javascript:SubmitForm('add','')"></center>
</s:form>
</body>
</html>

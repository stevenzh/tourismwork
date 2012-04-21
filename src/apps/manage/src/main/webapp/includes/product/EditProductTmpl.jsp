<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品描述模板</title>
<meta name="menu" content="ProductMenu"/>
</head>

<body>
<script language="JavaScript">
<!--//
function cancel(){
  var fm = document.updateProductTmpl;
  fm.action = "<s:url action='showProductTmpl' namespace='/product'/>"
  fm.submit();
}
//-->
</script>

<table border="0" style="width: 100%">
  <tr>
  	<td class="header"><s:if test='tmpl.id == 0'>添加产品描述模板</s:if>
  	<s:else>修改产品描述模板</s:else></td>
  </tr>
</table>

<s:form action="updateProductTmpl" method="POST" namespace="/product" theme="simple">
  <s:hidden name="tmplId"></s:hidden>
  <s:hidden name="teamId"></s:hidden>
  <s:hidden name="itemId"></s:hidden>
  
  <s:hidden name="tmpl.id"></s:hidden>
  <table cellpadding="2">
  	<tr>
      <td class="idx">工作组：</td>
      <td>
  	    <s:select name="tmpl.team.teamId"
  	              list="teamList"
  	              listKey="teamId"
  	              listValue="name"
  	              headerKey="0"
  	              headerValue="全部">
  	    </s:select>
        </td>
      <td class="idx">目的地 </td>
      <td class="data">
      	<s:select name="tmpl.destCode"
      	          value="%{tmpl.destCode}"
          			  list="destinationList"
          			  listKey="code"
          			  listValue="cnName"
          			  headerKey=""
          			  headerValue="请选择...">
          </s:select>
      </td>
  	</tr>
  	<tr>
      <td class="idx">描述项目:</td>
      <td>
  		  <s:select name="tmpl.item.itemId"
                  value="%{tmpl.item.itemId}"
  		            list="itemList"
  		            listKey="itemId"
  		            listValue="itemName1"
  		            headerKey="0"
  		            headerValue="全部">
  		   </s:select>
      </td>
      <td class="idx">标题</td>
      <td class="data">
        <s:textfield name="tmpl.subject"
                     value="%{tmpl.subject}"
                     size="10" maxlength="20">
        </s:textfield>
      </td>
  	</tr>
    <tr>
  		<td class="idx">内容:</td>
  		<td class="data" colspan="3">
  		  <s:textarea name="tmpl.content"
                    rows="5"
                    cols="100"
                    value="%{tmpl.content}">
  		  </s:textarea>
  		</td>
  	</tr>
  	<tr>
  		<td>&nbsp;<font color="red">*</font>&nbsp;为必填项目！</td>
  		<td></td>
  	</tr>
  </table>

  <table border="0" width="0%">
  	<tr>
  		<td align="center"><s:if test='tmpl.id == 0'><s:submit value="添加" />
            </s:if><s:else><s:submit value="修改"></s:submit></s:else>
            <input type="button" value="返回" onclick="javascript:cancel()"></td>
  	</tr>
  </table>
</s:form>
</body>
</html>

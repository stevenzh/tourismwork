<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品描述</title>
<meta name="menu" content="ProductMenu"/>
</head>

<body>

<script type="text/javascript">
<!--//
function SubmitForm(param, target)
{
  var fm = document.addRouteRule;
  fm.idx.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='deleteRouteRule' namespace='/product'/>"
  }
  fm.submit();
}

function selectAll()
{              
	var cb = document.all.checked;	
		for(i=0;i<cb.length;i++)
		{
			cb[i].checked = true;
		}
}

//-->
</script>

<div>
<br>
<table width="100%">
  <tr>
    <td class="header">[线路管理] <s:property value="line.lineName" />-<s:property value="item.itemName"></s:property></td>
  </tr>
</table>
<s:set name="oTitle" value="item.hasTitle"/>
<s:set name="oTmpl" value="item.isTmpl"/>

<s:form action="addRouteRule" namespace="/product" method="post" theme="simple" cssClass="yform">
<s:hidden name="idx" />
<s:hidden name="itemType"/>

<table>
    <tr>
      <td class="lstidx">No.</td>
      <s:if test="#oTitle">
      <td class="lstidx">标题</td>
      </s:if>
      <td class="lstidx">描述</td>
      <td class="lstidx" nowrap="nowrap">操作</td>
    </tr>

    <s:if test="#oTmpl">
	  <s:iterator value="tmpl" id="tp">
    <tr>
      <td class="lstidx">
      <s:checkbox name="checked" fieldValue="%{#tp.id}" id="1">
      </s:checkbox>
      </td>
      <s:if test="#oTitle">
      <td valign="top"><s:property value="#tp.subject"/></td>
      </s:if>
      <td valign="top"><s:property value="#tp.content"/></td>
      <td>&nbsp;</td>
    </tr>
	  </s:iterator>

    <tr>
      <td colspan="3"><font color="#CC0000"><STRONG>以上为<s:property value="item.itemName"/>的模板，选择所需要的文字保存，成为该线路的“<s:property value="item.itemName"/>”．</STRONG></font></td>
    </tr>
    </s:if>
    <s:iterator value="ruleList" id="ru">
      <tr>
        <td valign="top">
          <s:hidden name="ruleList(%{#ru.refNo}).id" value="%{#ru.id}"></s:hidden>
          <s:textfield value="%{#ru.refNo}"
                       size="5"
                       maxlength="2"
                       readonly="true">
          </s:textfield>
        </td>
        <s:if test="#oTitle">
        <td valign="top">
          <s:textfield name="ruleList(%{#ru.refNo}).item"
                       value="%{#ru.item}"
                       size="5"
                       maxlength="6">
          </s:textfield>
        </td>
        </s:if>
        <td class="data">
          <s:textarea name="ruleList(%{#ru.refNo}).description"
                       value="%{#ru.description}"
                       cols="100"
                       rows="5">
          </s:textarea>
        </td>
        <td>
        <a href="#" onClick="javascript:SubmitForm('delete','<s:property value="%{#ru.refNo}" />')" title="删除">删除</a></td>
       
      </tr>
    </s:iterator>

    <tr>
      <td colspan="3">
      <s:if test="#oTmpl">
      <input type="button" id="btnSelectAll" onclick="javascript:selectAll()" value="全选">
      </s:if>
      <input type="button" value="增加一行" onclick="SubmitForm('add', '2');" />
      <s:submit action="saveRouteRules" value="保存" />
      </td>
    </tr>

</table>

</s:form>
</div>

</body>
</html>
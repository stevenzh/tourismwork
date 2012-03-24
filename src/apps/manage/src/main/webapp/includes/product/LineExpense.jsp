<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品费用包含</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="产品费用包含">
</head>

<body>
<script type="text/javascript">
<!--//
function SubmitForm(param, target)
{
  var fm = document.RouteExpenseAdd;
  fm.refNo.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='RouteExpenseDelete' namespace='/product'/>"
  }
  fm.submit();
}

//-->
</script>

<s:form action="RouteExpenseAdd" namespace="/product" method="post" theme="simple">
  <s:hidden name="refNo" />
  <table>
    <tr>
      <td class="idx">直客包含自费项目</td>
      <td colspan="2">
      <s:textarea name="zkOwnExpense"
                  rows="2"
                  cols="80">
      </s:textarea>
      </td>
    </tr>
    <tr>
      <td class="idx">同业必须保证自费项目</td>
      <td colspan="2">
      <s:textarea name="tyOwnExpense"
                  rows="2"
                  cols="80">
      </s:textarea>
      </td>
    </tr>
  </table>
  <table>
    <tr>
      <td class="idx">No.</td>
      <td class="idx">项目</td>
      <td class="idx">描述</td>
      <td class="idx">是否包含</td>
      <td class="idx">操作</td>
    </tr>

    <s:iterator value="expenseList">
      <tr>
        <td valign="top">
          <s:hidden name="expenseList(%{refNo}).id" value="%{id}"></s:hidden>
          <s:textfield value="%{refNo}"
                       readonly="true"
                       size="3">
          </s:textfield>
        </td>
        <td valign="top">
          <s:textfield name="expenseList(%{refNo}).item"
                       value="%{item}"
                       size="20"
                       maxLength="20"
                       disabled="true">
          </s:textfield>
        </td>
        <td valign="top" class="data">
          <s:textarea name="expenseList(%{refNo}).traitDetail"
                       value="%{traitDetail}"
                       cols="80"
                       rows="5">
          </s:textarea>
        </td>
        <td valign="top" class="idx">
          <s:radio name="expenseList(%{refNo}).expenseType"
                   list="expenseTypes"
                   listKey="value"
                   listValue="label"
                   value="%{expenseType}">
          </s:radio>
        </td>
        <td valign="top">
        <s:if test='canDel eq "Y"'>
        <a href="#" onClick="javascript:SubmitForm('delete','<s:property value="refNo" />')">删除</a>
        </s:if>
        </td>
      </tr>
    </s:iterator>

    <tr>
      <td colspan="3"><s:submit action="RouteExpenseAdd" value="增加一行" /> &nbsp;&nbsp; 
      <s:submit action="RouteExpenseSave" value="保存" />
      </td>
    </tr>

  </table>

</s:form>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>填写发票</title>
<meta name="menu" content="FinanceMenu"/>
<meta name="heading" content="填写发票">
</head>

<body>
<center>
<s:form action="submitInvoice" namespace="/finance" method="POST" theme="simple">
<s:hidden name="incomeId"></s:hidden>
<table width="760" border="0" cellpadding="0" cellspacing="0" bordercolor="#000000">
  <tr>
    <td colspan="5">
    <table width="100%" border="0" cellpadding="0">
      <tr>
        <td align="center" class="title" height="25">发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;票</td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="62" align="left" nowrap>客户名称：</td>
    <td width="446" height="24">
    <s:textfield name="invoice.customer"
                 size="70"
                 maxlength="70">
    </s:textfield>
    </td>
    <td width="45">日期：</td>
    <td width="140"></td>
  </tr>
  <tr>
    <td colspan="4">
    <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#000000">
      <tr valign="top">
        <td width="70%">
        <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#000000">
          <tr bgcolor="#f2f2f2">
            <td align="center" width="75%" height="30">项&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目</td>
            <td align="center" width="25%">金&nbsp;&nbsp;&nbsp;&nbsp;额</td>
          </tr>

          <s:iterator value="items">
          <tr>
            <td height="25" align="center">
            <s:textfield name="items(%{id}).item"
                         value="%{item}"
                         size="60">
            </s:textfield></td>

            <td align="center">
            <s:textfield name="items(%{id}).expense"
                         value="%{expense}"
                         size="18"
                         maxlength="9"
                         cssStyle="text-align: right">
            </s:textfield></td>
          </tr>
          </s:iterator>

          <tr>
            <td height="31">人民币金额大写</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td height="31">&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
        </td>
        <td width="30%">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#000000">
          <tr valign="middle">
            <td height="31" colspan="2" align="left">收款方式：</td>
          </tr>
          <tr>
            <td width="21%" nowrap height="20">现金</td>
            <td width="79%" valign="middle">
            <s:checkbox name="pieces(0).type">
            </s:checkbox>&nbsp;
            <s:textfield name="pieces(0).amount"
                         size="16"
                         maxlength="9"
                         cssStyle="text-align: right">
             </s:textfield>
            </td>
          </tr>
          <tr>
            <td nowrap height="20">支票</td>
            <td valign="middle">
            <s:checkbox name="pieces(1).type">
            </s:checkbox>&nbsp;
            <s:textfield name="pieces(1).amount"
                         size="16"
                         maxlength="9"
                         cssStyle="text-align: right">
             </s:textfield>
             </td>
          </tr>
          <tr>
            <td nowrap height="20">转帐</td>
            <td valign="middle">
            <s:checkbox name="pieces(2).type">
            </s:checkbox>&nbsp;
            <s:textfield name="pieces(2).amount"
                         size="16"
                         maxlength="9"
                         cssStyle="text-align: right">
             </s:textfield>
            </td>
          </tr>
          <tr>
            <td nowrap height="20">信用卡</td>
            <td valign="middle">
            <s:checkbox name="pieces(3).type">
            </s:checkbox>&nbsp;
            <s:textfield name="pieces(3).amount"
                         size="16"
                         maxlength="9"
                         cssStyle="text-align: right">
             </s:textfield>
            </td>
          </tr>
          <tr>
            <td nowrap height="20">其他</td>
            <td valign="middle">
            <s:checkbox name="pieces(4).type">
            </s:checkbox>&nbsp;
            <s:textfield name="pieces(4).amount"
                         size="16"
                         maxlength="9"
                         cssStyle="text-align: right">
             </s:textfield>
            </td>
          </tr>
          <tr>
            <td height="58" colspan="2">
            <s:textarea name="invoice.remarks"
                        cols="32"
                        rows="3">
            </s:textarea>
            </td>
          </tr>
          <tr>
            <td colspan="2">账号：000000-00000000000<br>开户行：XXXXXXX支行</td>
          </tr>
        </table>
        </td>
      </tr>
	  <TR>
	    <TD colspan="2">
		 <table width="100%" border="0">
      <tr>
        <td width="53%">&nbsp;</td>
        <td width="23%">出纳
            <s:textfield name="invoice.casher"
                         size="16"
                         maxlength="8">
             </s:textfield></td>
        <td width="24%">经办人
            <s:textfield name="invoice.signature"
                         size="16"
                         maxlength="8">
             </s:textfield></td>
      </tr>
    </table></TD>
	  </TR>
    </table>
    </td>
  </tr>
  <tr>
    <td colspan="5" align="center">
    <s:submit value="提交"></s:submit>&nbsp;&nbsp;
    <s:reset value="清空"></s:reset>
   </td>
  </tr>
</table>

</s:form>

</center>
</body>
</html>

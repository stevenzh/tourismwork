<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加险种信息</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="添加险种信息">
</head>

<body>
<s:form action="submitPremium" namespace="/resource" method="post" theme="simple">
  <s:hidden name="preminuCode"></s:hidden>
  <table style="width: 100%" border="0" align="center">
    <tr>
      <td class="idx">险种：</td>
      <td class="data">
      <s:if test='tblPremium.precode == null'><s:textfield name="tblPremium.precode" size="8" 
            maxLength="8"></s:textfield><font color="ff0000">*</font>
      </s:if>
      <s:else>
        <s:property value="tblPremium.precode"/>
        <s:hidden name="tblPremium.precode"></s:hidden>
      </s:else>
      </td>
    </tr>
    <tr>
      <td class="idx">保费：</td>
      <td class="data"><s:textfield name="tblPremium.prem" /><font color="ff0000">*</font></td>
    </tr>
    <tr>
      <td class="idx">保险期限：</td>
      <td class="data"><s:textfield name="tblPremium.preday" /><font color="ff0000">*</font></td>
    </tr>
    <tr>
      <td class="idx">意外保额：</td>
      <td class="data"><s:textfield name="tblPremium.ywpre" /><font color="ff0000">*</font></td>
    </tr>
    <tr>
      <td class="idx">意外医疗保额：</td>
      <td class="data"><s:textfield name="tblPremium.ylpre" /><font color="ff0000">*</font></td>
    </tr>
    <tr>
      <td class="idx">补充保额：</td>
      <td class="data"><s:textfield name="tblPremium.bcpre" /><font color="ff0000">*</font></td>
    </tr>
    <tr>
      <td class="idx">操作开始日期：</td>
      <td class="data"><sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="tblPremium.dodate" /></td>
    </tr>
    <tr>
      <td class="idx">说明：</td>
      <td class="data"><s:textarea name="tblPremium.note" cols="60" rows="3"></s:textarea></td>
    </tr>
    <tr>
    <td>
      <font color="ff0000">*</font>为必填项!</td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <s:submit value="提  交"/>
        <s:submit action="listPremium" value="返  回"/>
      </td>
    </tr>
  </table>
</s:form>
</body>
</html>


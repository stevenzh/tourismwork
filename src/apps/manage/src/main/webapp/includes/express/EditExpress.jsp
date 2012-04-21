<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改配送信息</title>
<meta name="menu" content="ExpressMenu"/>
<meta name="heading" content="修改配送信息">
</head>

<body>
<s:form action="saveExpress" namespace="/express" method="post" theme="simple">
  <s:hidden name="express.expressId" value="%{expressId}"></s:hidden>
  <s:hidden name="expressId" value="%{expressId}"></s:hidden>
  <s:hidden name="note"></s:hidden>

  <table style="width: 100%">
    <tr>
      <td class="idx">配送单号：</td>
      <td colspan="3"><s:property value="express.expressId" /></td>
    </tr>
    <tr>
      <td class="idx">团号：</td>
      <td><s:property value="express.teamNo" /></td>
      <td class="idx">线路：</td>
      <td><s:property value="express.line" /></td>
    </tr>
    <tr>
      <td class="idx">客户/旅行社：</td>
      <td><s:property value="express.customer" /></td>
      <td class="idx">订单号：</td>
      <td><s:property value="express.orderId"></s:property></td>
    </tr>
    <tr>
      <td colspan="4" height="3"></td>
    </tr>
    <tr>
      <td class="idx">联系地址：</td>
      <td><s:textfield name="express.address"></s:textfield></td>
      <td class="idx">联系电话：</td>
      <td><s:textfield name="express.tel"></s:textfield></td>
    </tr>
    <tr>
      <td class="idx">联系人：</td>
      <td><s:textfield name="express.contactor"></s:textfield></td>
      <td class="idx">邮政编码：</td>
      <td><s:textfield name="express.zip"></s:textfield></td>
    </tr>
    <tr>
      <td colspan="4" height="3"></td>
    </tr>
    <tr>
     <td class="idx">配送类型：</td>
      <td><s:select list="expressModlueList" listKey="value" listValue="label" name="express.expressModlue"></s:select></td>
       <td class="idx">支付方式：</td>
      <td><s:select list="payModlueList" name="express.payModlue" listKey="value" listValue="label">
      </s:select></td>
    </tr>
    <tr>
      <td class="idx">收款类别：</td>
      <td><s:select list="payTypeList" name="express.payType" listKey="value" listValue="label">
      </s:select></td>
      <td class="idx">应收团款：</td>
      <td><s:textfield name="express.pay"></s:textfield></td>
    </tr>
    <tr>
      <td class="idx">配送时间：</td>
      <td><sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="express.expressDate"></sj:datepicker></td>
      <td class="idx">最后期限：</td>
      <td><sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="express.expressLastdate"></sj:datepicker></td>
    </tr>
    <tr>
      <td class="idx">配送方式：</td>
      <td colspan="3">
        <s:select list="eTypeList" listKey="value" listValue="label" name="express.expressType">
        </s:select>
      </td>
    </tr>
    <tr>
      <td class="idx">配送状态：</td>
      <td colspan="3">
        <s:select list="expressStateList" name="express.expressState" listKey="value" listValue="label">
        </s:select>
      </td>
    </tr>
    <tr>
      <td colspan="4" height="5"></td>
    </tr>
    <tr>
      <td style="vertical-align: top;" class="idx">配送明细：</td>
      <td colspan="3">
      <table style="width: 100%">
        <tr>
          <td class="lstidx">No.</td>
          <td class="lstidx">配送项目</td>
          <td class="lstidx">配送数量</td>
          <td class="lstidx">配送方式</td>
          <td class="lstidx">单位</td>
        </tr>
        <s:iterator value="expressList" status="rowcounter">
          <tr>
            <td class="cdata">
              <s:property value="#rowcounter.count" />
              <s:hidden name="expressList(%{itemId}).expressItem"
                        value="%{expressItem}">
              </s:hidden>&nbsp;
            </td>
            <td class="data">
              <s:textfield name="expressList(%{itemId}).note"
                           value="%{note}"
                           size="40" >
              </s:textfield>&nbsp;
            </td>
            <td class="cdata">
              <s:textfield name="expressList(%{itemId}).expressNum"
                           value="%{expressNum}"
                           size="3">
              </s:textfield>&nbsp;
            </td>
            <td class="cdata">
              <s:radio list="expressTypeList"
                       name="expressList(%{itemId}).expressType"
                       value="%{expressType}"
                       listKey="value"
                       listValue="label">
              </s:radio>&nbsp;
            </td>
            <td class="cdata">
              <s:textfield name="expressList(%{itemId}).unit"
                           value="%{unit}"
                           size="6"
                           maxLength="6">
              </s:textfield>&nbsp;
            </td>
          </tr>
        </s:iterator>
      </table>
      </td>
    </tr>
    <tr>
      <td class="idx">备注：</td>
      <td colspan="3"><s:textarea name="express.memo" cols="100" rows="5"></s:textarea></td>
    </tr>
    <tr>
      <td colspan="4" align="center">
        <s:submit value="保存" onclick='javascript:document.saveExpress.note.value=window.prompt("备注","请认真填写修改原因");'></s:submit>
        <input type="button" value="返回" onClick="javascript:history.go(-1);">
      </td>
    </tr>
  </table>
</s:form>
</body>
</html>

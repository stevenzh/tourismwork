<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>团款结算确认单</title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
</head>

<body>

<table border="0" style="width: 100%">
  <tr>
    <td class="header">[订单管理]-团款结算确认单</td>
  </tr>
</table>

<s:form action="tourBalanceConfirm" namespace="/sales" method="post" theme="simple">
<br>
<br>
  <table style="width: 80%">
    <caption><b>团款结算确认单</b><br><br></caption>
    <tr>
      <td>致：</td>
      <td></td>
      <td align="center">日期：</td>
    </tr>
    <tr>
      <td colspan="3">&nbsp;&nbsp;&nbsp;贵社委托我公司接待的由上海出境（入境）的旅游团队，现已开始操作。该团队信息及应收团款如下：</td>
    </tr>
    <tr>
      <td colspan="3">
        <table border="1" style="width: 100%">
          <tr>
            <td>出团日期</td>
            <td>&nbsp;<s:property value=""/></td>
            <td>旅游团队名称(团号)</td>
            <td>&nbsp;<s:property value=""/></td>
          </tr>
          <tr>
            <td>参团人数</td>
            <td>&nbsp;<s:property value=""/> 人 </td>
            <td>旅游线路</td>
            <td>&nbsp;<s:property value=""/></td>
          </tr>
          <tr height="120px">
            <td>团费核算</td>
            <td colspan="3">
              团费： <s:property value=""/>*<s:property value=""/>=&nbsp;<s:property value=""/>元
            </td>
          </tr>
          <tr>
            <td>团费总额</td>
            <td colspan="3">人名币：&nbsp;<s:property value=""/></td>
          </tr>
          <tr>
            <td>已付金额</td>
            <td>&nbsp;<s:property value=""/>元</td>
            <td>尚欠金额</td>
            <td>&nbsp;<s:property value=""/>元</td>
          </tr>
          <tr>
            <td colspan="4">特别说明：团款100%必须在团队出发前一天全部付清，否则不予以操作。谢谢配合！</td>
          </tr>
          <tr>
            <td colspan="4">
              <table>
                <tr>
                  <td width="150">重要提示：预期出签日:</td>
                  <td width="80"></td>
                  <td>如遇拒签损失费：80/人</td>
                </tr>
                <tr>
                  <td></td>
                  <td></td>
                  <td>机位定金损失费：</td>
                </tr>
              </table>
          </tr>
          <tr>
            <td>备注：</td>
            <td colspan="3"><s:textarea name="note" rows="5" cols="100"></s:textarea>  </td>
          </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td colspan="3">
      &nbsp;&nbsp;&nbsp;本团款结算确认单请务必出发前2日给予签名及加盖财务专用章确认并回传；若未回传，我公司有权终止并取消团队操作。若贵社为现结团款的客户单位，请务必于出团前一天
      中午12点前将银行汇出底单传真至我公司并给予电话确认，若我公司在出团钱一天中午12点前未收到贵社汇款底单的，我公司有权终止并取消团队操作。贵我双方一旦发生争执
      可通过友好协商解决。如果协商不成的，双方可将争议提交中国上海浦东新区人民法院判决。
      </td>
    </tr>
    <tr>
      <td>&nbsp;&nbsp;&nbsp;附我公司指定账号： </td>
      <td>XXXX旅行社</td>
      <td></td>
    </tr>
    <tr>
      <td></td>
      <td>XXXX银行XXXX支行 #########-##########</td>
      <td></td>
    </tr>
    <tr>
      <td colspan="3">
        <table>
          <tr>
            <td width="100">贵社确认人签名：</td>
            <td width="150"></td>
            <td width="100">贵社确认日期：</td>
            <td></td>
          </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td><br><br></td>
      <td></td>
      <td>
        <img src="<s:url value='/images/manage/sale/finance.gif'/>" name="finance"  border="0" id="finance" />
      </td>
    </tr>
    <tr>
      <td colspan="3">贵社财务确认章：</td>
    </tr>
    <tr>
      <td colspan="3">回传号码：021-61819886</td>
    </tr>
    <tr>
    <td align="center">
      <s:submit action="ReckoningModify" value="修改帐单"></s:submit> 
      <input type="button" onClick="javascript:toPrint()" value="打印帐单"> 
      <input type="button" onClick="javascript:history.go(-1);" value="返回">
    </td>
    </tr>
  </table>
</s:form>
</body>
</html>

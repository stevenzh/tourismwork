<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>线路报价</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="线路报价">
</head>

<body>
<script type="text/javascript">
<!--//
function OperateForm(param, target)
{
  var fm = document.RoutePrice;
  fm.recNo.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
     fm.note.value=window.prompt("备注","请认真填写删除原因");
     fm.action = "<s:url action='deleteRoutePrice' namespace='/product'/>"
  }
  if(param=='modify')
  {
     fm.action = "<s:url action='editRoutePrice' namespace='/product'/>"  
  }
   fm.submit();
}
//-->
</script>
<s:form action="RoutePrice" namespace="/product" method="POST" theme="simple">
  <s:hidden name="recNo" />
  <s:hidden name="note"></s:hidden>
  <table>
    <tr>
      <td class="idx">日期范围：&nbsp;<sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenStartDate"/>&nbsp;至&nbsp;
      <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenEndDate"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <s:submit action="RoutePrice" value="%{getText('common.forms.search')}" /></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <s:if test="priceList.size>0">
      <tr>
        <td>
        <table cellspacing="2">
          <tr>
            <td class="lstidx">NO.</td>
            <!-- 
            <td class="lstidx">航空公司</td>
            <td class="lstidx">航班号</td>
             -->
            <td class="lstidx">描述</td>
            <td class="lstidx">同行价</td>
            <td class="lstidx">直客价</td>
            <td class="lstidx">成本价</td>
            <td class="lstidx">单人房差</td>
            <td class="lstidx">有效期</td>
            <td class="lstidx">操作</td>
          </tr>
          <s:iterator value="priceList" status="rowcounter">
            <tr>
              <td class="cdata"><s:property value="#rowcounter.count" /></td>
              <!-- 
              <td class="cdata"><s:property value="airName" /></td>
              <td class="cdata"><s:property value="flightNo" /></td>
               -->
              <td class="cdata"><s:property value="subject" /></td>
              <td class="cdata"><s:property value="priceOther" /></td>
              <td class="cdata"><s:property value="price" /></td>
              <td class="cdata"><s:property value="priceCost" /></td>
              <td class="cdata"><s:property value="priceContrast" /></td>
              <td class="cdata"><s:date name="startDate" format="yyyy-MM-dd"/>至<s:date name="endDate" format="yyyy-MM-dd"/></td>
              <td class="cdata"><a href="#" onClick="javascript:OperateForm('modify','<s:property value="recNo" />');">修改</a>&nbsp;<a href="#" onClick="javascript:OperateForm('delete','<s:property value="recNo" />');">删除</a></td>
            </tr>
          </s:iterator>
        </table>
        </td>
      </tr>
    </s:if>
    <s:else>
      <tr>
        <td>没有搜索到满足条件的记录</td>
      </tr>
    </s:else>
    <tr>
      <td><s:submit action="editRoutePrice" value="添加" /></td>
    </tr>
  </table>
</s:form>
</body>
</html>

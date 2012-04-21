<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>线路报价修改</title>
<meta name="menu" content="ProductMenu"/>
</head>

<body>
<script type="text/javascript">
<!--//

function cancel()
{
  var fm=document.submitRoutePrice;
  fm.action="<s:url action='RoutePrice' namespace='/product' includeParams='none'/>"
  fm.submit();
}
function insertOrUpdate()
{
	if(document.submitRoutePrice.recNo.value != null)
		document.submitRoutePrice.note.value=window.prompt("备注","请认真填写修改原因");
}
function selectAll()
{
	var a = document.all.weekKey1.checked=true;
	var b = document.all.weekKey2.checked=true;
	var c= document.all.weekKey3.checked=true;
	var d = document.all.weekKey4.checked=true;
	var e = document.all.weekKey5.checked=true;
	var f = document.all.weekKey6.checked=true;                     
  var g = document.all.weekKey7.checked=true;  
}

function change()
{
  var unitPrice=document.getElementById('unitPrice').value;
  var roe=document.getElementById('roe').value;
	if(isNaN(unitPrice)){
	  alert('输入值只能为数字!');
	}
	if(isNaN(roe)){
	  alert('输入值只能为数字!');
	}
  document.getElementById('price1').value=Math.round(roe*unitPrice*100)/100;
}

function account(){
    var price1=document.getElementById('price1').value;
    var price2=document.getElementById('price2').value;
    var price3=document.getElementById('price3').value;
    var price4=document.getElementById('price4').value;
    var price5=document.getElementById('price5').value;
    var price6=document.getElementById('price6').value;
    var price7=document.getElementById('price7').value;
    var price8=document.getElementById('price8').value;
    var price9=document.getElementById('price9').value;
    var price10=document.getElementById('price10').value;
    var price11=document.getElementById('price11').value;
    var price12=document.getElementById('price12').value;
    var price13=document.getElementById('price13').value;
    var price14=document.getElementById('price14').value;

document.getElementById('all').value=Math.round((eval(price1)+eval(price2)+eval(price3)+eval(price4)
+eval(price5)+eval(price6)+eval(price7)+eval(price8)+eval(price9)+eval(price10)+eval(price11)
+eval(price12)+eval(price13)+eval(price14))*100)/100;
}

//-->
</script>

<table style="width: 100%">
  <tr>
    <td class="header">
      <s:if test="routePrice.recNo == null">修改<s:property value="line.lineName" />报价</s:if>
      <s:else>添加<s:property value="line.lineName" />报价</s:else>
    </td>
  </tr>
</table>

<s:form action="submitRoutePrice" namespace="/product" method="POST" theme="simple">
  <s:hidden name="routePrice.recNo"></s:hidden>
  <s:hidden name="kenEndDate"></s:hidden>
  <s:hidden name="kenStartDate"></s:hidden>
  <s:hidden name="note"></s:hidden>
  <s:hidden name="recNo"></s:hidden>

  <table>
    <tr>
      <td class="lstidx">标准描述</td>
      <td>
        <sj:autocompleter name="routePrice.type"
				                  list="priceTypeList"
				                  listKey="value"
				                  listValue="label"
				                  emptyOption="true"
				                  required="true"
				                  requiredposition="right">
        </sj:autocompleter>&nbsp;<font color="#ff0000">*</font>
      </td>
    </tr>
    <tr>
      <td class="lstidx">起始日期</td>
      <td>
        <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="routePrice.startDate">
        </sj:datepicker>&nbsp;<font color="#ff0000">*</font>
      </td>
    </tr>
    <tr>
      <td class="lstidx">结束日期</td>
      <td>
        <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="routePrice.endDate">
        </sj:datepicker>&nbsp;<font color="#ff0000">*</font>
      </td>
    </tr>
    <tr>
      <td class="lstidx">周期表</td>
      <td><table border="0">
        <tr>
          <td><s:checkbox name="weekKey1" requiredposition="right"></s:checkbox>星期一</td>
          <td><s:checkbox name="weekKey2" requiredposition="right"></s:checkbox>星期二</td>
          <td><s:checkbox name="weekKey3" requiredposition="right"></s:checkbox>星期三</td>
          <td><s:checkbox name="weekKey4" requiredposition="right"></s:checkbox>星期四</td>
          <td><s:checkbox name="weekKey5" requiredposition="right"></s:checkbox>星期五</td>
          <td><s:checkbox name="weekKey6" requiredposition="right"></s:checkbox>星期六</td>
          <td><s:checkbox name="weekKey7" requiredposition="right"></s:checkbox>星期日</td>
        </tr>
      </table></td>
    </tr>

    <tr>
      <td class="header" colspan="2">报价</td>
    </tr>
    <tr>
      <td colspan="2">
      <table border="0">
        <tr>
          <td class="lstidx">同行价</td>
          <td class="cdata">
            <s:textfield name="routePrice.priceOther"
                         required="true"
                         requiredposition="right" />&nbsp;<font color="#ff0000">*</font>
          </td>
          <td class="lstidx">直客价</td>
          <td class="cdata">
            <s:textfield name="routePrice.price"
                         required="true"
                         requiredposition="right" />&nbsp;<font color="#ff0000">*</font>
          </td>
        </tr>
        <tr>
          <td class="lstidx">总成本价</td>
          <td class="cdata">
            <s:textfield id="all"
                         name="routePrice.priceCost"
                         requiredposition="right" />&nbsp;<font color="#ff0000">*</font>
          </td>
          <td class="lstidx">单人房差</td>
          <td class="cdata">
            <s:textfield name="routePrice.priceContrast"
                         requiredposition="right" />&nbsp;<font color="#ff0000">*</font>
          </td>
        </tr>
        <tr>
          <td class="lstidx">订金</td>
          <td class="cdata">
            <s:textfield name="routePrice.subScription"
                         requiredposition="right" />&nbsp;<font color="#ff0000">*</font>
          </td>
        </tr>
      </table>
      </td>
    </tr>
    <tr>
      <td class="header" colspan="2">成本价细分(参考)</td>
    </tr>
    <tr>
      <td colspan="2">
      <table border="0">
        <tr>
          <td class="idx">地接币种</td>
          <td>
            <s:select name="routePrice.currency"
                      list="currencyList"
                      listKey="value"
                      listValue="value" />&nbsp;<font color="red">*</font>
          </td>
          <td class="idx">地接汇率</td>
          <TD>
            <s:textfield id="roe"
                         name="routePrice.roe"
                         required="true"
                         requiredposition="right"
                         onchange="javascript:change();" />&nbsp;<font color="red">*</font>
          </td>
        </tr>
        <tr>
          <td class="idx">地接单价(外币):</td>
          <TD>
            <s:textfield id="unitPrice"
                         name="routePrice.unitPrice"
                         required="true"
                         requiredposition="right"
                         onchange="javascript:change();" />&nbsp;<font color="red">*</font>
          </td>
          <td class="idx">地接费(人民币):</td>
          <td>
            <s:textfield id="price1"
                         name="routePrice.cost4"
                         required="true"
                         requiredposition="right"
                         onchange="javascript:account();" />&nbsp;<font color="#ff0000">*</font>
          </td>
        </tr>
        <tr>
          <td class="idx">机票费</td>
          <td>
            <s:textfield id="price2"
                         name="routePrice.cost1"
                         required="true"
                         requiredposition="right"
                         onchange="javascript:account();" />&nbsp;<font color="#ff0000">*</font>
          </td>
          <td class="idx">机票税:</td>
          <td>
            <s:textfield id="price3"
                         name="routePrice.cost3"
                         requiredposition="right"
                         onchange="javascript:account();" />&nbsp;<font color="#ff0000">*</font>
          </td>
        </tr>
        <tr>
          <td class="idx">签证费</td>
          <td>
            <s:textfield id="price4"
                         name="routePrice.cost5"
                         required="true"
                         requiredposition="right"
                         onchange="javascript:account();" />&nbsp;<font color="#ff0000">*</font>
          </td>
          <td class="idx">名单费</td>
          <td>
            <s:textfield id="price5"
                         name="routePrice.cost6"
                         requiredposition="right"
                         onchange="javascript:account();" />&nbsp;<font color="#ff0000">*</font>
          </td>
        </tr>

        <tr>
          <td class="idx">领队返佣</td>
          <td>
            <s:textfield id="price6"
                         name="routePrice.backMoney"
                         required="true"
                         requiredposition="right"
                         onchange="javascript:account();" />&nbsp;<font color="#ff0000">*</font>
          </td>
          <td class="idx">领队分摊</td>
          <td>
            <s:textfield id="price7"
                         name="routePrice.cost7"
                         required="true"
                         requiredposition="right"
                         onchange="javascript:account();" />&nbsp;<font color="#ff0000">*</font>
          </td>
        </tr>
        <tr>
          <td class="idx">离境税</td>
          <td>
            <s:textfield id="price8"
                         name="routePrice.afieldDuty"
                         requiredposition="right"
                         onchange="javascript:account();" />&nbsp;<font color="#ff0000">*</font>
          </td>
          <td class="idx">其他</td>
          <td>
            <s:textfield id="price9"
                         name="routePrice.cost8"
                         requiredposition="right"
                         onchange="javascript:account();" />&nbsp;&nbsp;
          </td>
        </tr>
        <tr>
          <td class="idx">景点门票</td>
          <td>
            <s:textfield id="price10"
                         name="routePrice.cost12"
                         required="true"
                         requiredposition="right"
                         onchange="javascript:account();" />&nbsp;
          </td>
          <td class="idx">国内段</td>
          <td>
            <s:textfield id="price11"
                         name="routePrice.cost2"
                         required="true" requiredposition="right"
                         onchange="javascript:account();" />&nbsp;&nbsp;
          </td>
        </tr>
        <tr>
          <td class="idx">住宿费（每日）</td>
          <td>
            <s:textfield id="price12"
                         name="routePrice.cost10"
                         required="true"
                         requiredposition="right"
                         onchange="javascript:account();" />&nbsp;
          </td>
          <td class="idx">餐费（每日）</td>
          <td>
            <s:textfield id="price13"
                         name="routePrice.cost9"
                         requiredposition="right"
                         onchange="javascript:account();" />&nbsp;
          </td>
        </tr>
        <tr>
          <td class="idx">小交通(境内交通)</td>
          <td>
            <s:textfield id="price14"
                         name="routePrice.cost11"
                         requiredposition="right"
                         onchange="javascript:account();" />&nbsp;
          </td>
        </tr>
      </table>
      </td>
    </tr>
    <tr>
      <td>
        <s:submit value="提交" theme="simple"
                  onclick="javascript:insertOrUpdate();" />&nbsp;&nbsp;
        <input value="返回" type="button" onclick="javascript:cancel();">
      </td>
    </tr>
  </table>
</s:form>

</body>
</html>

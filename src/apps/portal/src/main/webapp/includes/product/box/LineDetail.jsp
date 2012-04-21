<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<script language="javascript">
<!--//

function order(param)
{
  var fm = document.OrderAgreement;
  fm.planNo.value = param;
  fm.submit();
}

//-->
</script>

<body>
<h2><s:property value="line.lineName"/></h2>
<div><s:property value="route.description"/></div>

<s:if test="features.size() > 0">
<div class="middle_box">
	<div class="middle_box_top">
	  <h2>推荐特色</h2>
	</div>
  <table style="width: 100%" border="0">
    <s:iterator value="features" status="rowcounter">
      <tr>
        <td width="6%" valign="top">
        <div align="right"><s:property value="#rowcounter.count"/>、</div>
        </td>
        <td width="94%" valign="top"><s:property value="traitDetail"/></td>
      </tr>
    </s:iterator>
  </table>
</div>
</s:if>

<s:if test="planList.size() > 0">
<div class="middle_box">
  <div class="middle_box_top">
    <h2>出团计划</h2>
  </div>
  <table style="width: 100%" border="0">
    <tr>
      <td>
      <s:form action="OrderAgreement" namespace="/accounts" method="GET">
      <s:hidden name="planNo"></s:hidden>
      <table style="width: 100%" border="0" cellspacing="1">
        <tr>
          <td width="14%" align="center">开班日期</td>
          <td width="39%" align="center">团 号</td>
          <td width="14%" align="center">报 价</td>
          <td width="16%" align="center">名 额</td>
          <td align="center">操作</td>
        </tr>
        <s:iterator value="planList">
          <tr>
            <td align="center"><s:date name="outDate" format="yyyy-MM-dd"/></td>
            <td align="center"><s:property value="tourNo"/></td>
            <td align="right"><s:property value="packagePrice.price"/>元&nbsp;</td>
            <td align="center"><s:property value="pax3"/>&nbsp;</td>
            <td align="center">&nbsp;
            <s:if test='enter == false'>过期或名额已满 </s:if>
            <s:else>
            <a href="#" onclick="javascript:order('<s:property value="recordNo"/>')">预订</a>
            </s:else>
            </td>
          </tr>
        </s:iterator>
      </table>
      </s:form>
      </td>
    </tr>
  </table>
</div>
</s:if>

<div class="middle_box">
  <div class="middle_box_top">
    <h2>行程</h2>
  </div>
	<table style="width: 100%" border="0" cellspacing="3">
	  <s:iterator value="schedule">
	    <tr>
	      <td style="width: 40px">
	      <div align="center">第<s:property value="day"/>天</div>
	      </td>
	      <td><s:property value="traffic"/></td>
	    </tr>
	    <tr>
	      <td>&nbsp;</td>
	      <td><s:property value="program" /></td>
	    </tr>
	    <tr>
	      <td>住宿：</td>
	      <td><s:property value="quarter" /></td>
	    </tr>
	  </s:iterator>
	</table>
</div>

<s:if test="visaList.size() > 0">
<div class="middle_box">
  <div class="middle_box_top">
    <h2>所需办理签证</h2>
  </div>
  <table style="width: 100%" border="0" cellspacing="3">
    <s:iterator value="visaList">
      <tr>
        <td width="6%"><s:property value="countryName"/></td>
        <td width="94%"><s:property value="Description" escape="false"/></td>
      </tr>
    </s:iterator>
  </table>
</div>
</s:if>

<div class="middle_box">
  <div class="middle_box_top">
    <h2>费用包含</h2>
  </div>
  <table style="width: 100%" border="0">
    <s:iterator value="expenseCovered" status="rowcounter">
      <tr>
        <td width="6%" align="center"><s:property value ="#rowcounter.count" />、</td>
        <td width="94%"><s:property value="value"/></td>
      </tr>
    </s:iterator>

    <!-- 费用中已包含自费项目 -->
    <tr>
      <td align="center"><s:property value="expenseCovered.size() + 1" />、</td>
      <td><s:property value="route.zkOwnExpense"/></td>
    </tr>
  </table>
</div>

<s:if test="expenseExcept.size() > 0">
<div class="middle_box">
  <div class="middle_box_top">
    <h2>费用不包含</h2>
  </div>
  <table style="width: 100%" border="0">
    <s:iterator value="expenseExcept" status="rowcounter">
      <tr>
        <td width="6%" align="center"><s:property value ="#rowcounter.count" />、</td>
        <td width="94%"><s:property value="value"/></td>
      </tr>
    </s:iterator>
  </table>
</div>
</s:if>

<div class="middle_box">
  <div class="middle_box_top">
    <h2>出行警示</h2>
  </div>
  <table style="width: 100%" border="0">
    <s:iterator value="alerts" status="rowcounter">
    <tr>
      <td width="6%" valign="top">
      <div align="center"><s:property value="label"/>、</div>
      </td>
      <td width="94%" valign="top"><s:property value="value"/></td>
    </tr>
    </s:iterator>
  </table>
</div>


<div class="middle_box">
  <div class="middle_box_top">
    <h2>重要条款</h2>
  </div>
  <table style="width: 100%" border="0">
    <s:iterator value="rules" status="rowcounter">
    <tr>
      <td width="6%" valign="top">
      <div align="center"><s:property value="label"/>、</div>
      </td>
      <td width="94%" valign="top"><s:property value="value"/></td>
    </tr>
    </s:iterator>
  </table>
</div>

<div class="middle_box">
  <div class="middle_box_top">
    <h2>小贴士</h2>
  </div>
  <table style="width: 100%" border="0">
    <s:iterator value="routeTipsList" status="rowcounter">
      <tr>
        <td width="6%" align="center" valign="top"><s:property value ="#rowcounter.count" />、</td>
        <td width="30" valign="top"><s:property value="%{item}"/></td>
        <td width="64%" valign="top"><s:property value="%{description}"/></td>
        <td></td>
      </tr>
    </s:iterator>
  </table>
</div>

<!-- 
<div class="middle_box">
  <div class="middle_box_top">
    <h2>报价说明</h2>
  </div>
  <table style="width: 100%" border="0" cellspacing="1">
    <tr>
      <td width="5%" align="center">No.</td>
      <td align="center">摘 要</td>
      <td width="17%" align="center">报 价</td>
      <td width="17%" align="center">单人房差</td>
      <td width="29%" align="center">有 效 期</td>
    </tr>
    <s:iterator value="quotations" status="rowcounter">
      <tr>
        <td align="center"><s:property value ="#rowcounter.count" /></td>
        <td align="center"><s:property value="type"/></td>
        <td align="right"><s:property value="price"/>元&nbsp;</td>
        <td align="right"><s:property value="priceContrast"/>元&nbsp;</td>
        <td align="center"><s:date name="startDate" format="yyyy-MM-dd"/>&nbsp;--&nbsp;<s:date name="endDate" format="yyyy-MM-dd"/></td>
      </tr>
     </s:iterator>
  </table>
</div>
 -->

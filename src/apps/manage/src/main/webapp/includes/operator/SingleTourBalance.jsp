<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="indexHeader">需要确认的单团核算</div>
<table border="0" cellpadding="2" cellspacing="2" width="100%">
  <tr>
    <td class="lstidx">NO.</td>
    <td class="lstidx" nowrap="nowrap">团号</td>
    <td class="lstidx">线路</td>
    <td class="lstidx">出发日期</td>
    <td class="lstidx">人数</td>
    <td class="lstidx">总收入</td>
    <td class="lstidx">纯团费</td>
    <td class="lstidx">已收款</td>
    <td class="lstidx">成本</td>
    <td class="lstidx">毛利</td>
    <td class="lstidx">毛利率</td>
    <td class="lstidx">操作</td>
  </tr>

  <s:iterator value="tours" status="rowcounter">
  <s:if test="#rowcounter.count > fromRecord">
  <s:if test="#rowcounter.count <= toRecord">
  <tr>
    <td class="cdata"><s:property value="#rowcounter.count"/></td>
    <td class="data"><a href="#" title="点击查看团信息" onclick="javascript:detail('<s:property value='tourNo'/>')"><s:property value="tourNo" /></a></td>
    <td class="data"><s:property value="line.lineName"/><s:hidden name="line.lineName"/></td>
    <td class="cdata"><s:date name="outDate" format="yyyy-MM-dd"/></td>
    <td class="rdata"><s:property value="pax-leaderPax"/>+<s:property value="leaderPax"/></td>
    <td class="rdata"><s:property value="amount"/></td>
    <td class="rdata"><s:property value="tourAmount"/></td>
    <td class="rdata"><s:property value="alAmount"/></td>
    <td class="rdata"><s:property value="cost"/></td>
    <td class="rdata"><s:property value="grossAmount"/></td>
    <td class="rdata"><s:property value="grossAmountRate"/>%</td>
    <td class="cdata">
     <s:if test="frChecked eq 'N'">
     <a href="#" title="修改单团核算" onclick="javascript:modify('<s:property value="tourNo"/>');">修改</a>
      <a href="#" title="审核单团核算" onclick="javascript:auditing('<s:property value="tourNo"/>');">审核</a>
     </s:if>
     <s:else>
      <a href="#" title="查看单团核算" onclick="javascript:auditing('<s:property value="tourNo"/>');">已审核</a>
     </s:else>
    <a href="#" title="单团收款销帐" onclick="javascript:get('<s:property value="tourNo"/>');">收款</a>
     <a href="#" title="担保" onclick="javascript:warrant('<s:property value="tourNo"/>');">担保</a>
     <a href="#" title="打印单团核算表" onclick="javascript:printBalance('<s:property value="tourNo" />');">打印</a>
      </td>
  </tr>
  
  </s:if>
  </s:if>
  </s:iterator>
  <s:if test="tours.isEmpty() == false">
  <tr bgcolor="#b9c0ff">
    <td align="center" colspan="4"><b>合计</b></td>
    <td align="right"><b><s:property value="allPax-allLeaderPax"/>+<s:property value="allLeaderPax"/></b></td>
    <td align="right"><b><s:property value="allAmount"/></b></td>
    <td align="right"><b><s:property value="allTourAmount"/></b></td>
    <td align="right"><b><s:property value="allAlAmount"/></b></td>
    <td align="right"><b><s:property value="allCostAmount"/></b></td>
    <td align="right"><b><s:property value="allGrossAmount"/></b></td>
    <td align="right"><b><s:property value="allGrossAmountRate"/>%</b></td>
    <td align="right">&nbsp;</td>
  </tr>
    </s:if>
     <tr>
  <td colspan="14" align="right"><a href="<s:url action='SingleTourBalanceInput' namespace='/finance' includeParams='none' />">更多</a></td>
  </tr>
</table>



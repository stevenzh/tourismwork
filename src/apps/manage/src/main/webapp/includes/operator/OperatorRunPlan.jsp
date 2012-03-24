<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="authz"	uri="http://www.springframework.org/security/tags"%>

<table border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td class="lstidx">NO.</td>
		<td class="lstidx">&nbsp;</td>
		<td class="lstidx">团号</td>
		<td class="lstidx">线路名</td>
		<td class="lstidx">属性</td>
		<!-- 
    <td class="lstidx">工作组</td>
     -->
		<td class="lstidx">出发日期</td>
		<td class="lstidx">直客价</td>
		<td class="lstidx">同业价</td>
		<td class="lstidx">单人房差</td>
		<td class="lstidx">可收</td>
		<td class="lstidx">已收</td>
		<td class="lstidx">剩余</td>
		<td class="lstidx">占位</td>
		<!-- 
    <td class="lstidx">最小成团<br>人数</td>
     -->
		<td class="lstidx">报名截止</td>
		<!-- 
    <td class="lstidx">出发地</td>
    <td class="lstidx">创建时间</td>
    <td class="lstidx">创建人</td>
     -->
		<authz:authorize ifNotGranted="ROLE_PRODUCT">
			<td class="lstidx">操作</td>
		</authz:authorize>
	</tr>

	<s:iterator value="planList" status="rowcounter">
		<s:if test="#rowcounter.count < 11">
			<tr>
				<td class="cdata"><s:property value="#rowcounter.count" /></td>
				<td class="cdata"><s:if test='isBuildup eq "Y"'>
					<font color="green">★</font>
				</s:if></td>
				<td class="data"><a
					href="javascript:SubmitForm('change','<s:property value='recordNo'/>');"><s:property
					value="tourNo" /></a></td>
				<td class="data"><!-- <s:property value="line.lineName"/> -->
				<a href="#"
					onclick="javascript:showRouteJourney('<s:property value='line.lineNo'/>')"><s:property
					value="line.lineName" /></a></td>
				<!-- 
			    <td class="cdata"><s:property value="route.departmentName"/></td>
			     -->
				<td class="cdata"><s:property value="singleShow" /></td>
				<td class="cdata"><s:date name="outDate" format="yyyy-MM-dd[E]" /></td>
				<td class="rdata"><s:text name="format.money">
					<s:param value="packagePrice.price" />
				</s:text>&nbsp;</td>
				<td class="rdata"><s:text name="format.money">
					<s:param value="packagePrice.priceOther" />
				</s:text>&nbsp;</td>
				<td class="rdata"><s:text name="format.money">
					<s:param value="packagePrice.priceCost" />
				</s:text>&nbsp;</td>

				<td class="rdata"><s:property value="pax1" />&nbsp;</td>
				<td class="rdata"><s:property value="pax2" />&nbsp;</td>
				<td class="rdata"><s:property value="pax3" />&nbsp;</td>
				<td class="rdata"><s:property value="pax4" />&nbsp;</td>
				<!-- 
        <td class="rdata"><s:property value="pax5"/>&nbsp;</td>
         -->
				<td class="rdata"><s:date name="deadline" format="yyyy-MM-dd" /></td>
				<!-- 
        <td class="cdata"><s:property value="route.outCityName"/></td>
        <td class="rdata"><s:date name="dateCreated" format="yyyy-MM-dd"/></td>
        <td class="cdata"><s:property value="creator"/></td>
        -->
				<authz:authorize ifNotGranted="ROLE_PRODUCT">
					<td><s:if test="pax2 > 0">
						<a href="javascript:SubmitForm('booking','<s:property value='recordNo'/>');">订单</a>
					</s:if></td>
				</authz:authorize>
			</tr>
		</s:if>
	</s:iterator>
	<tr>
		<td colspan="14" align="right"><a
			href="<s:url action='TourPlanSearch' namespace='/operator' includeParams='none' />">更多</a></td>
	</tr>
</table>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<script language="javascript" type="text/javascript">
<!--//

/** 安排配送 */
function toExpress(obj)
{
  var fm=document.MyPage;
  fm.expressId.value = obj;
  fm.action="<s:url action='showAssignExpress' namespace='/express' includeParams='none'/>";
  fm.submit();
}

/** 审核配送单(签单部) */
function toAuditing(obj)
{
  var fm=document.MyPage;
  fm.expressId.value=obj;
  fm.action="<s:url action='showExpressDetail' namespace='/express' includeParams='none'/>";
  fm.submit();
}

//-->
</script>
<s:form action="assignExpress" namespace="/express" method="post" theme="simple">
  <s:hidden name="express.expressId" value="%{expressId}"></s:hidden>
  <s:hidden name="expressId" />

  <sj:tabbedpanel id="tabExpress">
  <authz:authorize ifAnyGranted="ROLE_TRANSPORT">
    <sj:tab target="wex" label="等待配送的订单"></sj:tab>
    <table id="wex" border="0" cellpadding="2" cellspacing="2" width="100%">
      <tr bgcolor="#b9c0ff">
        <td class="lstidx">NO.</td>
        <td class="lstidx">配送单号</td>
        <td class="lstidx">订单号</td>
        <td class="lstidx">团号</td>
        <td class="lstidx">线路</td>
        <td class="lstidx">客户</td>
        <td class="lstidx">联系人</td>
        <td class="lstidx">配送时间</td>
        <td class="lstidx">最后期限</td>
        <td class="lstidx">应收团款</td>
      </tr>
      <s:iterator value="arrangeExpressList" status="rowcounter">
        <tr>
          <td class="cdata"><s:property value="#rowcounter.count" /></td>
          <td align="center"><A href="#" onclick="javascript:toExpress('<s:property value='expressId'/>')"><s:property value='expressId' /></a>&nbsp;</td>
          <td align="center"><s:property value='orderId' />&nbsp;</td>
          <td align="left"><s:property value="teamNo" />&nbsp;</td>
          <td align="center"><s:property value="line" />&nbsp;</td>
          <td align="center"><s:property value="cusomer" />&nbsp;</td>
          <td align="center"><s:property value="contactor" />&nbsp;</td>
          <td align="center"><s:date name="expressDate" format="yyyy-MM-dd" />&nbsp;</td>
          <td align="center"><s:date name="expressLastDate" format="yyyy-MM-dd" />&nbsp;</td>
          <td align="right"><s:property value="pay" />&nbsp;</td>
        </tr>
      </s:iterator>
      <s:if test="arrangeExpressList.size > 10">
        <tr>
          <td colspan="8" align="right">更多</td>
        </tr>
      </s:if>
    </table>
  </authz:authorize>

  <authz:authorize ifAnyGranted="ROLE_CALLCENTER_SUPPORT">
		<sj:tab target="wse" label="等待审核的配送单"></sj:tab>
		<sj:tab target="wje" label="等待缴款的配送单"></sj:tab>
		<sj:tab target="wge" label="是否需要再次配送"></sj:tab>
		
    <table id="wse" border="0" cellpadding="2" cellspacing="2" width="100%">
      <tr bgcolor="#b9c0ff">
        <td class="lstidx">NO.</td>
        <td class="lstidx">配送单号</td>
        <td class="lstidx">订单号</td>
        <td class="lstidx">团号</td>
        <td class="lstidx">线路</td>
        <td class="lstidx">客户</td>
        <td class="lstidx">联系人</td>
        <td class="lstidx">配送时间</td>
        <td class="lstidx">应收团款</td>
      </tr>
      <s:iterator value="checkExpressList" status="rowcounter">
        <s:if test="#rowcounter.count < 11">
          <tr>
            <td class="cdata"><s:property value="#rowcounter.count" /></td>
            <td align="center"><A href="#" onclick="javascript:toAuditing('<s:property value='expressId'/>')"><s:property value="expressId" /></a>&nbsp;</td>
            <td align="center"><s:property value='orderId' />&nbsp;</td>
            <td align="center"><s:property value="teamNo" />&nbsp;</td>
            <td align="center"><s:property value="line" />&nbsp;</td>
            <td align="center"><s:property value="cusomer" />&nbsp;</td>
            <td align="center"><s:property value="contactor" />&nbsp;</td>
            <td align="center"><s:date name="expressDate" format="yyyy-MM-dd" />&nbsp;</td>
            <td align="right"><s:property value="pay" />&nbsp;</td>
          </tr>
        </s:if>
      </s:iterator>
      <s:if test="checkExpressList.size > 10">
        <tr>
          <td colspan="9" align="right">更多</td>
        </tr>
      </s:if>
    </table>

    <table id="wje" border="0" cellpadding="2" cellspacing="2" width="100%">
      <tr bgcolor="#b9c0ff">
        <td class="lstidx">NO.</td>
        <td class="lstidx">配送单号</td>
        <td class="lstidx">订单号</td>
        <td class="lstidx">团号</td>
        <td class="lstidx">线路</td>
        <td class="lstidx">客户</td>
        <td class="lstidx">联系人</td>
        <td class="lstidx">配送时间</td>
        <td class="lstidx">应收团款</td>
      </tr>
      <s:iterator value="accountExpressList" status="rowcounter">
        <s:if test="#rowcounter.count < 11">
          <tr>
            <td class="cdata"><s:property value="#rowcounter.count" /></td>
            <td align="center"><A href="#" onclick="javascript:toAuditing('<s:property value='expressId'/>')"><s:property value="expressId" /></a>&nbsp;</td>
            <td align="center"><s:property value='orderId' />&nbsp;</td>
            <td align="center"><s:property value="teamNo" />&nbsp;</td>
            <td align="center"><s:property value="line" />&nbsp;</td>
            <td align="center"><s:property value="cusomer" />&nbsp;</td>
            <td align="center"><s:property value="contactor" />&nbsp;</td>
            <td align="center"><s:date name="expressDate" format="yyyy-MM-dd" />&nbsp;</td>
            <td align="right"><s:property value="pay" />&nbsp;</td>
          </tr>
        </s:if>
      </s:iterator>
      <s:if test="accountExpressList.size > 10">
        <tr>
          <td colspan="7" align="right">更多</td>
        </tr>
      </s:if>
    </table>

    <table id="wge" border="0" cellpadding="2" cellspacing="2" width="100%">
      <tr bgcolor="#b9c0ff">
        <td class="lstidx">NO.</td>
        <td class="lstidx">配送单号</td>
        <td class="lstidx">订单号</td>
        <td class="lstidx">团号</td>
        <td class="lstidx">联系人</td>
        <td class="lstidx">配送时间</td>
        <td class="lstidx">应收团款</td>
        <td class="lstidx">是否需要<br>再次配送</td>
      </tr>
      <s:iterator value="expressAgainList" status="rowcounter">
        <s:if test="#rowcounter.count < 11">
          <tr>
            <td class="cdata"><s:property value="#rowcounter.count" /></td>
            <td align="center"><s:property value="expressId" />&nbsp;</td>
            <td align="center"><s:property value='orderId' />&nbsp;</td>
            <td align="center"><s:property value="teamNo" />&nbsp;</td>
            <td align="center"><s:property value="contactor" />&nbsp;</td>
            <td align="center"><s:date name="expressDate" format="yyyy-MM-dd" />&nbsp;</td>
            <td align="right"><s:property value="pay" />&nbsp;</td>
            <td align="center"><a href="#" onclick="javascript:toAuditing('<s:property value='expressId'/>',true)">是</a>
            <a href="#" onclick="javascript:IsExpressAgain('<s:property value='expressId'/>',false)">否</a>&nbsp;</td>
          </tr>
        </s:if>
      </s:iterator>
      <s:if test="expressAgainList.size > 10">
        <tr>
          <td colspan="6" align="right">更多</td>
        </tr>
      </s:if>
    </table>
  </authz:authorize>

	  <authz:authorize ifAnyGranted="ROLE_VISA_EXAMINE">
    <sj:tab target="vg" label="等待签证材料审核的配送单"></sj:tab>
      <table id="vg" border="0" cellpadding="2" cellspacing="2" width="100%">
        <tr bgcolor="#b9c0ff">
          <td class="lstidx">NO.</td>
          <td class="lstidx">配送单号</td>
          <td class="lstidx">订单号</td>
          <td class="lstidx">团号</td>
          <td class="lstidx">线路</td>
          <td class="lstidx">客户</td>
          <td class="lstidx">联系人</td>
          <td class="lstidx">配送时间</td>
          <td class="lstidx">应收团款</td>
        </tr>
        <s:iterator value="examineExpressList" status="rowcounter">
          <s:if test="#rowcounter.count < 11">
            <tr>
              <td class="cdata"><s:property value="#rowcounter.count" /></td>
              <td align="center"><A href="#" onclick="javascript:toAuditing('<s:property value='expressId'/>')"><s:property value="expressId" /></a>&nbsp;</td>
              <td align="center"><s:property value='orderId' />&nbsp;</td>
              <td align="center"><s:property value="teamNo" />&nbsp;</td>
              <td align="center"><s:property value="line" />&nbsp;</td>
              <td align="center"><s:property value="cusomer" />&nbsp;</td>
              <td align="center"><s:property value="contactor" />&nbsp;</td>
              <td align="center"><s:date name="expressDate" format="yyyy-MM-dd" />&nbsp;</td>
              <td align="right"><s:property value="pay" />&nbsp;</td>
            </tr>
          </s:if>
        </s:iterator>
        <s:if test="examineExpressList.size > 10">
          <tr>
            <td colspan="7" align="right">更多</td>
          </tr>
        </s:if>
      </table>
	  </authz:authorize>
  </sj:tabbedpanel>
</s:form>


<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>

<script language="JavaScript">
<!--//

function _getlist(type)
{
  var frm = document.processLineSearch;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  frm.movePage.value = type ;
  frm.submit();
}

//-->
</script>
<table style="width: 100%" border="0" align="center">
  <tr>
    <td>
    <s:form action="processLineSearch" namespace="/product" method="POST" theme="simple">
    <s:hidden name="kenOutCity"></s:hidden>
    <s:hidden name="kenDestination"></s:hidden>
    <s:hidden name="kenStartDatePeriod"></s:hidden>
    <s:hidden name="kenEndDatePeriod"></s:hidden>

    <table align="center" border="0" style="width: 100%">
      <tr>
        <td bgcolor="#ffffff" valign="top">
        <table bgcolor="#d1e9d7" border="0" style="width: 100%">
          <tr>
            <td height="45">
            <table border="0" style="width: 100%">
              <tr bgcolor="#d1e9d7">
                <th align="center">NO.</th>
                <th align="center">线路名</th>
                <th align="center">出发日期</th>
                <th align="center">报价</th>
                <th align="center">单人房差</th>
                <th align="center">可订名额</th>
                <th align="center">等候名额</th>
                <th align="center">出发地</th>
              </tr>
              <s:iterator value="plans" status="rowcounter">
                <s:if test="#rowcounter.count > fromRecord">
                <s:if test="#rowcounter.count <= toRecord">
                <tr bgcolor="#ffffff" height="22">
                  <td align="center"><s:property value ="#rowcounter.count" /></td>
                  <td><a href="<s:url action='RouteDetail' namespace='/'/>?routeNo=<s:property value="line.lineNo"/>" target="_bank"><s:property value="line.lineName" /></a></td>
                  <td align="center"><s:date name="outDate" format="MM/dd" /></td>
                  <td align="center"><s:property value="packagePrice.price" /></td>
                  <td align="center"><s:property value="package.priceCost" /></td>
                  <td align="center"><s:property value="pax3" /></td>
                  <td align="center"><s:property value="pax4" /></td>
                  <td align="center"><s:property value="line.outCity.citynm" /></td>
                </tr>
                </s:if>
                </s:if>
              </s:iterator>
            </table>
            </td>
          </tr>
        </table>

        <s:hidden name="countInPage"></s:hidden>
        <s:hidden name="currentPage"></s:hidden>
        <s:hidden name="sortId"></s:hidden>
        <s:hidden name="movePage"></s:hidden>
        <s:if test="plans.isEmpty() == false">
        <div class="PagerTable">
          <ul>
          <li class="doc" style="WIDTH: 420px">第<s:property value="currentPage"/>页 共<s:property value="totalPage"/>页 共<s:property value="totalRecord"/>条记录</li>
          <li class="button"><img class="PBMouseOut" title="首页" onclick="javascript:_getlist('first')" onmouseover="javascript:PBMOver(this);" onmouseout="javascript:PBMOut(this);" src="<s:url value="/images/manage/other/bbFirst.gif" />"></li>
          <li class="button"><img class="PBMouseOut" title="上一页" onclick="javascript:_getlist('prev')" onmouseover="javascript:PBMOver(this);" onmouseout="javascript:PBMOut(this);" src="<s:url value="/images/manage/other/bbPrev.gif" />"></li>
          <li class="button"><img class="PBMouseOut" title="下一页" onclick="javascript:_getlist('next')" onmouseover="javascript:PBMOver(this);" onmouseout="javascript:PBMOut(this);" src="<s:url value="/images/manage/other/bbNext.gif" />"></li>
          <li class="button"><img class="PBMouseOut" title="尾页" onclick="javascript:_getlist('last')" onmouseover="javascript:PBMOver(this);" onmouseout="javascript:PBMOut(this);" src="<s:url value="/images/manage/other/bbLast.gif" />"></li>
          <li class="button"><img class="PBMouseOut" title="跳转到" onclick="javascript:_getlist('page')" onmouseover="javascript:PBMOver(this);" onmouseout="javascript:PBMOut(this);" src="<s:url value="/images/manage/other/bbGotopage.gif" />"></li>
          <li class="txt"><input id="movePg" class="SeachTextEdit" size="3" maxlength="3" type="text"></li>
          </ul>
        </div>
        </s:if>

        </td>
      </tr>
    </table>
    </s:form>
    </td>
  </tr>
</table>

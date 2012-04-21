<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*,org.apache.struts.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<title>Insert title here</title>
</head>

<body>

<script type="text/javascript">
<!--//

function ShowDetail(name_no)
{
  var fm = document.BookDetail;
  fm.reserveNo.value = name_no;
  fm.submit();
}

function AddBooking(pid)
{
  var fm = document.showOrder;
  fm.recordNo.value = pid;
  fm.submit();
}

function LineDetail(lid)
{
  var fm = document.RouteDetail;
  fm.routeNo.value = lid;
  fm.submit();
}

function RegionPlan(region)
{
  var fm = document.PlanList;
  fm.regionId.value = region;
  fm.submit();
}
//-->
</script>
<table cellpadding="1" width="760" align="center">
  <tr>
    <!-- 左侧开始 -->
    <td valign="top" width="186" bgcolor="#f7f7f7">
    <!-- 注册区开始  -->
    <table style="width: 100%" bgcolor="#f7f7f7">
      <tr>
        <td width="50">用户名：</td>
        <td>
        <a title="点击修改当前用户个人信息！"href="#"><s:property value="#session['EBIZ_USER'].userName"/></a>&nbsp;&nbsp; 
        [<a title=点击修改当前用户的密码！href="#">修改密码</a>]</td>
      </tr>
      <tr>
        <td>部　门：</td>
        <td><s:property value="#session['EBIZ_USER'].departmentName" /></td>
      </tr>
    </table>
    <!-- 注册区结束  -->

    <!-- 最新出团通知开始 -->
    <table style="width: 100%">
      <tr>
        <td bgcolor="#efeef3" colspan="2"><strong>最新出团通知</strong></td>
      </tr>
      <tr>
        <td bgcolor="#f7f7f7">
      <s:iterator value="lastTours">
      <tr>
        <td><img height="8" src="/images/orange_arrow.gif" width="7"></td>
        <td valign="top" height="20"><a title="点击查看该团出团通知书！"
          href="#"
          target=_blank><s:property value="tourNo"/> <s:property value="lineName"/> <s:date name="outDate" format="M/d"/></a></td>
      </tr>
      </s:iterator>
      <s:if test="lastTours.isEmpty()">
      <tr>
        <td colspan="2">没有最新成团通知！</td>
      </tr>
      </s:if>

    </table>
    <!-- 最新出团通知结束 -->

    <!-- 订单查询区开始  -->
    <table style="width: 100%">
      <tr bgcolor="#efeef3">
        <td colspan="2"><strong>已审核订单</strong></td>
      </tr>
      <s:iterator value="confirmedBookings" status="rowcounter" >
      <s:if test="#rowcounter.count < 11">
      <tr>
        <td valign="top"><img height="8" src="/images/orange_arrow.gif" width="7"></td>
        <td valign="top" height="20">
          <a title="点击查看详细订单！" href="javascript:ShowDetail('<s:property value="recordNo" />');"><s:property value="lineName"/>、<s:date name="outDate" format="M/d"/>、<s:property value="pax"/>人</a>
        </td>
      </tr>
      </s:if>
      </s:iterator>
      <s:if test="confirmedBookings.isEmpty()">
      <tr>
        <td colspan="2">本机构没有已审核订单</td>
      </tr>
      </s:if>

      <tr bgcolor="#efeef3">
        <td colspan="2"><strong>未审核订单</strong></td>
      </tr>
      <s:iterator value="unconfirmedBookings" status="rowcounter">
      <s:if test="#rowcounter.count < 11">
      <tr>
        <td valign="top"><img height="8" src="/images/orange_arrow.gif" width="7"></td>
        <td valign="top" height="20">
          <a title="点击查看详细订单！" href="javascript:ShowDetail('<s:property value="recordNo" />');"><s:property value="lineName"/>、<s:date name="outDate" format="M/d"/>、<s:property value="pax"/>人</a>
        </td>
      </tr>
      </s:if>
      </s:iterator>
      
      <s:if test="unconfirmedBookings.isEmpty()">
      <tr>
        <td colspan="2">本机构没有未审核订单</td>
      </tr>
      </s:if>
    </table>
    <!-- 订单查询区结束  -->

    <!-- 最新线路区开始 -->
    <!-- 
    <table style="width: 100%" border="0">
      <tr>
        <td>最新线路</td>
      </tr>
      <tr>

        <td>
        <table style="width: 100%" border="0">
          <tr>
            <td colspan="2" height="5"></td>
          </tr>
          <tr>
            <td colspan="2" height="20"><img src="/images/icon.gif"
              width="9" height="7">-出境中心</td>
          </tr>
          <tr>
            <td colspan="2"></td>
          </tr>
          <tr>
            <td height="20">
              <img src="/images/arrow1.gif" height="5" width="5">
            </td>
            <td width="97%">
              <a href="#" target="_blank">桂林双飞四日常规游</a>
            </td>
          </tr>
          <tr>
            <td colspan="2" height="12"></td>
          </tr>
        </table>
        </td>

      </tr>
    </table>
    -->
    <!-- 最新线路区结束 -->

    </td>
    <!-- 左侧栏结束 -->


    <!-- 中间栏开始  -->
    <td valign="top">
    <!-- 目的地导航 -->
    <table style="width: 100%" bgcolor="#6493d9" border="0">
      <tr>
        <td colspan="4"><font color="#ffffff"><strong>出境旅游 - 目的地导航</strong></font></td>
      </tr>
      <tr>
        <td>
        <table cellpadding="1" style="width: 100%" bgcolor="#ffffff">
          <%
    List regionList = (List) request.getAttribute("abroadList");

    for (int i = 0; i < regionList.size(); i++) {
      List list = (List) regionList.get(i);
      for (int j = 0; j < list.size(); j++) {
        LabelValueBean lvb = (LabelValueBean) list.get(j);
        if (j == 0) {
          %>
          <tr>
            <td width="31%" height="20">&nbsp; 
            <img alt="" src="<s:url value='/images/icon.gif' encode='false' includeParams='none'/>" height="7" width="9"> &nbsp;-&nbsp; 
            <a href="#" onclick="javascript:RegionPlan('<%=lvb.getLabel() %>')"><%=lvb.getValue()%></a>
            </td>
            <%
        } else if ((j % 3 == 0) && j == list.size() - 1) {
            %>
            <td valign="top" width="167" height="20">
              <a href="#" onclick="javascript:RegionPlan('<%=lvb.getLabel() %>')"><%=lvb.getValue()%></a></td>
          </tr>
          <%
        } else if (j % 3 == 0) {
          %>
          <td valign="top" width="167" height="20"><a href="#" onclick="javascript:RegionPlan('<%=lvb.getLabel() %>')"><%=lvb.getValue()%></a></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <%
        } else {
            %>
            <td valign="top" width="167" height="20"><a href="#" onclick="javascript:RegionPlan('<%=lvb.getLabel() %>')"><%=lvb.getValue()%></a></td>
            <%
        }
      }
   }%>
        </table>
        </td>
      </tr>
    </table>
    <!-- 目的地导航-出境旅游 结束 -->

    <!-- 目的地导航 - 国内旅游 -->
    <table style="width: 100%" bgcolor="#6493d9" border="0">
      <tr>
        <td colspan="4"><font color="#ffffff"><strong>国内旅游 - 目的地导航</strong></font></td>
      </tr>
      <tr>
        <td>
        <table cellpadding="1" style="width: 100%" bgcolor="#ffffff" border="0">
          <%
    List insideList = (List) request.getAttribute("insideList");
    for (int i = 0; i < insideList.size(); i++) {
      List list = (List) insideList.get(i);
      for (int j = 0; j < list.size(); j++) {
        LabelValueBean lvb = (LabelValueBean) list.get(j);
        if (j == 0) {
          %>
          <tr>
            <td width="31%" height="20">&nbsp; <img alt=""
              src="<s:url value='/images/icon.gif' encode='false' includeParams='none'/>" height="7" width="9"> &nbsp;-&nbsp;
              <a href="#" onclick="javascript:RegionPlan('<%=lvb.getLabel() %>')"><%=lvb.getValue()%></a>
            </td>
            <%
        } else if ((j % 3 == 0) && j == list.size() - 1) {
            %>
            <td valign="top" width="167" height="20"><a href="#" onclick="javascript:RegionPlan('<%=lvb.getLabel() %>')"><%=lvb.getValue()%></a></td>
          </tr>
          <%
        } else if (j % 3 == 0) {
          %>
          <td valign="top" width="167" height="20"><a href="#" onclick="javascript:RegionPlan('<%=lvb.getLabel() %>')"><%=lvb.getValue()%></a></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <%
        } else {
            %>
            <td valign="top" width="167" height="20"><a href="#" onclick="javascript:RegionPlan('<%=lvb.getLabel() %>')"><%=lvb.getValue()%></a></td>
            <%
        }
      }
    } %>
        </table>
        </td>
      </tr>
    </table>
    <!-- 目的地导航 - 国内旅游 结束 -->

    <!-- 按工作组快速查找区开始 -->
    <!--
    <table style="width: 100%" height="14" border="0" cellspacing="1">
		<tr bgcolor="6493d9">
          <td height="20" colspan="4">按工作组快速查找</td>
		</tr>
        <tr>
          <td height="20">
		  <table style="width: 100%" border="0" cellspacing="1">
				<tr>
				<td width="25%" height="20"><img src="../images/icon.gif" width="9" height="7">&nbsp;-&nbsp;<a href="#" target="_blank">出境中心</a>
                </td>
				<td width="25%" height="20"><a href="#" target="_blank">亚洲部</a></td>
				<td width="25%" height="20"><a href="#" target="_blank">商务部</a></td>
				<td width="25%" height="20"><a href="#" target="_blank">团队销售部</a></td>
				<tr>
				<td width="25%" height="20"><font color="6699cc"><img src="../images/icon.gif" width="9" height="7"></font>&nbsp;-&nbsp;<a href="#" target="_blank"><font color="960605">国内中心    </font></a>
                </td>
				<td width="25%" height="20"><a href="#" target="_blank">国内计调部</a></td>
				<tr>
				<td width="25%" height="20"><font color="6699cc"><img src="../images/icon.gif" width="9" height="7"></font>&nbsp;-&nbsp;<a href="#" target="_blank"><font color="960605">FIT 事业部  </font></a>
                </td>
				<td width="25%" height="20"><a href="#" target="_blank">FIT 计调部</a></td>
				<tr>
				<td width="25%" height="20"><font color="6699cc"><img src="../images/icon.gif" width="9" height="7"></font>&nbsp;-&nbsp;<a href="#" target="_blank"><font color="960605">东南亚计调  </font></a>
                </td>
				<tr>
				<td width="25%" height="20"><font color="6699cc"><img src="../images/icon.gif" width="9" height="7"></font>&nbsp;-&nbsp;<a href="#" target="_blank"><font color="960605">欧美部计调  </font></a>
                </td>
				<tr>
				<td width="25%" height="20"><font color="6699cc"><img src="../images/icon.gif" width="9" height="7"></font>&nbsp;-&nbsp;<a href="#" target="_blank"><font color="960605">门店        </font></a>
                </td>
				<tr>
				<td width="25%" height="20"><font color="6699cc"><img src="../images/icon.gif" width="9" height="7"></font>&nbsp;-&nbsp;<a href="#" target="_blank"><font color="960605">国内部1     </font></a>
                </td>
			</tr>
            </table>
		  </td>
		 </tr>
		</table>
    -->
    <!-- 按工作组快速查找区结束 -->

    <!-- 查询区开始 -->
    <table style="width: 100%" bgcolor="#6699cc" border="0">
      <tr bgcolor="#6493d9">
        <td height="20"><font color="#ffffff"><strong>输入条件进行查找</strong></font></td>
      </tr>
      <tr>
        <td height="121">
        <s:form action="PlanSearchSubmit" namespace="/distribution" method="post" theme="simple">
        <table height="145" style="width: 100%" bgcolor="#ffffff" border="0">
          <tr>
            <td>分类：</td>
            <td>
            <s:radio name="kenClassType"
                     list="classTypes"
                     listKey="value"
                     listValue="label">
            </s:radio>
            </td>
          </tr>
          <tr>
            <td>线路名称：</td>
            <td><s:textfield name="kenRouteName" size="42"/></td>
          </tr>
          <tr>
            <td>出发日期：</td>
            <td>
            <sj:datepicker size="10" displayFormat="yy-mm-dd" name="kenStartDatePeriod"></sj:datepicker>&nbsp;至
            <sj:datepicker size="10" displayFormat="yy-mm-dd" name="kenEndDatePeriod"></sj:datepicker>
            </td>
          </tr>
          <tr>
            <td align=left width="16%">价格范围：</td>
            <td>
            <s:textfield name="kenLowerLimitPrice" size="10"></s:textfield> -
            <s:textfield name="kenUpperLimitPrice" size="10"></s:textfield> /元</td>
          </tr>
          <tr>
            <td align="right" bgcolor="#f7f7f7" colspan="2">
            <s:submit value="%{getText('common.forms.search')}"></s:submit>&nbsp;
            </td>
          </tr>
        </table>
        </s:form>
        </td>
      </tr>
    </table>
    <!-- 查询区结束 -->

    <!-- 各中心最近开班计划区开始 -->
    <table style="width: 100%" border="0">
      <tr>
        <td colspan="2" class="lstidx">线路</td>
        <td width="32" class="lstidx">出发<br>日期</td>
        <td width="60" class="lstidx">门市价</td>
        <td width="30" class="lstidx">剩余名额</td>
        <td class="lstidx">出发城市</td>
        <td width="30"class="lstidx">操作</td>
      </tr>

      <s:iterator value="abroadPlanList" id="kb">
        <tr>
          <td colspan="8" height="22"><strong>
          <s:iterator value="departments" id="kl">
           <s:if test="#kb.key eq #kl.key">
             <s:property value="#kl.value"/>
           </s:if>
          </s:iterator></strong></td>
        </tr>
        <tr>
          <td colspan="8" height="5"></td>
        </tr>
        <s:iterator value="value">
          <tr>
            <td width="15">
            <img alt="" src="<s:url value='/images/maru_g.gif' encode='false' includeParams='none'/>" height="12" width="12">
            </td>
            <td>
              <a href="#" onclick="LineDetail('<s:property value="line.lineNo"/>')"><s:property value='line.lineName'/></a>
            </td>
            <td><s:date name="outDate" format="MM/dd"/></td>
            <td class="rdata"><a href="#" title="同业价：<s:property value='packagePrice.priceOther' />"><s:property value="price"/></a>元</td>
            <td class="rdata"><s:property value="pax3"/>人</td>
            <td class="data" nowrap="nowrap"><s:property value="outCity"/>&nbsp;</td>
            <td>
            <%
            Set<String> auth = (Set<String>)request.getAttribute("auth");
            if (auth.contains("ROLE_SALES") || (auth.contains("ROLE_AGENT"))) {
            %>
              <s:if test="enter">
              <a onClick="javascript:AddBooking('<s:property value="recordNo"/>')" href="#">预订</a>
              </s:if>
            <% } %>
            </td>
          </tr>
        </s:iterator>
        <tr>
          <td align="right" colspan="8" height="10">
            <a href="<s:url action='PlanByDpt' namespace='/distribution'/>?dptNo=<s:property value='key' />" target=_blank>更多...</a>
          </td>
        </tr>
        <tr>
          <td colspan="8" height="5"></td>
        </tr>
      </s:iterator>

      <s:iterator value="insidePlanList" id="kt">
        <tr>
          <td colspan="8"><strong>
          <s:iterator value="departments" id="km">
           <s:if test="#kt.key eq #km.key">
             <s:property value="#km.value"/>
           </s:if>
          </s:iterator></strong>
          </strong></td>
        </tr>
        <tr>
          <td colspan="8" height="5"></td>
        </tr>
        <s:iterator value="value">
          <tr>
            <td width="15">
            <img alt="" src="<s:url value="/images/maru_g.gif" encode="false" includeParams="none" />" width="12" height="12">
            </td>
            <td>
            <a href="#" onclick="LineDetail('<s:property value="line.lineNo"/>')"><s:property value='line.lineName' /></a>
            </td>
            <td><s:date name="outDate" format="MM/dd"/></td>
            <td class="rdata"><a href="#" title="同业价：<s:property value='packagePrice.priceOther' />"><s:property value="price"/></a>元</td>
            <td class="rdata"><s:property value="pax3" />人</td>
            <td class="rdata">&nbsp;</td>
            <td class="data"><s:property value="outCity"/>&nbsp;</td>
            <td>
            <%
            Set<String> auth1 = (Set<String>)request.getAttribute("auth");
            if (auth1.contains("ROLE_SALES") || (auth1.contains("ROLE_AGENT"))) { %>
              <a onClick="javascript:AddBooking('<s:property value="recordNo" />')" href="#">预订</a>
            <% } %>
            </td>
          </tr>
        </s:iterator>
        <tr>
          <td align="right" colspan="8">
          <a href="<s:url action='PlanByDpt' namespace='/distribution'/>?dptNo=<s:property value='key' />" target=_blank>更多...</a>
          </td>
        <tr>
          <td colspan="8" height="5"></td>
      </s:iterator>
    </table>

    <s:form action="showOrder" method="post" namespace="/distribution">
      <s:hidden name="routeNo"></s:hidden>
      <s:hidden name="recordNo"></s:hidden>
    </s:form>
    <s:form id="RouteDetail" action="RouteDetail" method="post" namespace="/distribution">
      <s:hidden name="routeNo"></s:hidden>
    </s:form>
    <s:form id="PlanList" action="PlanList" method="post" namespace="/distribution">
      <s:hidden name="regionId"></s:hidden>
    </s:form>
    <s:form action="BookDetail" theme="simple" namespace="/distribution" method="POST">
      <s:hidden name="reserveNo"></s:hidden>
    </s:form>
    <!-- 各中心最近开班计划区结束 -->
    </td>
    <!-- 中间栏结束 -->

    <!-- 右栏开始 -->
    <td valign="top" width="140">
    
    <!-- 推广信息开始 -->
    <table style="width: 100%" border="0">
      <tr>
        <td bgcolor="#6493d9" align="center">推广信息</td>
      </tr>
      <tr>
        <td valign="center">
        <marquee onMouseOver="this.stop()" onMouseOut="this.start()" scrollamount="1" scrolldelay="60" direction="up" height="170">
        <table style="width: 100%" border="0">
          <tr>
            <td valign="top" colspan="2" align="center">
            <br>
            <font color="#ff0000">暂无推广信息</font>
            </td>
          </tr>
        </table>
        </marquee>
        </td>
      </tr>
    </table>
    <!-- 推广信息结束 -->

    <!-- 公告开始 -->
    <table style="width: 100%" border="0">
      <tr>
        <td bgcolor="#6493d9" align="center">公 告</td>
      </tr>
      <tr>
        <td valign="center">
        <marquee onMouseOver="this.stop()" onMouseOut="this.start()" scrollamount="1" scrolldelay="60" direction="up" height="240">
        <table style="width: 100%" border="0">
          <s:if test="notices.isEmpty()">
          <tr>
            <td valign="top" colspan="2" align="center">
            <br>
            <font color=#ff0000>没有最新公告！</font>
            </td>
          </tr>
          </s:if>
          <s:else>
          <s:iterator value="notices">
          <tr>
            <td valign="top" colspan="2" align="center"><s:property value="department."/><br><s:property value="subject"/></td>
          </tr>
          </s:iterator>
          </s:else>
        </table>
        
        </marquee>
        </td>
      </tr>
    </table>
    <!-- 公告结束 -->

    <!-- 签证办理办法开始 -->
    <table style="width: 100%" border="0">
      <tr>
        <td bgcolor="#6493d9" height="20" align="center">签证办理办法</td>
      </tr>
      <tr>
        <td>
        <s:iterator value="visaAreas">
        <a href="#?id=<s:property value="label"/>" target=_blank><s:property value="value"/></a>
        </s:iterator>
        </td>
      </tr>
    </table>
    <!-- 签证办理办法结束 -->
    </td>
    <!-- 右栏结束 -->
  </tr>
</table>

</body>
</html>

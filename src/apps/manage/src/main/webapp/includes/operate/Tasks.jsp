<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags"%>

<script type="text/javascript">

/** 审核订单 */
function detailBook(no)
{
  var fm = document.MyPage;
  fm.recordNo.value = no;
  fm.action="<s:url action='ConfirmBookInput' namespace='/operator' includeParams='none'/>";
  fm.submit();
}

/** 订单详细 */
function ShowAccountDetail(name_no)
{
  var fm = document.SalesBookDetail;
  fm.reserveNo.value = name_no;
  fm.submit();
}

function SubmitForm(param, target)
{
  var fm = document.TourPlanSearch;
  fm.recordNo.value = target;
  if (param =='delete') {
    if(confirm('确定删除吗？') == false){
      return;
    }
    fm.action = "<s:url action='TourPlanDelete' namespace='/operator'/>"
  } else if (param == 'change') {
      fm.action = "<s:url action='TourPlanChange' namespace='/operator' />"
  } else if (param == 'booking') {
      fm.action = "<s:url action='showPlanBooking' namespace='/operator' />"
  }
  fm.submit();
}

function detail(no)
{
  var fm = document.TourDetail;
  fm.tourNo.value = no;
  fm.submit();
}

function auditing(param)
{
   var fm=document.SingleBalanceAuditingInput;
   fm.tourNo.value=param;
   
   fm.action="<s:url action='SingleBalanceAuditingInput' namespace='/finance' includeParams='none'/>";
   fm.submit();
}

function modify(param)
{
   var fm=document.SingleTourBalanceModifyInput;
   fm.tourNo.value=param;
   
   fm.action="<s:url action='SingleTourBalanceModifyInput' namespace='/finance' includeParams='none'/>";
   fm.submit();
}

function get(param)
{
  var fm = document.SingleTourIncome;
  fm.tourNo.value=param;
  fm.action="<s:url action='SingleTourIncome' namespace='/finance' includeParams='none'/>";
  fm.submit();
}

function warrant(param)
{
  var fm = document.warrantInput;
  fm.tourNo.value=param;
  fm.action="<s:url action='warrantInput' namespace='/finance' includeParams='none'/>";
  fm.submit();
}

function printBalance(tourNo)
{
   var fm=document.fopReport;
   document.getElementById("paramid").value=tourNo;
   fm.submit();
}

function modifyCustomer(tourNo)
{
  var fm=document.ModifyCustomerInfoInput;
  fm.tourNo.value=tourNo;
  fm.action = "<s:url action='ModifyCustomerInfoInput' namespace='/operator' />"
  fm.submit();
}

function showRouteJourney(obj)
{
    var fm=document.ShowRouteJourney;
    fm.lineNo.value=obj;
    fm.action="<s:url action='ShowRouteJourney' namespace='/product' />";
    fm.submit();  
}

</script>

<sj:tabbedpanel id="opTab"> 
  <sj:tab target="tone" label="待审核的订单"></sj:tab>
  <sj:tab target="ttwo" label="待安排领队的团"></sj:tab>
  <sj:tab target="tthree" label="待成团的计划"></sj:tab>

	<table id="tone" border="0" style="width: 100%">
	  <tr bgcolor="#b9c0ff">
	    <td class="lstidx">NO.</td>
	    <td class="lstidx">订单号</td>
	    <td class="lstidx">线路</td>
	    <td class="lstidx">出发日期</td>
	    <td class="lstidx">客户</td>
	    <td class="lstidx">预订员</td>
	    <td class="lstidx">预订时间</td>
	    <td class="lstidx">预订人数</td>
	  </tr>
	  <s:iterator value="bookList" status="rowcounter">
	    <s:if test="#rowcounter.count < 11">
	      <tr>
	        <td class="cdata"><s:property value="#rowcounter.count" /></td>
	        <td align="left"><A href="#" onclick="javascript:detailBook('<s:property value='recordNo'/>')"><s:property value="recordNo" /></a>&nbsp;</td>
	        <td class="data"><s:property value="lineName" />&nbsp;</td>
	        <td class="cdata"><s:date name="outDate" format="yyyy-MM-dd" />&nbsp;</td>
	        <td class="data"><s:property value="customer.name" />&nbsp;</td>
	        <td class="data"><s:property value="reserve" />&nbsp;</td>
	        <td class="cdata"><s:date name="reserveDate" format="yyyy-MM-dd" />&nbsp;</td>
	        <td class="rdata"><s:property value="pax" />&nbsp;</td>
	      </tr>
	    </s:if>
	  </s:iterator>
	  <s:if test="bookList.size > 10">
	    <tr>
	      <td colspan="7" align="right">更多</td>
	    </tr>
	  </s:if>
	</table>

   <table id="ttwo" border="0" style="width: 100%">
     <tr bgcolor="#b9c0ff">
       <td class="lstidx">NO.</td>
       <td class="lstidx">团号</td>
       <td class="lstidx">配送时间</td>
     </tr>
     <s:iterator value="arrangeLeader" status="rowcounter">
       <tr>
         <td class="cdata"><s:property value="#rowcounter.count" /></td>
         <td align="center"><A href="#" onclick="javascript:toExpress('<s:property value='tourNo'/>')"><s:property value='tourNo' /></a>&nbsp;</td>
         <td align="center"><s:date name="outDate" format="yyyy-MM-dd" />&nbsp;</td>
       </tr>
     </s:iterator>
     <s:if test="arrangeLeader.size > 10">
       <tr>
         <td colspan="8" align="right">更多</td>
       </tr>
     </s:if>
   </table>

  <table id="tthree" border="0" style="width: 100%">
    <tr bgcolor="#b9c0ff">
      <td class="lstidx">NO.</td>
      <td class="lstidx">团号</td>
      <td class="lstidx">线路</td>
      <td class="lstidx">计划人数</td>
      <td class="lstidx">最小成团人数</td>
      <td class="lstidx">已订人数</td>
      <td class="lstidx">出发日期</td>
    </tr>
    <s:iterator value="canBuildList" status="rowcounter">
      <s:if test="#rowcounter.count < 11">
        <tr>
          <td class="cdata"><s:property value="#rowcounter.count" /></td>
          <td><A href="#" onclick="javascript:buildTour('<s:date name="outDate" format="yyyy-MM-dd 00:00:00" />','<s:date name="outDate" format="yyyy-MM-dd 00:00:00" />')"><s:property
            value="tourNo" /> </a>&nbsp;</td>
          <td><s:property value="line.lineName" />&nbsp;</td>
          <td><s:property value="planPax" />&nbsp;</td>
          <td><s:property value="buildMinPax" />&nbsp;</td>
          <td><s:property value="pax2" />&nbsp;</td>
          <td align="center"><s:date name="outDate"
            format="yyyy-MM-dd" />&nbsp;</td>
        </tr>
      </s:if>
    </s:iterator>
    <s:if test="canBuildList.size > 10">
      <tr>
        <td colspan="4" align="right">更多</td>
      </tr>
    </s:if>
  </table>  


</sj:tabbedpanel> 


<s:form action="MyPage" namespace="/default" method="post" theme="simple">
  <s:hidden name="recordNo"></s:hidden>
  <s:hidden name="isflag"></s:hidden>
</s:form>

<s:form action="SalesBookDetail" theme="simple" namespace="/sales" method="POST">
  <s:hidden name="reserveNo"></s:hidden>
</s:form>

<s:form action="TourPlanSearch" namespace="/operator" method="post" theme="simple">
  <s:hidden name="recordNo"></s:hidden>
</s:form>

<s:form action="TourDetail" namespace="/finance" method="post" theme="simple">
  <s:hidden name="tourNo"></s:hidden>
</s:form>

<s:form action='SingleBalanceAuditingInput' namespace='/finance' method="post" theme="simple">
  <s:hidden name="tourNo"></s:hidden>
</s:form>

<s:form action='SingleTourBalanceModifyInput' namespace='/finance' method="post" theme="simple">
  <s:hidden name="tourNo"></s:hidden>
</s:form>

<s:form action='SingleTourIncome' namespace='/finance' method="post" theme="simple">
  <s:hidden name="tourNo"></s:hidden>
</s:form>

<s:form action='warrantInput' namespace='/finance' method="post" theme="simple">
  <s:hidden name="tourNo"></s:hidden>
</s:form>

<s:form action="fopReport" namespace="/" method="POST" theme="simple">
  <s:hidden name="parameters(0).name" value="TOUR_NO"></s:hidden>
  <s:hidden id="paramid" name="parameters(0).data"></s:hidden>
  <s:hidden name="reportId" value="13"></s:hidden>
</s:form>

<s:form action="ModifyCustomerInfoInput" namespace="/operator" method="post" theme="simple">
  <s:hidden name="tourNo"></s:hidden>
</s:form>

<s:form action="ShowRouteJourney" namespace="/product" method="post" theme="simple">
  <s:hidden name="lineNo"></s:hidden>
</s:form>

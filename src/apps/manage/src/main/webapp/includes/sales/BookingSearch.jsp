<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单查询</title>
<meta name="menu" content="OrderMenu"/>
<meta name="heading" content="订单查询">
</head>

<body>
<script language="javascript">


/* 工作组变更 */
function change()
{
  var fm = document.submitBookingSearch;
  var departments = document.getElementById("departmentNo");
  var combo = document.getElementById("userId")
  var sdpt_no = departments.value;

  //更换目的地的内容
  //将原表中的内容清空
  while (combo.options.length>0) {
      combo.remove(0);
  }
  combo.disabled=false;
  
  $.ajax({
      type: "post",
      url: '<s:url value="/json/listEmployee.jspa" encode="false" includeParams="none"/>',
      timeout: 20000,
      error: function(){
          // alert('服务器错误');
      },
      data: {groupId: sdpt_no},
      async: false,
      success: function(data){
          $.each(data, function(i, n){

          if (typeof(n)=='object')
          {
              var kOption = document.createElement('OPTION');
              kOption.text = "全部";
              kOption.value = "";
              combo.options.add(kOption);
              
              $.each(n,function(j,m){
                  var oOption = document.createElement('OPTION');
                  oOption.text = m ;
                  oOption.value = j ;
                  combo.options.add(oOption);
              });
          }
      });
      }
  });
}
/* 订单详细 */
function ShowDetail(name_no)
{
  var fm = document.SalesBookDetail;
  fm.reserveNo.value = name_no;
  fm.submit();
}

/* 显示线路 */
function showRoute(param)
{
  var fm = document.RouteDetail;
  fm.routeNo.value = param;
  fm.submit();
}

/* 删除选中 订单 */
function deletebook()
{
	var fm = submitBookingSearch;
}

/* 分页列表 */
function _getlist(type)
{
  var frm = document.submitBookingSearch;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  document.getElementById("movePage").value = type ;
  frm.submit();
}

function selectAll(param)
{
	var cb = document.all.nameNo;
	if(param == 'Y')
	{
		for(i=0;i<cb.length;i++)
		{
			cb[i].checked = true;
		}
	}
	if(param == 'N')
	{
		for(i=0;i<cb.length;i++)
		{
			cb[i].checked = false;
		}
	}
}

function detail(no,val)
{
  var fm = document.submitBookingSearch;
  fm.recordNo.value = no;
  fm.bookingNo.value = no;
  if(val == "auditing")
  {
  	fm.action = "<s:url action='ConfirmBookInput' namespace='/sales' includeParams='none'/>";
  }
  else if(val == "search")
  {
  	 fm.action = "<s:url action='showReadBooking' namespace='/sales' includeParams='none'/>";
  }
  fm.submit();
}

function showRouteJourney(obj)
{
    var fm=document.submitBookingSearch;
    fm.lineNo.value=obj;
    fm.action="<s:url action='ShowRouteJourney' namespace='/product' />";
    fm.submit();  
}
</script>

<s:form action="submitBookingSearch" namespace="/sales" method="post" theme="simple">
  <s:hidden name="recordNo"></s:hidden>
  <s:hidden name="bookingNo"></s:hidden>

  <table style="width: 90%">
    <tr>
      <td class="idx">订单号：</td>
      <td><s:textfield name="kenReserveNo" size="15" /></td>
    </tr>
    <tr>
      <td class="idx">工作组(操作)：</td>
      <td>
        <s:select id="departmentNo"
                  name="kenDepartmentNo"
                  list="teamList"
                  listKey="teamId"
                  listValue="name"
                  headerKey="0"
                  headerValue="所有"
                  onchange="javascript:change();">
      </s:select></td>
      <td class="idx">出发日期：</td>
      <td>
      <sj:datepicker displayFormat="yy-mm-dd" name="kenStartDatePeriod" size="10" maxlength="10">
      </sj:datepicker>&nbsp;
                          至 <sj:datepicker displayFormat="yy-mm-dd" name="kenEndDatePeriod" size="10" maxlength="10">
      </sj:datepicker></td>
    </tr>
    <tr>
      <td class="idx">操作员：</td>
      <td>
      <s:select id="userId"
                name="kenUserId"
                list="employeeList"
                listKey="userId"
                listValue="userName"
                headerKey="0"
                headerValue="全部">
      </s:select></td>
      <td class="idx">预订日期：</td>
      <td>
      <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenReserveStart">
      </sj:datepicker>&nbsp;至 
      <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenReserveEnd">
      </sj:datepicker>
      </td>
    </tr>
    <tr>
      <td class="idx">线路名称：</td>
      <td><s:textfield name="kenRouteName" size="42" />[模糊查找]</td>
      <td class="idx">客人姓名：</td>
      <td><s:textfield name="kenTourist" size="10" maxlength="10"></s:textfield>[模糊查找]</td>
    </tr>
    <tr>
      <td class="idx">代理商：</td>
      <td><s:textfield name="kenAgent" size="20" maxlength="20"></s:textfield>[模糊查找]</td>
      <td class="idx">销售员：</td>
      <td><s:textfield name="kenSalesman" size="10" maxlength="10"></s:textfield></td>
    </tr>
    <tr>
      <td class="idx">是否占位：</td>
      <td><s:radio list="cfmList" name="kenCfm" listKey="value" listValue="label">
      </s:radio></td>
      <td class="idx">是否已读：</td>
      <td><s:radio list="readList" name="kenRead" listKey="value" listValue="label"></s:radio></td>
    </tr>
    <tr>
      <td class="idx">是否取消：</td>
      <td colspan="4"><s:radio list="delList" name="kenDel" listKey="value" listValue="label"></s:radio></td>
    </tr>
    <tr>
      <td colspan="4"></td>
      <td><s:submit value="%{getText('common.forms.search')}"></s:submit></td>
    </tr>
  </table>
  
  <table style="width: 100%">
    <tr>
      <td>
       <input type="button" onclick="javascript:selectAll('Y');" value="全选">
       <input type="button" onclick="javascript:selectAll('N');" value="全不选">
      </td>
    </tr>
    <tr>
      <td valign="top">
      <table style="width: 100%">
        <tr bgcolor="#b9c0ff">
          <td class="lstidx">&nbsp;</td>
          <td class="lstidx">No.</td>
          <td class="lstidx">订单号</td>
          <td class="lstidx">线路</td>
          <td class="lstidx">客户</td>
          <td class="lstidx">销售员</td>
          <td class="lstidx">预订员</td>
          <td class="lstidx">预订日期</td>
          <td class="lstidx">出发日期</td>
          <td class="lstidx">预订人数</td>
          <td class="lstidx">确认人数</td>
          <td class="lstidx">应收款</td>
          <td class="lstidx">已收款</td>
          <td class="lstidx">未收款</td>
          <!-- 
          <td class="lstidx">结算款</td>
          <td class="lstidx">状态</td>
          <td class="lstidx">审核否</td>-->
          <td class="lstidx">订单状态</td>
        </tr>

        <s:iterator value="bookings" status="rowcounter">
          <s:if test="#rowcounter.count > fromRecord">
            <s:if test="#rowcounter.count <= toRecord">

              <s:if test='delkey == "Y"'>
                <tr bgcolor="#FFDDDD">
                  <td align="center"><s:checkbox id="nameNo" name="nameNo" fieldValue="%{bookingNo}" /></td>
                  <td align="center"><s:property value="#rowcounter.count" /></td>
                  <td align="center">
                    <a href="javascript:ShowDetail('<s:property value='bookingNo'/>');"
                       title="点击查看该订单的详细内容！"><s:property value='bookingNo' /></a>
                  </td>
                  <td>
                    <a href="javascript:showRouteJourney('<s:property value='plan.line.lineNo'/>');"
                       title="点击查看该线路的详细内容！"><s:property value="plan.line.lineName" /></a>
                  </td>
                  <td><s:property value="customer.name" />&nbsp;</td>
                  <td align="left"><s:property value="salesman.userName" />&nbsp;</td>
                  <td align="left"><s:property value="reserve" />&nbsp;</td>
                  <td align="center"><s:date name="reserveDate" format="yyyy-MM-dd" />&nbsp;</td>
                  <td align="center"><s:date name="plan.outDate" format="yyyy-MM-dd" />&nbsp;</td>
                  <td align="right"><s:property value="pax" />&nbsp;</td>
                  <td align="right"><s:property value="confirmPax" />&nbsp;</td>
                  <td align="right"><s:property value="dbamt" />&nbsp;</td>
                  <td align="right"><s:property value="payCosts" />&nbsp;</td>
                  <td align="right"><s:property value="%{dbamt -payCosts}" />&nbsp;</td>
                  <td align="left">&nbsp;取消</td>
                </tr>
              </s:if>
              <s:else>
                <tr>
                  <td class="cdata"><s:checkbox id="nameNo" name="nameNo" fieldValue="%{bookingNo}" /></td>
                  <td class="cdata"><s:property value="#rowcounter.count" /></td>
                  <td class="cdata">
                    <a href="javascript:ShowDetail('<s:property value='bookingNo'/>');"
                       title="点击查看该订单的详细内容！"><s:property value='bookingNo' /></a>
                  </td>
                  <td class="data">
                    <a href="javascript:showRouteJourney('<s:property value='plan.line.lineNo'/>');"
                       title="点击查看该线路的详细内容！"><s:property value="plan.line.lineName" /></a>
                  </td>
                  <td class="data"><s:property value="customer.name" />&nbsp;</td>
                  <td class="data"><s:property value="salesman.userName" />&nbsp;</td>
                  <td class="data"><s:property value="reserve" />&nbsp;</td>
                  <td class="cdata"><s:date name="reserveDate" format="yyyy-MM-dd" />&nbsp;</td>
                  <td class="cdata"><s:date name="plan.outDate" format="yyyy-MM-dd" />&nbsp;</td>
                  <td class="rdata"><s:property value="pax" />&nbsp;</td>
                  <td class="rdata"><s:property value="confirmPax" />&nbsp;</td>
                  <td class="rdata"><s:property value="dbamt" />&nbsp;</td>
                  <td class="rdata"><s:property value="payCosts" />&nbsp;</td>
                  <td class="rdata"><s:property value="%{dbamt -payCosts}" />&nbsp;</td>
                  <!-- 
                  <td class="rdata">&nbsp;</td>
                  <td class="cdata">&nbsp;</td>
                   -->
                  <td class="data">&nbsp;
                    <s:if test='confirmStatus == "1"'>已占位</s:if>
                    <s:else><a href="#" onclick="javascript:detail('<s:property value='bookingNo'/>','auditing')" title="审核订单">未占位</a></s:else>
                    <s:if test='readKey == "Y"'>已读</s:if>
                    <s:else><a href="#" onclick="javascript:detail('<s:property value='bookingNo'/>','search')" title="查看未读订单">查看</a></s:else>
                    <s:if test='expressKey == "Y"'>
                    <A href="#" onclick="javascript:detail('<s:property value='bookingNo'/>','send')" title="配送">配送</A>
                  </s:if></td>
                </tr>
              </s:else>

            </s:if>
          </s:if>
        </s:iterator>

        <tr bgcolor="#c0c0c0">
          <td colspan="2">&nbsp;</td>
          <td>合计</td>
          <td colspan="6">&nbsp;</td>
          <td align="right"><s:property value="totalPax" />&nbsp;</td>
          <td align="right"><s:property value="totalConfirmPax" />&nbsp;</td>
          <td align="right"><s:property value="totalExpense" />&nbsp;</td>
          <td align="right"><s:property value="totalPay" />&nbsp;</td>
          <td align="right"><s:property value="totalUnPay" />&nbsp;</td>
          <td>&nbsp;</td>
        </tr>

        <authz:authorize ifAnyGranted="ROLE_SUPERUSER">
          <tr>
            <td align="center" colspan="15"><input type="button" onclick="javascript:deletebook()" value="删除选中订单" /></td>
          </tr>
        </authz:authorize>
      </table>

      <%@ include file="/includes/PagerTable.jsp" %>
      </td>
    </tr>
  </table>
</s:form>

<s:form action="SalesBookDetail" theme="simple" namespace="/sales" method="POST">
  <s:hidden name="reserveNo"></s:hidden>
</s:form>
<s:form action="RouteDetail" method="POST" namespace="/">
  <s:hidden name="routeNo"></s:hidden>
</s:form>
</body>
</html>

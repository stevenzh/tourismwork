<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>订单查询</title>
</head>

<body>
<script language="javascript">
<!--

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
  	fm.action = "<s:url action='ConfirmBookInput' namespace='/operator' includeParams='none'/>";
  }
  else if(val == "search")
  {
  	 fm.action = "<s:url action='showReadBooking' namespace='/operator' includeParams='none'/>";
  }
  fm.submit();
}


-->
</script>

<table align="center" border="0" style="width: 100%">
  <tr>
    <td class="header">[订单管理]-查询订单</td>
  </tr>
</table>

<s:form action="submitBookingSearch" namespace="/sales" method="post" theme="simple">
  <s:hidden name="countInPage"></s:hidden>
  <s:hidden name="currentPage"></s:hidden>
  <s:hidden name="sortId"></s:hidden>
  <s:hidden id="movePage" name="movePage"></s:hidden>
  <s:hidden name="recordNo"></s:hidden>

  <s:hidden name="bookingNo"></s:hidden>

  <table align="center" border="0" style="width: 100%">
    <tr>
      <td class="idx">线路所属工作组：</td>
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
      <input name="kenStartDatePeriod" size="10" type="text" onClick="WdatePicker()"/> 至
      <input name="kenEndDatePeriod" size="10" type="text" onClick="WdatePicker()"/>
      </td>
    </tr>
    <tr>
      <td class="idx">线路专管员：</td>
      <td>
      <s:select id="userId"
                name="kenUserId"
                list="employeeList"
                listKey="uid"
                listValue="userName"
                headerKey="0"
                headerValue="全部">
      </s:select>
      </td>
      <td class="idx">预订日期：</td>
      <td>
      <input name="kenReserveStart" size="10" type="text" onClick="WdatePicker()"/> 至
      <input name="kenReserveEnd" size="10" type="text" onClick="WdatePicker()"/>
      </td>
    </tr>
    <tr>
      <td class="idx">线路名称：</td>
      <td><s:textfield name="kenRouteName" size="42" /></td>
      <td class="idx">客人姓名：</td>
      <td><s:textfield name="kenTourist" size="10" maxlength="10"></s:textfield>
      [输入客人姓名，例如：张，则列出客人姓名中带张字的订单]</td>
    </tr>
    <tr>
      <td class="idx">代理商：</td>
      <td><s:textfield name="kenAgent" size="10" maxlength="10"></s:textfield></td>
      <td class="idx">销售员：</td>
      <td><s:textfield name="kenSalesman" size="10" maxlength="10"></s:textfield></td>
    </tr>
    <tr>
        <td class="idx">是否审核：</td>
        <td><s:radio list="cfmList" name="kenCfm" listKey="value" disabled="true" listValue="label">
        </s:radio></td>
        <td class="idx">是否已读：</td>
        <td><s:radio list="readList" name="kenRead" listKey="value" disabled="true" listValue="label"></s:radio></td>
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

  <table align="center" border="0" style="width: 100%">
    <tr>
      <td><input type="button" onclick="javascript:selectAll('Y');" value="全选">
       <input type="button" onclick="javascript:selectAll('N');" value="全不选">
      </td>
    </tr>
    <tr>
      <td valign="top">
      <table border="1" bordercolor="#b9c0ff" style="width: 100%">
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
           -->
          <!--<td class="lstidx">审核否</td>-->
          <td class="lstidx">订单状态</td>
        </tr>

        <s:iterator value="bookings" status="rowcounter">
          <s:if test="#rowcounter.count > fromRecord">
            <s:if test="#rowcounter.count <= toRecord">

              <s:if test='delkey == "Y"'>
                <tr bgcolor="#FFDDDD">
                  <td align="center"><s:checkbox id="nameNo" name="nameNo" fieldValue="%{recordNo}" /></td>
                  <td align="center"><s:property value="#rowcounter.count" /></td>
                  <td align="center"><a href="javascript:ShowDetail('<s:property value='recordNo'/>');"
                    title="点击查看该订单的详细内容！" alt="点击查看该订单的详细内容！"><s:property value='recordNo' /></a></td>
                  <td><a href="javascript:showRoute('<s:property value='lineNo'/>');" title="点击查看该线路的详细内容！"
                    alt="点击查看该线路的详细内容！"><s:property value="lineName" /></a></td>
                  <td><s:property value="customer.name" />&nbsp;</td>
                  <td align="left"><s:property value="salesman.userName" />&nbsp;</td>
                  <td align="left"><s:property value="reserve" />&nbsp;</td>
                  <td align="center"><s:date name="reserveDate" format="yyyy-MM-dd" /></td>
                  <td align="center"><s:date name="outDate" format="yyyy-MM-dd" /></td>
                  <td align="right"><s:property value="pax" /></td>
                  <td align="right"><s:property value="confirmPax" /></td>
                  <td align="right"><s:property value="dbamt" /></td>
                  <td align="right"><s:property value="payCosts" /></td>
                  <td align="right"><s:property value="%{dbamt -payCosts}" /></td>
                  <td align="left">&nbsp;取消</td>
                </tr>
              </s:if>
              <s:else>
                <tr>
                  <td class="cdata"><s:checkbox id="nameNo" name="nameNo" fieldValue="%{recordNo}" /></td>
                  <td class="cdata"><s:property value="#rowcounter.count" /></td>
                  <td class="cdata"><a href="javascript:ShowDetail('<s:property value='recordNo'/>');"
                    title="点击查看该订单的详细内容！"><s:property value='recordNo' /></a></td>
                  <td class="data"><a href="javascript:showRoute('<s:property value='lineNo'/>');"
                    title="点击查看该线路的详细内容！"><s:property value="lineName" /></a></td>
                  <td class="data"><s:property value="customer.name" />&nbsp;</td>
                  <td class="data"><s:property value="salesman.userName" />&nbsp;</td>
                  <td class="data"><s:property value="reserve" />&nbsp;</td>
                  <td class="cdata"><s:date name="reserveDate" format="yyyy-MM-dd" /></td>
                  <td class="cdata"><s:date name="outDate" format="yyyy-MM-dd" /></td>
                  <td class="rdata"><s:property value="pax" /></td>
                  <td class="rdata"><s:property value="confirmPax" /></td>
                  <td class="rdata"><s:property value="dbamt" /></td>
                  <td class="rdata"><s:property value="payCosts" /></td>
                  <td class="rdata"><s:property value="%{dbamt -payCosts}" /></td>
                  <!-- 
          <td class="rdata">&nbsp;</td>
          <td class="cdata">&nbsp;</td>
           -->
                  <td class="cdata"><s:if test='confirmStatus == "1"'>已占位</s:if> <s:else>
                    <A href="#" onclick="javascript:detail('<s:property value='recordNo'/>','auditing')" title="审核订单"
                      alt="审核订单">未占位</A>
                  </s:else> <s:if test='readKey == "Y"'>已读</s:if> <s:else>
                    <A href="#" onclick="javascript:detail('<s:property value='recordNo'/>','search')" title="查看未读订单"
                      alt="查看未读订单">查看</A>
                  </s:else> <s:if test='expressKey == "Y"'>
                    <A href="#" onclick="javascript:detail('<s:property value='recordNo'/>','send')" title="配送" alt="配送">配送</A>
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

        <!-- 
          <tr>
            <td align="center" colspan="15"><input type="button" onclick="javascript:deletebook()" value="删除选中订单" /></td>
          </tr>
           -->
      </table>

      <div class="PagerTable">
      <ul>
        <li class="doc" style="WIDTH: 560px">第<s:property value="currentPage" />页 共<s:property value="totalPage" />页
        共<s:property value="totalRecord" />条记录</li>
        <li class="button"><img class="PBMouseOut" title="首页" onclick="javascript:_getlist('first')"
          onmouseover="javascript:PBMOver(this);" onmouseout="javascript:PBMOut(this);"
          src='<s:url value="/images/manage/other/bbFirst.gif" />'></li>
        <li class="button"><img class="PBMouseOut" title="上一页" onclick="javascript:_getlist('prev')"
          onmouseover="javascript:PBMOver(this);" onmouseout="javascript:PBMOut(this);"
          src='<s:url value="/images/manage/other/bbPrev.gif" />'></li>
        <li class="button"><img class="PBMouseOut" title="下一页" onclick="javascript:_getlist('next')"
          onmouseover="javascript:PBMOver(this);" onmouseout="javascript:PBMOut(this);"
          src='<s:url value="/images/manage/other/bbNext.gif" />'></li>
        <li class="button"><img class="PBMouseOut" title="尾页" onclick="javascript:_getlist('last')"
          onmouseover="javascript:PBMOver(this);" onmouseout="javascript:PBMOut(this);"
          src='<s:url value="/images/manage/other/bbLast.gif" />'></li>
        <li class="button"><img class="PBMouseOut" title="跳转到" onclick="javascript:_getlist('page')"
          onmouseover="javascript:PBMOver(this);" onmouseout="javascript:PBMOut(this);"
          src='<s:url value="/images/manage/other/bbGotopage.gif" />'></li>
        <li class="txt"><input id="movePg" class="SeachTextEdit" size="3" maxlength="3" type="text"></li>
      </ul>
      </div>
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

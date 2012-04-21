<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title>订单一览表</title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
</head>

<body>
<SCRIPT type="text/javascript">

/* 修改订单  */
function change()
{
  var fm = document.SalesBookDetail;
  fm.action = "<s:url action='EditBook' namespace='/sales' includeParams='none' />"
  fm.submit();
}

/* 取消游客 */
function cancelCust()
{
  var fm = document.SalesBookDetail;
  var field = document.SalesBookDetail.selects;
  var isChecked=-1;

  for(var i = 0; i< field.length; i++)
  {
    if(field[i].checked)
    {
      isChecked = i;
      break;
    } 
  }

  if (isChecked == -1)
  {
    alert("请选择您指定的客人！");
    return;
  }

  if (confirm("确定取消该客人吗？") == true)
  {
    fm.action="<s:url action='cancelCustomers' namespace='/sales' includeParams='none'></s:url>";
    fm.submit();    
  }
}

/* 取消订单 */
function cancel()
{
  var fm = document.SalesBookDetail;
  if (confirm("确定取消该订单吗？") == true)
  {
    fm.action = "<s:url action='cancelOrder' namespace='/sales' includeParams='none'></s:url>"
    fm.submit();    
  }
}

/* 恢复客人 */
function resume()
{
  var fm = document.SalesBookDetail;
  if(confirm("确定恢复客人吗？") == true)
    {
    fm.action = "<s:url action='resumeCustomers' namespace='/sales' includeParams='none'></s:url>"
    fm.submit();    
  }
}

/* 查看帐单 */
function recknoingDetail()
{
  var fm = document.ReckoningDetail;
  fm.action = "<s:url action='ReckoningDetail' namespace='/operator' includeParams='none'></s:url>"
  fm.submit();    
}

/* 制作帐单 */
function reckoningMake()
{
  var fm = document.SalesBookDetail;
  fm.action = "<s:url action='ReckoningMake' namespace='/operator' includeParams='none'></s:url>";
  fm.submit();  
}

/* 游客全选 */
function selectAll(param)
{
	var cb = document.SalesBookDetail.selects;
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

/* 拆分订单 */
function split()
{
  var fm = document.SalesBookDetail;
  var field = document.SalesBookDetail.selects;
  var isChecked=-1;

  for(var i = 0; i< field.length; i++)
  {
    if(field[i].checked)
    {
      isChecked = i;
      break;
    } 
  }

  if (isChecked == -1)
  {
    alert("请选择您指定的客人！");
    return;
  }

  if (confirm("确定要拆分订单吗？") == true)
  {
    fm.action="<s:url action='splitBooking' namespace='/sales' includeParams='none'></s:url>";
    fm.submit();    
  }
}

</SCRIPT>

<table style="width: 100%">
  <tr>
    <td colspan="4" class="header">[订单管理]-查看订单</td>
  </tr>
</table>

<s:form action="SalesBookDetail" namespace="/sales" method="POST" theme="simple">

<!-- 订单NAME_NO -->
<s:hidden name="reserveNo" value="%{book.bookingNo}"></s:hidden>
<s:hidden name="book.bookingNo"></s:hidden>

<!-- 出团计划REC_NO -->
<s:hidden name="book.plan.planNo"></s:hidden>

<table align="center" border="0" style="width: 100%">
  <tr>
    <td class="idx">线路：</td>
    <td colspan="2"><s:property value="book.plan.line.lineName"/>&nbsp;</td>
    <td>状态:
    <STRONG>
    <s:if test='book.confirmStatus == "1"'>已占位</s:if>
    <s:else>未占位</s:else>
    <s:if test='book.delkey == "Y"'>已取消</s:if></STRONG>
    </td>
  </tr>
  <tr>
    <td class="idx">出发日期：</td>
    <td><s:date name="book.plan.outDate" format="yyyy-MM-dd"/></td>
    <td class="idx">团号：</td>
    <td><s:property value="book.plan.tourNo" /></td>
  </tr>
  <tr>
    <td class="idx">客户:</td>
    <td><s:property value="book.customer.name"/></td>
    <td class="idx">销售员：</td>
    <td><s:property value="book.salesman.userName" /></td>
  </tr>
  <tr>
    <td class="idx">联系人：</td>
    <td><s:property value="book.contact" /></td>
    <td class="idx">联系电话：</td>
    <td><s:property value="book.phone" /></td>
  </tr>
  <tr>
    <td class="idx">参团要求：</td>
    <td colspan="3">
    <s:radio name="book.canSplit"
             list="canSplitList"
             listKey="value"
             listValue="label"
             disabled="true"
             theme="simple">
     </s:radio>
     </td>
  </tr>
  <tr>
    <td class="idx">预订人数：</td>
    <td colspan="3"><s:property value="book.pax"/>人&nbsp;&nbsp;&nbsp;&nbsp;
       [确认人数：<s:property value="book.confirmPax"/>人、
        名单人数：<s:property value="book.importPax"/>人]</td>
  </tr>
  <tr>
    <td class="idx">预订人：</td>
    <td><s:property value="book.reserve" /></td>
    <td class="idx">预订时间：</td>
    <td><s:date name="book.reserveDate" format="yyyy-MM-dd HH:mm:ss" /></td>
  </tr>
  <tr>
    <td class="idx">更新人：</td>
    <td><s:property value="book.opuser" /></td>
    <td class="idx">更新时间：</td>
    <td><s:date name="book.opDate" format="yyyy-MM-dd HH:mm:ss" /></td>
  </tr>
  <tr>
    <td class="idx">备注：</td>
    <td colspan="3"><s:property value="book.remarks"/></td>
  </tr>
  <tr>
  	<td>
	  <input type="button" onclick="javascript:selectAll('Y');" value="全选">
	  <input type="button" onclick="javascript:selectAll('N');" value="全不选">
	</td>
  </tr>
  <tr>
    <td colspan="4">
    <table border="1" style="width: 100%">
      <tr>
        <td class="lstidx">&nbsp;</td>
        <td class="lstidx">No.</td>
        <td class="lstidx">姓名</td>
        <td class="lstidx">拼音</td>
        <td class="lstidx">身份证</td>
        <td class="lstidx">性别</td>
        <td class="lstidx">出生日期</td>
        <td class="lstidx">住房要求</td>
        <td class="lstidx">同住序号</td>
        <td class="lstidx">合同号</td>
        <td class="lstidx">发票号</td>
        <td class="lstidx">报价</td>
        <td class="lstidx">应收团款</td>
        <td class="lstidx">状态</td>
      </tr>

      <s:iterator value="book.customerList" status="rowccount">
      <s:if test='delkey == "N"'>
      <tr>
        <td class="cdata"><s:checkbox id="selects" name="selects" fieldValue="%{nmno}" theme="simple"></s:checkbox></td>
        <td class="cdata"><s:property value="#rowccount.count"/></td>
        <td class="data"><a href="javascript:showName('###');" title="点击查看客人的详细内容！"><s:property value="userName"/></a>&nbsp;</td>
	    <td class="data"><s:property value="pinYin"/>&nbsp;</td>
        <td class="data"><s:property value="idCard"/>&nbsp;</td>
        <td class="data"><s:property value="sex"/>&nbsp;</td>
        <td class="data"><s:date name="birthday" format="yyyy-MM-dd"/>&nbsp;</td>
        <td class="data"><s:property value="roomType"/>&nbsp;</td>
        <td class="rdata"><s:property value="roomNo"/>&nbsp;</td>
        <td class="rdata"><s:property value="contractNo"/>&nbsp;</td>
        <td class="rdata"><s:property value="invoiceNo"/>&nbsp;</td>
        <td class="rdata"><s:property value="price"/>&nbsp;</td>
        <td class="rdata"><s:property value="receivables"/>&nbsp;</td>
        <td class="data">&nbsp;<s:if test='UniteStatus == "T"'>已成团</s:if><s:else>团候</s:else></td>
      </tr>
      </s:if>

      <s:else>
      <tr bgcolor="#FFDDDD">
        <td align="center"><s:checkbox id="selects" name="selects" fieldValue="%{nmno}" theme="simple"></s:checkbox></td>
        <td align="center"><s:property value="#rowccount.count"/></td>
        <td><a href="javascript:showName('###');" title="点击查看客人的详细内容！"><s:property value="userName"/></a>&nbsp;</td>
        <td><s:property value="pinYin"/>&nbsp;</td>
        <td><s:property value="idCard"/>&nbsp;</td>
        <td><s:property value="sex"/>&nbsp;</td>
        <td><s:date name="birthday" format="yyyy-MM-dd"/>&nbsp;</td>
        <td><s:property value="roomType"/>&nbsp;</td>
        <td align="right"><s:property value="roomNo"/>&nbsp;</td>
        <td align="right"><s:property value="contractNo"/>&nbsp;</td>
        <td align="right"><s:property value="invoiceNo"/>&nbsp;</td>
        <td align="right"><s:property value="price"/>&nbsp;</td>
        <td align="right"><s:property value="receivables"/>&nbsp;</td>
        <td align="left">&nbsp;取消</td>
      </tr>
      </s:else>

      </s:iterator>

      <tr bgcolor="#b9c0ff">
        <td colspan="2">&nbsp;</td>
        <td colspan="10" align="center">小 计</td>
        <td align="right">&nbsp;</td>
        <td align="right">&nbsp;</td>
        <td align="right">&nbsp;</td>
        <td class="idx"><font color="#ff0000">&nbsp;</font></td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
</s:form>

<div>配送情况</div>

<div>签证办理情况</div>

<div>收款-付款</div>

<div>操作历史</div>
<br>  
<table style="width: 100%">
  <tr>
    <td width="100" class="lstidx">操作</td>
    <td align="center" class="data">
    <input type="button" value="订单取消" onclick="javascript:cancel();">
    <input type="button" value="游客取消" onclick="javascript:cancelCust();">
    <input type="button" value="单签申请">
    <input type="button" value="恢复游客" onclick="javascript:resume();">
    <input type="button" value="修改" onclick="javascript:change();">
    <input type="button" value="拆分订单" onclick="javascript:split();">
    <s:if test='status=="T"'>
      <input type="button" value="查看帐单" onclick="javascript:recknoingDetail();">
    </s:if>
    <s:else>
      <input type="button" value="制作帐单" onclick="javascript:reckoningMake();">
    </s:else>    
    </td>
    <td width="100" class="lstidx">可用操作流程</td>
    <td class="data">
    <input type="button" value="审核订单">
    <input type="button" value="制作帐单">
    </td>
  </tr>
</table> 

<s:form action="ReckoningDetail" namespace="/operator" method="POST" theme="simple">
  <s:hidden name="reserveNo" value="%{book.recordNo}"></s:hidden>
  <s:hidden name="reckoningId"></s:hidden>
</s:form>

</body>
</html>

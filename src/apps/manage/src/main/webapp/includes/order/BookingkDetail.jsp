<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详细</title>
<meta name="menu" content="OrderMenu"/>
<meta name="heading" content="订单详细">
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
  	fm.note.value = window.prompt("备注","请认真填写取消原因");
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
  	fm.note.value = window.prompt("备注","请认真填写取消原因");
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
    fm.note.value = window.prompt("备注","请认真填写恢复游客的原因");
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
  	fm.note.value = window.prompt("备注","请认真填写拆分订单的原因");
    fm.action="<s:url action='splitBooking' namespace='/sales' includeParams='none'></s:url>";
    fm.submit();    
  }
}

function toAuditing(obj)
{
  var fm=document.SalesBookDetail;
  fm.expressId.value=obj;
  fm.action="<s:url action='ToAuditing' namespace='/sales' includeParams='none'/>";
  fm.submit();
}

function addInvoice()
{
 alert("该功能尚未完成.") 
}

function gathing()
{
  var fm = document.showBookGathering;
  fm.submit();
}

function bookLogAll()
{
	document.getElementById("div_AllBookInfo").style.display="";
	document.getElementById("div_bookInfo").style.display="none";
	document.getElementById("div_bookNote").style.display="none";
	document.getElementById("td_all").style.backgroundColor="bbbbbb";
	document.getElementById("td_modifiedLog").style.backgroundColor="";
	document.getElementById("td_note").style.backgroundColor="";
	
	document.getElementById("a_all").style.textDecoration="none";
	document.getElementById("a_all").style.color="#FFFFFF";
	document.getElementById("a_modifiedLog").style.textDecoration="underline";
	document.getElementById("a_modifiedLog").style.color="#6699FF";
	document.getElementById("a_note").style.textDecoration="underline";
	document.getElementById("a_note").style.color="#6699FF";
}

function bookNote()
{
	document.getElementById("div_AllBookInfo").style.display="none";
	document.getElementById("div_bookInfo").style.display="none";
	document.getElementById("div_bookNote").style.display="";
	document.getElementById("td_note").style.backgroundColor="bbbbbb";
	document.getElementById("td_all").style.backgroundColor="";
	document.getElementById("td_modifiedLog").style.backgroundColor="";
	
	document.getElementById("a_note").style.textDecoration="none";
	document.getElementById("a_note").style.color="#FFFFFF";
	document.getElementById("a_all").style.textDecoration="underline";
	document.getElementById("a_all").style.color="#6699FF";
	document.getElementById("a_modifiedLog").style.textDecoration="underline";
	document.getElementById("a_modifiedLog").style.color="#6699FF";
}

function bookInfo()
{
	document.getElementById("div_AllBookInfo").style.display="none";
	document.getElementById("div_bookInfo").style.display="";
	document.getElementById("div_bookNote").style.display="none";
	document.getElementById("td_modifiedLog").style.backgroundColor="bbbbbb";
	document.getElementById("td_all").style.backgroundColor="";
	document.getElementById("td_note").style.backgroundColor="";
		
	document.getElementById("a_modifiedLog").style.textDecoration="none";
	document.getElementById("a_modifiedLog").style.color="#FFFFFF";
	document.getElementById("a_all").style.textDecoration="underline";
	document.getElementById("a_all").style.color="#6699FF";
	document.getElementById("a_note").style.textDecoration="underline";
	document.getElementById("a_note").style.color="#6699FF";
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
<!-- 配送单号 -->
<s:hidden name="expressId"></s:hidden>
<s:hidden name="note"></s:hidden>

<table align="center" border="0" style="width: 100%">
  <tr>
    <td class="idx">线路：</td>
    <td colspan="2"><s:property value="book.plan.line.lineName"/>&nbsp;</td>
    <td>状态:
    <STRONG>
    <s:if test='book.cfmKey == "1"'>已占位</s:if>
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
    <table style="width: 100%">
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
       <s:if test='del == "N"'>
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
        <td class="data"><s:property value="contractNo"/>&nbsp;</td>
        <td class="data"><s:property value="invoiceNo"/>&nbsp;</td>
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
        <td align="left">取消&nbsp;</td>
      </tr>
      </s:else>
        </s:iterator>
    </table>
    <br>
    <table style="width: 100%">
      <tr bgcolor="#b9c0ff">
        <td align="center">合计</td>
        <td class="lstidx">应收团款</td>
        <td align="right"><s:property value="book.dbamt"/>&nbsp;</td>
        <td class="lstidx">已收团款</td>
        <td align="right"><s:property value="book.payCosts"/>&nbsp;</td>
        <td class="lstidx">未收团款</td>
        <td align="right"><s:property value="%{book.dbamt - book.payCosts}"/>&nbsp;</td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>

  <tr>
    <td colspan="4"><STRONG>配送列表</STRONG></td>
  </tr>
  <tr>
    <td colspan="4">
    <s:if test="expressList.size == 0">没有配送记录</s:if>
    <s:else>
    <table border="1" bordercolor="#b9c0ff" style="width: 100%">
      <tr bgcolor="#b9c0ff">
        <td class="lstidx">配送单号</td>
        <td class="lstidx">团号</td>
        <td class="lstidx">配送时间</td>
        <td class="lstidx">应收团款</td>
        <td class="lstidx">备注</td>
      </tr>
      <s:iterator value="expressList" status="rowcounter">
        <tr>
          <td class="cdata"><A href="#" onclick="javascript:toAuditing('<s:property value='expressId'/>')"> <s:property value='expressId' /></a>&nbsp;</td>
          <td class="data"><s:property value="teamNo" />&nbsp;</td>
          <td class="cdata"><s:date name="expressDate" format="yyyy-MM-dd" />&nbsp;</td>
          <td class="rdata"><s:property value="pay" />&nbsp;</td>
          <td class="data"><s:property value="memo" />&nbsp;</td>
        </tr>
      </s:iterator>
    </table>
    </s:else>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4"><STRONG>付款记录</STRONG></td>
  </tr>
  <tr>
    <td colspan="4">
    <s:if test="payments.size == 0">没有收款记录</s:if>
    <s:else>
    <table border="1" style="width: 100%">
      <tr>
        <td class="lstidx">说明</td>
        <td class="lstidx">付款方式</td>
        <td class="lstidx">付款项目</td>
        <td class="lstidx">金额</td>
        <td class="lstidx">收款人</td>
        <td class="lstidx">操作人</td>
        <td class="lstidx">操作时间</td>
        <td class="lstidx">发票</td>
        <td class="lstidx">操作</td>
      </tr>

      <s:iterator value="payments" status="rowccount">
      <tr>
        <td class="data"><s:property value="note"/><s:hidden name="bookingNo"></s:hidden> &nbsp;</td>
        <td class="cdata"><s:property value="payMode"/>&nbsp;</td>
        <td class="cdata"><s:property value="useTypeLabel"/>&nbsp;</td>
        <td class="rdata"><s:property value="amount"/>&nbsp;</td>
        <td class="cdata"><s:property value="receiver"/>&nbsp;</td>
        <td class="cdata"><s:property value="operator"/>&nbsp;</td>
        <td class="cdata"><s:date name="incomeDate" format="yyyy-MM-dd HH:mm"/>&nbsp;</td>
        <td class="cdata">
          <s:iterator value="invices">
            <a href="#" onclick="javascript:printInv('<s:property value="recordNo"/>','<s:property value="amountChinese"/>')"><s:property value="recordNo"/>打印</a>
            <br>
          </s:iterator>&nbsp;
        </td>
        <td class="cdata"><a href="#" onclick="javascript:addInvoice('<s:property value="orderNo"/>')">填写发票</a></td>
      </tr>
      </s:iterator>
    </table>
    </s:else>
    </td>
  </tr>

  <tr>
    <td colspan="4">签证办理情况&nbsp;</td>
  </tr>

  <tr>
    <td colspan="4"><STRONG>操作历史</STRONG>&nbsp;</td>
  </tr>
  <s:if test="bookLogList.size != 0">
  <tr>
  	<td colspan="4">
		<table border="0" style="width: 100%" align="center">
			<tr>
        <td id="td_all" style="background-color: bbbbbb" width="1%" nowrap align="center">
            &nbsp;<b><a id="a_all" href="####" style="color:#FFFFFF ; text-decoration: none" onclick="javascript:bookLogAll()">所有</a></b>&nbsp;
        </td>
        <td id="td_note" width="1%" nowrap align="center">
            &nbsp;<b><a id="a_note" href="####" onclick="javascript:bookNote()">备注</a></b>&nbsp;
        </td>
        <td id="td_modifiedLog" width="1%" nowrap align="center">
            &nbsp;<b><a id="a_modifiedLog" href="####" onclick="javascript:bookInfo()">改动记录</a></b>&nbsp;
        </td>
        <td>&nbsp; </td>
      </tr>
    </table>
    </td>
  </tr>
	  
  <tr>
  	<td colspan="4">
		<table border="1" style="width: 100%" bordercolor="bbbbbb">
			<tr>
				<td>
		  		<div id="div_bookInfo"  style="display: none;">
		  			<s:iterator value="bookLogList" status="rowccount">
		  				<s:if test="info.size != 0">
				  			<table border="0" style="width: 100%">
			            <tr>
		                <td bgcolor="f0f0f0" colspan="3" style="padding-top: 3px;">
                       <s:property value="modifiedUserName"/>&nbsp; 进行了改变 &nbsp;<font size="-2">[<font color="336699"><s:date name="modifiedDate" format="yyyy年MM月dd日 HH:mm"/></font>]</font>
		                </td>
			            </tr>
			            <tr>
		                <td bgcolor="dddddd" width="20%"><b>字段</b></td>
		                <td bgcolor="dddddd" width="40%"><b>原值</b></td>
		                <td bgcolor="dddddd" width="40%"><b>新值</b></td>
			            </tr>
			            <s:iterator value="info" status="rowccount">
				          <tr>
		                <td bgcolor="ffffff" width="20%"><s:property value="name"/></td>
		                <td bgcolor="ffffff" width="40%"><s:property value="oldValue"/></td>
		                <td bgcolor="ffffff" width="40%"><s:property value="newValue"/></td>
				          </tr>
						    	</s:iterator>
					      </table>
				      </s:if>
			      </s:iterator>
		  		</div>
		  		<div id="div_bookNote"  style="display: none;">
		  			<s:iterator value="bookLogList" status="rowccount">
				  		<table border="0" style="width: 100%">
		            <tr>
                  <td bgcolor="f0f0f0" style="padding-top: 3px;">
                    <s:property value="modifiedUserName"/>&nbsp; - &nbsp; <font size="-2">[<font color="336699"><s:date name="modifiedDate" format="yyyy年MM月dd日 HH:mm"/></font>]</font>
                  </td>
		            </tr>
		            <tr>
		            	<td bgcolor="ffffff"><s:property value="note"/></td>
		            </tr>
				  		</table>
			  		 </s:iterator>
		  		</div>
		  		
		  		<div id="div_AllBookInfo">
		  			<s:iterator value="bookLogList" status="rowccount">
		  				<s:if test="info.size != 0">
				  			<table border="0" style="width: 100%">
			            <tr>
			                <td bgcolor="f0f0f0" colspan="3" style="padding-top: 3px;">
			                       <s:property value="modifiedUserName"/>&nbsp; 进行了改变 &nbsp;<font size="-2">[<font color="336699"><s:date name="modifiedDate" format="yyyy年MM月dd日 HH:mm"/></font>]</font>
			                </td>
			            </tr>
			            <tr>
			                <td bgcolor="dddddd" width="20%">
			                	<b>字段</b>
			                </td>
			                <td bgcolor="dddddd" width="40%">
			                	<b>原值</b>
			                </td>
			                <td bgcolor="dddddd" width="40%">
			                	<b>新值</b>
			                </td>
			            </tr>
			            <s:iterator value="info" status="rowccount">
				            <tr>
				                <td bgcolor="ffffff" width="20%">
				                    <s:property value="name"/>
				                </td>
				                <td bgcolor="ffffff" width="40%">
				                   	<s:property value="oldValue"/>
				                </td>
				                <td bgcolor="ffffff" width="40%">
				                	<s:property value="newValue"/>
				                </td>
				          </tr>
						    	</s:iterator>
						    	<tr>
		                <td bgcolor="f0f0f0" colspan="3" style="padding-top: 3px;">
		                       <s:property value="modifiedUserName"/>&nbsp; - &nbsp; <font size="-2">[<font color="336699"><s:date name="modifiedDate" format="yyyy年MM月dd日 HH:mm"/></font>]</font>
		                </td>
			            </tr>
			            <tr>
			            	<td bgcolor="ffffff" colspan="3"><s:property value="note"/></td>
			            </tr>
			        </table>
				  		</s:if>
			  		 </s:iterator>
		  		</div>	  		
	  		</td>
		  </tr>
		</table>
  	</td>
  </tr>
  </s:if>

  <s:else>
  	<tr>
  		<td colspan="4">没有任何历史记录</td>
  	</tr>  
  </s:else>     
</table>
</s:form>

<br>  
<table style="width: 100%">
  <tr>
    <td class="lstidx">操作</td>
    <td align="center" class="data">
    <s:if test='book.delkey != "Y"'>
    <input type="button" value="订单取消" onclick="javascript:cancel();">
    <input type="button" value="游客取消" onclick="javascript:cancelCust();">
    </s:if>
    <input type="button" value="恢复游客" onclick="javascript:resume();">
    <input type="button" value="修改订单" onclick="javascript:change();">
    <input type="button" value="拆分订单" onclick="javascript:split();">
    <s:if test="book.dbamt-book.payCosts > 0">
    <input type="button" value="签单收款" onclick="javascript:gathing();">
    </s:if>
    <s:if test='book.delkey != "Y"'>
    <s:if test='status=="T"'>
      <input type="button" value="查看帐单" onclick="javascript:recknoingDetail();">
    </s:if>
    <s:else>
      <input type="button" value="制作帐单" onclick="javascript:reckoningMake();">
    </s:else>   
    <input type="button" value="申请配送"> 
    </s:if>
   
    </td>
    
    <td class="lstidx">可用操作流程</td>
    <td class="data">
    <s:iterator value="taskList" id="task">
      <s:if test='#task.name eq "ORDER_CONFIRM"'>
      <input type="button" name="btnApprove" value="订单审核" onclick="auditing(true);">
      <br>
      </s:if>
    </s:iterator>&nbsp;
    </td>
  </tr>
</table> 
 
<s:form action="ReckoningDetail" namespace="/operator" method="POST" theme="simple">
  <s:hidden name="reserveNo" value="%{book.bookingNo}"></s:hidden>
  <s:hidden name="reckoningId"></s:hidden>
</s:form>

<s:form action="showBookGathering" namespace="/sales" method="POST" theme="simple">
  <s:hidden name="bookingNo" value="%{book.bookingNo}"></s:hidden>
</s:form>
</body>
</html>

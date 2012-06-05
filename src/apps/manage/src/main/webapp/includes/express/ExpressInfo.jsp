<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配送详细信息</title>
<meta name="menu" content="ExpressMenu"/>
<meta name="heading" content="配送详细信息">
</head>

<script type="text/javascript">
<!--//
function auditing(param)
{
	var fm=document.showExpressDetail;
	fm.isPass.value = param;
	fm.action="<s:url action='auditingExpress' namespace='/sales' includeParams='none'/>";
	fm.submit();
}

function examine(param)
{
  var fm=document.showExpressDetail;
  fm.isPass.value = param;
  fm.action="<s:url action='examineExpress' namespace='/sales' includeParams='none'/>";
  fm.submit();
}

function account()
{
  var fm=document.showExpressDetail;
  fm.action="<s:url action='accountExpress' namespace='/sales' includeParams='none'/>";
  fm.submit();
}

function toDelete()
{
  if(confirm("您确定要取消配送吗？"))
  {
    var fm=document.showExpressDetail;
    fm.note.value=window.prompt("备注","请认真填写删除原因");
    fm.action="<s:url action='cancelExpress' namespace='/express' includeParams='none'/>";
    fm.submit();
  }
}

function toEdit()
{
  var fm=document.showExpressDetail;
  fm.action="<s:url action='editExpress' namespace='/express' includeParams='none'/>";
  fm.submit();
}


function toPrint()
{
  window.alert("to print");
}

function expressLogAll()
{
	document.getElementById("div_AllExpressInfo").style.display="";
	document.getElementById("div_expressInfo").style.display="none";
	document.getElementById("div_expressNote").style.display="none";
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

function expressNote()
{
	document.getElementById("div_AllExpressInfo").style.display="none";
	document.getElementById("div_expressInfo").style.display="none";
	document.getElementById("div_expressNote").style.display="";
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

function expressInfo()
{
	document.getElementById("div_AllExpressInfo").style.display="none";
	document.getElementById("div_expressInfo").style.display="";
	document.getElementById("div_expressNote").style.display="none";
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
//-->
</script>

<body>
<s:form action="showExpressDetail" namespace="/express" theme="simple">
  <s:hidden name="expressId"></s:hidden>
  <s:hidden name="isPass"></s:hidden>
  <s:hidden name="note"></s:hidden>
  <table border="1" style="width: 80%" >
    <tr>
      <td class="idx">配送单号：</td>
      <td class="data" colspan="3"><s:property value="express.expressId" />&nbsp;</td>
    </tr>
    <tr>
      <td class="idx">团号：</td>
      <td class="data"><s:property value="express.teamNo" />&nbsp;</td>
      <td class="idx">线路：</td>
      <td class="data"><s:property value="express.line" />&nbsp;</td>
    </tr>
    <tr>
      <td class="idx">客户/旅行社：</td>
      <td class="data"><s:property value="express.customer" />&nbsp;</td>
      <td class="idx">订单号：</td>
      <td class="data"><s:property value="express.orderId" />&nbsp;</td>
    </tr>
    <tr>
      <td class="idx">联系地址：</td>
      <td class="data"><s:property value="express.address" />&nbsp;</td>
      <td class="idx">联系电话：</td>
      <td class="data"><s:property value="express.tel" />&nbsp;</td>
    </tr>
    <tr>
      <td class="idx">联系人：</td>
      <td class="data"><s:property value="express.contactor" />&nbsp;</td>
      <td class="idx">邮政编码：</td>
      <td class="data"><s:property value="express.zip" />&nbsp;</td>
    </tr>
    <tr>
      <td class="idx">配送类型：</td>
      <td class="data"><s:property value="eModlue" />&nbsp;</td>
      <td class="idx">支付方式：</td>
      <td class="data"><s:property value="pModlue" />&nbsp;</td>
    </tr>
    <tr>
      <td class="idx">收款类别：</td>
      <td class="data"><s:property value="pType" />&nbsp;</td>
      <td class="idx">应收团款：</td>
      <td class="data"><s:property value="express.pay" />&nbsp;</td>
    </tr>
    <tr>
      <td class="idx">配送时间：</td>
      <td class="data"><s:date name="express.expressDate" format="yyyy-MM-dd" />&nbsp;</td>
      <td class="idx">最后期限：</td>
      <td class="data"><s:date name="express.expressLastdate" format="yyyy-MM-dd" />&nbsp;</td>
    </tr>
    <tr>
      <td class="idx">配送方式：</td>
      <td class="data" colspan="3"><s:property value="eType" />&nbsp;</td>
    </tr>
    <tr>
      <td class="idx">配送状态：</td>
      <td class="data" colspan="3"><s:property value="eState" />&nbsp;</td>
    </tr>
  </table>
  <h4>配送明细列表</h4>
  <table border="1" style="width: 100%">
    <tr>
      <td class="lstidx">No.</td>
      <td class="lstidx">配送项目</td>
      <td class="lstidx">配送数量</td>
      <td class="lstidx">配送方式</td>
      <td class="lstidx">单位</td>
    </tr>
    <s:iterator value="expressList" status="rowcounter">
      <tr>
        <td class="cdata"><s:property value="#rowcounter.count" />&nbsp;</td>
        <td class="data">&nbsp;<s:property value="note" />&nbsp;</td>
        <td class="data">&nbsp;<s:property value="expressNum" />&nbsp;</td>
        <td class="cdata"><s:if test='expressType == "Y"'>送</s:if> <s:else>	取</s:else>&nbsp;</td>
        <td class="data">&nbsp;<s:property value="unit" />&nbsp;</td>
      </tr>
    </s:iterator>
  </table>
  <table>
    <tr>
      <td class="idx">备注：</td>
      <td class="data"><s:textarea name="express.memo" cols="100" rows="7"></s:textarea></td>
    </tr>
  </table>

  <br>
  <div>操作历史</div>
  <br>

<table style="width: 100%">
  <tr>
    <td class="lstidx">操作</td>
    <td align="center" class="data">
    <input type="button" name="btnDel" value="删除" onclick="toDelete()">
    <input type="button" name="btnPrint" value="修改" onclick="toEdit()">
    <input type="button" name="btnPrint" value="打印" onclick="toPrint()">
    <input type="button" value="返回" onClick="javascript:history.go(-1);">
    </td>
    <td class="lstidx">可用操作流程</td>
    <td class="data">
    <s:iterator value="taskList" var="task">
      <!-- 签单部审核 -->
      <s:if test='#task.name eq "renderPayments"'>
      <div>签单部审核&nbsp;
      <input type="button" name="btnApprove" value="通过" onclick="auditing(true);">
      <input type="button" name="btnDisapprove" value="不通过" onclick="auditing(false);">
      </div>
      <br>
      </s:if>

      <!-- 缴纳团款 -->
      <s:if test='#task.name eq "renderAccount"'>
      <input type="button" name="btnApprove" value="缴纳团款" onclick="account();">
      <br>
      </s:if>

      <!-- 缴纳团款 -->
      <s:if test='#task.name eq "examineData"'>
      <div>签单部审核&nbsp;
      <input type="button" name="btnApprove" value="通过" onclick="examine(true);">
      <input type="button" name="btnDisapprove" value="不通过" onclick="examine(false);">
      </div>
      </s:if>
    </s:iterator>
    </td>
  </tr>
</table>

<table style="width: 100%">
  <tr>
    <td colspan="4"><STRONG>操作历史</STRONG>&nbsp;</td>
  </tr>	
  <s:if test="expressLogList.size != 0">
	  <tr>
	  	<td colspan="4">
	  		<table style="width: 100%">
	  			<tr>
	                <td id="td_all" style="background-color: bbbbbb" width="1%" nowrap align="center">
	                    &nbsp;<b><a id="a_all" href="####" style="color:#FFFFFF ; text-decoration: none" onclick="javascript:expressLogAll()">所有</a></b>&nbsp;
	                </td>
	                <td id="td_note" width="1%" nowrap align="center">
	                    &nbsp;<b><a id="a_note" href="####" onclick="javascript:expressNote()">备注</a></b>&nbsp;
	                </td>
	                <td id="td_modifiedLog" width="1%" nowrap align="center">
	                    &nbsp;<b><a id="a_modifiedLog" href="####" onclick="javascript:expressInfo()">改动记录</a></b>&nbsp;
	                </td>
	                <td>
	                    &nbsp;
	                </td>
	            </tr>
	        </table>
	    </td>
	  </tr>
	  
	  <tr>
	  	<td colspan="4">
	  		<table style="width: 100%; border:1px; border-color: #bbbbbb">
	  			<tr>
	  				<td>
				  		<div id="div_expressInfo"  style="display: none;">
				  			<s:iterator value="expressLogList" status="rowccount">
					  			<table style="width: 100%; border: 1px">
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
						        </table>
					        </s:iterator>
				  		</div>
				  		<div id="div_expressNote"  style="display: none;">
				  			<s:iterator value="expressLogList" status="rowccount">
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
				  		
				  		<div id="div_AllExpressInfo">
				  			<s:iterator value="expressLogList" status="rowccount">
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
</body>
</html>

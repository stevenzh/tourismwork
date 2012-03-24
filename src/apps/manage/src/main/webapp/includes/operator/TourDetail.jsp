<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>旅游团队处理</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="旅游团队处理">
</head>

<body>
<script language="javascript">
	function checkUpdate()
	{
		var fm = document.submitTourOperate;
		if(confirm("确定要更新么？") == false)
			return;	
    fm.action="<s:url action='submitTourOperate' namespace='/operator'/>";
    fm.note.value=window.prompt("备注","请认真填写修改原因");
		fm.submit();
  }
	
	function checkCancel()
	{
		var fm = document.submitTourOperate;
		fm.action="<s:url action='cancelTour' namespace='/operator'/>"
		if(confirm("确定取消团？") == false)
			return;
	  fm.note.value=window.prompt("备注","请认真填写取消原因");
		fm.submit();
	}
	
	function generateXLS(param,param1)
	{
	  var fm = document.jxlsReport;
	  document.getElementById('paramid').value = param;
	  document.getElementById('paramid1').value = param1;
	  fm.submit();
	}

	function generateXLS2(param2,param3)
	{
	  var fm = document.jxlsReport2;
	  document.getElementById('paramid2').value = param2;
	  document.getElementById('paramid3').value = param3;
	  fm.submit();
	}
	
	function tourLogAll()
	{
		document.getElementById("div_AllTourInfo").style.display="";
		document.getElementById("div_tourInfo").style.display="none";
		document.getElementById("div_tourNote").style.display="none";
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

	function tourNote()
	{
		document.getElementById("div_AllTourInfo").style.display="none";
		document.getElementById("div_tourInfo").style.display="none";
		document.getElementById("div_tourNote").style.display="";
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

	function tourInfo()
	{
		document.getElementById("div_AllTourInfo").style.display="none";
		document.getElementById("div_tourInfo").style.display="";
		document.getElementById("div_tourNote").style.display="none";
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
  
  function ShowDetail(name_no)
  {
      var fm = document.submitTourOperate;
      fm.action="<s:url action='SalesBookDetail' namespace='/sales'/>";
      fm.reserveNo.value = name_no;
      fm.submit();
  }
	
</script>

<s:form action="submitTourOperate" namespace="/operator" method="POST" theme="simple">
<s:hidden name="kenDepartmentId"></s:hidden>
<s:hidden name="kenEmployeeId"></s:hidden>
<s:hidden name="kenStartDate"></s:hidden>
<s:hidden name="kenEndDate"></s:hidden>

<s:hidden name="tourNo"></s:hidden>
<s:hidden name="note"></s:hidden>
<s:hidden name="tour.planNo"></s:hidden>

<table align="center" border="0" bordercolor="#000000" cellpadding="2" cellspacing="2" width="100%">
  <tr>
    <td class="idx" width="15%">团号:</td>
    <td colspan="3"><s:textfield name="tour.tourNo" size="80" maxLength="100"/></td>
  </tr>
  <tr>
    <td class="idx" width="15%">线路：</td>
    <td><s:property value="tour.line.lineName"/></td>
    <td class="idx">是否需要领队：</td>
	  <td>
      <s:radio list="needLeaderList"
               name="tour.isneedleader"
               listKey="value"
               listValue="label">
      </s:radio>
    </td>
  </tr>
  <tr>
    <td class="idx">出境日期：</td>
    <td>
      <s:if test='isMorePlan=="Y"'>
       <s:textfield name="tour.outDate" readonly="true"></s:textfield>
    </s:if>
    <s:else>
       <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="tour.outDate">
       </sj:datepicker>
    </s:else>
    	<s:hidden name="oldOutDate"></s:hidden>
    </td>
    <td class="idx" width="15%">出境口岸：</td>
    <td>
    <s:select name="tour.outCity"
              list="portCitys"
              listKey="citycd"
              listValue="citynm"
              emptyOption="true">
    </s:select>
    </td>
  </tr>
  <tr>
    <td class="idx">入境日期：</td>
    <td>
      <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="tour.inDate">
      </sj:datepicker>
    </td>
    <td class="idx">入境口岸：</td>
    <td>
    <s:select name="tour.inCity"
              list="portCitys"
              listKey="citycd"
              listValue="citynm"
              emptyOption="true">
    </s:select></td>
  </tr>
  <tr>
    <td class="idx">聚合城市</td>
    <td colspan="3">
      <s:select name="tour.venue"
                list="portCitys"
                listKey="citycd"
                listValue="citynm"
                emptyOption="true">
      </s:select>
    </td>
  </tr>
	  <tr>
	    <td class="idx">总人数：</td>
	    <td colspan="3"><s:property value="tour.pax"/>人&nbsp;[男:<s:property value="tour.malePax"/>人、
	     女:<s:property value="tour.femalePax"/>人、领队:<s:property value="tour.leadPax"/>人]</td>
	  </tr>
  <tr>
    <td class="idx">订房数：</td>
    <td colspan="3">
      双人间:<s:textfield name="tour.doubleRoom" size="5"/>&nbsp;
      单人间:<s:textfield name="tour.singleRoom" size="5"/>&nbsp;
      加床:<s:textfield name="tour.extraBedRoom" size="5"/>
    </td>
  </tr>

  <tr>
    <td class="idx">建团人:</td>
    <td><s:property value="tour.creator"/>&nbsp;</td>
    <td class="idx">创建时间:</td>
    <td><s:date name="tour.dateCreated"/>&nbsp;</td>
  </tr>
  <tr>
    <td class="idx">修改人:</td>
    <td><s:property value="tour.opUser"/>&nbsp;</td>
    <td class="idx">修改时间:</td>
    <td><s:date name="tour.opDate"/>&nbsp;</td>
  </tr>

  <tr>
    <td class="idx">备注：</td>
    <td colspan="3"><s:textarea name="tour.remarks" cols="80" rows="4"/></td>
  </tr>

  <tr>
    <td colspan="4">
    <table bordercolor="#000000" border="0" cellpadding="0" cellspacing="1" width="100%">
        <tr>
          <td class="lstidx">No.</td>
          <td class="lstidx">姓名</td>
           <td class="lstidx">拼音</td>
          <td class="lstidx">性别</td>
          <td class="lstidx">出生日期</td>
          <td class="lstidx">出生地</td>
          <td class="lstidx">护照种类</td>
          <td class="lstidx">护照号码</td>
          <td class="lstidx">签发日期</td>
          <td class="lstidx">有效日期</td>
          <td class="lstidx">签发地点</td>
          <td class="lstidx">护照说明</td>
          <td class="lstidx">领队</td>
          <td class="lstidx">客户</td>
          <td class="lstidx">预订人</td>
          <td class="lstidx">备注</td>
        </tr>

	      <s:iterator value="tour.customerList" status="rowccount" id="cu">
        <tr>
          <td class="cdata"><s:property value="#rowccount.count"/></td>
          <td class="cdata">
            <a href="javascript:showName(form1,'0611X03618');" title="点击查看客人的详细内容！">
              <s:property value="userName"/>
            </a>
          </td>
          <td class="cdata"><s:property value="pinYin"/></td>
          <td class="data"><s:property value="sex"/>&nbsp;</td>
          <td class="cdata"><s:date name="birthday" format="yyyy-MM-dd"/>&nbsp;</td>
          <td class="data"><s:property value="birthplaceName"/>&nbsp;</td>
          <td class="data"><s:property value="passportType" />&nbsp;</td>
          <td class="data"><s:property value="passportNo"/>&nbsp;</td>
          <td class="cdata"><s:date name="passportDate" format="yyyy-MM-dd"/>&nbsp;</td>
          <td class="cdata"><s:date name="passportExpiry" format="yyyy-MM-dd"/>&nbsp;</td>
          <td class="data"><s:property value="passportPlaceName"/>&nbsp;</td>
          <td class="data"><s:date name="passportAnnotation"/>&nbsp;</td>
          <td class="data"><s:property value="leaderKey"/>&nbsp;</td>
          <td class="data"><s:property value="customerName"/>&nbsp;</td>
          <td class="data">
            <a href="javascript:ShowDetail('<s:property value='reserveNo'/>');" title="点击查看该订单的详细内容！">
              <s:property value="reserve"/>
            </a>
          </td>
          <td class="data"><s:property value="remarks"/></td>
        </tr>
        </s:iterator>

    </table>
    </td>
  </tr>
  <tr>
    <td colspan="4" align="right">
    <table width="100%">
      <tr>
        <td class="lstidx">操作</td>
        <td align="center" class="data">
        <input type="button" value="打印" onclick="javascript:generateXLS('<s:property value="tour.lineNo" />','<s:property value="tour.tourNo" />')">
        <input type="button" value="导出" onclick="javascript:generateXLS2('<s:property value="tour.lineNo" />','<s:property value="tour.tourNo" />')">
        <input type="button" value="保存" onclick="javascript:checkUpdate();">
        <input type="button" value="取消本团" onclick="javascript:checkCancel();">
        <input type="button" value="返回" onClick="javascript:history.go(-1);">
        
        </td>
        <td class="lstidx">可用操作流程</td>
        <td class="data">
        <s:iterator value="taskList" id="task">
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
  </td>
  </tr>
  <tr>
  	<td colspan="4"><br></br></td>
  </tr>
  <tr>
  	<td colspan="4"><STRONG>操作历史</STRONG>&nbsp;</td>
  </tr>
  <s:if test="tourLogList.size != 0">
	  <tr>
	  	<td colspan="4">
	  		<table cellpadding="2" cellspacing="0" border="0" width="100%" align="center">
	  			<tr>
            <td id="td_all" style="background-color: bbbbbb" width="1%" nowrap align="center">
                &nbsp;<b><a id="a_all" href="####" style="color:#FFFFFF ; text-decoration: none" onclick="javascript:tourLogAll()">所有</a></b>&nbsp;
            </td>
            <td id="td_note" width="1%" nowrap align="center">
                &nbsp;<b><a id="a_note" href="####" onclick="javascript:tourNote()">备注</a></b>&nbsp;
            </td>
            <td id="td_modifiedLog" width="1%" nowrap align="center">
                &nbsp;<b><a id="a_modifiedLog" href="####" onclick="javascript:tourInfo()">改动记录</a></b>&nbsp;
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
	  		<table border="1" width="100%" bordercolor="bbbbbb">
	  			<tr>
	  				<td>
				  		<div id="div_tourInfo"  style="display: none;">
				  			<s:iterator value="tourLogList" status="rowccount">
						  			<table cellpadding="2" cellspacing="1" border="0" width="100%">
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
				  		<div id="div_tourNote"  style="display: none;">
				  			<s:iterator value="tourLogList" status="rowccount">
						  		<table cellpadding="2" cellspacing="1" border="0" width="100%">
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
				  		
				  		<div id="div_AllTourInfo">
				  			<s:iterator value="tourLogList" status="rowccount">
						  			<table cellpadding="2" cellspacing="1" border="0" width="100%">
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

<s:form action="jxlsReport" namespace="/" method="POST" theme="simple">	
  <s:hidden name="parameters(0).name" value="ROUTE_NO"></s:hidden>
	<s:hidden id="paramid" name="parameters(0).data"></s:hidden>
	<s:hidden name="parameters(1).name" value="TOUR_NO"></s:hidden>
	<s:hidden id="paramid1" name="parameters(1).data"></s:hidden>
		
	<s:hidden name="reportId" value="22"></s:hidden>
</s:form> 

<s:form action="jxlsReport2" namespace="/" method="POST" theme="simple">	
  <s:hidden name="parameters(0).name" value="ROUTE_NO"></s:hidden>
	<s:hidden id="paramid2" name="parameters(0).data"></s:hidden>
	<s:hidden name="parameters(1).name" value="TOUR_NO"></s:hidden>
	<s:hidden id="paramid3" name="parameters(1).data"></s:hidden>
		
	<s:hidden name="reportId" value="23"></s:hidden>
</s:form>
</body>
</html>

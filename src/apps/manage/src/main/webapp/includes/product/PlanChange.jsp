<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改产品计划</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="修改产品计划">
</head>

<body>
<script language="JavaScript">
function cancel(){
  var fm = document.editPlan;
  fm.action = "<s:url action='showRoutePlan' namespace='/product'/>"
  fm.submit();
}

function change()
{
  var pax1 = document.getElementById("plan_pax1");
  var pax1_value = new Number(pax1.value);
  var pax2_value = new Number(document.getElementById("plan_pax2").value);
  var pax3 = document.getElementById("plan_pax3");
  var pax3_value = new Number(pax3.value);
  var pax4 = document.getElementById("plan_pax4");
  var pax4_value = new Number(pax4.value);
  
  if (pax4_value < 0)
  {
    pax4_value = 0;
    pax4.value = 0;
  }

  if (pax1_value < pax4_value)
  {
    pax1.value = pax4_value;
    pax1_value = pax4_value;
  }
  
  pax3.value = pax1_value - pax2_value - pax4_value;
}

function changePax5()
{
  var pax5 = document.getElementById("plan_pax5");
  var pax5_value = pax5.value;
  
  if (pax5_value < 1)
  {
    pax5.value = 1;
  }
}

function SubmitDeleteFlight(param, target)
{
  var fm = document.editPlan;
  fm.idx.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='editPlanDelFlight' namespace='/product'/>"
  }
  fm.submit();
}

function share(key)
{
    var show = document.getElementById("shareShow");
	if(key == "Y")
	{
		show.style.display = "block";
	}
	else if(key == "N")
	{
		show.style.display = "none";
	}
}


function planLogAll()
{
	document.getElementById("div_AllPlanInfo").style.display="";
	document.getElementById("div_planInfo").style.display="none";
	document.getElementById("div_planNote").style.display="none";
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

function planNote()
{
	document.getElementById("div_AllPlanInfo").style.display="none";
	document.getElementById("div_planInfo").style.display="none";
	document.getElementById("div_planNote").style.display="";
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

function planInfo()
{
	document.getElementById("div_AllPlanInfo").style.display="none";
	document.getElementById("div_planInfo").style.display="";
	document.getElementById("div_planNote").style.display="none";
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

function change()
{
  var combo = document.getElementById("userId")
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
      data: {groupId: $("#productTeamId").val()},
      async: false,
      success: function(data){
          $.each(data, function(i, n){

          if (typeof(n)=='object')
          {
              combo.options.add(new Option("全部", ""));
              
              $.each(n,function(j,m){
                combo.options.add(new Option(m, j));
              });
          }
      });
      }
  });
}

</script>

<s:form action="editPlan" method="POST" namespace="/product" theme="simple">
<s:hidden name="kenRrouteName"></s:hidden>
<s:hidden name="kenDepartment"></s:hidden>
<s:hidden name="kenPrincipal"></s:hidden>
<s:hidden name="kenStartDate"></s:hidden>
<s:hidden name="kenEndDate"></s:hidden>

<s:hidden name="plan.opDate"></s:hidden>
<s:hidden name="plan.recordNo"></s:hidden>
<s:hidden name="plan.line.lineNo"></s:hidden>
<s:hidden name="plan.outDate"></s:hidden>
<s:hidden name="recordNo" value="%{plan.recordNo}"></s:hidden>

<s:hidden name="note"></s:hidden>

  <table cellpadding="2">
    <tr>
      <td class="idx">线路名：</td>
      <td colspan="3"><s:property value="plan.line.lineName"/></td>
    </tr>
    <tr>
      <td class="idx">出发日期：</td>
      <td colspan="3"><s:date name="plan.outDate" format="yyyy-MM-dd"/></td>
    </tr>
    <tr>
      <td class="idx">旅游团队编号（团号）<span class="required">*</span>：</td>
      <td colspan="3" class="data">
      <s:textfield name="plan.tourNo"
                   size="80"
                   maxlength="100">
      </s:textfield>
      </td>
    </tr>
    <tr>
      <td class="idx">旅游团队名称:</td>
      <td colspan="3" class="data">
      <s:textfield name="plan.tourNm"
                   size="80"
                   maxlength="100">
      </s:textfield>
      </td>
    </tr>
    <tr>
      <td class="idx">工作组(操作)</td>
      <td class="data">
        <s:select id="productTeamId"
                  name="plan.team.teamId"
                  list="teamList"
                  listKey="teamId"
                  listValue="name"
                  required="true"
                  requiredposition="right"
                  onchange="javascript:change();">
        </s:select>
      </td>
      <td class="idx">操作员</td>
      <td class="data">
        <s:select id="userId"
                  name="plan.assigned.userId"
                  list="employeeList"
                  listKey="userId"
                  listValue="userName"
                  required="true"
                  requiredposition="right">
        </s:select>
      </td>
    </tr>
    <s:if test='#application["resource.shared"] eq "true"'>
    <tr>
    	<td class="idx">是否共享</td>
    	<td colspan="3">
    		<s:radio name="plan.shareFlight"
    		    		 list="shareStateList"
    		    		 listKey="value"
    		    		 listValue="label"
    		    		 onclick="javascript:share(this.value);changeShareRadio(this.value);">
		    </s:radio>
    	</td>
    </tr>
    <tr>
    <td colspan="4">
      <table id="shareShow">
  	    <tr>
  	    <td>
  	    <table>
  	    <tr>
  	    	<td><input type="radio" name="plan.selectNO" value="N" checked="checked">该计划共享</td>
  	    </tr>
        <tr>
          <td>航空公司</td>
          <td>航班号</td>
          <td>出发日期</td>
          <td>座位数</td>
          <td>可操作</td>
          <td>备注</td>
        </tr>
        <tr>
  	   		<td>
  	    		<s:select name="plan.airwaysCode"
                      list="airways"
                      listKey="code"
                      listValue="name"
                      emptyOption="true">
          		</s:select>
  	    	</td>
  	    	<td>
  	    		<s:textfield size="6" name="plan.flightNo"></s:textfield>
  	    	</td>
  	    	<td>
  	    		<sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="plan.departureDate">
            </sj:datepicker>
  	    	</td>
  	    	<td>
  	    		<s:textfield  size="6" id="plan_seating" name="plan.seating">
            </s:textfield>
  	    	</td>
  	    	<td>
  	    		<s:textfield  size="6" id="plan_handle" name="plan.handle">
            </s:textfield>
  	    	</td>
  	    	<td>
  	    		<s:textfield  size="15" name="plan.note">
            </s:textfield>
  	    	</td>
  	   	</tr>
  	   	</table>
  	   	</td>
  	   	</tr>
  	   	<tr>
  	   	<td>
  	   	<table>
  	   	<tr>
  	    	<td><input id="o" type="radio" name="plan.selectNO" value="O">选择已有共享</td>
  	    </tr>
  	    <tr>
  	    	<td>
  	    		<s:select name="shareId"
      	    				  list="shareList"
      	    				  listKey="label"
    	    			      listValue="value"
    	    			      onclick="javascript:check()">
  	    		</s:select>
  	    	</td>
  	   	</tr>
      </table>
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </s:if>
    <tr>
      <td class="idx">可收<span class="required">*</span>：</td>
      <td class="data">
      <s:textfield id="plan_pax1"
                   name="plan.planPax"
                   size="4"
                   maxlength="4"
                   onchange="javascript:change();">
      </s:textfield>
      </td>
      <td class="idx">已收：</td>
      <td class="data" colspan="3">
      <s:textfield id="plan_pax2"
                   name="plan.pax2"
                   size="5"
                   maxlength="5"
                   readonly="true">
      </s:textfield>
      </td>
    </tr>
    <tr>
      <td class="idx">可用名额：</td>
      <td class="data">
      <s:textfield id="plan_pax3"
                   name="plan.pax3"
                   size="5"
                   maxlength="5"
                   readonly="true">
      </s:textfield>
      </td>
      <td class="idx">占位<span class="required">*</span>：</td>
      <td class="data">
      <s:textfield id="plan_pax4"
                   name="plan.holdPax"
                   size="5"
                   maxlength="5"
                   onchange="javascript:change();">
      </s:textfield>
      </td>
    </tr>
    <tr>
      <td class="idx">最小成团人数<span class="required">*</span>：</td>
      <td class="data">
      <s:textfield name="plan.buildMinPax"
                   size="5"
                   maxlength="5"
                   onchange="javascript:changePax5();">
      </s:textfield>
      </td>
      <td class="idx">报名截止日期<span class="required">*</span>：</td>
      <td class="data">
      <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="plan.deadline">
      </sj:datepicker>
      </td>
    </tr>
    <tr>
      <td class="idx">是否公开<span class="required">*</span>：</td>
      <td class="data">
      <s:radio name="plan.deployFlag"
               list="deploys"
               listKey="value"
               listValue="label">
      </s:radio>
      </td>
    	<td class="idx">团队性质</td>
    	<td class="data">
    	<s:radio name="plan.singleFlag"
    			 list="singleFlag" 
    			 listKey="value"
           listValue="label"/>
    	</td>
    </tr>
    <tr>
      <td class="idx"><span class="required">推荐方式 *</span>:</td>
      <td colspan="3">
        <s:select name="plan.traitId"
                  list="traitList"
                  listKey="label"
                  listValue="value"
                  headerKey="0"
                  headerValue="普通">
        </s:select>
      </td>
    </tr>
    <tr>
      <td class="idx">特惠线路<span class="required">*</span>：</td>
      <td class="data">
      <s:radio name="plan.favourable"
               list="favourables"
               listKey="value"
               listValue="label">
      </s:radio>
      </td>
    </tr> 
    <tr>
      <td class="idx">控制名额否<span class="required">*</span>:</td>
      <td class="data">
      <s:radio list="paxkey" 
           name="plan.paxkey" 
           listKey="value" 
           listValue="label"/>
      </td>
    </tr>
    <tr>
      <td class="idx">备注：</td>
      <td class="data" colspan="3">
      <s:textfield name="plan.remarks" maxLength="60" size="60">
      </s:textfield>
      </td>
    </tr>
    <tr>
      <td class="idx">弹出提醒：</td>
      <td class="data" colspan="3">
      <s:textarea name="plan.message"
                  cols="50"
                  rows="4">
      </s:textarea>
      </td>
    </tr>
  </table>
  
  <table cellspacing="2">
    <tr>
      <td colspan="8" class="header">选择线路报价</td>
    </tr>
    <tr>
      <td class="lstidx">选择</td>
      <td class="lstidx">默认</td>
      <td class="lstidx">描述</td>
      <td class="lstidx">同行价</td>
      <td class="lstidx">直客价</td>
      <td class="lstidx">成本价</td>
      <td class="lstidx">单人房差</td>
      <td class="lstidx">有效期</td>
    </tr>
    <s:if test="priceList.size > 0">
    <s:iterator value="priceList" status="rowcounter">
      <tr>
        <td class="cdata">
          <s:checkbox name="select"
                      fieldValue="%{recNo}"
                      value="%{select}">
          </s:checkbox>
        </td>
        <td class="cdata">
          <s:if test="defaultPrice == true">
          <input name="plan.packagePrice.recNo"
                 type="radio"
                 value="<s:property value="recNo"/>"
                 checked="checked">
           </s:if>
           <s:else>
          <input name="plan.packagePrice.recNo"
                 type="radio"
                 value="<s:property value="recNo"/>">
           </s:else>
        </td>
        <td class="cdata"><s:property value="type" /></td>
        <td class="cdata"><s:property value="priceOther" /></td>
        <td class="cdata"><s:property value="price" /></td>
        <td class="cdata"><s:property value="priceCost" /></td>
        <td class="cdata"><s:property value="priceContrast" /></td>
        <td class="cdata"><s:date name="startDate" format="yyyy-MM-dd"/>至<s:date name="endDate" format="yyyy-MM-dd"/></td>
      </tr>
    </s:iterator>
   </s:if>
   <s:else>当前日期下暂无报价</s:else>
   <tr>
     <td>&nbsp;</td>
   </tr>
  </table>

  <table border="0" style="width: 100%">
    <tr>
      <td align="center">
      <s:submit action="submitEditPlan"
                value="提 交"
                onclick='document.editPlan.note.value = window.prompt("备注","请认真填写更改原因");'>
      </s:submit>
      <input type="button" value="返 回" onclick="javascript:cancel()"></td>
    </tr>
  </table>
  <table border="0" style="width: 100%">
	  <tr>
	    <td colspan="4"><STRONG>操作历史</STRONG>&nbsp;</td>
	  </tr>
	  <s:if test="planLogList.size != 0">
    <tr>
    	<td colspan="4">
    		<table border="0" style="width: 100%" align="center">
    			<tr>
              <td id="td_all" style="background-color: bbbbbb" width="1%" nowrap align="center">
                  &nbsp;<b><a id="a_all" href="####" style="color:#FFFFFF ; text-decoration: none" onclick="javascript:planLogAll()">所有</a></b>&nbsp;
              </td>
              <td id="td_note" width="1%" nowrap align="center">
                  &nbsp;<b><a id="a_note" href="####" onclick="javascript:planNote()">备注</a></b>&nbsp;
              </td>
              <td id="td_modifiedLog" width="1%" nowrap align="center">
                  &nbsp;<b><a id="a_modifiedLog" href="####" onclick="javascript:planInfo()">改动记录</a></b>&nbsp;
              </td>
              <td>&nbsp;</td>
            </tr>
        </table>
      </td>
    </tr>
		  
	  <tr>
	  	<td colspan="4">
  		<table border="1" style="width: 100%" bordercolor="bbbbbb">
  			<tr>
  				<td>
		  		<div id="div_planInfo"  style="display: none;">
		  			<s:iterator value="planLogList" status="rowccount">
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
				      </table>
			      </s:iterator>
		  		</div>
		  		<div id="div_planNote"  style="display: none;">
		  			<s:iterator value="planLogList" status="rowccount">
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
		  		
		  		<div id="div_AllPlanInfo">
	  			<s:iterator value="planLogList" status="rowccount">
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

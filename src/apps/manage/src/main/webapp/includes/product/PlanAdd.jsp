<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加产品计划</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="添加产品计划">
</head>

<body>
<script type="text/javascript">
<!--//

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
  var fm = document.submitPlanAdd;
  fm.idx.value=target;
  if (param =='delete')
  {
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='addPlanDelFlight' namespace='/product'/>"
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

  function changeShareRadio(key)
  {
  	if(key == "Y")
  	{
		  var sdate = document.getElementById("sDate");
		  var call = new WS.Call('<s:url value="/services/ShareService" encode="false" includeParams="none"/>'); 
		  var nsuri = 'http://www.opentravelsoft.com';
		  var qn_op = new WS.QName('roSearchShare',nsuri);
		  call.invoke_rpc(
		    qn_op,
		    new Array(
		      {name:'name',value:sdate}
		    ),null,
		    function(call,envelope) {
		      var ret = envelope.get_body().get_all_children()[0].get_all_children()[0].get_value();
		       var arrList = ret.split(',');
		       if(arrList.length > 0)
		      	{
		        var kOption = document.createElement('OPTION');
		        kOption.text = "";
		        kOption.value = "";
		        combo.options.add(kOption);
		
		        for(var i=0;i<empls.length -1;i=i+2)
		        {
		          var oOption = document.createElement('OPTION');
		          oOption.text = empls[i+1];
		          oOption.value = empls[i];
		          combo.options.add(oOption);
		        }
		      }
		    }
		  );
	  }
  }

function check()
{
	var o = document.getElementById("o");
	o.checked ="checked";
}

function selectAll()
{
	var a = document.all.weekKey1.checked=true;
	var b = document.all.weekKey2.checked=true;
	var c = document.all.weekKey3.checked=true;
	var d = document.all.weekKey4.checked=true;
	var e = document.all.weekKey5.checked=true;
	var f = document.all.weekKey6.checked=true;
  var g = document.all.weekKey7.checked=true;  
}

function changeEmp()
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
//-->
</script>

<s:form action="submitPlanAdd" namespace="/product" method="POST" theme="simple">
  <s:hidden name="plan.outCity.citycd" value="%{plan.line.outCity.citycd}"></s:hidden>
  <table cellpadding="2">
    <tr>
      <td class="idx">出发日期<span class="required">*</span>:</td>
      <td colspan="3">
      <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" id="sDate"
      					     name="plan.startDate"
                     minDate="%{startDate}">
      </sj:datepicker> 至 
      <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" id="eDate"
      					     name="plan.endDate"
      					     minDate="%{startDate}">
      </sj:datepicker>
      </td>
    </tr>
    <tr>
      <td class="idx">周期表:</td>
      <td colspan="3">
       星期一 <s:checkbox name="weekKey1"></s:checkbox>&nbsp;
       星期二 <s:checkbox name="weekKey2"></s:checkbox>&nbsp;
       星期三 <s:checkbox name="weekKey3"></s:checkbox>&nbsp; 
       星期四 <s:checkbox name="weekKey4"></s:checkbox>&nbsp;
       星期五 <s:checkbox name="weekKey5"></s:checkbox>&nbsp;
       星期六 <s:checkbox name="weekKey6"></s:checkbox>&nbsp; 
       星期日 <s:checkbox name="weekKey7"></s:checkbox>&nbsp;
       <input type="button" onclick="javascript:selectAll()" value="全选">
      </td>
    </tr>
    <tr>
      <td class="idx">旅游团队编号（团号）<span class="required">*</span>:</td>
      <td colspan="3"><s:textfield name="plan.tourNo" maxLength="100" size="80" /></td>
    </tr>
    <tr>
    	<td class="idx">旅游团队名称:</td>
    	<td colspan="3"><s:textfield name="plan.tourNm" maxlength="100" size="80"/></td>
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
                  onchange="javascript:changeEmp();">
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
            <s:textfield size="6" id="plan_seating" name="plan.seating">
            </s:textfield>
          </td>
          <td>
            <s:textfield size="6" id="plan_handle" name="plan.handle">
            </s:textfield>
          </td>
          <td>
            <s:textfield size="15" name="plan.note">
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
      <td class="idx">可收<span class="required">*</span>:</td>
      <td colspan="3">
      <s:textfield id="plan_pax1"
                   name="plan.planPax"
                   size="5"
                   maxlength="5" 
                   onchange="javascript:change();" />&nbsp;&nbsp;
                 已收:&nbsp;<s:textfield id="plan_pax2"
                    name="plan.pax2"
                    size="5" maxlength="5"
                    readonly="true" />&nbsp;&nbsp;
                 剩于名额:&nbsp;<s:textfield id="plan_pax3"
                    name="plan.pax3"
                    size="5" maxlength="5"
                    readonly="true" />&nbsp;&nbsp;
                  占位<span class="required">*</span>:&nbsp;
                  <s:textfield id="plan_pax4" 
                  name="plan.holdPax"
                  size="5" maxlength="5"
                  onchange="javascript:change();"/>&nbsp;&nbsp;
                   最小成团人数<span class="required">*</span>:&nbsp;
                 <s:textfield id="plan_pax5"
                 name="plan.pax5"
                 size="5" maxlength="5"
                 onchange="javascript:changePax5();"/>
      </td>
    </tr>
    <tr>
      <td class="idx">报名截止天数<span class="required">*</span>:</td>
      <td colspan="3"><s:textfield name="deadNum" maxLength="2" size="2" /></td>
    </tr>
    <tr>
      <td class="idx">是否开放<span class="required">*</span>:</td>
      <td>
        <s:radio name="plan.deployFlag"
                 list="deploys"
                 listKey="value"
                 listValue="label"/>
      </td>
      <td class="idx">旅游团队性质</td>
      <td>
        <s:radio name="plan.singleFlag"
                 list="singleflag"
                 listKey="value"
                 listValue="label">
        </s:radio>
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
    <!--  <tr>
      <td class="idx">特惠线路<span class="required">*</span>:</td>
      <td colspan="3">
        <s:radio name="plan.favourable"
                 list="favourables"
                 listKey="value"
                 listValue="label" />
      </td>
    </tr>-->
    <!-- 
    <tr>
      <td class="idx">控制名额否<span class="required">*</span>:</td>
      <td colspan="3"><s:radio list="paxkey" 
             name="plan.paxkey"
             listKey="value"
             listValue="label"/>
      </td>
    </tr>
     -->
    <tr>
      <td class="idx">备注:</td>
      <td colspan="3">
        <s:textfield name="plan.remarks"
                     maxLength="60"
                     size="60">
        </s:textfield>
      </td>
    </tr>
    <!-- 
    <tr>
      <td class="idx">弹出提醒:</td>
      <td colspan="3"><s:textarea name="plan.message" cols="50" rows="10"></s:textarea></td>
    </tr>
     -->
  </table>

  <s:hidden name="idx" />

  <table style="width: 100%" align="center">
    <tr>
      <td height="25" colspan="2"><font color="#0000FF"><strong>注意事项：
</strong></font></td>
    </tr>
    <tr>
      <td width="17" height="25"><strong><font color="#0000FF">1.</font></strong></td>
      <td width="731"><strong><font color="#0000FF"> 当需要一次制定多天的计划时，请输入第二
个日期，且第二个出发日期要求大于第一个， 他们之间的差为要制定的计划天数！
</font></strong></td>
    </tr>
    <tr>
      <td height="25"><strong><font color="#0000FF">2. </font></strong></td>
      <td height="25"><strong><font color="#0000FF">使用周期表，可以将出发日期制订到一周中的
某一天！</font></strong></td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <s:submit value="提交"></s:submit>
        <s:submit action="showRoutePlan" value="返回"></s:submit>
      </td>
    </tr>
  </table>
</s:form>
</body>
</html>

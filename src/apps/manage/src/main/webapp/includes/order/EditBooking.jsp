<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改订单</title>
<meta name="menu" content="OrderMenu"/>
<meta name="heading" content="修改订单">
<s:head/>
</head>

<body>
<script type="text/javascript">

function birthPlaceChanged(param1, param2) {
	$.ajax({
	   type: "post",
	   url: '<s:url value="/json/HomePlace.jspa" encode="false" includeParams="none"/>' ,
	   timeout: 20000,
	   error: function(){
	      // alert('服务器错误');
	   },
	   async: false,
	   success: function(data){
	       $.each(data, function(i, n){
	       if (i == 'map')
	       {
	         $("#" + param2).autocomplete(n, {
	             width: 160,
	             highlight: false,
	             matchContains: true,
	             minChars: 0,
	             scroll: true,
	             scrollHeight: 300,
	             formatItem: function(row, i, max) {
	               return  row.label +"->["+ row.value + "]";
	             },
	             formatMatch: function(row, i, max) {
	               return row.label;
	             },
	             formatResult: function(row, value) {
	               return row.label;
	             }
	           }).result(function(event, row) {
	             $("#" + param1).val(row.value);
	           });
	       }
	   });
	 }
	});
}

function changePax()
{
  var fm = document.submitEditBook;
  fm.action = "<s:url action='changeEditBookPax' namespace='/sales'/>";
  fm.submit();
}

//省份修改时，刷新客户列表(autocomplate)
function customerChanged(param) {
  $.ajax({
      type: "post",
      url: '<s:url value="/json/getAccountsByRegion.jspa" encode="false" includeParams="none"/>' ,
      timeout: 20000,
      data: {province: $("#regionCode").val()},
      error: function(){
         // alert('服务器错误');
      },
      async: false,
      success: function(data){
          $.each(data, function(i, n){
          if (i == 'map')
          {
            $("#agentList_name").autocomplete(n, {
                width: 320,
                max: 4,
                highlight: false,
		        		matchContains: true,
		        		minChars: 0,
                scroll: true,
                scrollHeight: 300,
                formatItem: function(row, i, max) {
                  return  row.value +"->["+ row.label + "]";
                },
                formatMatch: function(row, i, max) {
                  return row.value;
                },
                formatResult: function(row, value) {
                  return row.value;
                }
              }).result(function(event, row) {
                $("#agent_id").val(row.label);
                //changeAgent();
              });
          }
      });
    }
  });
  $("#agentList_name").focus();
}

//客户修改， 刷新联系人、联系人电话
function changeAgent()
{
  $("#contactName").focus();
  $.ajax({
      type: "post",
      url: '<s:url value="/json/getContacts.jspa" encode="false" includeParams="none"/>' ,
      timeout: 20000,
      data: {accountId: $("#agent_id").val()},
      error: function(){
         // alert('服务器错误');
      },
      async: false,
      success: function(data){
          $.each(data, function(i, n){
          if (i == 'map')
          {
            $("#contactName").autocomplete(n, {
                width: 140,
                max: 4,
                highlight: false,
		        		matchContains: true,
		        		minChars: 0,
                scroll: true,
                scrollHeight: 300,
                formatItem: function(row, i, max) {
                  return  row.name;
                },
                formatMatch: function(row, i, max) {
                  return row.name;
                },
                formatResult: function(row, value) {
                  return row.name;
                }
              }).result(function(event, row) {
                //$("#contact_id").val(row.contactId);
                $("#contactTel").val(row.phone);
                $("#salesman").val(row.salesId);
              });
          }
      });
    }
  });
}

function changePrice(param)
{
  var fm = document.submitOrder;
  var price = document.getElementById('submitEditBook_'+ param + '_price')
  receivables = document.getElementById('submitEditBook_'+ param + '_receivables')
  receivables.value = price.value;
}

function pinyin(param)
{
  $.ajax({
      type: "post",
      url: '<s:url value="/json/getPinyin.jspa" encode="false" includeParams="none"/>' ,
      timeout: 20000,
      data: {name: $("#customerName"+param).val()},
      error: function(){
          // alert('服务器错误');
      },
      async: false,
      success: function(data){
          $.each(data, function(i, n){

          if (i == 'pinyin')
          {
        	  $("#pinYin"+param).val(n);
          }
      });
      }
  });
}

function keyDown(event, target)
{
  var arr =('customerName', 'pinYin', 'idCard');
  var code=event.keyCode;
  var objname = target.substring(0, target.length - 1);
  var next = new Number(target.substr(target.length - 1));

  if (code == 40)
  {
    next = next + 1;
    document.getElementById(objname + next).focus();
    document.getElementById(objname + next).select();
  } else if(code == 38)
  {
    next = next -1;
    document.getElementById(objname + next).focus();
    document.getElementById(objname + next).select();
  } else if (code == 39)
  {
    if (objname =='customerName')
    {
      document.getElementById('pinYin' + next).focus();
      document.getElementById('pinYin' + next).select();
    } else if (objname =='pinYin')
    {
      document.getElementById('idCard' + next).focus();
      document.getElementById('idCard' + next).select();
    }
  } else if (code == 37)
  {
    if (objname =='pinYin')
    {
      document.getElementById('customerName' + next).focus();
      document.getElementById('customerName' + next).select();
    } else if (objname =='idCard')
    {
      document.getElementById('pinYin' + next).focus();
      document.getElementById('pinYin' + next).select();
    }
  }
} 

function editBook()
{
	var fm = document.submitEditBook;
	fm.action = "<s:url action='submitEditBook' namespace='/sales'/>"
	if(confirm("确定修改？") == false)
		return;
	fm.note.value=window.prompt("备注","请认真填写修改原因");
	fm.submit();
}
</script>

<s:form action="submitEditBook" namespace="/sales" method="post" theme="simple">
	<!-- 订单NAME_NO -->
	<s:hidden name="reserveNo"></s:hidden>
	<s:hidden name="book.bookingNo"></s:hidden>
	<!-- 出团计划REC_NO -->
	<s:hidden name="recordNo" value="%{book.plan.planNo}"></s:hidden>
	<s:hidden name="book.plan.planNo"></s:hidden>
	<s:hidden name="note"></s:hidden>

	<table style="width: 100%">
		<tr>
			<td class="tdLabel"><label class="label">线路:</label></td>
			<td><s:property value="book.plan.line.lineName" /></td>
		</tr>
		<tr>
			<td class="tdLabel"><label class="label">团号:</label></td>
			<td><s:property value="book.plan.tourNo" /></td>
		</tr>
		<tr>
			<td class="tdLabel"><label class="label">出发日期:</label></td>
			<td><s:date name="book.plan.outDate" format="yyyy-MM-dd" /></td>
		</tr>

		<tr>
			<td class="tdLabel" width="100"><label class="label">省份:</label></td>
			<td width="200">
        <s:select id="regionCode" 
								  name="book.customer.provinceCd"
								  list="regions" 
								  listKey="label" 
								  listValue="value" 
								  emptyOption="true"
								  onchange="javascript:customerChanged();">
  			</s:select>
      </td>
			<td class="tdLabel" width="100">
        <label class="label">代理商:</label>
        <span	class="required">*</span>
      </td>
			<td>
	      <s:hidden id="agent_id" name="book.customer.customerId"></s:hidden>
				<s:textfield id="agentList_name" 
	      						 name="book.customer.name"
	      						 onchange="javascript:changeAgent();" 
	      						 size="50">
				</s:textfield>
      </td>
		</tr>

		<tr>
			<td class="tdLabel"><label class="label">联系人:</label></td>
			<td colspan="3">
        <s:textfield id="contactName"
              			 name="book.contact"
                     size="20"
                     maxlength="20"
                     required="true"
              			 requiredposition="right">
			  </s:textfield>
      </td>
		</tr>

		<tr>
			<td class="tdLabel"><label class="label">联系电话:</label></td>
			<td colspan="3">
  			<s:textfield id="contactTel"
            			   name="book.phone"
                     size="60"
                     maxlength="60"
                     required="true"
            			   requiredposition="right">
  			</s:textfield>
      </td>
		</tr>

		<tr>
			<td class="tdLabel"><label class="label">参团要求:</label></td>
			<td colspan="3">
        <s:radio name="book.canSplit"
        			   list="canSplitList"
                 listKey="value"
                 listValue="label">
  			</s:radio>
      </td>
		</tr>

		<tr>
			<td class="tdLabel"><label class="label">订单状态:</label></td>
			<td>
        <s:radio list="confirmStatusList"
                 listKey="value"
          			 listValue="label"
                 value="%{book.confirmStatus}"
                 disabled="true">
			  </s:radio>
        <s:hidden name="book.confirmStatus"></s:hidden>
      </td>
		</tr>

		<tr>
			<td class="tdLabel">
        <label class="label">预订人数:</label>
        <span class="required">*</span>
      </td>
			<td colspan="3">
      <s:textfield name="book.pax"
                   size="3"
          				 maxlength="3"
                   onchange="javascript:changePax();"
                   required="true"
          				 requiredposition="right">
			</s:textfield>
      </td>
		</tr>

		<tr>
			<td class="tdLabel">
        <label class="label">销售员:</label>
        <span class="required">*</span>
      </td>
			<td colspan="3">
      <s:select id="salesman"
                list="salesmans"
                name="book.salesman.userId"
			          listKey="userId"
                listValue="userName"
                emptyOption="true">
			</s:select>
      </td>
		</tr>
		<tr>
			<td class="tdLabel"><label class="label">备注:</label></td>
			<td colspan="3">
        <s:textarea name="book.remarks"
                    cols="100"
          				  rows="4">
			  </s:textarea>
      </td>
		</tr>
	</table>

	<br />

	<table style="width: 100%">
		<tr>
			<td class="lstidx">No.</td>
			<td class="lstidx" nowrap="nowrap">状态</td>
			<td class="lstidx">姓名<span class="required">*</span></td>
			<td class="lstidx">拼音</td>
			<td class="lstidx">身份证</td>
			<td class="lstidx">性别</td>
			<td class="lstidx">出生日期</td>
			<td class="lstidx">出生地</td>
			<td class="lstidx">护照号</td>
			<td class="lstidx">签发机关</td>
			<td class="lstidx">签发日期</td>
			<td class="lstidx">签证有效期</td>
			<td class="lstidx">住房要求</td>
			<td class="lstidx">同住序号</td>
			<td class="lstidx">报价</td>
			<td class="lstidx">应收团款</td>
			<td class="lstidx">报价说明</td>
		</tr>
		<s:iterator value="customerList" status="rowcounter">
			<tr>

				<!-- 一个客人的信息开始 序号 -->
				<td align="center">
        <s:property value="#rowcounter.count" />
        <s:hidden name="customerList(%{id}).bookingNo" value="%{book.bookingNo}"></s:hidden>
				<s:hidden name="customerList(%{id}).nmno" value="%{nmno}"></s:hidden>
				<s:hidden name="customerList(%{id}).del" value="%{del}"></s:hidden>
				</td>

				<!-- 状态 -->
				<td align="center">
        <s:if test="del eq \"Y\"">
					<span class="required">取消</span>
				</s:if></td>

				<!-- 姓名 -->
				<td>
        <s:textfield id="customerName%{id}"
            				 name="customerList(%{id}).userName"
                     value="%{userName}"
            				 size="8"
                     maxlength="10"
            				 onchange="javascript:pinyin('%{id}');"
            				 onkeydown="javascript:keyDown(event,'customerName%{id}');">
				</s:textfield></td>

				<!-- 拼音 -->
				<td>
        <s:textfield id="pinYin%{id}"
            				 name="customerList(%{id}).pinYin"
                     value="%{pinYin}"
                     size="18"
            				 maxlength="18"
            				 ondblclick="javascript:pinyin('%{id}');"
            				 onkeydown="javascript:keyDown(event, 'pinYin%{id}');">
				</s:textfield></td>

				<!-- 身份证号码 -->
				<td>
          <s:textfield id="idCard%{id}"
              				 name="customerList(%{id}).idCard"
                       value="%{idCard}"
                       size="18"
              				 maxlength="18"
              				 onkeydown="javascript:keyDown(event, 'idCard%{id}');">
				</s:textfield></td>

				<!-- 性别 -->
				<td>
				<s:select name="customerList(%{id}).sex"
				          list="sexList"
				        	listKey="label"
                  listValue="value"
                  value="%{sex}">
				</s:select>
				</td>

				<!-- 出生日期 -->
				<td nowrap="nowrap" valign="middle">
        <sj:datepicker size="10" displayFormat="yy-mm-dd" name="customerList(%{id}).birthday"
                       value="%{birthday}"
                       maxlength="10">
				</sj:datepicker></td>

				<!-- 出生地  -->
				<td><s:hidden id="birthplace%{id}" name="customerList(%{id}).birthplace"></s:hidden>
				<s:textfield id="birthplaceName%{id}"
				             name="customerList(%{id}).birthplaceName"
                     onfocus="javascript:birthPlaceChanged('birthplace%{id}', 'birthplaceName%{id}')"
          					 size="16">
        </s:textfield>
        </td>

				<!-- 证件(护照)号码  -->
				<td>
        <s:textfield name="customerList(%{id}).passportNo"
				             value="%{passportNo}"
                     size="8"
                     maxlength="12">
				</s:textfield></td>

				<!-- 签发机关  -->
				<td><s:hidden id="passportPlace%{id}" name="customerList(%{id}).passportPlace"></s:hidden>
				<s:textfield id="passportPlaceName%{id}"
				             name="customerList(%{id}).passportPlaceName"
                     onfocus="javascript:birthPlaceChanged('passportPlace%{id}', 'passportPlaceName%{id}')"
          				   size="16">
        </s:textfield></td>

        <!-- 护照签发日期 -->
        <td nowrap="nowrap" valign="middle">
          <sj:datepicker size="10" displayFormat="yy-mm-dd" name="customerList(%{id}).passportDate"
                         value="%{passportDate}"
                         maxlength="10">
          </sj:datepicker>
        </td>
        
				<!-- 护照有效期 -->
				<td nowrap="nowrap" valign="middle">
        <sj:datepicker size="10" displayFormat="yy-mm-dd" name="customerList(%{id}).passportExpiry"
		               value="%{passportExpiry}"
                       maxlength="10">
				</sj:datepicker>
       </td>

				<!-- 住房要求  -->
				<td>
        <s:select name="customerList(%{id}).roomType"
        					list="roomTypeList"
                  listKey="value"
                  listValue="label"
        					value="%{roomType}">
				</s:select></td>

				<!-- 同住序号  -->
				<td>
          <s:textfield name="customerList(%{id}).roomNo"
				               value="%{roomNo}"
                       size="4"
                       maxlength="4">
				</s:textfield></td>

				<!-- 报价 -->
				<td>
          <s:select name="customerList(%{id}).price"
                    list="priceList"
          					listKey="value"
                    listValue="label"
                    value="%{priceSTR}"
          					onchange="javascript:changePrice('customerList(%{id})')">
				  </s:select>
        </td>

				<!-- 应收团款 -->
				<td>
          <s:textfield name="customerList(%{id}).receivables"
			                 value="%{receivablesSTR}"
                       size="8"
                       maxlength="8">
				  </s:textfield>
        </td>

				<!-- 报价说明 -->
				<td>
          <s:textfield name="customerList(%{id}).remarks"
					             value="%{remarks}"
                       size="40"
                       maxlength="40">
				  </s:textfield>
        </td>

				<!-- 一个客人的信息结束 -->
			</tr>
		</s:iterator>

		<tr>
			<td colspan="14">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="14">
         <input type="button" value="提交" onclick="javascript:editBook();">&nbsp;
         <input type="button" value="后退" onclick="javascript:history.go(-1);">
      </td>
		</tr>
	</table>
</s:form>
</body>
</html>

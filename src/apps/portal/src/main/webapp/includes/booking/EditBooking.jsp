<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<title>添加订单</title>
</head>

<body>
<script type="text/javascript">
  
function changePax()
{
  var fm = document.submitEditBook;
  fm.action = "<s:url action='changeBookPax' namespace='/distribution'/>";
  fm.submit();
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
</script>

<s:form action="submitEditBook" namespace="/distribution" method="post">
  <s:hidden name="reserveNo"></s:hidden>
  <s:hidden name="book.bookingNo"></s:hidden>
  <s:hidden name="book.plan.planNo"></s:hidden>
  <s:hidden name="book.plan.tourNo"></s:hidden>

  <table width="760" align="center" border="0" cellpadding="0" cellspacing="1">
    <tr>
      <td colspan="2">修改预订资料</td>
    </tr>
    <tr>
      <td class="tdLabel"><label class="label">线路:</label></td>
      <td><s:property value="book.plan.line.lineName"/></td>
    </tr>
    <tr>
      <td class="tdLabel"><label class="label">团号:</label></td>
      <td><s:property value="book.plan.tourNo"/></td>
    </tr>
    <tr>
      <td class="tdLabel"><label class="label">出发日期:</label></td>
      <td><s:date name="book.plan.outDate" format="yyyy-MM-dd" /></td>
    </tr>
    
    <s:textfield label="联系人"
                 name="book.contact"
                 size="20"
                 maxlength="20"
                 required="true"
                 requiredposition="right">
    </s:textfield>
    
    <s:textfield label="联系电话"
                 name="book.phone"
                 size="60"
                 maxlength="60"
                 required="true"
                 requiredposition="right">
    </s:textfield>
   
    <s:radio label="参团要求"
              name="book.canSplit"
              list="canSplitList"
              listKey="value"
              listValue="label">
    </s:radio>

    <s:radio label="订单状态"
             name="book.confirmStatus"
             list="confirmStatusList"
             listKey="value"
             listValue="label"
             disabled="true">
    </s:radio>

    <s:select label="客户"
              list="agentList"
              listKey="label"
              listValue="value"
              name="book.customer.customerId"
              emptyOption="true">
    </s:select>
  
    <s:textfield label="预订人数"
                 name="book.pax"
                 size="3"
                 maxlength="3"
                 onchange="javascript:changePax();"
                 required="true"
                 requiredposition="right">
    </s:textfield>
  
    <s:textarea label="备注"
                 name="book.remarks"
                 cols="100"
                 rows="4">
    </s:textarea>
  </table>
  <br/>
  
  <table width="100%" border="0" cellpadding="0" cellspacing="1">
    <tr>
      <td class="lstidx">No.</td>
      <td class="lstidx">姓名<span class="required">*</span></td>
      <td class="lstidx">拼音</td>
      <td class="lstidx">身份证</td>
      <td class="lstidx">性别</td>
      <td class="lstidx">出生日期</td>
      <td class="lstidx">出生地</td>
      <td class="lstidx">护照号</td>
      <td class="lstidx">签发机关</td>
      <td class="lstidx">签发日期</td>
      <td class="lstidx">住房要求</td>
      <td class="lstidx">同住序号</td>
      <td class="lstidx">报价</td>
      <td class="lstidx">应收团款</td>
      <td class="lstidx">报价说明</td>
    </tr>
    <s:iterator value="customerList" status="rowcounter">
    <tr>
  
      <!-- 一个客人的信息开始 序号 -->
      <td>
        <s:property value="#rowcounter.count"/>
        <s:hidden name="customerList(%{id}).bookingNo" value="%{book.bookingNo}"></s:hidden>
        <s:hidden name="customerList(%{id}).nmno" value="%{nmno}"></s:hidden>
      </td>
      <!-- 姓名 -->
      <td>
      <s:textfield id="customerName%{id}"
                   name="customerList(%{id}).customer.userName"
                   value="%{customer.userName}"
                   size="8"
                   maxlength="10"
                   theme="simple"
                   onchange="javascript:pinyin('%{id}');">
      </s:textfield>
      </td>

      <!-- 拼音 -->
      <td>
      <s:textfield id="pinYin%{id}"
                   name="customerList(%{id}).pinYin"
                   value="%{pinYin}"
                   size="18"
                   maxlength="18"
                   theme="simple"
                   ondblclick="javascript:pinyin('%{id}');">
      </s:textfield>
      </td>

      <!-- 身份证号码 -->
      <td>
      <s:textfield name="customerList(%{id}).idCard"
                    value="%{idCard}"
                    size="18"
                    maxlength="18"
                    theme="simple">
      </s:textfield>
      </td>
  
      <!-- 性别 -->
      <td>
      <s:select name="customerList(%{id}).sex"
                list="sexList"
                listKey="label"
                listValue="value"
                value="%{sex}"
                theme="simple">
      </s:select>
      </td>
  
      <!-- 出生日期 -->
      <td nowrap="nowrap" valign="middle">
      <sj:datepicker size="10" displayFormat="yy-mm-dd" name="customerList(%{id}).birthday"
                     value="%{birthday}"
                     maxlength="10">
      </sj:datepicker>
      </td>
  
      <!-- 出生地  -->
      <td>
      <s:select name="customerList(%{id}).birthplace"
                 list="birthPlaceList"
                 listKey="label"
                 listValue="value"
                 value="%{birthplace}"
                 emptyOption="true"
                 theme="simple">
      </s:select>
      </td>

      <!-- 证件(护照)号码  -->
      <td>
      <s:textfield name="customerList(%{id}).passportNo"
                   value="%{passportNo}"
                   size="8"
                   maxlength="12"
                   theme="simple">
      </s:textfield>
      </td>
  
      <!-- 签发机关  -->
      <td>
      <s:select name="customerList(%{id}).passportPlace"
                list="passportPlaceList"
                listKey="label"
                listValue="value"
                value="%{passportPlace}"
                emptyOption="true"
                theme="simple">
      </s:select>
      </td>
  
      <!-- 签发日期 -->
      <td nowrap="nowrap" valign="middle">
      <sj:datepicker size="10" displayFormat="yy-mm-dd" name="customerList(%{id}).passportDate"
                     value="%{passportDate}"
                     maxlength="10">
      </sj:datepicker>
      </td>
  
      <!-- 住房要求  -->
      <td>
      <s:select name="customerList(%{id}).roomType"
                list="roomTypeList"
                listKey="value"
                listValue="label"
                value="%{roomType}"
                theme="simple">
      </s:select>
      </td>
  
      <!-- 同住序号  -->
      <td>
      <s:textfield name="customerList(%{id}).roomNo"
                   value="%{roomNo}"
                   size="4"
                   maxlength="4"
                   theme="simple">
      </s:textfield>
      </td>
  
      <!-- 报价 -->
      <td>
      <s:select name="customerList(%{id}).price"
                list="priceList"
                listKey="value"
                listValue="label"
                value="%{priceSTR}"
                onchange="javascript:changePrice('customerList(%{id})')"
                theme="simple">
      </s:select>
      </td>
  
      <!-- 应收团款 -->
      <td>
      <s:textfield name="customerList(%{id}).receivables"
                   value="%{receivablesSTR}"
                   size="8"
                   maxlength="8"
                   theme="simple">
      </s:textfield>
      </td>
  
      <!-- 报价说明 -->
      <td>
      <s:textfield name="customerList(%{id}).remarks"
                   value="%{remarks}"
                   size="40"
                   maxlength="40"
                   theme="simple">
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
      <s:submit value="提交" theme="simple"></s:submit>
      <s:reset value="清除" theme="simple"></s:reset>
      </td>
    </tr>
  </table>
</s:form>
</body>
</html>

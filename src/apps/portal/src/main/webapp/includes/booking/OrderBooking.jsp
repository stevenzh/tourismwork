<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title>填写旅游订单内容</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:head/>
</head>
<body>

<script type="text/javascript">
  
  function changePax()
  {
    var fm = document.submitOrderBooking;
    fm.action = "<s:url action='submitOrderBooking' namespace='/accounts' includeParams='none' method='changePax'/>";
    fm.submit();
  }
</script>

<s:form action="submitOrderBooking" namespace="/accounts" method="post">
  <s:hidden name="book.plan.planNo"></s:hidden>
  <table style="width: 100%" align="center" border="0" cellspacing="1">
    <tr>
      <td colspan="2" align="center">预订表单</td>
    </tr>
    <tr>
      <td class="tdLabel">线路:</td>
      <td><s:property value="book.plan.line.lineName" /></td>
    </tr>
    
    <tr>
      <td colspan="4"><b>行程安排</b></td>
    </tr>
    <tr>
      <td colspan="4">
住宿标准：无<br>
用餐标准：0早0正，餐标：0 <br>
交通标准：无<br>
景点门票：无<br>
导游服务：无
报价含：上海－吉隆坡往返机票，马来西亚签注<br>
报价不含：国际机票税金<br>
一人报价：2900+税金<br>
上海-吉隆坡 MH387 (1015/1530) 吉隆坡-上海 MH386 (0140/0700) <br>
MH389 (1535/2050) MH388 (0850/1410)
      </td>
    </tr>
    <tr>
      <td class="tdLabel">团号:</td>
      <td><s:property value="book.plan.tourNo" /></td>
    </tr>
    <tr>
      <td class="tdLabel">出发日期:</td>
      <td><s:date name="book.plan.outDate" format="yyyy-MM-dd" /></td>
    </tr>
    <tr>
      <td class="tdLabel">预订人数:</td>
      <td>
        <s:textfield name="book.pax"
								     size="3" maxlength="3"
								     onchange="javascript:changePax();"
								     required="true"
								     requiredposition="right"
								     theme="simple">
		    </s:textfield>
      </td>
    </tr>
    <tr>
      <td class="tdLabel">备注:</td>
      <td>
				<s:textarea name="book.remarks"
				            cols="50" rows="4">
				</s:textarea>
      </td>
    </tr>
  </table>
  
  <br/>
  <table style="width: 100%" align="center" border="0" cellspacing="1">
    <tr>
      <td class="lstidx">No.</td>
      <td class="lstidx">姓名<span class="required">*</span></td>
      <td class="lstidx">身份证<span class="required">*</span></td>
      <td class="lstidx">性别<span class="required">*</span></td>
    </tr>
    <s:iterator value="customerList" status="rowcounter">
    <tr>
  
      <!-- 一个客人的信息开始 序号 -->
      <td><s:property value="#rowcounter.count"/></td>
      <!-- 姓名 -->
      <td>
      <s:textfield name="customerList(%{id}).userName"
                    value="%{userName}"
                    size="8"
                    maxlength="10"
                    theme="simple">
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
  
      <!-- 一个客人的信息结束 -->
    </tr>
    </s:iterator>

    <tr>
      <td colspan="4">提示： 请您务必正确填写参团人员与乘机时出示的有效身份证件一致的姓名与号码。</td>
    </tr>
    <tr>
      <td colspan="4">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="4">
      <s:submit value="完成" method="submit" theme="simple"></s:submit>
      </td>
    </tr>
  </table>
</s:form>

</body>
</html>


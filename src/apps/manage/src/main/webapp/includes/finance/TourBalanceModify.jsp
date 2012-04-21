<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改单团核算单</title>
<meta name="menu" content="FinanceMenu"/>
<meta name="heading" content="修改单团核算单">
</head>

<body>
<script language="JavaScript">
<!--//

function DeleteRow(param)
{
   var fm=document.SingleTourBalanceModify;
   fm.id.value=param;
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='SingleTourBalanceModifyDelete' namespace='/finance'/>";
    fm.submit();
}


function change(para,param2)
{
   var num = document.getElementById(param2);
   if(isNaN(num.value))
   {
     alert("输入值只能为数字！");
     num.value="";
     num.focus();
     return;
   }
   var roe=document.getElementById('SingleTourBalanceModify_'+ para + '_roe');
   var unitPrice=document.getElementById('SingleTourBalanceModify_'+ para + '_unitPrice');
   var count=document.getElementById('SingleTourBalanceModify_'+ para + '_count');
   var amount=document.getElementById('SingleTourBalanceModify_'+ para + '_amount');
   var cost=document.getElementById("SingleTourBalanceModify_singleTourBalance_cost");
   var grossAmount=document.getElementById("SingleTourBalanceModify_singleTourBalance_grossAmount");
   var grossAmountRate=document.getElementById("SingleTourBalanceModify_singleTourBalance_grossAmountRate");
   var tourAmount=document.getElementById("SingleTourBalanceModify_singleTourBalance_tourAmount");
   
   roe.value=Math.round(roe.value*100)/100;
   unitPrice.value=Math.round(unitPrice.value*100)/100;
   
   var tmepAmount = Math.round((unitPrice.value * count.value * roe.value)*100)/100;
   var cost=cost.value-amount.value + tmepAmount -tourAmount.value;
   
   if(cost>0)
      alert("你输入的的成本费已高于纯团费！")
    
    cost.value=Math.round((cost.value - amount.value  + tmepAmount)*100) / 100;
    amount.value=Math.round((unitPrice.value * count.value * roe.value)*100)/100;
    
    grossAmount.value=Math.round((tourAmount.value-cost.value)*100) /100;
    if(tourAmount.value!='0')
      grossAmountRate.value=(Math.round(grossAmount.value / tourAmount.value * 10000)) / 100;
    else
      grossAmountRate.value=0;
}

function countchange(para,param2)
{
    var num = document.getElementById(param2);
   if(isNaN(num.value))
   {
     alert("输入值只能为数字!");
     num.value="";
     num.focus();
     return;
   }
   else if(/[^0-9]/g.test(num.value))
        {
          alert("输入值只能为正整数或零!");
          num.value=0;
          num.focus();
          return;
        }
   
   var roe=document.getElementById('SingleTourBalanceModify_'+ para + '_roe');
   var unitPrice=document.getElementById('SingleTourBalanceModify_'+ para + '_unitPrice');
   var count=document.getElementById('SingleTourBalanceModify_'+ para + '_count');
   var amount=document.getElementById('SingleTourBalanceModify_'+ para + '_amount');
   var cost=document.getElementById("SingleTourBalanceModify_singleTourBalance_cost");
   var grossAmount=document.getElementById("SingleTourBalanceModify_singleTourBalance_grossAmount");
   var grossAmountRate=document.getElementById("SingleTourBalanceModify_singleTourBalance_grossAmountRate");
   var tourAmount=document.getElementById("SingleTourBalanceModify_singleTourBalance_tourAmount");
   
   var tmepAmount = Math.round((unitPrice.value * count.value * roe.value)*100)/100;
   var cost=cost.value-amount.value + tmepAmount -tourAmount.value;
   
   if(cost>0)
      alert("你输入的的成本费已高于纯团费！")
     
    cost.value=Math.round((cost.value - amount.value  + tmepAmount)*100) / 100;
    amount.value=Math.round((unitPrice.value * count.value * roe.value)*100)/100;
    grossAmount.value=Math.round((tourAmount.value-cost.value)*100) /100;
    
    if(tourAmount.value!='0')
      grossAmountRate.value=(Math.round(grossAmount.value / tourAmount.value * 10000)) / 100;
    else
      grossAmountRate.value=0;
}



function paxChange(param)
{
  var num = document.getElementById(param);
   if(isNaN(num.value))
   {
     alert("输入值只能为数字!");
     num.value="";
     num.focus();
     return;
   }
   else if(/[^0-9]/g.test(num.value))
        {
          alert("输入值只能为正整数或零!");
          num.value="";
          num.focus();
          return;
        }
  var exemptPax=document.getElementById(param);
  var pax=document.getElementById("SingleTourBalanceModify_singleTourBalance_pax");
  var allPax=document.getElementById("SingleTourBalanceModify_singleTourBalance_allPax");
  var num =allPax.value - exemptPax.value;
  if(num<0)
   {
     alert("减免人数已超出许可范围！");
     exemptPax.value=""
     exemptPax.focus();
     return;
   }
 
}

function tourAmountChange(param)
{
   var num = document.getElementById(param);
   if(isNaN(num.value))
   {
     alert("输入值只能为数字！");
     num.value="";
     num.focus();
     return;
   }
   var cost=document.getElementById("SingleTourBalanceModify_singleTourBalance_cost");
   var grossAmount=document.getElementById("SingleTourBalanceModify_singleTourBalance_grossAmount");
   var grossAmountRate=document.getElementById("SingleTourBalanceModify_singleTourBalance_grossAmountRate");
   var tourAmount=document.getElementById("SingleTourBalanceModify_singleTourBalance_tourAmount");
   
   tourAmount.value=Math.round(tourAmount.value*100)/100;
   var num=cost.value-tourAmount.value;
   
   if(num>0)
     alert("你输入的纯团费低于成本费！")
   
   grossAmount.value=Math.round((tourAmount.value-cost.value)* 100)/100;
   if(tourAmount.value!=0)
    grossAmountRate.value=Math.round(grossAmount.value / tourAmount.value * 10000)/100;
   else
      grossAmountRate.value=0;
}

function goBack()
{
  var fm=document.SingleTourBalanceModify;
  fm.action="<s:url action='SingleTourBalanceSearch' namespace='/finance' includeParams='none'/>";
  fm.submit();
}

function mustPayMidify()
{
  var fm=document.MustPayModifyInput;
  fm.submit();
}


function checkOut(para)
{
  var fm=document.SingleTourBalanceModify;
  
  var extrIncome=document.getElementById("SingleTourBalanceModify_singleTourBalance_extrIncome");
  var extrIncomeDec=document.getElementById("SingleTourBalanceModify_singleTourBalance_extrIncomeDec");
  if(extrIncome.value!="" && (new Number(extrIncome.value))!=0)
  {
    if(extrIncomeDec.value=="")
    {
	      alert("当存在“其它收入”时“收入说明”不能为空！");
	      return;
    }
  }
  
  var amountType;
  var supplierId;
  var unitPrice;
  var count;
  for(var i=1;i<=para;i++)
  {
     //amountType=document.getElementById('SingleTourBalanceModify_costList('+ i + ')_costType');
     supplierId=document.getElementById('SingleTourBalanceModify_costList('+ i + ')_supplierId');
     unitPrice=document.getElementById('SingleTourBalanceModify_costList('+ i + ')_unitPrice');
     count=document.getElementById('SingleTourBalanceModify_costList('+ i + ')_count');
     
     if(supplierId.value=="")
     {
        alert("第"+i+"行，"+"应付款单位不能为空！");
        return;
     }
     if(unitPrice.value==0.0)
     {
        alert("第"+i+"行，"+"单价必须大于0！");
        return;
     }
     if(count.value==0)
     {
        alert("第"+i+"行，"+"数量必须大于0！");
        return;
     }
  }
  
   var cost=document.getElementById("SingleTourBalanceModify_singleTourBalance_cost");
   var tourAmount=document.getElementById("SingleTourBalanceModify_singleTourBalance_tourAmount");
   var num=cost.value-tourAmount.value;
   
   if(num>0)
   {
     if(confirm("你输入的纯团费小于成本合计！是否继续？")==false)
         return;
   }
  
    fm.action = "<s:url action='SingleTourBalanceModify' namespace='/finance'/>";
    fm.submit();
}

function reCount(param)
  {
      var fm=document.TourBalanceMake;
      var cost=document.getElementById("SingleTourBalanceModify_singleTourBalance_cost");
	  var grossAmount=document.getElementById("SingleTourBalanceModify_singleTourBalance_grossAmount");
	  var grossAmountRate=document.getElementById("SingleTourBalanceModify_singleTourBalance_grossAmountRate");
	  var tourAmount=document.getElementById("SingleTourBalanceModify_singleTourBalance_tourAmount");
	  
	  var roe;
	  var unitPrice;
      var count;
      var amount;
	  var cost=0.0;
	  for(var i=1;i<=param;i++)
	  {
	     roe=document.getElementById('SingleTourBalanceModify_costList('+ i + ')_roe');
	     unitPrice=document.getElementById('SingleTourBalanceModify_costList('+ i + ')_unitPrice');
         count=document.getElementById('SingleTourBalanceModify_costList('+ i + ')_count');
	     amount=document.getElementById('SingleTourBalanceModify_costList('+ i + ')_amount');
	     amount.value = Math.round(new Number(roe.value) * new Number(unitPrice.value) * new Number(count.value)*100)/100; 
	     cost = new Number(cost) + new Number(amount.value);
	  }
	  cost.value=Math.round(cost*100)/100;
	  grossAmount.value = Math.round((tourAmount.value - cost)*100)/100;
	  if(tourAmount.value!=0)
	    grossAmountRate.value=Math.round(grossAmount.value / tourAmount.value * 10000)/100;
	  else
	    grossAmountRate.value=0;
	    
  }
  
  
  
   function extrIncomeChange(param)
  {
      var extrIncome=document.getElementById(param);
      var cost=document.getElementById("SingleTourBalanceModify_singleTourBalance_cost");
	  var grossAmount=document.getElementById("SingleTourBalanceModify_singleTourBalance_grossAmount");
	  var grossAmountRate=document.getElementById("SingleTourBalanceModify_singleTourBalance_grossAmountRate");
      var tourAmount=document.getElementById("SingleTourBalanceModify_singleTourBalance_tourAmount");
      var allAmount=document.getElementById("SingleTourBalanceModify_singleTourBalance_amount");
      var muAmount=document.getElementById("SingleTourBalanceModify_singleTourBalance_muAmount");
      
      extrIncome.value = Math.round(extrIncome.value*100)/100;
      tourAmount.value= Math.round((new Number(muAmount.value) + new Number(extrIncome.value))*100)/100;
      allAmount.value= Math.round((new Number(muAmount.value) + new Number(extrIncome.value))*100)/100;
      
      var num=cost.value-tourAmount.value;
   
	   if(num>0)
	     alert("你输入的纯团费低于成本费！")
	   
	   grossAmount.value=Math.round((tourAmount.value-cost.value)*100)/100;
	   if(tourAmount.value!=0)
	    grossAmountRate.value=Math.round(grossAmount.value / tourAmount.value * 10000) / 100;
	   else
	      grossAmountRate.value=0;
  }


//-->
</script>
<s:if test='status!="U"'>
<s:if test='status=="N"'>
  <s:form action="SingleTourBalanceModify" namespace="/finance" method="post" theme="simple">

    <s:hidden name="tourNo"></s:hidden>
    <s:hidden name="id"></s:hidden>
    <s:hidden name="status"></s:hidden>

    <table border="0" style="width: 100%">
      <tr>
        <td class="idx">线路</td>
        <td colspan="5" class="data">
        <s:property value="singleTourBalance.line.lineName" />
        <s:hidden name="singleTourBalance.line.lineName" />
        </td>
      </tr>
      <tr>
        <td class="idx">团号</td>
        <td colspan="5" class="data">
          <s:property value="singleTourBalance.tourNo" />
          <s:hidden name="singleTourBalance.tourNo" />
        </td>
      </tr>
      <tr>
        <td class="idx">出发日期</td>
        <td class="data">
          <s:date name="singleTourBalance.outDate" format="yyyy-MM-dd"/>
          <s:hidden name="singleTourBalance.outDate" />
        </td>
        <td class="idx">天数</td>
        <td colspan="3" class="data">
          <s:property value="singleTourBalance.line.lineDay" />&nbsp;
        </td>
      </tr>

      <tr>
        <td class="idx" width="80">客人人数</td>
        <td class="data">
          <s:property value="singleTourBalance.allPax" />
          <s:hidden name="singleTourBalance.allPax" /></td>
        <td class="idx" width="80">领队数</td>
        <td class="data">
          <s:property value="singleTourBalance.leaderPax" />
          <s:hidden name="singleTourBalance.leaderPax" /></td>
        <td class="idx" width="80">领队名单</td>
        <td class="data">
          <s:property value="singleTourBalance.leaderName" />
          <s:hidden name="singleTourBalance.leaderName" /></td>
      </tr>

      <tr>
        <td class="idx">房间情况</td>
        <td colspan="5" bgcolor="white">
          &nbsp;双人间：<s:property value="singleTourBalance.doubleRoom" /><s:hidden name="singleTourBalance.doubleRoom" />&nbsp;
          &nbsp;单人间：<s:property value="singleTourBalance.singleRoom" /><s:hidden name="singleTourBalance.singleRoom" />&nbsp;
          &nbsp;加床间：<s:property value="singleTourBalance.extraBedRoom" /><s:hidden name="singleTourBalance.extraBedRoom" />
        </td>
      </tr>

      <tr>
        <td class="idx">应收款</td>
        <td class="data">
          <font color="red">
          <s:property value="singleTourBalance.muAmount" />
          <s:hidden name="singleTourBalance.muAmount" /></font>
        </td>
        <td class="idx">已收款</td>
        <td class="data">
          <font color="red">
          <s:property value="singleTourBalance.alAmount" />
          <s:hidden name="singleTourBalance.alAmount" /></font>
        </td>
        <td class="idx">未收款</td>
        <td class="data"><font color="red">
          <s:property value="singleTourBalance.wiAmount" />
          <s:hidden name="singleTourBalance.wiAmount" /></font></td>
      </tr>
      <tr>
        <td class="idx">备注</td>
        <td colspan="5" class="data">
          <s:property value="singleTourBalance.remarks" />
          <s:hidden name="singleTourBalance.remarks" /></td>
      </tr>
      <tr>
        <td class="idx">操作组</td>
        <td class="data"><s:property value="singleTourBalance.team.name"/> </td>
        <td class="idx">操作人</td>
        <td class="data" colspan="3">
          <s:property value="singleTourBalance.oprateUser" />
          <s:hidden name="singleTourBalance.oprateUser" />
        </td>
      </tr>
      <tr>
        <td colspan="6">结算明细表</td>
      </tr>
      <tr>
        <td class="idx">减免人数</td>
        <td class="data">
          <s:textfield name="singleTourBalance.exemptPax" onchange="javascript:paxChange(this.id);"></s:textfield></td>
        <td class="idx">儿童人数</td>
        <td class="data"><s:textfield name="singleTourBalance.childPax" onchange="javascript:paxChange(this.id);"></s:textfield></td>
        <td class="idx">结算人数</td>
        <td class="data">
          <s:textfield
            name="singleTourBalance.pax" 
            onchange="javascript:paxChange(this.id);">
          </s:textfield>
        </td>
      </tr>

      <tr>
        <td class="idx">旅游团队总收入</td>
        <td class="data">
          <s:textfield name="singleTourBalance.amount"></s:textfield>
        </td>
        <td class="idx">纯团费</td>
        <td bgcolor="white">
          <s:textfield label="纯团费" name="singleTourBalance.tourAmount" onchange="javascript:tourAmountChange(this.id);"></s:textfield>
        </td>
        <td class="idx">成本合计</td>
        <td class="data">
          <s:textfield name="singleTourBalance.cost" cssStyle="background-color: EEEEEE;" readonly="true"></s:textfield>
        </td>
      </tr>
      <tr>
        <td class="idx">毛利</td>
        <td class="data">
          <s:textfield name="singleTourBalance.grossAmount"
                       cssStyle="background-color: EEEEEE;"
                       readonly="true">
          </s:textfield></td>
        <td class="idx">毛利率</td>
        <td class="data">
          <s:textfield name="singleTourBalance.grossAmountRate"
                       cssStyle="background-color: EEEEEE;"
                       size="6" readonly="true">
          </s:textfield>%</td>
        <td class="rdata" colspan="2" >
          <input type="button" value="重新计算成本"
                 onclick="javascript:reCount('<s:property value="costList.size()" />');">
        </td>
      </tr>
      
      <tr>
        <td class="idx">其它收入</td>
        <td class="data">
          <s:textfield name="singleTourBalance.extrIncome"
                       onchange="javascript:extrIncomeChange(this.id);">
          </s:textfield></td>
        <td class="idx">收入说明</td>
        <td bgcolor="white" colspan="3">
          <s:textfield name="singleTourBalance.extrIncomeDec">
          </s:textfield>
        </td>
      </tr>
    </table>
    
    <table border="0" style="width: 100%">
      <tr>
        <td colspan="6">
        <table border="0" style="width: 100%">
          <tr>
            <td class="lstidx">NO.</td>
            <td class="lstidx">费用类型</td>
            <td class="lstidx">应付款单位</td>
            <td class="lstidx">摘要</td>
            <td class="lstidx">币种</td>
            <td class="lstidx">汇率</td>
            <td class="lstidx">单价(外币)</td>
            <td class="lstidx">数量</td>
            <td class="lstidx">单位</td>
            <td class="lstidx">金额(人民币)</td>
            <td class="lstidx">操作</td>
          </tr>

          <s:if test="costList!=null">
            <s:iterator value="costList" status="rowCounter">
             <s:if test='frChecked == "Y"'>
                <tr>
                <td class="cdata">
                 <s:hidden name="costList(%{id}).payAmount" value="%{payAmount}"></s:hidden>
                 <s:hidden name="costList(%{id}).fcAmount" value="%{fcAmount}"></s:hidden>
                 <s:hidden name="costList(%{id}).frChecked" value="%{frChecked}"></s:hidden>
                 <s:hidden name="costList(%{id}).acctId" value="%{acctId}"/>
                <s:textfield cssStyle="background-color: EEEEEE;" 
				                     name="costList(%{id}).id"
				                     value="%{#rowCounter.count}"
				                     size="3"
				                     readonly="true"/>
                </td>
                <td class="cdata">
                <sj:autocompleter cssStyle="background-color: EEEEEE;"
  		                          list="costTypeList"
  		                          listKey="label"
  		                          listValue="label"
  		                          value="%{costType}" 
  		                          disabled="true"/>
                  <s:hidden name="costList(%{id}).costType"
                            value="%{costType}">
                  </s:hidden>
                </td>
                <td class="cdata">
                  <s:select cssStyle="background-color: EEEEEE;"
                            list="supplierList"
                            listKey="supplierId"
                            listValue="supplierName"
                            headerKey=""
                            headerValue=""
                            value="%{supplierId}" 
                            disabled="true"/>
                    <s:hidden name="costList(%{id}).customer.customerId" value="%{customer.customerId}"></s:hidden>
                </td>
                <td class="cdata">
                <s:textfield cssStyle="background-color: EEEEEE;"
                             name="costList(%{id}).description"
                             value="%{description}"
                             readonly="true"/>
                </td>
                <td class="cdata">
                <s:textfield cssStyle="background-color: EEEEEE;"
                             name="costList(%{id}).currency"
                             value="%{currency}"
                             readonly="true"
                             size="9" />
                </td>
                <td class="cdata">
                <s:textfield cssStyle="background-color: EEEEEE;"
                             name="costList(%{id}).roe"
                             value="%{roe}"
                             readonly="true"
                             size="8"/>
                </td>
                <td class="cdata">
                <s:textfield cssStyle="background-color: EEEEEE;"
                             name="costList(%{id}).unitPrice"
                             readonly="true"
                             value="%{unitPrice}"
                             size="10"
                             onchange="javascript:change('costList(%{id})',this.id)" />
                </td>
                <td class="cdata">
                <s:textfield cssStyle="background-color: EEEEEE;"
                             name="costList(%{id}).count"
                             readonly="true"
                             value="%{count}"
                             size="3"
                             onchange="javascript:countchange('costList(%{id})',this.id)" />
                </td>
                <td class="cdata">
                <s:textfield cssStyle="background-color: EEEEEE;"
                             name="costList(%{id}).unit"
                             readonly="true"
                             value="%{unit}"
                             size="6" />
                </td>
                <td class="cdata">
                <s:textfield cssStyle="background-color: EEEEEE;"
                             name="costList(%{id}).amount"
                             readonly="true"
                             value="%{amount}"
                             size="10"
                             readonly="true" />
                </td>
                <td class="cdata">
                <font color="red">已审核</font>
                </td>
              </tr>
             </s:if>

             <!-- 未审核 -->
             <s:else>
              <tr>
                <td class="cdata">
                  <s:hidden name="costList(%{id}).payAmount" value="%{payAmount}"></s:hidden>
                  <s:hidden name="costList(%{id}).fcAmount" value="%{fcAmount}"></s:hidden>
                  <s:hidden name="costList(%{id}).frChecked" value="%{frChecked}"></s:hidden>
                  <s:hidden name="costList(%{id}).isMakeOutcome" value="%{isMakeOutcome}"/>
                  <s:hidden name="costList(%{id}).acctId" value="%{acctId}"/>
                  <s:textfield name="costList(%{id}).id"
                               value="%{#rowCounter.count}"
                               size="3" />
                </td>
                <td class="cdata">
                  <sj:autocompleter name="costList(%{id}).costType"
				                            list="costTypeList"
				                            listKey="label"
				                            listValue="label"
				                            autoComplete="true"  
				                            searchType="substring" 
				                            value="%{costType}"/>
				        </td>
                <td class="cdata">
                  <s:select name="costList(%{id}).supplierId"
                            list="supplierList"
                            listKey="supplierId"
                            listValue="supplierName"
                            headerKey=""
                            headerValue=""
                            value="%{supplierId}"/>
                </td>
                <td class="cdata">
                  <s:textfield name="costList(%{id}).description"
                               value="%{description}" />
                </td>

                <!-- 币种 -->
                <td class="cdata">
                <s:select name="costList(%{id}).currency"
                          list="currencyList"
                          listKey="label"
                          listValue="value"
                          value="%{currency}"/>
                </td>

                <!-- 汇率 -->
                <td class="cdata">
                  <s:textfield name="costList(%{id}).roe"
                               value="%{roe}"
                               onchange="javascript:change('costList(%{id})',this.id)"
                               size="8" />
                </td>
                <td class="cdata">
                  <s:textfield name="costList(%{id}).unitPrice"
                               value="%{unitPrice}"
                               size="10"
                               onchange="javascript:change('costList(%{id})',this.id)" />
                </td>
                <td class="cdata">
                  <s:textfield name="costList(%{id}).count"
                               value="%{count}"
                               size="3"
                               onchange="javascript:countchange('costList(%{id})',this.id)" />
                </td>
                <td class="cdata"><s:textfield name="costList(%{id}).unit" value="%{unit}" size="6" /></td>
                <td class="cdata"><s:textfield name="costList(%{id}).amount" value="%{amount}" size="10" readonly="true" /></td>
                <td class="cdata">
                   <s:if test='isMakeOutcome == "Y"'>
                      <font color="red">已做付款申请</font>
                 </s:if>
                 <s:else>
                   <a href="javascript:DeleteRow('<s:property value="#rowCounter.count" />')">删除</a>
                 </s:else>
                  <s:hidden name="costList(%{id}).isMakeOutcome" value="%{isMakeOutcome}"/>
                  <s:hidden name="costList(%{id}).frChecked" value="%{frChecked}"></s:hidden>
                </td>
              </tr>
             </s:else>
            </s:iterator>
          </s:if>

        </table>
        </td>
      </tr>
      <tr>
        <td colspan="6">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="6" align="center">
        <s:submit action="SingleTourBalanceModifyAdd" value="增加一行"></s:submit>
        <input type="button" value="保存修改" onClick="javascript:checkOut('<s:property value="costList.size()" />')"> 
        <input type="button" value="修改应收帐款" onClick="javascript:mustPayMidify();"> 
        <input type="button" value="返回" onClick="javascript:goBack();"></td>
      </tr>
      <tr>
        <td colspan="6">&nbsp;</td>
      </tr>
    </table>

    <s:hidden name="kenDepartmentId"></s:hidden>
    <s:hidden name="kenEmployeeId"></s:hidden>
    <s:hidden name="kenRouteName"></s:hidden>
    <s:hidden name="kenStartDate"></s:hidden>
    <s:hidden name="kenEndDate"></s:hidden>
    <s:hidden name="kenBuildStartDate"></s:hidden>
    <s:hidden name="kenBuildEndDate"></s:hidden>

  </s:form>

  <s:form action="MustPayModifyInput" namespace="/finance" method="post" theme="simple">
    <s:hidden name="tourNo"></s:hidden>
    <s:hidden name="kenDepartmentId"></s:hidden>
    <s:hidden name="kenEmployeeId"></s:hidden>
    <s:hidden name="kenRouteName"></s:hidden>
    <s:hidden name="kenStartDate"></s:hidden>
    <s:hidden name="kenEndDate"></s:hidden>
    <s:hidden name="kenBuildStartDate"></s:hidden>
    <s:hidden name="kenBuildEndDate"></s:hidden>
  </s:form>
</s:if>
</s:if>

<s:elseif test='status=="U"'>
<s:form action="SingleTourBalanceModify" namespace="/finance" method="post" theme="simple">
  <div>
    <input type="button" value="返回" onClick="javascript:goBack();">
  </div>
      <s:hidden name="tourNo"></s:hidden>
      <s:hidden name="kenDepartmentId"></s:hidden>
      <s:hidden name="kenEmployeeId"></s:hidden>
      <s:hidden name="kenRouteName"></s:hidden>
      <s:hidden name="kenStartDate"></s:hidden>
      <s:hidden name="kenEndDate"></s:hidden>
      <s:hidden name="kenBuildStartDate"></s:hidden>
      <s:hidden name="kenBuildEndDate"></s:hidden>
   </s:form>
 </s:elseif>
 
 <s:if test='status=="Y"'>
 <s:form action="SingleTourBalanceModify" namespace="/finance" method="post" theme="simple">
  <div>
    <input type="button" value="返回" onClick="javascript:history.go(-1);">
  </div>
      <s:hidden name="tourNo"></s:hidden>
      <s:hidden name="kenDepartmentId"></s:hidden>
      <s:hidden name="kenEmployeeId"></s:hidden>
      <s:hidden name="kenRouteName"></s:hidden>
      <s:hidden name="kenStartDate"></s:hidden>
      <s:hidden name="kenEndDate"></s:hidden>
      <s:hidden name="kenBuildStartDate"></s:hidden>
      <s:hidden name="kenBuildEndDate"></s:hidden>
 </s:form>
 </s:if>

</body>
</html>
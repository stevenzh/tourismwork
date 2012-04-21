<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帐单制作--修改帐单</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="帐单制作--修改帐单">
</head>

<body>
<script type="text/javascript">
<!--//

function DeleteRow(param)
{
   var fm=document.ReckoningModifySubmit;
   fm.itemId.value=param;
    if (confirm("是否真的删除?") == false)
    {
      return;
    }
    fm.action = "<s:url action='ReckoningModifyDelete' namespace='/operator'/>"
    fm.submit();
}

function change(para,counter,para2)
{
   var num = document.getElementById(para2);
   if(isNaN(num.value))
   {
     alert("输入值只能为数字!");
     num.value="";
     num.focus();
     return;
   }
   var fm=document.ReckoningModifySubmit;
   var num=counter
   var unitPrice=document.getElementById('ReckoningModifySubmit_'+ para + '_unitPrice');
   var count=document.getElementById('ReckoningModifySubmit_'+ para + '_count');
   var amount=document.getElementById('ReckoningModifySubmit_'+ para + '_amount');
   var allAmount=document.getElementById("ReckoningModifySubmit_reckoning_amount");
   
     allAmount.value=allAmount.value-amount.value + unitPrice.value * count.value;
   amount.value=unitPrice.value * count.value;
}


function countchange(para,counter,para2)
{
   var num = document.getElementById(para2);
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
   var fm=document.ReckoningModifySubmit;
   var num=counter
   var unitPrice=document.getElementById('ReckoningModifySubmit_'+ para + '_unitPrice');
   var count=document.getElementById('ReckoningModifySubmit_'+ para + '_count');
   var amount=document.getElementById('ReckoningModifySubmit_'+ para + '_amount');
   var allAmount=document.getElementById("ReckoningModifySubmit_reckoning_amount");
   
     allAmount.value=allAmount.value-amount.value + unitPrice.value * count.value;
   amount.value=unitPrice.value * count.value;
}


function goBack()
{
   var fm=document.ReckoningModifySubmit;
   fm.action = "<s:url action='ReckoningDetail' namespace='/operator' includeParams='none'></s:url>";
   fm.submit();
}


function checkOut(param)
{
 var fm=document.ReckoningModifySubmit;
 var str="";
 if(document.getElementById("ReckoningModifySubmit_reckoning_contact").value=="")
 {
   str=str + "　联系人不能为空！";
 }
 if(document.getElementById("ReckoningModifySubmit_reckoning_phone").value=="")
 {
   str=str + "　电话不能为空！";
 }
 if(document.getElementById("ReckoningModifySubmit_reckoning_fax").value=="")
 {
   str=str + "　传真不能为空！";
 }
 if(document.getElementById("ReckoningModifySubmit_reckoning_routeName").value=="")
 {
   str=str + "　线路不能为空！";
 }
 
 if(str!="")
 {
   alert(str);
   return;
 }
 
  var unitPrice;
  var count;
  for(var i=1;i<=param;i++)
  {
     unitPrice=document.getElementById('ReckoningModifySubmit_reckoningAcctList('+ i + ')_unitPrice');
     count=document.getElementById('ReckoningModifySubmit_reckoningAcctList('+ i + ')_count');
     
     if(unitPrice.value==0.0)
     {
        alert("第"+i+"行，"+"单价必须大于0！");
        return;
     }
     if(count.value==0)
     {
        alert("第"+i+"行，"+"人数必须大于0！");
        return;
     }
  }
 
  fm.action = "<s:url action='ReckoningMakeSubmit' namespace='/operator' includeParams='none'></s:url>";
  fm.submit();
}



function checkOutPerN()
{
 var fm=document.ReckoningModifySubmit;
 var str="";
 if(document.getElementById("ReckoningModifySubmit_reckoning_contact").value=="")
 {
   str=str + "　联系人不能为空！";
 }
 if(document.getElementById("ReckoningModifySubmit_reckoning_phone").value=="")
 {
   str=str + "　电话不能为空！";
 }
 if(document.getElementById("ReckoningModifySubmit_reckoning_fax").value=="")
 {
   str=str + "　传真不能为空！";
 }
 if(document.getElementById("ReckoningModifySubmit_reckoning_routeName").value=="")
 {
   str=str + "　线路不能为空！";
 }
 
 if(str!="")
 {
   alert(str);
   return;
 }
 
 fm.action = "<s:url action='ReckoningModifySubmit' namespace='/operator' includeParams='none'></s:url>";
 fm.submit();
   
}

function checkOutN(param)
{
 var fm=document.ReckoningModifySubmit;
 var str="";
 if(document.getElementById("ReckoningModifySubmit_reckoning_contact").value=="")
 {
   str=str + "　联系人不能为空！";
 }
 if(document.getElementById("ReckoningModifySubmit_reckoning_phone").value=="")
 {
   str=str + "　电话不能为空！";
 }
 if(document.getElementById("ReckoningModifySubmit_reckoning_fax").value=="")
 {
   str=str + "　传真不能为空！";
 }
 if(document.getElementById("ReckoningModifySubmit_reckoning_routeName").value=="")
 {
   str=str + "　线路不能为空！";
 }
 
 if(str!="")
 {
   alert(str);
   return;
 }
 
  var unitPrice;
  var count;
  for(var i=1;i<=param;i++)
  {
     unitPrice=document.getElementById('ReckoningModifySubmit_reckoningAcctList('+ i + ')_unitPrice');
     count=document.getElementById('ReckoningModifySubmit_reckoningAcctList('+ i + ')_count');
     
     if(unitPrice.value==0.0)
     {
        alert("第"+i+"行，"+"单价必须大于0！");
        return;
     }
     if(count.value==0)
     {
        alert("第"+i+"行，"+"人数必须大于0！");
        return;
     }
  }
 
  fm.action = "<s:url action='ReckoningModifySubmit' namespace='/operator' includeParams='none'></s:url>";
  fm.submit();
}



function checkOutPer()
{
 var fm=document.ReckoningModifySubmit;
 var str="";
 if(document.getElementById("ReckoningModifySubmit_reckoning_contact").value=="")
 {
   str=str + "　联系人不能为空！";
 }
 if(document.getElementById("ReckoningModifySubmit_reckoning_phone").value=="")
 {
   str=str + "　电话不能为空！";
 }
 if(document.getElementById("ReckoningModifySubmit_reckoning_fax").value=="")
 {
   str=str + "　传真不能为空！";
 }
 if(document.getElementById("ReckoningModifySubmit_reckoning_routeName").value=="")
 {
   str=str + "　线路不能为空！";
 }
 
 if(str!="")
 {
   alert(str);
   return;
 }
 
 fm.action = "<s:url action='ReckoningMakeSubmit' namespace='/operator' includeParams='none'></s:url>";
 fm.submit();
   
}



//-->
</script>

<s:form action="ReckoningModifySubmit" namespace="/operator" method="post" theme="simple">
  <s:hidden name="reserveNo"></s:hidden>
  <s:hidden name="itemId"></s:hidden>
  <s:hidden name="nameKey"></s:hidden>
  <s:hidden name="isShort"></s:hidden>
  <s:hidden name="isPrint"></s:hidden>
  <s:hidden name="reckoningId"></s:hidden>
  <s:hidden name="reckoning.reckoningId"/>
  <s:hidden name="reckoning.version"/>
  <s:hidden name="reckoning.createDate"/>
  <s:hidden name="reckoning.createdBy"/>
  <s:hidden name="reckoning.printedCount"/>
  <s:hidden name="reckoning.pDate"/>

  <s:if test='isShort=="N"'>
    <table style="width: 100%">
      <tr>
        <td align="center" colspan="4">
        <h2>团费结算单</h2>
        </td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;编号:&nbsp;<s:property
          value="reckoning.reckoningId" />-<s:property
          value="reckoning.version" />
          </td>
        <td colspan="2" align="right">制作日期:&nbsp;<s:date name="reckoning.createDate" format="yyyy-MM-dd"/>&nbsp;
          </td>
      </tr>

      <!-- 散拼帐单 -->
      <s:if test='nameKey=="A"'>
         <tr>
        <td colspan="4">
        <table border="0" style="width: 100%">
          <tr>
            <td class="idx">客户</td>
            <td class="data" align="left" colspan="3">
              <s:property value="reckoning.client"/>&nbsp;<s:hidden name="reckoning.client"/></td>
          </tr>

          <tr>
            <td class="idx">联系人</td>
            <td class="data" align="left" colspan="3"><s:textfield
              name="reckoning.contact" size="40"></s:textfield></td>
          </tr>

          <tr>
            <td class="idx" width="80">电话</td>
            <td class="data"><s:textfield name="reckoning.phone" size="40"></s:textfield></td>
            <td class="idx" width="80">传真</td>
            <td class="data"><s:textfield name="reckoning.fax"></s:textfield></td>
          </tr>
          <tr>
            <td class="idx">线路</td>
            <td class="data" align="left" colspan="3"><s:textfield
              name="reckoning.routeName" size="40"></s:textfield></td>
          </tr>

          <tr>
            <td class="idx">团号</td>
            <td class="data"><s:property value="reckoning.tourNo"/>&nbsp;<s:hidden name="reckoning.tourNo"/></td>
            <td class="idx">出团日期</td>
            <td class="data"><s:property value="reckoning.oDate"/>&nbsp;<s:hidden name="reckoning.oDate"/></td>
          </tr>
          <tr>
            <td class="idx">人数</td>
            <td class="data" align="left" colspan="3"><s:property value="reckoning.pax"/>&nbsp;<s:hidden name="reckoning.pax"/></td>
          </tr>
        </table>
        </td>
      </tr>
      <tr>
        <td height="40" colspan="4">贵部委托我部操作的旅游团队,前期工作现已操作完毕。具体如下：</td>
      </tr>
        
        <tr>
          <td colspan="4">
          <table border="0" style="width: 100%">
            <tr>
              <td class="lstidx">NO.</td>
              <td class="lstidx">姓名</td>
              <td class="lstidx">金额</td>
              <td class="lstidx">NO.</td>
              <td class="lstidx">姓名</td>
              <td class="lstidx">金额</td>
            </tr>
            <s:if test="reckoningAcctList!=null">
              <s:iterator value="reckoningAcctList" status="rowCounter">
                <s:if test="(#rowCounter.count)%2==1">
                  <tr>
                </s:if>
                <td><s:property value="#rowCounter.count" /></td>
                <td><s:property value="name" /></td>
                <td><s:property value="amount" /></td>
                <s:if test="(#rowCounter.count)%2==0">
                  </tr>
                </s:if>
              </s:iterator>
              <s:if test="reckoningAcctList.size()%2==1">
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                </tr>
              </s:if>
            </s:if>
            <tr>
              <td>&nbsp;</td>
              <td colspan="4">
              <center>合计</center>
              </td>
              <td><s:textfield name="reckoning.amount" readonly="true" /></td>
            </tr>
          </table>

          </td>
        </tr>
      </s:if>


       <!-- 整团帐单 -->
      <s:else>
         <tr>
        <td colspan="4">
        <table border="0" style="width: 100%">
          <tr>
            <td class="idx">客户</td>
            <td class="data" align="left" colspan="3">
              <s:property value="reckoning.client"/>&nbsp;<s:hidden name="reckoning.client"/></td>
          </tr>

          <tr>
            <td class="idx">联系人</td>
            <td class="data" align="left" colspan="3"><s:textfield
              name="reckoning.contact" size="40"></s:textfield><font color="red">*</font></td>
          </tr>

          <tr>
            <td class="idx" width="80">电话</td>
            <td class="data"><s:textfield name="reckoning.phone" size="40"></s:textfield><font color="red">*</font></td>
            <td class="idx" width="80">传真</td>
            <td class="data"><s:textfield name="reckoning.fax"></s:textfield><font color="red">*</font></td>
          </tr>
          <tr>
            <td class="idx">线路</td>
            <td class="data" align="left" colspan="3"><s:textfield
              name="reckoning.routeName" size="40"></s:textfield><font color="red">*</font></td>
          </tr>

          <tr>
            <td class="idx">团号</td>
            <td class="data"><s:property value="reckoning.tourNo"/>&nbsp;<s:hidden name="reckoning.tourNo"/></td>
            <td class="idx">出团日期</td>
            <td class="data"><s:date name="reckoning.outDate" format="yyyy-MM-dd"/>&nbsp;<s:hidden name="reckoning.outDate"/></td>
          </tr>
          <tr>
            <td class="idx">人数</td>
            <td class="data" align="left"><s:property value="reckoning.pax"/>&nbsp;<s:hidden name="reckoning.pax"/></td>
            <td class="idx">领队数</td>
            <td class="data" align="left"><s:property value="reckoning.leaderPax"/>&nbsp;<s:hidden name="reckoning.leaderPax"/></td>
          </tr>
        </table>
        </td>
      </tr>
      <tr>
        <td height="40" colspan="4">贵部委托我部操作的旅游团队,前期工作现已操作完毕.具体如下:</td>
      </tr>
      
        <tr>
          <td colspan="4">
          <table border="0" style="width: 100%">
            <tr>
              <td class="lstidx">No.</td>
              <td class="lstidx">摘要</td>
              <td class="lstidx">单价</td>
              <td class="lstidx">人数</td>
              <td class="lstidx">单位</td>
              <td class="lstidx">金额</td>
              <td class="lstidx">操作</td>
            </tr>
            <s:if test="reckoningAcctList!=null">
              <s:iterator value="reckoningAcctList" status="rowCounter">
                <tr>
                  <td class="data"><s:textfield
                    name="reckoningAcctList(%{itemId}).itemId"
                    value="%{#rowCounter.count}" size="4"></s:textfield></td>
                  <td class="data"><s:textfield
                    name="reckoningAcctList(%{itemId}).description"
                    size="70" value="%{description}"></s:textfield></td>
                  <td class="rdata"><s:textfield
                    name="reckoningAcctList(%{itemId}).unitPrice"
                    value="%{unitPrice}" 
                    size="10"
                    onchange="javascript:change('reckoningAcctList(%{itemId})',%{reckoningAcctList.size()},this.id)"
                    onfocus="javascript:this.select();">
                  </s:textfield><font color="red">*</font></td>
                  <td class="rdata"><s:textfield
                    name="reckoningAcctList(%{itemId}).count" size="6"
                    value="%{count}"
                    onchange="javascript:countchange('reckoningAcctList(%{itemId})',%{reckoningAcctList.size()},this.id)"
                    onfocus="javascript:this.select();">
                  </s:textfield><font color="red">*</font></td>
                  <td class="rdata"><s:textfield
                    name="reckoningAcctList(%{itemId}).unit"  
                    size="10"
                    value="%{unit}"></s:textfield></td>
                  <td class="rdata"><s:textfield
                    name="reckoningAcctList(%{itemId}).amount"
                    value="%{amount}" size="10" readonly="true"></s:textfield></td>
                  <td class="data"><a
                    href="javascript:DeleteRow('<s:property value="#rowCounter.count" />')">删除</a></td>
                </tr>
              </s:iterator>
            </s:if>
            <tr>
              <td class="data">&nbsp;</td>
              <td class="data" colspan="4">
              <center>合计</center>
              </td>

              <td class="rdata"><s:textfield name="reckoning.amount"
                readonly="true" size="10"/></td>
              <td class="data">&nbsp;</td>
            </tr>
          </table>

          </td>
        </tr>
        <tr>
          <td align="right" colspan="4"><s:submit
            action="ReckoningModifyAdd" value="增加一行" /></td>
        </tr>
      </s:else>

      </td>
      </tr>
      <tr>
       <td colspan="4">&nbsp;<br>
        <p>请将上述款项汇至我公司如下帐号:</p>
        户　名：XXXX旅行社<br>
        开户行：XXXX银行XXXX支行<br>
        帐　号：XXXXXXXX-XXXXXXXXX
        <p>
        </td>
      </tr>

      <tr>
        <td colspan="4"><strong>
        <p>帐单状态：</p>
        </strong></td>
      </tr>
      <tr>
        <td colspan="2">创建人：&nbsp;<s:property
          value="reckoning.createdBy" />
          </td>
        <td colspan="2">创建日期：&nbsp;<s:date name="reckoning.createDate" format="yyyy-MM-dd"/>
          </td>
      </tr>
      <tr>
        <td colspan="2">打印次数：&nbsp;<s:property
          value="reckoning.printedCount" />
          </td>
        <td colspan="2">打印日期：&nbsp;<s:property
          value="reckoning.pDate" />
          </td>
      </tr>

      <tr>
        <td colspan="4">&nbsp;</td>
      </tr>
      <tr>
        <td align="center" colspan="4">
        <s:if test='nameKey=="A"'>
          <s:if test='isPrint=="N"'>
            <input type="button" value="提交修改" onClick="javascript:checkOutPerN();">
          </s:if>
          <s:else>
            <input type="button" value="提交修改" onClick="javascript:checkOutPer();">
          </s:else>
        </s:if>
        <s:else>
          <s:if test='isPrint=="N"'>
            <input type="button" value="提交修改" onClick="javascript:checkOutN('<s:property value="reckoningAcctList.size()" />');">
          </s:if>
          <s:else>
            <input type="button" value="提交修改" onClick="javascript:checkOut('<s:property value="reckoningAcctList.size()" />');">
          </s:else>
        </s:else> 
        
        <input type="button" onClick="javascript:goBack();" value="返回">
        </td>
      </tr>
    </table>
  </s:if>
  
   <!-- 国内短线帐单 -->
  <s:elseif test='isShort=="Y"'>
    <table style="width: 100%">
        <tr class="data">
          <td colspan="4" align="center"><h3>确认后请盖章回传</h3></td>
        </tr>
        <tr class="data">
          <td width="100">日期：</td>
          <td><sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="reckoning.outDate"/>&nbsp;格式：yyyy-MM-dd
          </td>
          <td width="100">线路：</td>
          <td><s:textfield name="reckoning.routeName"/></td>
        </tr>
        
        <tr class="data">
          <td>客人姓名：</td>
          <td><s:textfield name="reckoning.client"/></td>
          <td>人数：</td>
          <td><s:textfield name="reckoning.pax"/></td>
        </tr>
        
        <tr class="data">
          <td>座位号：</td>
          <td><s:textfield name=""/></td>
          <td>上车地点/时间：</td>
          <td><s:textfield name=""/>/
          <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="reckoning.outDate"
                                   displayFormat="HH:mm"/>
          </td>
        </tr>
        
        <tr class="data">
          <td>结算价：</td>
          <td><s:textfield name="reckoning.amount"/></td>
          <td></td>
          <td></td>
        </tr>
        <tr><td colspan="4">&nbsp;</td></tr>
       <tr>
        <td align="center" colspan="4">
          <s:submit action="ReckoningModify" value="保存修改"></s:submit> 
          <input type="button" onClick="javascript:goBack();" value="返回"></td>
      </tr>
        <tr>
          <td colspan="4">&nbsp;</td>
        </tr>
        
     </table> 
  
  </s:elseif>
</s:form>

</body>
</html>
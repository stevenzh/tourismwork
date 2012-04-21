<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帐单制作--显示帐单</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="帐单制作--显示帐单">
</head>

<body>

<SCRIPT type="text/javascript">
<!--//

function goBack()
{
   var fm=document.SalesBookDetail;
   fm.submit();
}

function comeBack()
{
   var fm=document.ReckoningDetail;
   fm.action = "<s:url action='ReckoningDetail' namespace='/operator' includeParams='none'></s:url>";
   fm.submit();
}

function print()
{
    var fm = document.fopReport;
    fm.submit();
}

function come(parma)
{
   var fm=document.ReckoningDetail;
   fm.reckoningId.value=parma;
   fm.action="<s:url action='ReckoningOneDetail' namespace='/operator' includeParams='none'/>";
   fm.submit();
}

//-->
</SCRIPT>
<s:form action="ReckoningDetail" namespace="/operator" method="post" theme="simple">
  <s:hidden name="reserveNo"></s:hidden>
  <s:hidden name="itemId"></s:hidden>
  <s:hidden name="nameKey"></s:hidden>
  <s:hidden name="isShort"></s:hidden>
  <s:hidden name="reckoningId"></s:hidden>

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
          value="reckoning.version" /></td>
        <td colspan="2" align="right">制作日期:&nbsp;<s:date name="reckoning.createDate" format="yyyy-MM-dd"/>&nbsp;</td>
      </tr>

       <!-- 散拼帐单 -->
      <s:if test='nameKey=="A"'>
        <tr>
        <td colspan="4">
        <table border="0" style="width: 100%">
          <tr>
            <td class="idx">客户</td>
            <td class="data" align="left" colspan="3"><s:property
              value="reckoning.client" />&nbsp;</td>
          </tr>

          <tr>
            <td class="idx">联系人</td>
            <td class="data" align="left" colspan="3"><s:property
              value="reckoning.contact" />&nbsp;</td>
          </tr>

          <tr>
            <td class="idx" width="80">电话</td>
            <td class="data"><s:property value="reckoning.phone" />&nbsp;</td>
            <td class="idx" width="80">传真</td>
            <td class="data"><s:property value="reckoning.fax" />&nbsp;</td>
          </tr>
          <tr>
            <td class="idx">线路</td>
            <td class="data" align="left" colspan="3"><s:property
              value="reckoning.routeName" />&nbsp;</td>
          </tr>

          <tr>
            <td class="idx">团号</td>
            <td class="data"><s:property value="reckoning.tourNo" />&nbsp;</td>
            <td class="idx">出团时间</td>
            <td class="data"><s:date name="reckoning.outDate" format="yyyy-MM-dd"/>&nbsp;</td>
          </tr>
          <tr>
            <td class="idx">人数</td>
            <td class="data" align="left" colspan="3"><s:property
              value="reckoning.pax" />&nbsp;</td>
          </tr>
        </table>
        </td>
      </tr>
      <tr>
        <td height="40" colspan="4">贵部委托我部操作的旅游团队，前期工作现已操作完毕。具体如下：</td>
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
                <td class="data"><s:property value="#rowCounter.count" /></td>
                <td class="data"><s:property value="name" /></td>
                <td class="data"><s:property value="amount" /></td>
                <s:if test="(#rowCounter.count)%2==0">
                  </tr>
                </s:if>
              </s:iterator>
              <s:if test="reckoningAcctList.size()%2==1">
                <td class="data">&nbsp;</td>
                <td class="data">&nbsp;</td>
                <td class="data">&nbsp;</td>
                </tr>
              </s:if>
            </s:if>

            <tr>
              <td class="data">&nbsp;</td>
              <td class="data" colspan="4">
              <center>合计</center>
              </td>
              <td class="data"><s:property value="reckoning.amount" /></td>
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
            <td class="data" align="left" colspan="3"><s:property
              value="reckoning.client" />&nbsp;</td>
          </tr>

          <tr>
            <td class="idx">联系人</td>
            <td class="data" align="left" colspan="3"><s:property
              value="reckoning.contact" />&nbsp;</td>
          </tr>

          <tr>
            <td class="idx" width="80">电话</td>
            <td class="data"><s:property value="reckoning.phone" />&nbsp;</td>
            <td class="idx" width="80">传真</td>
            <td class="data"><s:property value="reckoning.fax" />&nbsp;</td>
          </tr>
          <tr>
            <td class="idx">线路</td>
            <td class="data" align="left" colspan="3"><s:property
              value="reckoning.routeName" />&nbsp;</td>
          </tr>

          <tr>
            <td class="idx">团号</td>
            <td class="data"><s:property value="reckoning.tourNo" />&nbsp;</td>
            <td class="idx">出团时间</td>
            <td class="data"><s:date name="reckoning.outDate" format="yyyy-MM-dd"/>&nbsp;&nbsp;</td>
          </tr>
          <tr>
            <td class="idx">人数</td>
            <td class="data" align="left"><s:property value="reckoning.pax" />&nbsp;</td>
            <td class="idx">领队数</td>
            <td class="data" align="left"><s:property value="reckoning.leaderPax"/>&nbsp;</td>
          </tr>
        </table>
        </td>
      </tr>
      <tr>
        <td height="40" colspan="4">贵部委托我部操作的旅游团队，前期工作现已操作完毕。具体如下:</td>
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
            </tr>
            <s:if test="reckoning.reckoningAcctList!=null">
              <s:iterator value="reckoning.reckoningAcctList" status="rowCounter">
                <tr>
                  <td class="data"><s:property value="itemId" /></td>
                  <td class="data"><s:property value="description" /></td>
                  <td class="rdata"><s:property value="unitPrice" /></td>
                  <td class="rdata"><s:property value="count" /></td>
                  <td class="rdata"><s:property value="unit" /></td>
                  <td class="rdata"><s:property value="amount" /></td>
                </tr>
              </s:iterator>
            </s:if>
            <tr>
              <td class="data">&nbsp;</td>
              <td class="data" colspan="4">
              <center>合计</center>
              </td>
              <td class="rdata"><s:property value="reckoning.amount" /></td>
            </tr>

          </table>

          </td>
        </tr>
      </s:else>


      <tr>
        <td colspan="4">&nbsp;<br>
        <p>请将上述款项汇至我公司如下帐号：</p>
        户　名：XXXX旅行社<br>
        开户行：上海很行静安支行<br>
        帐　号：XXXXXXXXX-XXXX
        <p>
        </td>
      </tr>

      <tr>
        <td colspan="4"><strong>帐单状态：</strong></td>
      </tr>
      <tr>
        <td colspan="2">创建人：&nbsp;<s:property
          value="reckoning.createdBy" /></td>
        <td colspan="2">创建日期：&nbsp;<s:date name="reckoning.createDate" format="yyyy-MM-dd"/></td>
      </tr>
      <tr>
        <td colspan="2">打印次数：&nbsp;<s:property
          value="reckoning.printedCount" /></td>
        <td colspan="2">打印日期：&nbsp;<s:property
          value="reckoning.pDate" /></td>
      </tr>
      
    <s:if test="reckoningList.isEmpty() == false">
      <tr>
        <td colspan="4">
        <p>历史帐单：</p>

        </td>
      </tr>
      <tr>
        <td colspan="4">
          <s:iterator value="reckoningList" status="rowCounter">
            &nbsp;<a href="#" title="点击进入历史帐单" onclick="javascript:come(<s:property value="reckoningId"/>);"><s:property value="reckoningId"/>-<s:property value="version"/></a>&nbsp;
          </s:iterator>
        </td>
      </tr>
     </s:if>
     <s:else>
       <tr>
        <td colspan="4">&nbsp;</td>
      </tr>
     </s:else>
      
      <tr>
        <td align="center" colspan="4">
          <input type="button" onClick="javascript:print();" value="打印帐单"> 
          <s:if test='isPrint=="N"'>
            <s:submit action="ReckoningModify" value="修改帐单"></s:submit>
            <input type="button" onClick="javascript:goBack();" value="返回"> 
          </s:if>
          <s:else>
            <input type="button" onClick="javascript:comeBack();" value="返回"> 
          </s:else>
          
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
          <s:submit action="ReckoningModify" value="修改帐单"></s:submit> 
          <input type="button" onClick="javascript:print();" value="打印帐单"> 
          <input type="button" onClick="javascript:goBack();" value="返回"></td>
      </tr>
        <tr>
          <td colspan="4">&nbsp;</td>
        </tr>
        
     </table> 
      
      
      
      
  </s:elseif>

</s:form>

<s:form action="SalesBookDetail" namespace="/sales" method="POST"
  theme="simple">
  <s:hidden name="reserveNo"></s:hidden>
</s:form>

<s:form action="fopReport" namespace="/" method="POST" theme="simple">
  <s:hidden name="reserveNo"></s:hidden>
  <s:hidden name="reckoningId"></s:hidden>
  <s:hidden name="userId"></s:hidden>
  
 <s:if test='isShort=="N"'>
   <s:if test='nameKey=="A"'>
     <s:hidden name="reportId" value="10"></s:hidden>
   </s:if>
   <s:else>
     <s:hidden name="reportId" value="9"></s:hidden>
   </s:else>
 </s:if>
 <s:elseif test='isShort=="Y"'>
     <s:hidden name="reportId" value="11"></s:hidden>
 </s:elseif>
    
</s:form>


</body>
</html>
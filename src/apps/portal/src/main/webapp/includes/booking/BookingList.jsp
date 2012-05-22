<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<s:url value='/styles/layout.css' encode='false' includeParams='none'/>" type="text/css">
<title>同业订单查询--Search</title>
</head>
<br>

<body>
<script type="text/javascript" src="<s:url value='/scripts/lucene/luceneQueryEscaper.js' encode='false' includeParams='none'/>"></script>
<script type="text/javascript">
function tab(param)
{
    fieldF=document.getElementById("queryString");
    var queryStr=doEscapeQuery(fieldF);
    if(queryStr=="")
     {
        alert("请输入查询字符！");
        return;
     }else
     {
        fieldF.value = queryStr;
     }
  var fm = document.f1;
  document.getElementById("st").value = param;
  fm.submit();
}
function ShowDetail(name_no)
{
  var fm = document.BookDetail;
  fm.reserveNo.value = name_no;
  fm.submit();
}

function ShowRoute(routeNo)
{
  var fm = document.lineDetail;
  fm.routeNo.value = routeNo;
  fm.submit();
}


function actionSubmit()
{
    var fm = document.getElementById("");
}

</script>

<table style="width: 100%"  border="0" bgcolor="#b9c0ff">
  <tr>
    <td align="left" height="25"><font size="-1" style="font-weight: bold">搜索成果</td>
    <td align="right" ><font size="-1">共搜索到符合条件的订单&nbsp;<b><s:property value="bookList.size"/></b>&nbsp;个</font></td>
  </tr>
</table>

<table width="800">
  <tr>
    <td>
    <s:form action="BookSearchSubmit" namespace="/accounts" method="post" theme="simple">
    <table border="0" cellpadding="1" style="width: 100%">
      <tr>
        <td width="71"><font size="-1">分类：</font></td>
        <td><font size="-1">
        <s:radio list="classTypeList"
                 listKey="value"
                 listValue="label"
                 name="classType">
        </s:radio></font>
        </td>
      </tr>
      <tr>
        <td><font size="-1">线路：</font></td>
        <td><font size="-1">
        <s:textfield id="lineName" name="lineName" size="40" maxlength="80"></s:textfield>
        [输入线路名称，例如：普吉，则列出线路名称带普吉订单]</font>
        </td>
      </tr>
      <tr>
        <td><font size="-1">出发日期：</font></td>
        <td>
		    <input name="startDatePeriod" size="10" type="text" onClick="WdatePicker()"/> 至
		    <input name="endDatePeriod" size="10" type="text" onClick="WdatePicker()"/>
        </td>
      </tr>
      <tr>
        <td><font size="-1">预订日期：</font></td>
        <td>
        <input name="reserveStartDatePeriod" size="10" type="text" onClick="WdatePicker()"/> 至
        <input name="reserveEndDatePeriod" size="10" type="text" onClick="WdatePicker()"/>
        </td>
      </tr>
      <tr>
        <td><font size="-1">合同号：</font></td>
        <td><font size="-1">
        <s:textfield id="contractNo" name="contractNo" size="16" maxlength="16"></s:textfield></font>
        </td>
      </tr>
      <tr>
        <td><font size="-1">发票号：</font></td>
        <td><font size="-1">
        <s:textfield  name="invoiceNo" size="16" maxlength="16"></s:textfield></font>
        </td>
      </tr>
      <tr>
        <td><font size="-1">客人姓名：</font></td>
        <td><font size="-1">
        <s:textfield id="touristName" name="touristName" size="10" maxlength="10"></s:textfield>
        [输入客人姓名，例如：张，则列出客人姓名中带张字的订单]</font>
        </td>
      </tr>
      <tr>
        <td><font size="-1">状态：</font></td>
        <td><font size="-1">
        <s:radio list="bookStateList"
                  listKey="value"
                  listValue="label"
                  name="bookState">
        </s:radio></font>
        </td>
      </tr>
      <tr>
        <td><font size="-1">取消否：</font></td>
        <td><font size="-1">
        <s:radio list="cancelStateList"
                  listKey="value"
                  listValue="label"
                  name="cancelState">
        </s:radio>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <s:submit value="查　询"></s:submit>
        
        </font>
        </td>
      </tr>
    </table>
    </s:form>
    </td>
  </tr>
  <tr>
    <td>
    <table style="width: 100%" bordercolor="#000000" border="1" bordercolor="#b9c0ff" cellpadding="2">
      <tr bgcolor="#b9c0ff">
        <th align="center"><font size="-1">No.</font></th>
        <th align="center"><font size="-1">订单号</font></th>
        <th align="center"><font size="-1">线路</font></th>
        <th align="center"><font size="-1">预订日期</font></th>
        <th align="center"><font size="-1">出发日期</font></th>
        <th align="center" nowrap><font size="-1">预订人数</font></th>
        <th align="center" nowrap><font size="-1">名单人数</font></th>
        <th align="center"><font size="-1">应收款</font></th>
        <th align="center"><font size="-1">已收款</font></th>
        <th align="center"><font size="-1">未收款</font></th>
        <!--  
        <th align="center"><font size="-1">结算款</font></th>
        -->
        <th align="center"><font size="-1">审核否</font></th>
        <th align="center"><font size="-1">操作</font></th>
      </tr>

      <s:iterator value="bookList" status="rowcounter">
      <s:if test='delkey eq "Y"'>
      <tr bgcolor="#FFDDEE">
        <td align="center"><font size="-1"><s:property value="#rowcounter.count"/></font></td>
        <td align="center"><font size="-1"><a href="javascript:ShowDetail('<s:property value='recordNo'/>');" title="点击查看该订单的详细内容！"><s:property value='recordNo'/></a></font></td>
        <td><font size="-1"><a href="javascript:ShowRoute('<s:property value='lineNo'/>');" title="点击查看该线路的详细内容！"><s:property value="lineName" /></a></font></td>
        <td align="center" nowrap><font size="-1"><s:date name="reserveDate" format="yyyy-MM-dd"/></font></td>
        <td align="center" nowrap><font size="-1"><s:date name="outDate" format="yyyy-MM-dd"/></font></td>
        <td align="right"><font size="-1"><s:property value="pax"/></font></td>
        <td align="right"><font size="-1"><s:property value="importPax"/>&nbsp;</font></td>
        <td align="right"><font size="-1"><s:property value="dbamt"/></font></td>
        <td align="right"><font size="-1"><s:property value="payCosts"/></font></td>
        <td align="right"><font size="-1"><s:property value="%{dbamt -payCosts}"/></font></td>
        <td align="center"><font size="-1">已取消&nbsp;</font></td>
        <td align="center"><font size="-1">&nbsp;</font></td>
      </tr>
      </s:if>

      <s:else>
      <tr onMouseOver="this.style.backgroundColor='#CCFFCC'"	onmouseout="this.style.backgroundColor='#ffffff'">
        <td class="cdata"><font size="-1"><s:property value="#rowcounter.count"/></font></td>
        <td class="cdata"><font size="-1"><a href="javascript:ShowDetail('<s:property value='recordNo'/>');" title="点击查看该订单的详细内容！"><s:property value='recordNo'/></a></font></td>
        <td><font size="-1"><a href="javascript:ShowRoute('<s:property value='lineNo'/>');" title="点击查看该线路的详细内容！"><s:property value="lineName" /></a></font></td>
        <td class="cdata" nowrap><font size="-1"><s:date name="reserveDate" format="yyyy-MM-dd"/></font></td>
        <td class="cdata" nowrap><font size="-1"><s:date name="outDate" format="yyyy-MM-dd"/></font></td>
        <td class="rdata"><font size="-1"><s:property value="pax"/></font></td>
        <td class="rdata"><font size="-1"><s:property value="importPax"/>&nbsp;</font></td>
        <td class="rdata"><font size="-1"><s:property value="dbamt"/></font></td>
        <td class="rdata"><font size="-1"><s:property value="payCosts"/></font></td>
        <td class="rdata"><font size="-1"><s:property value="%{dbamt -payCosts}"/></font></td>
        <td class="cdata"><font size="-1"><s:property value="cfmKey"/>&nbsp;</font></td>
        <s:if test='tourNoticeIsExist eq "Y"'>
           <td align="center"><font size="-1"><a href="http://localhost:8080<s:property value="tourNoticeFilepath"/>">出团通知</a></font></td>
        </s:if>
        <s:else>
           <td align="center">&nbsp;</td>
        </s:else>
      </tr>
      </s:else>
      </s:iterator>

      <!-- 
      <tr bgcolor="#b9c0ff">
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>合计</td>
        <td>&nbsp;</td>
        <td>27</td>
        <td>18</td>
        <td align="right">&nbsp;</td>
        <td align="right">&nbsp;</td>
        <td align="right">&nbsp;</td>
        <td align="right">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
       -->
    </table>
    </td>
  </tr>
</table>

<s:form action="BookDetail" theme="simple" namespace="/accounts" method="POST">
<s:hidden name="reserveNo"></s:hidden>
</s:form>

<s:form action="lineDetail" method="post" namespace="/product">
  <s:hidden name="routeNo"></s:hidden>
</s:form>

</body>
</html>

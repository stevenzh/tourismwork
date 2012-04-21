<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配送单查询</title>
<meta name="menu" content="ExpressMenu"/>
<meta name="heading" content="配送单查询">
</head>

<body>
<script language="javascript">
<!--//

/** 订单详细 */
function ShowDetail(name_no)
{
	var fm=document.submitExpressSearch;
	fm.reserveNo.value = name_no;
	fm.action="<s:url action='SalesBookDetail' namespace='/sales' includeParams='none'/>";
	fm.submit();
}

/** 配送单详细 */
function showExpress(param)
{
	var fm=document.submitExpressSearch;
	fm.expressId.value = param;
	fm.action="<s:url action='showExpressDetail' namespace='/express' includeParams='none'/>";
	fm.submit();
}

/** 删除配送单 */
function deleteExpress()
{
	if(confirm("您确定要删除数据吗？"))
	{
		var cb = document.all.nameNo;
		var array=new Array();
		var j=0;
		for(i=0;i<cb.length;i++)
		{
			if(cb[i].checked)
			{
				array[j]=cb[i].value;
				j++;
			}
		}
	}
}


/* 分页列表 */
function _getlist(type)
{
  var frm = document.submitExpressSearch;
  if (type=="page")
  {
    type = document.getElementById("movePg").value;
  }
  document.getElementById("movePage").value = type ;
  frm.submit();
}


function selectAll(param)
{
	var cb = document.all.nameNo;
	if(param == 'Y')
	{
		for(i=0;i<cb.length;i++)
		{
			cb[i].checked = true;
		}
	}
	if(param == 'N')
	{
		for(i=0;i<cb.length;i++)
		{
			cb[i].checked = false;
		}
	}
}

//-->
</script>
<s:form action="submitExpressSearch" namespace="/express" method="post" theme="simple">
  <s:hidden name="expressId"></s:hidden>
  <s:hidden name="reserveNo"></s:hidden>

  <table align="center" border="0" style="width: 100%">
    <tr>
      <td><!-- 查询区开始 -->
      <table align="left" border="0" style="width: 100%">
          <tr>
            <td class="idx">配送单号：</td>
            <td><s:textfield name="kenExpressId" size="20" maxlength="10" /></td>
            <td class="idx">联系人：</td>
            <td><s:textfield name="kenContactor" size="20" /></td>
          </tr>
          <tr>
            <td class="idx">配送方式：</td>
            <td>
            <s:select list="expressTypeList" name="kenExpressType" listKey="value" listValue="label" headerKey="" headerValue="全部"></s:select>
            </td>
            <td class="idx">配送类型：</td>
            <td><s:select list="expressModlueList" name="kenExpressModlue" listKey="value" listValue="label" headerKey="" headerValue="全部"></s:select></td>
          </tr>
          <tr>
            <td class="idx">收款类别：</td>
            <td><s:select list="payTypeList" name="kenPayType" listKey="value" listValue="label" headerKey="" headerValue="全部"></s:select></td>
            <td class="idx">支付方式：</td>
            <td><s:select list="payModlueList" name="kenPayModlue" listKey="value" listValue="label" headerKey="" headerValue="全部"></s:select></td>
          </tr>
          <tr>
            <td class="idx">配送状态：</td>
            <td><s:select list="expressStateList" name="kenExpressState" listKey="value" listValue="label" headerKey="" headerValue="全部"></s:select></td>
            <td class="idx">团号：</td>
            <td><s:textfield name="kenTeamNo" size="30"></s:textfield></td>
          </tr>
          <tr>
            <td class="idx">旅行线路：</td>
            <td><s:textfield name="kenLine" size="30"></s:textfield></td>
            <td class="idx">配送时间：</td>
            <td><sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenExpressStart">
            </sj:datepicker>&nbsp;至 <sj:datepicker size="10" displayFormat="yy-mm-dd" maxlength="10" name="kenExpressEnd">
            </sj:datepicker></td>
          </tr>
          <tr>
            <td colspan="4"></td>
            <td><s:submit value="%{getText('common.forms.search')}"></s:submit></td>
          </tr>
      </table>
      <!-- 查询区结束 -->
      </td>
    </tr>
  </table>

  <table align="center" border="0" style="width: 100%">
    <tr>
      <td>
　　　　<input type="button" onclick="javascript:selectAll('Y');" value="全选">
　　　　<input type="button" onclick="javascript:selectAll('N');" value="全不选">
      </td>
    </tr>
    <tr>
      <td valign="top">
      <table border="1" bordercolor="#b9c0ff" cellpadding="1" style="width: 100%">
        <tr bgcolor="#b9c0ff">
          <td class="lstidx">&nbsp;</td>
          <td class="lstidx">No.</td>
          <td class="lstidx">配送单号</td>
          <td class="lstidx" nowrap="nowrap">预订单号</td>
          <td class="lstidx">客户/旅行社</td>
          <td class="lstidx">地址</td>
          <td class="lstidx" nowrap="nowrap">联系人</td>
          <td class="lstidx">旅行线路</td>
          <td class="lstidx">团号</td>
          <td class="lstidx">配送时间</td>
          <td class="lstidx">应收团款</td>
        </tr>

        <s:iterator value="expressList" status="rowcounter">
          <s:if test="#rowcounter.count > fromRecord">
            <s:if test="#rowcounter.count <= toRecord">
                <tr>
                  <td class="cdata"><s:checkbox id="nameNo" name="nameNo" fieldValue="%{expressId}" /></td>
                  <td class="cdata"><s:property value="#rowcounter.count" /></td>
                  <td class="data"><a href="javascript:showExpress('<s:property value='expressId'/>');"
                    title="点击查看该配送单的详细内容！"><s:property value="expressId" /></a></td>

                  <td class="cdata"><a href="javascript:ShowDetail('<s:property value='orderId'/>');"
                    title="点击查看该订单的详细内容！"><s:property value='orderId' /></a></td>
                  <td class="data"><s:property value="customer" />&nbsp;</td>
                  <td class="data"><s:property value="address" />&nbsp;</td>
                  <td class="data" nowrap="nowrap"><s:property value="contactor" />&nbsp;</td>
                  <td class="data"><s:property value="lineName" />&nbsp;</td>
                  <td class="data"><s:property value="teamNo" />&nbsp;</td>
                  <td class="rdata"><s:date name="expressDate" format="yyyy-MM-dd" />&nbsp;</td>
                  <td class="rdata"><s:property value="pay" />&nbsp;</td>
                </tr>
            </s:if>
          </s:if>
        </s:iterator>
        
        <authz:authorize ifAnyGranted="ROLE_SUPERUSER">
          <tr>
            <td align="center" colspan="15"><input type="button" onclick="javascript:deleteExpress()" value="删除选中订单" /></td>
          </tr>
        </authz:authorize>
      </table>

      <%@ include file="/includes/PagerTable.jsp" %>
      </td>
    </tr>
  </table>

</s:form>

</body>
</html>

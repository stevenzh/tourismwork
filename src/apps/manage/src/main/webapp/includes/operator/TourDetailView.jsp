<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>旅游团队信息</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="旅游团队信息">
</head>

<body>
<script language="javascript">
<!--//

function showCustomer(target)
{
  var fm = document.CustomerDetail;
  fm.nmno.value=target;
  fm.submit();
}

function simplePrint(tourNo)
{
    var fm = document.CustomerDetail;
    fm.tourNo.value = tourNo;
    fm.action="<s:url action='SimplePrint' namespace='/finance' includeParams='none' />";
    fm.submit();
}

//-->
</script>

<s:form action="CustomerDetail" namespace="/finance" method="POST" theme="simple">
<s:hidden name="nmno"></s:hidden>
<s:hidden name="tourNo"></s:hidden>

<table align="center" border="0" bordercolor="#000000" style="width: 100%">
  <tr>
    <td class="idx" width="15%">团号:</td>
    <td colspan="3"><s:property value="tour.tourNo"/></td>
  </tr>
  <tr>
    <td class="idx" width="15%">线路：</td>
    <td colspan="3"><a href='http://localhost:8080/RouteDetail.action?routeNo=<s:property value="tour.routeNo"/>' target="_blank">
    <s:property value="tour.lineName"/></a>
    </td>
  </tr>
  <tr>
    <td class="idx">出境日期：</td>
    <td>
     <s:date name="tour.outDate" format="yyyy-MM-dd"/>
    </td>
    <td class="idx" width="15%">出境口岸：</td>
    <td>
     <s:select name="tour.outCity"
              list="portCitys"
              listKey="citycd"
              listValue="citynm"
              disabled="true"
              emptyOption="true">
    </s:select>
    </td>
  </tr>
  <tr>
    <td class="idx">入境日期：</td>
    <td>
     <s:date name="tour.inDate" format="yyyy-MM-dd"/>
    </td>
    <td class="idx">入境口岸：</td>
    <td>
      <s:select name="tour.inCity"
              list="portCitys"
              listKey="citycd"
              listValue="citynm"
              disabled="true"
              emptyOption="true">
    </s:select>
    </td>
  </tr>
  <tr>
    <td class="idx">聚合城市</td>
    <td colspan="3">
      <s:select name="tour.venue"
              list="portCitys"
              listKey="citycd"
              listValue="citynm"
              disabled="true"
              emptyOption="true">
    </s:select>
    </td>
  </tr>
  <tr>
    <td class="idx">总人数：</td>
    <td colspan="3"><s:property value="tour.pax"/>人&nbsp;（男:<s:property value="tour.malePax"/>人、
     女:<s:property value="tour.femalePax"/>人、领队:<s:property value="tour.leadPax"/>人]</td>
  </tr>
  <tr>
    <td class="idx">订房数：</td>
    <td colspan="3">
      双人间:<s:property value="tour.doubleRoom"/>&nbsp;
      单人间:<s:property value="tour.singleRoom"/>&nbsp;
      加床:<s:property value="tour.extraBedRoom"/>
    </td>
  </tr>

  <tr>
    <td class="idx">建团人:</td>
    <td><s:property value="tour.creator"/>&nbsp;</td>
    <td class="idx">创建时间:</td>
    <td><s:date name="tour.dateCreated"/>&nbsp;</td>
  </tr>
  <tr>
    <td class="idx">修改人:</td>
    <td><s:property value="tour.opUser"/>&nbsp;</td>
    <td class="idx">修改时间:</td>
    <td><s:date name="tour.opDate" />&nbsp;</td>
  </tr>

  <tr>
    <td class="idx">备注：</td>
    <td colspan="3"><s:property value="tour.remarks"/>&nbsp;</td>
  </tr>

  <tr>
    <td colspan="4">
    <table bordercolor="#000000" border="0" style="width: 100%">
        <tr>
          <td class="lstidx">No.</td>
          <td class="lstidx">姓名</td>
          <td class="lstidx">拼音</td>
          <td class="lstidx">性别</td>
          <td class="lstidx">出生日期</td>
          <td class="lstidx">出生地</td>
          <td class="lstidx">护照种类</td>
          <td class="lstidx">护照号码</td>
          <td class="lstidx">签发日期</td>
          <td class="lstidx">有效日期</td>
          <td class="lstidx">签发地点</td>
          <td class="lstidx">护照说明</td>
          <td class="lstidx">领队</td>
          <td class="lstidx">客户</td>
          <td class="lstidx">备注</td>
          <td class="lstidx">客户备注</td>
        </tr>

        <s:iterator value="tour.customerList" status="rowccount">
        <s:if test="uniteStatus eq \"N\"">
        <tr bgcolor="#FFDDDD">
          <td align="center"><s:property value="#rowccount.count"/></td>
          <td align="center"><a href="javascript:showCustomer('<s:property value="nmno"/>');" title="点击查看客人的详细内容！"><s:property value="userName"/></a></td>
          <td align="center"><s:property value="pinYin"/> </td>
          <td><s:property value="sex"/>&nbsp;</td>
          <td align="center"><s:date name="birthday" format="yyyy-MM-dd"/>&nbsp;</td>
          <td><s:property value="birthplaceName"/>&nbsp;</td>
          <td><s:property value="passportType" />&nbsp;</td>
          <td><s:property value="passportNo"/>&nbsp;</td>
          <td align="center"><s:date name="passportDate" format="yyyy-MM-dd"/>&nbsp;</td>
          <td align="center"><s:date name="passportExpiry" format="yyyy-MM-dd"/>&nbsp;</td>
          <td><s:property value="passportPlaceName"/>&nbsp;</td>
          <td><s:date name="passportAnnotation"/>&nbsp;</td>
          <td><s:property value="leaderKey"/>&nbsp;</td>
          <td><s:property value="customerName"/>&nbsp;</td>
          <td>未成团</td>
          <td><s:property value="remarks"/></td>
        </tr>
        </s:if>
        <s:else>
        <tr>
          <td class="cdata"><s:property value="#rowccount.count"/></td>
          <td class="cdata"><a href="javascript:showCustomer('<s:property value="nmno"/>');" title="点击查看客人的详细内容！"><s:property value="userName"/></a></td>
          <td class="cdata"><s:property value="pinYin"/> </td>
          <td class="data"><s:property value="sex"/>&nbsp;</td>
          <td class="cdata"><s:date name="birthday" format="yyyy-MM-dd"/>&nbsp;</td>
          <td class="data"><s:property value="birthplaceName"/>&nbsp;</td>
          <td class="data"><s:property value="passportType" />&nbsp;</td>
          <td class="data"><s:property value="passportNo"/>&nbsp;</td>
          <td class="cdata"><s:date name="passportDate" format="yyyy-MM-dd"/>&nbsp;</td>
          <td class="cdata"><s:date name="passportExpiry" format="yyyy-MM-dd"/>&nbsp;</td>
          <td class="data"><s:property value="passportPlaceName"/>&nbsp;</td>
          <td class="data"><s:date name="passportAnnotation"/>&nbsp;</td>
          <td class="data"><s:property value="leaderKey"/>&nbsp;</td>
          <td class="data"><s:property value="customerName"/>&nbsp;</td>
          <td class="data">已成团</td>
          <td class="data"><s:property value="remarks"/></td>
        </tr>
        </s:else>
        </s:iterator>

    </table>
    </td>
  </tr>
  <tr>
    <td colspan="4" align="center">
      <input type="button" value="返回" onClick="javascript:history.go(-1)">
      <input type="button" value="简单打印" onClick="javascript:simplePrint('<s:property value="tour.tourNo"/>')">
  </td>
  </tr>
</table>
</s:form>


</body>
</html>

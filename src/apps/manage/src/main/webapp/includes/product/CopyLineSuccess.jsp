<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>线路复制成功</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="线路复制成功">
</head>

<body>
<script type="text/javascript">
<!--//
function cancel()
{
  var fm=document.RouteChange;
  fm.action="<s:url action='searchRoute' namespace='/product' includeParams='none'/>"
  fm.submit();
}

//-->
</script>
<s:form action="RouteChange" namespace="/product" method="post" theme="simple">
  <s:hidden name="kenUserId"></s:hidden>
  <s:hidden name="kenClasskey"></s:hidden>
  <s:hidden name="kenDepartmentNo"></s:hidden>
  <s:hidden name="kenRouteName"></s:hidden>
  <s:hidden name="kenClosekey"></s:hidden>
  <s:hidden name="line.lineNo" />
  <table style="width: 100%">
    <tr>
      <td class="tdLabel">线路名:</td>
      <td colspan="3"><s:property value="line.lineName"/></td>
    </tr>
    <tr>
      <td class="tdLabel">天数:</td>
      <td colspan="3"><s:property value="route.lineDay"/></td>
    </tr>
    <tr>
      <td class="tdLabel"><label class="label">工作组:</label></td>
      <td colspan="3">
        <s:select list="teamList"
                  listKey="teamId"
                  listValue="name"
                  value="%{route.departmentNo}"
                  disabled="true">
        </s:select>
      </td>
    </tr>

    <tr>
      <td class="tdLabel">出发城市:</td>
      <td colspan="3">
        <s:select name="route.outCity"
                  list="outCityList"
                  listKey="citycd"
                  listValue="%{citycd + citynm}"
                  disabled="true">
        </s:select>
      </td>
    </tr>

    <s:radio label="属性"
             name="route.classKeyContent"
             list="keyContentList"
             listKey="value"
             listValue="label"
             disabled="true"
             theme="xhtml">
    </s:radio>
  
    <s:select label="目的地"
              name="route.destination.destId"
              list="destinationList"
              listKey="destId"
              listValue="%{code +' '+ cnName}"
              disabled="true"
              theme="xhtml">
    </s:select>
  
    <s:select label="交通"
              name="route.classKeyVehicle"
              list="vehicleList"
              listKey="value"
              listValue="label"
              disabled="true"
              theme="xhtml">
    </s:select>
  
    <s:radio label="状态"
             name="route.isActive"
             list="closeKeyList"
             listKey="value"
             listValue="label"
             disabled="true"
             theme="xhtml">
    </s:radio>
 
    <tr>
      <td colspan="4">&nbsp;</td>
    </tr>
    <tr>
      <td class="idx">登记人:</td>
      <td><s:property value="route.createUserId" /></td>
      <td class="idx">修改人:</td>
      <td><s:property value="route.operateUserId" /></td>
    </tr>
    <tr>
      <td class="idx">登记日期:</td>
      <td><s:date name="route.createDate" format="yyyy-MM-dd HH:mm:ss" /></td>
      <td class="idx">修改日期:</td>
      <td><s:date name="route.operateDate" format="yyyy-MM-dd HH:mm:ss" /></td>
    </tr>
    <tr>
      <td colspan="4">
      <s:submit action="RouteChangeSubmit" value="提交"></s:submit>
      <input type="button" value="返回" onclick="javascript:cancel();">
      </td>
    </tr>
  </table>
</s:form>

</body>
</html>

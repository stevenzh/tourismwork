<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑产品信息</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="编辑产品信息">
</head>
<body>
<script type="text/javascript">
function change()
{
  var combo = document.getElementById("userId")
  while (combo.options.length>0) {
      combo.remove(0);
  }
  combo.disabled=false;

  $.ajax({
      type: "post",
      url: '<s:url value="/json/listEmployee.jspa" encode="false" includeParams="none"/>',
      timeout: 20000,
      error: function(){
          // alert('服务器错误');
      },
      data: {groupId: $("#productTeamId").val()},
      async: false,
      success: function(data){
          $.each(data, function(i, n){

          if (typeof(n)=='object')
          {
              combo.options.add(new Option("全部", ""));
              
              $.each(n,function(j,m){
                combo.options.add(new Option(m, j));
              });
          }
      });
      }
  });
}

</script>
<s:form action="saveRouteChange" namespace="/product" method="post">
  <s:hidden name="kenUserId" />
  <s:hidden name="kenClasskey" />
  <s:hidden name="kenDepartmentNo" />
  <s:hidden name="kenRouteName" />
  <s:hidden name="kenClosekey" />
  <s:hidden name="kenDestination" />

  <s:hidden name="routeNo" />
  <s:hidden name="route.lineNo" />

  <s:textfield label="线路名"
               name="route.lineName"
               required="true"
               requiredposition="right"
               size="60"
               maxLength="60" />

  <s:textfield label="天数"
               name="route.lineDay"
               required="true"
               requiredposition="right"
               size="3"
               maxLength="3" />

  <s:select id="productTeamId"
            label="产品工作组"
            name="route.team.teamId"
            list="productTeams"
            listKey="teamId"
            listValue="name"
            required="true"
            requiredposition="right"
            onchange="javascript:change();">
  </s:select>
  <s:select id="userId"
            label="产品负责人"
            name="route.assigned.userId"
            list="employees"
            listKey="userId"
            listValue="userName"
            required="true"
            requiredposition="right">
  </s:select>

  <s:select label="计调工作组"
            name="route.opTeam.teamId"
            list="operatorTeams"
      	    listKey="teamId"
      	    listValue="name"
            required="true"
            requiredposition="right">
  </s:select>

  <s:radio label="属性"
           name="route.classKeyContent"
           list="keyContentList"
           listKey="value"
           listValue="label"
           required="true"
           requiredposition="right">
  </s:radio>

  <s:select label="目的地"
            name="route.destination.destId"
            list="destinationList"
            listKey="destId"
            listValue="%{code +' '+ cnName}"
            required="true"
            requiredposition="right">
  </s:select>

  <s:select label="交通"
            name="route.classKeyVehicle"
            list="vehicleList"
            listKey="value"
            listValue="label"
            required="true"
            requiredposition="right">
  </s:select>

  <s:select label="出发城市"
            name="route.outCity.citycd" 
            list="outCityList"
            listKey="citycd"
            listValue="%{citycd + citynm}"
            required="true"
            requiredposition="right">
  </s:select>
  <s:select label="入境口岸"
            name="route.portOfEntry"
            list="portOfEntryList"
            listKey="value"
            listValue="label"
            required="true"
            requiredposition="right">
  </s:select>
  
  <s:select label="出境口岸"
            name="route.portOfDeparture"
            list="portOfDepartureList"
            listKey="value"
            listValue="label"
            required="true"
            requiredposition="right">
  </s:select>

  <s:radio label="状态"
           name="route.isActive"
           list="closeKeyList"
           listKey="value"
           listValue="label">
  </s:radio>

  <s:textfield label="副标题" name="route.title" maxlength="100" size="50"/>

  <s:textarea label="广告词" name="route.description" cols="50" rows="4">
  </s:textarea>

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
    <td colspan="4"><s:submit value="提交"></s:submit></td>
  </tr>
</s:form>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>复制线路</title>
<meta name="menu" content="ProductMenu"/>
<meta name="heading" content="复制线路">
</head>
<body>
<script type="text/javascript">
<!--//
function cancel()
{
  var fm=document.submitCopyRoute;
  fm.action="<s:url action='searchRoute' namespace='/product' includeParams='none'/>"
  fm.submit();
}

//-->
</script>

<s:form action="submitCopyRoute" namespace="/product" method="POST" theme="simple">
  <s:hidden name="kenUserId"></s:hidden>
  <s:hidden name="kenDepartmentNo"></s:hidden>
  <s:hidden name="kenRouteName"></s:hidden>
  <s:hidden name="kenClosekey"></s:hidden>
  <s:hidden name="lineNo" value="%{route.lineNo}" />

  <table border="0" cellpadding="2" cellspacing="0" width="100%">
    <tr>
      <td colspan="2"><strong>原线路信息</strong></td>
    </tr>
    <tr>
      <td width="160">线路名:</td> 
      <td><s:property value="route.lineName"/></td>
    </tr>
    <tr>
      <td>线路天数:</td>
      <td><s:property value="route.lineDay"/></td>
    </tr>
    <tr>
      <td colspan="2"><strong>新线路信息</strong></td>
    </tr>
    <tr>
      <td>新线路名：</td>
      <td><s:textfield name="newLineName"/></td>
    </tr>
    <tr>
      <td>是否复制行程</td>
      <td>
        <s:radio name="copySchedule"
                 list="status"
                 listKey="value"
                 listValue="label">
        </s:radio>
       </td>
    </tr>
    <tr>
      <td>是否复制线路特色</td>
      <td>
        <s:radio name="copyFeature"
                 list="status"
                 listKey="value"
                 listValue="label">
        </s:radio>
      </td>
     </tr>
    <tr>
      <td>是否复制目的地</td>
      <td>
        <s:radio name="copyDestination"
                 list="status"
                 listKey="value"
                 listValue="label">
        </s:radio>
      </td>
    </tr>
    <tr>
      <td>是否复制景点</td>
      <td>
        <s:radio name="copySight"
                 list="status"
                 listKey="value"
                 listValue="label">
        </s:radio>
      </td>
    </tr>
    <tr>
      <td>是否复制签证</td>
      <td>
        <s:radio name="copyVisa"
                 list="status"
                 listKey="value"
                 listValue="label">
        </s:radio>
      </td>
    </tr>
    <tr>
       <td>是否复制报价</td>
       <td>
         <s:radio name="copyPrice"
                  list="status"
                  listKey="value"
                  listValue="label">
         </s:radio>
       </td>
    </tr>
    <tr>
      <td>
        <s:submit value="确定复制"></s:submit>
        <input type="button" value="返回" onclick="javascript:cancel();">
      </td>
    </tr>
  </table>
  <br>
</s:form>
</body>
</html>

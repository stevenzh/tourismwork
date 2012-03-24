<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>境外报团名单</title>
<meta name="menu" content="OperatorMenu"/>
<meta name="heading" content="境外报团名单">
</head>

<script language="javascript">

</script>




<body>
<script type="text/javascript">
function selectChanged()
{
   var fm = document.OutBandChange;
   fm.submit();
}

function printRoute(param)
{
  var fm = document.fopReport;
  document.getElementById('paramid').value = param;
  if(document.getElementById('object_Type').value=="")
  {
     alert("请输入对象名！");
     return;
  }
  document.getElementById('objectType1').value = document.getElementById('object_Type').value;
  document.getElementById('userName1').value = document.getElementById('user_Name').value;
  document.getElementById('label1').value = document.getElementById('label_1').value;
  document.getElementById('label2').value = document.getElementById('label_2').value;
  document.getElementById('label3').value = document.getElementById('label_3').value;
  fm.submit();
}
</script>

<s:form action ="OutBandChange" namespace="/operator" method="post" theme="simple">

  <table border="0" cellpadding="0" width="100%">
    <tr>
      <td>对象：</td>
      <td>
       <s:textfield id="object_Type" name="objectType"></s:textfield>
       <s:hidden id="user_Name" name="userName"></s:hidden>
       <s:select
        id="outBandObject" list="outBandObjectList" name="outBandObject"
        listKey="type" listValue="showStr" headerKey="" headerValue="选择"
        onchange="javascript:selectChanged();">
      </s:select></td>
    </tr>
    <tr>
      <td>标注一：</td>
      <td><s:textarea id="label_1" name="label_1" cols="100"
        rows="3"></s:textarea></td>
    </tr>
    <tr>
      <td>标注二：</td>
      <td><s:textarea id="label_2" name="label_2" cols="100"
        rows="2"></s:textarea></td>
    </tr>
    <tr>
      <td>标注三：</td>
      <td><s:textarea id="label_3" name="label_3" cols="100"
        rows="10"></s:textarea></td>
    </tr>

  </table>
  <br>

  <table border="0" cellpadding="2" cellspacing="2" width="100%">
  <tr><td>
    <input type="button" value="打印"
      onclick="javascript:printRoute('<s:property value='tourNo' />')" />
  </td></tr>
  </table>
</s:form>
<s:form action="fopReport" namespace="/" method="POST" theme="simple">
  <s:hidden name="parameters(0).name" value="TOUR_NO"></s:hidden>
  <s:hidden id="paramid" name="parameters(0).data"></s:hidden>
  <s:hidden name="parameters(1).name" value="LABEL_ONE"></s:hidden>
  <s:hidden id="label1" name="parameters(1).data"></s:hidden>
  <s:hidden name="parameters(2).name" value="LABEL_TWO"></s:hidden>
  <s:hidden id="label2" name="parameters(2).data"></s:hidden>
  <s:hidden name="parameters(3).name" value="LABEL_THREE"></s:hidden>
  <s:hidden id="label3" name="parameters(3).data"></s:hidden>
  <s:hidden name="parameters(4).name" value="OBJECT_TYPE"></s:hidden>
  <s:hidden id="objectType1" name="parameters(4).data"></s:hidden>
  <s:hidden name="parameters(5).name" value="USER_NAME"></s:hidden>
  <s:hidden id="userName1" name="parameters(5).data"></s:hidden>
  <s:hidden name="reportId" value="5"></s:hidden>
</s:form>

</body>

</html>



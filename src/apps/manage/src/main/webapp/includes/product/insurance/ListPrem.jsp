<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>险种列表</title>
<meta name="menu" content="SettingMenu"/>
<meta name="heading" content="险种列表">
</head>
<body>

<script type="text/javascript">
function Shows(target)
{
  var fm = document.VisaFind;
  fm.recordNo.value = target;
  fm.action= "<s:url action='VisaDetail' namespace='/resource'/>"
  fm.submit();
}

function SubmitForm(param,target)
{
  var fm = document.listPremium;
  fm.preminuCode.value = target;
  if (param =='delete') 
  {
   if(confirm('确定删除吗？') == false)
   {
     return;
   }
  fm.action = "<s:url action='deletePremium' namespace='/product'/>"
  } 
  else if (param == 'modify') 
  {
  fm.action = "<s:url action='editPremium' namespace='/product' />"
  }
  fm.submit();
}

</script>

<table align="left" border="0" style="width: 100%">
  <s:form method="post" action="listPremium" namespace="/product" theme="simple">
 <s:hidden name="preminuCode"></s:hidden>

    <tr>
      <td>
      <table border="0" style="width: 100%">
        <tr bgcolor="#f3f3f3">
          <td class="lstidx">险种</td>
          <td class="lstidx" align="center">保费</td>
          <td class="lstidx" align="center">保险期限(天)</td>
          <td class="lstidx" align="center">意外保额(万)</td>
          <td class="lstidx" align="center">意外医疗保额(万)</td>
          <td class="lstidx" align="center">补充保额(万)</td>
          <td class="lstidx" align="center">开始日期</td>
          <td class="lstidx" align="center">说明</td>
          <!-- 
          <td class="lstidx" align="center">补充保额</td>
           -->
          <td class="lstidx" colspan="2" align="center">操作</td>
        </tr>

        <s:iterator value="preminus" status="rowcounter">
          <tr>
            <td class="cdata"><s:property value="precode" /></td>
            <td class="cdata"><s:property value="prem" /></td>
            <td class="cdata"><s:property value="preday" /></td>
            <td class="cdata" nowrap="nowrap"><s:property value="ywpre" /></td>
            <td class="cdata" nowrap="nowrap"><s:property value="ylpre" /></td>
            <td class="cdata" nowrap="nowrap"><s:property value="bcpre" /></td>
            <td class="cdata"><s:property value="dodate" /></td>
            <td class="cdata"><s:property value="note" /></td>
            <!-- 
            <td class="data"><s:property value="bcpre" /></td>
             -->
            <td align="center"><a href="javascript:SubmitForm('modify','<s:property value="precode"/>')">修改</a>
             <a href="javascript:SubmitForm('delete','<s:property value="precode"/>')">取消</a></td>
          </tr>
        </s:iterator>
      </table>
      <div align="center"><br>
      <input type="button" value="添加" onclick="javascript:SubmitForm('modify','<s:property value="precode"/>')"">
      </div>
      </td>
    </tr>
  </s:form>
</table>
</body>
</html>
